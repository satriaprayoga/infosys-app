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

import com.infosys.data.domain.Landmark;
import com.infosys.data.dto.ApiResponse;
import com.infosys.data.dto.errors.ResourceNotFoundException;
import com.infosys.data.dto.payload.LandmarkRequest;
import com.infosys.data.dto.payload.ObjectMapper;
import com.infosys.data.service.LandmarkService;

@RestController
@RequestMapping("/api/priv/landmarks")
public class LandmarkController {

	private final LandmarkService landmarkService;
	private final ObjectMapper objectMapper;
	
	public LandmarkController(LandmarkService landmarkService,ObjectMapper objectMapper) {
		this.landmarkService=landmarkService;
		this.objectMapper=objectMapper;
	}
	
	@PostMapping
	public ApiResponse createLandmark(@Valid @RequestBody LandmarkRequest landmark){
		Landmark obj=objectMapper.getLandmark(landmark);
		System.out.println("Landmarks:::::: "+obj);
		return new ApiResponse(HttpStatus.OK, "Landmark Created",landmarkService.save(obj));
	}
	
	@PutMapping	
	public ApiResponse updateLandmark(@Valid @RequestBody LandmarkRequest landmark) throws ResourceNotFoundException{
		Landmark obj=objectMapper.getLandmark(landmark);
		return new ApiResponse(HttpStatus.OK, "Landmark Created",landmarkService.save(obj));
	}
	
	@DeleteMapping("/{id}")
	public ApiResponse deleteLandmark(@PathVariable Long id) throws ResourceNotFoundException{
		landmarkService.deleteById(id);
		return new ApiResponse(HttpStatus.OK,"Landmark deleted");
	}
	
	
}
