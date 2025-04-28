package main.java.HospitalManagementSystem.repository.mapper;

import main.java.HospitalManagementSystem.repository.model.DoctorModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorMapper {

  public static Optional<DoctorModel> mapToDoctorDAO(ResultSet queryResult) {

    try {

      DoctorModel doctor = DoctorModel.builder()
        .id(queryResult.getInt("id"))
        .name(queryResult.getString("name"))
        .specialisation(queryResult.getString("specialisation"))
        .build();

      return Optional.of(doctor);

    } catch(SQLException e) {
      System.err.println("Error mapping Doctor Model for query result: " + queryResult);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public static Optional<List<DoctorModel>> mapToDoctorListDAO(ResultSet queryResult) {

    try {

      List<DoctorModel> doctorList = new ArrayList<>();
      while(queryResult.next()) {
        doctorList.add(DoctorModel.builder()
          .id(queryResult.getInt("id"))
          .name(queryResult.getString("name"))
          .specialisation(queryResult.getString("specialisation"))
          .build());
      }

      return Optional.of(doctorList);

    } catch(SQLException e) {
      System.err.println("Error mapping Doctor Model for query result: " + queryResult);
      e.printStackTrace();
    }

    return Optional.empty();

  }

}
