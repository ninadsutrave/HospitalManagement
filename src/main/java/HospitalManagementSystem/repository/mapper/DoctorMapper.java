package main.java.HospitalManagementSystem.repository.mapper;

import main.java.HospitalManagementSystem.entity.DoctorDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorMapper {

  public static Optional<DoctorDTO> mapToDoctor(ResultSet resultSet) {

    try {

      if (resultSet == null || resultSet.isClosed() || !resultSet.next()) {
        return Optional.empty();
      }

      DoctorDTO doctor = DoctorDTO.builder()
        .id(resultSet.getInt("id"))
        .name(resultSet.getString("name"))
        .specialisationId(resultSet.getInt("specialisation_id"))
        .yearsOfExperience(resultSet.getInt("years_of_experience"))
        .shiftStart(resultSet.getString("shift_start_time"))
        .shiftEnd(resultSet.getString("shift_end_time"))
        .isActive(resultSet.getInt("is_active"))
        .createdAt(resultSet.getString("created_at"))
        .recUpdatedAt(resultSet.getString("rec_updated_at"))
        .build();

      return Optional.of(doctor);

    } catch(SQLException e) {
      System.err.println("Error mapping Doctor DTO for query result: " + resultSet);
      e.printStackTrace();
    }

    return Optional.empty();

  }

  public static Optional<List<DoctorDTO>> mapToDoctorList(ResultSet resultSet) {

    List<DoctorDTO> doctorList = new ArrayList<>();
    try {

      if (resultSet == null || resultSet.isClosed()) {
        return Optional.empty();
      }

      while(resultSet.next()) {
        doctorList.add(DoctorDTO.builder()
          .id(resultSet.getInt("id"))
          .name(resultSet.getString("name"))
          .specialisationId(resultSet.getInt("specialisation_id"))
          .yearsOfExperience(resultSet.getInt("years_of_experience"))
          .shiftStart(resultSet.getString("shift_start_time"))
          .shiftEnd(resultSet.getString("shift_end_time"))
          .isActive(resultSet.getInt("is_active"))
          .createdAt(resultSet.getString("created_at"))
          .recUpdatedAt(resultSet.getString("rec_updated_at"))
          .build());
      }

    } catch(SQLException e) {
      System.err.println("Error mapping Doctor DAO for query result: " + resultSet);
      e.printStackTrace();
    }

    return doctorList.isEmpty() ? Optional.empty() : Optional.of(doctorList);

  }

}
