package main.java.HospitalManagementSystem.dao.interfaces;

import main.java.HospitalManagementSystem.entity.SpecialisationDTO;

import java.util.List;
import java.util.Optional;

public interface SpecialisationDAO {

  Optional<List<SpecialisationDTO>> getSpecialisationList();

}
