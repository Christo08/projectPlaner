package com.project.planer.backend.controllers;

import com.project.planer.backend.data.Project;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlFileController {

    private final File folder;
    private final String absolutePathToDataFolder;
    private Map<String, File> projectsFiles;
    private JAXBContext projectsContext;
    private Marshaller projectsMarshaller;

    public XmlFileController(String absolutePathToDataFolder) {
        this.absolutePathToDataFolder = absolutePathToDataFolder;
        folder = new File(absolutePathToDataFolder);
        projectsFiles = new HashMap<>();
        for (File projectFile : folder.listFiles())
        {
            projectsFiles.put(projectFile.getName(), projectFile);
        }

        try {
            projectsContext  = JAXBContext.newInstance(Project.class);
            projectsMarshaller = projectsContext.createMarshaller();
            projectsMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void createNewProjectFile(String projectID){
        File newFile = new File(absolutePathToDataFolder+"\\"+projectID +".xml");
        projectsFiles.put(newFile.getName(), newFile);
        newFile.exists();
    }

    public void saveProjects(List<Project> projects) {
        projects.parallelStream().forEach(project -> {
            try {
                projectsMarshaller.marshal(project, projectsFiles.get(project.getId()+".xml"));
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });
    }

    public List<Project> loadProjects(){
        List<Project> projects = new ArrayList<>();
        projectsFiles.values().parallelStream().forEach(project -> {
            try {
                projects.add((Project) projectsContext.createUnmarshaller().unmarshal(project));
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });
        return projects;
    }
}
