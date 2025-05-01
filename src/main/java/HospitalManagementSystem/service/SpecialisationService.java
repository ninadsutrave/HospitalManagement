package main.java.HospitalManagementSystem.service;

import main.java.HospitalManagementSystem.entity.SpecialisationDTO;
import main.java.HospitalManagementSystem.repository.implementation.SpecialisationDAOImpl;

import java.util.List;
import java.util.Scanner;

public class SpecialisationService {

  SpecialisationDAOImpl specialisationDAOImpl;
  private final Scanner scanner = new Scanner(System.in);

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

    System.out.println("Enter a specialisation id: ");
    int specialisationChoice = scanner.nextInt();

    if(specialisationChoice < 0 || specialisationChoice < specialisationList.size()) {
      return null;
    }

    return specialisationChoice;

  }

}
