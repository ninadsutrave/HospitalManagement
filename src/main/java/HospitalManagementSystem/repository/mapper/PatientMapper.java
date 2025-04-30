package main.java.HospitalManagementSystem.repository.mapper;

import main.java.HospitalManagementSystem.repository.dao.PatientDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientMapper {

  public static Optional<PatientDAO> mapToPatient(ResultSet queryResult) {

    try {

      PatientDAO patient = PatientDAO.builder()
        .id(queryResult.getInt("id"))
        .name(queryResult.getString("name"))
        .age(queryResult.getInt("age"))
        .gender(queryResult.getString("gender"))
        .phoneNumber(queryResult.getString("phone_number"))
        .build();

      return Optional.of(patient);

    } catch(SQLException e) {
      System.err.println("Error mapping Patient DAO for query result: " + queryResult);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public static Optional<List<PatientDAO>> mapToPatientList(ResultSet queryResult) {

    try {

      List<PatientDAO> patientList = new ArrayList<>();
      while(queryResult.next()) {
        patientList.add(PatientDAO.builder()
          .id(queryResult.getInt("id"))
          .name(queryResult.getString("name"))
          .age(queryResult.getInt("age"))
          .gender(queryResult.getString("gender"))
          .phoneNumber(queryResult.getString("phone_number"))
          .build());
      }

      return Optional.of(patientList);

    } catch(SQLException e) {
      System.err.println("Error mapping Patient DAO for query result: " + queryResult);
      e.printStackTrace();
    }

    return Optional.empty();

  }

}
