package com.infosys.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.data.domain.Image;
import com.infosys.data.domain.ImageCategory;

@Repository
public interface ImageRepository extends JpaRepository<Image, String>{

	List<Image> findByFilename(String filename);
	
	List<Image> findByImageCategoryAndRefId(ImageCategory cat, Long refId);
	
	Boolean existsByFilename(String filename);
	
	Optional<Image> findOneByImageCategoryAndRefIdAndCover(ImageCategory category,Long refId,boolean cover);
}
