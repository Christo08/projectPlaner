package com.project.planer.ui.controllers;

import com.project.planer.backend.controllers.ProjectController;
import com.project.planer.backend.data.AuthorityApproval;
import com.project.planer.backend.data.Client;
import com.project.planer.backend.data.FinancialIndicators;
import com.project.planer.backend.data.Project;
import com.project.planer.ui.util.Hub;
import com.project.planer.ui.util.EventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ProjectCreatorController implements Initializable, EventListener {

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

    private SpinnerValueFactory<Double> sellPriceValueFactory;
    private SpinnerValueFactory<Double> actualCostValueFactory;
    private SpinnerValueFactory<Double> budgetCostValueFactory;
    private SpinnerValueFactory<Integer> timeFrameValueFactory;
    private SpinnerValueFactory<Double> batteryBlankValueFactory;
    private SpinnerValueFactory<Double> generatorValueFactory;
    private SpinnerValueFactory<Double> privateGridValueFactory;
    private SpinnerValueFactory<Double> PVPlaneValueFactory;

    private Alert errorAlert;
    private Alert warmingAlert;
    private TextArea errorTextField;
    private ProjectController projectController;

    @FXML
    void closeWindow(ActionEvent event) {
        resetInputFields();

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void resetInputFields() {
        hasPrivateGridIntegrationCB.setSelected(false);
        hasGeneratorIntegrationCB.setSelected(false);
        hasBatteryBlankIntegrationCB.setSelected(false);
        hasPVPlaneCB.setSelected(false);
        isOwnerAndContactTheSamePersonCB.setSelected(false);
        hasPrivateGridCB.setSelected(false);
        hasGeneratorCB.setDisable(false);
        hasBatteryBlankCB.setSelected(false);
        hasCOCCertificateCB.setSelected(false);
        hasNRS097CertificateCB.setDisable(false);

        startDateOfProjectDP.promptTextProperty().set("");
        cocIssieDateDP.promptTextProperty().set("");
        nrs097CertificateIssieDateDP.promptTextProperty().set("");
        approvalHandedDateDP.promptTextProperty().set("");
        approvalReturnedFormClientDateDP.promptTextProperty().set("");
        paymentedDateDP.promptTextProperty().set("");
        approvalFromNetworkProvideDateDP.promptTextProperty().set("");
        nersaAppilcationDateDP.promptTextProperty().set("");
        nersaApprovalDateDP.promptTextProperty().set("");

        timeFrameValueFactory.setValue(1);

        businessNameTxtf.setText("");
        ownerNameTxt.setText("");
        ownerSurnameTxt.setText("");
        contactPersonNameTxt.setText("");
        contactPersonSurnameTxt.setText("");
        phoneNumberTxt.setText("");
        emailTxt.setText("");
        addressOrGPSCoordinatesTxtA.setText("");
        cocCertificateNrTxt.setText("");
        nrs097CertificateNrTxt.setText("");

        provinceCmb.setValue("Eastern Cape");
        networkProvideCmb.setValue("Eskom");
    }

    @FXML
    void saveProject(ActionEvent event) {
        boolean hasError = false;
        String emptyFieldsText ="The following flied can not be empty:";
        String incorrectNumberFieldsText ="The following flied can has an incorrect value:";

        if (businessNameTxtf.getText().isEmpty()){
            hasError = true;
            businessNameTxtf.setBorder(new Border(new BorderStroke(Color.RED,
                                                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tBusiness name";
        }

        if (ownerNameTxt.getText().isEmpty()){
            hasError = true;
            ownerNameTxt.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tOwner name";
        }

        if (ownerSurnameTxt.getText().isEmpty()){
            hasError = true;
            ownerSurnameTxt.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tOwner surname";
        }

        if (!isOwnerAndContactTheSamePersonCB.isSelected() && contactPersonNameTxt.getText().isEmpty()){
            hasError = true;
            contactPersonNameTxt.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tContact person name";
        }

        if (!isOwnerAndContactTheSamePersonCB.isSelected() && contactPersonSurnameTxt.getText().isEmpty()){
            hasError = true;
            contactPersonSurnameTxt.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tContact person surname";
        }

        if ( phoneNumberTxt.getText().isEmpty()){
            hasError = true;
            phoneNumberTxt.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tPhone number";
        }

        if (emailTxt.getText().isEmpty()){
            hasError = true;
            emailTxt.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tEmail";
        }

        if (addressOrGPSCoordinatesTxtA.getText().isEmpty()){
            hasError = true;
            addressOrGPSCoordinatesTxtA.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tAddress or GPS coordinates";
        }

        if (provinceCmb.getValue().isEmpty()){
            hasError = true;
            provinceCmb.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tProvince";
        }

        if (budgetCostSpn.getValue() <=0){
            hasError = true;
            budgetCostSpn.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            incorrectNumberFieldsText +="\n\tBudget Cost";

        }

        if (sellPriceSpn.getValue() <=0){
            hasError = true;
            sellPriceSpn.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            incorrectNumberFieldsText +="\n\tSell Price";
        }

        if (networkProvideCmb.getValue().isEmpty()){
            hasError = true;
            networkProvideCmb.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tNetwork provider";
        }

        if (hasCOCCertificateCB.isSelected() && cocIssieDateDP.getValue() == null){
            cocIssieDateDP.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tCOC issie date";
        }

        if (hasCOCCertificateCB.isSelected() && cocCertificateNrTxt.getText().isEmpty()){
            cocCertificateNrTxt.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tCOC certificate number";
        }

        if (hasNRS097CertificateCB.isSelected() && nrs097CertificateIssieDateDP.getValue() == null ){
            nrs097CertificateIssieDateDP.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tNRS 097 issie date";
        }

        if (hasNRS097CertificateCB.isSelected() && nrs097CertificateNrTxt.getText().isEmpty()){
            nrs097CertificateNrTxt.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tNRS 097 certificate number";
        }

        if (hasPVPlaneCB.isSelected() && sizeOfPVPlaneSpn.getValue() ==0){
            hasError = true;
            sizeOfPVPlaneSpn.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            incorrectNumberFieldsText +="\n\tSize of PV plane";
        }

        if (hasPrivateGridCB.isSelected() && sizeOfPrivateGridSpn.getValue() ==0){
            hasError = true;
            sizeOfPrivateGridSpn.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            incorrectNumberFieldsText +="\n\tSize of private grid";
        }

        if (hasGeneratorCB.isSelected() && sizeOfGeneratorSpn.getValue() ==0){
            hasError = true;
            sizeOfGeneratorSpn.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            incorrectNumberFieldsText +="\n\tSize of generator";
        }

        if (hasBatteryBlankCB.isSelected() && sizeOfBatteryBlankSpn.getValue() ==0){
            hasError = true;
            sizeOfBatteryBlankSpn.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            incorrectNumberFieldsText +="\n\tSize of battery blank";
        }

        if (startDateOfProjectDP.getValue() == null){
            startDateOfProjectDP.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            emptyFieldsText +="\n\tStart date";
        }

        if (!hasError){
            Client client = new Client(businessNameTxtf.getText(),
                                       ownerNameTxt.getText(),
                                       ownerSurnameTxt.getText(),
                                       contactPersonNameTxt.getText(),
                                       contactPersonSurnameTxt.getText(),
                                       phoneNumberTxt.getText(),
                                       emailTxt.getText(),
                                       addressOrGPSCoordinatesTxtA.getText());
            FinancialIndicators financialIndicators = new FinancialIndicators(budgetCostSpn.getValue(),
                                                                              actualCostSpn.getValue(),
                                                                              sellPriceSpn.getValue());
            AuthorityApproval authorityApproval = new AuthorityApproval(cocIssieDateDP.getValue(),
                                                                        cocCertificateNrTxt.getText(),
                                                                        nrs097CertificateIssieDateDP.getValue(),
                                                                        nrs097CertificateNrTxt.getText(),
                                                                        networkProvideCmb.getValue(),
                                                                        approvalHandedDateDP.getValue(),
                                                                        approvalReturnedFormClientDateDP.getValue(),
                                                                        paymentedDateDP.getValue(),
                                                                        approvalFromNetworkProvideDateDP.getValue(),
                                                                        nersaAppilcationDateDP.getValue(),
                                                                        nersaApprovalDateDP.getValue());
            String id = projectController.createProject(client, financialIndicators, authorityApproval,
                                                            hasPVPlaneCB.isSelected(),sizeOfPVPlaneSpn.getValue(), hasPrivateGridCB.isSelected(),
                                                            sizeOfPrivateGridSpn.getValue(), hasGeneratorCB.isSelected(), sizeOfGeneratorSpn.getValue(),
                                                            hasBatteryBlankCB.isSelected(), sizeOfBatteryBlankSpn.getValue(), hasPrivateGridIntegrationCB.isSelected(),
                                                            hasGeneratorIntegrationCB.isSelected(), hasBatteryBlankIntegrationCB.isSelected(),startDateOfProjectDP.getValue(),
                                                            timeFrameSpn.getValue());
            Hub.getInstance().addProject(id);
            resetInputFields();

            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
        else {
            String errorText ="";
            if (emptyFieldsText.contains("\n\t")){
                errorText += emptyFieldsText;
            }
            if (incorrectNumberFieldsText.contains("\n\t")){
                if (errorText.contains("\n\t")){
                    errorText+="\n";
                }
                errorText += incorrectNumberFieldsText;
            }
            errorTextField.clear();
            errorTextField.setText(errorText);
            errorAlert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Fields");
        errorAlert.setHeaderText("Error input");
        errorTextField = new TextArea();
        errorAlert.getDialogPane().setContent(errorTextField);

        warmingAlert = new Alert(Alert.AlertType.WARNING);
        warmingAlert.setTitle("Fields");
        warmingAlert.setHeaderText("Are you sure the time frame is 1 month?");
        warmingAlert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

        StringConverter<Double> randConverter = new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                return String.format("%,.2f", object);
            }

            @Override
            public Double fromString(String string) {
                return stringToDouble(string);
            }
        };

        StringConverter<Double> doubleConverter = new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                return String.format("%,.4f", object);
            }

            @Override
            public Double fromString(String string) {
                return stringToDouble(string);
            }
        };

        PVPlaneValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,Double.MAX_VALUE,0,1000.0);
        PVPlaneValueFactory.setConverter(doubleConverter);
        sizeOfPVPlaneSpn.setValueFactory(PVPlaneValueFactory);
        sizeOfPVPlaneSpn.setEditable(true);

        privateGridValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,Double.MAX_VALUE,0,1000.0);
        privateGridValueFactory.setConverter(doubleConverter);
        sizeOfPrivateGridSpn.setValueFactory(privateGridValueFactory);
        sizeOfPrivateGridSpn.setEditable(true);

        generatorValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,Double.MAX_VALUE,0,1000.0);
        generatorValueFactory.setConverter(doubleConverter);
        sizeOfGeneratorSpn.setValueFactory(generatorValueFactory);
        sizeOfGeneratorSpn.setEditable(true);

        batteryBlankValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,Double.MAX_VALUE,0,1000.0);
        batteryBlankValueFactory.setConverter(doubleConverter);
        sizeOfBatteryBlankSpn.setValueFactory(batteryBlankValueFactory);
        sizeOfBatteryBlankSpn.setEditable(true);

        timeFrameValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,120,0,1);
        timeFrameSpn.setValueFactory(timeFrameValueFactory);
        timeFrameSpn.setEditable(true);

        budgetCostValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,Double.MAX_VALUE,0,1000.0);
        budgetCostValueFactory.setConverter(randConverter);
        budgetCostSpn.setValueFactory(budgetCostValueFactory);
        budgetCostSpn.setEditable(true);

        actualCostValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,Double.MAX_VALUE,0,1000.0);
        actualCostValueFactory.setConverter(randConverter);
        actualCostSpn.setValueFactory(actualCostValueFactory);
        actualCostSpn.setEditable(true);

        sellPriceValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,Double.MAX_VALUE,0,1000.0);
        sellPriceValueFactory.setConverter(randConverter);
        sellPriceSpn.setValueFactory(sellPriceValueFactory);
        sellPriceSpn.setEditable(true);

        resetInputFields();

        hasPVPlaneCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            sizeOfPVPlaneSpn.setDisable(!newValue);
            hasBatteryBlankIntegrationCB.setDisable(!(newValue && hasBatteryBlankCB.isSelected()));
            hasPrivateGridIntegrationCB.setDisable(!(newValue && hasPrivateGridCB.isSelected()));
            hasGeneratorIntegrationCB.setDisable(!(newValue && hasGeneratorCB.isSelected()));
            if (!newValue){
                sizeOfPVPlaneSpn.getValueFactory().setValue(0.0);
                hasBatteryBlankIntegrationCB.setSelected(false);
                hasPrivateGridIntegrationCB.setSelected(false);
                hasGeneratorIntegrationCB.setSelected(false);
                sizeOfPVPlaneSpn.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        hasPrivateGridCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            sizeOfPrivateGridSpn.setDisable(!newValue);
            hasPrivateGridIntegrationCB.setDisable(!(newValue && hasPVPlaneCB.isSelected()));
            if (!newValue){
                hasPrivateGridIntegrationCB.setSelected(false);
                hasGeneratorCB.setSelected(false);
                hasBatteryBlankCB.setSelected(false);
                sizeOfPrivateGridSpn.getValueFactory().setValue(0.0);
                sizeOfPrivateGridSpn.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        hasGeneratorCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            sizeOfGeneratorSpn.setDisable(!newValue);
            hasGeneratorIntegrationCB.setDisable(!(newValue && hasPVPlaneCB.isSelected()));
            if (newValue) {
                hasPrivateGridCB.setSelected(true);
            }
            else
            {
                hasGeneratorIntegrationCB.setSelected(false);
                sizeOfGeneratorSpn.getValueFactory().setValue(0.0);
                sizeOfGeneratorSpn.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        hasBatteryBlankCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            sizeOfBatteryBlankSpn.setDisable(!newValue);
            hasBatteryBlankIntegrationCB.setDisable(!(newValue && hasPVPlaneCB.isSelected()));
            if (newValue) {
                hasPrivateGridCB.setSelected(true);
            }
            else
            {
                hasBatteryBlankIntegrationCB.setSelected(false);
                sizeOfBatteryBlankSpn.getValueFactory().setValue(0.0);
                sizeOfBatteryBlankSpn.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });

        isOwnerAndContactTheSamePersonCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            contactPersonNameTxt.setDisable(newValue);
            contactPersonSurnameTxt.setDisable(newValue);
            if (newValue){
                contactPersonNameTxt.setText(ownerNameTxt.getText());
                contactPersonSurnameTxt.setText(ownerSurnameTxt.getText());
                contactPersonNameTxt.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                contactPersonSurnameTxt.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
            else
            {
                contactPersonNameTxt.setText("");
                contactPersonSurnameTxt.setText("");
            }
        });

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
            if (!newValue && !Pattern.matches("[A-Za-z0-9._%+-]+@.[A-Za-z0-9.-]+[.][A-Za-z]{1,3}$",emailTxt.getText())){
                emailTxt.setText("");
            }
        });

        hasCOCCertificateCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            cocCertificateNrTxt.setDisable(!newValue);
            cocIssieDateDP.setDisable(!newValue);
            if (!newValue){
                cocIssieDateDP.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                cocCertificateNrTxt.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        hasNRS097CertificateCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            nrs097CertificateNrTxt.setDisable(!newValue);
            nrs097CertificateIssieDateDP.setDisable(!newValue);
            if (!newValue){
                nrs097CertificateIssieDateDP.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                nrs097CertificateNrTxt.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });

        provinceCmb.getItems().addAll("Eastern Cape","Free State","Gauteng","KwaZulu-Natal","Limpopo","Mpumalanga","Northern Cape","North-West","Western Cape");
        provinceCmb.setValue("");

        networkProvideCmb.getItems().addAll("Eskom","Municipal");
        networkProvideCmb.setValue("");

        businessNameTxtf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !businessNameTxtf.getText().isEmpty()){
                businessNameTxtf.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        ownerNameTxt.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !ownerNameTxt.getText().isEmpty()){
                ownerNameTxt.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        ownerSurnameTxt.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !ownerSurnameTxt.getText().isEmpty()){
                ownerSurnameTxt.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        contactPersonNameTxt.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !contactPersonNameTxt.getText().isEmpty()){
                contactPersonNameTxt.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        contactPersonSurnameTxt.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !contactPersonSurnameTxt.getText().isEmpty()){
                contactPersonSurnameTxt.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        phoneNumberTxt.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !phoneNumberTxt.getText().isEmpty()){
                phoneNumberTxt.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        emailTxt.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !emailTxt.getText().isEmpty()){
                emailTxt.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        addressOrGPSCoordinatesTxtA.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue){
                provinceCmb.getItems().stream().forEach(item -> {
                    if (addressOrGPSCoordinatesTxtA.getText().toUpperCase().contains(item.toUpperCase())){
                        provinceCmb.setValue(item);
                    }
                });
                if ( !addressOrGPSCoordinatesTxtA.getText().isEmpty()){
                    addressOrGPSCoordinatesTxtA.setBorder(new Border(new BorderStroke(Color.RED,
                            BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                }
            }
        });
        provinceCmb.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !provinceCmb.getValue().isEmpty()){
                provinceCmb.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });

        sellPriceSpn.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !(sellPriceSpn.getValue()<=0)){
                sellPriceSpn.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }

        });
        budgetCostSpn.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !(budgetCostSpn.getValue()<=0)){
                budgetCostSpn.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }

        });

        networkProvideCmb.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !networkProvideCmb.getValue().isEmpty()){
                networkProvideCmb.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        cocIssieDateDP.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && cocIssieDateDP.getValue() != null){
                cocIssieDateDP.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        cocCertificateNrTxt.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !cocCertificateNrTxt.getText().isEmpty()){
                cocCertificateNrTxt.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        nrs097CertificateIssieDateDP.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && nrs097CertificateIssieDateDP.getValue() != null){
                nrs097CertificateIssieDateDP.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        nrs097CertificateNrTxt.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !nrs097CertificateNrTxt.getText().isEmpty()){
                nrs097CertificateNrTxt.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });

        sizeOfPVPlaneSpn.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && hasPVPlaneCB.isSelected() && sizeOfPVPlaneSpn.getValue() !=0){
                sizeOfPVPlaneSpn.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        sizeOfPrivateGridSpn.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && hasPrivateGridCB.isSelected() && sizeOfPrivateGridSpn.getValue() !=0){
                sizeOfPrivateGridSpn.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        sizeOfBatteryBlankSpn.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && hasBatteryBlankCB.isSelected() && sizeOfBatteryBlankSpn.getValue() !=0){
                sizeOfBatteryBlankSpn.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        sizeOfGeneratorSpn.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && hasGeneratorCB.isSelected() && sizeOfGeneratorSpn.getValue() !=0){
                sizeOfGeneratorSpn.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        startDateOfProjectDP.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && startDateOfProjectDP.getValue() !=null){
                startDateOfProjectDP.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });
        timeFrameSpn.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && timeFrameSpn.getValue() >1){
                timeFrameSpn.setBorder(new Border(new BorderStroke(Color.RED,
                        BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        });

        Hub.getInstance().addListeners(this);
    }

    private Double stringToDouble(String number) {
        return Double.valueOf(number.replace(",",""));
    }

    @Override
    public void reload() {

    }

    @Override
    public void deleteProject(Project project) {

    }

    @Override
    public void setProjectController(ProjectController projectController) {
        this.projectController = projectController;
    }

    @Override
    public void selectProject(Project project) {

    }

    @Override
    public void addProject(String projectID) {

    }
}
