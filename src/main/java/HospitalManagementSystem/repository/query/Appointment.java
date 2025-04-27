package main.java.HospitalManagementSystem.repository.query;

public class Appointment {
  public static final String INSERT_APPOINTMENT = "INSERT into Appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
  public static final String GET_NUMBER_OF_DOCTOR_APPOINTMENTS_ON_GIVEN_DATE = "SELECT COUNT(*) FROM Appointments WHERE doctor_id = ? AND appointment_date = ?";
}
