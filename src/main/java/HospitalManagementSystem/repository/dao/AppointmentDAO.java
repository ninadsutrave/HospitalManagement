package main.java.HospitalManagementSystem.repository.dao;

import main.java.HospitalManagementSystem.config.DatabaseConfig;
import main.java.HospitalManagementSystem.repository.database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static main.java.HospitalManagementSystem.repository.query.AppointmentQuery.GET_NUMBER_OF_DOCTOR_APPOINTMENTS_ON_DATE;
import static main.java.HospitalManagementSystem.repository.query.AppointmentQuery.INSERT_APPOINTMENT;

public class AppointmentDAO {

  private static final DatabaseConfig config = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(config);

  public static boolean insertAppointment(int patientId, int doctorId, String appointmentDate) {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_APPOINTMENT);

      preparedStatement.setInt(1, patientId);
      preparedStatement.setInt(2, doctorId);
      preparedStatement.setString(3, appointmentDate);

      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        System.out.println("Appointment created successfully!");
        return true;
      } else {
        System.err.println("Appointment creation failed for doctorId: " + doctorId + ", patientId: " + patientId + ", aapointmentDate: " + appointmentDate);
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while creating Appointment info for doctorId: " + doctorId + ", patientId: " + patientId + ", aapointmentDate: " + appointmentDate);
      e.printStackTrace();
    }

    return false;

  }

  public static Optional<Integer> getNumberOfDoctorAppointmentsOnDate(int doctorId, String appointmentDate) {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_NUMBER_OF_DOCTOR_APPOINTMENTS_ON_DATE);
      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()) {
        return Optional.of(resultSet.getInt("count"));
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

}
