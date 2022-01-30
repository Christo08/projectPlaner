package com.project.planer.backend.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client{
    private String businessName = "";
    private String ownerName = "";
    private String ownerSurname = "";
    private String contactPersonsName = "";
    private String contactPersonsSurname = "";
    private String contactPhoneNumber = "";
    private String email = "";
    private String addressGPSCoordinates = "";

    public Client() {
    }

    public Client(String businessName, String ownerName, String ownerSurname, String contactPersonsName, String contactPersonsSurname, String contactPhoneNumber, String email, String addressGPSCoordinates) {
        this.businessName = businessName;
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.contactPersonsName = contactPersonsName;
        this.contactPersonsSurname = contactPersonsSurname;
        this.contactPhoneNumber = contactPhoneNumber;
        this.email = email;
        this.addressGPSCoordinates = addressGPSCoordinates;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getContactPersonsSurname() {
        return contactPersonsSurname;
    }

    public void setContactPersonsSurname(String contactPersonsSurname) {
        this.contactPersonsSurname = contactPersonsSurname;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }

    public String getContactPersonsName() {
        return contactPersonsName;
    }

    public void setContactPersonsName(String contactPersonsName) {
        this.contactPersonsName = contactPersonsName;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressGPSCoordinates() {
        return addressGPSCoordinates;
    }

    public void setAddressGPSCoordinates(String addressGPSCoordinates) {
        this.addressGPSCoordinates = addressGPSCoordinates;
    }
}
