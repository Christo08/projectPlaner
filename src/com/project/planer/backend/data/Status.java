package com.project.planer.backend.data;

public class Status {

    private String name;
    private String description;
    private long startTime;
    private long stopTime;

    public Status(String name, String description, long startTime, long stopTime) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.stopTime = stopTime;
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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }
}
