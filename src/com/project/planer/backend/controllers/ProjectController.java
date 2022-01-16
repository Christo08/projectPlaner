package com.project.planer.backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.planer.backend.data.Client;
import com.project.planer.backend.data.Project;
import com.project.planer.backend.data.Status;

public class ProjectController {
    private List<Project> projects;
    private Map<Integer, Client> clients;
    private Map<Integer, List<Status>> statuses;

    public ProjectController() {
        projects = new ArrayList<>();
        clients = new HashMap<>();
        statuses = new HashMap<>();
    }

    public Project getProject(int projectID) {
        Project outputProject =projects.stream()
                                       .filter(project -> project.getProjectID()==projectID)
                                       .findFirst()
                                        .get();
        return outputProject;
    }

    public Client getClient(int projectID){
        return clients.get(projectID);
    }

    public List<Status> getStatus(int projectID){
        return statuses.get(projectID);
    }

    public int createProject(String name, String description, long startTimeStamp, long stopTimeStamp){
        Project newProject = new Project(name,description,startTimeStamp,stopTimeStamp);
        projects.add(newProject);
        return newProject.getProjectID();
    }

    public void createClient(int projectID, String name, String surname, String contactNumber, String companyName, String address){
        clients.put(projectID, new Client(name, surname, contactNumber, companyName, address));
    }

    public void createStatus(int projectID, String name, String description, long startTime, long stopTime){
        List<Status> statusesForClient;
        if (statuses.containsKey(projectID)){
            statusesForClient = statuses.get(projectID);
        }else {
            statusesForClient = new ArrayList<>();
        }
        statusesForClient.add(new Status(name, description, startTime, stopTime));
        statuses.put(projectID,statusesForClient);
    }

    public void saveData(){
        String projectData ="";
        String clientData ="";
        String statusesData ="";

        for (Project project: projects) {
            int projectID = project.getProjectID();

            projectData += project+"\n";
            clientData += projectID+","+ getClient(projectID)+"\n";
            for (Status status: getStatus(projectID)){
                statusesData += projectID+","+status+"\n";
            }
        }
    }
}
