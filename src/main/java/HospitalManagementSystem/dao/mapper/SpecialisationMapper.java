package main.java.HospitalManagementSystem.dao.mapper;

import main.java.HospitalManagementSystem.entity.SpecialisationDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpecialisationMapper {

  public static Optional<List<SpecialisationDTO>> mapToSpecialisationList(ResultSet resultSet) {

    List<SpecialisationDTO> specialisationList = new ArrayList<>();
    try {

      if (resultSet == null || resultSet.isClosed()) {
        return Optional.empty();
      }

      while(resultSet.next()) {
        SpecialisationDTO specialisation = SpecialisationDTO.builder()
          .id(resultSet.getInt("id"))
          .name(resultSet.getString("name"))
          .build();

        specialisationList.add(specialisation);
      }

    } catch(SQLException e) {
      System.err.println("SQLException occurred while mapping Specialisation DTO for query result: " + resultSet);
      e.printStackTrace();
    }

    return specialisationList.isEmpty() ? Optional.empty() : Optional.of(specialisationList);

  }

}
