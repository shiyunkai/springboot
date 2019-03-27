package com.example.producer.constants;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/20 14:24
 * @Description: 消息的状态
 */
public enum StatusEnum {
    ORDER_SENDING(1,"消息发送中"),
    ORDER_SEND_SUCCESS(2,"消息发送成功"),
    ORDER_SEND_FAILURE(3,"消息发送失败");

    private Integer code;
    private String message;

    StatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
