package com.vtw.pulsar.schedule.job;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import com.vtw.pulsar.schedule.entity.Schedule;
import com.vtw.pulsar.schedule.service.ScheduleService;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ScheduleJob implements Job {

	@Autowired
	private ProducerTemplate template;
	
	@Autowired
	private CamelContext camelContext;
	
	@Autowired
	ScheduleService scheduleService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try {
			updatePrevNextTime(context);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
		Exchange exchange = new DefaultExchange(camelContext);
		exchange.setProperty("TriggerId", context.getTrigger().getKey().getName());
		exchange.setProperty("JobExecutionContext", context);
		
		exchange = template.send("vtw-scheduler:" + context.getJobDetail().getKey().getName(), exchange);
	}
	
	public void updatePrevNextTime(JobExecutionContext context) throws SchedulerException {
		
//		String trgId = context.getTrigger().getKey().getName();
//		String trgGroup = context.getTrigger().getKey().getGroup();
//		Schedule schedule = new Schedule(trgId, trgGroup, trgId);
//		
//		LocalTime prevTime = LocalDateTime
//				.ofInstant(Instant.ofEpochMilli(context.getScheduledFireTime().getTime()), ZoneId.systemDefault()).toLocalTime();
//		LocalTime nextTime = LocalDateTime
//				.ofInstant(Instant.ofEpochMilli(context.getScheduledFireTime().getTime()), ZoneId.systemDefault()).toLocalTime();	
//		
//		schedule.setPrevFireTime(prevTime);
//		schedule.setNextFireTime(nextTime);
//		
//		scheduleService.updateSchedule(schedule);
	}
	
	

}
