package com.vtw.pulsar.schedule.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.quartz.SchedulerException;
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
import com.vtw.pulsar.pss.search.DefaultSpecification;
import com.vtw.pulsar.pss.search.SearchBuilder;
import com.vtw.pulsar.pss.search.SearchCriteria;
import com.vtw.pulsar.schedule.entity.Schedule;
import com.vtw.pulsar.schedule.service.ScheduleService;

@RestController
@RequestMapping("/schedules")
@CrossOrigin(origins = "http://localhost:4200")
public class ScheduleController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private SearchBuilder<Schedule> builder;

	@Autowired
	ScheduleService scheduleService;
		
	@PostConstruct
    public void createSearchBuilder() {
		builder = new SearchBuilder<Schedule>(new DefaultSpecification<Schedule>() {
			@Override
			public Predicate insertPredicate(SearchCriteria criteria, Root<Schedule> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (criteria.isLike() && criteria.isKey("conInterface")) {
	        		return builder.equal(root.join("conInterface").get("intfId"), criteria.getValue());
		        }
				return null;
			}
		});
    }
	
	@GetMapping()
	public List<Schedule> getSchedules(@RequestParam(value = "search", required = false) String search, PageInfo pageInfo) {

		return scheduleService.getSchedules(builder.build(search), pageInfo);
	}
	
	@GetMapping("/count")
	public int getCount(@RequestParam(value = "search", required = false) String search) {

		return scheduleService.getCount(builder.build(search));
	}

	@GetMapping("{trgId}")
	public Schedule getSchedule(@PathVariable String id) {

		return scheduleService.getSchedule(id);
	}

	@PostMapping()
	public Schedule addSchedule(@RequestBody Schedule schedule) throws SchedulerException {
		System.out.println("add : " + schedule);
		return scheduleService.addSchedule(schedule);
	}

	@PutMapping()
	public Schedule updateSchedule(@RequestBody Schedule schedule) throws SchedulerException {

		return scheduleService.updateSchedule(schedule);
	}

	@DeleteMapping("{group}/{trgId}")
	public void deleteSchedule(@PathVariable String trgId, @PathVariable String group) throws SchedulerException {

		scheduleService.deleteSchedule(trgId, group);
	}

	@GetMapping("/runOnce/{group}/{jobId}")
	public void runOnce(@PathVariable String jobId, @PathVariable String group) throws SchedulerException {
		
		scheduleService.runOnce(jobId, group);
	}

	@GetMapping("/resume/{group}/{trgId}")
	public void resume(@PathVariable String trgId, @PathVariable String group) throws SchedulerException {

		scheduleService.resume(trgId, group);
	}

	@GetMapping("/resumeAll/{group}")
	public void resumeAll(@PathVariable String group) throws SchedulerException {

		scheduleService.resumeAll(group);
	}

	@GetMapping("/pause/{group}/{trgId}")
	public void pause(@PathVariable String trgId, @PathVariable String group) throws SchedulerException {

		scheduleService.pause(trgId, group);
	}

	@GetMapping("/pauseAll/{group}")
	public void pauseAll(@PathVariable String group) throws SchedulerException {

		scheduleService.pauseAll(group);
	}

	
	
}
