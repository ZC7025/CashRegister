package com.sucheng.query;

import java.util.Date;

public class TaskPlanQuery extends BaseQuery {

    private Integer id;
    /**
     * 类名
     */
    private String className;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 是否在执行
     */
    private String isStart;
    private String descript;
    /**
     * 任务状态，是否生效
     */
    private String status;
    private Date updateTime;
    private Date createdTime;

    public TaskPlanQuery() {
    }

    public TaskPlanQuery(Integer id,
                         String jobName, String cronExpression,
                         String isStart, String descript, String status,
                         Date updateTime, Date createdTime) {
        this.id = id;
        this.jobName = jobName;
        this.cronExpression = cronExpression;
        this.isStart = isStart;
        this.descript = descript;
        this.status = status;
        this.updateTime = updateTime;
        this.createdTime = createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getIsStart() {
        return isStart;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "TaskPlanVO{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", isStart='" + isStart + '\'' +
                ", descript='" + descript + '\'' +
                ", status='" + status + '\'' +
                ", updateTime=" + updateTime +
                ", createdTime=" + createdTime +
                '}';
    }
}
