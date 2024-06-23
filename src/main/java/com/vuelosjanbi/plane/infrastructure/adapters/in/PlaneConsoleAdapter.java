package com.vuelosjanbi.plane.infrastructure.adapters.in;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vuelosjanbi.plane.application.PlaneService;
import com.vuelosjanbi.plane.application.ports.out.PlaneRepositoryPort;
import com.vuelosjanbi.plane.domain.models.Plane;
import com.vuelosjanbi.plane.infrastructure.adapters.out.MySQLPlaneRepository;
import com.vuelosjanbi.planeModel.application.PlaneModelService;
import com.vuelosjanbi.planeModel.application.ports.out.PlaneModelRepositoryPort;
import com.vuelosjanbi.planeModel.domain.models.PlaneModel;
import com.vuelosjanbi.planeModel.infrastructure.adapters.out.MySQLPlaneModelRepository;
import com.vuelosjanbi.planeStatus.application.PlaneStatusService;
import com.vuelosjanbi.planeStatus.application.ports.out.PlaneStatusRepositoryPort;
import com.vuelosjanbi.planeStatus.domain.models.PlaneStatus;
import com.vuelosjanbi.planeStatus.infrastructure.adapters.out.MySQLPlaneStatusRepository;

@Component
public class PlaneConsoleAdapter {

    private PlaneService planeService;

    @Autowired
    private PlaneModelService planeModelService;
    @Autowired
    private PlaneStatusService planeStatusService;
    @Autowired
    private PlaneRepositoryPort planeRepositoryPort;
    @Autowired
    private PlaneModelRepositoryPort planeModelRepositoryPort;
    @Autowired
    private PlaneStatusRepositoryPort planeStatusRepositoryPort;

    private final String url = "jdbc:mysql://localhost:3306/vuelosJanbi";
    private final String user = "admin";
    private final String password = "1324";

    public void start(boolean useJpa) {
        if (useJpa) {
            planeService = new PlaneService(planeRepositoryPort);
            planeModelService = new PlaneModelService(planeModelRepositoryPort);
            planeStatusService = new PlaneStatusService(planeStatusRepositoryPort);
            System.out.println("JPA");
        } else {
            planeService = new PlaneService(new MySQLPlaneRepository(url, user, password));
            planeModelService = new PlaneModelService(new MySQLPlaneModelRepository(url, user, password));
            planeStatusService = new PlaneStatusService(new MySQLPlaneStatusRepository(url, user, password));
            System.out.println("MYSQL");
        }

        Scanner scanner = new Scanner(System.in);
        List<Plane> planes;
        List<PlaneModel> models = planeModelService.getAllPlaneModels();
        List<PlaneStatus> statusList = planeStatusService.getAllPlaneStatus();

        while (true) {
            planes = planeService.getAllPlanes();
            System.out.println("1. Create Plane.");
            System.out.println("2. Update Plane.");
            System.out.println("3. Delete Plane.");
            System.out.println("4. List all Planes.");
            System.out.println("5. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createPlane(scanner, models, statusList);
                    break;
                case 2:
                    updatePlane(scanner, planes, models, statusList);
                    break;
                case 3:
                    deletePlane(scanner, planes);
                    break;
                case 4:
                    listPlanes(planes);
                    break;
                case 5:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void createPlane(Scanner scanner, List<PlaneModel> models, List<PlaneStatus> statusList) {
        System.out.println("Type the plate of the plane:");
        String plate = scanner.nextLine();
        System.out.println("Type the capacity:");
        Integer capacity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Type the fabrication date:");
        System.out.println("Day:");
        int day = scanner.nextInt();
        System.out.println("Month:");
        int month = scanner.nextInt();
        System.out.println("Year:");
        int year = scanner.nextInt();
        scanner.nextLine();
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        Date fabricationDate = new Date(cal.getTimeInMillis());

        System.out.println("Choose the model of the plane:");
        for (PlaneModel model : models) {
            System.out.printf("ID: %d, Model: %s\n", model.getId(), model.getName());
        }
        Long modelChoice = scanner.nextLong();

        System.out.println("Choose the status of the plane:");
        for (PlaneStatus status : statusList) {
            System.out.printf("ID: %d, Status: %s\n", status.getId(), status.getName());
        }
        Long statusChoice = scanner.nextLong();

        PlaneModel chosenModel = models.stream()
                .filter(model -> model.getId().equals(modelChoice))
                .findFirst()
                .orElse(null);

        PlaneStatus chosenStatus = statusList.stream()
                .filter(status -> status.getId().equals(statusChoice))
                .findFirst()
                .orElse(null);

        if (chosenModel == null || chosenStatus == null) {
            System.out.println("Invalid model or status ID.");
            return;
        }

        Plane plane = new Plane();
        plane.setPlate(plate);
        plane.setCapacity(capacity);
        plane.setFabricationDate(fabricationDate);
        plane.setModel(chosenModel);
        plane.setStatus(chosenStatus);

        planeService.createPlane(plane);

        System.out.println("Plane created successfully!");
    }

    private void updatePlane(Scanner scanner, List<Plane> planes, List<PlaneModel> models,
            List<PlaneStatus> statusList) {
        System.out.println("Choose the plane you want to modify:");
        for (Plane p : planes) {
            System.out.printf("ID: %d, Plate: %s\n", p.getId(), p.getPlate());
        }

        Long planeId = scanner.nextLong();
        scanner.nextLine();

        Plane planeToUpdate = planes.stream()
                .filter(p -> p.getId().equals(planeId))
                .findFirst()
                .orElse(null);

        if (planeToUpdate == null) {
            System.out.println("Invalid plane ID.");
            return;
        }

        System.out.println("Type the new capacity (current: " + planeToUpdate.getCapacity() + "):");
        Integer newCapacity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Type the new fabrication date:");
        System.out.println("Day:");
        int newDay = scanner.nextInt();
        System.out.println("Month:");
        int newMonth = scanner.nextInt();
        System.out.println("Year:");
        int newYear = scanner.nextInt();
        scanner.nextLine();
        Calendar cal = Calendar.getInstance();
        cal.set(newYear, newMonth - 1, newDay);
        Date newFabricationDate = new Date(cal.getTimeInMillis());

        System.out.println("Choose the new model of the plane:");
        for (PlaneModel model : models) {
            System.out.printf("ID: %d, Model: %s\n", model.getId(), model.getName());
        }
        Long newModelChoice = scanner.nextLong();

        System.out.println("Choose the new status of the plane:");
        for (PlaneStatus status : statusList) {
            System.out.printf("ID: %d, Status: %s\n", status.getId(), status.getName());
        }
        Long newStatusChoice = scanner.nextLong();

        PlaneModel newChosenModel = models.stream()
                .filter(model -> model.getId().equals(newModelChoice))
                .findFirst()
                .orElse(null);

        PlaneStatus newChosenStatus = statusList.stream()
                .filter(status -> status.getId().equals(newStatusChoice))
                .findFirst()
                .orElse(null);

        if (newChosenModel == null || newChosenStatus == null) {
            System.out.println("Invalid model or status ID.");
            return;
        }

        planeToUpdate.setCapacity(newCapacity);
        planeToUpdate.setFabricationDate(newFabricationDate);
        planeToUpdate.setModel(newChosenModel);
        planeToUpdate.setStatus(newChosenStatus);

        planeService.updatePlane(planeToUpdate);

        System.out.println("Plane updated successfully!");
    }

    private void deletePlane(Scanner scanner, List<Plane> planes) {
        System.out.println("Planes:");
        listPlanes(planes);
        System.out.println("Type the plane Id you want to delete");
        Long deletePlaneChoice = scanner.nextLong();
        scanner.nextLine();

        planeService.deletePlane(deletePlaneChoice);
        System.out.println("Plane deleted successfully!");
    }

    private void listPlanes(List<Plane> planes) {
        for (Plane plane : planes) {
            System.out.printf("ID: %d, Plate: %s, Capacity: %d, Fabrication Date: %s, Model: %s, Status: %s\n",
                    plane.getId(), plane.getPlate(), plane.getCapacity(), plane.getFabricationDate(),
                    plane.getModel().getName(), plane.getStatus().getName());
        }
    }
}
