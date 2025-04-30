package main.java.HospitalManagementSystem.repository.query;

public class DoctorQuery {
  public static final String INSERT_DOCTOR = "INSERT INTO doctor (name, specialisation, years_of_experience, shift_start, shift_end) VALUES (?, ?, ?, ?)";
  public static final String GET_ALL_DOCTORS = "SELECT * FROM doctor";
  public static final String GET_DOCTOR_BY_ID = "SELECT * from doctor where id = ?";
  public static final String GET_DOCTOR_BY_SPECIALISATION = "SELECT * from doctor where specialisation = ?";
  public static final String UPDATE_DOCTOR = "UPDATE <<update_attributes>> WHERE id = ?";
  public static final String REMOVE_DOCTOR = "UPDATE doctor SET isActive = 0 WHERE id = ?"; //update status as inactive, don't delete row
}
