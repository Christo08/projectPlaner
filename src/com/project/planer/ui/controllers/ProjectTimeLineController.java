package com.project.planer.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class ProjectTimeLineController {

    @FXML
    private DatePicker startDatumDP;

    @FXML
    private DatePicker stopDatumDP;

    @FXML
    private ComboBox<String> statusCmd;

    @FXML
    private Button zoomInBtn;

    @FXML
    private Button zoomOutBtn;

    @FXML
    private SubScene projectSS;

    @FXML
    private SubScene timeLineSS;

    @FXML
    void zoomIn(ActionEvent event) {

    }

    @FXML
    void zoomOut(ActionEvent event) {

    }

}
