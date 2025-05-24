package main.java.HospitalManagementSystem.dao.implementation;

import main.java.HospitalManagementSystem.config.DatabaseConfig;
import main.java.HospitalManagementSystem.dao.interfaces.PatientDAO;
import main.java.HospitalManagementSystem.entity.PatientDTO;
import main.java.HospitalManagementSystem.dao.database.DatabaseConnectionManager;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

import static main.java.HospitalManagementSystem.dao.mapper.PatientMapper.mapToPatient;
import static main.java.HospitalManagementSystem.dao.query.PatientQuery.INSERT_PATIENT;
import static main.java.HospitalManagementSystem.dao.query.PatientQuery.GET_PATIENT_BY_ID;
import static main.java.HospitalManagementSystem.dao.query.PatientQuery.UPDATE_PATIENT;
import static main.java.HospitalManagementSystem.dao.query.PatientQuery.DEACTIVATE_PATIENT;

public class PatientDAOImpl implements PatientDAO {

  private static final DatabaseConfig config = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(config);

  @Override
  public boolean insertPatient(PatientDTO patient) {

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT, Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setString(1, patient.getName());
      preparedStatement.setDate(2, patient.getDateOfBirth());
      preparedStatement.setString(3, patient.getGender().toString());
      preparedStatement.setString(4, patient.getPhoneNumber());

      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            int generatedId = generatedKeys.getInt(1);
            System.out.println("Patient added successfully with ID: " + generatedId);
          } else {
            System.err.println("Insertion succeeded but no ID returned.");
            return false;
          }
        }
        return true;
      } else {
        System.err.println("Patient insert failed for name: " + patient);
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while inserting Patient info for name: " + patient);
      e.printStackTrace();
      return false;
    }

  }

  @Override
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
      return Optional.empty();
    }

  }

  @Override
  public boolean updatePatient(PatientDTO updatedPatient) {

    try (Connection connection = connectionManager.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PATIENT)) {

      preparedStatement.setString(1, updatedPatient.getName());
      preparedStatement.setDate(2, updatedPatient.getDateOfBirth());
      preparedStatement.setString(3, updatedPatient.getGender().toString());
      preparedStatement.setString(4, updatedPatient.getPhoneNumber());
      preparedStatement.setInt(5, updatedPatient.getIsActive());
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
      return false;
    }

  }

  @Override
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
      return false;
    }

  }

}
