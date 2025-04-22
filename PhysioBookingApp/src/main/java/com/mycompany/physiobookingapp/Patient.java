
package com.mycompany.physiobookingapp;



public class Patient {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String address;
    private String phone;
    private String password;

    public Patient(String name, String address, String phone, String password) {
        this.id = idCounter++;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return "Patient #" + id + ": " + name + " (" + phone + ")";
    }
}