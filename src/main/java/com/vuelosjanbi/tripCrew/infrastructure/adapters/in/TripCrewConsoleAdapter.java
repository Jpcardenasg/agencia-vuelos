package com.vuelosjanbi.tripCrew.infrastructure.adapters.in;

import org.springframework.beans.factory.annotation.Autowired;

import com.vuelosjanbi.employee.application.EmployeeService;
import com.vuelosjanbi.employee.application.ports.out.EmployeeRepositoryPort;
import com.vuelosjanbi.employee.domain.models.Employee;
import com.vuelosjanbi.employee.infrastructure.adapters.out.MySQLEmployeeRepository;
import com.vuelosjanbi.flightConnection.application.FlightConnectionService;
import com.vuelosjanbi.flightConnection.application.ports.out.FlightConnectionRepositoryPort;
import com.vuelosjanbi.flightConnection.infrastructure.adapters.out.MySQLFlightConnectionRepository;
import com.vuelosjanbi.tripCrew.application.TripCrewService;
import com.vuelosjanbi.tripCrew.application.ports.out.TripCrewRepositoryPort;
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
      System.out.println("1. Create Trip Crew.");
      System.out.println("2. Update Trip Crew.");
      System.out.println("3. Delete Trip Crew.");
      System.out.println("4. List all Trip Crew.");
      System.out.println("5. Exit.");

      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          // createPlane(scanner, planes, models, statusList);
          break;
        case 2:
          // updatePlane(scanner, planes, models, statusList);
          break;
        case 3:
          // deletePlane(scanner, planes);
          break;
        case 4:
          // listPlanes(planes);
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

}
