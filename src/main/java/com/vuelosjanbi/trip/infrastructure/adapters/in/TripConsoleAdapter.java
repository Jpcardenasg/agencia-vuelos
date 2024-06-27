package com.vuelosjanbi.trip.infrastructure.adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.vuelosjanbi.airport.application.AirportService;
import com.vuelosjanbi.airport.domain.models.Airport;
import com.vuelosjanbi.customer.domain.models.Customer;
import com.vuelosjanbi.flightConnection.application.FlightConnectionService;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;
import com.vuelosjanbi.flightFare.application.FlightFareService;
import com.vuelosjanbi.flightFare.domain.models.FlightFare;
import com.vuelosjanbi.trip.application.TripService;
import com.vuelosjanbi.trip.domain.models.Trip;
import com.vuelosjanbi.tripBooking.application.TripBookingService;
import com.vuelosjanbi.tripBooking.domain.models.TripBooking;
import com.vuelosjanbi.tripBookingDetail.application.TripBookingDetailService;
import com.vuelosjanbi.tripBookingDetail.domain.models.TripBookingDetail;

@Controller
public class TripConsoleAdapter {

  @Autowired
  private TripService tripService;
  @Autowired
  private AirportService airportService;
  @Autowired
  private FlightConnectionService flightConnectionService;
  @Autowired
  private FlightFareService flightFareService;
  @Autowired
  private TripBookingService tripBookingService;
  @Autowired
  private TripBookingDetailService tripBookingDetailService;

  // private final String url = "jdbc:mysql://localhost:3307/vuelosjanpi";
  // private final String user = "root";
  // private final String password = "1324";

  public void start(boolean jpa) {
    if (jpa) {
      System.out.println("Using JPA repository.");
    }

    try (Scanner scanner = new Scanner(System.in)) {
      while (true) {
        printMenu();
        int choice = getInputInt(scanner, "Choose an option: ");
        switch (choice) {
          case 1 -> createTrip(scanner);
          case 2 -> lookAvailableTrips(scanner);
          case 3 -> updateTrip(scanner);
          case 4 -> deleteTrip(scanner);
          case 5 -> listTrips();
          case 6 -> consultTripInfo(scanner);
          case 7 -> consultFlightConnectionsByTrip(scanner);
          case 8 -> {
            System.out.println("Exiting...");
            return;
          }
          default -> System.out.println("Invalid choice. Please try again.");
        }
      }
    }
  }

  private void printMenu() {
    System.out.println("\nPlane Manufacturer Management Menu:");
    System.out.println("1. Create Trip.");
    System.out.println("2. Look available trips for origin, destination, and date.");
    System.out.println("3. Update Trip.");
    System.out.println("4. Delete Trip.");
    System.out.println("5. List all Trips.");
    System.out.println("6. Consult information trip.");
    System.out.println("7. Consult scales of a trip.");
    System.out.println("8. Exit.");
  }

  private void createTrip(Scanner scanner) {
    List<Airport> airports = airportService.getAllAirports();
    Date tripDate = getInputDate(scanner, "Enter trip date (YYYY-MM-DD): ");
    double tripPrice = getInputDouble(scanner, "Enter trip price: ");

    airports
        .forEach(airport -> System.out.printf("Airport id: %d Airport name: %s%n", airport.getId(), airport.getName()));
    long originAirportId = getInputLong(scanner, "Enter origin airport id: ");
    long destinationAirportId = getInputLong(scanner, "Enter destination airport id: ");

    Trip trip = new Trip(tripDate, tripPrice, airportService.getAirportById(originAirportId),
        airportService.getAirportById(destinationAirportId));
    tripService.createTrip(trip);
    System.out.println("Trip created successfully.");
  }

  private void lookAvailableTrips(Scanner scanner) {
    String originCityName = getInputString(scanner, "Enter origin city name: ");
    String destinationCityName = getInputString(scanner, "Enter destination city name: ");
    Date tripDate = getInputDate(scanner, "Enter trip date (YYYY-MM-DD): ");

    List<Trip> trips = tripService.getTripsByOriginCityAndFinalDestinationCityWithStopover(originCityName,
        destinationCityName, tripDate.toString());
    trips.forEach(System.out::println);

    if (getInputString(scanner, "Do you want to book a trip? (y/n): ").equalsIgnoreCase("y")) {
      bookTrip(scanner, trips);
    }
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
      return;
    }

    System.out.printf("Id: %d, price: %.2f, Date: %s, Origin Airport: %s, Destination Airport: %s%n",
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
    flightConnections.forEach(System.out::println);
  }

  private void bookTrip(Scanner scanner, List<Trip> trips) {
    long tripId = getInputLong(scanner, "Enter trip id: ");
    Trip trip = tripService.getTripById(tripId);
    if (trip == null) {
      System.out.println("Trip not found.");
      return;
    }

    TripBooking tripBooking = new TripBooking(Date.valueOf(LocalDate.now()), trip);
    while (true) {
      Customer customer = new Customer();
      String passengerName = getInputString(scanner, "Enter passenger name: ");
      int age = getInputInt(scanner, "Enter passenger age: ");
      String passengerDocument = getInputString(scanner, "Enter passenger document: ");
      customer.setName(passengerName);
      customer.setAge(age);
      customer.setId(passengerDocument);

      TripBookingDetail tripBookingDetail = new TripBookingDetail();
      tripBookingDetail.setCustomer(customer);
      tripBookingDetail.setTripBooking(tripBooking);

      List<FlightFare> flightFares = flightFareService.getAllFlightFares();
      flightFares.forEach(flightFare -> System.out.printf("Flight fare id: %d Flight fare price: %.2f%n",
          flightFare.getId(), flightFare.getValue()));
      long flightFareId = getInputLong(scanner, "Enter flight fare id: ");
      FlightFare flightFare = flightFareService.getFlightFareById(flightFareId).orElse(null);
      if (flightFare == null) {
        System.out.println("Flight fare not found.");
        return;
      }

      FlightConnection flightConnection = flightConnectionService.getConnectionByTripId(tripId).orElse(null);
      if (flightConnection == null) {
        System.out.println("Flight connection not found.");
        return;
      }

      System.out.println("Select available seat number: ");
      flightConnection.getSeats().forEach(seat -> {
        if (seat.isAvailable()) {
          System.out.println("Seat number: " + seat.getSeatNumber());
        }
      });

      int seatNumber = getInputInt(scanner, "Enter seat number: ");
      flightConnection.getSeats().forEach(seat -> {
        if (seat.getSeatNumber().equals(String.valueOf(seatNumber))) {
          seat.setAvailable(false);
        }
      });

      tripBookingDetail.setSeatNumber(seatNumber);
      tripBookingDetail.setFlightFare(flightFare);
      tripBookingService.createTripBooking(tripBooking);
      tripBookingDetailService.createTripBookingDetail(tripBookingDetail);

      if (getInputString(scanner, "Do you want to add another passenger? (y/n): ").equalsIgnoreCase("n")) {
        processPayment(scanner);
        break;
      }
    }
  }

  private void processPayment(Scanner scanner) {
    int paymentMethod = getInputInt(scanner, "How do you want to pay? (1. Credit card, 2. Cash): ");
    switch (paymentMethod) {
      case 1 -> {
        String creditCardNumber = getInputString(scanner, "Enter credit card number: ");
        String expirationDate = getInputString(scanner, "Enter credit card expiration date (MM/YY): ");
        String securityCode = getInputString(scanner, "Enter credit card security code: ");
        String ownerName = getInputString(scanner, "Enter credit card owner name: ");
        String ownerDocument = getInputString(scanner, "Enter credit card owner document: ");
        String ownerPhone = getInputString(scanner, "Enter credit card owner phone: ");
        System.out.printf("Processing payment with credit card ending in %s%n",
            creditCardNumber.substring(creditCardNumber.length() - 4));
      }
      case 2 -> {
        double cashAmount = getInputDouble(scanner, "Enter cash amount: ");
        System.out.printf("Processing cash payment of %.2f%n", cashAmount);
      }
      default -> System.out.println("Invalid payment method.");
    }
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

  private String getInputString(Scanner scanner, String prompt) {
    System.out.print(prompt);
    return scanner.next();
  }

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
