package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.dao.interfaces.SpecialisationDAO;
import main.java.HospitalManagementSystem.entity.SpecialisationDTO;
import main.java.HospitalManagementSystem.dao.implementation.SpecialisationDAOImpl;
import main.java.HospitalManagementSystem.util.InputUtil;

import java.util.List;

public class SpecialisationService {

  SpecialisationDAO specialisationDAOImpl;

  public SpecialisationService() {
    specialisationDAOImpl = new SpecialisationDAOImpl();
  }

  public Integer getSpecialisationChoice() {

    List<SpecialisationDTO> specialisationList = specialisationDAOImpl
      .getSpecialisationList()
      .orElse(List.of());

    if(specialisationList.isEmpty()) {
      return null;
    }

    for(SpecialisationDTO specialisation: specialisationList) {
      specialisation.print();
    }

    int specialisationChoice = InputUtil.readInt("Enter a specialisation id: ");
    if(specialisationChoice <= 0 || specialisationChoice > specialisationList.size()) {
      return null;
    }

    return specialisationChoice;

  }

}
