package main.java.HospitalManagementSystem.util;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;

@Builder
@Data
public class TimeRange {
  Time startTime;
  Time endTime;
}
