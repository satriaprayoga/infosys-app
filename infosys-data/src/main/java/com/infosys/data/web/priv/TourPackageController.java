package com.infosys.data.web.priv;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.data.domain.TourPackage;
import com.infosys.data.dto.ApiResponse;
import com.infosys.data.dto.payload.ObjectMapper;
import com.infosys.data.dto.payload.PackageRequest;
import com.infosys.data.service.PackageService;

@RestController
@RequestMapping("/api/priv/packages")
public class TourPackageController {

	private final PackageService packageService;
	private final ObjectMapper objectMapper;
	
	public TourPackageController(PackageService packageService,ObjectMapper objectMapper) {
		this.packageService=packageService;
		this.objectMapper=objectMapper;
	}
	
	@PostMapping
	public ApiResponse createTourPackage(@Valid @RequestBody PackageRequest request) {
		TourPackage pkg=objectMapper.getPackage(request);
		return new ApiResponse(HttpStatus.OK, packageService.save(pkg));
	}
	
	@PutMapping
	public ApiResponse editTourPackage(@Valid @RequestBody PackageRequest request) {
		TourPackage pkg=objectMapper.getPackage(request);
		return new ApiResponse(HttpStatus.OK, packageService.save(pkg));
	}
	
}
