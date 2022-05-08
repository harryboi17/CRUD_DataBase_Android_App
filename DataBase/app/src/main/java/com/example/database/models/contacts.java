package com.example.database.models;

public class contacts {
    private int id;
    private String name;
    private String phoneNumber;
    private String Year;
    private String Branch;

    public contacts(int id, String name, String phoneNumber, String year, String branch) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        Year = year;
        Branch = branch;
    }
    public contacts(String name, String phoneNumber, String year, String branch) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        Year = year;
        Branch = branch;
    }
    public contacts(){}

    public int getId() { return id; }
    public void setId(int id) { this.id = id;}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getYear() {
        return Year;
    }
    public void setYear(String year) {
        Year = year;
    }

    public String getBranch() {
        return Branch;
    }
    public void setBranch(String branch) {
        Branch = branch;
    }
}
