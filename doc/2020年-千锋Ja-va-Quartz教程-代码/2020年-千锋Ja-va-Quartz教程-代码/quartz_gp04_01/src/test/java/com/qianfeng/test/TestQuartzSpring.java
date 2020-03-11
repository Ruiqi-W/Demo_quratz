package com.qianfeng.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName TestQuartz
 * @Description: TODO
 * @Author 臧红久
 * @Date 2019/10/19
 * @Version V1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_quartz.xml")
public class TestQuartzSpring {

    @Autowired
    private StdScheduler scheduler;

    @Test
    public void test1() throws InterruptedException, SchedulerException {
        System.out.println("hello");
        Thread.sleep(5000);

        // 删除 job
        /*scheduler.pauseTrigger(TriggerKey.triggerKey("hw_trigger","hw_trigger_group"));//暂停触发器的计时
        scheduler.unscheduleJob(TriggerKey.triggerKey("hw_trigger", "hw_trigger_group"));// 移除触发器中的任务
        scheduler.deleteJob(JobKey.jobKey("job1","group1"));//移除trigger后，删除工作*/

        // job 暂停 和 恢复
       /* scheduler.pauseJob(JobKey.jobKey("job1","group1"));
        Thread.sleep(30000);
        scheduler.resumeJob(JobKey.jobKey("job1","group1"));*/

        GroupMatcher<JobKey> groups = GroupMatcher.groupEquals("group1");//名字等于group1
        scheduler.pauseJobs(groups);// 暂停组内所有的job
        Thread.sleep(5000);
        scheduler.resumeJobs(groups);
        Thread.sleep(5000);
    }
}
