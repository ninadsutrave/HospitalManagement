package main.java.HospitalManagementSystem.repository.query;

public class Doctor {
  public static final String INSERT_DOCTOR = "INSERT INTO Doctors (name, specialisation) VALUES (?, ?)";
  public static final String GET_ALL_DOCTORS = "SELECT * FROM Doctors";
  public static final String GET_DOCTOR_BY_ID = "SELECT * from Doctors where id = ?";
}
