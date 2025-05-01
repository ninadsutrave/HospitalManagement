package main.java.HospitalManagementSystem.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppointmentDTO {
  private Integer id;
  private Integer patientId;
  private Integer doctorId;
  private String date;
  private String startTime;
  private String endTime;
  private String notes;
  private String createdAt;
  private String recUpdatedAt;

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
