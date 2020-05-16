package com.infosys.search.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.search.domain.Landmark;
import com.infosys.search.service.LandmarkService;

@RestController
@RequestMapping("/api/landmarks")
public class LandmarkController {

	@Autowired
	private LandmarkService landmarkService;
	
	@GetMapping
	public List<Landmark> findByDestinationCode(@RequestParam String destCode){
		return landmarkService.findByDestinationCode(destCode);
	}
}
