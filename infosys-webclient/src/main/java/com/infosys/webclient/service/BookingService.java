package com.infosys.webclient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.webclient.dto.BookingDTO;
import com.infosys.webclient.dto.payload.BookingInfoRequest;
import com.infosys.webclient.dto.payload.BookingRequest;
import com.infosys.webclient.dto.payload.PaymentInfoRequest;
import com.infosys.webclient.service.clients.CustomerDataService;

@Service
public class BookingService {

	@Autowired
	private CustomerDataService customerDataService;
	
	public List<BookingDTO> findByCustomer(String customerId){
		return customerDataService.findByCustomer(customerId).getBody();
	}
	
	public BookingDTO findByCode(String code) {
		return customerDataService.findByCode(code).getBody();
	}
	
	public BookingDTO findByCustomerAndCode(String customerCode, String code) {
		return customerDataService.findOneByCustomerAndCode(customerCode, code).getBody();
	}
	
	public BookingDTO findByCustomerAndCodeAndStatus(String customerCode,String code,String status) {
		return customerDataService.findOneByCustomerAndCodeAndStatus(customerCode, code, status).getBody();
	}
	
	public BookingDTO bookTour(BookingRequest request) {
		return customerDataService.bookTour(request).getBody();
	}
	
	public BookingDTO saveBookingInfo(BookingInfoRequest request) {
		return customerDataService.saveBookingInfo(request).getBody();
	}
	
	public BookingDTO savePaymentInfo(PaymentInfoRequest request) {
		return customerDataService.savePaymentInfo(request).getBody();
	}
}
