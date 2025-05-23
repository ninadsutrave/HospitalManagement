package main.java.HospitalManagementSystem.dao.mapper;

import lombok.experimental.UtilityClass;
import main.java.HospitalManagementSystem.entity.DoctorDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class DoctorMapper {

  public static Optional<DoctorDTO> mapToDoctor(ResultSet resultSet) {

    try {

      if (resultSet == null || resultSet.isClosed() || !resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(createDoctorDTO(resultSet));

    } catch(SQLException e) {
      System.err.println("SQLException occurred while mapping Doctor DTO for query result: " + resultSet);
      e.printStackTrace();
      return Optional.empty();
    }

  }

  public static Optional<List<DoctorDTO>> mapToDoctorList(ResultSet resultSet) {

    List<DoctorDTO> doctorList = new ArrayList<>();
    try {

      if (resultSet == null || resultSet.isClosed()) {
        return Optional.empty();
      }

      while(resultSet.next()) {
        doctorList.add(createDoctorDTO(resultSet));
      }

    } catch(SQLException e) {
      System.err.println("SQLException occurred while mapping Doctor DTO for query result: " + resultSet);
      e.printStackTrace();
    }

    return doctorList.isEmpty() ? Optional.empty() : Optional.of(doctorList);

  }

  private static DoctorDTO createDoctorDTO(ResultSet resultSet) throws SQLException {
    return DoctorDTO.builder()
      .id(resultSet.getInt("id"))
      .name(resultSet.getString("name"))
      .specialisationId(resultSet.getInt("specialisation_id"))
      .specialisation(resultSet.getString("specialisation"))
      .yearsOfExperience(resultSet.getInt("years_of_experience"))
      .shiftStart(resultSet.getTime("shift_start_time"))
      .shiftEnd(resultSet.getTime("shift_end_time"))
      .isActive(resultSet.getInt("is_active"))
      .createdAt(resultSet.getTimestamp("created_at"))
      .recUpdatedAt(resultSet.getTimestamp("rec_updated_at"))
      .build();
  }

}
