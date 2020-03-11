package com.qianfeng.test;

import com.qianfeng.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.DateBuilder.*;

/**
 * @ClassName TestQuartz
 * @Description: TODO
 * @Author 臧红久
 * @Date 2019/10/19
 * @Version V1.0
 **/
public class TestQuartz {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        testCronTrigger();
    }

    public static void testCronTrigger()  throws SchedulerException, InterruptedException{
        // 1. 创建scheduler，调度器  核心组件
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // 2. 定义一个Trigger,创建触发器：Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1") //定义name/group
                .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?"))
                .build();
        // 3. 创建JobDetail，JobBuilder
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("job04", "group04")
                .usingJobData("data04", "hello world~~")
                .build();
        // 4. 注册 JobDetail 和 Trigger
        scheduler.scheduleJob(jobDetail,trigger);
        // 5. 启动调度器, 内部注册的所有触发器开始计时
        scheduler.start();

        // 6.关闭调度器
        Thread.sleep(100000);
        scheduler.shutdown();
    }
    public static void testDailyTrigger() throws SchedulerException, InterruptedException{
        // 1. 创建scheduler，调度器  核心组件
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // 2. 定义一个Trigger,创建触发器：Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1") //定义name/group
                .startNow()//一旦加入scheduler，立即生效，即开始时间
                .withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                    .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9, 0))
                    .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(20, 0))
                    .onDaysOfTheWeek(MONDAY,TUESDAY,SATURDAY)
                    .withIntervalInMinutes(1))
                    //.withRepeatCount(10))
                .build();
        // 3. 创建JobDetail，JobBuilder
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("job04", "group04")
                .usingJobData("data04", "hello world~~")
                .build();
        // 4. 注册 JobDetail 和 Trigger
        scheduler.scheduleJob(jobDetail,trigger);
        // 5. 启动调度器, 内部注册的所有触发器开始计时
        scheduler.start();

        // 6.关闭调度器
        Thread.sleep(100000);
        scheduler.shutdown();
    }
    public static void testCalendarTrigger() throws SchedulerException, InterruptedException {
        // 1. 创建scheduler，调度器  核心组件
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // 2. 定义一个Trigger,创建触发器：Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1") //定义name/group
                .startNow()//一旦加入scheduler，立即生效，即开始时间
//                .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInYears(1)) //每年
//                .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInMonths(2)) //每2个月
//                .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInDays(3)) //每3天
                .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInSeconds(1))
                .build();
        // 3. 创建JobDetail，JobBuilder
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("job04", "group04")
                .usingJobData("data04", "hello world~~")
                .build();
        // 4. 注册 JobDetail 和 Trigger
        scheduler.scheduleJob(jobDetail,trigger);
        // 5. 启动调度器, 内部注册的所有触发器开始计时
        scheduler.start();

        // 6.关闭调度器
        Thread.sleep(10000);
        scheduler.shutdown();
    }
    public static void testSimpleTrigger() throws SchedulerException, InterruptedException {
        // 1. 创建scheduler，调度器  核心组件
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // 2. 定义一个Trigger,创建触发器：Trigger
        /*Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1") //定义name/group
                .startNow()//一旦加入scheduler，立即生效，即开始时间
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(2).withRepeatCount(2))
                .build();*/
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1") //定义name/group
                .startNow()//一旦加入scheduler，立即生效，即开始时间
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2).repeatForever())
                //.endAt(new GregorianCalendar(2019,9,19,9,59,10).getTime())
                .build();
        // 3. 创建JobDetail，JobBuilder
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("job04", "group04")
                .usingJobData("data04", "hello world~~")
                .build();
        // 4. 注册 JobDetail 和 Trigger
        scheduler.scheduleJob(jobDetail,trigger);
        // 5. 启动调度器, 内部注册的所有触发器开始计时
        scheduler.start();

        // 6.关闭调度器
        Thread.sleep(10000);
        scheduler.shutdown();
    }
}
