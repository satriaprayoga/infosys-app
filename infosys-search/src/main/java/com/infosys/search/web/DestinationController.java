package com.infosys.search.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.search.domain.Destination;
import com.infosys.search.service.DestinationService;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

	@Autowired
	private DestinationService destinationService;
	
	public DestinationController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping
	public ResponseEntity<?> findAllDestinations(){
		List<Destination> destinations=destinationService.findAll();
		if(destinations.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(destinations);
		}
	}
	
	@GetMapping("/id")
	public ResponseEntity<?> findByQuery(@RequestParam String q){
		List<Destination> destinations=destinationService.findByDestinationOrLocation(q);
		if(destinations.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(destinations);
		}
	}
	
	@GetMapping("/code")
	public ResponseEntity<?> findByCode(@RequestParam String c){
		Destination dest=destinationService.findOneByCode(c);
		return ResponseEntity.ok(dest);
	}
}
