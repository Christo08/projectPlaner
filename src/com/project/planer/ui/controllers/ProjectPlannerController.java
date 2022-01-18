package com.project.planer.ui.controllers;

import com.project.planer.backend.controllers.ProjectController;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class ProjectPlannerController {
    @FXML
    private TableView<?> projectTable;

    @FXML
    private TabPane detailsView;

    @FXML
    private BorderPane projectTimeLine;

    private ProjectController projectController;

    public void setAbsolutePathToDataFolder(String absolutePathToDataFolder) {
        projectController = new ProjectController(absolutePathToDataFolder);
    }
}