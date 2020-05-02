package com.infosys.customer.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.customer.domain.BookingStatus;
import com.infosys.customer.domain.Customer;
import com.infosys.customer.dto.ApiResponse;
import com.infosys.customer.dto.payload.BookingInfoRequest;
import com.infosys.customer.dto.payload.BookingRequest;
import com.infosys.customer.dto.payload.PaymentInfoRequest;
import com.infosys.customer.exceptions.ResourceNotFoundException;
import com.infosys.customer.service.data.BookingService;
import com.infosys.customer.service.data.CustomerService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	private final BookingService bookingService;
	private final CustomerService customerService;
	
	public BookingController(BookingService bookingService,CustomerService customerService) {
		this.customerService = customerService;
		this.bookingService = bookingService;
	}
	
	@GetMapping("/{code}")
	public ApiResponse findByCode(@PathVariable String code) throws ResourceNotFoundException{
		return new ApiResponse(HttpStatus.OK,bookingService.findOneByCode(code));
	}
	
	@GetMapping("/{customerCode}/{code}")
	public ApiResponse findOneByCustomerAndCode(@PathVariable String customerCode,@PathVariable String code) throws ResourceNotFoundException{
		Customer c=customerService.findById(customerCode);
		return new ApiResponse(HttpStatus.OK, bookingService.findOneByCustomerAndCode(c, code));
	}
	
	@GetMapping("/{customerCode}/{code}/{status}")
	public ApiResponse findOneByCustomerAndCodeAndStatus
			(@PathVariable String customerCode,@PathVariable String code,@PathVariable String status) throws ResourceNotFoundException{
		Customer c=customerService.findById(customerCode);
		return new ApiResponse(HttpStatus.OK, bookingService.findOneByCustomerAndCodeAndStatus(c, code, BookingStatus.valueOf(status)));
	}
	
	@PostMapping
	public ApiResponse bookTour(@Valid @RequestBody BookingRequest request) {
		return new ApiResponse(HttpStatus.OK, "booking created", bookingService.bookTour(request));
	}
	
	@PutMapping("/info")
	public ApiResponse saveBookingInfo(@Valid @RequestBody BookingInfoRequest request) {
		return new ApiResponse(HttpStatus.OK, "booking updated",bookingService.saveBookingInfo(request));
	}
	
	@PutMapping("/payment")
	public ApiResponse savePaymentInfo(@Valid @RequestBody PaymentInfoRequest request) {
		return new ApiResponse(HttpStatus.OK, "booking updated",bookingService.savePaymentInfo(request));
	}
}
