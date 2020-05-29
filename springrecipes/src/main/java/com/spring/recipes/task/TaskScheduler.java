package com.spring.recipes.task;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.spring.recipes.task.domain.JobModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@EnableScheduling
public class TaskScheduler {
    private static Logger logger = LoggerFactory.getLogger(TaskScheduler.class);

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10), new ThreadFactoryBuilder().setNameFormat("job-exec-pool-%d").build());

    @Autowired
    private ApplicationContext applicationContext ;

    @Autowired
    private JdbcTemplate jdbcTemplate ;

    private final static String createTableSql = "CREATE TABLE IF NOT EXISTS `batch_task` (\n" +
            "\t`id` INT(11) NOT NULL COMMENT '编号',\n" +
            "\t`name` VARCHAR(50) NOT NULL COMMENT '批处理名称',\n" +
            "\t`params` VARCHAR(8192) NULL DEFAULT NULL COMMENT '批处理参数',\n" +
            "\t`description` VARCHAR(100) NOT NULL COMMENT '批处理描述',\n" +
            "\t`enable` TINYINT(1) UNSIGNED ZEROFILL NOT NULL COMMENT '启用状态',\n" +
            "\t`running` TINYINT(1) UNSIGNED ZEROFILL NOT NULL COMMENT '执行状态',\n" +
            "\t`start_time` TIME NOT NULL COMMENT '开始时间',\n" +
            "\t`end_time` TIME NOT NULL COMMENT '结束时间',\n" +
            "\t`intervals` INT(11) NOT NULL COMMENT '间隔时间（0每天执行一次）',\n" +
            "\t`timeout` INT(11) NOT NULL COMMENT '超时时间（分钟）',\n" +
            "\t`exec_time` DATETIME NOT NULL COMMENT '执行时间',\n" +
            "\t`next_exec_time` DATETIME NOT NULL COMMENT '下次执行时间',\n" +
            "\t`contacts` VARCHAR(100) NULL DEFAULT NULL COMMENT '告警接收人',\n" +
            "\t`creator` VARCHAR(120) NOT NULL COMMENT '创建人',\n" +
            "\t`update` VARCHAR(120) NULL DEFAULT NULL COMMENT '修改人',\n" +
            "\t`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
            "\t`update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '修改时间',\n" +
            "\tPRIMARY KEY (`id`)\n" +
            ")\n" +
            "COMMENT='批处理任务表'\n" +
            "COLLATE='utf8_general_ci'\n" +
            "ENGINE=InnoDB;\n";

    private final static String selectJobSql = "SELECT id, name, `description`, params, timeout, start_time as startTime, end_time endTime, " +
            " exec_time execTime, next_exec_time nextExecTime, \n" +
            "`intervals`, contacts from ipcc_batch_task where enable=1 \n" +
            "and ((running=0 and next_exec_time < now()) \n" +
            "or (running=1 and timeout > 0 and date_add(exec_time, interval timeout minute) < now())) \n" +
            "order by exec_time asc limit ? ";

    @Scheduled(fixedDelay = 3000 )
    public void run(){
        int count = executor.getCorePoolSize() - executor.getActiveCount() ;
        if( count == 0){
            return ;
        }else{
            List<JobModel> jobs = getJobs(count);
            for( JobModel jobModel : jobs){
                try {
                    AbstractJob job = applicationContext.getBean(jobModel.getName(), AbstractJob.class);
                    job.setJobModel(jobModel);
                    executor.execute(job);
                }catch (NoSuchBeanDefinitionException e){
                    logger.warn(" job {} not exists ", jobModel.getName());
                }
            }
        }
    }

    @PostConstruct
    protected void init(){
        logger.info("taskScheduler init ");
        jdbcTemplate.execute(createTableSql);
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `batch_history` (\n" +
                "\t`id` INT(11) NOT NULL COMMENT '编号',\n" +
                "\t`job_id` INT(11) NULL DEFAULT NULL COMMENT '任务编号',\n" +
                "\t`status` TINYINT(2) NULL DEFAULT NULL COMMENT '执行状态0执行中 1执行成功 2执行失败',\n" +
                "\t`exec_time` TIMESTAMP NULL DEFAULT NULL COMMENT '执行时间',\n" +
                "\t`finish_time` TIMESTAMP NULL DEFAULT NULL COMMENT '执行结束时间',\n" +
                "\t`exec_ip` VARCHAR(50) NULL DEFAULT NULL COMMENT '执行机器',\n" +
                "\tPRIMARY KEY (`id`),\n" +
                "\tINDEX `job_id` (`job_id`),\n" +
                "\tINDEX `exec_time` (`exec_time`)\n" +
                ")\n" +
                "COMMENT='任务执行记录表'\n" +
                "COLLATE='utf8_general_ci'\n" +
                "ENGINE=InnoDB;\n");
        logger.info("taskScheduler init success");
    }

    private List<JobModel> getJobs(int count) {
        List<JobModel> jobModels = new ArrayList<>();
        try {
            jobModels = jdbcTemplate.query(selectJobSql, new BeanPropertyRowMapper<>(JobModel.class), count);
            logger.info("get jobs num " + jobModels.size() );
        }catch (Exception e){
            logger.error("get jobs error", e);
        }
        return  jobModels ;
    }
}
