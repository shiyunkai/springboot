package com.example.producer.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.common.domain.Order;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/20 11:04
 * @Description: JSON和java对象转换工具
 */
public class FastJsonConvertUtil {
    /* * @Auther: shiyunkai
     * @Description: json转换为java对象
     * @Param: [message, object]
     * @Date:  11:08 2019/3/20
     * @return: com.example.common.domain.Order
     **/
    public static Order convertJSONToObject(String message){
        Order order = JSON.parseObject(message, new TypeReference<Order>() {
        });

        return order;
    }

    /* * @Auther: shiyunkai
     * @Description: java对象转换为JSON
     * @Param: [object]
     * @Date:  11:12 2019/3/20
     * @return: java.lang.String
     **/
    public static String convertObjectToJSON(Object object){
        String text = JSON.toJSONString(object);
        return text;
    }

}
