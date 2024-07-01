package com.vuelosjanbi.systemUser.infraesctructure.adapter.in;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.airline.infrastructure.adapters.in.AirlineConsoleAdapter;
import com.vuelosjanbi.airport.infrastructure.adapters.in.AirportConsoleAdapter;
import com.vuelosjanbi.airportGate.infrastructure.adapters.in.AirportGateConsoleAdapter;
import com.vuelosjanbi.city.infrastructure.adapters.in.CityConsoleAdapter;
import com.vuelosjanbi.country.infrastructure.adapters.in.CountryConsoleAdapter;
import com.vuelosjanbi.crewRole.infrastructure.adapters.in.CrewRoleConsoleAdapter;
import com.vuelosjanbi.customer.infrastructure.adapters.in.CustomerConsoleAdapter;
import com.vuelosjanbi.documentType.infrastructure.adapters.in.DocumentTypeConsoleAdapter;
import com.vuelosjanbi.employee.infrastructure.adapters.in.EmployeeConsoleAdapter;
import com.vuelosjanbi.flightFare.infrastructure.adapters.in.FlightFareConsoleAdapter;
import com.vuelosjanbi.plane.infrastructure.adapters.in.PlaneConsoleAdapter;
import com.vuelosjanbi.planeManufacturer.infrastructure.adapters.in.PlaneManufacturerConsoleAdapter;
import com.vuelosjanbi.planeModel.infrastructure.adapters.in.PlaneModelConsoleAdapter;
import com.vuelosjanbi.systemUser.application.ports.SystemUserService;
import com.vuelosjanbi.systemUser.domain.SystemUser;
import com.vuelosjanbi.systemUser.domain.UserRole;
import com.vuelosjanbi.trip.infrastructure.adapters.in.TripConsoleAdapter;
import com.vuelosjanbi.tripBooking.infrastructure.adapters.in.TripBookingConsoleAdapter;
import com.vuelosjanbi.tripCrew.infrastructure.adapters.in.TripCrewConsoleAdapter;

@Controller
public class SystemUserAdminController {

  @Autowired
  private SystemUserService systemUserService;

  @Autowired
  private PlaneConsoleAdapter planeConsoleAdapter;

  @Autowired
  private EmployeeConsoleAdapter employeeConsoleAdapter;

  @Autowired
  private TripConsoleAdapter tripConsoleAdapter;

  @Autowired
  private TripCrewConsoleAdapter tripCrewConsoleAdapter;

  @Autowired
  private AirportConsoleAdapter airportConsoleAdapter;

  @Autowired
  private AirlineConsoleAdapter airlineConsoleAdapter;

  @Autowired
  private CustomerConsoleAdapter customerConsoleAdapter;

  @Autowired
  private TripBookingConsoleAdapter tripBookingConsoleAdapter;

  @Autowired
  private FlightFareConsoleAdapter flightFareConsoleAdapter;

  @Autowired
  private DocumentTypeConsoleAdapter documentTypeConsoleAdapter;

  @Autowired
  private CountryConsoleAdapter countryConsoleAdapter;

  @Autowired
  private CityConsoleAdapter cityConsoleAdapter;

  @Autowired
  private PlaneManufacturerConsoleAdapter planeManufacturerConsoleAdapter;

  @Autowired
  private PlaneModelConsoleAdapter planeModelConsoleAdapter;

  @Autowired
  private AirportGateConsoleAdapter airportGateConsoleAdapter;

  @Autowired
  private CrewRoleConsoleAdapter crewRoleConsoleAdapter;

  @Autowired
  private SystemUserCustomerController systemUserCustomerController;

  @Autowired
  private SystemUserTechnicianController systemUserTechnicianController;

  @Autowired
  private SystemUserSalesAgentController systemUserSalesAgentController;

  public void run(String... args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    try {
      SystemUser user = login(scanner, systemUserService);
      switch (user.getRole()) {
        case ADMIN:
          showAdminMenu(scanner);
          break;
        case CUSTOMER:
          systemUserCustomerController.showCustomerMenu(scanner);
          break;
        case TECHNICIAN:
          systemUserTechnicianController.showTechnicianMenu(scanner);
          break;
        case SALE_AGENT:
          systemUserSalesAgentController.showSalesAgentMenu(scanner);
          break;
        default:
          System.out.println("Invalid role. Please contact the system administrator.");
      }
    } catch (Exception e) {
      System.out.println("An error occurred: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private static SystemUser login(Scanner scanner, SystemUserService systemUserService) {
    System.out.println("Enter username: ");
    String username = scanner.nextLine();
    System.out.println("Enter password: ");
    String password = scanner.nextLine();

    // Assuming systemUserService.findByUsernameAndPassword(username, password)
    // returns the user
    SystemUser user = systemUserService.getSystemUserByUsernameAndPassword(username, password);
    if (user == null) {
      System.out.println("Invalid credentials. Please try again.");
      return login(scanner, systemUserService);
    }
    return user;
  }

  private void showAdminMenu(Scanner scanner) {
    int opcionPrincipal = -1;

    do {
      try {
        showAdminMainMenu();
        if (scanner.hasNextInt()) {
          opcionPrincipal = scanner.nextInt();
          scanner.nextLine(); // Clear the buffer

          switch (opcionPrincipal) {
            case 1:
              planeConsoleAdapter.start();
              break;
            case 2:
              employeeConsoleAdapter.start();
              break;
            case 3:
              tripCrewConsoleAdapter.start();
              break;
            case 4:
              tripConsoleAdapter.start();
              break;
            case 5:
              airportConsoleAdapter.start();
              break;
            case 6:
              tripBookingConsoleAdapter.start();
              break;
            case 7:
              flightFareConsoleAdapter.start();
              break;
            case 8:
              customerConsoleAdapter.start();
              break;
            case 9:
              documentTypeConsoleAdapter.start();
              break;
            case 10:
              managementEntityMenu(scanner);
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

  private static void showAdminMainMenu() {
    System.out.println("\n======================================");
    System.out.println("             ADMIN MENU               ");
    System.out.println("======================================");
    System.out.println("1. Plane Management");
    System.out.println("2. Employee Management");
    System.out.println("3. Trip Crew Management");
    System.out.println("4. Fly Management");
    System.out.println("5. Airport Management");
    System.out.println("6. Trip Booking Management");
    System.out.println("7. Flight Fare Management");
    System.out.println("8. Customer Management");
    System.out.println("9. Document Types Management");
    System.out.println("10. Other Entities Management");
    System.out.println("0. Log out");
    System.out.println("======================================");
    System.out.print("Choose an option: ");
  }

  private void managementEntityMenu(Scanner scanner) {
    System.out.println("\n======================================");
    System.out.println("          ENTITY MENU                  ");
    System.out.println("======================================");
    System.out.println("1. Country Management");
    System.out.println("2. City Management");
    System.out.println("3. Plane Manufacturer Management");
    System.out.println("4. Plane Model Management");
    System.out.println("5. Airpot Gates Management");
    System.out.println("6. Airline Management");
    System.out.println("7. Crew Rol Management");
    System.out.println("8. Create new System user");
    System.out.println("0. Exit");

    System.out.print("Choose an option: ");
    int choice = scanner.nextInt();
    scanner.nextLine();

    switch (choice) {
      case 1:
        countryConsoleAdapter.start();
        break;
      case 2:
        cityConsoleAdapter.start();
        break;
      case 3:
        planeManufacturerConsoleAdapter.start();
        break;
      case 4:
        planeModelConsoleAdapter.start();
        break;
      case 5:
        airportGateConsoleAdapter.start();
        break;
      case 6:
        airlineConsoleAdapter.start();
        break;
      case 7:
        crewRoleConsoleAdapter.start();
        break;
      case 8:
        createUser(scanner, systemUserService);
        break;
      case 0:
        System.out.println("Logging out...");
        break;
      default:
        System.out.println("Invalid choice. Please try again.");
    }
  }

  private static SystemUser createUser(Scanner scanner, SystemUserService systemUserService) {
    System.out.println("Registering a new user...");
    System.out.println("Enter username: ");
    String username = scanner.nextLine();
    System.out.println("Select role (1: ADMIN, 2: CUSTOMER, 3: TECHNICIAN, 4: SALES AGENT): ");
    int roleChoice = scanner.nextInt();
    scanner.nextLine();
    System.out.println("Enter password: ");
    String password = scanner.nextLine();

    UserRole role;
    switch (roleChoice) {
      case 1:
        role = UserRole.ADMIN;
        break;
      case 2:
        role = UserRole.CUSTOMER;
        break;
      case 3:
        role = UserRole.TECHNICIAN;
        break;
      case 4:
        role = UserRole.SALE_AGENT;
        break;
      default:
        throw new IllegalArgumentException("Invalid role choice.");
    }

    SystemUser newUser = new SystemUser(username, password, role);
    systemUserService.createSystemUser(newUser);
    System.out.println("User created successfully: " + newUser);

    return newUser;
  }
}
