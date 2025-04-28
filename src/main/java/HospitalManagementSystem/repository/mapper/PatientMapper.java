package main.java.HospitalManagementSystem.repository.mapper;

import main.java.HospitalManagementSystem.repository.model.PatientModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientMapper {

  public static Optional<PatientModel> mapToPatientDAO(ResultSet queryResult) {

    try {

      PatientModel patient = PatientModel.builder()
        .id(queryResult.getInt("id"))
        .name(queryResult.getString("name"))
        .age(queryResult.getInt("age"))
        .gender(queryResult.getString("gender"))
        .build();

      return Optional.of(patient);

    } catch(SQLException e) {
      System.err.println("Error mapping Doctor Model for query result: " + queryResult);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public static Optional<List<PatientModel>> mapToPatientListDAO(ResultSet queryResult) {

    try {

      List<PatientModel> doctorList = new ArrayList<>();
      while(queryResult.next()) {
        doctorList.add(PatientModel.builder()
          .id(queryResult.getInt("id"))
          .name(queryResult.getString("name"))
          .age(queryResult.getInt("age"))
          .gender(queryResult.getString("gender"))
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
