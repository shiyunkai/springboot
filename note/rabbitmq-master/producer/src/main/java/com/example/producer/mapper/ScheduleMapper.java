package com.example.producer.mapper;

import com.example.producer.domain.SchejobMsg;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/20 10:01
 * @Description:
 */
public interface ScheduleMapper {

    Integer insert(SchejobMsg schejob);

    void update(SchejobMsg schejob);

    void delete(SchejobMsg schejobMsg);
}
