package com.project.planer.ui.controllers;

import com.project.planer.backend.controllers.ProjectController;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
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

    private Alert failedToLoadProjects;
    private Alert failedToLoadCreatorFxml;
    private Alert failedToSave;
    private Alert successfullySaved;

    private ProjectController projectController;

    private ScheduledExecutorService savingExecutorService;
    private Runnable autoSavingRunnable;

    private Stage projectCreatorStage;

    public ProjectPlannerController() {
        savingExecutorService = Executors.newSingleThreadScheduledExecutor();
        autoSavingRunnable = ()->{
            try {
                projectController.saveData();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        };

        initAlert();
    }

    private void initAlert() {

        failedToLoadProjects = new Alert(Alert.AlertType.ERROR);
        failedToLoadProjects.setTitle("Load failed");
        failedToLoadProjects.setHeaderText("Failed to load projects.");

        failedToSave = new Alert(Alert.AlertType.ERROR);
        failedToSave.setTitle("Saved failed");
        failedToSave.setHeaderText("Failed to saved projects.");

        failedToLoadCreatorFxml= new Alert(Alert.AlertType.ERROR);
        failedToLoadCreatorFxml.setTitle("Load failed");
        failedToLoadCreatorFxml.setHeaderText("Failed to load creator fxml.");

        successfullySaved = new Alert(Alert.AlertType.INFORMATION);
        successfullySaved.setTitle("Saved projects");
        successfullySaved.setHeaderText("Saved all projects.");
    }

    public void setAbsolutePathToDataFolder(String absolutePathToDataFolder) {

        try {
            projectController = new ProjectController(absolutePathToDataFolder);
            savingExecutorService.scheduleWithFixedDelay(autoSavingRunnable,5,5, TimeUnit.MINUTES);
        } catch (Exception e) {
             failedToLoadProjects.show();
            e.printStackTrace();
        }
    }

    public void createProject(ActionEvent actionEvent) {


        try {
            URL fxmlURL = Paths.get("C:\\Users\\User\\OneDrive\\Work\\PVSolution\\projectPlaner\\src\\com\\project\\planer\\ui\\fxml\\ProjectCreator.fxml").toUri().toURL();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(fxmlURL);
            Parent projectCreatorParent = fxmlLoader.load();
            ProjectCreatorController dialogController = fxmlLoader.getController();
            dialogController.setMainController(this);

            Scene projectCreatorScene = new Scene(projectCreatorParent, 725, 585);
            projectCreatorStage = new Stage();
            projectCreatorStage.initModality(Modality.APPLICATION_MODAL);
            projectCreatorStage.setTitle("Project Creator");

            projectCreatorStage.setScene(projectCreatorScene);
            projectCreatorStage.showAndWait();
        } catch (IOException e) {
            failedToLoadCreatorFxml.show();
            e.printStackTrace();
        }
    }

    public void deleteProject(ActionEvent actionEvent) {
    }

    public void saveAll(ActionEvent actionEvent) {
        try {
            projectController.saveData();
            successfullySaved.show();
        } catch (JAXBException e) {
            failedToSave.show();
            e.printStackTrace();
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