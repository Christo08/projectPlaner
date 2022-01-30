package com.project.planer.backend.controllers;

import com.project.planer.backend.data.Project;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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

    public void saveProjects(List<Project> projects) throws JAXBException {
        for (Project project:projects) {
            File newFile = new File(absolutePathToDataFolder+"\\"+project.getId() +".xml");
            projectsMarshaller.marshal(project, newFile);
        }
    }

    public List<Project> loadProjects() throws JAXBException, FileNotFoundException {
        List<Project> projects = new ArrayList<>();

        for (File projectFile : projectsFiles.values())
        {
            projects.add((Project) projectsContext.createUnmarshaller().unmarshal(projectFile));
            PrintWriter writer = new PrintWriter(projectFile);
            writer.print("");
            writer.close();
        }

        projectsFiles.clear();

        return projects;
    }

    public void delete(String id) {
        projectsFiles.get(id).delete();
        projectsFiles.remove(id);
    }
}
