package main.java.HospitalManagementSystem.repository.mapper;

import main.java.HospitalManagementSystem.repository.dao.SpecialisationDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpecialisationMapper {

  public static Optional<List<SpecialisationDAO>> mapToSpecialisationList(ResultSet queryResult) {

    try {

      List<SpecialisationDAO> specialisationList = new ArrayList<>();
      while(queryResult.next()) {
        SpecialisationDAO specialisation = SpecialisationDAO.builder()
          .id(queryResult.getInt("id"))
          .name(queryResult.getString("name"))
          .build();

        specialisationList.add(specialisation);
      }

      return Optional.of(specialisationList);

    } catch(SQLException e) {
      System.err.println("Error mapping Specialisation DAO for query result: " + queryResult);
      e.printStackTrace();
    }

    return Optional.empty();

  }

}
