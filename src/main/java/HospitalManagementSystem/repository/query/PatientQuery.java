package main.java.HospitalManagementSystem.repository.query;

public class PatientQuery {
  public static final String INSERT_PATIENT = "INSERT INTO patient (name, age, gender, phone_number) VALUES (?, ?, ?, ?)";
  public static final String GET_ALL_PATIENTS = "SELECT * FROM patient";
  public static final String GET_PATIENT_BY_ID = "SELECT * FROM patient WHERE id = ?";
  public static final String UPDATE_PATIENT = "UPDATE <<update_attributes>> WHERE id = ?";
  public static final String REMOVE_PATIENT = "UPDATE patient set isActive = 0 WHERE id = ?"; //update status as inactive, don't delete row
}
