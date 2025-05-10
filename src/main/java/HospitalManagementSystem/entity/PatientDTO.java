package main.java.HospitalManagementSystem.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Builder
@Data
public class PatientDTO {
  private Integer id;
  private String name;
  private Date dateOfBirth;
  private Gender gender;
  private String phoneNumber;
  private Integer isActive;
  private Timestamp createdAt;
  private Timestamp recUpdatedAt;

  public void print() {
    System.out.println("  ID           : " + id);
    System.out.println("  Name         : " + name);
    System.out.println("  DOB          : " + dateOfBirth);
    System.out.println("  Gender       : " + gender);
    System.out.println("  Phone        : " + phoneNumber);
    System.out.println("  IsActive     : " + isActive);
    System.out.println("  CreatedAt    : " + createdAt);
    System.out.println("  RecUpdatedAt : " + recUpdatedAt);
  }

}
