package com.project.planer.backend.data;

import com.project.planer.backend.controllers.ProjectController;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.*;

@XmlRootElement(name = "authorityApproval")
@XmlAccessorType(XmlAccessType.FIELD)

public class AuthorityApproval {
    private long cocIssieDate;
    private String cocCertificateNumber;

    private long nrs097IssieDate;
    private String nrs097CertificateNumber;

    private String networkProvide;

    private long dateOfApplicationHandedIn;
    private long dateOfApplicationReturned;

    private long dateOfPaymentReceived;
    private long expectedDateOfNetworkProvideApproval;
    private long actualDateOfNetworkProvideApproval;

    private long dateOfNERSAApplication;
    private long expectedDateOfNERSAApproval;
    private long actualDateOfNERSAApproval;

    public AuthorityApproval() {
    }

    public AuthorityApproval(String networkProvide) {
        this.networkProvide = networkProvide;
    }

    public AuthorityApproval(long cocIssieDate, String cocCertificateNumber, long nrs097IssieDate,
                             String nrs097CertificateNumber, String networkProvide, long dateOfApplicationHandedIn,
                             long dateOfApplicationReturned, long dateOfPaymentReceived, long actualDateOfNetworkProvideApproval,
                             long dateOfNERSAApplication, long actualDateOfNERSAApproval) {
        this(networkProvide);
        this.cocIssieDate =  cocIssieDate;
        this.cocCertificateNumber = cocCertificateNumber;
        this.nrs097IssieDate =  nrs097IssieDate;
        this.nrs097CertificateNumber = nrs097CertificateNumber;
        this.dateOfApplicationHandedIn =  dateOfApplicationHandedIn;
        this.dateOfApplicationReturned =  dateOfApplicationReturned;
        this.dateOfPaymentReceived =  dateOfPaymentReceived;
        this.expectedDateOfNetworkProvideApproval =  dateOfPaymentReceived+(120*86400000);
        this.actualDateOfNetworkProvideApproval =  actualDateOfNetworkProvideApproval;
        this.dateOfNERSAApplication =  dateOfNERSAApplication;
        this.expectedDateOfNERSAApproval =  dateOfNERSAApplication+(60*86400000);
        this.actualDateOfNERSAApproval =  actualDateOfNERSAApproval;
    }
    public AuthorityApproval(LocalDate cocIssieDate, String cocCertificateNumber, LocalDate nrs097IssieDate,
                             String nrs097CertificateNumber, String networkProvide, LocalDate dateOfApplicationHandedIn,
                             LocalDate dateOfApplicationReturned, LocalDate dateOfPaymentReceived, LocalDate actualDateOfNetworkProvideApproval,
                             LocalDate dateOfNERSAApplication, LocalDate actualDateOfNERSAApproval) {
        this(ProjectController.localDateToTimestamp(cocIssieDate), cocCertificateNumber, ProjectController.localDateToTimestamp(nrs097IssieDate),
             nrs097CertificateNumber, networkProvide, ProjectController.localDateToTimestamp(dateOfApplicationHandedIn),
             ProjectController.localDateToTimestamp(dateOfApplicationReturned), ProjectController.localDateToTimestamp(dateOfPaymentReceived), ProjectController.localDateToTimestamp(actualDateOfNetworkProvideApproval),
             ProjectController.localDateToTimestamp(dateOfNERSAApplication), ProjectController.localDateToTimestamp(actualDateOfNERSAApproval));
    }


    public long getCocIssieDate() {
        return cocIssieDate;
    }

    public LocalDate getCocIssieDateAsLocalDate() {
        return ProjectController.timestampToLocalDate(cocIssieDate);
    }

    public void setCocIssieDate(long cocIssieDate) {
        this.cocIssieDate = cocIssieDate;
    }

    public void setCocIssieDate(LocalDate cocIssieDate) {
        this.cocIssieDate = ProjectController.localDateToTimestamp(cocIssieDate);
    }

    public String getCocCertificateNumber() {
        return cocCertificateNumber;
    }

    public void setCocCertificateNumber(String cocCertificateNumber) {
        this.cocCertificateNumber = cocCertificateNumber;
    }

    public long getNrs097IssieDate() {
        return nrs097IssieDate;
    }

    public LocalDate getNrs097IssieDateAsLocalDate() {
        return ProjectController.timestampToLocalDate(nrs097IssieDate);
    }

    public void setNrs097IssieDate(long nrs097IssieDate) {
        this.nrs097IssieDate = nrs097IssieDate;
    }

    public void setNrs097IssieDate(LocalDate nrs097IssieDate) {
        this.nrs097IssieDate = ProjectController.localDateToTimestamp(nrs097IssieDate);
    }

    public String getNrs097CertificateNumber() {
        return nrs097CertificateNumber;
    }

    public void setNrs097CertificateNumber(String nrs097CertificateNumber) {
        this.nrs097CertificateNumber = nrs097CertificateNumber;
    }

    public String getNetworkProvide() {
        return networkProvide;
    }

    public void setNetworkProvide(String networkProvide) {
        this.networkProvide = networkProvide;
    }

    public long getDateOfApplicationHandedIn() {
        return dateOfApplicationHandedIn;
    }

    public LocalDate getDateOfApplicationHandedInAsALocalDate() {
        return ProjectController.timestampToLocalDate(dateOfApplicationHandedIn);
    }

    public void setDateOfApplicationHandedIn(long dateOfApplicationHandedIn) {
        this.dateOfApplicationHandedIn = dateOfApplicationHandedIn;
    }

    public void setDateOfApplicationHandedIn(LocalDate dateOfApplicationHandedIn) {
        this.dateOfApplicationHandedIn = ProjectController.localDateToTimestamp(dateOfApplicationHandedIn);
    }

    public long getDateOfApplicationReturned() {
        return dateOfApplicationReturned;
    }

    public LocalDate getDateOfApplicationReturnedAsLocalDate() {
        return ProjectController.timestampToLocalDate(dateOfApplicationReturned);
    }

    public void setDateOfApplicationReturned(long dateOfApplicationReturned) {
        this.dateOfApplicationReturned = dateOfApplicationReturned;
    }

    public void setDateOfApplicationReturned(LocalDate dateOfApplicationReturned) {
        this.dateOfApplicationReturned = ProjectController.localDateToTimestamp(dateOfApplicationReturned);
    }

    public long getDateOfPaymentReceived() {
        return dateOfPaymentReceived;
    }

    public LocalDate getDateOfPaymentReceivedAsLocalDate() {
        return ProjectController.timestampToLocalDate(dateOfPaymentReceived);
    }

    public void setDateOfPaymentReceived(long dateOfPaymentReceived) {
        this.dateOfPaymentReceived = dateOfPaymentReceived;
        this.expectedDateOfNetworkProvideApproval =  dateOfPaymentReceived+(120*86400000);
    }

    public void setDateOfPaymentReceived(LocalDate dateOfPaymentReceived) {
        this.dateOfPaymentReceived = ProjectController.localDateToTimestamp(dateOfPaymentReceived);
        this.expectedDateOfNetworkProvideApproval =  ProjectController.localDateToTimestamp(dateOfPaymentReceived.plusDays(120));
    }

    public long getExpectedDateOfNetworkProvideApproval() {
        return expectedDateOfNetworkProvideApproval;
    }

    public LocalDate getExpectedDateOfNetworkProvideApprovalAsLocalDate() {
        return ProjectController.timestampToLocalDate(expectedDateOfNetworkProvideApproval);
    }

    public void setExpectedDateOfNetworkProvideApproval(long expectedDateOfNetworkProvideApproval) {
        this.expectedDateOfNetworkProvideApproval = expectedDateOfNetworkProvideApproval;
    }

    public long getActualDateOfNetworkProvideApproval() {
        return actualDateOfNetworkProvideApproval;
    }

    public LocalDate getActualDateOfNetworkProvideApprovalAsLocalDate() {
        return ProjectController.timestampToLocalDate(actualDateOfNetworkProvideApproval);
    }

    public void setActualDateOfNetworkProvideApproval(LocalDate actualDateOfNetworkProvideApproval) {
        this.actualDateOfNetworkProvideApproval = ProjectController.localDateToTimestamp(actualDateOfNetworkProvideApproval);
    }

    public long getDateOfNERSAApplication() {
        return dateOfNERSAApplication;
    }

    public LocalDate getDateOfNERSAApplicationAsLocalDate() {
        return ProjectController.timestampToLocalDate(dateOfNERSAApplication);
    }

    public void setDateOfNERSAApplication(long dateOfNERSAApplication) {
        this.dateOfNERSAApplication = dateOfNERSAApplication;
    }

    public void setDateOfNERSAApplication(LocalDate dateOfNERSAApplication) {
        this.dateOfNERSAApplication = ProjectController.localDateToTimestamp(dateOfNERSAApplication);
        this.expectedDateOfNERSAApproval =  ProjectController.localDateToTimestamp(dateOfNERSAApplication.plusDays(60));
    }

    public long getExpectedDateOfNERSAApproval() {
        return expectedDateOfNERSAApproval;
    }

    public LocalDate getExpectedDateOfNERSAApprovalAsLocalDate() {
        return ProjectController.timestampToLocalDate(expectedDateOfNERSAApproval);
    }

    public void setExpectedDateOfNERSAApproval(long expectedDateOfNERSAApproval) {
        this.expectedDateOfNERSAApproval = expectedDateOfNERSAApproval;
    }

    public long getActualDateOfNERSAApproval() {
        return actualDateOfNERSAApproval;
    }

    public LocalDate getActualDateOfNERSAApprovalAsLocalDate() {
        return ProjectController.timestampToLocalDate(actualDateOfNERSAApproval);
    }

    public void setActualDateOfNERSAApproval(long actualDateOfNERSAApproval) {
        this.actualDateOfNERSAApproval = actualDateOfNERSAApproval;
    }

    public void setActualDateOfNERSAApproval(LocalDate actualDateOfNERSAApproval) {
        this.actualDateOfNERSAApproval = ProjectController.localDateToTimestamp(actualDateOfNERSAApproval);
    }
}
