package main.java.HospitalManagementSystem.repository.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DoctorModel {
  private int id;
  private String name;
  private String specialisation;
}
