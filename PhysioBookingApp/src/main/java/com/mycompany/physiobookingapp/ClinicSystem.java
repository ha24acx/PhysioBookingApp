
package com.mycompany.physiobookingapp;

import java.time.LocalDateTime;
import java.util.*;

public class ClinicSystem {
    private List<Patient> allPatients = new ArrayList<>();
    private List<Physiotherapist> physios = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void initializeData() {
        // === Physiotherapists and Treatments ===
        Physiotherapist p1 = new Physiotherapist("Khan", "03451234567", Arrays.asList("Massage", "Rehabilitation"));
        Physiotherapist p2 = new Physiotherapist("Imran", "03551234567", Arrays.asList("Osteopathy", "Physiotherapy"));
        
    
        // Week 1–4: spread over 4 weeks in May 2025
        p1.addTreatment(new Treatment("Massage", LocalDateTime.of(2025, 5, 1, 10, 0), p1));
        p1.addTreatment(new Treatment("Rehabilitation", LocalDateTime.of(2025, 5, 3, 11, 0), p1));
        
    
        p2.addTreatment(new Treatment("Osteopathy", LocalDateTime.of(2025, 5, 2, 14, 0), p2));
        p2.addTreatment(new Treatment("Physiotherapy", LocalDateTime.of(2025, 5, 6, 13, 0), p2));
        
    
        physios.addAll(Arrays.asList(p1, p2));
    
        // === 15 Sample Patients ===
        allPatients.add(new Patient("Ali Raza", "Lahore", "03011234567"));
        allPatients.add(new Patient("Sara Khan", "Karachi", "03021234567"));
        allPatients.add(new Patient("Ahmed Butt", "Islamabad", "03031234567"));
    
    }
    

    public void start() {
        while (true) {
            System.out.println("\n--- Welcome to Boost Physio Clinic ---");
            System.out.println("1. I am a Patient");
            System.out.println("2. I am Admin");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> patientPanel();
                case 2 -> adminPanel();
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void patientPanel() {
        while (true) {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. Book Appointment");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. Update Appointment");
            System.out.println("4. Back");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> bookAppointmentFlow();
                case 2 -> cancelAppointmentFlow();
                case 3 -> updateAppointmentFlow();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void adminPanel() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Mark Appointment as Attended");
            System.out.println("2. Generate Report");
            System.out.println("3. Back");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> markAttended();
                case 2 -> showReport();
                case 3 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void bookAppointmentFlow() {
        System.out.println("\n--- Book Appointment ---");
        System.out.println("1. By Expertise");
        System.out.println("2. By Doctor");
        System.out.print("Choose option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Treatment> available = new ArrayList<>();

        if (choice == 1) {
            Set<String> allExpertise = new HashSet<>();
            for (Physiotherapist p : physios) {
                allExpertise.addAll(p.getExpertise());
            }

            List<String> list = new ArrayList<>(allExpertise);
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }

            System.out.print("Select expertise: ");
            int ex = scanner.nextInt() - 1;
            scanner.nextLine();
            if (ex < 0 || ex >= list.size()) return;

            String selected = list.get(ex);
            for (Physiotherapist p : physios) {
                if (p.getExpertise().contains(selected)) {
                    for (Treatment t : p.getTreatments()) {
                        if (t.getName().equalsIgnoreCase(selected) && t.isAvailable()) {
                            available.add(t);
                        }
                    }
                }
            }
        } else if (choice == 2) {
            for (int i = 0; i < physios.size(); i++) {
                System.out.println((i + 1) + ". " + physios.get(i).getName());
            }
            System.out.print("Choose doctor: ");
            int doc = scanner.nextInt() - 1;
            scanner.nextLine();
            if (doc < 0 || doc >= physios.size()) return;

            Physiotherapist selected = physios.get(doc);
            for (Treatment t : selected.getTreatments()) {
                if (t.isAvailable()) {
                    available.add(t);
                }
            }
        }

        if (available.isEmpty()) {
            System.out.println("No available appointments.");
            return;
        }

        for (int i = 0; i < available.size(); i++) {
            System.out.println((i + 1) + ". " + available.get(i));
        }

        System.out.print("Select appointment: ");
        int idx = scanner.nextInt() - 1;
        scanner.nextLine();
        if (idx < 0 || idx >= available.size()) return;

        Patient newPatient = createNewPatient();
        allPatients.add(newPatient);
        available.get(idx).book(newPatient);
        System.out.println("Appointment booked successfully.");
    }

    private void cancelAppointmentFlow() {
        List<Treatment> booked = getBookedAppointments();
        if (booked.isEmpty()) {
            System.out.println("No booked appointments.");
            return;
        }

        for (int i = 0; i < booked.size(); i++) {
            System.out.println((i + 1) + ". " + booked.get(i));
        }

        System.out.print("Select appointment to cancel: ");
        int idx = scanner.nextInt() - 1;
        scanner.nextLine();
        if (idx < 0 || idx >= booked.size()) return;

        booked.get(idx).cancel();
        System.out.println("Appointment cancelled.");
    }

    private void updateAppointmentFlow() {
        List<Treatment> booked = getBookedAppointments();
        if (booked.isEmpty()) {
            System.out.println("No booked appointments.");
            return;
        }

        Treatment current = booked.get(0);  // For simplicity, update first found
        System.out.println("Your current appointment: " + current);

        List<Treatment> available = new ArrayList<>();
        for (Physiotherapist p : physios) {
            for (Treatment t : p.getTreatments()) {
                if (t.isAvailable()) {
                    available.add(t);
                }
            }
        }

        for (int i = 0; i < available.size(); i++) {
            System.out.println((i + 1) + ". " + available.get(i));
        }

        System.out.print("Select new appointment: ");
        int idx = scanner.nextInt() - 1;
        scanner.nextLine();
        if (idx < 0 || idx >= available.size()) return;

        Patient patient = current.getPatient();
        current.cancel();
        available.get(idx).book(patient);
        System.out.println("Appointment updated successfully.");
    }

    private void markAttended() {
        List<Treatment> booked = getBookedAppointments();
        if (booked.isEmpty()) {
            System.out.println("No booked appointments.");
            return;
        }

        for (int i = 0; i < booked.size(); i++) {
            System.out.println((i + 1) + ". " + booked.get(i));
        }

        System.out.print("Select appointment to mark as attended: ");
        int idx = scanner.nextInt() - 1;
        scanner.nextLine();
        if (idx < 0 || idx >= booked.size()) return;

        booked.get(idx).attend();
        System.out.println("Appointment marked as attended.");
    }

    private void showReport() {
        System.out.println("\n--- Appointment Report ---");
        Map<Physiotherapist, Integer> attendCount = new HashMap<>();

        for (Physiotherapist p : physios) {
            for (Treatment t : p.getTreatments()) {
                System.out.println(p.getName() + " | " + t);
                if (t.getStatus() == AppointmentStatus.ATTENDED) {
                    attendCount.put(p, attendCount.getOrDefault(p, 0) + 1);
                }
            }
        }

        System.out.println("\n--- Physios by Attended Appointments ---");
        physios.stream()
                .sorted((a, b) -> attendCount.getOrDefault(b, 0) - attendCount.getOrDefault(a, 0))
                .forEach(p -> System.out.println(p.getName() + ": " + attendCount.getOrDefault(p, 0)));
    }

    private Patient createNewPatient() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your phone: ");
        String phone = scanner.nextLine();
        return new Patient(name, address, phone);
    }

    private List<Treatment> getBookedAppointments() {
        List<Treatment> result = new ArrayList<>();
        for (Physiotherapist p : physios) {
            for (Treatment t : p.getTreatments()) {
                if (t.getStatus() == AppointmentStatus.BOOKED) {
                    result.add(t);
                }
            }
        }
        return result;
    }
}
