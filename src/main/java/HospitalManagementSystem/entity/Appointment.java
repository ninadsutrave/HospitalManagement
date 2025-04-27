package main.java.HospitalManagementSystem.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Appointment {
  private int id;
  private int patientId;
  private int doctorId;
  private String appointmentDate;
}
