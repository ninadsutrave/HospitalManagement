package main.java.HospitalManagementSystem.repository.dao;

import main.java.HospitalManagementSystem.config.DatabaseConfig;
import main.java.HospitalManagementSystem.repository.database.DatabaseConnectionManager;
import main.java.HospitalManagementSystem.repository.model.DoctorModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static main.java.HospitalManagementSystem.repository.mapper.DoctorMapper.mapToDoctorDAO;
import static main.java.HospitalManagementSystem.repository.mapper.DoctorMapper.mapToDoctorListDAO;
import static main.java.HospitalManagementSystem.repository.query.DoctorQuery.*;

public class DoctorDAO {

  private static final DatabaseConfig config = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(config);

  public static boolean insertDoctor(String name, String specialisation) {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DOCTOR);

      preparedStatement.setString(1, name);
      preparedStatement.setString(2, specialisation);

      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        System.out.println("Doctor added successfully!");
        return true;
      } else {
        System.err.println("Doctor insert failed for name: " + name + ", specialisation: " + specialisation);
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while inserting Doctor info for name: " + name + ", specialisation: " + specialisation);
      e.printStackTrace();
    }

    return false;

  }

  public static Optional<DoctorModel> getDoctorById(int id) {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_DOCTOR_BY_ID);

      preparedStatement.setInt(1, id);

      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()) {
        return mapToDoctorDAO(resultSet);
      } else {
        System.err.println("Get doctor failed for id: " + id);
        return Optional.empty();
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting Doctor info for id: " + id);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public static Optional<List<DoctorModel>> getAllDoctors() {

    try {
      Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DOCTORS);
      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()) {
        return mapToDoctorListDAO(resultSet);
      } else {
        System.err.println("Get all doctor query failed!");
        return Optional.empty();
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting all Doctor info");
      e.printStackTrace();
    }

    return Optional.empty();

  }

}
