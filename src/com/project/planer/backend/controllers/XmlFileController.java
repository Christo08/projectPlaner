package com.project.planer.backend.controllers;

import com.project.planer.backend.Warppers.ProjectList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlFileController {

    private File projectsFile;
    private JAXBContext projectsContext;
    private Marshaller projectsMarshaller;

    public XmlFileController(String absolutePathToDataFolder) {
        projectsFile = new File(absolutePathToDataFolder+"\\Data.xml");

        try {
            projectsContext  = JAXBContext.newInstance(ProjectList.class);
            projectsMarshaller = projectsContext.createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void saveProjects(ProjectList project) throws JAXBException {
        projectsMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        projectsMarshaller.marshal(project, projectsFile);
    }

    public ProjectList loadProjects(){
        try {
            return (ProjectList) projectsContext.createUnmarshaller().unmarshal(projectsFile);
        } catch (JAXBException e) {
            return new ProjectList();
        }
    }
}
