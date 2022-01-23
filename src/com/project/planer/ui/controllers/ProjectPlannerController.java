package com.project.planer.ui.controllers;

import com.project.planer.backend.controllers.ProjectController;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProjectPlannerController {
    @FXML
    private BorderPane allPane;

    @FXML
    private SplitPane mainFunctionPane;

    @FXML
    private HBox controlsHBox;

    @FXML
    private TableView<?> projectTable;

    @FXML
    private TabPane detailsView;

    @FXML
    private BorderPane projectTimeLine;

    private Alert failedToLoad;
    private Alert failedToSave;
    private Alert successfullySaved;

    private ProjectController projectController;

    private ScheduledExecutorService savingExecutorService;
    private Runnable autoSavingRunnable;

    public ProjectPlannerController() {
        savingExecutorService = Executors.newSingleThreadScheduledExecutor();
        autoSavingRunnable = ()->{
            try {
                projectController.saveData();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        };

        failedToLoad = new Alert(Alert.AlertType.ERROR);
        failedToLoad.setTitle("Load failed");
        failedToLoad.setHeaderText("Failed to load projects.");

        failedToSave = new Alert(Alert.AlertType.ERROR);
        failedToSave.setTitle("Saved failed");
        failedToSave.setHeaderText("Failed to saved projects.");

        successfullySaved = new Alert(Alert.AlertType.INFORMATION);
        successfullySaved.setTitle("Saved projects");
        successfullySaved.setHeaderText("Saved all projects.");
    }

    public void setAbsolutePathToDataFolder(String absolutePathToDataFolder) {

        try {
            projectController = new ProjectController(absolutePathToDataFolder);
            savingExecutorService.scheduleWithFixedDelay(autoSavingRunnable,5,5, TimeUnit.MINUTES);
        } catch (Exception e) {
            Platform.runLater(()->failedToLoad.show());
            e.printStackTrace();
        }
    }

    public void createProject(ActionEvent actionEvent) {
    }

    public void deleteProject(ActionEvent actionEvent) {
    }

    public void saveAll(ActionEvent actionEvent) {
        try {
            projectController.saveData();
            Platform.runLater(()->successfullySaved.show());
        } catch (JAXBException e) {
            Platform.runLater(()->failedToSave.show());
        }
    }

    public void onCloseProgram() {
        try {
            projectController.saveData();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public void bindDimensions(ReadOnlyDoubleProperty widthProperty, ReadOnlyDoubleProperty heightProperty) {
        projectTable.prefWidthProperty().bind(mainFunctionPane.prefWidthProperty());
        projectTable.prefHeightProperty().bind(mainFunctionPane.prefHeightProperty()
                                                               .multiply(mainFunctionPane.getDividers().get(0).positionProperty()));

        detailsView.prefWidthProperty().bind(mainFunctionPane.prefWidthProperty());
        detailsView.prefHeightProperty().bind(mainFunctionPane.prefHeightProperty()
                                                              .multiply(mainFunctionPane.getDividers().get(1).positionProperty().subtract(mainFunctionPane.getDividers().get(0).positionProperty())));

        projectTimeLine.prefWidthProperty().bind(mainFunctionPane.prefWidthProperty());
        projectTimeLine.prefHeightProperty().bind(mainFunctionPane.prefHeightProperty()
                                                                  .multiply(mainFunctionPane.getDividers().get(1).positionProperty().multiply(-1).add(1)));

        mainFunctionPane.prefWidthProperty().bind(allPane.prefWidthProperty());
        mainFunctionPane.prefHeightProperty().bind(allPane.prefHeightProperty().multiply(0.925));

        controlsHBox.prefWidthProperty().bind(allPane.prefWidthProperty());
        controlsHBox.prefHeightProperty().bind(allPane.prefHeightProperty().multiply(0.075));

        allPane.prefWidthProperty().bind(widthProperty);
        allPane.prefHeightProperty().bind(heightProperty);

    }
}