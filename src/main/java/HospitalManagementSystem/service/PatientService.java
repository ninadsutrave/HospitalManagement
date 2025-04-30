package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.entity.PatientDTO;
import main.java.HospitalManagementSystem.repository.implementation.PatientDAOImpl;

import java.util.Scanner;

public class PatientService {

  PatientDAOImpl patientDAOImpl;

  public void registerPatient() {

    Scanner scanner = new Scanner(System.in);
    System.out.println("ADD NEW PATIENT");

    System.out.print("Enter name of patient: ");
    String name = scanner.next();
    System.out.print("Enter age of patient: ");
    int age = scanner.nextInt();
    System.out.print("Enter gender of patient: ");
    String gender = scanner.next();
    System.out.print("Enter phone number: ");
    String phoneNumber = scanner.next();

    PatientDTO patientDTO = PatientDTO.builder()
        .name(name)
        .age(age)
        .gender(gender)
        .phoneNumber(phoneNumber)
        .build();

    patientDAOImpl.insertPatient(patientDTO);

  }

}
