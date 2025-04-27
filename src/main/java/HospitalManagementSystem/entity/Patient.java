package main.java.HospitalManagementSystem.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Patient {
  private int id;
  private String name;
  private int age;
  private String gender;
}
