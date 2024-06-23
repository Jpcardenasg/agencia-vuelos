package com.vuelosjanbi.tripCrew.infrastructure.adapters.in;

import org.springframework.beans.factory.annotation.Autowired;

import com.vuelosjanbi.employee.application.EmployeeService;
import com.vuelosjanbi.employee.application.ports.out.EmployeeRepositoryPort;
import com.vuelosjanbi.employee.domain.models.Employee;
import com.vuelosjanbi.employee.infrastructure.adapters.out.MySQLEmployeeRepository;
import com.vuelosjanbi.flightConnection.application.FlightConnectionService;
import com.vuelosjanbi.flightConnection.application.ports.out.FlightConnectionRepositoryPort;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;
import com.vuelosjanbi.flightConnection.infrastructure.adapters.out.MySQLFlightConnectionRepository;
import com.vuelosjanbi.tripCrew.application.TripCrewService;
import com.vuelosjanbi.tripCrew.application.ports.out.TripCrewRepositoryPort;
import com.vuelosjanbi.tripCrew.domain.models.TripCrew;
import com.vuelosjanbi.tripCrew.infrastructure.adapters.out.MySQLTripCrewRepository;

import java.util.Scanner;

public class TripCrewConsoleAdapter {

  @Autowired
  private TripCrewRepositoryPort tripCrewRepositoryPort;

  @Autowired
  private EmployeeRepositoryPort employeeRepositoryPort;

  @Autowired
  private FlightConnectionRepositoryPort flightConnectionRepositoryPort;

  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private FlightConnectionService flightConnectionService;
  @Autowired
  private TripCrewService tripCrewService;

  private final String url = "jdbc:mysql://localhost:3307/vuelosjanpi";
  private final String user = "root";
  private final String password = "1324";

  public TripCrewConsoleAdapter() {
  }

  public TripCrewConsoleAdapter(TripCrewRepositoryPort tripCrewRepositoryPort,
      EmployeeRepositoryPort employeeRepositoryPort, FlightConnectionRepositoryPort flightConnectionRepositoryPort) {
    this.tripCrewRepositoryPort = tripCrewRepositoryPort;
    this.employeeRepositoryPort = employeeRepositoryPort;
    this.flightConnectionRepositoryPort = flightConnectionRepositoryPort;
  }

  public void start(boolean useJpa) {
    if (useJpa) {
      tripCrewService = new TripCrewService(tripCrewRepositoryPort);
      employeeService = new EmployeeService(employeeRepositoryPort);
      flightConnectionService = new FlightConnectionService(flightConnectionRepositoryPort);
    } else {
      flightConnectionService = new FlightConnectionService(new MySQLFlightConnectionRepository(url, user, password));
      employeeService = new EmployeeService(new MySQLEmployeeRepository(url, user, password));
      tripCrewService = new TripCrewService(new MySQLTripCrewRepository(url, user, password,
          new MySQLEmployeeRepository(url, user, password), new MySQLFlightConnectionRepository(url, user, password)));
    }

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("1. Add employee to Trip Crew.");
      System.out.println("2. Remove employee from Trip Crew.");
      System.out.println("3. List all Trip Crew.");
      System.out.println("4. Exit.");

      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          addEmployeeToFlightConnection(scanner);
          break;
        case 2:
          RemoveEmployeeFromFlightConnection(scanner);
          break;
        case 3:
          listEmplosyeesInFlightConnection();
          break;
        case 4:
          scanner.close();
          break;
        case 5:
          scanner.close();
          return;
        default:
          System.out.println("Invalid choice. Please try again.");
          break;
      }
    }
  }

  public void addEmployeeToFlightConnection(Scanner scanner) {
    flightConnectionService.getAllFlightConnections().forEach(flightConnection -> {
      System.out.println(flightConnection);
    });
    System.out.println("Enter the flight connection id:");
    Long flightConnectionId = scanner.nextLong();
    scanner.nextLine();
    FlightConnection flightConnection = flightConnectionRepositoryPort.findById(flightConnectionId).orElse(null);
    while (true) {
      employeeService.getAllEmployees().forEach(employee -> {
        System.out.println(employee);
      });
      System.out.println("Enter the employee id:");
      Long employeeId = scanner.nextLong();
      scanner.nextLine();
      Employee employee = employeeRepositoryPort.findById(employeeId).orElse(null);
      if (employee == null) {
        System.out.println("Employee not found.");
        continue;
      }
      tripCrewService.addEmployeeToFlightConnection(new TripCrew(employee, flightConnection));
      System.out.println("Do you want to add another employee to this flight connection? (y/n)");
      String choice = scanner.nextLine();
      if (choice.equals("n")) {
        break;
      }
    }
  }

  public void RemoveEmployeeFromFlightConnection(Scanner scanner) {
    flightConnectionService.getAllFlightConnections().forEach(flightConnection -> {
      System.out.println(flightConnection);
    });
    System.out.println("Enter the flight connection id:");
    Long flightConnectionId = scanner.nextLong();
    scanner.nextLine();
    FlightConnection flightConnection = flightConnectionRepositoryPort.findById(flightConnectionId).orElse(null);
    while (true) {
      employeeService.getAllEmployees().forEach(employee -> {
        System.out.println(employee);
      });
      System.out.println("Enter the employee id:");
      Long employeeId = scanner.nextLong();
      scanner.nextLine();
      Employee employee = employeeRepositoryPort.findById(employeeId).orElse(null);
      if (employee == null) {
        System.out.println("Employee not found.");
        continue;
      }
      tripCrewService.removeEmployeeFromFlightConnection(new TripCrew(employee, flightConnection));
      System.out.println("Do you want to remove another employee from this flight connection? (y/n)");
      String choice = scanner.nextLine();
      if (choice.equals("n")) {
        break;
      }
    }
  }

  public void listEmplosyeesInFlightConnection() {
    tripCrewService.getAllTripCrews().forEach(tripCrew -> {
      System.out.println(tripCrew.getEmployee() + " is in " + tripCrew.getFlightConnection());
    });
  }

}
