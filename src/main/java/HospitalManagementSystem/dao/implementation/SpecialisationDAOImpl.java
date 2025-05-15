package main.java.HospitalManagementSystem.dao.implementation;

import main.java.HospitalManagementSystem.config.DatabaseConfig;
import main.java.HospitalManagementSystem.dao.interfaces.SpecialisationDAO;
import main.java.HospitalManagementSystem.entity.SpecialisationDTO;
import main.java.HospitalManagementSystem.dao.database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static main.java.HospitalManagementSystem.dao.mapper.SpecialisationMapper.mapToSpecialisationList;
import static main.java.HospitalManagementSystem.dao.query.SpecialisationQuery.GET_SPECIALISATION_LIST;

public class SpecialisationDAOImpl implements SpecialisationDAO {

  private static final DatabaseConfig config = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(config);

  public Optional<List<SpecialisationDTO>> getSpecialisationList() {

    try (Connection connection = connectionManager.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_SPECIALISATION_LIST)) {

      try(ResultSet resultSet = preparedStatement.executeQuery()) {
        return mapToSpecialisationList(resultSet);
      }

    } catch(SQLException e) {
      System.err.println("SQLException occurred occurred while getting specialisation list");
      e.printStackTrace();
      return Optional.empty();
    }

  }

}
