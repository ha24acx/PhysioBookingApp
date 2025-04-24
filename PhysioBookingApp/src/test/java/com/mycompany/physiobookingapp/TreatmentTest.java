package com.mycompany.physiobookingapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.time.LocalDateTime;



public class TreatmentTest {
    @Test
    public void testTreatmentCreation() {
        Physiotherapist physio = new Physiotherapist("Dr. Khan", "03001234567", null);
        LocalDateTime time = LocalDateTime.now();
        Treatment t = new Treatment("Massage", time, physio);
        
        assertEquals("Massage", t.getName());
        assertEquals(time, t.getTime());
        assertEquals(physio, t.getPhysio());
        assertEquals(AppointmentStatus.AVAILABLE, t.getStatus());
        assertTrue(t.isAvailable());
    }
    
    @Test
    public void testStatusTransitions() {
        Physiotherapist physio = new Physiotherapist("Dr. Khan", "03001234567", null);
        Treatment t = new Treatment("Massage", null, physio);
        Patient p = new Patient("Ali", "Lahore", "03001234567", "pass123");
        
        // Test booking
        t.book(p);
        assertEquals(AppointmentStatus.BOOKED, t.getStatus());
        assertEquals(p, t.getPatient());
        
        // Test attending
        t.attend();
        assertEquals(AppointmentStatus.ATTENDED, t.getStatus());
        
        // Test cancellation
        t.cancel();
        assertEquals(AppointmentStatus.CANCELLED, t.getStatus());
    }
}