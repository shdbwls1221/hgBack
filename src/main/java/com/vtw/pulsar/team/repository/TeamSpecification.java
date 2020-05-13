package com.vtw.pulsar.team.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vtw.pulsar.team.entity.Team;
import com.vtw.pulsar.team.entity.TeamSearch;

public class TeamSpecification {
	
	@SuppressWarnings("serial")
	public static Specification<Team> searchTeam(TeamSearch search) {
		return new Specification<Team>() {
			@Override
			public Predicate toPredicate(Root<Team> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
				Predicate p = null;
				if(search.getId() == 0) {
					p = criteriaBuilder.notEqual(root.get("id"), search.getId());
				} else {
					p = criteriaBuilder.equal(root.get("id"), search.getId());
				}
				return criteriaBuilder.and(
						criteriaBuilder.like(root.get("name"), "%"+search.getName()+"%"),
						p
						);
						
			}
		};
	}
}
