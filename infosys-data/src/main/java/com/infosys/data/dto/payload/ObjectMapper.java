package com.infosys.data.dto.payload;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.infosys.data.domain.Accessability;
import com.infosys.data.domain.Additional;
import com.infosys.data.domain.Destination;
import com.infosys.data.domain.Landmark;
import com.infosys.data.domain.TourGroup;
import com.infosys.data.domain.TourPackage;
import com.infosys.data.dto.errors.ResourceNotFoundException;
import com.infosys.data.dto.payload.DestinationProfile.AccessabilityItem;
import com.infosys.data.dto.payload.PackageProfile.AdditionalItem;
import com.infosys.data.dto.payload.PackageProfile.LandmarkItem;
import com.infosys.data.dto.payload.PackageRequest.EditAdditionalRequest;
import com.infosys.data.repository.AccessabilityRepository;
import com.infosys.data.repository.AdditionalRepository;
import com.infosys.data.repository.DestinationRepository;
import com.infosys.data.repository.LandmarkRepository;
import com.infosys.data.repository.TourGroupRepository;
import com.infosys.data.repository.TourPackageRepository;
import com.infosys.data.util.RandomUtils;

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

	public DestinationProfile getDestinationProfile(Long id) {
		return destinationRepository.findById(id).map((destination) -> {
			List<Accessability> access = accessabilityRepository.findByDestination(destination);
			if (!access.isEmpty()) {
				List<AccessabilityItem> items=access.stream()
						.map(acc->new AccessabilityItem(acc.getId(),acc.getKm(),acc.getMinute(),acc.getAccess())).collect(Collectors.toList());
				return new DestinationProfile(destination.getId(), destination.getName(), destination.getDescription(),
						destination.getLocation(), destination.getCompany(), destination.getEmail(),
						destination.getPhone(),items);
			} else {
				return new DestinationProfile(destination.getId(), destination.getName(), destination.getDescription(),
						destination.getLocation(), destination.getCompany(), destination.getEmail(),
						destination.getPhone(), null);
			}

		}).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id", id.toString());
		});
	}

	public LandmarkProfile getLandmarkProfile(Long id) {
		return landmarkRepository.findById(id).map((landmark) -> {
			return new LandmarkProfile(landmark.getId(), landmark.getName(), landmark.getDescription(),
					landmark.getHour(), landmark.getMinute(), landmark.getDestination().getName(),
					landmark.getDestination().getLocation(), landmark.getDestination().getId());
		}).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(Landmark.class, "id", id.toString());
		});
	}

	public PackageProfile getPackageProfile(Long id) {
		return packageRepository.findOneById(id).map((pack) -> {
			PackageProfile profile = PackageProfile.fromPackage(pack);
			if(!pack.getLandmarks().isEmpty()) {
				List<LandmarkItem> landmarks=pack.getLandmarks().stream().map((l)->{
					return new LandmarkItem(l.getId(),l.getName());
				}).collect(Collectors.toList());
				profile.setLandmarks(landmarks);
			}
			if(!pack.getAdditionals().isEmpty()) {
				List<AdditionalItem> additionals=pack.getAdditionals().stream().map((l)->{
					return new AdditionalItem(l.getId(),l.getName());
				}).collect(Collectors.toList());
				profile.setAdditionals(additionals);
			}
			return profile;
		}).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(TourPackage.class, "id", id.toString());
		});
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
	
	public Landmark getLandmark(LandmarkRequest request) {
		return landmarkRepository.findOneById(request.getId()).map((landmark)->{
			landmark.setDescription(request.getDescription());
			landmark.setHour(request.getHour());
			landmark.setMinute(request.getMinute());
			landmark.setName(request.getName());
			return landmark;
		}).orElseGet(()->{
			Landmark ld=new Landmark();
			ld.setDestination(destinationRepository.findById(request.getDestId()).get());
			ld.setDescription(request.getDescription());
			ld.setHour(request.getHour());
			ld.setMinute(request.getMinute());
			ld.setName(request.getName());
			return ld;
		});
	}
	
	public TourGroup getGroup(TourGroupRequest request) {
		return tourGroupRepository.findOneById(request.getId()).map((tg)->{
			tg.setName(request.getName());
			return tg;
		}).orElseGet(()->{
			TourGroup grup=new TourGroup();
			Destination d=destinationRepository.findOneById(request.getDestId()).get();
			grup.setDestination(d);
			grup.setName(request.getName());
			return grup;
		});
	}
	
	public TourPackage getPackage(PackageRequest request) {
		return packageRepository.findOneById(request.getId()).map(p->{
			p.setTourName(request.getTourName());
			p.setUnit(request.getUnit());
			p.setPublished(request.isPublished());
			p.setPrice(request.getPrice());
			p.setNight(request.getNight());
			p.setMinOrder(request.getMinOrder());
			p.setDay(request.getDay());
			p.setCapacity(request.getCapacity());
			return p;
		}).orElseGet(()->{
			TourPackage pkg=new TourPackage();
			TourGroup tg=tourGroupRepository.findOneById(request.getTourGroupId()).get();
			pkg.setTourGroup(tg);
			pkg.setTourName(request.getTourName());
			pkg.setUnit(request.getUnit());
			pkg.setPublished(request.isPublished());
			pkg.setPrice(request.getPrice());
			pkg.setNight(request.getNight());
			pkg.setMinOrder(request.getMinOrder());
			pkg.setDay(request.getDay());
			pkg.setCapacity(request.getCapacity());
			
			return pkg;
			
		});
	}

	
	
	public TourPackage addAdditional(EditAdditionalRequest additionalRequest) {
		return packageRepository.findOneById(additionalRequest.getPackageId()).map(p->{
			Additional additional=additionalRepository.findById(additionalRequest.getAdditionalId()).get();
			p.addAdditional(additional);
			return p;
		}).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(TourPackage.class, "id", additionalRequest.getPackageId().toString());
		});
	}
	
	public TourPackage removeAdditional(EditAdditionalRequest additionalRequest) {
		return packageRepository.findOneById(additionalRequest.getPackageId()).map(p->{
			Additional additional=additionalRepository.findById(additionalRequest.getAdditionalId()).get();
			p.removeAdditional(additional);
			return p;
		}).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(TourPackage.class, "id", additionalRequest.getPackageId().toString());
		});
	}
}
