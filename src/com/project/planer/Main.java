package com.project.planer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Project Planer");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/fxml/ProjectPlanner.fxml"));

        VBox root = loader.load();

        primaryStage.setScene(new Scene(root, 850, 675));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
