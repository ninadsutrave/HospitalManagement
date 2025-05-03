package main.java.HospitalManagementSystem.dao.interfaces;

import main.java.HospitalManagementSystem.entity.PatientDTO;

import java.util.Optional;

public interface PatientDAO {

  boolean insertPatient(PatientDTO patient);

  Optional<PatientDTO> getPatientById(int id);

  boolean updatePatient(PatientDTO updatedPatient);

  boolean deactivatePatient(int id);

}
