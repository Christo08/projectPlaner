package com.project.planer.ui.controllers;

import com.project.planer.backend.controllers.ProjectController;
import com.project.planer.backend.data.Project;
import com.project.planer.ui.util.EventListener;
import com.project.planer.ui.util.Hub;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProjectPlannerController implements Initializable, EventListener {
    @FXML
    private BorderPane allPane;

    @FXML
    private SplitPane mainFunctionPane;

    @FXML
    private HBox controlsHBox;

    @FXML
    private ScrollPane projectTable;

    @FXML
    private TabPane detailsView;

    @FXML
    private BorderPane projectTimeLine;

    @FXML
    private Button deleteBtn;

    private Alert failedToLoadProjects;
    private Alert failedToLoadCreatorFxml;
    private Alert failedToSave;
    private Alert successfullySaved;

    private ProjectController projectController;

    private ScheduledExecutorService savingExecutorService;
    private Runnable autoSavingRunnable;

    private Stage projectCreatorStage;

    private Hub hub;
    private Project selectedProject;

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
            projectController= new ProjectController(absolutePathToDataFolder);
            hub.setProjectController(projectController);
            hub.addListeners(this);
            hub.reloadProjects();
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

            Scene projectCreatorScene = new Scene(projectCreatorParent, 725, 585);
            projectCreatorStage = new Stage();
            projectCreatorStage.initModality(Modality.APPLICATION_MODAL);
            projectCreatorStage.setTitle("Project Creator");

            projectCreatorStage.setScene(projectCreatorScene);
            hub.setProjectController(projectController);
            projectCreatorStage.showAndWait();
        } catch (IOException e) {
            failedToLoadCreatorFxml.show();
            e.printStackTrace();
        }
    }

    public void deleteProject(ActionEvent actionEvent) {
        projectController.deleteProject(selectedProject.getId());
        Hub.getInstance().deleteProject(selectedProject);
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

        allPane.prefHeightProperty().bind(heightProperty);
        mainFunctionPane.prefHeightProperty().bind(allPane.prefHeightProperty().multiply(0.925));
        controlsHBox.prefHeightProperty().bind(allPane.prefHeightProperty().multiply(0.075));

        projectTable.prefHeightProperty().bind(mainFunctionPane.prefHeightProperty()
                                                               .multiply(mainFunctionPane.getDividers().get(0).positionProperty()));
        detailsView.prefHeightProperty().bind(mainFunctionPane.prefHeightProperty()
                                                              .multiply(mainFunctionPane.getDividers().get(1).positionProperty().subtract(mainFunctionPane.getDividers().get(0).positionProperty())));

        projectTimeLine.prefHeightProperty().bind(mainFunctionPane.prefHeightProperty()
                                                                  .multiply(new SimpleDoubleProperty(1).subtract(mainFunctionPane.getDividers().get(1).positionProperty())));

        allPane.prefWidthProperty().bind(widthProperty);
        controlsHBox.prefWidthProperty().bind(allPane.prefWidthProperty());
        mainFunctionPane.prefWidthProperty().bind(allPane.prefWidthProperty());
        projectTimeLine.prefWidthProperty().bind(mainFunctionPane.prefWidthProperty());
        detailsView.prefWidthProperty().bind(mainFunctionPane.prefWidthProperty());
        projectTable.prefWidthProperty().bind(mainFunctionPane.prefWidthProperty());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hub = Hub.getInstance();
    }

    @Override
    public void reload() {

    }

    @Override
    public void deleteProject(Project project) {

    }

    @Override
    public void setProjectController(ProjectController projectController) {

    }

    @Override
    public void selectProject(Project project) {
        selectedProject = project;
        deleteBtn.setDisable(project == null);
    }

    @Override
    public void addProject(String projectID) {
        try {
            projectController.saveData();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}