package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.dao.interfaces.PatientDAO;
import main.java.HospitalManagementSystem.entity.Gender;
import main.java.HospitalManagementSystem.entity.PatientDTO;
import main.java.HospitalManagementSystem.dao.implementation.PatientDAOImpl;
import main.java.HospitalManagementSystem.util.InputUtil;

import java.sql.Date;

public class PatientService {

  PatientDAO patientDAOImpl;

  public PatientService() {
    patientDAOImpl = new PatientDAOImpl();
  }

  public void registerPatient() {

    System.out.println("ADD NEW PATIENT");

    String name = InputUtil.readLine("Enter name: ");
    Date dateOfBirth = InputUtil.readDate("Enter DOB (YYYY-MM-DD): ");
    Gender gender = InputUtil.readEnum("Enter gender (MALE/FEMALE/OTHER): ", Gender.class);
    String phoneNumber = InputUtil.readLine("Enter phone number: ");

    PatientDTO patient = PatientDTO.builder()
      .name(name)
      .dateOfBirth(dateOfBirth)
      .gender(gender)
      .phoneNumber(phoneNumber)
      .build();

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
    String name = InputUtil.readLine("Enter name: ");
    Date dateOfBirth = InputUtil.readDate("Enter DOB (YYYY-MM-DD): ");
    Gender gender = InputUtil.readEnum("Enter gender (MALE/FEMALE/OTHER): ", Gender.class);
    String phoneNumber = InputUtil.readLine("Enter phone number: ");

    PatientDTO updatedPatient = PatientDTO.builder()
      .id(patient.getId())
      .name(name)
      .dateOfBirth(dateOfBirth)
      .gender(gender)
      .phoneNumber(phoneNumber)
      .isActive(patient.getIsActive())
      .build();

    patientDAOImpl.updatePatient(updatedPatient);

  }

  public void removePatient() {

    System.out.println("REMOVE PATIENT");

    int id = InputUtil.readInt("Enter patient id: ");
    patientDAOImpl.deactivatePatient(id);

  }

}
