package com.vuelosjanbi.planeRevision.infrastructure.adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.Scanner;
import java.sql.Date;
import com.vuelosjanbi.plane.domain.models.Plane;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.employee.application.EmployeeService;
import com.vuelosjanbi.employee.domain.models.Employee;
import com.vuelosjanbi.plane.application.PlaneService;
import com.vuelosjanbi.planeRevision.application.PlaneRevisionService;
import com.vuelosjanbi.planeRevision.domain.models.PlaneRevision;
import com.vuelosjanbi.planeRevisionEmployee.application.PlaneRevisionEmployeeService;
import com.vuelosjanbi.planeRevisionEmployee.domain.models.PlaneRevisionEmployee;

@Controller
public class PlaneRevisionConsoleAdapter {

    @Autowired
    private PlaneRevisionService planeRevisionService;

    @Autowired
    private PlaneService planeService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PlaneRevisionEmployeeService planRevisionEmployeeService;

    public void start(boolean useJpa) {
        System.out.println("Plane Revision Console Adapter started");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create plane revision.");
            System.out.println("2. Get plane revision by id.");
            System.out.println("3. Update plane revision.");
            System.out.println("4. Delete plane revision.");
            System.out.println("5. Get all plane revisions by plane id.");
            System.out.println("6. Get all plane revisions.");
            System.out.println("0. Exit.");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    createPlaneRevision(scanner);
                    break;
                case 2:
                    getPlaneRevisionById(scanner);
                    break;
                case 3:
                    updatePlaneRevision(scanner);
                    break;
                case 4:
                    deletePlaneRevision(scanner);
                    break;
                case 5:
                    getPlaneRevisionsByPlaneId(scanner);
                    break;
                case 6:
                    getAllPlaneRevisions();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    break;
            }
        }
    }

    public void createPlaneRevision(Scanner scanner) {
        System.out.println("\n");
        System.out.println("Creating plane revision");
        System.out.println("Enter day of the revision:");
        String day = scanner.next();
        System.out.println("Enter month of the plane revision:");
        String month = scanner.next();
        System.out.println("Enter year of the plane revision:");
        String year = scanner.next();
        String revisionDateString = year + "-" + month + "-" + day;
        Date revisionDate = Date.valueOf(revisionDateString);
        PlaneRevision newPlaneRevision = new PlaneRevision();

        List<Plane> planes = planeService.getAllPlanes();
        for (Plane plane : planes) {
            System.out.println(plane);
        }
        System.out.println("Enter plane id:");
        Long planeId = scanner.nextLong();
        scanner.nextLine();

        Plane plane = planeService.getPlaneById(planeId);
        if (plane == null) {
            System.out.println("Plane not found");
            return;
        }
        List<Employee> employees = employeeService.getAllEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        System.out.println("Select the employee id for the revision:");
        String employeeId = scanner.nextLine();
        Employee employee = employeeService.getEmployeeById(employeeId).orElse(null);
        if (employee == null) {
            System.out.println("Employee not found");
            return;
        }

        PlaneRevisionEmployee planRevisionEmployee = new PlaneRevisionEmployee();
        planRevisionEmployee.setEmployee(employee);
        planRevisionEmployee.setPlanRevision(newPlaneRevision);
        planeRevisionService.createPlaneRevision(newPlaneRevision);
        planRevisionEmployeeService.createPlaneRevisionEmployee(planRevisionEmployee);
        newPlaneRevision.setRevisionDate(revisionDate);
        newPlaneRevision.setPlane(plane);

        System.out.println("Plane revision created");
    }

    public void getPlaneRevisionById(Scanner scanner) {
        System.out.println("Enter the plane revision id:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Optional<PlaneRevision> planeRevision = planeRevisionService.getPlaneRevisionById(id);
        if (planeRevision == null) {
            System.out.println("Plane revision not found");
            return;
        }
        System.out.println(planeRevision);
    }

    public void updatePlaneRevision(Scanner scanner) {
        System.out.println("Enter the plane revision id:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        PlaneRevision planeRevision = planeRevisionService.getPlaneRevisionById(id).get();
        if (planeRevision == null) {
            System.out.println("Plane revision not found");
            return;
        }
        System.out.println("Enter day of the revision:");
        String day = scanner.next();
        System.out.println("Enter month of the revision:");
        String month = scanner.next();
        System.out.println("Enter year of the revision:");
        String year = scanner.next();
        String revisionDateString = year + "-" + month + "-" + day;
        Date revisionDate = Date.valueOf(revisionDateString);
        planeRevision.setRevisionDate(revisionDate);
        planeRevisionService.updatePlaneRevision(planeRevision);
        System.out.println("Plane revision updated successfully!");
    }

    public void deletePlaneRevision(Scanner scanner) {
        System.out.println("Enter the plane revision id:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        planeRevisionService.deletePlaneRevision(id);
        System.out.println("Plane revision deleted successfully!");
    }

    public void getAllPlaneRevisions() {
        List<PlaneRevision> planeRevisions = planeRevisionService.getAllPlaneRevisions();
        for (PlaneRevision planeRevision : planeRevisions) {
            System.out.println(planeRevision);
        }
    }

    public void getPlaneRevisionsByPlaneId(Scanner scanner) {
        System.out.println("Enter the plane id:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        List<PlaneRevision> planeRevisions = planeRevisionService.getPlaneRevisionByPlaneId(id);
        for (PlaneRevision planeRevision : planeRevisions) {
            System.out.println(planeRevision);
        }
    }

}
