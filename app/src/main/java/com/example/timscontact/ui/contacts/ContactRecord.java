package com.example.timscontact.ui.contacts;

public class ContactRecord {
    private String fullname, company, address, email;
    private String number;
    private String contactImg;

    public ContactRecord(String fullname, String company, String address, String email, String number, String contactImg) {
        this.fullname = fullname;
        this.company = company;
        this.address = address;
        this.email = email;
        this.number = number;
        this.contactImg = contactImg;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContactImg() {
        return contactImg;
    }

    public void setContactImg(String contactImg) {
        this.contactImg = contactImg;
    }
}
