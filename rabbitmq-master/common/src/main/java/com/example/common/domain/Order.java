package com.example.common.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/* * @Auther: shiyunkai
 * @Description: 订单表
 * @Param:
 * @Date:  12:37 2019/3/23
 * @return:
 **/
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = -6080897836948252094L;

    private Integer id;
    private String name;
    private String messageId;
    private Date createTime;
    private Date updateTime;

}
