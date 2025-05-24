package main.java.HospitalManagementSystem;

import main.java.HospitalManagementSystem.manager.MenuManager;

public class HospitalManagementSystem {

  public static void main(String[] args) {

    MenuManager menuService = new MenuManager();
    menuService.showWelcomeScreen();

  }

}
