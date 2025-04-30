package main.java.HospitalManagementSystem.repository.mapper;

import main.java.HospitalManagementSystem.repository.dao.DoctorDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorMapper {

  public static Optional<DoctorDAO> mapToDoctor(ResultSet queryResult) {

    try {

      DoctorDAO doctor = DoctorDAO.builder()
        .id(queryResult.getInt("id"))
        .name(queryResult.getString("name"))
        .specialisation(queryResult.getString("specialisation"))
        .yearsOfExperience(queryResult.getInt("years_of_experience"))
        .shiftStart(queryResult.getString("shift_start"))
        .shiftEnd(queryResult.getString("shift_end"))
        .build();

      return Optional.of(doctor);

    } catch(SQLException e) {
      System.err.println("Error mapping Doctor DAO for query result: " + queryResult);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public static Optional<List<DoctorDAO>> mapToDoctorList(ResultSet queryResult) {

    try {

      List<DoctorDAO> doctorList = new ArrayList<>();
      while(queryResult.next()) {
        doctorList.add(DoctorDAO.builder()
          .id(queryResult.getInt("id"))
          .name(queryResult.getString("name"))
          .specialisation(queryResult.getString("specialisation"))
          .yearsOfExperience(queryResult.getInt("years_of_experience"))
          .shiftStart(queryResult.getString("shift_start"))
          .shiftEnd(queryResult.getString("shift_end"))
          .build());
      }

      return Optional.of(doctorList);

    } catch(SQLException e) {
      System.err.println("Error mapping Doctor DAO for query result: " + queryResult);
      e.printStackTrace();
    }

    return Optional.empty();

  }

}
