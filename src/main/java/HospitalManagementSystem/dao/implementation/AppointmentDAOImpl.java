package main.java.HospitalManagementSystem.dao.implementation;

import main.java.HospitalManagementSystem.config.DatabaseConfig;
import main.java.HospitalManagementSystem.dao.interfaces.AppointmentDAO;
import main.java.HospitalManagementSystem.entity.AppointmentDTO;
import main.java.HospitalManagementSystem.dao.database.DatabaseConnectionManager;
import main.java.HospitalManagementSystem.entity.TimeRange;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.Statement;

import java.util.List;
import java.util.Optional;

import static main.java.HospitalManagementSystem.dao.mapper.AppointmentMapper.mapToAppointment;
import static main.java.HospitalManagementSystem.dao.mapper.AppointmentMapper.mapToAppointmentList;
import static main.java.HospitalManagementSystem.dao.query.AppointmentQuery.VIEW_APPOINTMENT;
import static main.java.HospitalManagementSystem.dao.query.AppointmentQuery.INSERT_APPOINTMENT;
import static main.java.HospitalManagementSystem.dao.query.AppointmentQuery.GET_PATIENT_APPOINTMENTS_FOR_DATE;
import static main.java.HospitalManagementSystem.dao.query.AppointmentQuery.GET_DOCTOR_APPOINTMENTS_FOR_DATE;
import static main.java.HospitalManagementSystem.dao.query.AppointmentQuery.UPDATE_APPOINTMENT;
import static main.java.HospitalManagementSystem.dao.query.AppointmentQuery.DELETE_APPOINTMENT;

public class AppointmentDAOImpl implements AppointmentDAO {

  private static final DatabaseConfig config = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(config);

  @Override
  public Optional<AppointmentDTO> getAppointmentById(int id) {

    try(Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(VIEW_APPOINTMENT)) {

      preparedStatement.setInt(1, id);

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        return mapToAppointment(resultSet);
      }

    } catch(SQLException e) {
      System.err.println("SQLException occurred while getting appointment details for Appointment id: " + id);
      e.printStackTrace();
      return Optional.empty();
    }

  }

  @Override
  public boolean insertAppointment(AppointmentDTO appointment) {

    try(Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_APPOINTMENT, Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setInt(1, appointment.getPatientId());
      preparedStatement.setInt(2, appointment.getDoctorId());
      preparedStatement.setDate(3, appointment.getDate());
      preparedStatement.setTime(4, appointment.getStartTime());
      preparedStatement.setTime(5, appointment.getEndTime());

      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            int generatedId = generatedKeys.getInt(1);
            System.out.println("Appointment created successfully with ID: " + generatedId);
          } else {
            System.err.println("Insertion succeeded but no ID returned.");
            return false;
          }
        }
        return true;
      } else {
        System.err.println("Appointment creation failed for doctorId: " + appointment.getDoctorId() + ", patientId: " + appointment.getPatientId() + ", appointmentDate: " + appointment.getDate());
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while creating Appointment info for doctorId: " + appointment.getDoctorId() + ", patientId: " + appointment.getPatientId() + ", appointmentDate: " + appointment.getDate());
      e.printStackTrace();
      return false;
    }

  }

  @Override
  public Optional<List<TimeRange>> getPatientAppointmentsForDate(int patientId, Date appointmentDate) {

    try(Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_PATIENT_APPOINTMENTS_FOR_DATE)) {

      preparedStatement.setInt(1, patientId);
      preparedStatement.setDate(2, appointmentDate);

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        return mapToAppointmentList(resultSet);
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting appointments for Patient id: " + patientId + " Appointment Date: " + appointmentDate);
      e.printStackTrace();
      return Optional.empty();
    }

  }

  @Override
  public Optional<List<TimeRange>> getDoctorAppointmentsForDate(int doctorId, Date appointmentDate) {

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_DOCTOR_APPOINTMENTS_FOR_DATE)) {

      preparedStatement.setInt(1, doctorId);
      preparedStatement.setDate(2, appointmentDate);

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        return mapToAppointmentList(resultSet);
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting appointments for Doctor id: " + doctorId + " Appointment Date: " + appointmentDate);
      e.printStackTrace();
      return Optional.empty();
    }

  }

  @Override
  public boolean updateAppointment(AppointmentDTO appointment) {

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_APPOINTMENT)) {

      preparedStatement.setDate(1, appointment.getDate());
      preparedStatement.setTime(2, appointment.getStartTime());
      preparedStatement.setTime(3, appointment.getEndTime());
      preparedStatement.setInt(4, appointment.getId());

      int rowsUpdated = preparedStatement.executeUpdate();

      if(rowsUpdated > 0) {
        System.out.println("Appointment rescheduled to your preferred slot!");
        return true;
      } else {
        System.err.println("Updating appointment query failed for Appointment id: " + appointment.getId());
        return false;
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred while updating Appointment id " + appointment.getId());
      e.printStackTrace();
      return false;
    }

  }

  @Override
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
      return false;
    }

  }

}
