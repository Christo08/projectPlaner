package com.project.planer.ui.util;

import com.project.planer.backend.data.Project;

import java.time.format.DateTimeFormatter;

public class TableRowObject {
    private String id;
    private String businessName;
    private String contactPerson;
    private String phoneNR;
    private String pvPlaneSize;
    private String privateGridSize;
    private String generatorSize;
    private String batteryBlankSize;
    private String integration;
    private String startDate;
    private String endDate;
    private String budgetCost;
    private String actualCost;
    private String sellPrice;
    private String projectedProfit;
    private String actualProfit;
    private String provide;

    private Project project;

    public TableRowObject(Project project) {
        this.project = project;
        id = project.getId();

        businessName = project.getClient().getBusinessName();
        contactPerson = project.getClient().getContactPersonsName() + " " +project.getClient().getContactPersonsSurname();
        phoneNR = project.getClient().getContactPhoneNumber();

        pvPlaneSize = (project.getPvPlaneSize() > 0) ? String.format("%,.4f", project.getPvPlaneSize()) : "N/S";
        privateGridSize = (project.getPrivateGridSize() > 0) ? String.format("%,.4f", project.getPrivateGridSize()) : "N/S";
        generatorSize = (project.getGeneratorSize() > 0) ? String.format("%,.4f", project.getGeneratorSize()) : "N/S";
        batteryBlankSize = (project.getBatteryBlankSize() > 0) ? String.format("%,.4f", project.getBatteryBlankSize()) : "N/S";
        integration ="";
        if (project.isNeedsIntegrationOfPVPlaneAndPrivateGrid()){
            integration += "Private Grid";
        }
        if (project.isNeedsIntegrationOfPVPlaneAndGenerator()){
            if (!integration.isEmpty())
                integration += ", ";
            integration += "Generator";
        }
        if (project.isNeedsIntegrationOfPVPlaneAndBatteryBlank()){
            if (!integration.isEmpty())
                integration += ", ";
            integration += "Battery Blank";
        }
        if (integration.isEmpty())
            integration = "N/A";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        startDate = project.getStartDateAsLocalDate().format(formatter);
        endDate = project.getEstimatedStopDateAsLocalDate().format(formatter);

        budgetCost = String.format("%,.2f", project.getFinancialIndicators().getBudgetCost());
        actualCost = String.format("%,.2f", project.getFinancialIndicators().getActualCost());
        sellPrice = String.format("%,.2f", project.getFinancialIndicators().getSellPrice());
        projectedProfit = String.format("%,.2f", project.getFinancialIndicators().getProjectedProfit());
        actualProfit = String.format("%,.2f", project.getFinancialIndicators().getActualProfit());

        provide = project.getAuthorityApproval().getNetworkProvide();
    }

    public String getID(){
        return id;
    }

    public Project getProject() {
        return project;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhoneNR() {
        return phoneNR;
    }

    public void setPhoneNR(String phoneNR) {
        this.phoneNR = phoneNR;
    }

    public String getPvPlaneSize() {
        return pvPlaneSize;
    }

    public void setPvPlaneSize(String pvPlaneSize) {
        this.pvPlaneSize = pvPlaneSize;
    }

    public String getPrivateGridSize() {
        return privateGridSize;
    }

    public void setPrivateGridSize(String privateGridSize) {
        this.privateGridSize = privateGridSize;
    }

    public String getGeneratorSize() {
        return generatorSize;
    }

    public void setGeneratorSize(String generatorSize) {
        this.generatorSize = generatorSize;
    }

    public String getBatteryBlankSize() {
        return batteryBlankSize;
    }

    public void setBatteryBlankSize(String batteryBlankSize) {
        this.batteryBlankSize = batteryBlankSize;
    }

    public String getIntegration() {
        return integration;
    }

    public void setIntegration(String integration) {
        this.integration = integration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBudgetCost() {
        return budgetCost;
    }

    public void setBudgetCost(String budgetCost) {
        this.budgetCost = budgetCost;
    }

    public String getActualCost() {
        return actualCost;
    }

    public void setActualCost(String actualCost) {
        this.actualCost = actualCost;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getProjectedProfit() {
        return projectedProfit;
    }

    public void setProjectedProfit(String projectedProfit) {
        this.projectedProfit = projectedProfit;
    }

    public String getActualProfit() {
        return actualProfit;
    }

    public void setActualProfit(String actualProfit) {
        this.actualProfit = actualProfit;
    }

    public String getProvide() {
        return provide;
    }

    public void setProvide(String provide) {
        this.provide = provide;
    }
}
