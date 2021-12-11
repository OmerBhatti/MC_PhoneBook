package com.codefumes.phonebook;

public class Contact {
    public static int id = 0;
    public String name;
    public String phoneNumber;
    public String email;

    public Contact(String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id++;
    }
}
