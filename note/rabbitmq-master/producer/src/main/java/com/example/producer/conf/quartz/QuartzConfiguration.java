package com.example.producer.conf.quartz;

import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;

/**
 * @Auther: liyinhai
 * @Date: 2018/12/21 14:51
 * @Description: quartz的配置
 */
@Configuration
@EnableScheduling
public class QuartzConfiguration {

    /* * @Auther: shiyunkai
     * @Description: //TODO
     * @Param: 自动注入的jobBean工厂,负责生成实现了Job接口的类的实例对象Bean
     * @Date:  11:01 2019/3/22
     * @return:
     **/
    public static class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements
            ApplicationContextAware {
        private transient AutowireCapableBeanFactory beanFactory;

        @Override
        public void setApplicationContext(final ApplicationContext context) {
            beanFactory = context.getAutowireCapableBeanFactory();
        }

        @Override
        protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
            final Object job = super.createJobInstance(bundle);
            //job实例注入spring ioc
            beanFactory.autowireBean(job);
            return job;
        }
    }

    /**
     * @Description: 配置任务工厂实例
     * @param: context
     * @return:
     * @auther: liyinhai
     * @date: 2018/12/21 0021 15:09
     */
    @Bean
    public JobFactory jobFactory(ApplicationContext context) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(context);

        return jobFactory;
    }

    /**
     * @Description: 配置任务调度器
     * @param: jobFactory  自定义任务工厂
     * initMethod -- 容器在初始化完 Bean 之后，会调用该属性指定的方法。这等价于 XML 配置中 的 init-method 属性。
     * destroyMethod -- 该属性与 initMethod 功能相似，在容器销毁 Bean 之前，会调用该属性指定的方法。
     * @param: dataSource  数据源
     * @return: 任务调度器
     * @auther: liyinhai
     * @date: 2018/12/21 0021 15:14
     */
    @Bean(destroyMethod = "destroy", autowire = Autowire.NO)
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, DataSource dataSource) {

        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //将spring管理job自定义工厂交由调度器维护
        schedulerFactoryBean.setJobFactory(jobFactory);
        //设置覆盖已存在的任务
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        //项目启动完成后，等待2秒后开始执行调度器初始化
        schedulerFactoryBean.setStartupDelay(2);
        //设置调度器自动运行
        schedulerFactoryBean.setAutoStartup(true);
        //设置数据源，使用与项目统一数据源
        schedulerFactoryBean.setDataSource(dataSource);
        //设置上下文spring bean name
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        //设置配置文件位置
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("/quartz.properties"));

        return schedulerFactoryBean;
    }

}
