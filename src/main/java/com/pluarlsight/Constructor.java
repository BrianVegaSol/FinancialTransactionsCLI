package com.pluarlsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Constructor {
    private String type;
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime dateTime;
    private String description;
    private String vendor;
    private double amount;
    private LocalDateTime timestamp;

    public Constructor() {
        this.type = type;
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }


    // Constructor
    public Constructor(String type, LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.type = type;
        this.date = date;
        this.time = time;
        this.dateTime = LocalDateTime.of(date, time); // Combine date and time
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return (type + "|" + date + "|" + time + "|" + description + "|" +
                vendor + "|" + amount);
    }

}
