package com.ichwan.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class QuartzApplication {

	public static void main(String[] args) throws SchedulerException {
		SpringApplication.run(QuartzApplication.class, args);
		//onStart();
	}

	@Bean
	public Scheduler scheduler(SchedulerFactoryBean factoryBean) throws SchedulerException {
		Scheduler scheduler = factoryBean.getScheduler();
		scheduler.start();
		return scheduler;
	}

	@Bean
	public CommandLineRunner run(Scheduler scheduler) {
		return (String[] args) -> {
			JobDetail job = JobBuilder.newJob(SimpleJob.class)
					.usingJobData("name","Ichwan Sholihin")
					.usingJobData("address","Lampung Selatan")
					.build();

			Date date = Date.from(LocalDateTime.now().plusSeconds(5)
					.atZone(ZoneId.systemDefault()).toInstant());

			Trigger trigger = TriggerBuilder.newTrigger()
					.startAt(date)
					.build();

			scheduler.scheduleJob(job, trigger);
		};
	}

	private static void onStart() throws SchedulerException {
		JobDetail job = JobBuilder.newJob(SimpleJob.class)
				.usingJobData("name", "Ichwan Sholihin")
				.usingJobData("address","Lampung")
				.build();

		Date date = Date.from(LocalDateTime.now().plusSeconds(2)
				.atZone(ZoneId.systemDefault()).toInstant());
		Trigger trigger = TriggerBuilder.newTrigger()
				.startAt(date)
				.build();

		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
	}
}
