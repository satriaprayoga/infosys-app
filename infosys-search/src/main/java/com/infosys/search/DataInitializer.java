package com.infosys.search;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.infosys.search.domain.Destination;
import com.infosys.search.domain.Landmark;
import com.infosys.search.domain.Tour;
import com.infosys.search.service.DestinationService;
import com.infosys.search.service.LandmarkService;
import com.infosys.search.service.TourService;


@Component
public class DataInitializer implements CommandLineRunner{

	@Value("classpath:data/tour.json")
	private Resource tourJsonFile;

	@Value("classpath:data/destination.json")
	private Resource destinationJsonFile;
	
	@Value("classpath:data/landmark.json")
	private Resource landmarkJsonFile;

	@Autowired
	private TourService tourService;

	@Autowired
	private DestinationService destinationService;
	
	@Autowired
	private LandmarkService landmarkService;

	public void run(String... args) throws Exception {
//		if (this.isInitialized()) {
//			return;
//		}
//
		List<Tour> tours = this.loadTourFromFile();
		tours.forEach(tourService::create);
		
		List<Landmark> land=this.loadLandmarkfromfile();
		land.forEach(landmarkService::create);
		List<Destination> dest=this.loadDestinationfromfile();
		dest.forEach(destinationService::create);
		
	


	}

	private List<Tour> loadTourFromFile() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class,
				TourJson.class);
		List<TourJson> allFakeUsers = objectMapper.readValue(this.tourJsonFile.getFile(), collectionType);
		return allFakeUsers.stream().map(this::from).map(this::generateTourId).collect(Collectors.toList());
	}

	private List<Destination> loadDestinationfromfile() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class,
				DestinationJson.class);
		List<DestinationJson> allFakeUsers = objectMapper.readValue(this.destinationJsonFile.getFile(), collectionType);
		return allFakeUsers.stream().map(this::from).map(this::generateDestId).collect(Collectors.toList());

	}
	
	private List<Landmark> loadLandmarkfromfile() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class,
				LandmarkJson.class);
		List<LandmarkJson> allFakeUsers = objectMapper.readValue(this.landmarkJsonFile.getFile(), collectionType);
		return allFakeUsers.stream().map(this::from).map(this::generateLandId).collect(Collectors.toList());

	}

	private Tour generateTourId(Tour tour) {
		tour.setId(UUID.randomUUID().toString());
		return tour;
	}

	private Destination generateDestId(Destination dest) {
		dest.setId(UUID.randomUUID().toString());
		return dest;
	}
	
	private Landmark generateLandId(Landmark land) {
		land.setId(UUID.randomUUID().toString());
		return land;
	}

	private Destination from(DestinationJson destinationJson) {
		Destination destination = new Destination();
		destination.setAccessabilities(destinationJson.getAccessabilities());
		destination.setCode(destinationJson.getCode());
		destination.setCompany(destinationJson.getCompany());
		destination.setDescription(destinationJson.getDescription());
		destination.setDestination(destinationJson.getDestination());
		destination.setEmail(destinationJson.getEmail());
		destination.setLocation(destinationJson.getLocation());
		destination.setPhone(destinationJson.getPhone());
		return destination;
	}
	
	private Landmark from(LandmarkJson landmarkJson) {
		Landmark landmark=new Landmark();
		landmark.setDestination(landmarkJson.getDestination());
		landmark.setDescription(landmarkJson.getDescription());
		landmark.setDestinationCode(landmarkJson.getDestinationCode());
		landmark.setHour(landmarkJson.getHour());
		landmark.setMinute(landmarkJson.getMinute());
		landmark.setLandmark(landmarkJson.getLandmark());
		return landmark;
	}

	private Tour from(TourJson tourJson) {
		Tour tour = new Tour();
		tour.setAccomodationIncluded(tourJson.isAccomodationIncluded());
		tour.setCapacity(tourJson.getCapacity());
		tour.setDay(tourJson.getDay());
		tour.setDestination(tourJson.getDestination());
		tour.setDestinationId(tourJson.getDestinationId());
		tour.setLandmarks(tourJson.getLandmarks());
		tour.setLocation(tourJson.getLocation());
		tour.setMinOrder(tourJson.getMinOrder());
		tour.setNight(tourJson.getNight());
		tour.setPrice(tourJson.getPrice());
		tour.setPublished(tourJson.isPublished());
		tour.setGroup(tourJson.getGroup());
		tour.setName(tourJson.getName());
		tour.setUnit(tourJson.getUnit());
		return tour;
	}

	

}
