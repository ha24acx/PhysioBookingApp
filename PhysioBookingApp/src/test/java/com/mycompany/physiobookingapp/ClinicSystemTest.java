
package com.mycompany.physiobookingapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.List;



public class ClinicSystemTest {

    private ClinicSystem system;

    @BeforeEach
    public void setUp() {
        system = new ClinicSystem();
        system.initializeData();  // Pre-load physios and patients
    }

    @Test
    public void testPhysiosInitializedCorrectly() throws Exception {
        Field physiosField = ClinicSystem.class.getDeclaredField("physios");
        physiosField.setAccessible(true);

        List<Physiotherapist> physios = (List<Physiotherapist>) physiosField.get(system);

        assertEquals(5, physios.size());
        assertEquals("Khan", physios.get(0).getName());
        assertEquals("Imran", physios.get(1).getName());
    }

    @Test
    public void testPatientsInitializedCorrectly() throws Exception {
        Field patientsField = ClinicSystem.class.getDeclaredField("allPatients");
        patientsField.setAccessible(true);

        List<Patient> patients = (List<Patient>) patientsField.get(system);

        assertEquals(10, patients.size());
        assertEquals("Ali Raza", patients.get(0).getName());
    }

    @Test
    public void testTreatmentBookingFlow() throws Exception {
        Field physiosField = ClinicSystem.class.getDeclaredField("physios");
        Field patientsField = ClinicSystem.class.getDeclaredField("allPatients");
        physiosField.setAccessible(true);
        patientsField.setAccessible(true);

        List<Physiotherapist> physios = (List<Physiotherapist>) physiosField.get(system);
        List<Patient> patients = (List<Patient>) patientsField.get(system);

        Treatment t = physios.get(0).getTreatments().get(0);  // Massage
        Patient p = patients.get(0);  // Ali Raza

        assertTrue(t.isAvailable());

        t.book(p);
        assertEquals(AppointmentStatus.BOOKED, t.getStatus());
        assertEquals(p, t.getPatient());
    }

    @Test
    public void testTreatmentCancellation() throws Exception {
        Field physiosField = ClinicSystem.class.getDeclaredField("physios");
        physiosField.setAccessible(true);

        List<Physiotherapist> physios = (List<Physiotherapist>) physiosField.get(system);
        Treatment t = physios.get(0).getTreatments().get(0);
        t.book(new Patient("Test", "City", "000", "123"));

        t.cancel();
        assertEquals(AppointmentStatus.CANCELLED, t.getStatus());
    }

    @Test
    public void testTreatmentAttendance() throws Exception {
        Field physiosField = ClinicSystem.class.getDeclaredField("physios");
        physiosField.setAccessible(true);

        List<Physiotherapist> physios = (List<Physiotherapist>) physiosField.get(system);
        Treatment t = physios.get(1).getTreatments().get(0);
        t.book(new Patient("Test", "City", "000", "123"));

        t.attend();
        assertEquals(AppointmentStatus.ATTENDED, t.getStatus());
    }
}
