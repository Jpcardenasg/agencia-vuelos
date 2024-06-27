package com.vuelosjanbi;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vuelosjanbi.airport.infrastructure.adapters.in.AirportConsoleAdapter;
import com.vuelosjanbi.airportGate.infrastructure.adapters.in.AirportGateConsoleAdapter;
import com.vuelosjanbi.city.infrastructure.adapters.in.CityConsoleAdapter;
import com.vuelosjanbi.country.infrastructure.adapters.in.CountryConsoleAdapter;
import com.vuelosjanbi.crewRole.infrastructure.adapters.in.CrewRoleConsoleAdapter;
import com.vuelosjanbi.customer.infrastructure.adapters.in.CustomerConsoleAdapter;
import com.vuelosjanbi.documentType.infrastructure.adapters.in.DocumentTypeConsoleAdapter;
import com.vuelosjanbi.flightFare.infrastructure.adapters.in.FlightFareConsoleAdapter;
import com.vuelosjanbi.plane.infrastructure.adapters.in.PlaneConsoleAdapter;
import com.vuelosjanbi.planeManufacturer.infrastructure.adapters.in.PlaneManufacturerConsoleAdapter;
import com.vuelosjanbi.planeModel.infrastructure.adapters.in.PlaneModelConsoleAdapter;
import com.vuelosjanbi.trip.infrastructure.adapters.in.TripConsoleAdapter;
import com.vuelosjanbi.tripBooking.infrastructure.adapters.in.TripBookingConsoleAdapter;
import com.vuelosjanbi.tripCrew.infrastructure.adapters.in.TripCrewConsoleAdapter;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private PlaneConsoleAdapter planeConsoleAdapter;

    @Autowired
    private TripConsoleAdapter tripConsoleAdapter;

    @Autowired
    private TripCrewConsoleAdapter tripCrewConsoleAdapter;

    @Autowired
    private AirportConsoleAdapter airportConsoleAdapter;

    @Autowired
    private CustomerConsoleAdapter customerConsoleAdapter;

    @Autowired
    private TripBookingConsoleAdapter tripBookingConsoleAdapter;

    @Autowired
    private FlightFareConsoleAdapter flightFareConsoleAdapter;

    @Autowired
    private DocumentTypeConsoleAdapter documentTypeConsoleAdapter;

    @Autowired
    private CountryConsoleAdapter countryConsoleAdapter;

    @Autowired
    private CityConsoleAdapter cityConsoleAdapter;

    @Autowired
    private PlaneManufacturerConsoleAdapter planeManufacturerConsoleAdapter;

    @Autowired
    private PlaneModelConsoleAdapter planeModelConsoleAdapter;

    @Autowired
    private AirportGateConsoleAdapter airportGateConsoleAdapter;

    @Autowired
    private CrewRoleConsoleAdapter crewRoleConsoleAdapter;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcionPrincipal;

        do {
            mostrarMenuPrincipal();
            opcionPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (opcionPrincipal) {
                case 1:
                    planeConsoleAdapter.start(true);
                    break;
                case 2:
                    tripCrewConsoleAdapter.start(true);
                    break;
                case 3:
                    tripConsoleAdapter.start(true);
                    break;
                case 4:
                    airportConsoleAdapter.start(true);
                    break;
                case 5:
                    tripBookingConsoleAdapter.start(true);
                    break;
                case 6:
                    customerConsoleAdapter.start(true);
                    break;
                case 7:
                    flightFareConsoleAdapter.start(true);
                    break;
                case 8:
                    documentTypeConsoleAdapter.start(true);
                    break;
                case 9:
                    managementEntityMenu(scanner);
                    break;
                case 10:

                    break;
                case 11:

                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Ivalid Option. Please, select a valid option.");
            }

        } while (opcionPrincipal != 11);
        scanner.close();
    }

    private void managementEntityMenu(Scanner scanner) {
        System.out.println("1. Country Management");
        System.out.println("2. City Management");
        System.out.println("3. Plane Manufacturer Management");
        System.out.println("4. Plane Model Management");
        System.out.println("5. Airpot Gates Management");
        System.out.println("6. Crew Rol Management");

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                countryConsoleAdapter.start();
                break;
            case 2:
                cityConsoleAdapter.start();
                break;
            case 3:
                planeManufacturerConsoleAdapter.start();
                break;
            case 4:
                planeModelConsoleAdapter.start();
                break;
            case 5:
                airportGateConsoleAdapter.start();
                break;
            case 6:
                crewRoleConsoleAdapter.start();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n======================================");
        System.out.println("             MENÚ PRINCIPAL           ");
        System.out.println("======================================");
        System.out.println("1. Gestión de Aviones");
        System.out.println("--------------------------------------");
        System.out.println("2. Gestión  de Tripulación");
        System.out.println("--------------------------------------");
        System.out.println("3. Gestión de Rutas y Escalas");
        System.out.println("--------------------------------------");
        System.out.println("4. Gestión de Aeropuertos");
        System.out.println("--------------------------------------");
        System.out.println("5. Gestión de Reservas");
        System.out.println("--------------------------------------");
        System.out.println("6. Gestión de Clientes");
        System.out.println("--------------------------------------");
        System.out.println("7. Gestión de Tarifas");
        System.out.println("--------------------------------------");
        System.out.println("8. Gestión de Documentos");
        System.out.println("--------------------------------------");
        System.out.println("9. Buscar y Reservar Vuelos (Clientes)");
        System.out.println("--------------------------------------");
        System.out.println("10. Consultas");
        System.out.println("--------------------------------------");
        System.out.println("11. Gestión de Vuelos");
        System.out.println("--------------------------------------");
        System.out.println("0. Salir");
        System.out.println("======================================");
        System.out.print("Seleccione una opción: ");
    }

}