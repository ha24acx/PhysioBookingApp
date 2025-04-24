
package com.mycompany.physiobookingapp;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;



public class PatientTest {
    @Test
    public void testPatientCreation() {
        Patient patient = new Patient("Ali", "Lahore", "03001234567", "pass123");
        assertTrue(patient.getId() > 0);
        assertEquals("Ali", patient.getName());
        assertEquals("pass123", patient.getPassword());
        assertNotNull(patient.toString());
    }
    
    @Test
    public void testIdAutoIncrement() {
        // Create first patient and get its ID
        Patient p1 = new Patient("A", "B", "C", "D");
        int firstId = p1.getId();
        
        // Create second patient - should have next ID
        Patient p2 = new Patient("E", "F", "G", "H");
        assertEquals(firstId + 1, p2.getId());
        
        // Create third patient - should continue sequence
        Patient p3 = new Patient("I", "J", "K", "L");
        assertEquals(firstId + 2, p3.getId());
    }
}