
package com.mycompany.physiobookingapp;

import java.util.*;

public class Physiotherapist {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String phone;
    private List<String> expertise;
    private List<Treatment> treatments = new ArrayList<>();

    public Physiotherapist(String name, String phone, List<String> expertise) {
        this.id = idCounter++;
        this.name = name;
        this.phone = phone;
        this.expertise = expertise;
    }

    public void addTreatment(Treatment t) {
        treatments.add(t);
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public List<String> getExpertise() {
        return expertise;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Dr. " + name + " [" + String.join(", ", expertise) + "]";
    }
}
