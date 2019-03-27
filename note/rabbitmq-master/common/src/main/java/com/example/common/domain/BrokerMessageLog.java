package com.example.common.domain;

import lombok.Data;

import java.util.Date;
/* * @Auther: shiyunkai
 * @Description: 消息日志表
 * @Param:
 * @Date:  12:36 2019/3/23
 * @return:
 **/
@Data
public class BrokerMessageLog {
    private String id;

    private String message; //消息

    private Integer tryCount;//发送失败时的最大重发次数

    private String status; //消息状态

    private Date nextRetry;//下一次重试时间

    private Date createTime;

    private Date updateTime;

    private Integer subjectId;//实际发送的消息主体id(这个项目中使用到的是Order对象)


}

