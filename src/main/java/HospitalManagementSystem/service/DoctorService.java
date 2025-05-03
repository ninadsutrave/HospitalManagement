package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.dao.interfaces.DoctorDAO;
import main.java.HospitalManagementSystem.entity.DoctorDTO;
import main.java.HospitalManagementSystem.dao.implementation.DoctorDAOImpl;
import main.java.HospitalManagementSystem.util.InputUtil;

import java.sql.Time;
import java.util.List;
import java.util.Objects;

public class DoctorService {

  DoctorDAO doctorDAOImpl;

  public DoctorService() {
    doctorDAOImpl = new DoctorDAOImpl();
  }

  public void viewDoctors(Integer specialisationId) {

    if(Objects.isNull(specialisationId)) {
      System.err.println("Invalid specialisation id!");
      return;
    }

    System.out.println("VIEW DOCTORS");

    List<DoctorDTO> doctors = doctorDAOImpl
      .getDoctorBySpecialisation(specialisationId)
      .orElse(List.of());

    if(doctors.isEmpty()) {
      return;
    }

    System.out.println("+------+--------------------+--------------------+----------------------+");
    System.out.println("|  Id  | Name               | Specialisation Id  | Years of Experience  |");
    System.out.println("+------+--------------------+--------------------+----------------------+");

    for(DoctorDTO doctor : doctors) {
      System.out.printf("|  %-4s|  %-18s|  %-18s|  %-20s|\n", doctor.getId(), doctor.getName(), doctor.getSpecialisationId(), doctor.getYearsOfExperience());
      System.out.println("+------+--------------------+--------------------+----------------------+");
    }

  }

  public void addDoctor() {

    System.out.println("ADD NEW DOCTOR");

    System.out.print("Enter name: ");
    String name = InputUtil.readLine("Enter name: ");
    int specialisationId = InputUtil.readInt("Enter specialisation id: ");
    int yearsOfExperience = InputUtil.readInt("Enter years of experience: ");
    Time shiftStart = InputUtil.readTime("Enter shift start time: ");
    Time shiftEnd = InputUtil.readTime("Enter shift end time: ");

    DoctorDTO doctor = DoctorDTO.builder()
      .name(name)
      .specialisationId(specialisationId)
      .yearsOfExperience(yearsOfExperience)
      .shiftStart(shiftStart)
      .shiftEnd(shiftEnd)
      .build();

    doctorDAOImpl.insertDoctor(doctor);

  }

  public void viewDoctorById() {

    System.out.println("VIEW DOCTOR");

    int id = InputUtil.readInt("Enter doctor id: ");

    DoctorDTO doctor = doctorDAOImpl
      .getDoctorById(id)
      .orElse(null);

    if (Objects.isNull(doctor)) {
      System.err.println("Doctor records not present!");
      return;
    }

    doctor.print();

  }

  public void editDoctor() {

    System.out.println("EDIT DOCTOR");

    int id = InputUtil.readInt("Enter doctor id: ");

    DoctorDTO doctor = doctorDAOImpl
      .getDoctorById(id)
      .orElse(null);

    if (Objects.isNull(doctor)) {
      System.err.println("Doctor records not present!");
      return;
    }

    doctor.print();

    System.out.println("Re-enter updated details: ");
    String name = InputUtil.readLine("Enter name of doctor: ");
    int specialisationId = InputUtil.readInt("Enter specialisation id: ");
    int yearsOfExperience = InputUtil.readInt("Enter years of experience: ");
    Time shiftStart = InputUtil.readTime("Enter shift start time: ");
    Time shiftEnd = InputUtil.readTime("Enter shift end time: ");
    int isActive =InputUtil.readInt("Enter isActive: ");

    DoctorDTO updatedDoctor = DoctorDTO.builder()
      .id(doctor.getId())
      .name(name)
      .specialisationId(specialisationId)
      .yearsOfExperience(yearsOfExperience)
      .shiftStart(shiftStart)
      .shiftEnd(shiftEnd)
      .isActive(isActive)
      .build();

    doctorDAOImpl.updateDoctor(updatedDoctor);

  }

  public void removeDoctor() {

    System.out.println("REMOVE DOCTOR");

    int id = InputUtil.readInt("Enter doctor id: ");
    doctorDAOImpl.deactivateDoctor(id);

  }

}
