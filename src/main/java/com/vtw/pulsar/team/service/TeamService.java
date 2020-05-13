package com.vtw.pulsar.team.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.vtw.pulsar.jpa.PageInfo;
import com.vtw.pulsar.pss.search.SearchCriteria;
import com.vtw.pulsar.team.entity.Team;
import com.vtw.pulsar.team.entity.TeamSearch;

public interface TeamService {
	   
	public List<Team> getTeams();
	
    public List<Team> getTeams(String searchName, PageInfo pageInfo);
    
	public int getCount(Specification<Team> search);
    
	public Team getTeam(long id);

    public void addTeam(Team team);
    
    public long generateId();

	public void updateTeam(Team team);

	public void deleteTeam(int id);
    
}
