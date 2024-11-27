package com.Student.Entity;

import java.util.Date;

public class errorDetails {
    private Date date;
    private String message;
    private String request;

    public errorDetails(Date date, String message, String request) {
        this.date = date;
        this.message = message;
        this.request = request;
    }
}
