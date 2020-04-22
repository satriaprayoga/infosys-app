package com.infosys.data.web.pub;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.data.dto.ApiResponse;
import com.infosys.data.dto.errors.ResourceNotFoundException;
import com.infosys.data.service.LandmarkService;

@RestController
@RequestMapping("/api/pub/landmarks")
public class PublicLandmarkController {

	private final LandmarkService landmarkService;
	
	public PublicLandmarkController(LandmarkService landmarkService) {
		this.landmarkService=landmarkService;
	}
	
	@GetMapping
	public ApiResponse findAll() {
		return new ApiResponse(HttpStatus.OK, landmarkService.findAll());
	}
	
	@GetMapping("/dest/{destId}")
	public ApiResponse findByDestination(@PathVariable Long destId)throws ResourceNotFoundException{
		return new ApiResponse(HttpStatus.OK, landmarkService.findByDestination(destId));
	}
	
	@GetMapping("/{id}")
	public ApiResponse findById(@PathVariable Long id) throws ResourceNotFoundException{
		return new ApiResponse(HttpStatus.OK,landmarkService.findOne(id).get());
	}
}
