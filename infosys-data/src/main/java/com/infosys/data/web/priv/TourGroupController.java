package com.infosys.data.web.priv;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.data.domain.TourGroup;
import com.infosys.data.dto.ApiResponse;
import com.infosys.data.dto.errors.ResourceConstraintException;
import com.infosys.data.dto.errors.ResourceNotFoundException;
import com.infosys.data.dto.payload.ObjectMapper;
import com.infosys.data.dto.payload.TourGroupRequest;
import com.infosys.data.service.TourGroupService;

@RestController
@RequestMapping("/api/priv/groups")
public class TourGroupController {

	private final TourGroupService tourGroupService;
	private final ObjectMapper objectMapper;
	
	public TourGroupController(TourGroupService tourGroupService,ObjectMapper objectMapper) {
		this.tourGroupService=tourGroupService;
		this.objectMapper=objectMapper;
	}
	
	@PostMapping
	public ApiResponse create(@Valid @RequestBody TourGroupRequest request) {
		TourGroup tg=objectMapper.getGroup(request);
		return new ApiResponse(HttpStatus.OK, tourGroupService.save(tg));
	}
	
	@DeleteMapping("/{id}")
	public ApiResponse delete(@PathVariable Long id)throws ResourceConstraintException,ResourceNotFoundException {
		tourGroupService.deleteById(id);
		return new ApiResponse(HttpStatus.OK, null);
	}
}
