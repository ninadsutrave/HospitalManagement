package main.java.HospitalManagementSystem.util;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {
  private static final Scanner scanner = new Scanner(System.in);

  public static int readInt(String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        int value = scanner.nextInt();
        scanner.nextLine();

        return value;
      } catch (InputMismatchException e) {
        System.err.println("Invalid integer. Please try again.");
        e.printStackTrace();
      }
    }
  }

  public static String readLine(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine();
  }

  public static Time readTime(String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        String input = scanner.next();
        scanner.nextLine();

        LocalTime parsedTime = LocalTime.parse(input);
        return Time.valueOf(parsedTime);
      } catch (DateTimeParseException | IllegalArgumentException e) {
        System.err.println("Invalid time format. Use HH:mm (e.g., 09:30).");
        e.printStackTrace();
      }
    }
  }

  public static Date readDate(String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        String input = scanner.next();
        scanner.nextLine();

        LocalDate parsedDate = LocalDate.parse(input);
        return Date.valueOf(parsedDate);
      } catch (DateTimeParseException | IllegalArgumentException e) {
        System.err.println("Invalid date format. Use YYYY-MM-DD (e.g., 2025-05-03).");
        e.printStackTrace();
      }
    }
  }

  public static <E extends Enum<E>> E readEnum(String prompt, Class<E> enumType) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.nextLine().trim().toUpperCase();

      try {
        return Enum.valueOf(enumType, input);
      } catch (IllegalArgumentException e) {
        System.err.println("Invalid input. Options are: ");
        for (E constant : enumType.getEnumConstants()) {
          System.err.print(constant.name().charAt(0) + constant.name().substring(1).toLowerCase() + " ");
        }
        System.err.println();
      }
    }
  }

}
