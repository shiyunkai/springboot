package com.example.producer.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/20 10:27
 * @Description: 让 Druid 支持事务
 */
//@Configuration
//@EnableTransactionManagement
//@AutoConfigureAfter({DruidConfig.class})// 让这个类在 DruidConfig 被 Spring 加载之后，再去被 Spring 加载
//@MapperScan(basePackages = {"com.example.producer.mapper"})
public class TransactionConfig implements TransactionManagementConfigurer {

    //@Autowired
    //private DruidDataSource mDataSource;

    /* * @Auther: shiyunkai
     * @Description: 在这个方法里面给 DruidDataSource 开启事务
     * @Param: []
     * @Date:  10:30 2019/3/20
     * @return: org.springframework.transaction.PlatformTransactionManager
     **/
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return null; //new DataSourceTransactionManager(mDataSource);
    }
}
