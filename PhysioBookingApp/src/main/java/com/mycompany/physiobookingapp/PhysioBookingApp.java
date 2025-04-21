
package com.mycompany.physiobookingapp;

public class PhysioBookingApp {

    public static void main(String[] args) {
        ClinicSystem system = new ClinicSystem();
        system.initializeData();  // Add sample physios and treatments
        system.start();           // Begin menu system
    }
}
