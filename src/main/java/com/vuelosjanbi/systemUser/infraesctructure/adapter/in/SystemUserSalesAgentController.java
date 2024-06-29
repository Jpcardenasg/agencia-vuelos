package com.vuelosjanbi.systemUser.infraesctructure.adapter.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.customer.infrastructure.adapters.in.CustomerConsoleAdapter;
import com.vuelosjanbi.flightFare.infrastructure.adapters.in.FlightFareConsoleAdapter;
import com.vuelosjanbi.tripBooking.infrastructure.adapters.in.TripBookingConsoleAdapter;

import java.util.Scanner;

@Controller
public class SystemUserSalesAgentController {

  @Autowired
  private TripBookingConsoleAdapter tripBookingConsoleAdapter;

  @Autowired
  private CustomerConsoleAdapter customerConsoleAdapter;

  @Autowired
  private FlightFareConsoleAdapter flightFareConsoleAdapter;

  public void showSalesAgentMenu(Scanner scanner) {
    int opcionPrincipal = -1;

    do {
      try {
        showSalesAgentMainMenu();
        if (scanner.hasNextInt()) {
          opcionPrincipal = scanner.nextInt();
          scanner.nextLine();

          switch (opcionPrincipal) {
            case 1:
              tripBookingConsoleAdapter.start();
              break;
            case 2:
              customerConsoleAdapter.start();
              break;
            case 3:
              flightFareConsoleAdapter.start();
              break;
            case 0:
              System.out.println("Logging out...");
              break;
            default:
              System.out.println("Invalid Option. Please, select a valid option.");
          }
        } else {
          System.out.println("Invalid input. Please enter a number.");
          scanner.next(); // Clear the invalid input
        }
      } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
        e.printStackTrace();
      }
    } while (opcionPrincipal != 0);
  }

  private static void showSalesAgentMainMenu() {
    System.out.println("\n======================================");
    System.out.println("          SALES AGENT MENU            ");
    System.out.println("======================================");
    System.out.println("1. Trip Booking Management");
    System.out.println("2. Customer Management");
    System.out.println("3. Flight Fare Management");
    System.out.println("0. Log out");
    System.out.println("======================================");
    System.out.print("Choose an option: ");
  }

}
