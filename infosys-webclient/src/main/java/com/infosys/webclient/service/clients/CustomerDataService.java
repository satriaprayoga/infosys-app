package com.infosys.webclient.service.clients;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.infosys.webclient.config.CustomerClientConfig;
import com.infosys.webclient.dto.BookingDTO;
import com.infosys.webclient.dto.CustomerActivationDTO;
import com.infosys.webclient.dto.CustomerAuthDTO;
import com.infosys.webclient.dto.CustomerDTO;
import com.infosys.webclient.dto.payload.ActivationRequest;
import com.infosys.webclient.dto.payload.BookingInfoRequest;
import com.infosys.webclient.dto.payload.BookingRequest;
import com.infosys.webclient.dto.payload.ChangePasswordRequest;
import com.infosys.webclient.dto.payload.CustomerInfoRequest;
import com.infosys.webclient.dto.payload.CustomerRequest;
import com.infosys.webclient.dto.payload.PaymentInfoRequest;

@FeignClient(name="infosys-customer", configuration = CustomerClientConfig.class)
public interface CustomerDataService {

	@RequestMapping(method = RequestMethod.POST, path = "/api/customers/register")
	ResponseEntity<CustomerDTO> register(@Valid @RequestBody CustomerRequest request);
	
	@RequestMapping(method = RequestMethod.PUT,path="/api/customers/update/{id}")
	ResponseEntity<CustomerDTO> updateCustomerInfo(@PathVariable String id,@Valid @RequestBody CustomerInfoRequest request);
	
	@RequestMapping(method = RequestMethod.GET, path = "/api/customers/{id}")
	ResponseEntity<CustomerAuthDTO> findById(@PathVariable String id);
	
	@RequestMapping(method = RequestMethod.GET, path = "/api/customers/email")
	ResponseEntity<CustomerAuthDTO> findByEmail(@RequestParam String email);
	
	@RequestMapping(method = RequestMethod.GET, path = "/api/customers/phone")
	ResponseEntity<CustomerAuthDTO> findByPhone(@RequestParam String phone);
	
	
	@RequestMapping(method = RequestMethod.PUT, path = "/api/customers/activate")
	ResponseEntity<CustomerActivationDTO> activate(@Valid @RequestBody ActivationRequest request);
	
	@RequestMapping(method = RequestMethod.PUT, path = "/api/customers/changePassword")
	ResponseEntity<CustomerAuthDTO> changePassword(@Valid @RequestBody ChangePasswordRequest request);
	
	@RequestMapping(method = RequestMethod.GET, path = "/api/customers/email/exists")
	ResponseEntity<Boolean> existsByEmail(@RequestParam String email);
	
		
	@RequestMapping(method = RequestMethod.GET, path = "/api/customers/{id}/expired")
	ResponseEntity<Boolean> isActivationExpired(@PathVariable String id);
	@RequestMapping(method = RequestMethod.GET, path = "/api/customers/{id}/profileCompleted")
	ResponseEntity<Boolean> isProfileCompleted(@PathVariable String id);
	@RequestMapping(method = RequestMethod.GET, path = "/api/bookings/customer/{customerId}")
	ResponseEntity<List<BookingDTO>> findByCustomer(@PathVariable String customerId);
	@RequestMapping(method = RequestMethod.GET, path = "/api/bookings/{code}")
	ResponseEntity<BookingDTO> findByCode(@PathVariable String code);
	@RequestMapping(method = RequestMethod.GET, path = "/api/bookings/{customerCode}/{code}")
	ResponseEntity<BookingDTO> findOneByCustomerAndCode(@PathVariable String customerCode,@PathVariable String code); 
	@RequestMapping(method = RequestMethod.GET, path = "/api/bookings/{customerCode}/{code}/{status}")
	ResponseEntity<BookingDTO> findOneByCustomerAndCodeAndStatus(@PathVariable String customerCode,@PathVariable String code,@PathVariable String status);
	@RequestMapping(method = RequestMethod.POST, path="/api/bookings/")
	ResponseEntity<BookingDTO> bookTour(@Valid @RequestBody BookingRequest request);
	@RequestMapping(method = RequestMethod.PUT, path="/api/bookings/info")
	ResponseEntity<BookingDTO> saveBookingInfo(@Valid @RequestBody BookingInfoRequest request);
	@RequestMapping(method = RequestMethod.PUT, path="/api/bookings/payment")
	ResponseEntity<BookingDTO> savePaymentInfo(@Valid @RequestBody PaymentInfoRequest request);
}
