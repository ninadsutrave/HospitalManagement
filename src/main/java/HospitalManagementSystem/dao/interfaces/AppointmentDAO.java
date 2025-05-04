package main.java.HospitalManagementSystem.dao.interfaces;

import main.java.HospitalManagementSystem.entity.AppointmentDTO;
import main.java.HospitalManagementSystem.entity.TimeRange;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface AppointmentDAO {

  Optional<AppointmentDTO> getAppointmentById(int id);

  boolean insertAppointment(AppointmentDTO appointment);

  Optional<List<TimeRange>> getPatientAppointmentsForDate(int patientId, Date appointmentDate);

  Optional<List<TimeRange>> getDoctorAppointmentsForDate(int doctorId, Date appointmentDate);

  boolean updateAppointment(AppointmentDTO appointment);

  boolean deleteAppointment(int id);

}
