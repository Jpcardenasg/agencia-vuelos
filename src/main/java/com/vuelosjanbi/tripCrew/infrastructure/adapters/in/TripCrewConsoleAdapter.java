package com.vuelosjanbi.tripCrew.infrastructure.adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.employee.application.EmployeeService;
import com.vuelosjanbi.employee.domain.models.Employee;
import com.vuelosjanbi.flightConnection.application.FlightConnectionService;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;
import com.vuelosjanbi.tripCrew.application.TripCrewService;
import com.vuelosjanbi.tripCrew.domain.models.TripCrew;

import java.util.List;
import java.util.Scanner;

@Controller
public class TripCrewConsoleAdapter {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private FlightConnectionService flightConnectionService;

    @Autowired
    private TripCrewService tripCrewService;

    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n");
            System.out.println("1. Add employees to Trip Crew.");
            System.out.println("2. Remove employee from Trip Crew.");
            System.out.println("3. Trip Crew information.");
            System.out.println("0. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployeeToFlightConnection(scanner);
                    break;
                case 2:
                    removeEmployeeFromFlightConnection(scanner);
                    break;
                case 3:
                    tripCrewInformationByTripId(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
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
        FlightConnection flightConnection = flightConnectionService.getConnectionById(flightConnectionId).orElse(null);
        while (true) {
            List<Employee> employees = employeeService.getAllEmployees();
            if (employees.isEmpty()) {
                System.out.println("No employees found.");
                break;
            }
            employees.forEach(employee -> {
                System.out.println(employee);
            });
            System.out.println("Enter the employee id:");
            String employeeId = scanner.nextLine();
            Employee employee = employeeService.getEmployeeById(employeeId).orElse(null);
            if (employee == null) {
                System.out.println("Employee not found.");
                continue;
            }
            tripCrewService.addEmployeeToFlightConnection(new TripCrew(employee, flightConnection));
            System.out.println("Employee added to the flight connection.");
            System.out.println("Do you want to add another employee to this flight connection? (y/n)");
            String choice = scanner.nextLine();
            if (choice.equals("n")) {
                break;
            }
        }
    }

    public void removeEmployeeFromFlightConnection(Scanner scanner) {
        flightConnectionService.getAllFlightConnections().forEach(flightConnection -> {
            System.out.println(flightConnection);
        });
        System.out.println("Enter the flight connection id:");
        Long flightConnectionId = scanner.nextLong();
        scanner.nextLine();
        FlightConnection flightConnection = flightConnectionService.getConnectionById(flightConnectionId).orElse(null);
        while (true) {
            employeeService.getAllEmployees().forEach(employee -> {
                System.out.println(employee);
            });
            System.out.println("Enter the employee id:");
            String employeeId = scanner.nextLine();
            scanner.nextLine();
            Employee employee = employeeService.getEmployeeById(employeeId).orElse(null);
            if (employee == null) {
                System.out.println("Employee not found.");
                continue;
            }
            tripCrewService.removeEmployeeFromFlightConnection(new TripCrew(employee, flightConnection));
            System.out.println("Employee removed from the flight connection.");
            System.out.println("Do you want to remove another employee from this flight connection? (y/n)");
            String choice = scanner.nextLine();
            if (choice.equals("n")) {
                break;
            }
        }
    }

    public void tripCrewInformationByTripId(Scanner scanner) {
        System.out.println("Trips:");
        flightConnectionService.getAllFlightConnections().forEach(flightConnection -> {
            System.out.println(flightConnection.getTrip());
        });
        System.out.println("Enter the trip id:");
        Long tripId = scanner.nextLong();
        scanner.nextLine();
        List<TripCrew> tripCrew = tripCrewService.getTripCrewByFlightConnectionTrip(tripId);
        if (tripCrew == null) {
            System.out.println("No employees found for this trip.");
            return;
        }
        System.out.println("Trip Crew:");
        for (TripCrew tripCrew2 : tripCrew) {
            System.out.println(tripCrew2.getEmployee());
        }
    }

}
