package main.java.HospitalManagementSystem.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;

@Builder
@Data
public class TimeRange {
  Time startTime;
  Time endTime;
}
