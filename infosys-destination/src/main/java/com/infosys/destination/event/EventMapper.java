package com.infosys.destination.event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.infosys.destination.domain.Accessability;
import com.infosys.destination.domain.Destination;

@Component
public class EventMapper {

	public DestinationEvent mapEvent(String eventType,Destination destination) {
		DestinationEvent event=new DestinationEvent();
		event.setId(destination.getId());
		event.setCode(destination.getDestinationCode());
		event.setCompany(destination.getCompany());
		event.setDescription(destination.getDescription());
		event.setDestination(destination.getName());
		event.setEmail(destination.getEmail());
		event.setLocation(destination.getLocation());
		event.setPhone(destination.getPhone());
		event.setEventType(eventType);
		
		if(!destination.getAccessabilities().isEmpty()) {
			List<Accessability> acc=destination.getAccessabilities();
			ArrayList<String> accs=acc.stream().map((a)->{
				return new String(a.getKm()+" km and "+a.getMinute()+" minute from "+a.getAccess());
			}).collect(Collectors.toCollection(ArrayList::new));
			event.setAccessabilities(accs.toArray(new String[accs.size()]));
		}
		return event;
	}
}
