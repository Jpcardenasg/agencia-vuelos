package com.vuelosjanbi.trip.infrastructure.adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;
import java.sql.Date;
import java.util.List;

import com.vuelosjanbi.airport.application.AirportService;
import com.vuelosjanbi.airport.domain.models.Airport;
import com.vuelosjanbi.airport.infrastructure.adapters.out.MySQLAirportRepository;
import com.vuelosjanbi.trip.application.TripService;
import com.vuelosjanbi.trip.domain.models.Trip;
import com.vuelosjanbi.trip.infrastructure.adapters.out.MySQLTripRepository;

@Controller
public class TripConsoleAdapter {

  @Autowired
  private TripService tripService;
  @Autowired
  AirportService airportService;

  private final String url = "jdbc:mysql://localhost:3307/vuelosjanpi";
  private final String user = "root";
  private final String password = "1324";

  public void start(boolean jpa) {
    if (jpa) {
      System.out.println("Using JPA");
    } else {
      tripService = new TripService(
          new MySQLTripRepository(url, user, password, new MySQLAirportRepository(url, user, password)));
      airportService = new AirportService(new MySQLAirportRepository(url, user, password));
    }

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("1. Create Trip.");
      System.out.println("2. Update Trip.");
      System.out.println("3. Delete Trip.");
      System.out.println("4. List all Trips.");
      System.out.println("5. Exit.");

      int choice = scanner.nextInt();
      switch (choice) {
        case 1:
          createTrip(scanner, tripService, airportService);
          break;
        case 2:
          updateTrip(scanner, tripService, airportService);
          break;
        case 3:
          System.out.println("Enter trip id: ");
          long tripId2 = scanner.nextLong();
          tripService.deleteTripById(tripId2);
          break;
        case 4:
          tripService.getAllTrips().forEach(System.out::println);
          break;
        case 5:
          listTrips(tripService);
          scanner.close();
          return;
        default:
          System.out.println("Invalid choice.");
          break;
      }
    }
  }

  public static void createTrip(Scanner scanner, TripService tripService, AirportService airportService) {
    List<Airport> airports = airportService.getAllAirports();
    System.out.println("Enter trip day: ");
    String tripDay = scanner.next();
    System.out.println("Enter trip month: ");
    String tripMonth = scanner.next();
    System.out.println("Enter trip year: ");
    String tripYear = scanner.next();
    String tripDateStr = tripYear + "-" + tripMonth + "-" + tripDay;
    Date tripDate = Date.valueOf(tripDateStr);
    System.out.println("Enter trip price: ");
    scanner.nextLine();
    double tripPrice = scanner.nextDouble();
    for (Airport airport : airports) {
      System.out.println("Airport id: " + airport.getId() + " Airport name: " + airport.getName());
    }
    System.out.println("Enter origin airport id: ");
    long originAirportId = scanner.nextLong();
    for (Airport airport : airports) {
      System.out.println("Airport id: " + airport.getId() + " Airport name: " + airport.getName());
    }
    System.out.println("Enter destination airport id: ");
    long destinationAirportId = scanner.nextLong();
    Trip trip = new Trip(tripDate, tripPrice, airportService.getAirportById(originAirportId),
        airportService.getAirportById(destinationAirportId));
    tripService.createTrip(trip);
  }

  public static void updateTrip(Scanner scanner, TripService tripService, AirportService airportService) {
    List<Airport> airports = airportService.getAllAirports();
    System.out.println("Enter trip id: ");
    airports = airportService.getAllAirports();
    long tripId = scanner.nextLong();
    Trip trip = tripService.getTripById(tripId);
    if (trip == null) {
      System.out.println("Trip not found.");
      return;
    }
    System.out.println("Enter trip day: ");
    String tripDay2 = scanner.next();
    System.out.println("Enter trip month: ");
    String tripMonth2 = scanner.next();
    System.out.println("Enter trip year: ");
    String tripYear2 = scanner.next();
    String tripDateStr2 = tripYear2 + "-" + tripMonth2 + "-" + tripDay2;
    Date tripDate2 = Date.valueOf(tripDateStr2);
    System.out.println("Enter trip price: ");
    double tripPrice2 = scanner.nextDouble();
    for (Airport airport : airports) {
      System.out.println("Airport id: " + airport.getId() + " Airport name: " + airport.getName());
    }
    System.out.println("Enter origin airport id: ");
    long originAirportId2 = scanner.nextLong();
    for (Airport airport : airports) {
      System.out.println("Airport id: " + airport.getId() + " Airport name: " + airport.getName());
    }
    System.out.println("Enter destination airport id: ");
    long destinationAirportId2 = scanner.nextLong();
    trip.setTripDate(tripDate2);
    trip.setTripPrice(tripPrice2);
    trip.setOriginAirport(airportService.getAirportById(originAirportId2));
    trip.setDestinationAirport(airportService.getAirportById(destinationAirportId2));
    tripService.updateTrip(trip);
  }

  public static void listTrips(TripService tripService) {
    tripService.getAllTrips().forEach(System.out::println);
  }

}
