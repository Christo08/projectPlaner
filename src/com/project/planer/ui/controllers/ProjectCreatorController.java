package com.project.planer.ui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ProjectCreatorController implements Initializable {

    @FXML
    private CheckBox hasPrivateGridIntegrationCB;

    @FXML
    private CheckBox hasGeneratorIntegrationCB;

    @FXML
    private CheckBox hasBatteryBlankIntegrationCB;

    @FXML
    private DatePicker startDateOfProjectDP;

    @FXML
    private Spinner<Integer> timeFrameSpn;

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
    private ComboBox<String> provinceCmb;

    @FXML
    private CheckBox hasPVPlaneCB;

    @FXML
    private CheckBox hasPrivateGridCB;

    @FXML
    private CheckBox hasGeneratorCB;

    @FXML
    private CheckBox hasBatteryBlankCB;

    @FXML
    private Spinner<Double> sizeOfPVPlaneSpn;

    @FXML
    private Spinner<Double> sizeOfPrivateGridSpn;

    @FXML
    private Spinner<Double> sizeOfGeneratorSpn;

    @FXML
    private Spinner<Double> sizeOfBatteryBlankSpn;

    @FXML
    private Spinner<Double> budgetCostSpn;

    @FXML
    private Spinner<Double> actualCostSpn;

    @FXML
    private Spinner<Double> sellPriceSpn;

    @FXML
    private CheckBox hasCOCCertificateCB;

    @FXML
    private DatePicker cocIssieDateDP;

    @FXML
    private TextField cocCertificateNrTxt;

    @FXML
    private CheckBox hasNRS097CertificateCB;

    @FXML
    private TextField nrs097CertificateNrTxt;

    @FXML
    private DatePicker nrs097CertificateIssieDateDP;

    @FXML
    private ComboBox<String> networkProvideCmb;

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

    private ProjectPlannerController parent;

    @FXML
    void closeWindow(ActionEvent event) {

    }

    @FXML
    void saveProject(ActionEvent event) {

    }


    public void setMainController(ProjectPlannerController projectPlannerController) {
        parent = projectPlannerController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hasPVPlaneCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            sizeOfPVPlaneSpn.setDisable(!newValue);
            hasBatteryBlankIntegrationCB.setDisable(!(newValue && hasBatteryBlankCB.isSelected()));
            hasPrivateGridIntegrationCB.setDisable(!(newValue && hasPrivateGridCB.isSelected()));
            hasGeneratorIntegrationCB.setDisable(!(newValue && hasGeneratorCB.isSelected()));
        });
        hasPrivateGridCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            sizeOfPrivateGridSpn.setDisable(!newValue);
            hasPrivateGridIntegrationCB.setDisable(!(newValue && hasPVPlaneCB.isSelected()));
        });
        hasGeneratorCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            sizeOfGeneratorSpn.setDisable(!newValue);
            hasGeneratorIntegrationCB.setDisable(!(newValue && hasPVPlaneCB.isSelected()));
            if (newValue) {
                hasPrivateGridCB.setSelected(true);
            }
        });
        hasBatteryBlankCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            sizeOfBatteryBlankSpn.setDisable(!newValue);
            hasBatteryBlankIntegrationCB.setDisable(!(newValue && hasPVPlaneCB.isSelected()));
            if (newValue) {
                hasPrivateGridCB.setSelected(true);
            }
        });

        isOwnerAndContactTheSamePersonCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            contactPersonNameTxt.setDisable(newValue);
            contactPersonSurnameTxt.setDisable(newValue);
            if (newValue){
                contactPersonNameTxt.setText(ownerNameTxt.getText());
                contactPersonSurnameTxt.setText(ownerSurnameTxt.getText());
            }
        });

        SpinnerValueFactory<Double> PVPlaneValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(1.0,1000000000.0,1.0,1000.0);
        sizeOfPVPlaneSpn.setValueFactory(PVPlaneValueFactory);

        SpinnerValueFactory<Double> privateGridValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(1.0,1000000000.0,1.0,1000.0);
        sizeOfPrivateGridSpn.setValueFactory(privateGridValueFactory);

        SpinnerValueFactory<Double> generatorValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(1.0,1000000000.0,1.0,1000.0);
        sizeOfGeneratorSpn.setValueFactory(generatorValueFactory);

        SpinnerValueFactory<Double> batteryBlankValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(1.0,1000000000.0,1.0,1000.0);
        sizeOfBatteryBlankSpn.setValueFactory(batteryBlankValueFactory);

        SpinnerValueFactory<Integer> timeFrameValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,120,1,1);
        timeFrameSpn.setValueFactory(timeFrameValueFactory);

        SpinnerValueFactory<Double> budgetCostValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(1.0,1000000000.0,1.0,1000.0);
        budgetCostSpn.setValueFactory(budgetCostValueFactory);

        SpinnerValueFactory<Double> actualCostValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(1.0,1000000000.0,1.0,1000.0);
        actualCostSpn.setValueFactory(actualCostValueFactory);

        SpinnerValueFactory<Double> sellPriceValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(1.0,1000000000.0,1.0,1000.0);
        sellPriceSpn.setValueFactory(sellPriceValueFactory);

        ownerNameTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isOwnerAndContactTheSamePersonCB.isSelected()){
                contactPersonNameTxt.setText(newValue);
            }
        });
        ownerSurnameTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isOwnerAndContactTheSamePersonCB.isSelected()){
                contactPersonSurnameTxt.setText(newValue);
            }
        });

        phoneNumberTxt.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !Pattern.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$",phoneNumberTxt.getText())){
                phoneNumberTxt.setText("");
            }
        });

        emailTxt.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !Pattern.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$",emailTxt.getText())){
                emailTxt.setText("");
            }
        });

        hasCOCCertificateCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            cocCertificateNrTxt.setDisable(!newValue);
            cocIssieDateDP.setDisable(!newValue);
        });

        hasNRS097CertificateCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            nrs097CertificateNrTxt.setDisable(!newValue);
            nrs097CertificateIssieDateDP.setDisable(!newValue);
        });

        provinceCmb.getItems().addAll("Eastern Cape","Free State","Gauteng","KwaZulu-Natal","Limpopo","Mpumalanga","Northern Cape","North-West","Western Cape");
        provinceCmb.setValue("Eastern Cape");

        networkProvideCmb.getItems().addAll("Eskom","Municipal");
        networkProvideCmb.setValue("Eskom");
    }
}
