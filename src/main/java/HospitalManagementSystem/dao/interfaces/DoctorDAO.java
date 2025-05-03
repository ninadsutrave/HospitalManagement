package main.java.HospitalManagementSystem.dao.interfaces;

import main.java.HospitalManagementSystem.entity.DoctorDTO;

import java.util.List;
import java.util.Optional;

public interface DoctorDAO {

  boolean insertDoctor(DoctorDTO doctor);

  Optional<DoctorDTO> getDoctorById(int id);

  Optional<List<DoctorDTO>> getDoctorBySpecialisation(Integer specialisationId);

  boolean updateDoctor(DoctorDTO updatedDoctor);

  boolean deactivateDoctor(int id);

}
