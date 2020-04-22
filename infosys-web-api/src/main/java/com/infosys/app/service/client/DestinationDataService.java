package com.infosys.app.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("backend-data")
public interface DestinationDataService {

	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/destinations")
	ResponseEntity<?> getAllDestinations();
	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/destinations/search/{location}")
	ResponseEntity<?> getLocationLike(@PathVariable String location);
	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/destinations/{id}")
	ResponseEntity<?> getById(@PathVariable Long id);
	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/destinations/code/{code}")
	ResponseEntity<?> getByCode(@PathVariable String code);
	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/destinations//accessabilities/{destId}")
	ResponseEntity<?> getAccessFromDestinationId(@PathVariable Long destId);
}
