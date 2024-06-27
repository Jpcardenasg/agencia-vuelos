package com.vuelosjanbi.country.infrastructure.adapters.in;

import java.util.List;
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
        List<Country> countries = countryService.getAllCountries();

        while (true) {
            System.out.println("1. Create Country.");
            System.out.println("2. Update Country.");
            System.out.println("3. Delete Country.");
            System.out.println("4. List all Countries.");
            System.out.println("5. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Type the name of the country:");
                    String countryName = scanner.nextLine();

                    Country newCountry = new Country(countryName);
                    countryService.createCountry(newCountry);
                    break;

                case 2:
                    System.out.println("Choose the country you want to modify:");
                    countries.forEach(country -> {
                        System.out.printf("ID: %d =, Name: %s\n", country.getId(), country.getName());
                    });

                    Long updateCountryId = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Type the new name of the country:");
                    String newCountryName = scanner.nextLine();

                    Country updatedCountry = new Country(updateCountryId, newCountryName);
                    countryService.updateCountry(updatedCountry);
                    break;

                case 3:
                    System.out.println("Choose the country you want to delete:");
                    countries.forEach(country -> {
                        System.out.printf("ID: %d =, Name: %s\n", country.getId(), country.getName());
                    });

                    Long deleteCountryId = scanner.nextLong();
                    scanner.nextLine();

                    countryService.deleteCountry(deleteCountryId);
                    break;

                case 4:
                    System.out.println("List of Countries:");
                    countries.forEach(country -> {
                        System.out.printf("ID: %d =, Name: %s\n", country.getId(), country.getName());
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
