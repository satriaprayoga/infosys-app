package com.infosys.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.app.service.client.LandmarkDataService;

@RestController
@RequestMapping("/data/landmarks")
public class LandmarkController {

	@Autowired
	private LandmarkDataService landmarkService;
	
	public LandmarkController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping
	public ResponseEntity<?> getAllLandmarks(){
		return landmarkService.getAllLandmarks();
	}
	
	@GetMapping("/dest/{destId}")
	public ResponseEntity<?> findByDestination(@PathVariable Long destId){
		return landmarkService.findByDestination(destId);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return landmarkService.findById(id);
	}
}
