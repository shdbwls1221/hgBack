package com.vtw.pulsar.user.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vtw.pulsar.jpa.PageInfo;
import com.vtw.pulsar.user.entity.User;
import com.vtw.pulsar.user.entity.UserSearch;
import com.vtw.pulsar.user.repository.UserRepository;
import com.vtw.pulsar.user.repository.UserSpecification;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
    
    public UserServiceImpl(UserRepository userRepository) {
    	this.userRepository = userRepository;
    }
    
    @Override
	public List<User> getUsers(Specification<User> search, PageInfo pageInfo) {
	
		return (List<User>) userRepository.findAll(search, pageInfo.toPageable("id")).getContent();
	}
    
    
	public int getCount(Specification<User> search) {
		
		return (int) userRepository.count(search);
	}
    
	public User getUser(long id) {

		return userRepository.findById(id).get();
	}

    public void addUser(User user) {
    	userRepository.save(user);
    }
    
    public long generateId() {
    	
    	return 0;
    }

	public User updateUser(User user) {

		return userRepository.save(user);
	}

	public void deleteUser(int id) {

		userRepository.deleteById((long)id);
	}

	
}
