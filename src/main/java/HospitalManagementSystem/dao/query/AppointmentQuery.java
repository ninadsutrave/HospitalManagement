package main.java.HospitalManagementSystem.dao.query;

public class AppointmentQuery {
  public static final String VIEW_APPOINTMENT = "SELECT * from appointment where id = ?";
  public static final String INSERT_APPOINTMENT = "INSERT into appointment (patient_id, doctor_id, appointment_date, start_time, end_time) VALUES (?, ?, ?, ?, ?)";
  public static final String GET_PATIENT_APPOINTMENTS_FOR_DATE = "SELECT * from appointment where patient_id = ? and appointment_date = ?";
  public static final String GET_DOCTOR_APPOINTMENTS_FOR_DATE = "SELECT * from appointment where doctor_id = ? and appointment_date = ?";
  public static final String UPDATE_APPOINTMENT = "UPDATE appointment set appointment_date = ?, start_time = ?, end_time = ? where id = ?";
  public static final String DELETE_APPOINTMENT = "DELETE FROM appointment where id = ?";
}
