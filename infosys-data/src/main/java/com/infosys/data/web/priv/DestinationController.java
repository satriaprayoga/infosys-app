package com.infosys.data.web.priv;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.data.domain.Accessability;
import com.infosys.data.domain.Destination;
import com.infosys.data.dto.ApiResponse;
import com.infosys.data.dto.errors.ResourceConstraintException;
import com.infosys.data.dto.errors.ResourceNotFoundException;
import com.infosys.data.dto.payload.DestinationRequest;
import com.infosys.data.dto.payload.ObjectMapper;
import com.infosys.data.service.DestinationService;

@RestController
@RequestMapping("/api/priv/destinations")
public class DestinationController {

	private final DestinationService destinationService;
	private final ObjectMapper objectMapper;
	
	public DestinationController(DestinationService destinationService,ObjectMapper objectMapper) {
		this.destinationService=destinationService;
		this.objectMapper=objectMapper;
	}
	
	@PostMapping
	public ApiResponse createDestination(@Valid @RequestBody DestinationRequest request) {
		Destination destination=objectMapper.getDestination(request);
		return new ApiResponse(HttpStatus.OK, "Destination created",destinationService.create(destination));
	}
	
	@PutMapping
	public ApiResponse updateDestination(
			@Valid @RequestBody DestinationRequest request) throws ResourceNotFoundException{
		Destination destination=objectMapper.getDestination(request);
		return new ApiResponse(HttpStatus.OK, "Destination updated",destinationService.save(destination));
	}
	
	@DeleteMapping("/{id}")
	public ApiResponse deleteDestination(@PathVariable Long id) throws ResourceNotFoundException, ResourceConstraintException{
		destinationService.deleteById(id);
		return new ApiResponse(HttpStatus.OK,"Destination deleted", null);
	}
	
	@PostMapping("/accessabilities/{destId}")
	public ApiResponse addAccessForDestinationId(@PathVariable Long destId, @Valid @RequestBody Accessability accessability) {
		return new ApiResponse(HttpStatus.OK, destinationService.addAccessability(destId, accessability));
	}
	
	@DeleteMapping("/{id}/accessabilities/{accessId}")
	public ApiResponse deleteAccessability(@PathVariable Long id,@PathVariable Long accessId)throws ResourceNotFoundException{
		return new ApiResponse(HttpStatus.OK, destinationService.removeAccessability(id, accessId));
	}
	
}
