package com.vuelosjanbi.country.infrastructure.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.country.application.CountryService;
import com.vuelosjanbi.country.domain.models.Country;

@Controller
public class CountryConsoleAdapter {

    @Autowired
    private CountryService countryService;

    public CountryConsoleAdapter(CountryService countryService) {
        this.countryService = countryService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n");
            System.out.println("1. Create Country.");
            System.out.println("2. Update Country.");
            System.out.println("3. Delete Country.");
            System.out.println("4. Find Country by Id.");
            System.out.println("5. Find Country by name.");
            System.out.println("6. List all Countries.");
            System.out.println("0. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createCountry(scanner);
                    break;

                case 2:
                    updateCountry(scanner);
                    break;

                case 3:
                    deleteCountry(scanner);
                    break;

                case 4:
                    findCountryById(scanner);
                    break;

                case 5:
                    findCountryByName(scanner);
                    break;

                case 6:
                    listAllCountries();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option, please try again");
            }
        }
    }

    private void createCountry(Scanner scanner) {
        System.out.println("Type the name of the country:");
        String countryName = scanner.nextLine();

        Country newCountry = new Country(countryName);
        countryService.createCountry(newCountry);
        System.out.println("Country created successfully!");
    }

    private void updateCountry(Scanner scanner) {
        System.out.println("Choose the country you want to modify:");
        List<Country> countries = countryService.getAllCountries();
        countries.forEach(country -> {
            System.out.printf("ID: %d  Name: %s\n", country.getId(), country.getName());
        });

        Long updateCountryId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Type the new name of the country:");
        String newCountryName = scanner.nextLine();

        Optional<Country> optionalCountry = countryService.getCountryById(updateCountryId);
        if (optionalCountry.isPresent()) {
            Country updatedCountry = optionalCountry.get();
            updatedCountry.setName(newCountryName);
            countryService.updateCountry(updatedCountry);
            System.out.println("Country updated successfully!");
        } else {
            System.out.println("Country not found.");
        }
    }

    private void deleteCountry(Scanner scanner) {
        System.out.println("Choose the country you want to delete:");
        List<Country> countries = countryService.getAllCountries();
        countries.forEach(country -> {
            System.out.printf("ID: %d  Name: %s\n", country.getId(), country.getName());
        });

        Long deleteCountryId = scanner.nextLong();
        scanner.nextLine();

        Optional<Country> optionalCountry = countryService.getCountryById(deleteCountryId);
        if (optionalCountry.isPresent()) {
            countryService.deleteCountry(deleteCountryId);
            System.out.println("Country deleted successfully!");
        } else {
            System.out.println("Country not found.");
        }
    }

    private void findCountryById(Scanner scanner) {
        System.out.println("Type the ID of the country:");
        Long countryId = scanner.nextLong();
        scanner.nextLine();

        Optional<Country> optionalCountry = countryService.getCountryById(countryId);
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            System.out.printf("ID: %d  Name: %s\n", country.getId(), country.getName());
        } else {
            System.out.println("Country not found.");
        }
    }

    private void findCountryByName(Scanner scanner) {
        System.out.println("Type the name of the country:");
        String countryName = scanner.nextLine();

        Optional<Country> optionalCountry = countryService.getCountryByName(countryName);
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            System.out.printf("ID: %d  Name: %s\n", country.getId(), country.getName());
        } else {
            System.out.println("Country not found.");
        }
    }

    private void listAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        if (countries != null && !countries.isEmpty()) {
            countries.forEach(country -> {
                System.out.printf("ID: %d  Name: %s\n", country.getId(), country.getName());
            });
        } else {
            System.out.println("No countries available.");
        }
    }
}
