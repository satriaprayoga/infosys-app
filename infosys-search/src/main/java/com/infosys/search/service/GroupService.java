package com.infosys.search.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infosys.search.domain.Group;
import com.infosys.search.repository.GroupRepository;

@Service
public class GroupService extends AbstractBaseService<Group, String>{

	private final GroupRepository groupRepository;
	
	public GroupService(GroupRepository groupRepository) {
		setRepository(groupRepository);
		this.groupRepository=groupRepository;
	}
	
	public List<Group> findByDestination(String destination){
		return groupRepository.findByDestination(destination);
	}
}
