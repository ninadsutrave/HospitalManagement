package main.java.HospitalManagementSystem.repository.database;

import main.java.HospitalManagementSystem.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

  private final DatabaseConfig databaseConfig;

  public DatabaseConnectionManager(DatabaseConfig databaseConfig) {
    this.databaseConfig = databaseConfig;
  }

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(
      databaseConfig.getUrl(),
      databaseConfig.getUser(),
      databaseConfig.getPassword()
    );
  }
}

