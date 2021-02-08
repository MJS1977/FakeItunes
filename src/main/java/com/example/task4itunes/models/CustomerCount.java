package com.example.task4itunes.models;

public class CustomerCount { //class for counting customers per country

    String customersPerCountry;
    String country;

    public CustomerCount() {
    }

    public CustomerCount(  String country, String customersPerCountry ) {
        this.country = country;
        this.customersPerCountry = customersPerCountry;
    }

    public void setCustomersPerCountry(String customersPerCountry) {
        this.customersPerCountry = customersPerCountry;
    }

    public String getCustomersPerCountry() {
        return customersPerCountry;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
