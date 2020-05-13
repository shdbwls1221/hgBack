package com.vtw.pulsar.schedule.service;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.data.jpa.domain.Specification;

import com.vtw.pulsar.jpa.PageInfo;
import com.vtw.pulsar.schedule.entity.Schedule;

public interface ScheduleService {
	
	// ** DB CRUD ** //
	
    public List<Schedule> getSchedules(Specification<Schedule> search, PageInfo pageInfo);
    
	public int getCount(Specification<Schedule> search);
    
	public Schedule getSchedule(String trgId);

    public Schedule addSchedule(Schedule schedule) throws SchedulerException;
    
    public String generateId(Schedule schedule);

	public Schedule updateSchedule(Schedule schedule) throws SchedulerException;

	public void deleteSchedule(String trgId, String group) throws SchedulerException;
	
	
	// ** Quartz Schedule managing ** //
	
	void generateTrigger(Schedule schedule) throws SchedulerException;
	
	void runOnce(String jobId, String group) throws SchedulerException;
	
	void resume(String trgId, String group) throws SchedulerException;
	
	void resumeAll(String group) throws SchedulerException;;
		
	void pause(String trgId, String group) throws SchedulerException;
	
	void pauseAll(String group) throws SchedulerException;
	
}
