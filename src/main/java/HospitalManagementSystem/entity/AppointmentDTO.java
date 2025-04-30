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
}
