package main.java.HospitalManagementSystem.dao.mapper;

import main.java.HospitalManagementSystem.entity.AppointmentDTO;
import main.java.HospitalManagementSystem.entity.TimeRange;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentMapper {

  public static Optional<AppointmentDTO> mapToAppointment(ResultSet resultSet) {

    try {

      if (resultSet == null || resultSet.isClosed() || !resultSet.next()) {
        return Optional.empty();
      }

      AppointmentDTO appointment = AppointmentDTO.builder()
        .id(resultSet.getInt("id"))
        .patientId(resultSet.getInt("patient_id"))
        .doctorId(resultSet.getInt("doctor_id"))
        .date(resultSet.getDate("appointment_date"))
        .startTime(resultSet.getTime("start_time"))
        .endTime(resultSet.getTime("end_time"))
        .notes(resultSet.getString("notes"))
        .build();

      return Optional.of(appointment);

    } catch (SQLException e) {
      System.err.println("SQLException occurred while mapping Appointment DTO for query result: " + resultSet);
      e.printStackTrace();
      return Optional.empty();
    }

  }

  public static Optional<List<TimeRange>> mapToAppointmentList(ResultSet resultSet) {

    List<TimeRange> appointmentList = new ArrayList<>();
    try {

      if (resultSet == null || resultSet.isClosed()) {
        return Optional.empty();
      }

      while(resultSet.next()) {
        TimeRange appointment = TimeRange.builder()
          .startTime(resultSet.getTime("start_time"))
          .endTime(resultSet.getTime("end_time"))
          .build();

        appointmentList.add(appointment);
      }
    } catch (SQLException e) {
      System.err.println("SQLException occurred while mapping Appointment DTO for query result: " + resultSet);
      e.printStackTrace();
    }

    return appointmentList.isEmpty() ? Optional.empty() : Optional.of(appointmentList);
  }

}
