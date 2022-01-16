package com.project.planer.backend.data;

import java.util.Objects;

public class Project{

    private int projectID;
    private String name;
    private String description;
    private long startTimeStamp;
    private long stopTimeStamp;

    public Project(String name, String description, long startTimeStamp, long stopTimeStamp) {
        this.name = name;
        this.description = description;
        this.startTimeStamp = startTimeStamp;
        this.stopTimeStamp = stopTimeStamp;
        projectID = hashCode();
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

    public long getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(long startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public long getStopTimeStamp() {
        return stopTimeStamp;
    }

    public void setStopTimeStamp(long stopTimeStamp) {
        this.stopTimeStamp = stopTimeStamp;
    }

    public int getProjectID() {
        return projectID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, startTimeStamp, stopTimeStamp);
    }

    @Override
    public String toString() {
        return  projectID +","+name+","+description +","+startTimeStamp+"," + stopTimeStamp;
    }
}
