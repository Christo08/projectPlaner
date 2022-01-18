package com.project.planer.backend.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "project")
public class Project{

    private int id;
    private String name;
    private String description;
    private long startTimeStamp;
    private long stopTimeStamp;
    private Client client;
    private List<Status> statuses;

    public Project(){

    }

    public Project(String name, String description, long startTimeStamp, long stopTimeStamp, Client client) {
        this.name = name;
        this.description = description.replaceAll("\n"," ");
        this.startTimeStamp = startTimeStamp;
        this.stopTimeStamp = stopTimeStamp;
        this.id = hashCode();
        this.client = client;
        this.statuses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement(name = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    public long getStartTimeStamp() {
        return startTimeStamp;
    }

    @XmlElement(name = "startTimeStamp")
    public void setStartTimeStamp(long startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public long getStopTimeStamp() {
        return stopTimeStamp;
    }

    @XmlElement(name = "stopTimeStamp")
    public void setStopTimeStamp(long stopTimeStamp) {
        this.stopTimeStamp = stopTimeStamp;
    }

    public Client getClient() {
        return client;
    }

    @XmlElement(name = "client")
    public void setClient(Client client) {
        this.client = client;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    @XmlElement(name = "statuses")
    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public void addStatus(Status status){
        statuses.add(status);
    }

    public Status getCurrentStatus(long currentTimestamp) {
        return statuses.stream()
                .filter(status -> status.getStartTime()<=currentTimestamp && status.getStopTime()>=currentTimestamp)
                .findFirst()
                .get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, startTimeStamp, stopTimeStamp);
    }

}
