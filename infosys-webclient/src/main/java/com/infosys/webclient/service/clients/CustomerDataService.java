package com.infosys.webclient.service.clients;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.infosys.webclient.config.CustomerClientConfig;
import com.infosys.webclient.dto.CustomerActivationDTO;
import com.infosys.webclient.dto.CustomerAuthDTO;
import com.infosys.webclient.dto.CustomerDTO;
import com.infosys.webclient.dto.payload.ActivationRequest;
import com.infosys.webclient.dto.payload.ChangePasswordRequest;
import com.infosys.webclient.dto.payload.CustomerInfoRequest;
import com.infosys.webclient.dto.payload.CustomerRequest;

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
	
}
