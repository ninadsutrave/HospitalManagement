package main.java.HospitalManagementSystem.service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuService {

  PatientService patientService;
  DoctorService doctorService;
  SpecialisationService specialisationService;
  AppointmentService appointmentService;
  private static final Scanner scanner = new Scanner(System.in);

  public MenuService() {
    patientService = new PatientService();
    doctorService = new DoctorService();
    specialisationService = new SpecialisationService();
    appointmentService = new AppointmentService();
  }

  public void showWelcomeScreen() {

    while(true) {
      System.out.println("\nHOSPITAL MANAGEMENT SYSTEM");

      System.out.println("Log in as: ");
      System.out.println("1. Admin");
      System.out.println("2. Patient");
      System.out.println("3. Exit\n");

      try {
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
          case 1: showAdminMenu();
                  break;
          case 2: showPatientMenu();
                  break;
          case 3: return;
          default: System.err.println("Enter a correct choice!\n");
        }
      } catch(InputMismatchException e) {
        System.err.println("Invalid input!");
        e.printStackTrace();
      }

    }

  }

  public void showAdminMenu() {

    while(true) {
      System.out.println("\nHOSPITAL MANAGEMENT SYSTEM\nHello Admin!\n");

      System.out.println("1. Add Patient");
      System.out.println("2. View Patient");
      System.out.println("3. Edit Patient");
      System.out.println("4. Remove Patient");
      System.out.println("5. Add Doctor");
      System.out.println("6. View Doctor");
      System.out.println("7. Edit Doctor");
      System.out.println("8. Remove Doctor");
      System.out.println("9. Get Doctor's Report");
      System.out.println("10. Logout\n");

      try {
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
          case 1: patientService.registerPatient();
                  break;
          case 2: patientService.viewPatient();
                  break;
          case 3: patientService.editPatient();
                  break;
          case 4: patientService.removePatient();
                  break;
          case 5: doctorService.addDoctor();
                  break;
          case 6: doctorService.viewDoctorById();
                  break;
          case 7: doctorService.editDoctor();
                  break;
          case 8: doctorService.removeDoctor();
                  break;
          case 9: doctorService.getDoctorReport();
                  break;
          case 10: return;
          default: System.err.println("Enter a correct choice!\n");
        }
      } catch(InputMismatchException e) {
        System.err.println("Invalid input!");
        e.printStackTrace();
      }

    }

  }

  public void showPatientMenu() {

    while(true) {
      System.out.println("\nHOSPITAL MANAGEMENT SYSTEM\nHello Patient!\n");

      System.out.println("1. Register");
      System.out.println("2. View Doctors");
      System.out.println("3. Book Appointment");
      System.out.println("4. Reschedule Appointment");
      System.out.println("5. Cancel Appointment");
      System.out.println("6. Logout\n");

      try {
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
          case 1: patientService.registerPatient();
                  break;
          case 2: Integer specialisationId = specialisationService.getSpecialisationChoice();
                  doctorService.viewDoctors(specialisationId);
                  break;
          case 3: appointmentService.bookAppointment();
                  break;
          case 4: break;
          case 5: appointmentService.cancelAppointment();
                  break;
          case 6: return;
          default: System.err.println("Enter a correct choice!\n");
        }
      } catch(InputMismatchException e) {
        System.err.println("Invalid input!");
        e.printStackTrace();
      }

    }

  }

}
