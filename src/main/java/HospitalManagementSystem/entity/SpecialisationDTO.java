package main.java.HospitalManagementSystem.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SpecialisationDTO {
  private Integer id;
  private String name;

  public void print() {
    System.out.println(id + ". " + name);
  }
}
