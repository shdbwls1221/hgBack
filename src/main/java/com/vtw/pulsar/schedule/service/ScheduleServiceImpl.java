package com.vtw.pulsar.schedule.service;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vtw.pulsar.jpa.PageInfo;
import com.vtw.pulsar.schedule.entity.Schedule;
import com.vtw.pulsar.schedule.job.ScheduleJob;
import com.vtw.pulsar.schedule.repository.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	private Scheduler scheduler;
	
	private final ScheduleRepository scheduleRepository;
    
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
    	this.scheduleRepository = scheduleRepository;
    }

	// ** DB CRUD ** //
	
	@Override
	public List<Schedule> getSchedules(Specification<Schedule> search, PageInfo pageInfo) {

		return (List<Schedule>) scheduleRepository.findAll(search, pageInfo.toPageable("trgId")).getContent();
	}

	@Override
	public int getCount(Specification<Schedule> search) {

		return (int) scheduleRepository.count(search);
	}

	@Override
	public Schedule getSchedule(String trgId) {

		return scheduleRepository.findById(trgId).get();
	}

	@Override
	public Schedule addSchedule(Schedule schedule) throws SchedulerException {
		
		schedule.setTrgId(schedule.getConInterface().getIntfId()); // 트리거ID = 인터페이스ID
		schedule.setTrgGroup(schedule.getConInterface().getInstCode()); // 트리거 그룹 = 인터페이스 그룹
		
		generateTrigger(schedule);

		return scheduleRepository.save(schedule);
	}

	@Override
	public String generateId(Schedule schedule) {

		return null;
	}

	@Override
	public Schedule updateSchedule(Schedule schedule) throws SchedulerException {
		
		if(schedule.getCron() != null) {
			generateTrigger(schedule);
		}

		return scheduleRepository.save(schedule);
	}

	@Override
	public void deleteSchedule(String trgId, String group) throws SchedulerException {
		
		scheduler.unscheduleJob(TriggerKey.triggerKey(trgId, group));
		scheduleRepository.deleteById(trgId);
	}

	
	// ** Quartz Schedule managing ** //
	
	@Override
	public void generateTrigger(Schedule schedule) throws SchedulerException {
		
		JobKey jobKey = JobKey.jobKey(schedule.getConInterface().getIntfId(), schedule.getConInterface().getInstCode());
		
		Trigger newTrigger = TriggerBuilder.newTrigger()
				.withIdentity(schedule.getTrgId(), schedule.getTrgGroup())
				.withDescription(schedule.getConInterface().getIntfName())
				.withSchedule(CronScheduleBuilder.cronSchedule(schedule.getCron()))
				.forJob(jobKey)
				.build();
		
		if(!scheduler.checkExists(jobKey)) {
			
			JobDetail job = JobBuilder.newJob(ScheduleJob.class)
					.withIdentity(jobKey)
					.withDescription(schedule.getConInterface().getIntfName())
					.storeDurably()
					.build();
			
			scheduler.scheduleJob(job, newTrigger);
		} else {
			
			scheduler.unscheduleJob(newTrigger.getKey());
			scheduler.scheduleJob(newTrigger);
		}
		
		scheduler.pauseTrigger(newTrigger.getKey());
	}
	
	@Override
	public void runOnce(String jobId, String group) throws SchedulerException {
		
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
				.withIdentity(jobId, "once")
				.forJob(jobId, group)
				.startNow().build();
		
		scheduler.scheduleJob(trigger);
	}

	@Override
	public void resume(String trgId, String group) throws SchedulerException {
		
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(TriggerKey.triggerKey(trgId, group));		
		JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(trgId, group));
		
		Trigger newTrigger = TriggerBuilder.newTrigger()
				.withIdentity(trgId, group)
				.withDescription(trigger.getDescription())
				.withSchedule(CronScheduleBuilder.cronSchedule(trigger.getCronExpression()))
				.forJob(jobDetail)
				.build();
		
		scheduler.unscheduleJob(newTrigger.getKey());
		scheduler.scheduleJob(newTrigger);
	}

	@Override
	public void resumeAll(String group) throws SchedulerException {

		scheduler.resumeJobs(GroupMatcher.jobGroupEquals(group));
	}

	@Override
	public void pause(String trgId, String group) throws SchedulerException {

		scheduler.pauseTrigger(TriggerKey.triggerKey(trgId, group));
	}

	@Override
	public void pauseAll(String group) throws SchedulerException {

		scheduler.pauseJobs(GroupMatcher.jobGroupEquals(group));
	}

}
