package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.dao.implementation.AppointmentDAOImpl;
import main.java.HospitalManagementSystem.dao.implementation.DoctorDAOImpl;
import main.java.HospitalManagementSystem.dao.interfaces.AppointmentDAO;
import main.java.HospitalManagementSystem.entity.AppointmentDTO;
import main.java.HospitalManagementSystem.util.InputUtil;
import main.java.HospitalManagementSystem.util.TimeRange;

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
    appointmentDAOImpl = new AppointmentDAOImpl();
  }

  public void bookAppointment() {

    int patientId = InputUtil.readInt("Enter patient id: ");
    int doctorId = InputUtil.readInt("Enter doctorId: ");
    Date appointmentDate = InputUtil.readDate("Enter date: ");

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

  private Optional<AppointmentDTO> createValidatedAppointment(int doctorId, int patientId, Date appointmentDate) {
    TimeRange doctorShift = doctorDAOImpl
      .getDoctorShift(doctorId)
      .orElse(null);

    if(doctorShift == null) {
      System.err.println("Error fetching doctor shift timings");
      return Optional.empty();
    }

    List<TimeRange> patientAppointments = appointmentDAOImpl
      .getPatientAppointmentsForDate(patientId, appointmentDate)
      .orElse(List.of());

    List<TimeRange> doctorAppointments = appointmentDAOImpl
      .getDoctorAppointmentsForDate(doctorId, appointmentDate)
      .orElse(List.of());

    List<TimeRange> availableSlots = findNonOverlappingSlots(doctorShift, doctorAppointments, patientAppointments);

    if (availableSlots.isEmpty()) {
      System.err.println("No available slots for this date! Please try again for another date!");
      return Optional.empty();
    }

    Time startTime = InputUtil.readTime("Start Time: ");
    Time endTime = InputUtil.readTime("End Time: ");

    TimeRange selectedSlot = TimeRange.builder()
      .startTime(startTime)
      .endTime(endTime)
      .build();

    boolean isValid = availableSlots.stream().anyMatch(
      slot -> !selectedSlot.getStartTime().before(slot.getStartTime()) &&
        !selectedSlot.getEndTime().after(slot.getEndTime())
    );

    if (!isValid) {
      System.err.println("Selected time does not fall in any available slot.");
      return Optional.empty();
    }

    AppointmentDTO appointment = AppointmentDTO.builder()
      .patientId(patientId)
      .doctorId(doctorId)
      .date(appointmentDate)
      .startTime(startTime)
      .endTime(endTime)
      .build();

    return Optional.of(appointment);
  }

  private boolean canPatientEditAppointment(int patientId, AppointmentDTO appointment) {
    return (patientId == appointment.getPatientId());
  }

  private List<TimeRange> findNonOverlappingSlots(TimeRange doctorShift, List<TimeRange> doctorAppointments, List<TimeRange> patientAppointments) {
    List<TimeRange> availableSlots = new ArrayList<>();

    List<TimeRange> allAppointments = new ArrayList<>();
    allAppointments.addAll(doctorAppointments);
    allAppointments.addAll(patientAppointments);
    allAppointments.sort(Comparator.comparing(TimeRange::getStartTime));

    Time startTime = doctorShift.getStartTime();
    Time endTime = doctorShift.getEndTime();

    if (endTime.before(startTime)) {
      Time eveningEnd = Time.valueOf("23:59:59");
      TimeRange eveningShift = TimeRange.builder()
        .startTime(startTime)
        .endTime(eveningEnd)
        .build();

      Time morningStart = Time.valueOf("00:00:00");
      TimeRange morningShift = TimeRange.builder()
        .startTime(morningStart)
        .endTime(endTime)
        .build();

      availableSlots.addAll(getAvailableSlotsInShift(eveningShift, allAppointments));
      availableSlots.addAll(getAvailableSlotsInShift(morningShift, allAppointments));
    } else {
      availableSlots.addAll(getAvailableSlotsInShift(doctorShift, allAppointments));
    }

    return availableSlots;
  }

  private List<TimeRange> getAvailableSlotsInShift(TimeRange shift, List<TimeRange> appointments) {
    List<TimeRange> availableSlots = new ArrayList<>();
    Time current = shift.getStartTime();

    for (TimeRange appointment : appointments) {
      Time appointmentStart = appointment.getStartTime();
      Time appointmentEnd = appointment.getEndTime();

      if (appointmentEnd.before(shift.getStartTime()) || appointmentStart.after(shift.getEndTime())) {
        continue;
      }

      if (current.before(appointmentStart)) {
        Time slotEnd = appointmentStart.before(shift.getEndTime()) ? appointmentStart : shift.getEndTime();
        if (current.before(slotEnd)) {
          availableSlots.add(TimeRange.builder()
            .startTime(current)
            .endTime(slotEnd)
            .build());
        }
      }

      if (appointmentEnd.after(current)) {
        current = appointmentEnd;
      }
    }

    if (current.before(shift.getEndTime())) {
      availableSlots.add(TimeRange.builder()
        .startTime(current)
        .endTime(shift.getEndTime())
        .build());
    }

    return availableSlots;
  }

}
