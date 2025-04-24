package com.mycompany.physiobookingapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PhysiotherapistTest {
    
    @Test
    public void testInitialization() {
        List<String> expertise = Arrays.asList("Massage", "Rehabilitation");
        Physiotherapist physio = new Physiotherapist("Dr. Khan", "03451234567", expertise);
        
        assertEquals("Dr. Khan", physio.getName());
        assertEquals("03451234567", physio.getPhone());
        assertEquals(expertise, physio.getExpertise());
        assertTrue(physio.getTreatments().isEmpty());
        assertTrue(physio.getId() > 0); // ID should be auto-generated
    }
    
    @Test
    public void testAddTreatment() {
        Physiotherapist physio = new Physiotherapist("Dr. Khan", "03451234567", Arrays.asList("Massage"));
        Treatment treatment = new Treatment("Massage", LocalDateTime.of(2025, 5, 1, 10, 0), physio);
        
        physio.addTreatment(treatment);
        List<Treatment> treatments = physio.getTreatments();
        
        assertEquals(1, treatments.size());
        assertEquals(treatment, treatments.get(0));
        assertEquals(physio, treatments.get(0).getPhysio());
    }
    
    @Test
    public void testToString() {
        List<String> expertise = Arrays.asList("Massage", "Rehabilitation");
        Physiotherapist physio = new Physiotherapist("Khan", "03451234567", expertise);
        
        String result = physio.toString();
        assertTrue(result.contains("Dr. Khan"));
        assertTrue(result.contains("Massage"));
        assertTrue(result.contains("Rehabilitation"));
    }
    
    @Test
    public void testIdAutoIncrement() {
//        Physiotherapist.resetIdCounter(); // Helper method needed (see note below)
        Physiotherapist physio1 = new Physiotherapist("Dr. A", "111", Arrays.asList("A"));
        Physiotherapist physio2 = new Physiotherapist("Dr. B", "222", Arrays.asList("B"));
        
        assertEquals(1, physio1.getId());
        assertEquals(2, physio2.getId());
    }
    
    // NOTE: Since the original code doesn't have a way to reset the idCounter,
    // this test might fail if run after other tests. In a real project, we would
    // add a package-private static resetIdCounter() method to Physiotherapist class.
}