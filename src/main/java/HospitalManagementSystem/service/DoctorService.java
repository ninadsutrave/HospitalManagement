package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.entity.DoctorDTO;
import main.java.HospitalManagementSystem.repository.implementation.DoctorDAOImpl;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DoctorService {

  DoctorDAOImpl doctorDAOImpl;
  private final Scanner scanner = new Scanner(System.in);

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
      System.out.printf("|2s-%-4s|2s-%-18s|2s-%-18s|2s-%-20s|\n", doctor.getId(), doctor.getName(), doctor.getYearsOfExperience());
      System.out.println("+------+--------------------+--------------------+----------------------+");
    }

  }

  public void addDoctor() {

    Scanner scanner = new Scanner(System.in);
    System.out.println("ADD NEW DOCTOR");

    System.out.print("Enter name: ");
    String name = scanner.nextLine();
    System.out.print("Enter specialisation id: ");
    int specialisationId = scanner.nextInt();
    System.out.print("Enter years of experience: ");
    int yearsOfExperience = scanner.nextInt();
    System.out.print("Enter shift start time: ");
    String shiftStart = scanner.next();
    System.out.print("Enter shift end time: ");
    String shiftEnd = scanner.next();

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

    System.out.print("Enter doctor id: ");
    int id = scanner.nextInt();

    DoctorDTO doctor = doctorDAOImpl
      .getDoctorById(id)
      .orElse(null);

    if(Objects.isNull(doctor))  {
      System.err.println("Doctor records not present!");
      return;
    }

    doctor.print();

  }

  public void editDoctor() {

    System.out.println("EDIT DOCTOR");

    System.out.print("Enter doctor id: ");
    int id = scanner.nextInt();

    DoctorDTO doctor = doctorDAOImpl
      .getDoctorById(id)
      .orElse(null);

    if(Objects.isNull(doctor))  {
      System.err.println("Doctor records not present!");
      return;
    }

    doctor.print();

    System.out.println("Re-enter all details: ");
    System.out.print("Enter name of doctor: ");
    String name = scanner.nextLine();
    System.out.print("Enter specialisation id: ");
    int specialisationId = scanner.nextInt();
    System.out.print("Enter years of experience: ");
    int yearsOfExperience = scanner.nextInt();
    System.out.print("Enter isActive: ");
    Integer isActive = scanner.nextInt();
    System.out.print("Enter shift start time: ");
    String shiftStart = scanner.next();
    System.out.print("Enter shift end time: ");
    String shiftEnd = scanner.next();

    DoctorDTO updatedDoctor = DoctorDTO.builder()
      .id(doctor.getId())
      .name(name)
      .specialisationId(specialisationId)
      .yearsOfExperience(yearsOfExperience)
      .isActive(isActive)
      .shiftStart(shiftStart)
      .shiftEnd(shiftEnd)
      .build();

    doctorDAOImpl.updateDoctor(updatedDoctor);

  }

  public void removeDoctor() {

    System.out.println("REMOVE DOCTOR");

    System.out.print("Enter doctor id: ");
    int id = scanner.nextInt();

    doctorDAOImpl.deactivateDoctor(id);

  }

}
