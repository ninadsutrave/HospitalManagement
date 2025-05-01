package main.java.HospitalManagementSystem.dao.mapper;

import main.java.HospitalManagementSystem.entity.AppointmentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentMapper {

  public static Optional<List<AppointmentDTO>> mapToAppointmentList(ResultSet resultSet) {

    List<AppointmentDTO> appointmentList = new ArrayList<>();
    try {

      if (resultSet == null || resultSet.isClosed()) {
        return Optional.empty();
      }

      while(resultSet.next()) {
        AppointmentDTO appointment = AppointmentDTO.builder()
          .id(resultSet.getInt("id"))
          .doctorId(resultSet.getInt("doctor_id"))
          .patientId(resultSet.getInt("patient_id"))
          .date(resultSet.getString("date"))
          .startTime(resultSet.getString("start_time"))
          .endTime(resultSet.getString("end_time"))
          .notes(resultSet.getString("notes"))
          .build();

        appointmentList.add(appointment);
      }
    } catch (SQLException e) {
      System.err.println("Error mapping Appointment DTO for query result: " + resultSet);
      e.printStackTrace();
    }

    return appointmentList.isEmpty() ? Optional.empty() : Optional.of(appointmentList);
  }

}
