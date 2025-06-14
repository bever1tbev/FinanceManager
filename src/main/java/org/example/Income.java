package org.example;

import java.io.File;

public class Income {
    private int value;
    private String name;
    private String date;

    public Income(String name, int value, String date) {
        this.value = value;
        this.name = name;
        this.date = date;
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }
}
