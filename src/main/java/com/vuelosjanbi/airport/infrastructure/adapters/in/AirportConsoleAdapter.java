package com.vuelosjanbi.airport.infrastructure.adapters.in;

import java.util.Scanner;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.airport.application.AirportService;
import com.vuelosjanbi.airport.domain.models.Airport;
import com.vuelosjanbi.airport.infrastructure.adapters.out.MySQLAirportRepository;
import com.vuelosjanbi.city.application.CityService;
import com.vuelosjanbi.city.domain.models.City;
import com.vuelosjanbi.city.infrastructure.adapters.out.MySQLCityRepository;

@Controller
public class AirportConsoleAdapter {

  @Autowired
  private AirportService airportService;

  @Autowired
  private CityService cityService;

  private final String url = "jdbc:mysql://localhost:3307/vuelosjanpi";
  private final String user = "root";
  private final String password = "1324";

  public void start(boolean jpa) {
    if (jpa) {
      System.out.println("Using JPA");
    } else {
      airportService = new AirportService(new MySQLAirportRepository(url, user, password));
      cityService = new CityService(new MySQLCityRepository(url, user, password));
    }

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("\n");
      System.out.println("1. Create Airport.");
      System.out.println("2. Consult Airport information.");
      System.out.println("3. Update Airport information.");
      System.out.println("4. Delete Airport.");
      System.out.println("5. List all Airports.");
      System.out.println("0. Exit.");

      int choice = scanner.nextInt();
      switch (choice) {
        case 1:
          createAirport(scanner);
          break;
        case 2:
          consultAiportDetails(scanner);
          break;
        case 3:
          updateAirport(scanner);
          break;
        case 4:
          deleteAirport(scanner);
          break;
        case 5:
          listAllAirports();
          break;
        case 0:
          System.out.println("Exiting...");
          return;
      }
    }

  }

  public void createAirport(Scanner scanner) {
    List<City> citieList = cityService.getAllCities();
    System.out.println("Enter airport name: ");
    String name = scanner.next();
    Airport airport = new Airport();
    airport.setName(name);
    for (City city : citieList) {
      System.out.println(city.getId() + " - " + city.getName());
    }
    System.out.println("Enter city id where is the airport: ");
    long cityId = scanner.nextLong();

    airport.setCity(cityService.getCityById(cityId).orElse(null));
    if (airport.getCity() == null) {
      System.out.println("City not found.");
      return;
    }
    airportService.createAirport(airport);
  }

  public void consultAiportDetails(Scanner scanner) {
    System.out.println("Enter airport id: ");
    long id = scanner.nextLong();
    Airport airport = airportService.getAirportById(id).orElse(null);
    if (airport == null) {
      System.out.println("Airport not found.");
      return;
    }
    System.out.println("Airport name: " + airport.getName());
    System.out.println("City: " + airport.getCity().getName());
  }

  public void deleteAirport(Scanner scanner) {
    System.out.println("Enter airport id: ");
    long id = scanner.nextLong();
    airportService.deleteAirportById(id);
  }

  public void listAllAirports() {
    List<Airport> airports = airportService.getAllAirports();
    for (Airport airport : airports) {
      System.out.println(airport);
    }
  }

  public void updateAirport(Scanner scanner) {
    System.out.println("Enter the airport id:");
    Long airportIdToUpdate = scanner.nextLong();
    Airport airportToUpdate = airportService.getAirportById(airportIdToUpdate).orElse(null);
    System.out.println("Enter the airport name:");
    scanner.nextLine();
    String airportNameToUpdate = scanner.nextLine();
    System.out.println("Enter the city id:");
    Long cityIdToUpdate = scanner.nextLong();
    airportToUpdate.setCity(cityService.getCityById(cityIdToUpdate).orElse(null));
    airportToUpdate.setName(airportNameToUpdate);
    airportService.updateAirport(airportToUpdate);
    System.out.println("Airport updated successfully!");
  }
}
