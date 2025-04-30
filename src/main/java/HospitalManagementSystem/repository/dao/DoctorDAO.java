package main.java.HospitalManagementSystem.repository.dao;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DoctorDAO {
  private Integer id;
  private String name;
  private String specialisation;
  private Integer yearsOfExperience;
  private String shiftStart;
  private String shiftEnd;
  private Boolean isActive;
}
