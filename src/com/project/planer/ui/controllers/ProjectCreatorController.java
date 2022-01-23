package com.project.planer.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProjectCreatorController {

    @FXML
    private CheckBox hasPrivateGridIntegrationCB;

    @FXML
    private CheckBox hasGeneratorIntegrationCB;

    @FXML
    private CheckBox hasBatteryBlankIntegrationCB;

    @FXML
    private DatePicker startDateOfProjectDP;

    @FXML
    private Spinner<?> timeFrameSpn;

    @FXML
    private TextField businessNameTxtf;

    @FXML
    private CheckBox isOwnerAndContactTheSamePersonCB;

    @FXML
    private TextField ownerNameTxt;

    @FXML
    private TextField ownerSurnameTxt;

    @FXML
    private TextField contactPersonNameTxt;

    @FXML
    private TextField contactPersonSurnameTxt;

    @FXML
    private TextField phoneNumberTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextArea addressOrGPSCoordinatesTxtA;

    @FXML
    private ComboBox<?> provinceCmb;

    @FXML
    private CheckBox hasPVPlaneCB;

    @FXML
    private CheckBox hasPrivateGridCB;

    @FXML
    private CheckBox hasGeneratorCB;

    @FXML
    private CheckBox hasBatteryBlankCB;

    @FXML
    private Spinner<?> sizeOfPVPlaneSpn;

    @FXML
    private Spinner<?> sizeOfPrivateGridSpn;

    @FXML
    private Spinner<?> sizeOfGeneratorSpn;

    @FXML
    private Spinner<?> sizeOfBatteryBlankSpn;

    @FXML
    private Spinner<?> budgetCostSpn;

    @FXML
    private Spinner<?> actualCostSpn;

    @FXML
    private Spinner<?> sellPriceSpn;

    @FXML
    private DatePicker cocIssieDateDP;

    @FXML
    private TextField cocCertificateNrTxt;

    @FXML
    private TextField nrs097CertificateNrTxt;

    @FXML
    private DatePicker nrs097IssieDateDP;

    @FXML
    private ComboBox<?> networkProvideCmb;

    @FXML
    private DatePicker approvalHandedDateDP;

    @FXML
    private DatePicker approvalReturnedFormClientDateDP;

    @FXML
    private DatePicker paymentedDateDP;

    @FXML
    private DatePicker approvalFromNetworkProvideDateDP;

    @FXML
    private DatePicker nersaAppilcationDateDP;

    @FXML
    private DatePicker nersaApprovalDateDP;

    @FXML
    private Button saveBtn;

    public ProjectCreatorController() {
        
    }

    @FXML
    void closeWindow(ActionEvent event) {

    }

    @FXML
    void saveProject(ActionEvent event) {

    }

}
