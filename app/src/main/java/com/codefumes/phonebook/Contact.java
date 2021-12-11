package com.codefumes.phonebook;

public class Contact {
    public static int idCounter = 0;
    public int id ;
    public String name;
    public String phoneNumber;
    public String email;

    public Contact(String name, String phoneNumber, String email) {
        this.id = idCounter;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idCounter++;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
