package com.infosys.data.web.pub;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.data.dto.ApiResponse;
import com.infosys.data.service.TourGroupService;

@RestController
@RequestMapping("/api/pub/groups")
public class PublicTourGroupController {

	private final TourGroupService tourGroupService;
	
	public PublicTourGroupController(TourGroupService tourGroupService) {
		this.tourGroupService=tourGroupService;
	}
	@GetMapping
	public ApiResponse findAll() {
		return new ApiResponse(HttpStatus.OK, tourGroupService.findAll());
	}
	
	@GetMapping("/{id}")
	public ApiResponse findById(@PathVariable Long id){
		return new ApiResponse(HttpStatus.OK, tourGroupService.findById(id));
	}
	
	@GetMapping("/destination/{destId}")
	public ApiResponse findByDestination(@PathVariable(required = true) Long destId) {
		return new ApiResponse(HttpStatus.OK, tourGroupService.findByDestination(destId));
	}
	
	
}
