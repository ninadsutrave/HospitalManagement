package main.java.HospitalManagementSystem.repository.implementation;

import main.java.HospitalManagementSystem.config.DatabaseConfig;
import main.java.HospitalManagementSystem.entity.PatientDTO;
import main.java.HospitalManagementSystem.repository.dao.PatientDAO;
import main.java.HospitalManagementSystem.repository.database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static main.java.HospitalManagementSystem.repository.mapper.PatientMapper.*;
import static main.java.HospitalManagementSystem.repository.query.PatientQuery.INSERT_PATIENT;
import static main.java.HospitalManagementSystem.repository.query.PatientQuery.GET_PATIENT_BY_ID;
import static main.java.HospitalManagementSystem.repository.query.PatientQuery.GET_ALL_PATIENTS;

public class PatientDAOImpl {

  private static final DatabaseConfig config = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(config);

  public boolean insertPatient(PatientDTO patientDTO) {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT);

      preparedStatement.setString(1, patientDTO.getName());
      preparedStatement.setInt(2, patientDTO.getAge());
      preparedStatement.setString(3, patientDTO.getGender());
      preparedStatement.setString(4, patientDTO.getPhoneNumber());

      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        System.out.println("Patient added successfully!");
        return true;
      } else {
        System.err.println("Patient insert failed for name: " + patientDTO.getName() + ", age: " + patientDTO.getAge() + ", gender: " + patientDTO.getGender() + ", phoneNumber: " + patientDTO.getPhoneNumber());
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while inserting Patient info for name: " + patientDTO.getName() + ", age: " + patientDTO.getAge() + ", gender: " + patientDTO.getGender() + ", phoneNumber: " + patientDTO.getPhoneNumber());
      e.printStackTrace();
    }

    return false;

  }

  public static Optional<PatientDAO> getPatientById(int id) {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_PATIENT_BY_ID);

      preparedStatement.setInt(1, id);

      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()) {
        return mapToPatient(resultSet);
      } else {
        System.err.println("Get patient failed for id: " + id);
        return Optional.empty();
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting Patient info for id: " + id);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public static Optional<List<PatientDAO>> getAllPatients() {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PATIENTS);
      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()) {
        return mapToPatientList(resultSet);
      } else {
        System.err.println("Get all patient query failed!");
        return Optional.empty();
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting all Patient info");
      e.printStackTrace();
    }

    return Optional.empty();

  }

}
