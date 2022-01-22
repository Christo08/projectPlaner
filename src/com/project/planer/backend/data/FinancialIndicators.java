package com.project.planer.backend.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "financialIndicators")
@XmlAccessorType(XmlAccessType.FIELD)
public class FinancialIndicators {
    private double budgetCost;
    private double actualCost;

    private double sellPrice;

    private double projectedProfit;
    private double actualProfit;

    public FinancialIndicators() {
    }

    public FinancialIndicators(double budgetCost, double sellPrice) {
        this.budgetCost = budgetCost;
        this.sellPrice = sellPrice;
        this.projectedProfit = sellPrice - budgetCost;
    }

    public FinancialIndicators(double budgetCost, double actualCost, double sellPrice) {
        this.budgetCost = budgetCost;
        this.actualCost = actualCost;
        this.sellPrice = sellPrice;
        this.projectedProfit = sellPrice - budgetCost;
        this.actualProfit = sellPrice - actualCost;
    }

    public double getBudgetCost() {
        return budgetCost;
    }

    public void setBudgetCost(double budgetCost) {
        this.budgetCost = budgetCost;
    }

    public double getActualCost() {
        return actualCost;
    }

    public void setActualCost(double actualCost) {
        this.actualCost = actualCost;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getProjectedProfit() {
        return projectedProfit;
    }

    public void setProjectedProfit(double projectedProfit) {
        this.projectedProfit = projectedProfit;
    }

    public double getActualProfit() {
        return actualProfit;
    }

    public void setActualProfit(double actualProfit) {
        this.actualProfit = actualProfit;
    }

}
