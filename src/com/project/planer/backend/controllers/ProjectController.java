package com.project.planer.backend.controllers;

import com.project.planer.backend.Warppers.ProjectList;
import com.project.planer.backend.data.Client;
import com.project.planer.backend.data.Project;
import com.project.planer.backend.data.Status;

import javax.xml.bind.JAXBException;
import java.util.*;

public class ProjectController {
    private List<Project> projects;
    private XmlFileController xmlFileController;

    public ProjectController(String pathToFolder) {
        xmlFileController = new XmlFileController(pathToFolder);
        projects = new ArrayList<>();
        parseProjects();
    }

    public Project getProject(int projectID) {
        Project outputProject =projects.stream()
                                       .filter(project -> project.getId()==projectID)
                                       .findFirst()
                                        .get();
        return outputProject;
    }

    public Client getClientOfProject(int projectID){
        return getProject(projectID).getClient();
    }

    public List<Status> getStatusOfProject(int projectID){
        return getProject(projectID).getStatuses();
    }

    public Status getCurrentStatusOfProject(int projectID){
        return getProject(projectID).getCurrentStatus(System.currentTimeMillis() / 1000L);
    }

    public int createProject(String name, String description, long startTimeStamp, long stopTimeStamp, Client client){
        Project newProject = new Project(name,description,startTimeStamp,stopTimeStamp,client);
        projects.add(newProject);
        return newProject.getId();
    }

    public void createStatus(int projectID, String name, String description, long startTime, long stopTime){
        getProject(projectID).addStatus(new Status(name, description, startTime, stopTime));
    }

    public void saveData()
    {
        ProjectList projectList = new ProjectList(projects);
        try {
            xmlFileController.saveProjects(projectList);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void parseProjects() {
        projects = new ArrayList<>();
        projects = xmlFileController.loadProjects().projects;
    }
}
