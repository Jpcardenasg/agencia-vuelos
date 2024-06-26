package com.vuelosjanbi.trip.infrastructure.adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;
import java.sql.Date;
import java.util.List;

import com.vuelosjanbi.airport.application.AirportService;
import com.vuelosjanbi.airport.domain.models.Airport;
import com.vuelosjanbi.airport.infrastructure.adapters.out.MySQLAirportRepository;
import com.vuelosjanbi.flightConnection.application.FlightConnectionService;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;
import com.vuelosjanbi.trip.application.TripService;
import com.vuelosjanbi.trip.domain.models.Trip;
import com.vuelosjanbi.trip.infrastructure.adapters.out.MySQLTripRepository;

@Controller
public class TripConsoleAdapter {

  @Autowired
  private TripService tripService;
  @Autowired
  AirportService airportService;
  @Autowired
  private FlightConnectionService flightConnectionService;

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
      System.out.println("2. Look availeble trips for origin, destination and date.");
      System.out.println("3. Update Trip.");
      System.out.println("4. Delete Trip.");
      System.out.println("5. List all Trips.");
      System.out.println("6. Consult information trip.");
      System.out.println("7 Consult scales of a trip.");
      System.out.println("8. Exit.");

      int choice = scanner.nextInt();
      switch (choice) {
        case 1:
          createTrip(scanner, tripService, airportService);
          break;
        case 2:
          lookAvailebleTripsForOriginDestinationAndDate(scanner);
          break;
        case 3:
          updateTrip(scanner, tripService, airportService);
          break;
        case 4:
          List<Trip> trips = tripService.getAllTrips();
          System.out.println("Enter trip id: ");
          for (Trip trip : trips) {
            System.out.println("Trip id: " + trip.getId() + " Trip date: " + trip.getTripDate() + " Trip price: "
                + trip.getTripPrice());
          }
          long tripId2 = scanner.nextLong();

          if (tripService.getTripById(tripId2) == null) {
            System.out.println("Error Trip not found.");
          } else {
            System.out.println("Trip deleted.");
          }
          tripService.deleteTripById(tripId2);
          break;
        case 5:
          tripService.getAllTrips().forEach(System.out::println);
          break;
        case 6:
          System.out.println("Enter trip id: ");
          long tripId3 = scanner.nextLong();
          Trip trip = tripService.getTripById(tripId3);
          String tripDate = trip.getTripDate().toString();
          if (tripService.getTripById(tripId3) == null) {
            System.out.println("Error Trip not found.");
          }
          FlightConnection flightConnection = flightConnectionService.getConnectionByTripId(tripId3).orElse(null);
          System.out.printf("Id: %d , price: %d, Date: %s,  Origin Airport: %s, Destination Airport: %s", trip.getId(),
              trip.getTripPrice(), tripDate, flightConnection.getOriginAirport().getName(),
              flightConnection.getDestinationAirport().getName());
          break;
        case 7:

          break;
        case 8:
          scanner.close();
          return;
        default:
          System.out.println("Invalid choice.");
          break;
      }
    }
  }

  public void lookAvailebleTripsForOriginDestinationAndDate(Scanner scanner) {
    System.out.println("Enter origin city name: ");
    String originCityName = scanner.next();
    System.out.println("Enter destination city name: ");
    String destinationCityName = scanner.next();
    System.out.println("Enter trip day: ");
    String tripDay = scanner.next();
    System.out.println("Enter trip month: ");
    String tripMonth = scanner.next();
    System.out.println("Enter trip year: ");
    String tripYear = scanner.next();
    String tripDate = tripYear + "-" + tripMonth + "-" + tripDay;
    List<Trip> trips = tripService.getTripsByOriginCityAndFinalDestinationCityWithStopover(originCityName,
        destinationCityName,
        tripDate);
    for (Trip trip : trips) {
      System.out.println(trip);
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
    System.out.println("What trip do you want to update?");
    List<Trip> trips = tripService.getAllTrips();
    for (Trip trip : trips) {
      System.out.println("Trip id: " + trip.getId() + " Trip date: " + trip.getTripDate() + " Trip price: "
          + trip.getTripPrice());
    }
    System.out.println("Enter trip id: ");
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
    // for (Airport airport : airports) {
    // System.out.println("Airport id: " + airport.getId() + " Airport name: " +
    // airport.getName());
    // }
    // System.out.println("Enter origin airport id: ");
    // long originAirportId2 = scanner.nextLong();
    // for (Airport airport : airports) {
    // System.out.println("Airport id: " + airport.getId() + " Airport name: " +
    // airport.getName());
    // }
    // System.out.println("Enter destination airport id: ");
    // long destinationAirportId2 = scanner.nextLong();
    trip.setTripDate(tripDate2);
    trip.setTripPrice(tripPrice2);
    tripService.updateTrip(trip);
  }

  public static void listTrips(TripService tripService) {
    tripService.getAllTrips().forEach(System.out::println);
  }

  public void consulFlightConnectionsByTrip(Scanner scanner) {
    List<Trip> trips = tripService.getAllTrips();
    for (Trip trip : trips) {
      System.out.println("Trip id: " + trip.getId() + " Trip date: " + trip.getTripDate() + " Trip price: "
          + trip.getTripPrice());
    }
    System.out.println("Enter trip id: ");

    long tripId = scanner.nextLong();
    Trip trip = tripService.getTripById(tripService.getTripById(tripId).getId());
    if (trip == null) {
      System.out.println("Trip not found.");
      return;
    }
    List<FlightConnection> flightConnections = flightConnectionService.getConnectionByTripId(trip);
    for (FlightConnection flightConnection : flightConnections) {
      System.out.println(flightConnection);
    }
  }

}
