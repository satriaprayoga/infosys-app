package com.infosys.data.web.pub;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.data.domain.Image;
import com.infosys.data.dto.ApiResponse;
import com.infosys.data.dto.errors.ResourceNotFoundException;
import com.infosys.data.service.ImageService;

@RestController
@RequestMapping("/api/pub/images")
public class PublicImageController {

	private final ImageService imageService;
	
	public PublicImageController(ImageService imageService) {
		this.imageService=imageService;
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> download(@PathVariable String id){
		Image image=imageService.loadImage(id);
		return ResponseEntity.ok()
							 .contentType(MediaType.parseMediaType(image.getType()))
							 .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + image.getFilename() + "\"")
							 .body(new ByteArrayResource(image.getData()));
	}
	@GetMapping("/download/{category}/{refId}")
	public ApiResponse getFileWithCategoryAndRefId(@PathVariable String category, @PathVariable Long refId) {
		return new ApiResponse(HttpStatus.OK, imageService.loadImage(category, refId));
	}
	@GetMapping("/cover/{category}/{refId}")
	public ResponseEntity<Resource> getImageCover(@PathVariable String category,@PathVariable Long refId) throws ResourceNotFoundException {
		Image image=imageService.getCoverFor(category, refId);
		return ResponseEntity.ok()
				 .contentType(MediaType.parseMediaType(image.getType()))
				 .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + image.getFilename() + "\"")
				 .body(new ByteArrayResource(image.getData()));
	}
	
	@GetMapping("/check/{category}/{refId}")
	public Boolean hasCover(@PathVariable String category,@PathVariable Long refId) {
		return imageService.hasCover(category, refId);
	}
}
