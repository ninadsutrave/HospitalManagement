package main.java.HospitalManagementSystem.manager;

import main.java.HospitalManagementSystem.service.AppointmentService;
import main.java.HospitalManagementSystem.service.DoctorService;
import main.java.HospitalManagementSystem.service.PatientService;
import main.java.HospitalManagementSystem.service.SpecialisationService;
import main.java.HospitalManagementSystem.util.InputUtil;

public class MenuManager {

  PatientService patientService;
  DoctorService doctorService;
  SpecialisationService specialisationService;
  AppointmentService appointmentService;

  public MenuManager() {
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

      int choice = InputUtil.readInt("Enter your choice: ");

      switch (choice) {
        case 1: showAdminMenu();
          break;
        case 2: showPatientMenu();
          break;
        case 3: return;
        default: System.err.println("Enter a valid choice!\n");
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
      System.out.println("9. Logout\n");

      int choice = InputUtil.readInt("Enter your choice: ");

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
        case 9: return;
        default: System.err.println("Enter a valid choice!\n");
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

      int choice = InputUtil.readInt("Enter your choice: ");

      switch (choice) {
        case 1: patientService.registerPatient();
          break;
        case 2: Integer specialisationId = specialisationService.getSpecialisationChoice();
          doctorService.viewDoctors(specialisationId);
          break;
        case 3: appointmentService.bookAppointment();
          break;
        case 4: appointmentService.rescheduleAppointment();
          break;
        case 5: appointmentService.cancelAppointment();
          break;
        case 6: return;
        default: System.err.println("Enter a valid choice!\n");
      }

    }

  }

}
