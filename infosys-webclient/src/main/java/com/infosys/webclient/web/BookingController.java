package com.infosys.webclient.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.webclient.dto.ApiResponse;
import com.infosys.webclient.dto.BookingDTO;
import com.infosys.webclient.dto.payload.BookingRequest;
import com.infosys.webclient.dto.payload.PaymentInfoRequest;
import com.infosys.webclient.event.BookingEvent;
import com.infosys.webclient.event.BookingEventPublisher;
import com.infosys.webclient.security.CurrentUser;
import com.infosys.webclient.security.UserPrincipal;
import com.infosys.webclient.service.BookingService;
import com.infosys.webclient.service.PaymentService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private BookingEventPublisher bookingEventPublisher;

	@PostMapping
	@PreAuthorize("#principal.email == authentication.name")
	public ApiResponse bookTour(@CurrentUser UserPrincipal principal, @Valid @RequestBody BookingRequest bookingRequest) {
		BookingDTO book=bookingService.bookTour(bookingRequest);
		Map<String,Object> charged=paymentService.chargeBankTransfer(book);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> items = (List<Map<String, String>>) charged.get("va_numbers");
		Map<String,String> bankInfo=items.get(0);
		BookingEvent event=new BookingEvent(book.getName(),book.getEmail(), 
				book.getBillingAddress(), 
				book.getPackageName(), book.getPackageGroup(), book.getDestination(), bankInfo.get("bank")+" "+bankInfo.get("va_number"), (String) charged.get("gross_amount"), (String)charged.get("order_id"), (String)charged.get("transaction_status"));
		bookingEventPublisher.publishBookingEvent(event);
		return new ApiResponse(HttpStatus.OK, book);
	}
	
	@PutMapping("/payment")
	@PreAuthorize("#principal.email == authentication.name")
	public ApiResponse savePaymentInfo(@CurrentUser UserPrincipal principal, @Valid @RequestBody PaymentInfoRequest request) {
		return new ApiResponse(HttpStatus.OK, bookingService.savePaymentInfo(request));
	}
	
	@GetMapping("/customer/{customerId}")
	@PreAuthorize("#principal.email == authentication.name")
	public ApiResponse findByCustomer(@CurrentUser UserPrincipal principal, @PathVariable String customerId) {
		return new ApiResponse(HttpStatus.OK, bookingService.findByCustomer(customerId));
	}

}
