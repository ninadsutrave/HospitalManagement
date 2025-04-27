package main.java.HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
  private Connection connection;
  private Scanner scanner;

  public Patient(Connection connection, Scanner scanner) {
    this.connection = connection;
    this.scanner = scanner;
  }

  public void addPatient() {
    System.out.print("Enter name of patient: ");
    String name = scanner.next();
    System.out.print("Enter age of patient: ");
    int age = scanner.nextInt();
    System.out.print("Enter gender of patient: ");
    String gender = scanner.next();

    try {
      String query = "INSERT INTO Patients(name, age, gender) values(?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, name);
      preparedStatement.setInt(2, age);
      preparedStatement.setString(3, gender);

      int affectedRows = preparedStatement.executeUpdate();

      if(affectedRows > 0) {
        System.out.println("Patient added successfully!");
      } else {
        System.err.println("Patient insert failed!");
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred!");
      e.printStackTrace();
    } catch (Exception e) {
      System.err.println("Exception occurred while inserting patient!");
      e.printStackTrace();
    }

  }

  public void viewPatient() {
    try {
      String query = "SELECT * from Patients";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();

      System.out.println("Patients: ");
      System.out.println("+----+------------------+------+--------+");
      System.out.println("| Id | Name             | Age  | Gender |");
      System.out.println("+----+------------------+------+--------+");

      while(resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        String gender = resultSet.getString("gender");
        System.out.printf("|%-4s|%-18s|%-6s|%-8s|\n", id, name, age, gender);
        System.out.println("+----+------------------+------+--------+");
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred!");
      e.printStackTrace();
    } catch (Exception e) {
      System.err.println("Exception occurred while viewing patient info!");
      e.printStackTrace();
    }
  }

  public boolean getPatientById(int id) {

    try {
      String query = "SELECT * from Patients where id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);

      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()) {
        return true;
      } else {
        return false;
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred!");
      e.printStackTrace();
    } catch (Exception e) {
      System.err.println("Exception occurred while viewing patient info!");
      e.printStackTrace();
    }

    return false;

  }
}
