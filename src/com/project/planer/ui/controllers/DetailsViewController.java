package com.project.planer.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DetailsViewController {

    @FXML
    private TabPane detailPanes;

    @FXML
    private TextArea projectDescriptionTxtA;

    @FXML
    private TextField projectNameTxtF;

    @FXML
    private DatePicker projectStartDatumDP;

    @FXML
    private DatePicker projectStopDatumDP;

    @FXML
    private Button addSatusBtn;

    @FXML
    private Button projectAddOrChangeBtn;

    @FXML
    private Button projectResetBtn;

    @FXML
    private TextArea clientAddressTxtA;

    @FXML
    private TextField clientNameTxtF;

    @FXML
    private Button clientChangeBtn;

    @FXML
    private Button clientResetBtn;

    @FXML
    private TextField clientSurnameTxtF;

    @FXML
    private TextField clientContactNumberTxtF;

    @FXML
    private TextField companyNameTxtF;

    @FXML
    void addAStatusToProject(ActionEvent event) {

    }

    @FXML
    void addOrChangeProject(ActionEvent event) {

    }

    @FXML
    void changeClientDetails(ActionEvent event) {

    }

    @FXML
    void resetClientDetails(ActionEvent event) {

    }

    @FXML
    void resetProjectDetail(ActionEvent event) {

    }

}
