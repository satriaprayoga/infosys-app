package com.infosys.data.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.infosys.data.domain.Image;
import com.infosys.data.domain.ImageCategory;
import com.infosys.data.dto.errors.ImageStorageException;
import com.infosys.data.dto.errors.ResourceNotFoundException;
import com.infosys.data.repository.ImageRepository;


@Service
public class ImageService {

	private final ImageRepository imageRepository;
	
	public ImageService(ImageRepository imageRepository) {
		this.imageRepository=imageRepository;
	}
	
	public Image storeImage(String category,Long refId,MultipartFile file) {
		String filename=StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(filename.contains("..")) {
				throw new ImageStorageException("Sorry!the filename : "+filename+" contains invalid path");
			}
			Image image= new Image(filename, file.getContentType(), file.getBytes());
			image.setImageCategory(ImageCategory.valueOf(category));
			image.setRefId(refId);
			if(this.loadImage(category, refId).isEmpty()) {
				image.setCover(true);
			}
			return imageRepository.save(image);
		} catch (IOException e) {
			throw new ImageStorageException("Could not store file " + filename + ". Please try again!", e);
		}
		
	}
	
	public Image loadImage(String id) {
		return imageRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(Image.class,"id",id));
	}
	
	public void deleteImage(String id) {
		if(imageRepository.findById(id).isPresent()) {
			imageRepository.deleteById(id);
		}else {
			throw new ResourceNotFoundException(Image.class,"id", id);
		}
	}
	
	public List<Image> loadImage(String category,Long refId) {
		return imageRepository.findByImageCategoryAndRefId(ImageCategory.valueOf(category), refId);
	}
	
	public Image getCoverFor(String category,Long refId) {
		return imageRepository.findOneByImageCategoryAndRefIdAndCover(ImageCategory.valueOf(category),refId, true).orElseThrow(()->new ResourceNotFoundException(Image.class,"id",refId.toString()));
	}
	
	public Image setAsCover(String category,Long refId, String id) {
		Image cover=loadImage(id);
		if(cover.isCover()==false) {
			Optional<Image> currentCover=imageRepository.findOneByImageCategoryAndRefIdAndCover(ImageCategory.valueOf(category), refId, true);
			Image currentCoverImg=currentCover.get();
			currentCoverImg.setCover(false);
			imageRepository.save(currentCoverImg);
			cover.setCover(true);
			return imageRepository.save(cover);
		}
		return cover;
	}
	
	public Boolean hasCover(String category, Long refId) {
		return imageRepository.findOneByImageCategoryAndRefIdAndCover(ImageCategory.valueOf(category),refId, true).isPresent();
	}
}
