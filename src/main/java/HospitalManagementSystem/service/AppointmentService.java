package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.dao.implementation.AppointmentDAOImpl;
import main.java.HospitalManagementSystem.dao.interfaces.AppointmentDAO;
import main.java.HospitalManagementSystem.util.InputUtil;

public class AppointmentService {

  AppointmentDAO appointmentDAOImpl;

  public AppointmentService() {
    appointmentDAOImpl = new AppointmentDAOImpl();
  }

  public void cancelAppointment() {

    System.out.println("CANCEL APPOINTMENT");
    int appointmentId = InputUtil.readInt("Enter appointment id: ");

    appointmentDAOImpl.deleteAppointment(appointmentId);

  }

}
