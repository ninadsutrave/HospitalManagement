package main.java.HospitalManagementSystem.dao.query;

public class PatientQuery {
  public static final String INSERT_PATIENT = "INSERT INTO patient (name, date_of_birth, gender, phone_number, is_active) VALUES (?, ?, ?, ?, 1)";
  public static final String GET_PATIENT_BY_ID = "SELECT * FROM patient WHERE id = ?";
  public static final String UPDATE_PATIENT = "UPDATE patient SET name = ?, date_of_birth = ?, gender = ?, phone_number = ?, is_active = ? WHERE id = ?";
  public static final String DEACTIVATE_PATIENT = "UPDATE patient set is_active = 0 WHERE id = ?";
}
