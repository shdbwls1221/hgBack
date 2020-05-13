package com.vtw.pulsar.team.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtw.pulsar.jpa.PageInfo;
import com.vtw.pulsar.pss.search.SearchBuilder;
import com.vtw.pulsar.pss.search.SearchCriteria;
import com.vtw.pulsar.team.entity.Team;
import com.vtw.pulsar.team.service.TeamService;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = "http://localhost:4200")
public class TeamController {

	private SearchBuilder<Team> builder;
	
	@Autowired
	TeamService teamService;	
    	
	@PostConstruct
    public void createSearchBuilder() {
		builder = new SearchBuilder<>();
    }
	
    @GetMapping()
    public List<Team> getTeams(@RequestParam(value = "search", required = false) String search, PageInfo pageInfo) {
    	String searchName = (String) SearchCriteria.getSearches(search).stream()
    			.filter(s -> s.getKey().equals("name")).map(SearchCriteria::getValue).findFirst().orElse(null);
    	List<Team> teams =  teamService.getTeams(searchName, pageInfo);
    	return teams;
    }
    
    @GetMapping("/count")
    public int getCount(@RequestParam(value = "search", required = false) String search) {
    	
    	return teamService.getCount(builder.build(search));
    }
    
    @GetMapping("/{id}")
    public Team getTeam(@PathVariable int id) {
    	
        return teamService.getTeam((long)id);
    }

    @PostMapping()
    void addTeam(@RequestBody Team team) {
    	
    	teamService.addTeam(team);
    }
    
    @PutMapping()
    void updateTeam(@RequestBody Team team) {
    	
    	teamService.updateTeam(team);
    }
    
    @DeleteMapping("/{id}")
    void deleteTeam(@PathVariable int id) {
    	
    	teamService.deleteTeam(id);
    }
      
}
