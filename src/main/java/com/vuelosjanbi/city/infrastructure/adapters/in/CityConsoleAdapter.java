package com.vuelosjanbi.city.infrastructure.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.city.application.CityService;
import com.vuelosjanbi.city.domain.models.City;

@Controller
public class CityConsoleAdapter {

    @Autowired
    private final CityService cityService;

    public CityConsoleAdapter(CityService cityService) {
        this.cityService = cityService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n");
            System.out.println("1. Create City.");
            System.out.println("2. Update City.");
            System.out.println("3. Delete City.");
            System.out.println("4. Find Cities by Country ID.");
            System.out.println("5. List all Cities.");
            System.out.println("0. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createCity(scanner);
                    break;

                case 2:
                    updateCity(scanner);
                    break;

                case 3:
                    deleteCity(scanner);
                    break;

                case 4:
                    findCitiesByCountryId(scanner);
                    break;

                case 5:
                    listAllCities();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option, please try again");
            }
        }
    }

    private void createCity(Scanner scanner) {
        System.out.println("Type the name of the City:");
        String cityName = scanner.nextLine();

        City newCity = new City(cityName);
        cityService.createCity(newCity);
        System.out.println("City created successfully!");
    }

    private void updateCity(Scanner scanner) {
        System.out.println("Choose the City you want to modify:");
        List<City> cities = cityService.getAllCities();
        cities.forEach(city -> {
            System.out.printf("ID: %d  Name: %s\n", city.getId(), city.getName());
        });

        Long updateCityId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Type the new name of the City:");
        String newCityName = scanner.nextLine();

        Optional<City> optionalCity = cityService.getCityById(updateCityId);
        if (optionalCity.isPresent()) {
            City updatedCity = optionalCity.get();
            updatedCity.setName(newCityName);
            cityService.updateCity(updatedCity);
            System.out.println("City updated successfully!");
        } else {
            System.out.println("City not found.");
        }
    }

    private void deleteCity(Scanner scanner) {
        System.out.println("Choose the City you want to delete:");
        List<City> cities = cityService.getAllCities();
        cities.forEach(city -> {
            System.out.printf("ID: %d  Name: %s\n", city.getId(), city.getName());
        });

        Long deleteCityId = scanner.nextLong();
        scanner.nextLine();

        Optional<City> optionalCity = cityService.getCityById(deleteCityId);
        if (optionalCity.isPresent()) {
            cityService.deleteCity(deleteCityId);
            System.out.println("City deleted successfully!");
        } else {
            System.out.println("City not found.");
        }
    }

    private void findCitiesByCountryId(Scanner scanner) {
        System.out.println("Type the Country ID:");
        Long countryId = scanner.nextLong();
        scanner.nextLine();

        List<City> cities = cityService.getCitiesByCountryId(countryId);
        if (cities != null && !cities.isEmpty()) {
            cities.forEach(city -> {
                System.out.printf("ID: %d  Name: %s\n", city.getId(), city.getName());
            });
        } else {
            System.out.println("No cities found for this country ID.");
        }
    }

    private void listAllCities() {
        List<City> cities = cityService.getAllCities();
        if (cities != null && !cities.isEmpty()) {
            cities.forEach(city -> {
                System.out.printf("ID: %d  Name: %s\n", city.getId(), city.getName());
            });
        } else {
            System.out.println("No cities available.");
        }
    }
}
