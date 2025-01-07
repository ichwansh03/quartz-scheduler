package com.ichwan.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
