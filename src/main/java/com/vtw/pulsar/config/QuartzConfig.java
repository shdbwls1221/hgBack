package com.vtw.pulsar.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class QuartzConfig {
	
	@Value("${pulsar.schedule-enabled}")
	private boolean scheduleEnabled;

	@Bean
	public SchedulerFactoryBeanCustomizer schedulerFactory() {
		return (schedulerFactoryBean) -> {
			schedulerFactoryBean.setAutoStartup(scheduleEnabled);
			schedulerFactoryBean.setOverwriteExistingJobs(true);
			schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
			schedulerFactoryBean.setQuartzProperties(quartzProperties());
		};
	}

    private Properties quartzProperties() {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("/config/quartz/quartz.yml"));
        yamlPropertiesFactoryBean.afterPropertiesSet();
        return yamlPropertiesFactoryBean.getObject();
    }
    
}