package com.project.planer.ui.controllers;

import com.project.planer.backend.controllers.ProjectController;
import com.project.planer.backend.data.Project;
import com.project.planer.ui.util.EventListener;
import com.project.planer.ui.util.Hub;
import com.project.planer.ui.util.TableRowObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProjectTableController implements Initializable, EventListener {

    @FXML
    private ScrollPane tableViewSP;

    @FXML
    private TableView<TableRowObject> tableTV;

    @FXML
    private TableColumn<TableRowObject, String> businessNameCol;

    @FXML
    private TableColumn<TableRowObject, String> contantPersonCol;

    @FXML
    private TableColumn<TableRowObject, String> phoneNRCol;

    @FXML
    private TableColumn<TableRowObject, String> pvPlaneSizeCol;

    @FXML
    private TableColumn<TableRowObject, String> privateGridSizeCol;

    @FXML
    private TableColumn<TableRowObject, String> generatorSizeCol;

    @FXML
    private TableColumn<TableRowObject, String> batteryBlankSizeCol;

    @FXML
    private TableColumn<TableRowObject, String> intergrationCol;

    @FXML
    private TableColumn<TableRowObject, String> startDateCol;

    @FXML
    private TableColumn<TableRowObject, String> endDateCol;

    @FXML
    private TableColumn<TableRowObject, String> budgetCostCol;

    @FXML
    private TableColumn<TableRowObject, String> actualCostCol;

    @FXML
    private TableColumn<TableRowObject, String> sellPriceCol;

    @FXML
    private TableColumn<TableRowObject, String> projectedProfitCol;

    @FXML
    private TableColumn<TableRowObject, String> actualProfitCol;

    @FXML
    private TableColumn<TableRowObject, String> provideCol;

    private ProjectController projectController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableTV.prefWidthProperty().bind(tableViewSP.prefWidthProperty());
        tableTV.prefHeightProperty().bind(tableViewSP.prefHeightProperty());

        businessNameCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.06));
        businessNameCol.setCellValueFactory(new PropertyValueFactory<>("businessName"));
        contantPersonCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.06));
        contantPersonCol.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        phoneNRCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.05));
        phoneNRCol.setCellValueFactory(new PropertyValueFactory<>("phoneNR"));

        pvPlaneSizeCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.06));
        pvPlaneSizeCol.setCellValueFactory(new PropertyValueFactory<>("pvPlaneSize"));
        privateGridSizeCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.06));
        privateGridSizeCol.setCellValueFactory(new PropertyValueFactory<>("privateGridSize"));
        generatorSizeCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.06));
        generatorSizeCol.setCellValueFactory(new PropertyValueFactory<>("generatorSize"));
        batteryBlankSizeCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.07));
        batteryBlankSizeCol.setCellValueFactory(new PropertyValueFactory<>("batteryBlankSize"));
        intergrationCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.12));
        intergrationCol.setCellValueFactory(new PropertyValueFactory<>("integration"));
        startDateCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.05));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.05));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        budgetCostCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.06));
        budgetCostCol.setCellValueFactory(new PropertyValueFactory<>("budgetCost"));
        actualCostCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.06));
        actualCostCol.setCellValueFactory(new PropertyValueFactory<>("actualCost"));
        sellPriceCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.06));
        sellPriceCol.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        projectedProfitCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.07));
        projectedProfitCol.setCellValueFactory(new PropertyValueFactory<>("projectedProfit"));
        actualProfitCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.06));
        actualProfitCol.setCellValueFactory(new PropertyValueFactory<>("actualProfit"));

        provideCol.prefWidthProperty().bind(tableTV.prefWidthProperty().multiply(0.04));
        provideCol.setCellValueFactory(new PropertyValueFactory<>("provide"));

        tableTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableTV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null)
                Hub.getInstance().selectProject(null);
            else
                Hub.getInstance().selectProject(newValue.getProject());
        });

        Hub.getInstance().addListeners(this);
    }

    @Override
    public void reload() {
        List<TableRowObject> tableRows = projectController.getProjects().stream().map(project -> new TableRowObject(project)).collect(Collectors.toList());
        tableTV.getItems().addAll(tableRows);
    }

    @Override
    public void deleteProject(Project project) {
        tableTV.getItems().removeIf(item ->item.getID().equals(project.getId()));
        tableTV.getSelectionModel().select(null);
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
        tableTV.getItems().add(new TableRowObject(projectController.getProject(projectID)));
    }
}
