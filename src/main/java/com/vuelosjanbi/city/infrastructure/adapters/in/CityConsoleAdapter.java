package com.vuelosjanbi.city.infrastructure.adapters.in;

import java.util.List;
import java.util.Scanner;

import com.vuelosjanbi.city.application.CityService;
import com.vuelosjanbi.city.domain.models.City;

public class CityConsoleAdapter {

    private final CityService CityService;

    public CityConsoleAdapter(CityService CityService) {
        this.CityService = CityService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        List<City> cities = CityService.getAllCities();

        while (true) {
            System.out.println("1. Create City.");
            System.out.println("2. Update City.");
            System.out.println("3. Delete City.");
            System.out.println("4. List all Cities.");
            System.out.println("5. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Type the name of the City:");
                    String CityName = scanner.nextLine();

                    City newCity = new City(CityName);
                    CityService.createCity(newCity);
                    break;

                case 2:
                    System.out.println("Choose the City you want to modify:");
                    cities.forEach(City -> {
                        System.out.printf("ID: %d =, Name: %s\n", City.getId(), City.getName());
                    });

                    Long updateCityId = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Type the new name of the City:");
                    String newCityName = scanner.nextLine();

                    City updatedCity = new City(updateCityId, newCityName);
                    CityService.updateCity(updatedCity);
                    break;

                case 3:
                    System.out.println("Choose the City you want to delete:");
                    cities.forEach(City -> {
                        System.out.printf("ID: %d =, Name: %s\n", City.getId(), City.getName());
                    });

                    Long deleteCityId = scanner.nextLong();
                    scanner.nextLine();

                    CityService.deleteCity(deleteCityId);
                    break;

                case 4:
                    System.out.println("List of Cities:");
                    cities.forEach(City -> {
                        System.out.printf("ID: %d =, Name: %s\n", City.getId(), City.getName());
                    });

                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option, please try again");
            }
        }

    }

}
