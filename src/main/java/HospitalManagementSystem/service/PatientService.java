package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.entity.PatientDTO;
import main.java.HospitalManagementSystem.repository.implementation.PatientDAOImpl;

import java.util.Objects;
import java.util.Scanner;

public class PatientService {

  PatientDAOImpl patientDAOImpl;
  private final Scanner scanner = new Scanner(System.in);

  public void registerPatient() {

    System.out.println("ADD NEW PATIENT");

    System.out.print("Enter name of patient: ");
    String name = scanner.next();
    System.out.print("Enter age of patient: ");
    int age = scanner.nextInt();
    System.out.print("Enter gender of patient: ");
    String gender = scanner.next();
    System.out.print("Enter phone number: ");
    String phoneNumber = scanner.next();

    PatientDTO patient = PatientDTO.builder()
        .name(name)
        .age(age)
        .gender(gender)
        .phoneNumber(phoneNumber)
        .build();

    patientDAOImpl.insertPatient(patient);

  }

  public void viewPatient() {

    System.out.println("VIEW PATIENT");

    System.out.print("Enter patient id: ");
    int id = scanner.nextInt();

    PatientDTO patient = patientDAOImpl
      .getPatientById(id)
      .orElse(null);

    if(Objects.isNull(patient))  {
      System.err.println("Patient records not present!");
      return;
    }

    patient.print();

  }

  public void editPatient() {

    System.out.println("EDIT PATIENT");

    System.out.print("Enter patient id: ");
    int id = scanner.nextInt();

    PatientDTO patient = patientDAOImpl
      .getPatientById(id)
      .orElse(null);

    if(Objects.isNull(patient))  {
      System.err.println("Patient records not present!");
      return;
    }

    patient.print();

    System.out.println("Re-enter all details: ");
    System.out.print("Enter name of patient: ");
    String name = scanner.next();
    System.out.print("Enter age of patient: ");
    int age = scanner.nextInt();
    System.out.print("Enter gender of patient: ");
    String gender = scanner.next();
    System.out.print("Enter phone number: ");
    String phoneNumber = scanner.next();
    System.out.print("Enter isActive: ");
    boolean isActive = scanner.nextBoolean();

    PatientDTO updatedPatient = PatientDTO.builder()
      .id(patient.getId())
      .name(name)
      .age(age)
      .gender(gender)
      .phoneNumber(phoneNumber)
      .isActive(isActive)
      .build();


    patientDAOImpl.updatePatient(updatedPatient);

  }

  public void removePatient() {

    System.out.println("REMOVE PATIENT");

    System.out.print("Enter patient id: ");
    int id = scanner.nextInt();

    patientDAOImpl.deactivatePatient(id);

  }


}
