package com.qianfeng.controller;

import com.qianfeng.pojo.JobAndTrigger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName QuartzController
 * @Description: TODO
 * @Author 臧红久
 * @Date 2019/10/19
 * @Version V1.0
 **/
@Controller
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired //注入了工厂中 调度器
    private Scheduler scheduler;

    @RequestMapping("/add")
    public String addJob(JobAndTrigger jt) throws ClassNotFoundException, SchedulerException {
        // 创建JobDetail
        JobDetail jobDetail=null;
        jobDetail = JobBuilder.newJob((Class<? extends Job>)Class.forName(jt.getJobClassName()))
                .withIdentity(jt.getJobName(), jt.getJobGroup()).storeDurably(true).build();
        CronTrigger cronTrigger = null;
        cronTrigger = TriggerBuilder.newTrigger().withIdentity(jt.getJobName(),jt.getJobGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(jt.getCronExpression()))
                .build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
        return "index";
    }
}
