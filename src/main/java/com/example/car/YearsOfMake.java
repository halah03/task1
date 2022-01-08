package com.example.car;

public class YearsOfMake {
    private int id;
    private int yearOfMake;

    public YearsOfMake(int id, int yearOfMake) {
        this.id = id;
        this.yearOfMake = yearOfMake;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYearOfMake() {
        return yearOfMake;
    }

    public void setYearOfMake(int yearOfMake) {
        this.yearOfMake = yearOfMake;
    }
}
