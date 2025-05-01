package main.java.HospitalManagementSystem.repository.implementation;

import main.java.HospitalManagementSystem.config.DatabaseConfig;
import main.java.HospitalManagementSystem.entity.PatientDTO;
import main.java.HospitalManagementSystem.repository.database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static main.java.HospitalManagementSystem.repository.mapper.PatientMapper.mapToPatient;
import static main.java.HospitalManagementSystem.repository.mapper.PatientMapper.mapToPatientList;
import static main.java.HospitalManagementSystem.repository.query.PatientQuery.INSERT_PATIENT;
import static main.java.HospitalManagementSystem.repository.query.PatientQuery.GET_PATIENT_BY_ID;
import static main.java.HospitalManagementSystem.repository.query.PatientQuery.GET_ALL_PATIENTS;
import static main.java.HospitalManagementSystem.repository.query.PatientQuery.UPDATE_PATIENT;
import static main.java.HospitalManagementSystem.repository.query.PatientQuery.DEACTIVATE_PATIENT;

public class PatientDAOImpl {

  private static final DatabaseConfig config = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(config);

  public boolean insertPatient(PatientDTO patient) {

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT)) {

      preparedStatement.setString(1, patient.getName());
      preparedStatement.setInt(2, patient.getAge());
      preparedStatement.setString(3, patient.getGender());
      preparedStatement.setString(4, patient.getPhoneNumber());

      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        System.out.println("Patient added successfully!");
        return true;
      } else {
        System.err.println("Patient insert failed for name: " + patient);
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while inserting Patient info for name: " + patient);
      e.printStackTrace();
    }

    return false;

  }

  public Optional<PatientDTO> getPatientById(int id) {

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_PATIENT_BY_ID)) {

      preparedStatement.setInt(1, id);

      try(ResultSet resultSet = preparedStatement.executeQuery()) {
        return mapToPatient(resultSet);
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting Patient info for id: " + id);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public Optional<List<PatientDTO>> getAllPatients() {

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PATIENTS)) {

      try(ResultSet resultSet = preparedStatement.executeQuery()) {
        return mapToPatientList(resultSet);
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting all Patient info");
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public boolean updatePatient(PatientDTO updatedPatient) {

    try (Connection connection = connectionManager.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PATIENT)) {

      preparedStatement.setString(1, updatedPatient.getName());
      preparedStatement.setInt(2, updatedPatient.getAge());
      preparedStatement.setString(3, updatedPatient.getGender());
      preparedStatement.setString(4, updatedPatient.getPhoneNumber());
      preparedStatement.setBoolean(5, updatedPatient.getIsActive());
      preparedStatement.setInt(6, updatedPatient.getId());

      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        System.out.println("Patient updated successfully!");
        return true;
      } else {
        System.err.println("Patient updated failed for " + updatedPatient);
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while inserting Patient info for " + updatedPatient);
      e.printStackTrace();
    }

    return false;

  }

  public boolean deactivatePatient(int id) {

    try (Connection connection = connectionManager.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(DEACTIVATE_PATIENT)) {

      preparedStatement.setInt(1, id);
      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        System.out.println("Patient deactivated successfully!");
        return true;
      } else {
        System.err.println("Patient deactivation failed for Patient id: " + id);
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while deactivating Patient id: " + id);
      e.printStackTrace();
    }

    return false;

  }

}
