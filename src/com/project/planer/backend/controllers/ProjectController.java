package com.project.planer.backend.controllers;

import com.project.planer.backend.data.*;

import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectController {
    private Map<String, Project> projects;
    private XmlFileController xmlFileController;

    public ProjectController(String pathToFolder) {
        xmlFileController = new XmlFileController(pathToFolder);
        parseProjects();
    }

    public Project getProject(String projectID) {
        return projects.get(projectID);
    }

    public List<Project> getProjects(){
        return new ArrayList<>(projects.values());
    }

    public Client getClientOfProject(String projectID){
        return getProject(projectID).getClient();
    }

    public List<Status> getStatusOfProject(String projectID){
        return getProject(projectID).getStatuses();
    }

    public Status getCurrentStatusOfProject(String projectID){
        return getProject(projectID).getCurrentStatus(LocalDate.now());
    }

    public String createProject(LocalDate startDate, int timeFrame, Client client, FinancialIndicators financialIndicators, AuthorityApproval authorityApproval){
        Project project = new Project(startDate, timeFrame, client, financialIndicators,  authorityApproval);
        xmlFileController.createNewProjectFile(project.getId());
        projects.put(project.getId(), project);
        return project.getId();
    }

    public void createStatus(String projectID, String name, String description, LocalDate startDate, LocalDate stopDate){
        getProject(projectID).addStatus(new Status(name, description, startDate, stopDate));
    }

    public void saveData(){
        xmlFileController.saveProjects(new ArrayList<>(projects.values()));
    }

    private void parseProjects() {
        projects = new HashMap<>();
        xmlFileController.loadProjects().stream().forEach(project -> projects.put(project.getId(), project));

    }
//platfrom
    // not remove
    // add all
    // add to arraylist
    public static void main(String[] args) {
        ProjectController projectController= new ProjectController(args[0]);
        Client client = new Client("Die plaas","Jan De Wet","Jan De Wet",
                "012 345 6789","JanDeWet@DiePlass.com","123 street,\n Lynnwood,\n Pretoria,\n Gauteng,\n South Africa" );
        FinancialIndicators financialIndicators = new FinancialIndicators(500000.0,  1000000.0);
        AuthorityApproval authorityApproval = new AuthorityApproval("Eskom");
        String id = projectController.createProject(LocalDate.now(), 12, client, financialIndicators, authorityApproval);
        projectController.createStatus(id, "testing","",LocalDate.now(),LocalDate.now().plusMonths(12));
        projectController.saveData();
    }

    public static LocalDate timestampToLocalDate(long timestamp){
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static long localDateToTimestamp(LocalDate date){
        return  LocalDateTime.of(date, LocalTime.of(0,0,0)).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
