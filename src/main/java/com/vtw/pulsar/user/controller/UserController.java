package com.vtw.pulsar.user.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
import com.vtw.pulsar.pss.search.DefaultSpecification;
import com.vtw.pulsar.pss.search.SearchBuilder;
import com.vtw.pulsar.pss.search.SearchCriteria;
import com.vtw.pulsar.user.entity.User;
import com.vtw.pulsar.user.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	private SearchBuilder<User> builder;
	
	@Autowired
	UserService userService;
	
	@PostConstruct
    public void createSearchBuilder() {
		builder = new SearchBuilder<User>(new DefaultSpecification<User>() {
			@Override
			public Predicate insertPredicate(SearchCriteria criteria, Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (criteria.isLike() && criteria.isKey("team")) {
	        		return builder.equal(root.join("team").get("id"), criteria.getValue());
		        }
				return null;
			}
		});
    }
	   
    @GetMapping()
    public List<User> getUsers(@RequestParam(value = "search", required = false) String search, PageInfo pageInfo) {
    	return userService.getUsers(builder.build(search), pageInfo);
    }
    
    @GetMapping("/count")
    public int getCount(@RequestParam(value = "search", required = false) String search) {
    	return userService.getCount(builder.build(search));
    }
      
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
    	
        return userService.getUser((long)id);
    }

    @PostMapping()
    void addUser(@RequestBody User user) {
    	//if (user.getTeam().getId() ==  0) user.setTeam(null);
    	userService.addUser(user);
    }
    
    @PutMapping()
    void updateUser(@RequestBody User user) {
    	
    	userService.updateUser(user);
    }
    
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable int id) {
    	
    	userService.deleteUser(id);
    }
   	
	
	
}
