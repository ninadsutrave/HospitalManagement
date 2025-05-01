package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.dao.implementation.AppointmentDAOImpl;

import java.util.Scanner;

public class AppointmentService {

  AppointmentDAOImpl appointmentDAOImpl;
  private final Scanner scanner = new Scanner(System.in);

  public AppointmentService() {
    appointmentDAOImpl = new AppointmentDAOImpl();
  }

  public void cancelAppointment() {

    System.out.println("CANCEL APPOINTMENT");

    System.out.print("Enter appointment id: ");
    int appointmentId = scanner.nextInt();

    appointmentDAOImpl.deleteAppointment(appointmentId);

  }

}
