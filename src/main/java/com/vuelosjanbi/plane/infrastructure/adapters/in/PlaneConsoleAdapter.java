package com.vuelosjanbi.plane.infrastructure.adapters.in;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.vuelosjanbi.plane.application.PlaneService;
import com.vuelosjanbi.plane.application.ports.out.PlaneRepositoryPort;
import com.vuelosjanbi.plane.domain.models.Plane;
import com.vuelosjanbi.plane.infrastructure.adapters.out.MySQLPlaneRepository;
import com.vuelosjanbi.planeModel.application.PlaneModelService;
import com.vuelosjanbi.planeModel.domain.models.PlaneModel;
import com.vuelosjanbi.planeStatus.application.PlaneStatusService;
import com.vuelosjanbi.planeStatus.domain.models.PlaneStatus;

public class PlaneConsoleAdapter {

    private PlaneService planeService;

    @Autowired
    private PlaneModelService planeModelService;
    @Autowired
    private PlaneStatusService planeStatusService;
    @Autowired
    private PlaneRepositoryPort jpaRepository;

    private final String url = "jdbc:mysql://localhost:3306/vuelosJanbi";
    private final String user = "admin";
    private final String password = "1324";

    public void start(boolean useJpa) {
        if (useJpa) {
            planeService = new PlaneService(jpaRepository);
        } else {
            planeService = new PlaneService(new MySQLPlaneRepository(url, user, password));
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
                        System.out.printf("ID: %d, Model: %s", model.getId(), model.getName());
                    }
                    Long modelChoice = scanner.nextLong();

                    System.out.println("Choose the status of the plane:");
                    for (PlaneStatus status : statusList) {
                        System.out.printf("ID: %d, Status: %s", status.getId(), status.getName());
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
                        break;
                    }

                    
                    Plane plane = new Plane();
                    plane.setPlate(plate);
                    plane.setCapacity(capacity);
                    plane.setFabricationDate(fabricationDate);
                    plane.setModel(models.get(modelChoice));
                    plane.setStatus(status.get(statusChoice));

                    planeService.createPlane(plane);

                    System.out.println("Plane created successfully!");
                    break;
                case 2:
                    System.out.println("Choose the plane you want to modify:");
                    for (Plane p : planes) {
                        System.out.printf("ID: %d, Plate: %s", p.getId(), p.getPlate());
                    }

                    int updatePlane = scanner.

                    break;

                default:
                    break;
            }
        }

    }
}
