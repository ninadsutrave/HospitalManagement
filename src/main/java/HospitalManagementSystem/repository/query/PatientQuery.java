package main.java.HospitalManagementSystem.repository.query;

public class PatientQuery {
  public static final String INSERT_PATIENT = "INSERT INTO Patients (name, age, gender) VALUES (?, ?, ?)";
  public static final String GET_ALL_PATIENTS = "SELECT * FROM Patients";
  public static final String GET_PATIENT_BY_ID = "SELECT * FROM Patients WHERE id = ?";
}
