package main.java.HospitalManagementSystem;

import main.java.HospitalManagementSystem.service.MenuService;

public class HospitalManagementSystem {

  public static void main(String[] args) {

    MenuService menuService = new MenuService();
    menuService.showWelcomeScreen();

  }

}
