package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.repository.implementation.AppointmentDAOImpl;

import java.util.Scanner;

public class AppointmentService {

  AppointmentDAOImpl appointmentDAOImpl;
  private final Scanner scanner = new Scanner(System.in);

  public void cancelAppointment() {

    System.out.println("CANCEL APPOINTMENT");

    System.out.print("Enter appointment id: ");
    int appointmentId = scanner.nextInt();

    appointmentDAOImpl.deleteAppointment(appointmentId);

  }

}
