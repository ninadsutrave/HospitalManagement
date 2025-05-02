package main.java.HospitalManagementSystem.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Builder
@Data
public class AppointmentDTO {
  private Integer id;
  private Integer patientId;
  private Integer doctorId;
  private Date date;
  private Time startTime;
  private Time endTime;
  private String notes;
  private Timestamp createdAt;
  private Timestamp recUpdatedAt;

  public void print() {
    System.out.println("  ID          : " + id);
    System.out.println("  Patient ID  : " + patientId);
    System.out.println("  Doctor ID   : " + doctorId);
    System.out.println("  Date        : " + date);
    System.out.println("  Start Time  : " + startTime);
    System.out.println("  End Time    : " + endTime);
    System.out.println("  Notes       : " + notes);
    System.out.println("  CreatedAt   : " + createdAt);
    System.out.println("  RecUpdatedAt: " + recUpdatedAt);
  }
}
