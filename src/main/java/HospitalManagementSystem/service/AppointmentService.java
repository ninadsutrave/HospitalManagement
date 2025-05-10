package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.dao.implementation.AppointmentDAOImpl;
import main.java.HospitalManagementSystem.dao.implementation.DoctorDAOImpl;
import main.java.HospitalManagementSystem.dao.interfaces.AppointmentDAO;
import main.java.HospitalManagementSystem.entity.AppointmentDTO;
import main.java.HospitalManagementSystem.util.InputUtil;
import main.java.HospitalManagementSystem.entity.TimeRange;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AppointmentService {

  DoctorDAOImpl doctorDAOImpl;
  AppointmentDAO appointmentDAOImpl;

  public AppointmentService() {
    doctorDAOImpl = new DoctorDAOImpl();
    appointmentDAOImpl = new AppointmentDAOImpl();
  }

  public void bookAppointment() {

    int patientId = InputUtil.readInt("Enter patient id: ");
    int doctorId = InputUtil.readInt("Enter doctorId: ");
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

    Date newAppointmentDate = InputUtil.readDate("Enter preferred appointment date: ");

    AppointmentDTO newAppointment = createValidatedAppointment(appointment.getDoctorId(), patientId, newAppointmentDate)
      .orElse(null);

    if(newAppointment == null) return;

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
    TimeRange doctorShift = fetchDoctorShift(doctorId);
    if (doctorShift == null) return Optional.empty();

    List<TimeRange> availableSlots = getAvailableSlots(doctorId, patientId, appointmentDate, doctorShift);
    if (availableSlots.isEmpty()) {
      System.err.println("No available slots for this date! Please try again for another date!");
      return Optional.empty();
    }

    TimeRange selectedSlot = selectSlotFromAvailable(availableSlots);
    if (selectedSlot == null) return Optional.empty();

    return Optional.of(buildAppointment(doctorId, patientId, appointmentDate, selectedSlot));
  }

  private TimeRange fetchDoctorShift(int doctorId) {
    return doctorDAOImpl.getDoctorShift(doctorId)
      .orElseGet(() -> {
        System.err.println("Error fetching doctor shift timings");
        return null;
      });
  }

  private List<TimeRange> getAvailableSlots(int doctorId, int patientId, Date date, TimeRange doctorShift) {
    List<TimeRange> patientAppointments = appointmentDAOImpl
      .getPatientAppointmentsForDate(patientId, date)
      .orElse(List.of());

    List<TimeRange> doctorAppointments = appointmentDAOImpl
      .getDoctorAppointmentsForDate(doctorId, date)
      .orElse(List.of());

    return findNonOverlappingSlots(doctorShift, doctorAppointments, patientAppointments);
  }

  private TimeRange selectSlotFromAvailable(List<TimeRange> availableSlots) {
    System.out.println("Available slots:");
    for (int i = 0; i < availableSlots.size(); i++) {
      TimeRange slot = availableSlots.get(i);
      System.out.printf("%d. %s - %s%n", i + 1, slot.getStartTime(), slot.getEndTime());
    }

    int choice = InputUtil.readInt("Choose a slot by number: ");
    if (choice < 1 || choice > availableSlots.size()) {
      System.err.println("Invalid choice.");
      return null;
    }

    return availableSlots.get(choice - 1);
  }

  private AppointmentDTO buildAppointment(int doctorId, int patientId, Date date, TimeRange slot) {
    return AppointmentDTO.builder()
      .doctorId(doctorId)
      .patientId(patientId)
      .date(date)
      .startTime(slot.getStartTime())
      .endTime(slot.getEndTime())
      .build();
  }


  public List<TimeRange> findNonOverlappingSlots(TimeRange shift, List<TimeRange> doctorAppointments, List<TimeRange> patientAppointments) {
    List<TimeRange> busySlots = new ArrayList<>();
    busySlots.addAll(doctorAppointments);
    busySlots.addAll(patientAppointments);

    busySlots.sort(Comparator.comparing(TimeRange::getStartTime));

    List<TimeRange> availableSlots = new ArrayList<>();
    Time currentStart = shift.getStartTime();

    for (TimeRange busy : busySlots) {
      if (busy.getStartTime().after(currentStart)) {
        availableSlots.add(TimeRange.builder()
          .startTime(currentStart)
          .endTime(busy.getStartTime())
          .build());
      }
      if (busy.getEndTime().after(currentStart)) {
        currentStart = busy.getEndTime();
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
