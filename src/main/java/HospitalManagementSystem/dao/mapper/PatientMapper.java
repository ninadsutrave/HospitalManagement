package main.java.HospitalManagementSystem.dao.mapper;

import lombok.experimental.UtilityClass;
import main.java.HospitalManagementSystem.entity.Gender;
import main.java.HospitalManagementSystem.entity.PatientDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class PatientMapper {

  public static Optional<PatientDTO> mapToPatient(ResultSet resultSet) {

    try {

      if (resultSet == null || resultSet.isClosed() || !resultSet.next()) {
        return Optional.empty();
      }

      Gender gender = Optional.ofNullable(resultSet.getString("gender"))
        .map(g -> Gender.valueOf(g.toUpperCase()))
        .orElse(null);

      return Optional.of(createPatientDTO(resultSet, gender));

    } catch(SQLException e) {
      System.err.println("SQLException occurred while mapping Patient DTO for query result: " + resultSet);
      e.printStackTrace();
      return Optional.empty();
    }

  }

  public static Optional<List<PatientDTO>> mapToPatientList(ResultSet resultSet) {

    List<PatientDTO> patientList = new ArrayList<>();
    try {

      if (resultSet == null || resultSet.isClosed()) {
        return Optional.empty();
      }

      while(resultSet.next()) {
        Gender gender = Optional.ofNullable(resultSet.getString("gender"))
          .map(g -> Gender.valueOf(g.toUpperCase()))
          .orElse(null);

        patientList.add(createPatientDTO(resultSet, gender));
      }

      return Optional.of(patientList);

    } catch(SQLException e) {
      System.err.println("SQLException occurred while mapping Patient DTO for query result: " + resultSet);
      e.printStackTrace();
    }

    return patientList.isEmpty() ? Optional.empty() : Optional.of(patientList);

  }

  private static PatientDTO createPatientDTO(ResultSet resultSet, Gender gender) throws SQLException {
    return PatientDTO.builder()
      .id(resultSet.getInt("id"))
      .name(resultSet.getString("name"))
      .dateOfBirth(resultSet.getDate("date_of_birth"))
      .gender(gender)
      .phoneNumber(resultSet.getString("phone_number"))
      .isActive(resultSet.getInt("is_active"))
      .createdAt(resultSet.getTimestamp("created_at"))
      .recUpdatedAt(resultSet.getTimestamp("rec_updated_at"))
      .build();
  }

}
