package com.project.planer;

import com.project.planer.ui.controllers.ProjectPlannerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static String absolutePathToDataFolder;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Project Planer");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/fxml/ProjectPlanner.fxml"));

        VBox root = loader.load();
        ProjectPlannerController projectPlannerController = loader.getController();
        projectPlannerController.setAbsolutePathToDataFolder(absolutePathToDataFolder);

        primaryStage.setScene(new Scene(root, 850, 675));
        primaryStage.show();
    }

    public static void main(String[] args) {
        absolutePathToDataFolder = args[0];
        launch(args);
    }
}
