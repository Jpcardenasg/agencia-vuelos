package com.vuelosjanbi.employee.infrastructure.adapters.in;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.airline.application.AirlineService;
import com.vuelosjanbi.airline.domain.models.Airline;
import com.vuelosjanbi.airport.application.AirportService;
import com.vuelosjanbi.airport.domain.models.Airport;
import com.vuelosjanbi.crewRole.application.CrewRoleService;
import com.vuelosjanbi.crewRole.domain.models.CrewRole;
import com.vuelosjanbi.employee.application.EmployeeService;
import com.vuelosjanbi.employee.domain.models.Employee;

@Controller
public class EmployeeConsoleAdapter {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CrewRoleService crewRoleService;
    @Autowired
    private AirlineService airlineService;
    @Autowired
    private AirportService airportService;

    public void start() {

        Scanner scanner = new Scanner(System.in);
        List<Employee> employees;

        while (true) {
            employees = employeeService.getAllEmployees();
            System.out.println("\n");
            System.out.println("1. Register Employee.");
            System.out.println("2. Update Employee.");
            System.out.println("3. Delete Employee.");
            System.out.println("4. Find Employee by ID.");
            System.out.println("5. Find Employee by Rol.");
            System.out.println("6. List all Employees.");
            System.out.println("0. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createEmployee(scanner);
                    break;
                case 2:
                    updateEmployee(scanner, employees);
                    break;
                case 3:
                    deleteEmployee(scanner, employees);
                    break;
                case 4:
                    findEmployeeById(scanner);
                    break;
                case 5:
                    findEmployeeByRolId(scanner);
                    break;
                case 6:
                    listEmployees(employees);
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

    private void createEmployee(Scanner scanner) {
        List<CrewRole> crewRoles = crewRoleService.getAllCrewRoles();
        List<Airline> airlines = airlineService.getAllAirlines();
        List<Airport> airports = airportService.getAllAirports();

        String id = getInput("Type the Identification Number:", scanner);
        String name = getInput("Type the Employee name:", scanner);
        Date entryDate = getEntryDate(scanner);

        CrewRole chosenCrewRole = chooseCrewRole(crewRoles, scanner);
        Airline chosenAirline = chooseAirline(airlines, scanner);
        Airport chosenAirport = chooseAirport(airports, scanner);

        if (chosenCrewRole == null || chosenAirline == null || chosenAirport == null) {
            System.out.println("Invalid ID.");
            return;
        }

        Employee newEmployee = new Employee();
        newEmployee.setId(id);
        newEmployee.setName(name);
        newEmployee.setEntryDate(entryDate);
        newEmployee.setRol(chosenCrewRole);
        newEmployee.setAirline(chosenAirline);
        newEmployee.setAirport(chosenAirport);

        employeeService.createEmployee(newEmployee);
        System.out.println("Employee created successfully!");
    }

    private void updateEmployee(Scanner scanner, List<Employee> employees) {
        List<CrewRole> crewRoles = crewRoleService.getAllCrewRoles();
        List<Airline> airlines = airlineService.getAllAirlines();
        List<Airport> airports = airportService.getAllAirports();

        Employee chosenEmployee = chooseEmployee(employees, scanner);
        if (chosenEmployee == null) {
            System.out.println("Invalid Employee ID.");
            return;
        }

        String newEmployeeName = getInput("Type the new name:", scanner);
        Date newEntryDate = getEntryDate(scanner);

        CrewRole newChosenCrewRole = chooseCrewRole(crewRoles, scanner);
        Airline newChosenAirline = chooseAirline(airlines, scanner);
        Airport newChosenAirport = chooseAirport(airports, scanner);

        if (newChosenCrewRole == null || newChosenAirline == null || newChosenAirport == null) {
            System.out.println("Invalid ID.");
            return;
        }

        chosenEmployee.setName(newEmployeeName);
        chosenEmployee.setEntryDate(newEntryDate);
        chosenEmployee.setRol(newChosenCrewRole);
        chosenEmployee.setAirline(newChosenAirline);
        chosenEmployee.setAirport(newChosenAirport);

        employeeService.updateEmployee(chosenEmployee);

        System.out.println("Employee updated successfully!");
    }

    private void deleteEmployee(Scanner scanner, List<Employee> employees) {
        System.out.println("Employees:");
        listEmployees(employees);
        String deleteEmployeeChoice = getInput("Type the employee ID you want to delete:", scanner);

        employeeService.deleteEmployeeById(deleteEmployeeChoice);
        System.out.println("Employee deleted successfully!");
    }

    private void findEmployeeById(Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String employeeId = getInput("Type the Employee ID:", scanner);
        Optional<Employee> employeeOpt = employeeService.getEmployeeById(employeeId);

        employeeOpt.ifPresentOrElse(employee -> {
            String formattedDate = dateFormat.format(employee.getEntryDate());
            System.out.printf(
                    "ID: %s  Name: %s  Entry Date: %s  Rol: %s  Airline: %s  Airport: %s\n",
                    employee.getId(), employee.getName(), formattedDate,
                    employee.getRol().getName(), employee.getAirline().getName(),
                    employee.getAirport().getName());
        }, () -> System.out.println("Employee not found"));
    }

    private void findEmployeeByRolId(Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<CrewRole> crewRoles = crewRoleService.getAllCrewRoles();
        CrewRole chosenCrewRole = chooseCrewRole(crewRoles, scanner);

        if (chosenCrewRole != null) {
            List<Employee> employeesWithRol = employeeService.getEmployeesByRol(chosenCrewRole.getId());

            for (Employee employee : employeesWithRol) {
                String formattedDate = dateFormat.format(employee.getEntryDate());
                System.out.printf("ID: %s  Name: %s  Entry Date: %s  Rol: %s  Airline: %s  Airport: %s\n",
                        employee.getId(), employee.getName(), formattedDate,
                        employee.getRol().getName(), employee.getAirline().getName(),
                        employee.getAirport().getName());
            }
        } else {
            System.out.println("Invalid Rol ID.");
        }
    }

    private void listEmployees(List<Employee> employees) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Employee employee : employees) {
            String formattedDate = dateFormat.format(employee.getEntryDate());
            System.out.printf("ID: %s  Name: %s  Entry Date: %s  Rol: %s  Airline: %s  Airport: %s\n",
                    employee.getId(), employee.getName(), formattedDate,
                    employee.getRol().getName(), employee.getAirline().getName(),
                    employee.getAirport().getName());
        }
    }

    // Helpers
    private String getInput(String prompt, Scanner scanner) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private Date getEntryDate(Scanner scanner) {
        System.out.println("Type the Employee entry date:");
        System.out.println("Day:");
        int day = scanner.nextInt();
        System.out.println("Month:");
        int month = scanner.nextInt();
        System.out.println("Year:");
        int year = scanner.nextInt();
        scanner.nextLine();
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return new Date(cal.getTimeInMillis());
    }

    private CrewRole chooseCrewRole(List<CrewRole> crewRoles, Scanner scanner) {
        System.out.println("Crew Roles:");
        for (CrewRole crewRole : crewRoles) {
            System.out.printf("ID: %d  Rol: %s \n", crewRole.getId(), crewRole.getName());
        }
        System.out.println("Choose the ID of the rol:");
        Long crewRoleId = scanner.nextLong();
        scanner.nextLine();
        return crewRoles.stream().filter(role -> role.getId().equals(crewRoleId)).findFirst().orElse(null);
    }

    private Airline chooseAirline(List<Airline> airlines, Scanner scanner) {
        System.out.println("Airlines:");
        for (Airline airline : airlines) {
            System.out.printf("ID: %d  Airline: %s \n", airline.getId(), airline.getName());
        }
        System.out.println("Choose the ID of the airline:");
        Long airlineId = scanner.nextLong();
        scanner.nextLine();
        return airlines.stream().filter(airline -> airline.getId().equals(airlineId)).findFirst().orElse(null);
    }

    private Airport chooseAirport(List<Airport> airports, Scanner scanner) {
        System.out.println("Airports:");
        for (Airport airport : airports) {
            System.out.printf("ID: %d  Airport: %s \n", airport.getId(), airport.getName());
        }
        System.out.println("Choose the ID of the airport:");
        Long airportId = scanner.nextLong();
        scanner.nextLine();
        return airports.stream().filter(airport -> airport.getId().equals(airportId)).findFirst().orElse(null);
    }

    private Employee chooseEmployee(List<Employee> employees, Scanner scanner) {
        System.out.println("Employees:");
        for (Employee employee : employees) {
            System.out.printf("ID: %s  Name: %s \n", employee.getId(), employee.getName());
        }
        String employeeId = getInput("Choose the ID of the employee:", scanner);
        return employeeService.getEmployeeById(employeeId).orElse(null);
    }

}
