package com.infosys.data.web.pub;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.data.domain.TourPackage;
import com.infosys.data.dto.ApiResponse;
import com.infosys.data.dto.payload.ObjectMapper;
import com.infosys.data.dto.payload.PackageProfile;
import com.infosys.data.service.PackageService;

@RestController
@RequestMapping("/api/pub/packages")
public class PublicTourPackageController {

	
	private final PackageService packageService;
	private final ObjectMapper objectMapper;
	
	public PublicTourPackageController(PackageService packageService,ObjectMapper objectMapper) {
		this.packageService=packageService;
		this.objectMapper=objectMapper;
	}
	
	@GetMapping
	public ApiResponse findAll(){
		List<TourPackage> packages=packageService.findAll();
		if(packages.isEmpty()) {
			return new ApiResponse(HttpStatus.OK, Collections.emptyList());
		}else {
			List<PackageProfile> profiles=packages.stream().map((p)->{
				return objectMapper.getPackageProfile(p.getId());
			}).collect(Collectors.toList());
			return new ApiResponse(HttpStatus.OK, profiles);
		}
	
	}
	
	@GetMapping("/{id}")
	public ApiResponse findById(@PathVariable Long id) {
		return new ApiResponse(HttpStatus.OK, objectMapper.getPackageProfile(id));
	}
}
