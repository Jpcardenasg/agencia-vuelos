package com.vuelosjanbi.flightFare.infrastructure.adapters.in;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.flightFare.application.FlightFareService;
import com.vuelosjanbi.flightFare.domain.models.FlightFare;
import com.vuelosjanbi.flightFare.infrastructure.adapters.out.MySQLFlightFareRepository;

@Controller
public class FlightFareConsoleAdapter {

  @Autowired
  private FlightFareService flightFareService;

  private final String url = "jdbc:mysql://localhost:3307/vuelosjanpi";
  private final String user = "root";
  private final String password = "1324";

  public void start(boolean jpa) {
    if (jpa) {
      System.out.println("Using JPA");
    } else {
      System.out.println("Using MySQL Manual Queries");
      flightFareService = new FlightFareService(new MySQLFlightFareRepository(url, user, password));
    }

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("1. Create Flight Fare");
      System.out.println("2. Get Flight Fare");
      System.out.println("3. Update Flight Fare");
      System.out.println("4. Delete Flight Fare");
      System.out.println("5. Get All Flight Fares");
      System.out.println("6. Exit");

      switch (scanner.nextInt()) {
        case 1:
          createFlightFare(scanner);
          break;
        case 2:
          getFlightFare(scanner);
          break;
        case 3:
          updateFlightFare(scanner);
          break;
        case 4:
          deleteFlightFare(scanner);
          break;
        case 5:
          getAllFlightFares();
          break;
        case 6:
          System.exit(0);
          break;
        default:
          break;
      }
    }

  }

  private void getAllFlightFares() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllFlightFares'");

  }

  private void deleteFlightFare(Scanner scanner) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteFlightFare'");
  }

  private void updateFlightFare(Scanner scanner) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateFlightFare'");
  }

  private void getFlightFare(Scanner scanner) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getFlightFare'");
  }

  private void createFlightFare(Scanner scanner) {
    System.out.println("Type description the flight fare: ");
    String description = scanner.next();
    System.out.println("Type details flight fare: ");
    String details = scanner.next();
    System.out.println("Type price the flight fare: ");
    Double value = scanner.nextDouble();
    FlightFare newFlightFare = new FlightFare(description, details, value);
    flightFareService.createFlightFare(newFlightFare);
  }

}
