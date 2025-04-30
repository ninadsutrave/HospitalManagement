package main.java.HospitalManagementSystem.service;

import java.util.Scanner;

public class MenuService {

  PatientService patientService;
  DoctorService doctorService;
  SpecialisationService specialisationService;
  AppointmentService appointmentService;

  public void welcomeScreen() {

    Scanner scanner = new Scanner(System.in);

    while(true) {
      System.out.println("HOSPITAL MANAGEMENT SYSTEM");

      System.out.println("Log in as: ");
      System.out.println("1. Admin");
      System.out.println("2. Patient");
      System.out.println("3. Exit");

      int choice = scanner.nextInt();

      switch(choice) {
        case 1: showAdminMenu();
                break;
        case 2: showPatientMenu();
                break;
        default: System.err.println("Enter a correct choice!");
      }

    }

  }

  public void showAdminMenu() {

    Scanner scanner = new Scanner(System.in);

    while(true) {
      System.out.println("HOSPITAL MANAGEMENT SYSTEM");

      System.out.println("1. Add Patient");
      System.out.println("2. View Patient");
      System.out.println("3. Edit Patient");
      System.out.println("4. Remove Patient");
      System.out.println("5. Add Doctor");
      System.out.println("6. View Doctor");
      System.out.println("7. Edit Doctor");
      System.out.println("8. Remove Doctor");
      System.out.println("9. Exit");

      int choice = scanner.nextInt();

      switch(choice) {
        case 1: break;
        case 2: break;
        case 3: break;
        case 4: break;
        case 5: break;
        case 6: break;
        case 7: break;
        case 8: break;
        default: System.err.println("Enter a correct choice!");
      }

    }

  }

  public void showPatientMenu() {

    Scanner scanner = new Scanner(System.in);

    while(true) {
      System.out.println("HOSPITAL MANAGEMENT SYSTEM");

      System.out.println("1. Register");
      System.out.println("2. View Doctors");
      System.out.println("2. Book Appointment");
      System.out.println("3. Reschedule Appointment");
      System.out.println("4. Exit");

      int choice = scanner.nextInt();

      switch(choice) {
        case 1: patientService.registerPatient();
                break;
        case 2: specialisationService.viewSpecialisationList();
                doctorService.viewDoctors();
                break;
        case 3: break;
        case 4: break;
        default: System.err.println("Enter a correct choice!");
      }

    }

  }

}
