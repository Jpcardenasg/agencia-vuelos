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
      System.out.println("1. Create Airport.");
      System.out.println("2. Consult Airport.");

      int choice = scanner.nextInt();
      switch (choice) {
        case 1:
          createAirport(scanner, airportService);
          break;
        case 2:
          consultAiportDetails(scanner, airportService);
          break;
      }
    }

  }

  public void createAirport(Scanner scanner, AirportService airportService) {
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
    airport.setCity(cityService.getCityById(cityId));
    if (airport.getCity() == null) {
      System.out.println("City not found.");
      return;
    }
    airportService.createAirport(airport);
  }

  public void consultAiportDetails(Scanner scanner, AirportService airportService) {
    System.out.println("Enter airport id: ");
    long id = scanner.nextLong();
    Airport airport = airportService.getAirportById(id);
    if (airport == null) {
      System.out.println("Airport not found.");
      return;
    }
    System.out.println("Airport name: " + airport.getName());
    System.out.println("City: " + airport.getCity().getName());
  }
}
