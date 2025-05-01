package main.java.HospitalManagementSystem.repository.query;

public class DoctorQuery {
  public static final String INSERT_DOCTOR = "INSERT INTO doctor (name, specialisation_id, years_of_experience, is_active, shift_start_time, shift_end_time) VALUES (?, ?, ?, 1, ?, ?)";
  public static final String GET_ALL_DOCTORS = "SELECT * FROM doctor";
  public static final String GET_DOCTOR_BY_ID = "SELECT * from doctor where id = ?";
  public static final String GET_DOCTOR_BY_SPECIALISATION = "SELECT * FROM doctor INNER JOIN specialisation WHERE doctor.specialisation_id = specialisation.id AND specialisation_id = ? AND is_active = 1";
  public static final String UPDATE_DOCTOR = "UPDATE <<update_attributes>> WHERE id = ?";
  public static final String DEACTIVATE_DOCTOR = "UPDATE doctor SET is_active = 0 WHERE id = ?";
}
