package main.java.HospitalManagementSystem.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Builder
@Data
public class DoctorDTO {
  private Integer id;
  private String name;
  private Integer specialisationId;
  private String specialisation;
  private Integer yearsOfExperience;
  private Time shiftStart;
  private Time shiftEnd;
  private Integer isActive;
  private Timestamp createdAt;
  private Timestamp recUpdatedAt;

  public void print() {
    System.out.println("  ID                : " + id);
    System.out.println("  Name              : " + name);
    System.out.println("  Specialisation    : " + specialisationId);
    System.out.println("  Years of Exp.     : " + yearsOfExperience);
    System.out.println("  Shift Start       : " + shiftStart);
    System.out.println("  Shift End         : " + shiftEnd);
    System.out.println("  IsActive          : " + isActive);
    System.out.println("  CreatedAt         : " + createdAt);
    System.out.println("  RecUpdatedAt      : " + recUpdatedAt);
  }

}
