package com.infosys.search.web;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.search.domain.Hotel;
import com.infosys.search.service.BookedService;
import com.infosys.search.service.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	@Autowired
	private BookedService bookedService;
	
	public HotelController() {
	}
	
	@GetMapping
	public List<Hotel> search(@RequestParam(required=false) String destination,
			@RequestParam(required = false) String group){
		if(!StringUtils.hasText(destination)) {
			return hotelService.findAll();
		}else if(!StringUtils.hasText(group)) {
			return hotelService.findByDestinationOrLocation(destination);
		}else {
			return hotelService.findByDestinationGroup(destination, group);
		}
	}
	
	@GetMapping("/{id}")
	public Hotel findById(@PathVariable String id) {
		return hotelService.findById(id);
	}
	
	@GetMapping("/aggregate")
	public ResponseEntity<?> aggregate(@RequestParam String destinationId){
		return ResponseEntity.ok(hotelService.aggregateFilter(destinationId));
	}
	
	
	@GetMapping("/available")
	public ResponseEntity<?> searchByBookDate(@RequestParam String packageId, @RequestParam String bookDate) throws ParseException{
		
		return ResponseEntity.ok(bookedService.searchByBookDate(packageId,bookDate));
	}
	
	@GetMapping("/check")
	public ResponseEntity<Boolean> checkAvailable(@RequestParam String packageId, 
				@RequestParam String bookDate,
				@RequestParam int max, 
				@RequestParam int order) throws ParseException{
		
		return ResponseEntity.ok(bookedService.isAvailable(packageId, bookDate, max, order));
	}
}
