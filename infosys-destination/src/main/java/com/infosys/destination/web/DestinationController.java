package com.infosys.destination.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.destination.domain.Destination;
import com.infosys.destination.dto.ObjectMapper;
import com.infosys.destination.dto.data.DestinationProfile;
import com.infosys.destination.dto.data.DestinationRequest;
import com.infosys.destination.event.DestinationEventPublisher;
import com.infosys.destination.event.EventType;
import com.infosys.destination.service.data.DestinationService;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

	private final DestinationService destinationService;
	private final ObjectMapper objectMapper;
	private final DestinationEventPublisher eventPublisher;
		
	public DestinationController(DestinationService destinationService,
			ObjectMapper objectMapper,DestinationEventPublisher eventPublisher) {
		this.destinationService=destinationService;
		this.objectMapper=objectMapper;
		this.eventPublisher=eventPublisher;
	}
	
	@PostMapping
	public ResponseEntity<DestinationProfile> create(@RequestBody @Valid DestinationRequest destinationRequest){
		Destination destination=destinationService.create(objectMapper.getDestination(destinationRequest));
		eventPublisher.publishEvent(EventType.CREATE, destination);
		return ResponseEntity.ok(objectMapper.getDestinationProfile(destination.getId()));
	}
	@PutMapping
	public ResponseEntity<DestinationProfile> update(@RequestBody @Valid DestinationRequest destinationRequest){
		Destination destination=destinationService.save(objectMapper.getDestination(destinationRequest));
		eventPublisher.publishEvent(EventType.UPDATE, destination);
		return ResponseEntity.ok(objectMapper.getDestinationProfile(destination.getId()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		Destination destination=destinationService.findOneById(id);
		eventPublisher.publishEvent(EventType.DELETE, destination);
		destinationService.deleteById(id);
		return ResponseEntity.ok("Deleted");
	}
}
