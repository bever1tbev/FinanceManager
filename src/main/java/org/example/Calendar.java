package org.example;

public class Calendar {
    private String day;
    private String month;
    private String year;

    public Calendar(String date) {
        this.year = date.split("-")[0];
        this.month = date.split("-")[1];
        this.day = date.split("-")[2];
    }


}
