package com.infosys.data.web.pub;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.data.domain.Destination;
import com.infosys.data.dto.ApiResponse;
import com.infosys.data.dto.errors.ResourceNotFoundException;
import com.infosys.data.dto.payload.DestinationProfile;
import com.infosys.data.dto.payload.ObjectMapper;
import com.infosys.data.service.DestinationService;

@RestController
@RequestMapping("/api/pub/destinations")
public class PublicDestinationControler {

	private final DestinationService destinationService ;
	private final ObjectMapper objectMapper;
	
	public PublicDestinationControler(DestinationService destinationService,ObjectMapper objectMapper) {
		this.destinationService=destinationService;
		this.objectMapper=objectMapper;
	}
	
	
	
	@GetMapping
	public ApiResponse getAllDestinations() {
		List<Destination> destinations=destinationService.findAll();
		if(destinations.isEmpty()) {
			return new ApiResponse(HttpStatus.NOT_FOUND, "No Destination(s) Found");
		}
		return new ApiResponse(HttpStatus.OK, destinations);
	}
	
	@GetMapping("/paged")
	public ApiResponse getAllPaged(
			@RequestParam int page,
			@RequestParam int size, 
			@RequestParam String sort, 
			@RequestParam String direction) {
		Page<Destination> pageDestination=destinationService.findAll(size, page, sort, direction);
		if(page>pageDestination.getTotalPages()) {
			throw new ResourceNotFoundException(Destination.class, "no resource found");
		}
		return new ApiResponse(HttpStatus.OK, pageDestination);
	}
	
	
	@GetMapping("/search/{location}")
	public ApiResponse getLocationLike(@PathVariable String location){
		List<Destination> destinations=destinationService.findLocationLike(location);
		if(destinations.isEmpty()) {
			return new ApiResponse(HttpStatus.NOT_FOUND, "No Destination(s) Found");
		}
		return new ApiResponse(HttpStatus.OK, destinations);
	}
	
	@GetMapping("/{id}")
	public ApiResponse findById(@PathVariable Long id) throws ResourceNotFoundException{
		DestinationProfile destination=objectMapper.getDestinationProfile(id);
		return new ApiResponse(HttpStatus.OK, destination);
	}
	
	@GetMapping("/code/{code}")
	public ApiResponse findById(@PathVariable String code) throws ResourceNotFoundException{
		Destination destination=destinationService.findOneByCode(code);
		return new ApiResponse(HttpStatus.OK, destination);
	}
	
	@GetMapping("/accessabilities/{destId}")
	public ApiResponse getAccessFromDestinationId(@PathVariable Long destId) {
		return new ApiResponse(HttpStatus.OK, destinationService.getAccessabilities(destId));
	}
}
