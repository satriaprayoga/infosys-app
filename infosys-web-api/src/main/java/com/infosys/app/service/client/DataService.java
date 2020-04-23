package com.infosys.app.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("infosys-data" )
public interface DataService {

	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/destinations")
	ResponseEntity<?> getAllDestinations();
	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/destinations/search/{location}")
	ResponseEntity<?> getDestinationLike(@PathVariable String location);
	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/destinations/{id}")
	ResponseEntity<?> getDestinationById(@PathVariable Long id);
	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/destinations/code/{code}")
	ResponseEntity<?> getDestinationByCode(@PathVariable String code);
	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/destinations//accessabilities/{destId}")
	ResponseEntity<?> getAccessFromDestinationId(@PathVariable Long destId);
	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/landmarks")
	ResponseEntity<?> getAllLandmarks();
	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/landmarks/dest/{destId}")
	ResponseEntity<?> findLandmarkByDestinationId(@PathVariable Long destId);
	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/landmarks/{id}")
	ResponseEntity<?> findLandmarkById(@PathVariable Long id);
	@RequestMapping(method = RequestMethod.GET,path = "/api/pub/groups")
	ResponseEntity<?> findAllTourGroups();
	@RequestMapping(method = RequestMethod.GET,path = "/api/pub/groups/{id}")
	ResponseEntity<?> findTourGroupById(@PathVariable Long id);
	@RequestMapping(method = RequestMethod.GET,path = "/api/pub/groups/destination/{destId}")
	ResponseEntity<?> findTourGroupByDestinationId(@PathVariable Long destId);
	@RequestMapping(method = RequestMethod.GET, path = "/api/pub/packages")
    ResponseEntity<?> findAllTourPackages();
	
}
