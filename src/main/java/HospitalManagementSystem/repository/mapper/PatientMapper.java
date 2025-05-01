package main.java.HospitalManagementSystem.repository.mapper;

import main.java.HospitalManagementSystem.entity.PatientDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientMapper {

  public static Optional<PatientDTO> mapToPatient(ResultSet resultSet) {

    try {

      if (resultSet == null || resultSet.isClosed() || !resultSet.next()) {
        return Optional.empty();
      }

      PatientDTO patient = PatientDTO.builder()
        .id(resultSet.getInt("id"))
        .name(resultSet.getString("name"))
        .age(resultSet.getInt("age"))
        .gender(resultSet.getString("gender"))
        .phoneNumber(resultSet.getString("phone_number"))
        .isActive(resultSet.getInt("is_active"))
        .createdAt(resultSet.getString("created_at"))
        .recUpdatedAt(resultSet.getString("rec_updated_at"))
        .build();

      return Optional.of(patient);

    } catch(SQLException e) {
      System.err.println("Error mapping Patient DAO for query result: " + resultSet);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public static Optional<List<PatientDTO>> mapToPatientList(ResultSet resultSet) {

    List<PatientDTO> patientList = new ArrayList<>();
    try {

      if (resultSet == null || resultSet.isClosed()) {
        return Optional.empty();
      }

      while(resultSet.next()) {
        patientList.add(PatientDTO.builder()
          .id(resultSet.getInt("id"))
          .name(resultSet.getString("name"))
          .age(resultSet.getInt("age"))
          .gender(resultSet.getString("gender"))
          .phoneNumber(resultSet.getString("phone_number"))
          .isActive(resultSet.getInt("is_active"))
          .createdAt(resultSet.getString("created_at"))
          .recUpdatedAt(resultSet.getString("rec_updated_at"))
          .build());
      }

      return Optional.of(patientList);

    } catch(SQLException e) {
      System.err.println("Error mapping Patient DAO for query result: " + resultSet);
      e.printStackTrace();
    }

    return patientList.isEmpty() ? Optional.empty() : Optional.of(patientList);

  }

}
