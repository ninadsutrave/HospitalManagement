package main.java.HospitalManagementSystem.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DoctorDTO {
  private Integer id;
  private String name;
  private Integer specialisationId;
  private Integer yearsOfExperience;
  private String shiftStart;
  private String shiftEnd;
  private Integer isActive;
  private String createdAt;
  private String recUpdatedAt;

  public void print() {
    System.out.println("  ID                : " + id);
    System.out.println("  Name              : " + name);
    System.out.println("  Specialisation    : " + specialisationId);
    System.out.println("  Years of Exp.     : " + yearsOfExperience);
    System.out.println("  Shift Start       : " + shiftStart);
    System.out.println("  Shift End         : " + shiftEnd);
    System.out.println("  Active            : " + isActive);
    System.out.println("  CreatedAt         : " + createdAt);
    System.out.println("  RecUpdatedAt      : " + recUpdatedAt);
  }

}
