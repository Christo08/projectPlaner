package com.project.planer.backend.data;

import com.project.planer.backend.controllers.ProjectController;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement(name = "status")
@XmlAccessorType(XmlAccessType.FIELD)
public class Status {

    private String name;
    private String description;
    private long startDate;
    private long stopDate;

    public Status() {
    }

    public Status(String name, String description, long startDate, long stopDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.stopDate = stopDate;
    }

    public Status(String name, String description, LocalDate startDate, LocalDate stopDate) {
        this(name,description, ProjectController.localDateToTimestamp(startDate),ProjectController.localDateToTimestamp(stopDate));
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

    public long getStartDate() {
        return startDate;
    }

    public LocalDate getStartDateAsLocalDate() {
        return ProjectController.timestampToLocalDate(startDate);
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = ProjectController.localDateToTimestamp(startDate);
    }

    public long getStopDate() {
        return stopDate;
    }

    public LocalDate getStopDateAsLocalDate() {
        return ProjectController.timestampToLocalDate(stopDate);
    }

    public void setStopDate(long stopDate) {
        this.stopDate = stopDate;
    }

    public void setStopDate(LocalDate stopDate) {
        this.stopDate = ProjectController.localDateToTimestamp(stopDate);
    }
}
