package com.ichwan.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.DateBuilder.*;

@RestController
public class SchedulerController {

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
                .startNow()
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

        scheduler.scheduleJob(trigger);

        return "Done";
    }
}
