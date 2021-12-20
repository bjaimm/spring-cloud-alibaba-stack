package com.example.herosoft.springclouddemo.redis.quartz;

import com.example.herosoft.springclouddemo.redis.service.UserLikeRedisService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class LikeTask extends QuartzJobBean {

    @Autowired
    private UserLikeRedisService userLikeRedisService;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("LikeTask started ---------{}"+simpleDateFormat.format(new Date()));

        userLikeRedisService.transLikedFromRedisToDB();
        userLikeRedisService.transLikedCountFromRedisToDB();
    }
}
