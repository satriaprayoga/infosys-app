package com.infosys.customer.web;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.customer.domain.Booking;
import com.infosys.customer.domain.BookingStatus;
import com.infosys.customer.domain.Customer;
import com.infosys.customer.dto.BookingDTO;
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
	public ResponseEntity<BookingDTO> findByCode(@PathVariable String code) throws ResourceNotFoundException{
		return ResponseEntity.ok(BookingDTO.create(bookingService.findOneByCode(code)));
	}
	
	@GetMapping("/{customerCode}/{code}")
	public ResponseEntity<BookingDTO> findOneByCustomerAndCode(@PathVariable String customerCode,@PathVariable String code) throws ResourceNotFoundException{
		Customer c=customerService.findById(customerCode);
		return ResponseEntity.ok(BookingDTO.create(bookingService.findOneByCustomerAndCode(c, code)));
	}
	
	@GetMapping("/{customerCode}/{code}/{status}")
	public ResponseEntity<BookingDTO> findOneByCustomerAndCodeAndStatus
	(@PathVariable String customerCode,@PathVariable String code,@PathVariable String status) throws ResourceNotFoundException{
		Customer c=customerService.findById(customerCode);
		return ResponseEntity.ok(BookingDTO.create(bookingService.findOneByCustomerAndCodeAndStatus(c, code, BookingStatus.valueOf(status))));
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<BookingDTO>> findByCustomer(@PathVariable String customerId){
		Customer c=customerService.findById(customerId);
		List<Booking> bo=bookingService.findByCustomer(c);
		if(bo.size()>0) {
			List<BookingDTO> dtos=bo.stream().map((e)->BookingDTO.create(e)).collect(Collectors.toList());
			return ResponseEntity.ok(dtos);
		}else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}
	
	@PostMapping
	public ResponseEntity<BookingDTO> bookTour(@Valid @RequestBody BookingRequest request) {
		Booking book=bookingService.bookTour(request);
		BookingDTO dto=BookingDTO.create(book);
		return ResponseEntity.ok(dto);
	}
	
	
	@PutMapping("/info")
	public ResponseEntity<BookingDTO> saveBookingInfo(@Valid @RequestBody BookingInfoRequest request) {
		return ResponseEntity.ok(BookingDTO.create(bookingService.saveBookingInfo(request)));
	}
	
	@PutMapping("/payment")
	public ResponseEntity<BookingDTO> savePaymentInfo(@Valid @RequestBody PaymentInfoRequest request) {
		return ResponseEntity.ok(BookingDTO.create(bookingService.savePaymentInfo(request)));
	}
}
