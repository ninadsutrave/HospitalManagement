package main.java.HospitalManagementSystem.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {

  private static Connection connection;

  private static String URL;
  private static String USER;
  private static String PASSWORD;

  public DatabaseConfig() {
    try {
      Properties properties = new Properties();
      String configFilePath = "resources/config.properties"; // Adjust the path if necessary
      FileInputStream fileInputStream = new FileInputStream(configFilePath);
      properties.load(fileInputStream);

      URL = properties.getProperty("db.url");
      USER = properties.getProperty("db.user");
      PASSWORD = properties.getProperty("db.password");

      Class.forName("com.mysql.cj.jdbc.Driver");

    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Error while loading the database configuration: " + e.getMessage());
      e.printStackTrace();
    } catch (Exception e) {
      System.err.println("Unexpected error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static Connection getConnection() {
    try {
      if (connection == null || connection.isClosed()) {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
      }
      return connection;
    } catch (SQLException e) {
      System.err.println("Error while establishing database connection: " + e.getMessage());
      e.printStackTrace();
    } catch (Exception e) {
      System.err.println("Unexpected error: " + e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  public static void closeConnection() {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException e) {
      System.err.println("Error while closing connection: " + e.getMessage());
      e.printStackTrace();
    } catch (Exception e) {
      System.err.println("Unexpected error during closing connection: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
