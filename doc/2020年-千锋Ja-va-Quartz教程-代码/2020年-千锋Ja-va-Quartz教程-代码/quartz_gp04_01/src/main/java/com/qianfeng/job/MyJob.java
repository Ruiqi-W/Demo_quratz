package com.qianfeng.job;

import org.quartz.*;

import java.util.Date;

/**
 * @ClassName MyJob
 * @Description: job类，任务
 * @Author 臧红久
 * @Date 2019/10/19
 * @Version V1.0
 **/
@DisallowConcurrentExecution
public class MyJob implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //创建工作详情
        JobDetail jobDetail=context.getJobDetail();
        //获取工作的名称
        String name = jobDetail.getKey().getName();//任务名
        String group = jobDetail.getKey().getGroup();//任务group
        //String job=jobDetail.getJobDataMap().getString("data04");//任务中的数据
        System.out.println("job执行，job名："+name+" group:"+group+new Date());
    }
}
