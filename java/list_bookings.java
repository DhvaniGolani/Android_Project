package com.example.login;

public class list_bookings {

    private String UserName;
    private String ServiceType;
    private String Date;
    private String time;

    public list_bookings() {
    }

    public list_bookings(String userName, String serviceType, String date, String time) {
        UserName = userName;
        ServiceType = serviceType;
        Date = date;
        this.time = time;
    }

    public String getUserName() {
        return UserName;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public String getDate() {
        return Date;
    }

    public String gettime() {
        return time;
    }
}
