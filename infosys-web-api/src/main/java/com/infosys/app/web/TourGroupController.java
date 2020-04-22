package com.infosys.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.app.service.client.TourGroupDataService;

@RestController
@RequestMapping("/data/groups")
public class TourGroupController {

	@Autowired
	private TourGroupDataService tourGroupService;
	
	@GetMapping
	public ResponseEntity<?>findAll() {
		return tourGroupService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?>findById(@PathVariable Long id){
		return tourGroupService.findById(id);
	}
	
	@GetMapping("/destination/{destId}")
	public ResponseEntity<?>findByDestination(@PathVariable(required = true) Long destId) {
		return tourGroupService.findByDestId(destId);
	}
}
