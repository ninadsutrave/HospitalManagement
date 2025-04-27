package main.java.HospitalManagementSystem.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Doctor {
  private int id;
  private String name;
  private String specialisation;
}
