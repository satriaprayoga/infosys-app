package com.infosys.data.web.priv;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.infosys.data.domain.Image;
import com.infosys.data.dto.ApiResponse;
import com.infosys.data.dto.payload.UploadImageResponse;
import com.infosys.data.service.ImageService;

@RestController
@RequestMapping("/api/priv/images")
public class ImageController {
	
	private final ImageService imageService;
	
	public ImageController(ImageService imageService) {
		this.imageService=imageService;
	}
	
	@PostMapping("/upload/{category}/{refId}")
	public ApiResponse uploadImage(@RequestParam("file") MultipartFile file, @PathVariable String category,@PathVariable Long refId) {
		Image image=imageService.storeImage(category, refId, file);
		String downloadUri=ServletUriComponentsBuilder.fromCurrentContextPath()
						   .path("/api/pub/download/")
						   .path(image.getId())
						   .toUriString();
		return new ApiResponse(HttpStatus.OK, 
				new UploadImageResponse(image.getId(),image.getFilename(), downloadUri, file.getContentType(), file.getSize()));
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public ApiResponse deleteImage(@PathVariable String id) {
		imageService.deleteImage(id);
		return new ApiResponse(HttpStatus.OK, null);
	}
	
	@PutMapping("/cover/{category}/{refId}/{id}")
	public ApiResponse changeCover(@PathVariable String category,@PathVariable Long refId, @Valid @RequestBody String id) {
		return new ApiResponse(HttpStatus.OK, imageService.setAsCover(category,refId, id));
	}

}
