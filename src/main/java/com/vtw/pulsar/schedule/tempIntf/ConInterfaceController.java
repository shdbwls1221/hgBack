package com.vtw.pulsar.schedule.tempIntf;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/intf")
@CrossOrigin(origins = "http://localhost:4200")
public class ConInterfaceController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private SearchBuilder<ConInterface> builder;

	@Autowired
	ConInterfaceService conInterfaceService;
		
	@PostConstruct
    public void createSearchBuilder() {
		builder = new SearchBuilder<>();
    }
	

    	
    	
	@GetMapping()
	public List<ConInterface> getConInterfaces(@RequestParam(value = "search", required = false) String search, PageInfo pageInfo) {
    	String searchName = (String) SearchCriteria.getSearches(search).stream()
    			.filter(s -> s.getKey().equals("name")).map(SearchCriteria::getValue).findFirst().orElse(null);
		return conInterfaceService.getConInterfaces(builder.build(searchName), pageInfo);
	}
	
	@GetMapping("/count")
	public int getCount(@RequestParam(value = "search", required = false) String search) {

		return conInterfaceService.getCount(builder.build(search));
	}

	@GetMapping("{intfId}")
	public ConInterface getConInterface(@PathVariable String intfId) {

		return conInterfaceService.getConInterface(intfId);
	}
	

	@PostMapping()
	public ConInterface addConInterface(@RequestBody ConInterface conInterface) {

		return conInterfaceService.addConInterface(conInterface);
	}

	@PutMapping()
	public ConInterface updateConInterface(@RequestBody ConInterface conInterface) {

		return conInterfaceService.updateConInterface(conInterface);
	}

	@DeleteMapping("/{intfId}")
	public void deleteConInterface(@PathVariable String intfId) {

		conInterfaceService.deleteConInterface(intfId);
	}
	
}
