package com.infosys.app.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("backend-data")
public interface LandmarkDataService {

	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/landmarks")
	ResponseEntity<?> getAllLandmarks();
	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/landmarks/dest/{destId}")
	ResponseEntity<?> findByDestination(@PathVariable Long destId);
	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/landmarks/{id}")
	ResponseEntity<?> findById(@PathVariable Long id);
}
