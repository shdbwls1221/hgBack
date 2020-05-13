package com.vtw.pulsar.config;

import org.apache.camel.component.direct.DirectComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelComponentConfig {
	
	@Bean("vtw-scheduler")
	public DirectComponent VipScheduler() {
		DirectComponent comp = new DirectComponent();
		return comp;
	}
	
}
