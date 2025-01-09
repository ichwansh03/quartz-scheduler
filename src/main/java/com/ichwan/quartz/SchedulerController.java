package com.ichwan.quartz;

import lombok.AllArgsConstructor;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TimeZone;

import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.impl.matchers.EverythingMatcher.*;

@RestController
@AllArgsConstructor
public class SchedulerController {

    private SimpleJobListener listener;

    @GetMapping("/api")
    public String scheduledFactory() throws SchedulerException {

        SchedulerFactory sch = new StdSchedulerFactory();
        Scheduler scheduler = sch.getScheduler();

        scheduler.start();

        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("simple job", "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .withSchedule(weeklyOnDayAndHourAndMinute(DateBuilder.FRIDAY,10,30)
                        .inTimeZone(TimeZone.getTimeZone("Asia/Jakarta")))
                .build();

        scheduler.scheduleJob(job, trigger);

        return "Done";
    }

    //spesific moment in time, then repeating every 10 seconds 10 times
    @GetMapping("/spesific")
    public String scheduledSpesific() throws SchedulerException {

        SchedulerFactory sch = new StdSchedulerFactory();
        Scheduler scheduler = sch.getScheduler();

        scheduler.start();

        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                .usingJobData("name", "Abdul Ali")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("myTrigger1","group1")
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .withRepeatCount(10))
                .forJob(job)
                .build();

        scheduler.scheduleJob(job, trigger);
        //ERROR Cannot invoke "org.quartz.JobListener.getName()" because "jobListener" is null
        scheduler.getListenerManager().addJobListener(listener, allJobs());

        return "Done";
    }
}
