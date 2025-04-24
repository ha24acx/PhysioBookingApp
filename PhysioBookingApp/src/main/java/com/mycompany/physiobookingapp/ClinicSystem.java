
package com.mycompany.physiobookingapp;



import java.time.LocalDateTime;
import java.util.*;

public class ClinicSystem {
    private List<Patient> allPatients = new ArrayList<>();
    private List<Physiotherapist> physios = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private Patient currentPatient = null;
    private final String ADMIN_PASSWORD = "admin123"; // Default admin password

    public void initializeData() {
    // === Physiotherapists and Treatments ===
    Physiotherapist p1 = new Physiotherapist("Khan", "03451234567", Arrays.asList("Massage", "Rehabilitation"));
    Physiotherapist p2 = new Physiotherapist("Imran", "03551234567", Arrays.asList("Osteopathy", "Physiotherapy"));
    Physiotherapist p3 = new Physiotherapist("Fatima", "03331234567", Arrays.asList("Acupuncture", "Yoga Therapy"));
    Physiotherapist p4 = new Physiotherapist("Abdullah", "03111234567", Arrays.asList("Sports Therapy", "Pain Management"));
    Physiotherapist p5 = new Physiotherapist("Ayesha", "03211234567", Arrays.asList("Pediatric Care", "Geriatric Care"));
    
    
    p1.addTreatment(new Treatment("Massage", LocalDateTime.of(2025, 5, 1, 10, 0), p1));
    p1.addTreatment(new Treatment("Rehabilitation", LocalDateTime.of(2025, 5, 3, 11, 0), p1));
    p1.addTreatment(new Treatment("Massage", LocalDateTime.of(2025, 5, 15, 14, 0), p1));
    p2.addTreatment(new Treatment("Osteopathy", LocalDateTime.of(2025, 5, 2, 14, 0), p2));
    p2.addTreatment(new Treatment("Physiotherapy", LocalDateTime.of(2025, 5, 6, 13, 0), p2));
    p2.addTreatment(new Treatment("Osteopathy", LocalDateTime.of(2025, 5, 20, 10, 0), p2));
    p3.addTreatment(new Treatment("Acupuncture", LocalDateTime.of(2025, 5, 5, 9, 0), p3));
    p3.addTreatment(new Treatment("Yoga Therapy", LocalDateTime.of(2025, 5, 12, 16, 0), p3));
    p4.addTreatment(new Treatment("Sports Therapy", LocalDateTime.of(2025, 5, 7, 11, 0), p4));
    p4.addTreatment(new Treatment("Pain Management", LocalDateTime.of(2025, 5, 14, 15, 0), p4));
    p5.addTreatment(new Treatment("Pediatric Care", LocalDateTime.of(2025, 5, 8, 10, 0), p5));
    p5.addTreatment(new Treatment("Geriatric Care", LocalDateTime.of(2025, 5, 22, 13, 0), p5));
    
    physios.addAll(Arrays.asList(p1, p2, p3, p4, p5));
    
    allPatients.add(new Patient("Ali Raza", "Lahore", "03011234567", "pass123"));
    allPatients.add(new Patient("Sara Khan", "Karachi", "03021234567", "pass123"));
    allPatients.add(new Patient("Ahmed Butt", "Islamabad", "03031234567", "pass123"));
    allPatients.add(new Patient("Fatima Akhtar", "Rawalpindi", "03041234567", "pass123"));
    allPatients.add(new Patient("Usman Malik", "Peshawar", "03051234567", "pass123"));
    allPatients.add(new Patient("Ayesha Siddiqui", "Quetta", "03061234567", "pass123"));
    allPatients.add(new Patient("Bilal Ahmed", "Faisalabad", "03071234567", "pass123"));
    allPatients.add(new Patient("Hina Shah", "Multan", "03081234567", "pass123"));
    allPatients.add(new Patient("Kamran Ali", "Gujranwala", "03091234567", "pass123"));
    allPatients.add(new Patient("Nadia Khan", "Hyderabad", "03101234567", "pass123"));
}
    
    public void start() {
        while (true) {
            System.out.println("\n***************************************");
            System.out.println("  --- Welcome to Boost Physio Clinic ---");
            System.out.println("*****************************************");
            System.out.println("1. I am a Patient");
            System.out.println("2. I am Admin");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> patientAuthPanel();
                case 2 -> adminAuthPanel();
                case 3 -> System.exit(0);
                default -> System.out.println("---Invalid choice.----");
            }
        }
    }

    private void patientAuthPanel() {
        while (true) {
            System.out.println("\n--- Patient Authentication ---");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Back");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> signInPatient();
                case 2 -> signUpPatient();
                case 3 -> { return; }
                default -> System.out.println("---Invalid choice.---");
            }
            
            if (currentPatient != null) {
                patientPanel();
                currentPatient = null; // Reset after patient is done
            }
        }
    }

    private void signInPatient() {
        System.out.print("Enter your patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        for (Patient p : allPatients) {
            if (p.getId() == id && p.getPassword().equals(password)) {
                currentPatient = p;
                System.out.println("Login successful! Welcome " + p.getName());
                return;
            }
        }
        System.out.println("Invalid ID or password.");
    }

    private void signUpPatient() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your phone: ");
        String phone = scanner.nextLine();
        System.out.print("Set your password: ");
        String password = scanner.nextLine();

        Patient newPatient = new Patient(name, address, phone, password);
        allPatients.add(newPatient);
        System.out.println("\n***************************************");
        System.out.println("Registration successful! Your patient ID is: " + newPatient.getId());
        System.out.println("Please use this ID to sign in next time.");
        System.out.println("***************************************");
    }

    private void adminAuthPanel() {
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        if (password.equals(ADMIN_PASSWORD)) {
            System.out.println("\n************************");
            System.out.println("Welcom to Admin Panel");
            System.out.println("************************");
            adminPanel();
        } else {
            System.out.println("\n********************************");
            System.out.println("Invalid password. Access denied.");
            System.out.println("********************************");
        }
    }

    private void patientPanel() {
        while (true) {
            System.out.println("\n**********************");
            System.out.println("--- Patient Menu ---");
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
        System.out.println("\n***************************");
        System.out.println("--- Book Appointment ---");
        System.out.println("1. By Expertise");
        System.out.println("2. By Doctor");
        System.out.println("***************************");
        System.out.print("Choose option: \n");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("***************************\n");
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
    
            System.out.print("\nSelect expertise: ");
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
            System.out.println("\n**************************");
            System.out.println("No available appointments.");
            System.out.println("**************************");
            return;
        }

        for (int i = 0; i < available.size(); i++) {
            System.out.println((i + 1) + ". " + available.get(i));
        }

        System.out.print("Select appointment: ");
        int idx = scanner.nextInt() - 1;
        scanner.nextLine();
        if (idx < 0 || idx >= available.size()) return;

        available.get(idx).book(currentPatient);
        System.out.println("\n********************************");
        System.out.println("Appointment booked successfully.");
        System.out.println("********************************");

        // Show details of booking
        Treatment bookedTreatment = available.get(idx);
        System.out.println("Booking Details:");
        System.out.println("Patient ID: "+ currentPatient.getId());
        System.out.println("Patient: " + currentPatient.getName());
        System.out.println("Date & Time: " + bookedTreatment.getTime());
        System.out.println("Treatment: " + bookedTreatment.getName());
        System.out.println("Physiotherapist: " + bookedTreatment.getPhysio().getName());
    }

    private void cancelAppointmentFlow() {
        List<Treatment> booked = getBookedAppointmentsForCurrentPatient();
        if (booked.isEmpty()) {
            System.out.println("\n***********************");
            System.out.println("No booked appointments.");
            System.out.println("***********************");
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
        // 1. Get current patient's booked appointments
        List<Treatment> bookedAppointments = getBookedAppointmentsForCurrentPatient();
        if (bookedAppointments.isEmpty()) {
            System.out.println("\n***************************************");
            System.out.println("You do not have any booked appointment.");
            System.out.println("***************************************");
            return;
        }
    
        // 2. Show current appointments
        System.out.println("\n****************************");
        System.out.println("Your Current Appointments:"); 
        System.out.println("****************************");
        for (int i = 0; i < bookedAppointments.size(); i++) {
            System.out.println((i+1) + ". " + bookedAppointments.get(i));
        }
    
        // 3. Select appointment to update
        System.out.println("\n*********************************************************");
        System.out.print("To update, please select an appointment (enter the number):");
        System.out.println("*********************************************************");
        int selectedIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (selectedIndex < 0 || selectedIndex >= bookedAppointments.size()) {
            System.out.println("\n********************************");
            System.out.println("Invalid selection. Please retry.");
            System.out.println("********************************");
            return;
        }
    
        Treatment oldAppointment = bookedAppointments.get(selectedIndex);
        
        // 4. Cancel old appointment
        oldAppointment.cancel();
        System.out.println("\nAppointment cancelled: " + oldAppointment);
    
        // 5. Get ALL available appointments 
        List<Treatment> availableSlots = getAllAvailableAppointments();
        
        if (availableSlots.isEmpty()) {
            System.out.println("\n***********************************************************");
            System.out.println("No slots are available. Your appointment has been canceled.");
            System.out.println("***********************************************************");
            return;
        }
    
        // 6. Show available slots
        System.out.println("\n******************");
        System.out.println("\nAvailable Slots:");
        System.out.println("******************");
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.println((i+1) + ". " + availableSlots.get(i));
        }
    
        // 7. Select new appointment
        System.out.println("\n*************************************");
        System.out.print("Choose a new appointment (enter the number):");
        System.out.println("***************************************");
        int newSlotIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (newSlotIndex < 0 || newSlotIndex >= availableSlots.size()) {
            System.out.println("\n************************************************");
            System.out.println("Wrong selection. The appointment remains canceled.");
            System.out.println("************************************************");
            return;
        }
    
        // 8. Book new appointment
        Treatment newAppointment = availableSlots.get(newSlotIndex);
        newAppointment.book(currentPatient);
        
        // 9. Show confirmation
        System.out.println("\n*******************************************");
        System.out.println("Confirmed! Your appointment has been changed:");
        System.out.println("*******************************************");
        printAppointmentDetails(newAppointment);
    }
    
    // Naya helper method - getAllAvailableAppointments()
    private List<Treatment> getAllAvailableAppointments() {
        List<Treatment> available = new ArrayList<>();
        for (Physiotherapist p : physios) {
            for (Treatment t : p.getTreatments()) {
                if (t.getStatus() == AppointmentStatus.AVAILABLE) {
                    available.add(t);
                }
            }
        }
        return available;
    }
    
    
    private void printAppointmentDetails(Treatment t) {
        System.out.println("\n*******************************");
        System.out.println("     Your Appointment Details     ");
        System.out.println("*********************************");
        System.out.println("Patient ID: " + t.getPatient().getId());
        System.out.println("Patient: " + t.getPatient().getName());
        System.out.println("Date/Time: " + t.getTime());
        System.out.println("Treatment: " + t.getName());
        System.out.println("Doctor: Dr. " + t.getPhysio().getName());
        System.out.println("--------------------------------");
    }

    private void markAttended() {
        List<Treatment> booked = getBookedAppointments();
        if (booked.isEmpty()) {
            System.out.println("\n*********************");
            System.out.println("No booked appointments.");
            System.out.println("*********************");
            return;
        }

        for (int i = 0; i < booked.size(); i++) {
            System.out.println((i + 1) + ". " + booked.get(i));
        }
        System.out.println("\n*************************************");
        System.out.print("Select appointment to mark as attended: ");
        System.out.println("**************************************");
        int idx = scanner.nextInt() - 1;
        scanner.nextLine();
        if (idx < 0 || idx >= booked.size()) return;

        booked.get(idx).attend();
        System.out.println("\n******************************");
        System.out.println("Appointment marked as attended.");
        System.out.println("******************************");
    }

    private void showReport() {
        System.out.println("\n******************************");
        System.out.println("\n--- Appointment Report ---");
        System.out.println("******************************");
        Map<Physiotherapist, Integer> attendCount = new HashMap<>();

        for (Physiotherapist p : physios) {
            for (Treatment t : p.getTreatments()) {
                System.out.println(p.getName() + " | " + t);
                if (t.getStatus() == AppointmentStatus.ATTENDED) {
                    attendCount.put(p, attendCount.getOrDefault(p, 0) + 1);
                }
            }
        }
        System.out.println("\n*****************************************");
        System.out.println("\n--- Physios by Attended Appointments ---");
        System.out.println("*******************************************");
        physios.stream()
                .sorted((a, b) -> attendCount.getOrDefault(b, 0) - attendCount.getOrDefault(a, 0))
                .forEach(p -> System.out.println(p.getName() + ": " + attendCount.getOrDefault(p, 0)));
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

    private List<Treatment> getBookedAppointmentsForCurrentPatient() {
        List<Treatment> result = new ArrayList<>();
        for (Physiotherapist p : physios) {
            for (Treatment t : p.getTreatments()) {
                if (t.getStatus() == AppointmentStatus.BOOKED && 
                    t.getPatient() != null && 
                    t.getPatient().getId() == currentPatient.getId()) {
                    result.add(t);
                }
            }
        }
        return result;
    }

    
}