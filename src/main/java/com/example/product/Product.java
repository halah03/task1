package com.example.product;


public class Product {
    private int id;
    private String name;
    private String yearOfMake;
    private int amount;

    public Product() {
    }
    public Product(int id, String name, String yearOfMake, int amount) {
        this.id = id;
        this.name = name;
        this.yearOfMake = yearOfMake;
        this.amount = amount;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getYearOfMake() {
        return yearOfMake;
    }
    public void setYearOfMake(String yearOfMake) {
        this.yearOfMake = yearOfMake;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void addAmount(int amount)
    {
        this.amount += amount;
    }
    public void removeAmount(int amount)
    {
        this.amount -= amount;
    }
}
