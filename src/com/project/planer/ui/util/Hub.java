package com.project.planer.ui.util;

import com.project.planer.backend.controllers.ProjectController;
import com.project.planer.backend.data.Project;

import java.util.LinkedList;
import java.util.List;

public class Hub {

    private List<EventListener> listeners;
    private static Hub instance;

    public Hub() {
        listeners = new LinkedList<>();
    }

    public static Hub getInstance(){
        if (instance == null)
            instance = new Hub();
        return instance;
    }

    public void addListeners(EventListener listener) {
        this.listeners.add(listener);
    }

    public void reloadProjects(){
        listeners.stream().forEach(listener -> listener.reload());
    }

    public void deleteProject(Project project){
        listeners.stream().forEach(listener -> listener.deleteProject(project));
    }

    public void setProjectController(ProjectController projectController){
        listeners.stream().forEach(listener -> listener.setProjectController(projectController));
    }

    public void selectProject(Project project){
        listeners.stream().forEach(listener -> listener.selectProject(project));
    }

    public void addProject(String projectID){
        listeners.stream().forEach(listener -> listener.addProject(projectID));
    }
}
