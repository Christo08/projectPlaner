package com.project.planer.backend.controllers;

import com.project.planer.backend.data.*;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectController {
    private Map<String, Project> projects;
    private XmlFileController xmlFileController;

    public ProjectController(String pathToFolder) throws JAXBException, FileNotFoundException {
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

    public String createProject(boolean hasAPVPlane, double pvPlaneSize, boolean hasAPrivateGrid,
                                double privateGridSize, boolean hasAGenerator, double generatorSize,
                                boolean hasABatteryBlank, double batteryBlankSize, boolean needsIntegrationOfPVPlaneAndPrivateGrid,
                                boolean needsIntegrationOfPVPlaneAndGenerator, boolean needsIntegrationOfPVPlaneAndBatteryBlank, long startDate,
                                int timeFrame, Client client, FinancialIndicators financialIndicators, AuthorityApproval authorityApproval){
        Project project = new Project(hasAPVPlane, pvPlaneSize, hasAPrivateGrid,
        privateGridSize, hasAGenerator, generatorSize,
        hasABatteryBlank, batteryBlankSize, needsIntegrationOfPVPlaneAndPrivateGrid,
        needsIntegrationOfPVPlaneAndGenerator, needsIntegrationOfPVPlaneAndBatteryBlank, startDate,
        timeFrame, client, financialIndicators, authorityApproval);
        projects.put(project.getId(), project);
        return project.getId();
    }


    public String createProject(Client client, FinancialIndicators financialIndicators, AuthorityApproval authorityApproval,
                                boolean hasAPVPlane, double pvPlaneSize, boolean hasAPrivateGrid,
                                double privateGridSize, boolean hasAGenerator, double generatorSize,
                                boolean hasABatteryBlank, double batteryBlankSize, boolean needsIntegrationOfPVPlaneAndPrivateGrid,
                                boolean needsIntegrationOfPVPlaneAndGenerator, boolean needsIntegrationOfPVPlaneAndBatteryBlank, LocalDate startDate,
                                int timeFrame){
        Project project = new Project(hasAPVPlane, (hasAPVPlane) ? pvPlaneSize: 0, hasAPrivateGrid,
                (hasAPrivateGrid) ? privateGridSize : 0, hasAGenerator, (hasAGenerator) ? generatorSize : 0,
                hasABatteryBlank, batteryBlankSize, needsIntegrationOfPVPlaneAndPrivateGrid,
                needsIntegrationOfPVPlaneAndGenerator, needsIntegrationOfPVPlaneAndBatteryBlank, startDate,
                timeFrame, client, financialIndicators, authorityApproval);
        projects.put(project.getId(), project);
        return project.getId();
    }

    public void createStatus(String projectID, String name, String description, LocalDate startDate, LocalDate stopDate){
        getProject(projectID).addStatus(new Status(name, description, startDate, stopDate));
    }

    public void saveData() throws JAXBException {
        xmlFileController.saveProjects(new ArrayList<>(projects.values()));
    }

    private void parseProjects() throws JAXBException, FileNotFoundException {
        projects = new HashMap<>();
        xmlFileController.loadProjects().stream().forEach(project -> projects.put(project.getId(), project));

    }

    public static LocalDate timestampToLocalDate(long timestamp){
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static long localDateToTimestamp(LocalDate date){
        if (date == null)
            return 0;
        else
            return  LocalDateTime.of(date, LocalTime.of(0,0,0)).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public void deleteProject(String id) {
        projects.remove(id);
        xmlFileController.delete(id);
    }

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        ProjectController projectController= new ProjectController(args[0]);
/*
        Client client = new Client("Die plaas","Jan De Wet","Jan De Wet",
                "012 345 6789","JanDeWet@DiePlass.com","123 street,\n Lynnwood,\n Pretoria,\n Gauteng,\n South Africa" );
        FinancialIndicators financialIndicators = new FinancialIndicators(500000.0,  1000000.0);
        AuthorityApproval authorityApproval = new AuthorityApproval("Eskom");
        String id = projectController.createProject(LocalDate.now(), 12, client, financialIndicators, authorityApproval);
        projectController.createStatus(id, "testing","",LocalDate.now(),LocalDate.now().plusMonths(12));

        Client client1 = new Client("Die twee plaas","Pieter Van Wyk","Pieter Van Wyk",
                "012 345 6789","PieterVanWyk@DieTweePlass.com","123 street,\n Wonderboom,\n Pretoria,\n Gauteng,\n South Africa" );
        FinancialIndicators financialIndicators1 = new FinancialIndicators(1000000.0,  2000000.0);
        AuthorityApproval authorityApproval1 = new AuthorityApproval("Eskom");
        String id1 = projectController.createProject(LocalDate.now(), 12, client1, financialIndicators1, authorityApproval1);
        projectController.createStatus(id1, "testing","",LocalDate.now(),LocalDate.now().plusMonths(12));*/

        projectController.saveData();
    }
}
