package com.vtw.pulsar.team.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vtw.pulsar.jpa.PageInfo;
import com.vtw.pulsar.team.entity.Team;
import com.vtw.pulsar.team.repository.TeamRepository;
import com.vtw.pulsar.user.repository.UserRepository;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private UserRepository userRepository;
    
    public List<Team> getTeams() {
    	
        return teamRepository.findAll();
    }
    
    @Transactional
    public List<Team> getTeams(String searchName, PageInfo page) {
    	
    	//page.setKey("users.size");
    	return teamRepository.findAll(searchName, page.toPageable("id")).stream().map(team -> {
        	//team.setUserCount(team.getUsers().size());
        	return team;
    	}).collect(Collectors.toList());
    }
    
	public int getCount(Specification<Team> search) {
		
		return (int) teamRepository.count(search);
	}
    
	public Team getTeam(long id) {

		return teamRepository.findById(id).get();
	}

    public void addTeam(Team team) {   	
		teamRepository.save(team); 
    }
    
    public long generateId() {
    	
    	return 0;
    }

	public void updateTeam(Team team) {

		teamRepository.save(team);
	}

	public void deleteTeam(int id) {

		teamRepository.deleteById((long)id);
	}
    
    
}
