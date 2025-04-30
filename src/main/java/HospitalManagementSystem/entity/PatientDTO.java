package main.java.HospitalManagementSystem.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PatientDTO {
  private Integer id;
  private String name;
  private Integer age;
  private String gender;
  private String phoneNumber;
  private Boolean isActive;
}
