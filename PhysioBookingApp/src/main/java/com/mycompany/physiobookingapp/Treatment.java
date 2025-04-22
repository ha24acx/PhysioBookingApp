
package com.mycompany.physiobookingapp;



import java.time.LocalDateTime;

public class Treatment {
    private String name;
    private LocalDateTime time;
    private Patient patient;
    private AppointmentStatus status;
    private Physiotherapist physio;

    public Treatment(String name, LocalDateTime time, Physiotherapist physio) {
        this.name = name;
        this.time = time;
        this.status = AppointmentStatus.AVAILABLE;
        this.physio = physio;
    }

    public void book(Patient p) {
        this.patient = p;
        this.status = AppointmentStatus.BOOKED;
    }

    public void cancel() {
        this.status = AppointmentStatus.CANCELLED;
    }

    public void attend() {
        this.status = AppointmentStatus.ATTENDED;
    }

    public boolean isAvailable() {
        return status == AppointmentStatus.AVAILABLE;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Patient getPatient() {
        return patient;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public Physiotherapist getPhysio() {
        return physio;
    }

    public void reset() {
        this.patient = null;
        this.status = AppointmentStatus.AVAILABLE;
    }

    public String toString() { 
        return name + " at " + time + " - " + status +
                (status != AppointmentStatus.AVAILABLE ? " (Patient: " + patient.getName() + ")" : "");
    }
}