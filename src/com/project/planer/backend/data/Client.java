package com.project.planer.Data;

public class Client{

    private String name;
    private String surname;
    private String contactNumber;
    private String companyName;
    private String address;

    public Client(String name, String surname, String contactNumber, String companyName, String address) {
        this.name = name;
        this.surname = surname;
        this.contactNumber = contactNumber;
        this.companyName = companyName;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return name+","+surname+","+contactNumber+","+companyName+","+address ;
    }
}
