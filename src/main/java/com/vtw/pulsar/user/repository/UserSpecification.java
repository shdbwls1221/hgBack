package com.vtw.pulsar.user.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vtw.pulsar.team.entity.Team;
import com.vtw.pulsar.user.entity.User;
import com.vtw.pulsar.user.entity.UserSearch;

public class UserSpecification {
	
	public static Specification<User> searchUser(UserSearch search) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Join<User, Team> u = root.join("team");
				
				Predicate teamJoin = null;
				if(search.getTeamId() == 0) {
					teamJoin = criteriaBuilder.notEqual(u.get("id"), search.getTeamId());
				} else {
					teamJoin = criteriaBuilder.equal(u.get("id"), search.getTeamId());
				}
				return criteriaBuilder.and(
						criteriaBuilder.like(root.get("name"), "%"+search.getName()+"%"),
						teamJoin,
						criteriaBuilder.between(root.get("join"), LocalDate.parse(search.getStartDate()), LocalDate.parse(search.getEndDate()))
						
						);
						
			}
		};
	}
}
