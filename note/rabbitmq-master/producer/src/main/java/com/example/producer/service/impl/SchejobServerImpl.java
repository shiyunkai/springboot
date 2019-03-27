package com.example.producer.service.impl;

import com.example.producer.domain.SchejobMsg;
import com.example.producer.mapper.ScheduleMapper;
import com.example.producer.scheduleJobs.job.ScheduleJob;
import com.example.producer.service.SchejobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


/**
 * @Auther: shiyunkai
 * @Date: 2019/03/22 17:15
 * @Description:
 */
@Service
public class SchejobServerImpl implements SchejobService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    ScheduleMapper scheduleMapper;

    // 向数据库中添加或者更新定时任务
    @Override
    public void addJob(SchejobMsg schejob) {

        // 查看Schejob对应的trigger是否存在,根据任务名称和任务组名可以得到
        TriggerKey triggerKey = TriggerKey.triggerKey(schejob.getJobName(), schejob.getJobGroup());
        Trigger trigger = null;

        try {
            // 如果不存在则重新生成
            if(null == scheduler.getTrigger(triggerKey)){
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(schejob.getJobName(),schejob.getJobGroup())
                        .withSchedule(CronScheduleBuilder.cronSchedule(schejob.getCronExperssion()))
                        .build();
                // 添加任务进数据库
                scheduleMapper.insert(schejob);
            }else{
             // 如果存在则进行更新,先在任务调度器中进行删除
                removeJob(triggerKey,new JobKey(schejob.getJobName(),schejob.getJobGroup()),schejob);
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(schejob.getJobName(),schejob.getJobGroup())
                        .withSchedule(CronScheduleBuilder.cronSchedule(schejob.getCronExperssion()))
                        .build();
                // 更新数据库中的任务
                scheduleMapper.update(schejob);
            }

            // 用任务调度器进行调用添加或更新的任务
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class)
                    .withIdentity(schejob.getJobName(),schejob.getJobGroup())
                    .build();
            // 在jobDetail中添加信息
            jobDetail.getJobDataMap().put("schejob",schejob);
            scheduler.scheduleJob(jobDetail,trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteJob(String jobName, String jobGroup, SchejobMsg schejobMsg) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        JobKey jobKey = new JobKey(jobName, jobGroup);
        try {
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
            scheduleMapper.delete(schejobMsg);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pauseJob(String jobName, String jobGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            scheduler.pauseTrigger(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resumeJob(String jobName, String jobGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            scheduler.resumeTrigger(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SchejobMsg> getRunningJob() {
        List<JobExecutionContext> jobExecutionContexts = null;
        try {
            jobExecutionContexts = scheduler.getCurrentlyExecutingJobs();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        System.out.println(jobExecutionContexts.size());
        List<SchejobMsg> schejobs = new LinkedList<SchejobMsg>();
        for (JobExecutionContext jobExecutionContext : jobExecutionContexts) {
            System.out.println(jobExecutionContext.getJobDetail().getKey().getName() + jobExecutionContext.getJobDetail().getKey().getGroup());
            Trigger trigger = jobExecutionContext.getTrigger();
            if (trigger instanceof CronTrigger) {
                SchejobMsg schejob = new SchejobMsg();
                JobDetail jobDetail = jobExecutionContext.getJobDetail();
                JobKey jobkey = jobDetail.getKey();
                schejob.setJobGroup(jobkey.getGroup());
                schejob.setJobName(jobkey.getName());
                schejob.setCronExperssion(((CronTrigger) trigger).getCronExpression());
                schejob.setJobStatus("0");
                schejobs.add(schejob);
            }
        }
        return schejobs;
    }



    /* * @Auther: shiyunkai
     * @Description: 删除定时任务
     * @Param: []
     * @Date:  9:44 2019/3/23
     * @return: void
     **/
    private void removeJob(TriggerKey triggerKey, JobKey jobKey, SchejobMsg schejobMsg){
        try {
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
            scheduleMapper.delete(schejobMsg);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
