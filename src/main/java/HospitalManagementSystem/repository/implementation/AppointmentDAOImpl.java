package main.java.HospitalManagementSystem.repository.implementation;

import main.java.HospitalManagementSystem.config.DatabaseConfig;
import main.java.HospitalManagementSystem.entity.AppointmentDTO;
import main.java.HospitalManagementSystem.repository.database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static main.java.HospitalManagementSystem.repository.mapper.AppointmentMapper.mapToAppointmentList;
import static main.java.HospitalManagementSystem.repository.query.AppointmentQuery.INSERT_APPOINTMENT;
import static main.java.HospitalManagementSystem.repository.query.AppointmentQuery.GET_PATIENT_APPOINTMENTS_FOR_DATE;
import static main.java.HospitalManagementSystem.repository.query.AppointmentQuery.GET_DOCTOR_APPOINTMENTS_FOR_DATE;
import static main.java.HospitalManagementSystem.repository.query.AppointmentQuery.UPDATE_APPOINTMENT;
import static main.java.HospitalManagementSystem.repository.query.AppointmentQuery.DELETE_APPOINTMENT;

public class AppointmentDAOImpl {

  private static final DatabaseConfig config = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(config);

  public boolean insertAppointment(AppointmentDTO appointment) {

    try(Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_APPOINTMENT)) {

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

  public Optional<List<AppointmentDTO>> getPatientAppointmentsForDate(int patientId, String appointmentDate) {

    try(Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_PATIENT_APPOINTMENTS_FOR_DATE)) {

      preparedStatement.setInt(1, patientId);
      preparedStatement.setString(2, appointmentDate);

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        return mapToAppointmentList(resultSet);
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting number of appointments for Patient id: " + patientId + " Appointment Date: " + appointmentDate);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public Optional<List<AppointmentDTO>> getDoctorAppointmentsForDate(int doctorId, String appointmentDate) {

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_DOCTOR_APPOINTMENTS_FOR_DATE)) {

      preparedStatement.setInt(1, doctorId);
      preparedStatement.setString(2, appointmentDate);

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        return mapToAppointmentList(resultSet);
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting number of appointments for Doctor id: " + doctorId + " Appointment Date: " + appointmentDate);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public boolean updateAppointment(AppointmentDTO appointment) {

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_APPOINTMENT)) {

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

  public boolean deleteAppointment(int id) {

    try (Connection connection = connectionManager.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(DELETE_APPOINTMENT)) {

      preparedStatement.setInt(1, id);
      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        System.out.println("Appointment deleted successfully!");
        return true;
      } else {
        System.err.println("Appointment deletion failed for Appointment id: " + id);
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while deleting Appointment id: " + id);
      e.printStackTrace();
    }

    return false;

  }

}
