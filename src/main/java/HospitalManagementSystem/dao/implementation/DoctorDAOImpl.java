package main.java.HospitalManagementSystem.dao.implementation;

import main.java.HospitalManagementSystem.config.DatabaseConfig;
import main.java.HospitalManagementSystem.entity.DoctorDTO;
import main.java.HospitalManagementSystem.dao.database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static main.java.HospitalManagementSystem.dao.mapper.DoctorMapper.mapToDoctor;
import static main.java.HospitalManagementSystem.dao.mapper.DoctorMapper.mapToDoctorList;
import static main.java.HospitalManagementSystem.dao.query.DoctorQuery.INSERT_DOCTOR;
import static main.java.HospitalManagementSystem.dao.query.DoctorQuery.GET_DOCTOR_BY_ID;
import static main.java.HospitalManagementSystem.dao.query.DoctorQuery.GET_ALL_DOCTORS;
import static main.java.HospitalManagementSystem.dao.query.DoctorQuery.GET_DOCTOR_BY_SPECIALISATION;
import static main.java.HospitalManagementSystem.dao.query.DoctorQuery.UPDATE_DOCTOR;
import static main.java.HospitalManagementSystem.dao.query.DoctorQuery.DEACTIVATE_DOCTOR;

public class DoctorDAOImpl {

  private static final DatabaseConfig config = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(config);

  public boolean insertDoctor(DoctorDTO doctor) {

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DOCTOR)) {

      preparedStatement.setString(1, doctor.getName());
      preparedStatement.setInt(2, doctor.getSpecialisationId());
      preparedStatement.setInt(3, doctor.getYearsOfExperience());
      preparedStatement.setString(4, doctor.getShiftStart());
      preparedStatement.setString(5, doctor.getShiftEnd());

      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        System.out.println("Doctor added successfully!");
        return true;
      } else {
        System.err.println("Doctor insert failed for: " + doctor);
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while inserting Doctor: " + doctor);
      e.printStackTrace();
    }

    return false;

  }

  public Optional<DoctorDTO> getDoctorById(int id) {

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_DOCTOR_BY_ID)) {

      preparedStatement.setInt(1, id);

      try(ResultSet resultSet = preparedStatement.executeQuery()) {
        return mapToDoctor(resultSet);
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting Doctor info for id: " + id);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public Optional<List<DoctorDTO>> getDoctorBySpecialisation(Integer specialisationId) {

    if(Objects.isNull(specialisationId)) {
      return Optional.empty();
    }

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_DOCTOR_BY_SPECIALISATION)) {

      preparedStatement.setInt(1, specialisationId);

      try(ResultSet resultSet = preparedStatement.executeQuery()) {
        return mapToDoctorList(resultSet);
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting Doctor info for specialisationId: " + specialisationId);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public Optional<List<DoctorDTO>> getAllDoctors() {

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DOCTORS)) {

      try(ResultSet resultSet = preparedStatement.executeQuery()) {
        return mapToDoctorList(resultSet);
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred occurred while getting all Doctor info");
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public boolean updateDoctor(DoctorDTO updatedDoctor) {

    try (Connection connection = connectionManager.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DOCTOR)) {

      preparedStatement.setString(1, updatedDoctor.getName());
      preparedStatement.setInt(2, updatedDoctor.getSpecialisationId());
      preparedStatement.setInt(3, updatedDoctor.getYearsOfExperience());
      preparedStatement.setString(4, updatedDoctor.getShiftStart());
      preparedStatement.setString(5, updatedDoctor.getShiftEnd());
      preparedStatement.setInt(6, updatedDoctor.getIsActive());
      preparedStatement.setInt(7, updatedDoctor.getId());

      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        System.out.println("Doctor updated successfully!");
        return true;
      } else {
        System.err.println("Doctor updated failed for " + updatedDoctor);
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while inserting Doctor info for " + updatedDoctor);
      e.printStackTrace();
    }

    return false;

  }

  public boolean deactivateDoctor(int id) {

    try (Connection connection = connectionManager.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(DEACTIVATE_DOCTOR)) {

      preparedStatement.setInt(1, id);
      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        System.out.println("Doctor deactivated successfully!");
        return true;
      } else {
        System.err.println("Doctor deactivation failed for Doctor id: " + id);
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while deactivating Doctor id: " + id);
      e.printStackTrace();
    }

    return false;

  }

}
