package main.java.HospitalManagementSystem.dao.query;

public class DoctorQuery {
  public static final String INSERT_DOCTOR = "INSERT INTO doctor (name, specialisation_id, years_of_experience, shift_start_time, shift_end_time, is_active) VALUES (?, ?, ?, ?, ?, 1)";
  public static final String GET_DOCTOR_BY_ID = "SELECT d.id, d.name, d.specialisation_id, d.years_of_experience, d.shift_start_time, d.shift_end_time, d.is_active, d.created_at, d.rec_updated_at, s.name as specialisation FROM doctor d INNER JOIN specialisation s WHERE d.specialisation_id = s.id AND d.id = ?";
  public static final String GET_DOCTOR_BY_SPECIALISATION = "SELECT d.id, d.name, d.specialisation_id, d.years_of_experience, d.shift_start_time, d.shift_end_time, d.is_active, d.created_at, d.rec_updated_at, s.name as specialisation FROM doctor d INNER JOIN specialisation s WHERE d.specialisation_id = s.id AND specialisation_id = ? AND is_active = 1";
  public static final String GET_DOCTOR_SHIFT = "SELECT shift_start_time, shift_end_time from doctor where id = ?";
  public static final String UPDATE_DOCTOR = "UPDATE doctor SET name = ?, specialisation_id = ?, years_of_experience = ?, shift_start_time = ?, shift_end_time = ?, is_active = ? WHERE id = ?";
  public static final String DEACTIVATE_DOCTOR = "UPDATE doctor SET is_active = 0 WHERE id = ?";
}
