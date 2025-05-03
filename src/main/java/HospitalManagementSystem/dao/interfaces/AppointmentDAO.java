package main.java.HospitalManagementSystem.dao.interfaces;

import main.java.HospitalManagementSystem.entity.AppointmentDTO;

import java.util.List;
import java.util.Optional;

public interface AppointmentDAO {

  boolean insertAppointment(AppointmentDTO appointment);

  Optional<List<AppointmentDTO>> getPatientAppointmentsForDate(int patientId, String appointmentDate);

  Optional<List<AppointmentDTO>> getDoctorAppointmentsForDate(int doctorId, String appointmentDate);

  boolean updateAppointment(AppointmentDTO appointment);

  boolean deleteAppointment(int id);

}
