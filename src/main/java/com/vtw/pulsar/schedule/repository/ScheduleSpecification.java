package com.vtw.pulsar.schedule.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vtw.pulsar.schedule.entity.Schedule;
import com.vtw.pulsar.schedule.entity.ScheduleSearch;
import com.vtw.pulsar.schedule.tempIntf.ConInterface;

public class ScheduleSpecification {
	
	public static Specification<Schedule> searchSchedule(ScheduleSearch search) {
		return new Specification<Schedule>() {
			@Override
			public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
				Join<Schedule, ConInterface> u = root.join("connectionInterface");
				Predicate scheduleJoin = null;
				
				if(search.getTrgId() == null) {
					scheduleJoin = criteriaBuilder.notEqual(u.get("trgId"), search.getTrgId());
				} else {
					scheduleJoin = criteriaBuilder.equal(u.get("trgId"), search.getTrgId());
				}
				return criteriaBuilder.and(
						//criteriaBuilder.like(root.get("name"), "%"+search.getName()+"%"),
						scheduleJoin
						
						);
			}
		};
	}
}
