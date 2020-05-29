package com.spring.recipes.task.domain;

import java.util.Date;

public class JobModel {
    private int id ;
    private String name ;
    private String description ;
    private String params ;
    private int timeout ;
    private Date startTime ;
    private Date endTime ;
    private Date execTime ;
    private Date nextExecTime ;
    private int intervals ;
    private String contacts ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getExecTime() {
        return execTime;
    }

    public void setExecTime(Date execTime) {
        this.execTime = execTime;
    }

    public Date getNextExecTime() {
        return nextExecTime;
    }

    public void setNextExecTime(Date nextExecTime) {
        this.nextExecTime = nextExecTime;
    }

    public int getIntervals() {
        return intervals;
    }

    public void setIntervals(int intervals) {
        this.intervals = intervals;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "JobModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", params='" + params + '\'' +
                ", timeout=" + timeout +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", execTime=" + execTime +
                ", nextExecTime=" + nextExecTime +
                ", intervals=" + intervals +
                ", contacts='" + contacts + '\'' +
                '}';
    }
}
