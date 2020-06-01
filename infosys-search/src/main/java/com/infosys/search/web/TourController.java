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

import com.infosys.search.domain.Tour;
import com.infosys.search.service.BookedService;
import com.infosys.search.service.TourService;

@RestController
@RequestMapping("/api/tours")
public class TourController {

	@Autowired
	private TourService tourService;
	@Autowired
	private BookedService bookedService;
	
	public TourController() {
	}
	
	@GetMapping
	public List<Tour> search(@RequestParam(required=false) String destination,
			@RequestParam(required = false) String landmark,
			@RequestParam(required = false) String group){
		if(!StringUtils.hasText(destination)) {
			return tourService.findAll();
		}else if(!StringUtils.hasText(landmark) || !StringUtils.hasText(group)) {
			return tourService.findByDestination(destination);
		}else {
			return tourService.findByDestinationIdLandmarksGroup(destination, landmark, group);
		}
	}
	
	@GetMapping("/{id}")
	public Tour findById(@PathVariable String id) {
		return tourService.findById(id);
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
