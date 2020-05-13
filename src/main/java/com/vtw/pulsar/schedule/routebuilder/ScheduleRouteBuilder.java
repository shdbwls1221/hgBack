package com.vtw.pulsar.schedule.routebuilder;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vtw.pulsar.schedule.entity.Schedule;
import com.vtw.pulsar.schedule.repository.ScheduleRepository;

@Component
public class ScheduleRouteBuilder extends RouteBuilder {
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Override
	public void configure() throws Exception {

		List<Schedule> list = scheduleRepository.findAll()
				.stream().filter(distinctByKey(s -> s.getConInterface().getIntfId()))
				.collect(Collectors.toList());

		for (Schedule schedule : list) {
			
			from("vtw-scheduler:" + schedule.getConInterface().getIntfId())
			.log(schedule.getConInterface().getIntfName());
		}
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Set<Object> seen = ConcurrentHashMap.newKeySet();
	    return t -> seen.add(keyExtractor.apply(t));
	}
	
}
