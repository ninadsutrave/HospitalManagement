package main.java.HospitalManagementSystem.repository.mapper;

import main.java.HospitalManagementSystem.repository.dao.AppointmentDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentMapper {

  public static Optional<List<AppointmentDAO>> mapToAppointmentList(ResultSet queryResult) {

    try {

      List<AppointmentDAO> appointmentList = new ArrayList<>();
      while(queryResult.next()) {
        AppointmentDAO appointment = AppointmentDAO.builder()
          .id(queryResult.getInt("id"))
          .doctorId(queryResult.getInt("doctor_id"))
          .patientId(queryResult.getInt("patient_id"))
          .date(queryResult.getString("date"))
          .startTime(queryResult.getString("start_time"))
          .endTime(queryResult.getString("end_time"))
          .notes(queryResult.getString("notes"))
          .build();

        appointmentList.add(appointment);
      }

      return Optional.of(appointmentList);

    } catch (SQLException e) {
      System.err.println("Error mapping Appointment DAO for query result: " + queryResult);
      e.printStackTrace();
    }

    return Optional.empty();

  }

}
