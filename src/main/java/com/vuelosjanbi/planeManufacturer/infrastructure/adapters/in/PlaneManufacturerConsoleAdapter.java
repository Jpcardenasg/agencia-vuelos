package com.vuelosjanbi.planeManufacturer.infrastructure.adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.planeManufacturer.application.PlaneManufacturerService;
import com.vuelosjanbi.planeManufacturer.domain.models.PlaneManufacturer;

import java.util.Optional;
import java.util.Scanner;

@Controller
public class PlaneManufacturerConsoleAdapter {

  @Autowired
  private PlaneManufacturerService planeManufacturerService;

  private Scanner scanner = new Scanner(System.in);

  public void start() {

    while (true) {
      System.out.println("Plane Manufacturer Management Menu:");
      System.out.println("1. Create Plane Manufacturer");
      System.out.println("2. Delete Plane Manufacturer");
      System.out.println("3. Get Plane Manufacturer by ID");
      System.out.println("4. Get Plane Manufacturer by Name");
      System.out.println("5. List all Plane Manufacturers");
      System.out.println("6. Exit");
      System.out.print("Choose an option: ");

      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          createPlaneManufacturer();
          break;
        case 2:
          deletePlaneManufacturer();
          break;
        case 3:
          getPlaneManufacturerById();
          break;
        case 4:
          getPlaneManufacturerByName();
          break;
        case 5:
          listAllPlaneManufacturers();
          break;
        case 6:
          System.out.println("Exiting...");
          return;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private void createPlaneManufacturer() {
    System.out.print("Enter Manufacturer Name: ");
    String name = scanner.nextLine();

    PlaneManufacturer manufacturer = new PlaneManufacturer();
    manufacturer.setName(name);
    PlaneManufacturer createdManufacturer = planeManufacturerService.createPlaneManufacturer(manufacturer);
    System.out.println("Plane Manufacturer created: " + createdManufacturer);
  }

  private void deletePlaneManufacturer() {
    System.out.print("Enter Manufacturer ID to delete: ");
    Long id = scanner.nextLong();
    scanner.nextLine();

    planeManufacturerService.deletePlaneManufacturer(id);
    System.out.println("Plane Manufacturer deleted.");
  }

  private void getPlaneManufacturerById() {
    System.out.print("Enter Manufacturer ID: ");
    Long id = scanner.nextLong();
    scanner.nextLine();

    Optional<PlaneManufacturer> optionalManufacturer = planeManufacturerService.getPlaneManufacturerById(id);
    if (optionalManufacturer.isPresent()) {
      PlaneManufacturer manufacturer = optionalManufacturer.get();
      System.out.println("Manufacturer found: " + manufacturer);
    } else {
      System.out.println("Manufacturer not found.");
    }
  }

  private void getPlaneManufacturerByName() {
    System.out.print("Enter Manufacturer Name: ");
    String name = scanner.nextLine();

    Optional<PlaneManufacturer> optionalManufacturer = planeManufacturerService.getPlaneManufacturerByName(name);
    if (optionalManufacturer.isPresent()) {
      PlaneManufacturer manufacturer = optionalManufacturer.get();
      System.out.println("Manufacturer found: " + manufacturer);
    } else {
      System.out.println("Manufacturer not found.");
    }
  }

  private void listAllPlaneManufacturers() {
    System.out.println("Listing all Plane Manufacturers:");
    planeManufacturerService.getAllManufacturers().forEach(manufacturer -> {
      System.out.printf("ID: %d, Name: %s \n", manufacturer.getId(), manufacturer.getName());
    });
  }
}
