package com.project.planer.backend.data;

import com.project.planer.backend.controllers.ProjectController;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class Project{
    @XmlTransient
    private String id ="";

    private boolean hasAPVPlane = false;
    private double pvPlaneSize = 0;

    private boolean hasAPrivateGrid = false;
    private double privateGridSize = 0;

    private boolean hasAGenerator = false;
    private double generatorSize = 0;

    private boolean hasABatteryBlank = false;
    private double batteryBlankSize = 0;

    private boolean needsIntegrationOfPVPlaneAndPrivateGrid = false;
    private boolean needsIntegrationOfPVPlaneAndGenerator = false;
    private boolean needsIntegrationOfPVPlaneAndBatteryBlank = false;

    private long startDate = 0;
    private long estimatedStopDate = 0;
    private int timeFrame = 0;

    private Client client;
    private FinancialIndicators financialIndicators;
    private AuthorityApproval authorityApproval;
    private List<Status> statuses;

    public Project(){

    }

    public Project(long startDate, int timeFrame, Client client,
                   FinancialIndicators financialIndicators, AuthorityApproval authorityApproval) {
        this.startDate = startDate;
        this.timeFrame = timeFrame;
        this.estimatedStopDate = this.startDate+(timeFrame *2628000000L);
        this.client = client;
        this.financialIndicators = financialIndicators;
        this.authorityApproval = authorityApproval;
        id = createID();
    }

    public Project(LocalDate startDate, int timeFrame, Client client,
                   FinancialIndicators financialIndicators, AuthorityApproval authorityApproval) {
        this(ProjectController.localDateToTimestamp(startDate),timeFrame,client,financialIndicators,authorityApproval);
    }

    public Project(boolean hasAPVPlane, double pvPlaneSize, boolean hasAPrivateGrid,
                   double privateGridSize, boolean hasAGenerator, double generatorSize,
                   boolean hasABatteryBlank, double batteryBlankSize, boolean needsIntegrationOfPVPlaneAndPrivateGrid,
                   boolean needsIntegrationOfPVPlaneAndGenerator, boolean needsIntegrationOfPVPlaneAndBatteryBlank, long startDate,
                   int timeFrame, Client client, FinancialIndicators financialIndicators, AuthorityApproval authorityApproval) {
        this(startDate, timeFrame, client, financialIndicators, authorityApproval);
        this.hasAPVPlane = hasAPVPlane;
        if (hasAPVPlane){
            this.pvPlaneSize = pvPlaneSize;
        } else{
            this.pvPlaneSize = -1;
        }
        this.hasAPrivateGrid = hasAPrivateGrid;
        if (hasAPrivateGrid){
            this.privateGridSize = privateGridSize;
        } else{
            this.privateGridSize = -1;
        }
        this.hasAGenerator = hasAGenerator;
        if (hasAGenerator){
            this.generatorSize = generatorSize;
        } else{
            this.generatorSize = -1;
        }
        this.hasABatteryBlank = hasABatteryBlank;
        if (hasABatteryBlank){
            this.batteryBlankSize = batteryBlankSize;
        } else{
            this.batteryBlankSize = -1;
        }
        this.needsIntegrationOfPVPlaneAndPrivateGrid = needsIntegrationOfPVPlaneAndPrivateGrid;
        this.needsIntegrationOfPVPlaneAndGenerator = needsIntegrationOfPVPlaneAndGenerator;
        this.needsIntegrationOfPVPlaneAndBatteryBlank = needsIntegrationOfPVPlaneAndBatteryBlank;

    }

    public Project(boolean hasAPVPlane, double pvPlaneSize, boolean hasAPrivateGrid,
                   double privateGridSize, boolean hasAGenerator, double generatorSize,
                   boolean hasABatteryBlank, double batteryBlankSize, boolean needsIntegrationOfPVPlaneAndPrivateGrid,
                   boolean needsIntegrationOfPVPlaneAndGenerator, boolean needsIntegrationOfPVPlaneAndBatteryBlank, LocalDate startDate,
                   int timeFrame, Client client, FinancialIndicators financialIndicators, AuthorityApproval authorityApproval) {
        this(hasAPVPlane,pvPlaneSize,hasAPrivateGrid,privateGridSize,hasAGenerator,
                generatorSize,hasABatteryBlank,batteryBlankSize, needsIntegrationOfPVPlaneAndPrivateGrid,needsIntegrationOfPVPlaneAndGenerator,needsIntegrationOfPVPlaneAndBatteryBlank,
                ProjectController.localDateToTimestamp(startDate), timeFrame,client,financialIndicators,authorityApproval);

    }

    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public boolean isHasAPVPlane() {
        return hasAPVPlane;
    }

    public void setHasAPVPlane(boolean hasAPVPlane) {
        this.hasAPVPlane = hasAPVPlane;
    }

    public double getPvPlaneSize() {
        return pvPlaneSize;
    }

    public void setPvPlaneSize(double pvPlaneSize) {
        this.pvPlaneSize = pvPlaneSize;
    }

    public boolean isHasAPrivateGrid() {
        return hasAPrivateGrid;
    }

    public void setHasAPrivateGrid(boolean hasAPrivateGrid) {
        this.hasAPrivateGrid = hasAPrivateGrid;
    }

    public double getPrivateGridSize() {
        return privateGridSize;
    }

    public void setPrivateGridSize(double privateGridSize) {
        this.privateGridSize = privateGridSize;
    }

    public boolean isHasAGenerator() {
        return hasAGenerator;
    }

    public void setHasAGenerator(boolean hasAGenerator) {
        this.hasAGenerator = hasAGenerator;
    }

    public double getGeneratorSize() {
        return generatorSize;
    }

    public void setGeneratorSize(double generatorSize) {
        this.generatorSize = generatorSize;
    }

    public boolean isHasABatteryBlank() {
        return hasABatteryBlank;
    }

    public void setHasABatteryBlank(boolean hasABatteryBlank) {
        this.hasABatteryBlank = hasABatteryBlank;
    }

    public double getBatteryBlankSize() {
        return batteryBlankSize;
    }

    public void setBatteryBlankSize(double batteryBlankSize) {
        this.batteryBlankSize = batteryBlankSize;
    }

    public boolean isNeedsIntegrationOfPVPlaneAndPrivateGrid() {
        return needsIntegrationOfPVPlaneAndPrivateGrid;
    }

    public void setNeedsIntegrationOfPVPlaneAndPrivateGrid(boolean needsIntegrationOfPVPlaneAndPrivateGrid) {
        this.needsIntegrationOfPVPlaneAndPrivateGrid = needsIntegrationOfPVPlaneAndPrivateGrid;
    }

    public boolean isNeedsIntegrationOfPVPlaneAndGenerator() {
        return needsIntegrationOfPVPlaneAndGenerator;
    }

    public void setNeedsIntegrationOfPVPlaneAndGenerator(boolean needsIntegrationOfPVPlaneAndGenerator) {
        this.needsIntegrationOfPVPlaneAndGenerator = needsIntegrationOfPVPlaneAndGenerator;
    }

    public boolean isNeedsIntegrationOfPVPlaneAndBatteryBlank() {
        return needsIntegrationOfPVPlaneAndBatteryBlank;
    }

    public void setNeedsIntegrationOfPVPlaneAndBatteryBlank(boolean needsIntegrationOfPVPlaneAndBatteryBlank) {
        this.needsIntegrationOfPVPlaneAndBatteryBlank = needsIntegrationOfPVPlaneAndBatteryBlank;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
        this.estimatedStopDate = this.startDate+(timeFrame *2628000000L);
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = ProjectController.localDateToTimestamp(startDate);
        this.estimatedStopDate = this.startDate+(timeFrame *2628000000L);
    }

    public LocalDate getEstimatedStopDateAsLocalDate() {
        return ProjectController.timestampToLocalDate(estimatedStopDate);
    }

    public long getEstimatedStopDate() {
        return estimatedStopDate;
    }

    public void setEstimatedStopDate(long estimatedStopDate) {
        this.estimatedStopDate = estimatedStopDate;
    }
    public LocalDate getStartDateAsLocalDate() {
        return ProjectController.timestampToLocalDate(startDate);
    }

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
        this.estimatedStopDate = this.startDate+(timeFrame *2628000000L);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public FinancialIndicators getFinancialIndicators() {
        return financialIndicators;
    }

    public void setFinancialIndicators(FinancialIndicators financialIndicators) {
        this.financialIndicators = financialIndicators;
    }

    public AuthorityApproval getAuthorityApproval() {
        return authorityApproval;
    }

    public void setAuthorityApproval(AuthorityApproval authorityApproval) {
        this.authorityApproval = authorityApproval;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public void addStatus(Status status){
        if (statuses == null){
            statuses = new ArrayList<>();
        }
        statuses.add(status);
    }

    public Status getCurrentStatus(LocalDate currentDate) {
        return statuses.stream()
                .filter(status -> (status.getStopDateAsLocalDate().isBefore(currentDate) && status.getStartDateAsLocalDate().isAfter(currentDate)) ||
                                   status.getStopDateAsLocalDate().isEqual(currentDate) ||
                                   status.getStartDateAsLocalDate().isEqual(currentDate))
                .findFirst()
                .get();
    }

    public String createID(){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger number = new BigInteger(1, md.digest(LocalDateTime.now().toString().getBytes(StandardCharsets.UTF_8)));

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}
