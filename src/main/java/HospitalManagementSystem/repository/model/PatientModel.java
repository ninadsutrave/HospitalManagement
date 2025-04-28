package main.java.HospitalManagementSystem.repository.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PatientModel {
  private int id;
  private String name;
  private int age;
  private String gender;
}
