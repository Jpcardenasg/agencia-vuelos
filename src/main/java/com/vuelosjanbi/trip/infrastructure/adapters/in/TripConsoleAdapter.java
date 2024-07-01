package com.vuelosjanbi.trip.infrastructure.adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;
import java.sql.Date;
import java.util.List;

import com.vuelosjanbi.airport.application.AirportService;
import com.vuelosjanbi.airport.domain.models.Airport;
import com.vuelosjanbi.flightConnection.application.FlightConnectionService;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;
import com.vuelosjanbi.flightConnection.infrastructure.adapters.in.FlightConnectionConsoleAdapter;
import com.vuelosjanbi.trip.application.TripService;
import com.vuelosjanbi.trip.domain.models.Trip;

@Controller
public class TripConsoleAdapter {

  @Autowired
  private TripService tripService;
  @Autowired
  private AirportService airportService;
  @Autowired
  private FlightConnectionService flightConnectionService;
  @Autowired
  private FlightConnectionConsoleAdapter flightConnectionConsoleAdapter;

  public void start() {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      printMenu();
      int choice = getInputInt(scanner, "Choose an option: ");
      switch (choice) {
        case 1 -> createTrip(scanner);
        case 2 -> flightConnectionConsoleAdapter.start();
        case 3 -> updateTrip(scanner);
        case 4 -> deleteTrip(scanner);
        case 5 -> listTrips();
        case 6 -> consultTripInfo(scanner);
        case 7 -> consultFlightConnectionsByTrip(scanner);
        case 0 -> {
          System.out.println("Exiting trip console...");
          return;
        }
        default -> System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private void printMenu() {
    System.out.println("\n");
    System.out.println("1. Create Trip.");
    System.out.println("2. Manage flight Connections.");
    System.out.println("3. Update Trip.");
    System.out.println("4. Delete Trip.");
    System.out.println("5. List all Trips.");
    System.out.println("6. Consult information trip.");
    System.out.println("7. Consult scales of a trip.");
    System.out.println("0. Exit.");
  }

  private void createTrip(Scanner scanner) {
    List<Airport> airports = airportService.getAllAirports();
    Date tripDate = getInputDate(scanner, "Enter trip date (YYYY-MM-DD): ");
    double tripPrice = getInputDouble(scanner, "Enter trip price: ");

    airports
        .forEach(
            airport -> System.out.printf("Airport id: %d  Airport name: %s%n", airport.getId(), airport.getName()));
    long originAirportId = getInputLong(scanner, "Enter origin airport id: ");
    long destinationAirportId = getInputLong(scanner, "Enter destination airport id: ");

    Trip trip = new Trip(tripDate, tripPrice, airportService.getAirportById(originAirportId).orElse(null),
        airportService.getAirportById(destinationAirportId).orElse(null));
    tripService.createTrip(trip);
    System.out.println("Trip created successfully.");
    flightConnectionConsoleAdapter.start();
  }

  private void updateTrip(Scanner scanner) {
    listTrips();
    long tripId = getInputLong(scanner, "Enter trip id to update: ");
    Trip trip = tripService.getTripById(tripId);
    if (trip == null) {
      System.out.println("Trip not found.");
      return;
    }

    Date tripDate = getInputDate(scanner, "Enter new trip date (YYYY-MM-DD): ");
    double tripPrice = getInputDouble(scanner, "Enter new trip price: ");
    trip.setTripDate(tripDate);
    trip.setTripPrice(tripPrice);
    tripService.updateTrip(trip);
    System.out.println("Trip updated successfully.");
  }

  private void deleteTrip(Scanner scanner) {
    listTrips();
    long tripId = getInputLong(scanner, "Enter trip id to delete: ");
    if (tripService.getTripById(tripId) != null) {
      tripService.deleteTripById(tripId);
      System.out.println("Trip deleted successfully.");
    } else {
      System.out.println("Trip not found.");
    }
  }

  private void listTrips() {
    tripService.getAllTrips().forEach(System.out::println);
  }

  private void consultTripInfo(Scanner scanner) {
    long tripId = getInputLong(scanner, "Enter trip id: ");
    Trip trip = tripService.getTripById(tripId);
    if (trip == null) {
      System.out.println("Trip not found.");
      return;
    }

    FlightConnection flightConnection = flightConnectionService.getConnectionByTripId(tripId).orElse(null);
    if (flightConnection == null) {
      System.out.println("Flight connection not found.");
      System.out.printf("Id: %d  price: %.2f  Date: %s\n",
          trip.getId(), trip.getTripPrice(), trip.getTripDate());
      return;
    }
    System.out.printf("Id: %d  price: %.2f  Date: %s  Origin Airport: %s  Destination Airport: %s%n",
        trip.getId(), trip.getTripPrice(), trip.getTripDate(), flightConnection.getOriginAirport().getName(),
        flightConnection.getDestinationAirport().getName());
  }

  private void consultFlightConnectionsByTrip(Scanner scanner) {
    listTrips();
    long tripId = getInputLong(scanner, "Enter trip id: ");
    Trip trip = tripService.getTripById(tripId);
    if (trip == null) {
      System.out.println("Trip not found.");
      return;
    }

    List<FlightConnection> flightConnections = flightConnectionService.getConnectionByTripId(trip);
    if (flightConnections == null || flightConnections.isEmpty()) {
      System.out.println("There are not scales for this trip.");
      return;
    }
    flightConnections.forEach(System.out::println);
  }

  // Helper methods for input
  private int getInputInt(Scanner scanner, String prompt) {
    System.out.print(prompt);
    while (!scanner.hasNextInt()) {
      System.out.print("Invalid input. " + prompt);
      scanner.next();
    }
    return scanner.nextInt();
  }

  private long getInputLong(Scanner scanner, String prompt) {
    System.out.print(prompt);
    while (!scanner.hasNextLong()) {
      System.out.print("Invalid input. " + prompt);
      scanner.next();
    }
    return scanner.nextLong();
  }

  private double getInputDouble(Scanner scanner, String prompt) {
    System.out.print(prompt);
    while (!scanner.hasNextDouble()) {
      System.out.print("Invalid input. " + prompt);
      scanner.next();
    }
    return scanner.nextDouble();
  }

  // private String getInputString(Scanner scanner, String prompt) {
  // System.out.print(prompt);
  // return scanner.next();
  // }

  private Date getInputDate(Scanner scanner, String prompt) {
    System.out.print(prompt);
    String dateStr = scanner.next();
    try {
      return Date.valueOf(dateStr);
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid date format. Please enter in YYYY-MM-DD format.");
      return getInputDate(scanner, prompt);
    }
  }
}
