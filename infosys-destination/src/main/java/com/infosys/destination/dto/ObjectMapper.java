package com.infosys.destination.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.infosys.destination.domain.Accessability;
import com.infosys.destination.domain.Destination;
import com.infosys.destination.dto.data.DestinationProfile;
import com.infosys.destination.dto.data.DestinationProfile.AccessabilityItem;
import com.infosys.destination.dto.data.DestinationRequest;
import com.infosys.destination.exception.ResourceNotFoundException;
import com.infosys.destination.repository.AccessabilityRepository;
import com.infosys.destination.repository.AdditionalRepository;
import com.infosys.destination.repository.DestinationRepository;
import com.infosys.destination.repository.LandmarkRepository;
import com.infosys.destination.repository.TourGroupRepository;
import com.infosys.destination.repository.TourPackageRepository;
import com.infosys.destination.utils.RandomUtils;

@Component
public class ObjectMapper {

	private final DestinationRepository destinationRepository;
	private final AccessabilityRepository accessabilityRepository;
	private final LandmarkRepository landmarkRepository;
	private final AdditionalRepository additionalRepository;
	private final TourGroupRepository tourGroupRepository;
	private final TourPackageRepository packageRepository;

	public ObjectMapper(DestinationRepository destinationRepository, AccessabilityRepository accessabilityRepository,
			LandmarkRepository landmarkRepository, AdditionalRepository additionalRepository,TourGroupRepository tourGroupRepository,
			TourPackageRepository packageRepository) {
		this.destinationRepository = destinationRepository;
		this.accessabilityRepository = accessabilityRepository;
		this.landmarkRepository = landmarkRepository;
		this.additionalRepository = additionalRepository;
		this.tourGroupRepository=tourGroupRepository;
		this.packageRepository = packageRepository;
	}
	
	public Destination getDestination(DestinationRequest request) {
		return destinationRepository.findOneById(request.getId()).map((destination)->{
			destination.setName(request.getName());
			destination.setCompany(request.getCompany());
			destination.setDescription(request.getDescription());
			destination.setEmail(request.getEmail());
			destination.setLocation(request.getLocation());
			destination.setPhone(request.getPhone());
			return destination;
		}).orElseGet(()->{
			Destination dest=new Destination();
			dest.setName(request.getName());
			dest.setCompany(request.getCompany());
			dest.setDescription(request.getDescription());
			dest.setEmail(request.getEmail());
			dest.setLocation(request.getLocation());
			dest.setPhone(request.getPhone());
			dest.setDestinationCode(RandomUtils.generateRandomAlphabet(5));
			return dest;
		});
	}
	
	public DestinationProfile getDestinationProfile(String id) {
		return destinationRepository.findById(id).map((destination) -> {
			List<Accessability> access = accessabilityRepository.findByDestination(destination);
			return DestinationProfile.fromDestination(destination, access);

		}).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, id.toString());
		});
	}
	
	
}
