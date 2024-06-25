package com.vuelosjanbi.tripBooking.infrastructure.adapters.in;

import java.sql.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.customer.application.CustomerService;
import com.vuelosjanbi.customer.infrastructure.adapters.out.MySQLCustomerRepository;
import com.vuelosjanbi.flightFare.application.FlightFareService;
import com.vuelosjanbi.trip.application.TripService;
import com.vuelosjanbi.tripBooking.application.TripBookingService;
import com.vuelosjanbi.tripBooking.domain.models.TripBooking;
import com.vuelosjanbi.tripBooking.infrastructure.adapters.out.MySQLTripBookingRepository;
import com.vuelosjanbi.tripBookingDetail.application.TripBookingDetailService;
import com.vuelosjanbi.tripBookingDetail.domain.models.TripBookingDetail;
import com.vuelosjanbi.tripBookingDetail.infrastructure.adapters.out.MySQLTripBookingDetailRepository;

@Controller
public class TripBookingConsoleAdapter {

  @Autowired
  private static TripBookingService tripBookingService;

  @Autowired
  private static TripBookingDetailService tripBookingDetailService;

  @Autowired
  private static CustomerService customerService;

  @Autowired
  private static TripService tripService;

  @Autowired
  private static FlightFareService flightFareService;

  private final String url = "jdbc:mysql://localhost:3307/vuelosjanpi";
  private final String user = "root";
  private final String password = "1324";

  public void start(boolean useJpa) {
    if (useJpa) {
      System.out.println("Using JPA");
    } else {
      System.out.println("Using MySQL Manual Queries");
      tripBookingService = new TripBookingService(new MySQLTripBookingRepository(url, user, password));
      tripBookingDetailService = new TripBookingDetailService(
          new MySQLTripBookingDetailRepository(url, user, password));
      customerService = new CustomerService(new MySQLCustomerRepository(url, user, password));

    }
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("1. Create Trip Booking");
      System.out.println("2. Get Trip Booking");
      System.out.println("3. Update Trip Booking");
      System.out.println("4. Delete Trip Booking");
      System.out.println("5. Get All Trip Bookings");
      System.out.println("6. Exit");
      switch (scanner.nextInt()) {
        case 1:
          createTripBooking(scanner);
          break;

        default:
          break;
      }
    }
  }

  private static void createTripBooking(Scanner scanner) {
    System.out.println("Enter day of the trip booking:");
    String day = scanner.next();
    System.out.println("Enter month of the trip booking:");
    String month = scanner.next();
    System.out.println("Enter year of the trip booking:");
    String year = scanner.next();
    String tripBookingDate = year + "-" + month + "-" + day;
    Date tripDate = Date.valueOf(tripBookingDate);
    System.out.println("Enter the trip id:");
    for (int i = 0; i < tripService.getAllTrips().size(); i++) {
      System.out.println(tripService.getAllTrips().get(i));
    }
    Long tripId = scanner.nextLong();
    TripBooking tripBooking = new TripBooking();
    tripBooking.setDate(tripDate);
    tripBooking.setTrip(tripService.getTripById(tripId));
    tripBookingService.createTripBooking(tripBooking);
    System.out.println("Select the customer id:");
    for (int i = 0; i < customerService.getAllCustomers().size(); i++) {
      System.out.println(customerService.getAllCustomers().get(i));
    }
    String customerId = scanner.nextLine();
    System.out.println("Select the flight fare id:");
    for (int i = 0; i < flightFareService.getAllFlightFares().size(); i++) {
      System.out.println(flightFareService.getAllFlightFares().get(i));
    }
    Long flightFareId = scanner.nextLong();
    TripBookingDetail tripBookingDetail = new TripBookingDetail();
    tripBookingDetail.setCustomer(customerService.getCustomer(customerId).orElse(null));
    tripBookingDetail.setFlightFare(flightFareService.getFlightFareById(flightFareId).orElse(null));
    tripBookingDetail.setTripBooking(tripBooking);
    tripBookingDetailService.createTripBookingDetail(tripBookingDetail);
    System.out.println("Trip booking created successfully!");
  }
}