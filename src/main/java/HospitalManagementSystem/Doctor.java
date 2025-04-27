package main.java.HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
  private Connection connection;

  public Doctor(Connection connection) {
    this.connection = connection;
  }

  public void viewDoctor() {
    try {
      String query = "SELECT * from Doctors";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();

      System.out.println("Doctors: ");
      System.out.println("+----+------------------+--------------------+");
      System.out.println("| Id | Name             | Specialisation     |");
      System.out.println("+----+------------------+--------------------+");

      while(resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String specialisation = resultSet.getString("specialisation");

        System.out.printf("|%-4s|%-18s|%-20s|\n", id, name, specialisation);
        System.out.println("+----+------------------+--------------------+");
      }

    } catch (SQLException e) {
      System.err.println("SQLException occurred!");
      e.printStackTrace();
    } catch (Exception e) {
      System.err.println("Exception occurred while viewing doctor info!");
      e.printStackTrace();
    }
  }

  public boolean getDoctorById(int id) {

    try {
      String query = "SELECT * from Doctors where id = ?";
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
      System.err.println("Exception occurred while viewing doctor info!");
      e.printStackTrace();
    }

    return false;

  }
}
