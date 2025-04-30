package main.java.HospitalManagementSystem.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DoctorDTO {
  private Integer id;
  private String name;
  private String specialisation;
  private Integer yearsOfExperience;
  private String shiftStart;
  private String shiftEnd;
  private Boolean isActive;
}
