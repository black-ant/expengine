package com.gang.etl.plugin.config;

import com.gang.etl.plugin.logic.CronQuartzJobBean;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname QuartzSampleConfig
 * @Description TODO
 * @Date 2021/2/10 14:48
 * @Created by zengzg
 */
//@Configuration
public class QuartzSampleConfig {

    // 更新频率 , 每30秒更新一次
    private static final int TIME = 30;

    // JobDetail :  注册任务
//    @Bean
//    public JobDetail weatherDataSyncJobDetail() {
//        return JobBuilder.newJob(CronQuartzJobBean.class).withIdentity("myjob")
//                .storeDurably().build();
//    }
//
//    // Trigger : 触发器
//    @Bean
//    public Trigger weatherDataSyncTrigger() {
//        SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(TIME).repeatForever();
//        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
//                .withIdentity("weatherDataSyncTrigger").withSchedule(schedBuilder).build();
//    }


}
