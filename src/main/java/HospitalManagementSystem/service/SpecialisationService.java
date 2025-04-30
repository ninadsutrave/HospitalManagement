package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.repository.dao.SpecialisationDAO;
import main.java.HospitalManagementSystem.repository.implementation.SpecialisationDAOImpl;

import java.util.List;
import java.util.Optional;

public class SpecialisationService {

  SpecialisationDAOImpl specialisationDAOImpl;

  public List<SpecialisationDAO> viewSpecialisationList() {

    return specialisationDAOImpl
            .getSpecialisationList()
            .orElse(List.of());

  }

}
