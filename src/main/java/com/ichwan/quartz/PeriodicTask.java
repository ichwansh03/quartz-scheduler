package com.ichwan.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PeriodicTask {

    @Scheduled(cron = "0/2 * * * * ?")
    public void scheduledTwoSeconds() {
        System.out.println("Periodic task: "+new Date());
    }
}
