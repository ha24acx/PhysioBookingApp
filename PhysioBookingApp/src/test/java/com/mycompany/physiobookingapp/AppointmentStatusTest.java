package com.mycompany.physiobookingapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.List;




public class AppointmentStatusTest {
    @Test
    public void testEnumValues() {
        assertEquals(4, AppointmentStatus.values().length);
        assertEquals(AppointmentStatus.AVAILABLE, AppointmentStatus.valueOf("AVAILABLE"));
        assertEquals(AppointmentStatus.BOOKED, AppointmentStatus.valueOf("BOOKED"));
        assertEquals(AppointmentStatus.CANCELLED, AppointmentStatus.valueOf("CANCELLED"));
        assertEquals(AppointmentStatus.ATTENDED, AppointmentStatus.valueOf("ATTENDED"));
    }
}