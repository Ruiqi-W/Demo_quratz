package com.qianfeng.pojo;

import lombok.Data;

import java.math.BigInteger;

/**
 * @ClassName JobAndTrigger
 * @Description: TODO
 * @Author 臧红久
 * @Date 2019/10/19
 * @Version V1.0
 **/
@Data
public class JobAndTrigger {
    private String jobName;
    private String jobGroup;
    private String jobClassName;
    private String triggerName;
    private String triggerGroup;
    private BigInteger repeatInterval;
    private BigInteger timesTriggered;
    private String cronExpression;
    private String timeZoneId;
}
