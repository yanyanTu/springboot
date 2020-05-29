package com.spring.recipes.task;

import com.spring.recipes.task.domain.JobModel;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

public abstract class AbstractJob implements  Runnable{

    protected Logger logger = LoggerFactory.getLogger(AbstractJob.class);

    @Autowired
    private JdbcTemplate jdbcTemplate ;

    @Autowired(required =  false )
    private SmsService smsService ;

    private JobModel jobModel ;

    private  int execId ;

    public void run() {
        logger.info("job {} ({}) starting ", jobModel.getName(), jobModel.getDescription());
        int status = 2;
        try {
            if (!init()) {
                logger.warn("job {} ({}) is already running by other", jobModel.getName(), jobModel.getDescription());
                return;
            }
            runJob(jobModel);
            status = 1;
        }catch (Exception e){
            logger.error("job {} ({}) exec error", jobModel.getName(), jobModel.getDescription(), e);
            try {
                // 执行异常，发送警告通知
                alert(e);
                jobExceptionHandler(e);
            }catch (Exception ignored){

            }
        }finally {
            try{
                finish(status);
            }catch (Exception e){
                logger.error("job {} ({}) finish error,", jobModel.getName(), jobModel.getDescription(), e);
            }
        }
    }

    /**
     * 执行结束后处理
     * @param status
     */
    private void finish(int status) {
        Date nextExecTime ;
        if( jobModel.getIntervals() <= 0 ){
            nextExecTime = getDate(jobModel.getStartTime(), 1);
        }else{
            Date endTime = getDate(jobModel.getEndTime(), 0);
            nextExecTime = getNextExecTime(jobModel.getIntervals());
            if( nextExecTime.after(endTime)){
                nextExecTime = getDate(jobModel.getStartTime(), 1);
            }
        }
        logger.info("job {}({}) finish, next_exec_time {}", jobModel.getName(), jobModel.getDescription(), nextExecTime);
        int update = jdbcTemplate.update("update batch_task set running =0, next_exec_time = ? where id=? and next_exec_time = ? ", nextExecTime, jobModel.getId(), jobModel.getNextExecTime());
        if( update == 0){
            logger.warn("job update fail, maybe next_exec_time changed by other");
        }
        jdbcTemplate.update("update batch_history set status=?, finish_time= now() where id = ? ", status, execId);
    }

    /**
     * 下次执行时间
     * @param intervals
     * @return
     */
    private Date getNextExecTime(int intervals) {
        Date date = jdbcTemplate.queryForObject("select now()", Date.class);
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + intervals);
        return calendar.getTime() ;
    }

    /**
     * 获取指定天的时间
     * @param date
     * @param day
     * @return
     */
    private Date getDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(date);
        Calendar calendar1 = Calendar.getInstance() ;
        calendar1.setTime(new Date());
        calendar1.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);
        calendar1.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        calendar1.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
        calendar1.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
        return calendar1.getTime() ;
    }

    /**
     * 预警提示信息
     * @param e
     */
    private void alert(Exception e){
        if( smsService != null && StringUtils.isNotBlank(jobModel.getContacts())){
            String[] contacts = jobModel.getContacts().split(",|;|，|；");
            String msg = "批处理异常，任务名：{" +jobModel.getName()+"}({"+jobModel.getDescription()+"})，异常信息：{"+ e.getMessage()+"}";
            for(String mobile : contacts){
                smsService.send(mobile, msg);
            }
        }
    }

    protected abstract void jobExceptionHandler(Exception e);

    protected abstract void runJob(JobModel jobModel);

    private boolean init(){
        int update = jdbcTemplate.update("update batch_task set running = 1, exec_time = now() where id = ? and exec_time = ? ", jobModel.getId(), jobModel.getExecTime()) ;
        if( update > 0 ){
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String ip = "";
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                logger.info("unknowHostException ", e);
            }
            final String finalIp = ip ;
            jdbcTemplate.update(connection->{
                PreparedStatement ps = connection.prepareStatement("insert into batch_history(job_id, status, exec_time, exec_ip) values(?, 0, now(), ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, jobModel.getId());
                ps.setString(2, finalIp);
                return ps ;
            }, keyHolder);
            execId = keyHolder.getKey().intValue() ;
        }
        return  update > 0 ;
    }

    public JobModel getJobModel() {
        return jobModel;
    }

    public void setJobModel(JobModel jobModel) {
        this.jobModel = jobModel;
    }
}
