package com.infosys.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.app.security.exception.ResourceNotFoundException;
import com.infosys.app.service.client.DestinationDataService;


@RestController
@RequestMapping("/data/destinations")
public class DestinationController {

	@Autowired
	private DestinationDataService destinationService;
	
	@GetMapping
	public ResponseEntity<?> getDestinations() {
		return destinationService.getAllDestinations();
	}
	
	@GetMapping("/search/{location}")
	public ResponseEntity<?> getLocationLike(@PathVariable String location){
		return destinationService.getLocationLike(location);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) throws ResourceNotFoundException{
		return destinationService.getById(id);
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<?> getByCode(@PathVariable String code) throws ResourceNotFoundException{
		return destinationService.getByCode(code);
	}
	
	@GetMapping("/accessabilities/{destId}")
	public ResponseEntity<?> getAccessFromDestinationId(@PathVariable Long destId) {
		return destinationService.getAccessFromDestinationId(destId);
	}
}
