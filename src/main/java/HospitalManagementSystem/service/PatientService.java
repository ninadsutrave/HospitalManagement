package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.dao.interfaces.PatientDAO;
import main.java.HospitalManagementSystem.entity.PatientDTO;
import main.java.HospitalManagementSystem.dao.implementation.PatientDAOImpl;
import main.java.HospitalManagementSystem.util.InputUtil;

public class PatientService {

  PatientDAO patientDAOImpl;

  public PatientService() {
    patientDAOImpl = new PatientDAOImpl();
  }

  public void registerPatient() {

    System.out.println("ADD NEW PATIENT");

    String name = InputUtil.readLine("Enter name of patient: ");
    int age = InputUtil.readInt("Enter age of patient: ");
    String gender = InputUtil.readLine("Enter gender of patient: ");
    String phoneNumber = InputUtil.readLine("Enter phone number: ");

    PatientDTO patient = PatientDTO.builder()
      .name(name)
      .age(age)
      .gender(gender)
      .phoneNumber(phoneNumber)
      .build();

    // print patient id
    patientDAOImpl.insertPatient(patient);

  }

  public void viewPatient() {

    System.out.println("VIEW PATIENT");

    int id = InputUtil.readInt("Enter patient id: ");

    PatientDTO patient = patientDAOImpl
      .getPatientById(id)
      .orElse(null);

    if (patient == null) {
      System.err.println("Patient records not present!");
      return;
    }

    patient.print();
  }

  public void editPatient() {

    System.out.println("EDIT PATIENT");

    int id = InputUtil.readInt("Enter patient id: ");

    PatientDTO patient = patientDAOImpl
      .getPatientById(id)
      .orElse(null);

    if (patient == null) {
      System.err.println("Patient records not present!");
      return;
    }

    patient.print();

    System.out.println("Enter updated details: ");
    String name = InputUtil.readLine("Enter name of patient: ");
    int age = InputUtil.readInt("Enter age of patient: ");
    String gender = InputUtil.readLine("Enter gender of patient: ");
    String phoneNumber = InputUtil.readLine("Enter phone number: ");
    int isActive = InputUtil.readInt("Enter isActive: ");

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

    int id = InputUtil.readInt("Enter patient id: ");
    patientDAOImpl.deactivatePatient(id);

  }


}
