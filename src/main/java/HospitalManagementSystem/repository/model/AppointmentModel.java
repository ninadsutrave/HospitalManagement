package main.java.HospitalManagementSystem.repository.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppointmentModel {
  private int id;
  private int patientId;
  private int doctorId;
  private String appointmentDate;
}
