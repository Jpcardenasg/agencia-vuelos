package com.vuelosjanbi.tripBooking.infrastructure.adapters.in;

import java.sql.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.tripBooking.application.TripBookingService;
import com.vuelosjanbi.tripBooking.infrastructure.adapters.out.MySQLTripBookingRepository;
import com.vuelosjanbi.tripBookingDetail.application.TripBookingDetailService;
import com.vuelosjanbi.tripBookingDetail.infrastructure.adapters.out.MySQLTripBookingDetailRepository;

@Controller
public class TripBookingConsoleAdapter {

  @Autowired
  private TripBookingService tripBookingService;

  @Autowired
  private TripBookingDetailService tripBookingDetailService;

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

  private void createTripBooking(Scanner scanner) {
    System.out.println("Enter day of the trip booking:");
    String day = scanner.next();
    System.out.println("Enter month of the trip booking:");
    String month = scanner.next();
    System.out.println("Enter year of the trip booking:");
    String year = scanner.next();
    String tripBookingDate = year + "-" + month + "-" + day;
    Date tripDate = Date.valueOf(tripBookingDate);
    System.out.println("Enter the trip id:");
    Long tripId = scanner.nextLong();
    tripBookingService.createTripBooking(null);
  }
}