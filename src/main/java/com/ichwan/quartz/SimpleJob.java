package com.ichwan.quartz;

import org.quartz.*;

import java.text.MessageFormat;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String name = dataMap.getString("name");
        String address = dataMap.getString("address");
        System.out.println(MessageFormat.format("Job {0}; Param {1} and Address: "+address, getClass(), name));
    }
}
