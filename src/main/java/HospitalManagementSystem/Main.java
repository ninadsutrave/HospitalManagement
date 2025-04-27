package main.java.HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class Main {

  private static final String url = "jdbc:mysql://localhost:3306/mydatabase";
  private static final String username = "root";
  private static final String password = "password";

  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch(ClassNotFoundException e) {
      System.out.println("Class not found exception!");
      e.printStackTrace();
    } catch(Exception e) {
      System.out.println("Exception occurred!");
    }

    Scanner scanner = new Scanner(System.in);

    try {
      Connection connection = DriverManager.getConnection(url, username, password);
      Patient patient = new Patient(connection, scanner);
      Doctor doctor = new Doctor(connection);

      while(true) {
        System.out.println("HOSPITAL MANAGEMENT SYSTEM");

        System.out.println("1. Add Patient");
        System.out.println("2. View Patient");
        System.out.println("3. Vew Doctors");
        System.out.println("4. Book Appointment");
        System.out.println("5. Exit");

        int choice = scanner.nextInt();

        switch(choice) {
          case 1: patient.addPatient(); break;
          case 2: patient.viewPatient(); break;
          case 3: doctor.viewDoctor(); break;
          case 4: bookAppointment(patient, doctor, connection, scanner); break;
          case 5: return;
        }

      }
    } catch (SQLException e) {
      System.out.println("SQLException occurred!");
      e.printStackTrace();
    }

  }

  public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner) {

    System.out.print("Enter patient id: ");
    int patientId = scanner.nextInt();
    System.out.print("Enter doctor id: ");
    int doctorId = scanner.nextInt();

    System.out.print("Enter appointment date (YYYY-MM-DD): ");
    String appointmentDate = scanner.next();

    if(!patient.getPatientById(patientId)) {
      System.err.println("Invalid patient id!");
      return;
    }

    if(!doctor.getDoctorById(doctorId)) {
      System.err.println("Invalid doctor id!");
      return;
    }

    if(checkDoctorAvailability(appointmentDate, doctorId, connection)) {

      try {
        String query = "INSERT into Appointments(patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, patientId);
        preparedStatement.setInt(2, doctorId);
        preparedStatement.setString(3, appointmentDate);

        int rowsAffected = preparedStatement.executeUpdate();

        if(rowsAffected > 0) {
          System.out.println("Appointment created!");
          return;
        } else {
          System.err.println("Appointment creation failed!");
        }

      } catch(SQLException e) {
        System.err.println("SQLException occurred while creating appointment!");
        e.printStackTrace();
      }

    }

    System.out.println("Doctor not available on given date!");

  }

  public static boolean checkDoctorAvailability(String appointmentDate, int doctorId, Connection connection) {

    String query = "SELECT COUNT(*) FROM Appointments WHERE doctor_id = ? AND appointment_date = ?";

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, doctorId);
      preparedStatement.setString(2, appointmentDate);

      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()) {
        int count = resultSet.getInt(1);
        return (count == 0);
      }
    } catch(SQLException e) {
      System.err.println("SQL Exception while checking doctor availability!");
      e.printStackTrace();
    }

    return false;

  }

}
