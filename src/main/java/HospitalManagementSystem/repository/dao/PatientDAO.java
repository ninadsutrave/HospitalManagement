package main.java.HospitalManagementSystem.repository.dao;

import main.java.HospitalManagementSystem.config.DatabaseConfig;
import main.java.HospitalManagementSystem.repository.database.DatabaseConnectionManager;
import main.java.HospitalManagementSystem.repository.model.PatientModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static main.java.HospitalManagementSystem.repository.mapper.PatientMapper.mapToPatientDAO;
import static main.java.HospitalManagementSystem.repository.mapper.PatientMapper.mapToPatientListDAO;
import static main.java.HospitalManagementSystem.repository.query.PatientQuery.INSERT_PATIENT;
import static main.java.HospitalManagementSystem.repository.query.PatientQuery.GET_PATIENT_BY_ID;
import static main.java.HospitalManagementSystem.repository.query.PatientQuery.GET_ALL_PATIENTS;

public class PatientDAO {

  private static final DatabaseConfig config = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(config);

  public static boolean insertPatient(String name, int age, String gender) {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT);

      preparedStatement.setString(1, name);
      preparedStatement.setInt(2, age);
      preparedStatement.setString(3, gender);

      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        System.out.println("Patient added successfully!");
        return true;
      } else {
        System.err.println("Patient insert failed for name: " + name + ", age: " + age + ", gender: " + gender);
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while inserting Patient info for name: " + name + ", age: " + age + ", gender: " + gender);
      e.printStackTrace();
    }

    return false;

  }

  public static Optional<PatientModel> getPatientById(int id) {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_PATIENT_BY_ID);

      preparedStatement.setInt(1, id);

      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()) {
        return mapToPatientDAO(resultSet);
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

  public static Optional<List<PatientModel>> getAllPatients() {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PATIENTS);
      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()) {
        return mapToPatientListDAO(resultSet);
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
