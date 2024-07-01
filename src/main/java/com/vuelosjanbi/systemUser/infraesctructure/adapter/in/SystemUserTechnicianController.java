package com.vuelosjanbi.systemUser.infraesctructure.adapter.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.Scanner;

import com.vuelosjanbi.plane.infrastructure.adapters.in.PlaneConsoleAdapter;
import com.vuelosjanbi.planeManufacturer.infrastructure.adapters.in.PlaneManufacturerConsoleAdapter;
import com.vuelosjanbi.planeModel.infrastructure.adapters.in.PlaneModelConsoleAdapter;
import com.vuelosjanbi.trip.infrastructure.adapters.in.TripConsoleAdapter;

@Controller
public class SystemUserTechnicianController {

  @Autowired
  private PlaneConsoleAdapter planeConsoleAdapter;

  @Autowired
  private TripConsoleAdapter tripConsoleAdapter;

  @Autowired
  private PlaneManufacturerConsoleAdapter planeManufacturerConsoleAdapter;

  @Autowired
  private PlaneModelConsoleAdapter planeModelConsoleAdapter;

  public void showTechnicianMenu(Scanner scanner) {
    int opcionPrincipal = -1;

    do {
      try {
        showTechnicianMainMenu();
        if (scanner.hasNextInt()) {
          opcionPrincipal = scanner.nextInt();
          scanner.nextLine(); // Clear the buffer

          switch (opcionPrincipal) {
            case 1:
              planeConsoleAdapter.start();
              break;
            case 2:
              tripConsoleAdapter.start();
              break;
            case 3:
              planeManufacturerConsoleAdapter.start();
              break;
            case 4:
              planeModelConsoleAdapter.start();
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

  private static void showTechnicianMainMenu() {
    System.out.println("\n======================================");
    System.out.println("          TECHNICIAN MENU             ");
    System.out.println("======================================");
    System.out.println("1. Plane Management");
    System.out.println("2. Fly Management");
    System.out.println("3. Plane Manufacturer Management");
    System.out.println("4. Plane Model Management");
    System.out.println("0. Log out");
    System.out.println("======================================");
    System.out.print("Choose an option: ");
  }
}
