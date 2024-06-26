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
import com.vuelosjanbi.employee.infrastructure.adapters.out.MySQLEmployeeRepository;

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

    private final String url = "jdbc:mysql://localhost:3307/vuelosjanpi";
    private final String user = "root";
    private final String password = "1324";
    private MySQLEmployeeRepository mySQLEmployeeRepository = new MySQLEmployeeRepository(url, user, password);

    public void start(boolean useJpa) {
        if (useJpa) {
            System.out.println("Using JPA");
        } else {
            employeeService = new EmployeeService(new MySQLEmployeeRepository(url, user, password));
            System.out.println("Using MYSQL");
        }

        Scanner scanner = new Scanner(System.in);
        List<Employee> employees;

        while (true) {
            employees = employeeService.getAllEmployees();
            System.out.println("1. Register Employee.");
            System.out.println("2. Update Employee.");
            System.out.println("3. Delete Employee.");
            System.out.println("4. Find Employee by ID.");
            System.out.println("5. Find Employee by Rol.");
            System.out.println("6. List all Employees.");
            System.out.println("7. Exit.");

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
                case 7:
                    scanner.close();
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

        System.out.println("Type the Identification Number:");
        String id = scanner.nextLine();
        System.out.println("Type the Employee name:");
        String name = scanner.nextLine();
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
        Date entryDate = new Date(cal.getTimeInMillis());

        System.out.println("Crew Roles:");
        for (CrewRole crewRole : crewRoles) {
            System.out.printf("ID: %d, Rol: %s \n", crewRole.getId(), crewRole.getName());
        }
        System.out.println("Choose the ID of the rol:");
        Long crewRoleId = scanner.nextLong();

        System.out.println("Airlines:");
        for (Airline airline : airlines) {
            System.out.printf("ID: %d, Airline: %s \n", airline.getId(), airline.getName());
        }
        System.out.println("Choose the ID of the airline:");
        Long airlineId = scanner.nextLong();

        System.out.println("Airports:");
        for (Airport airport : airports) {
            System.out.printf("ID: %d, Airport: %s \n", airport.getId(), airport.getName());
        }
        System.out.println("Choose the ID of the airport:");
        Long airportId = scanner.nextLong();
        scanner.nextLine();

        CrewRole chosenCrewRole = crewRoles.stream()
                .filter(role -> role.getId().equals(crewRoleId))
                .findFirst()
                .orElse(null);
        Airline chosenAirline = airlines.stream()
                .filter(airline -> airline.getId().equals(airlineId))
                .findFirst()
                .orElse(null);
        Airport chosenAirport = airports.stream()
                .filter(airport -> airport.getId().equals(airportId))
                .findFirst()
                .orElse(null);

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

        System.out.println("Employees:");
        for (Employee employee : employees) {
            System.out.printf("ID: %s, Name: %s \n", employee.getId(), employee.getName());
        }
        System.out.println("Choose the ID of the employee:");
        String employeeId = scanner.nextLine();
        Employee chosenEmployee = employeeService.getEmployeeById(employeeId).orElse(null);
        if (chosenEmployee == null) {
            System.out.println("Invalid Employee ID.");
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(chosenEmployee.getEntryDate());
            System.out.printf("ID: %s, Name: %d, Entry Date: %s, IdRol: %d, IdAirline: %d, IdAirport: %d\n",
                    chosenEmployee.getId(), chosenEmployee.getName(), formattedDate,
                    chosenEmployee.getRol().getId(), chosenEmployee.getAirline().getId(),
                    chosenEmployee.getAirport().getId());
        }

        System.out.println("Type the new Employee identification number:");
        String newEmployeeId = scanner.nextLine();
        System.out.println("Type the new name:");
        String newEmployeeName = scanner.nextLine();
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
        Date newEntryDate = new Date(cal.getTimeInMillis());

        System.out.println("Crew Roles:");
        for (CrewRole crewRole : crewRoles) {
            System.out.printf("ID: %d, Rol: %s \n", crewRole.getId(), crewRole.getName());
        }
        System.out.println("Choose the ID of the rol:");
        Long newCrewRoleId = scanner.nextLong();

        System.out.println("Airlines:");
        for (Airline airline : airlines) {
            System.out.printf("ID: %d, Airline: %s \n", airline.getId(), airline.getName());
        }
        System.out.println("Choose the ID of the airline:");
        Long newAirlineId = scanner.nextLong();

        System.out.println("Airports:");
        for (Airport airport : airports) {
            System.out.printf("ID: %d, Airport: %s \n", airport.getId(), airport.getName());
        }
        System.out.println("Choose the ID of the airport:");
        Long newAirportId = scanner.nextLong();
        scanner.nextLine();

        CrewRole newChosenCrewRole = crewRoles.stream()
                .filter(role -> role.getId().equals(newCrewRoleId))
                .findFirst()
                .orElse(null);
        Airline newChosenAirline = airlines.stream()
                .filter(airline -> airline.getId().equals(newAirlineId))
                .findFirst()
                .orElse(null);
        Airport newChosenAirport = airports.stream()
                .filter(airport -> airport.getId().equals(newAirportId))
                .findFirst()
                .orElse(null);

        if (newChosenCrewRole == null || newChosenAirline == null || newChosenAirport == null) {
            System.out.println("Invalid ID.");
            return;
        }

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(newEmployeeId);
        updatedEmployee.setName(newEmployeeName);
        updatedEmployee.setEntryDate(newEntryDate);
        updatedEmployee.setRol(newChosenCrewRole);
        updatedEmployee.setAirline(newChosenAirline);
        updatedEmployee.setAirport(newChosenAirport);

        mySQLEmployeeRepository.update(updatedEmployee, employeeId);
        System.out.println("Employee created successfully!");
    }

    private void deleteEmployee(Scanner scanner, List<Employee> employees) {
        System.out.println("Employees:");
        listEmployees(employees);
        System.out.println("Type the employee Id you want to delete");
        String deleteEmployeeChoice = scanner.nextLine();
        scanner.nextLine();

        employeeService.deleteEmployeeById(deleteEmployeeChoice);
        System.out.println("employee deleted successfully!");
    }

    private void findEmployeeById(Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Type the Employee ID:");
        String employeeId = scanner.nextLine();
        Optional<Employee> employeeOpt = employeeService.getEmployeeById(employeeId);

        employeeOpt.ifPresentOrElse(employee -> {
            String formattedDate = dateFormat.format(employee.getEntryDate());
            System.out.printf(
                    "ID: %s, Name: %d, Entry Date: %s, Rol: %s, Airline: %s, Airport: %s\n",
                    employee.getId(), employee.getName(), formattedDate,
                    employee.getRol().getName(), employee.getAirline().getName(),
                    employee.getAirport().getName());
        }, () -> System.out.println("Employee not found"));
    }

    private void findEmployeeByRolId(Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<CrewRole> crewRoles = crewRoleService.getAllCrewRoles();
        for (CrewRole crewRole : crewRoles) {
            System.out.printf("ID: %d, Rol: %s \n", crewRole.getId(), crewRole.getName());
        }
        System.out.println("Choose the ID of the rol:");
        Long crewRoleId = scanner.nextLong();
        scanner.nextLine();

        List<Employee> employeesWithRol = employeeService.getEmployeesByRol(crewRoleId);

        for (Employee employee : employeesWithRol) {
            String formattedDate = dateFormat.format(employee.getEntryDate());
            System.out.printf("ID: %s, Name: %d, Entry Date: %s, Rol: %s, Airline: %s, Airport: %s\n",
                    employee.getId(), employee.getName(), formattedDate,
                    employee.getRol().getName(), employee.getAirline().getName(),
                    employee.getAirport().getName());
        }
    }

    private void listEmployees(List<Employee> employees) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Employee employee : employees) {
            String formattedDate = dateFormat.format(employee.getEntryDate());
            System.out.printf("ID: %s, Name: %d, Entry Date: %s, Rol: %s, Airline: %s, Airport: %s\n",
                    employee.getId(), employee.getName(), formattedDate,
                    employee.getRol().getName(), employee.getAirline().getName(),
                    employee.getAirport().getName());
        }
    }

}
