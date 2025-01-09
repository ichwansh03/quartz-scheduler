package com.ichwan.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimpleJobListener implements JobListener {

    public static final Logger logger = LoggerFactory.getLogger(SimpleJobListener.class);

    @Override
    public String getName() {
        return "Simple Job Listener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        logger.info("job to be executed {}", context.getJobDetail().getKey());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        logger.info("job execution vetoed {}", context.getJobDetail().getJobClass());
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        logger.info("job was executed {}", context.getJobDetail().getJobDataMap());
    }
}
