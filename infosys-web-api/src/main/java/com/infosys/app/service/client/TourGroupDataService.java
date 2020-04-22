package com.infosys.app.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("backend-data")
public interface TourGroupDataService {

	@RequestMapping(method = RequestMethod.GET,path = "/api/pub/groups")
	ResponseEntity<?> findAll();
	@RequestMapping(method = RequestMethod.GET,path = "/api/pub/groups/{id}")
	ResponseEntity<?> findById(@PathVariable Long id);
	@RequestMapping(method = RequestMethod.GET,path = "/api/pub/groups/destination/{destId}")
	ResponseEntity<?> findByDestId(@PathVariable Long destId);
}
