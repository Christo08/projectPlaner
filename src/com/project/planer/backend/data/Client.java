package com.project.planer.backend.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client{
    private String businessName;
    private String ownerNameSurname;
    private String contactPersonsNameSurname;
    private String contactPhoneNumber;
    private String email;
    private String addressGPSCoordinates;

    public Client() {
    }

    public Client(String businessName, String ownerNameSurname, String contactPersonsNameSurname, String contactPhoneNumber, String email, String addressGPSCoordinates) {
        this.businessName = businessName;
        this.ownerNameSurname = ownerNameSurname;
        this.contactPersonsNameSurname = contactPersonsNameSurname;
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

    public String getOwnerNameSurname() {
        return ownerNameSurname;
    }

    public void setOwnerNameSurname(String ownerNameSurname) {
        this.ownerNameSurname = ownerNameSurname;
    }

    public String getContactPersonsNameSurname() {
        return contactPersonsNameSurname;
    }

    public void setContactPersonsNameSurname(String contactPersonsNameSurname) {
        this.contactPersonsNameSurname = contactPersonsNameSurname;
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
