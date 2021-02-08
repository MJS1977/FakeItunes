package com.example.task4itunes.models;

public class CustomerSpend { //class for calculating highest spending customers

    String customerId;
    String firstName;
    String lastName;
    String total;

    public CustomerSpend(){
    }

    public CustomerSpend(String customerId, String firstName, String lastName, String total) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.total = total;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
