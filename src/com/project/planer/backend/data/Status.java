package com.project.planer.backend.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "status")
public class Status {

    private String name;
    private String description;
    private long startTime;
    private long stopTime;

    public Status() {
    }

    public Status(String name, String description, long startTime, long stopTime) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "startTime")
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @XmlElement(name = "stopTime")
    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }
}
