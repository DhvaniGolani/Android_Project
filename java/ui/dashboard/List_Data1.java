package com.example.login.ui.dashboard;

public class List_Data1 {
    private String ServiceType;
    private String Status;
    private String Date;
    private String time;

    public List_Data1() {
    }

    public List_Data1(String serviceType, String status, String date, String time) {
        this.ServiceType = serviceType;
        this.Status = status;
        this.Date = date;
        this.time = time;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public String getStatus() {
        return Status;
    }

    public String getDate() {
        return Date;
    }

    public String gettime() {
        return time;
    }
}
