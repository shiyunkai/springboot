package com.example.producer.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @Auther: shiyunkai
 * @Date: 2019/03/22 17:08
 * @Description: 存储在数据库中的任务信息实体类
 */
@Data
public class SchejobMsg implements Serializable {
    private static final long serialVersionUID = 8326085734026006555L;

    private Integer id;

    private String jobName;//任务名

    private String jobGroup;//任务组名

    private String jobStatus;//任务状态

    private String cronExperssion;//任务cron表达式

    private Date createTime;//任务创建时间

    private Integer jobDataId;//任务中存放的数据id,这里放的数据指的是向MQ中发送的消息


}
