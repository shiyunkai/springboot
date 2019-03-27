package com.example.producer.mapper;

import com.example.common.domain.BrokerMessageLog;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/20 10:01
 * @Description:
 */
public interface BrokerMessageLogMapper {

    int insert(BrokerMessageLog record);

    int update(BrokerMessageLog record);

    BrokerMessageLog findById(String id);
}
