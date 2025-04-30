package main.java.HospitalManagementSystem.repository.implementation;

import main.java.HospitalManagementSystem.config.DatabaseConfig;
import main.java.HospitalManagementSystem.entity.AppointmentDTO;
import main.java.HospitalManagementSystem.repository.dao.AppointmentDAO;
import main.java.HospitalManagementSystem.repository.database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static main.java.HospitalManagementSystem.repository.mapper.AppointmentMapper.mapToAppointmentList;
import static main.java.HospitalManagementSystem.repository.query.AppointmentQuery.*;

public class AppointmentDAOImpl {

  private static final DatabaseConfig config = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(config);

  public static boolean insertAppointment(AppointmentDTO appointment) {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_APPOINTMENT);

      preparedStatement.setInt(1, appointment.getPatientId());
      preparedStatement.setInt(2, appointment.getDoctorId());
      preparedStatement.setString(3, appointment.getDate());

      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        System.out.println("Appointment created successfully!");
        return true;
      } else {
        System.err.println("Appointment creation failed for doctorId: " + appointment.getDoctorId() + ", patientId: " + appointment.getPatientId() + ", appointmentDate: " + appointment.getDate());
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while creating Appointment info for doctorId: " + appointment.getDoctorId() + ", patientId: " + appointment.getPatientId() + ", appointmentDate: " + appointment.getDate());
      e.printStackTrace();
    }

    return false;

  }

  public static Optional<List<AppointmentDAO>> getPatientAppointmentsForDate(int patientId, String appointmentDate) {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_PATIENT_APPOINTMENTS_FOR_DATE);

      preparedStatement.setInt(1, patientId);
      preparedStatement.setString(2, appointmentDate);

      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()) {
        return mapToAppointmentList(resultSet);
      } else {
        System.err.println("Get number of appointments query failed for Patient id: " + patientId + " Appointment Date: " + appointmentDate);
        return Optional.empty();
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting number of appointments for Patient id: " + patientId + " Appointment Date: " + appointmentDate);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public static Optional<List<AppointmentDAO>> getDoctorAppointmentsForDate(int doctorId, String appointmentDate) {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_DOCTOR_APPOINTMENTS_FOR_DATE);

      preparedStatement.setInt(1, doctorId);
      preparedStatement.setString(2, appointmentDate);

      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()) {
        return mapToAppointmentList(resultSet);
      } else {
        System.err.println("Get number of appointments query failed for Doctor id: " + doctorId + " Appointment Date: " + appointmentDate);
        return Optional.empty();
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting number of appointments for Doctor id: " + doctorId + " Appointment Date: " + appointmentDate);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public static boolean updateAppointment(AppointmentDTO appointment) {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_APPOINTMENT);

      preparedStatement.setString(1, appointment.getDate());
      preparedStatement.setString(2, appointment.getStartTime());
      preparedStatement.setString(3, appointment.getEndTime());

      int rowsUpdated = preparedStatement.executeUpdate();

      if(rowsUpdated > 0) {
        return true;
      } else {
        System.err.println("Updating appointment query failed for Appointment id: " + appointment.getId());
        return false;
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred while updating Appointment id " + appointment.getId());
      e.printStackTrace();
    }

    return false;

  }

}
