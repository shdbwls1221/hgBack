package com.vtw.pulsar.schedule.job;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.vtw.pulsar.schedule.entity.Schedule;
import com.vtw.pulsar.schedule.repository.ScheduleRepository;

@Configuration
public class ScheduleJobBuilder {
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	private Scheduler scheduler;
	
	@Autowired
	public void execute() throws SchedulerException {
		
		
		List<Schedule> list = (List<Schedule>) scheduleRepository.findAll();
		
		for (Schedule schedule : list) {
			
			JobKey jobKey = JobKey.jobKey(schedule.getConInterface().getIntfId(), schedule.getConInterface().getInstCode());
			TriggerKey triggerKey = TriggerKey.triggerKey(schedule.getTrgId(), schedule.getTrgGroup());
			
			boolean hasJob = scheduler.checkExists(jobKey);
			boolean hasTrigger = scheduler.checkExists(triggerKey);
			
			if(!hasTrigger) {
				
				Trigger trigger = TriggerBuilder.newTrigger()
						.withIdentity(triggerKey)
						.withDescription(schedule.getConInterface().getIntfName())
						.withSchedule(CronScheduleBuilder.cronSchedule(schedule.getCron()))
						.forJob(jobKey)
						.build();
				
				if(!hasJob) {
					
					JobDetail job = JobBuilder.newJob(ScheduleJob.class)
							.withIdentity(jobKey)
							.withDescription(schedule.getConInterface().getIntfName())
							.storeDurably()
							.build();
					
					scheduler.scheduleJob(job, trigger);
					
				} else {
					scheduler.scheduleJob(trigger);
				}
				
				scheduler.pauseTrigger(triggerKey);
			}
		}	
	}
}
