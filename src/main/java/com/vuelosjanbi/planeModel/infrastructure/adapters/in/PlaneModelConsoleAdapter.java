package com.vuelosjanbi.planeModel.infrastructure.adapters.in;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.planeManufacturer.application.PlaneManufacturerService;
import com.vuelosjanbi.planeManufacturer.domain.models.PlaneManufacturer;
import com.vuelosjanbi.planeModel.application.PlaneModelService;
import com.vuelosjanbi.planeModel.domain.models.PlaneModel;

@Controller
public class PlaneModelConsoleAdapter {

    @Autowired
    private PlaneModelService planeModelService;
    @Autowired
    private PlaneManufacturerService planeManufacturerService;

    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n");
            System.out.println("1. Create a Plane Model.");
            System.out.println("2. Update a Plane Model.");
            System.out.println("3. Delete a Plane Model.");
            System.out.println("4. List all Plane Models.");
            System.out.println("5. Find Plane Model by ID.");
            System.out.println("0. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createPlaneModel(scanner);
                    break;

                case 2:
                    updatePlaneModel(scanner);
                    break;

                case 3:
                    deletePlaneModel(scanner);
                    break;

                case 4:
                    listAllPlaneModels();
                    break;

                case 5:
                    findPlaneModelById(scanner);
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

    private void createPlaneModel(Scanner scanner) {
        System.out.println("Type the name of the model:");
        String newModel = scanner.nextLine();

        PlaneManufacturer chosenPlaneManufacturer = selectPlaneManufacturer(scanner);

        PlaneModel planeModel = new PlaneModel();
        planeModel.setName(newModel);
        planeModel.setPlaneManufacturer(chosenPlaneManufacturer);

        planeModelService.createPlaneModel(planeModel);
        System.out.println("Plane Model created successfully!");
    }

    private void updatePlaneModel(Scanner scanner) {

        PlaneModel chosenPlaneModel = selectPlaneModel(scanner);

        System.out.println("Type the new name of the model:");
        String updatedModelName = scanner.nextLine();

        PlaneManufacturer updatedPlaneManufacturer = selectPlaneManufacturer(scanner);

        chosenPlaneModel.setName(updatedModelName);
        chosenPlaneModel.setPlaneManufacturer(updatedPlaneManufacturer);

        planeModelService.updatePlaneModel(chosenPlaneModel);
        System.out.println("Plane Model updated successfully!");
    }

    private void deletePlaneModel(Scanner scanner) {
        PlaneModel chosenPlaneModel = selectPlaneModel(scanner);

        planeModelService.deletePlaneModel(chosenPlaneModel.getId());
        System.out.println("Plane Model deleted successfully!");
    }

    private void listAllPlaneModels() {
        planeModelService.getAllPlaneModels().forEach(model -> {
            System.out.printf("ID: %d  Model: %s  Manufacturer: %s \n", model.getId(), model.getName(),
                    model.getPlaneManufacturer().getName());
        });
    }

    private void findPlaneModelById(Scanner scanner) {
        System.out.println("Enter the ID of the plane model you want to find:");
        Long modelId = scanner.nextLong();
        scanner.nextLine();

        Optional<PlaneModel> optionalPlaneModel = planeModelService.getPlaneModelById(modelId);
        if (optionalPlaneModel.isPresent()) {
            PlaneModel planeModel = optionalPlaneModel.get();
            System.out.printf("ID: %d  Model: %s  Manufacturer: %s \n", planeModel.getId(), planeModel.getName(),
                    planeModel.getPlaneManufacturer().getName());
        } else {
            System.out.println("Plane Model not found.");
        }
    }

    private PlaneManufacturer selectPlaneManufacturer(Scanner scanner) {
        System.out.println("Choose the plane manufacturer ID:");
        planeManufacturerService.getAllManufacturers().forEach(manufacturer -> {
            System.out.printf("ID: %d  Name: %s \n", manufacturer.getId(), manufacturer.getName());
        });

        PlaneManufacturer chosenPlaneManufacturer = null;
        while (chosenPlaneManufacturer == null) {
            Long manufacturerId = scanner.nextLong();
            scanner.nextLine();

            Optional<PlaneManufacturer> optionalManufacturer = planeManufacturerService
                    .getPlaneManufacturerById(manufacturerId);
            if (optionalManufacturer.isPresent()) {
                chosenPlaneManufacturer = optionalManufacturer.get();
            } else {
                System.out.println("Invalid Plane Manufacturer ID. Please try again.");
            }
        }
        return chosenPlaneManufacturer;
    }

    private PlaneModel selectPlaneModel(Scanner scanner) {
        System.out.println("Choose the plane model you want to modify:");
        planeModelService.getAllPlaneModels().forEach(model -> {
            System.out.printf("ID: %d  Model: %s  Manufacturer: %s \n", model.getId(), model.getName(),
                    model.getPlaneManufacturer().getName());
        });

        PlaneModel chosenPlaneModel = null;
        while (chosenPlaneModel == null) {
            Long modelId = scanner.nextLong();
            scanner.nextLine();

            Optional<PlaneModel> optionalPlaneModel = planeModelService.getPlaneModelById(modelId);
            if (optionalPlaneModel.isPresent()) {
                chosenPlaneModel = optionalPlaneModel.get();
            } else {
                System.out.println("Invalid Plane Model ID. Please try again.");
            }
        }
        return chosenPlaneModel;
    }
}
