package com.project.planer.backend.Warppers;

import com.project.planer.backend.data.Project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectList {

    @XmlElement(name="project")
    public List<Project> projects;

    public ProjectList() {
        projects = new ArrayList<>();
    }

    public ProjectList(List<Project> projects) {
       this.projects =projects;
    }
}
