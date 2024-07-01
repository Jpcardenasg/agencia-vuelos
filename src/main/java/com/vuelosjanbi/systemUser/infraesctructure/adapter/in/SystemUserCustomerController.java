package com.vuelosjanbi.systemUser.infraesctructure.adapter.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.Scanner;

import com.vuelosjanbi.customer.infrastructure.adapters.in.CustomerConsoleAdapter;
import com.vuelosjanbi.documentType.infrastructure.adapters.in.DocumentTypeConsoleAdapter;
import com.vuelosjanbi.trip.infrastructure.adapters.in.TripConsoleAdapter;
import com.vuelosjanbi.tripBooking.infrastructure.adapters.in.TripBookingConsoleAdapter;

@Controller
public class SystemUserCustomerController {

  @Autowired
  private TripConsoleAdapter tripConsoleAdapter;

  @Autowired
  private TripBookingConsoleAdapter tripBookingConsoleAdapter;

  @Autowired
  private CustomerConsoleAdapter customerConsoleAdapter;

  @Autowired
  private DocumentTypeConsoleAdapter documentTypeConsoleAdapter;

  public void showCustomerMenu(Scanner scanner) {
    int opcionPrincipal = -1;
    do {
      try {
        showCustomerMainMenu();
        if (scanner.hasNextInt()) {
          opcionPrincipal = scanner.nextInt();
          scanner.nextLine(); // Clear the buffer

          switch (opcionPrincipal) {
            case 1:
              tripConsoleAdapter.start();
              break;
            case 2:
              tripBookingConsoleAdapter.start(true);
              break;
            case 3:
              customerConsoleAdapter.start(true);
              break;
            case 4:
              documentTypeConsoleAdapter.start(true);
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

  private static void showCustomerMainMenu() {
    System.out.println("\n======================================");
    System.out.println("           CUSTOMER MENU              ");
    System.out.println("======================================");
    System.out.println("1. View Trips");
    System.out.println("2. Book Trips");
    System.out.println("3. Manage Profile");
    System.out.println("4. Document Types Management");
    System.out.println("0. Log out");
    System.out.println("======================================");
    System.out.print("Choose an option: ");
  }

}
