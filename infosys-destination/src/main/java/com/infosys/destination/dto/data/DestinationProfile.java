package com.infosys.destination.dto.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.infosys.destination.domain.Accessability;
import com.infosys.destination.domain.Destination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DestinationProfile {

	private String id;
	private String name;
	
	private String description;
	private String location;
	private String company;
	private String email;
	private String phone;
	
	private List<AccessabilityItem> accessabilities=new ArrayList<>();
	
	public static DestinationProfile fromDestination(Destination destination) {
		return new DestinationProfile(destination.getId(), 
				destination.getName(), 
				destination.getDescription(), destination.getLocation(), destination.getCompany(), destination.getEmail(), destination.getPhone(), null);
	}
	
	public static DestinationProfile fromDestination(Destination destination, List<Accessability> accesses) {
		if(accesses.isEmpty()) {
			return fromDestination(destination);
		}
		List<AccessabilityItem> accessabilities=accesses.stream()
				.map(access->new AccessabilityItem(access.getId(), access.getKm(), access.getMinute(), access.getAccess()))
				.collect(Collectors.toList());
		return new DestinationProfile(destination.getId(), 
				destination.getName(), 
				destination.getDescription(), destination.getLocation(), destination.getCompany(), destination.getEmail(), destination.getPhone(), accessabilities);
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class AccessabilityItem{
		String id;
		int km;
		int minute;
		String access;
	}
}
