package com.project.planer.ui.util;

import com.project.planer.backend.controllers.ProjectController;
import com.project.planer.backend.data.Project;

public interface EventListener {
    void reload();

    void deleteProject(Project project);

    void setProjectController(ProjectController projectController);

    void selectProject(Project project);

    void addProject(String projectID);
}
