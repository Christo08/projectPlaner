package com.project.planer;

import com.project.planer.ui.controllers.ProjectPlannerController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {

    private static String absolutePathToDataFolder;
    private ProjectPlannerController projectPlannerController;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Project Planer");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/fxml/ProjectPlanner.fxml"));

        BorderPane root = loader.load();
        projectPlannerController = loader.getController();
        projectPlannerController.setAbsolutePathToDataFolder(absolutePathToDataFolder);

        primaryStage.setScene(new Scene(root, 850, 675));
        projectPlannerController.bindDimensions(primaryStage.getScene().widthProperty(),primaryStage.getScene().heightProperty());
        primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,this::saveAll);
        primaryStage.show();
    }

    private <T extends Event> void saveAll(T t) {
        projectPlannerController.onCloseProgram();
    }

    public static void main(String[] args) {
        absolutePathToDataFolder = args[0];
        launch(args);
    }
}
