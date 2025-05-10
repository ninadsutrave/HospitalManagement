package main.java.HospitalManagementSystem.dao.implementation;

import main.java.HospitalManagementSystem.config.DatabaseConfig;
import main.java.HospitalManagementSystem.dao.interfaces.DoctorDAO;
import main.java.HospitalManagementSystem.entity.DoctorDTO;
import main.java.HospitalManagementSystem.dao.database.DatabaseConnectionManager;
import main.java.HospitalManagementSystem.entity.TimeRange;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import static main.java.HospitalManagementSystem.dao.mapper.DoctorMapper.mapToDoctor;
import static main.java.HospitalManagementSystem.dao.mapper.DoctorMapper.mapToDoctorList;
import static main.java.HospitalManagementSystem.dao.query.DoctorQuery.*;

public class DoctorDAOImpl implements DoctorDAO {

  private static final DatabaseConfig config = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(config);

  public boolean insertDoctor(DoctorDTO doctor) {

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DOCTOR, Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setString(1, doctor.getName());
      preparedStatement.setInt(2, doctor.getSpecialisationId());
      preparedStatement.setInt(3, doctor.getYearsOfExperience());
      preparedStatement.setTime(4, doctor.getShiftStart());
      preparedStatement.setTime(5, doctor.getShiftEnd());

      int updatedRows = preparedStatement.executeUpdate();

      if(updatedRows > 0) {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            int generatedId = generatedKeys.getInt(1);
            System.out.println("Doctor added successfully with ID: " + generatedId);
          } else {
            System.err.println("Insertion succeeded but no ID returned.");
            return false;
          }
        }
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

    if(specialisationId == null) {
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

  public Optional<TimeRange> getDoctorShift(int id) {

    try (Connection connection = connectionManager.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(GET_DOCTOR_SHIFT)) {
         preparedStatement.setInt(1, id);

      try(ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet == null || resultSet.isClosed() || !resultSet.next()) {
          return Optional.empty();
        }

        TimeRange doctorShift = TimeRange.builder()
          .startTime(resultSet.getTime("shift_start_time"))
          .endTime(resultSet.getTime("shift_end_time"))
          .build();

        return Optional.of(doctorShift);
      }

    } catch(SQLException e) {
      System.err.println("SQLException occurred while getting Doctor shift info for Doctor id: " + id);
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
      preparedStatement.setTime(4, updatedDoctor.getShiftStart());
      preparedStatement.setTime(5, updatedDoctor.getShiftEnd());
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
      System.err.println("SQLException occurred while inserting Doctor info for " + updatedDoctor);
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
      System.err.println("SQLException occurred while deactivating Doctor id: " + id);
      e.printStackTrace();
    }

    return false;

  }

}
