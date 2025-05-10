package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.dao.implementation.AppointmentDAOImpl;
import main.java.HospitalManagementSystem.dao.implementation.DoctorDAOImpl;
import main.java.HospitalManagementSystem.dao.implementation.PatientDAOImpl;
import main.java.HospitalManagementSystem.dao.interfaces.AppointmentDAO;
import main.java.HospitalManagementSystem.dao.interfaces.DoctorDAO;
import main.java.HospitalManagementSystem.dao.interfaces.PatientDAO;
import main.java.HospitalManagementSystem.entity.AppointmentDTO;
import main.java.HospitalManagementSystem.entity.PatientDTO;
import main.java.HospitalManagementSystem.util.InputUtil;
import main.java.HospitalManagementSystem.entity.TimeRange;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static main.java.HospitalManagementSystem.constants.Constants.APPOINTMENT_DURATION_IN_MINUTES;
import static main.java.HospitalManagementSystem.constants.Constants.MILLISECONDS_PER_MINUTE;

public class AppointmentService {

  PatientDAO patientDAOImpl;
  DoctorDAO doctorDAOImpl;
  AppointmentDAO appointmentDAOImpl;

  public AppointmentService() {
    patientDAOImpl = new PatientDAOImpl();
    doctorDAOImpl = new DoctorDAOImpl();
    appointmentDAOImpl = new AppointmentDAOImpl();
  }

  public void bookAppointment() {

    int patientId = InputUtil.readInt("Enter patient id: ");
    int doctorId = InputUtil.readInt("Enter doctor id: ");
    Date appointmentDate = InputUtil.readDate("Enter date (YYYY-MM-DD): ");

    AppointmentDTO appointment = createValidatedAppointment(doctorId, patientId, appointmentDate)
      .orElse(null);

    if(appointment == null) return;

    appointmentDAOImpl.insertAppointment(appointment);

  }

  public void rescheduleAppointment() {

    System.out.println("RESCHEDULE APPOINTMENT");
    int appointmentId = InputUtil.readInt("Enter appointment id: ");
    int patientId = InputUtil.readInt("Enter your patient id: ");

    AppointmentDTO appointment = appointmentDAOImpl
      .getAppointmentById(appointmentId)
      .orElse(null);

    if(appointment == null) {
      System.err.println("No appointment found for given appointment id!");
      return;
    }

    if(!canPatientEditAppointment(patientId, appointment)) {
      System.err.println("You're not authorised to edit this appointment!");
      return;
    }

    Date newAppointmentDate = InputUtil.readDate("Enter preferred appointment date (YYYY-MM-DD): ");

    AppointmentDTO newAppointment = createValidatedAppointment(appointment.getDoctorId(), patientId, newAppointmentDate)
      .orElse(null);

    if(newAppointment == null) return;
    newAppointment.setId(appointment.getId());

    appointmentDAOImpl.updateAppointment(newAppointment);

  }


  public void cancelAppointment() {

    System.out.println("CANCEL APPOINTMENT");
    int appointmentId = InputUtil.readInt("Enter appointment id: ");
    int patientId = InputUtil.readInt("Enter your patient id: ");

    AppointmentDTO appointment = appointmentDAOImpl
      .getAppointmentById(appointmentId)
      .orElse(null);

    if(appointment == null) {
      System.err.println("No appointment found for given appointment id!");
      return;
    }

    if(!canPatientEditAppointment(patientId, appointment)) {
      System.err.println("You're not authorised to delete this appointment!");
      return;
    }

    appointmentDAOImpl.deleteAppointment(appointmentId);

  }

  private boolean canPatientEditAppointment(int patientId, AppointmentDTO appointment) {
    return (patientId == appointment.getPatientId());
  }

  private Optional<AppointmentDTO> createValidatedAppointment(int doctorId, int patientId, Date appointmentDate) {
    PatientDTO patient = patientDAOImpl
      .getPatientById(patientId)
      .orElse(null);

    if(patient == null) {
      System.err.println("Invalid patient id!");
      return Optional.empty();
    }

    TimeRange doctorShift = doctorDAOImpl
      .getDoctorShift(doctorId)
      .orElse(null);

    if (doctorShift == null) {
      System.err.println("Error getting doctor's shift!");
      return Optional.empty();
    }

    List<TimeRange> availableSlots = getAvailableSlots(doctorId, patientId, appointmentDate, doctorShift);
    if (availableSlots.isEmpty()) {
      System.err.println("No available slots for this date! Please try again for another date!");
      return Optional.empty();
    }

    TimeRange appointmentTimeSlot = selectSlotFromAvailable(availableSlots);
    if (appointmentTimeSlot == null) return Optional.empty();

    AppointmentDTO appointment = AppointmentDTO.builder()
      .doctorId(doctorId)
      .patientId(patientId)
      .date(appointmentDate)
      .startTime(appointmentTimeSlot.getStartTime())
      .endTime(appointmentTimeSlot.getEndTime())
      .build();

    return Optional.of(appointment);
  }

  private List<TimeRange> getAvailableSlots(int doctorId, int patientId, Date date, TimeRange doctorShift) {
    List<TimeRange> patientAppointments = appointmentDAOImpl
      .getPatientAppointmentsForDate(patientId, date)
      .orElse(List.of());

    List<TimeRange> doctorAppointments = appointmentDAOImpl
      .getDoctorAppointmentsForDate(doctorId, date)
      .orElse(List.of());

    return getNonOverlappingSlots(doctorShift, doctorAppointments, patientAppointments);
  }

  private TimeRange selectSlotFromAvailable(List<TimeRange> availableSlots) {
    System.out.println("Available slots:");
    List<TimeRange> allAvailableSlots = new ArrayList<>();

    for (TimeRange fullSlot : availableSlots) {
      Time startTime = fullSlot.getStartTime();
      Time endTime = fullSlot.getEndTime();

      List<TimeRange> timeRanges = generateTimeSlots(startTime, endTime, APPOINTMENT_DURATION_IN_MINUTES);
      allAvailableSlots.addAll(timeRanges);
    }

    for (int i = 0; i < allAvailableSlots.size(); i++) {
      TimeRange slot = allAvailableSlots.get(i);
      System.out.printf("%d. %s - %s%n", i + 1, slot.getStartTime(), slot.getEndTime());
    }
    System.out.println();

    int choice = InputUtil.readInt("Choose a slot by number: ");
    if (choice < 1 || choice > allAvailableSlots.size()) {
      System.err.println("Invalid choice.");
      return null;
    }

    return allAvailableSlots.get(choice - 1);
  }

  private List<TimeRange> generateTimeSlots(Time startTime, Time endTime, int intervalMinutes) {
    List<TimeRange> timeSlots = new ArrayList<>();
    long intervalInMillis = intervalMinutes * MILLISECONDS_PER_MINUTE;
    long startTimeInMillis = startTime.getTime();
    long endTimeInMillis = endTime.getTime();

    for (long currentTimeInMillis = startTimeInMillis; currentTimeInMillis < endTimeInMillis; currentTimeInMillis += intervalInMillis) {
      Time slotStart = new Time(currentTimeInMillis);
      Time slotEnd = new Time(currentTimeInMillis + intervalInMillis);
      timeSlots.add(TimeRange.builder().startTime(slotStart).endTime(slotEnd).build());
    }

    return timeSlots;
  }

  private List<TimeRange> getNonOverlappingSlots(TimeRange shift, List<TimeRange> doctorAppointments, List<TimeRange> patientAppointments) {
    List<TimeRange> occupiedSlots = new ArrayList<>();
    occupiedSlots.addAll(doctorAppointments);
    occupiedSlots.addAll(patientAppointments);

    occupiedSlots.sort(Comparator.comparing(TimeRange::getStartTime));

    List<TimeRange> availableSlots = new ArrayList<>();
    Time currentStart = shift.getStartTime();

    for (TimeRange occupiedSlot : occupiedSlots) {
      if (occupiedSlot.getStartTime().after(currentStart)) {
        availableSlots.add(TimeRange.builder()
          .startTime(currentStart)
          .endTime(occupiedSlot.getStartTime())
          .build());
      }
      if (occupiedSlot.getEndTime().after(currentStart)) {
        currentStart = occupiedSlot.getEndTime();
      }
    }

    if (currentStart.before(shift.getEndTime())) {
      availableSlots.add(TimeRange.builder()
        .startTime(currentStart)
        .endTime(shift.getEndTime())
        .build());
    }

    return availableSlots;
  }

}
