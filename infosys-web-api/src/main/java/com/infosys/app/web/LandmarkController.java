package com.infosys.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.app.service.client.DataService;

@RestController
@RequestMapping("/data/landmarks")
public class LandmarkController {

	@Autowired
	private DataService dataService;
	
	public LandmarkController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping
	public ResponseEntity<?> getAllLandmarks(){
		return dataService.getAllLandmarks();
	}
	
	@GetMapping("/dest/{destId}")
	public ResponseEntity<?> findByDestination(@PathVariable Long destId){
		return dataService.findLandmarkByDestinationId(destId);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return dataService.findLandmarkById(id);
	}
}
