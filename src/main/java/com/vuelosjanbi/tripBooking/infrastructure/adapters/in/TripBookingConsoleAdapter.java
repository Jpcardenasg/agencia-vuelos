package com.vuelosjanbi.tripBooking.infrastructure.adapters.in;

import java.sql.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

import com.vuelosjanbi.customer.application.CustomerService;
import com.vuelosjanbi.customer.domain.models.Customer;
import com.vuelosjanbi.documentType.application.DocumentTypeService;
import com.vuelosjanbi.flightConnection.application.FlightConnectionService;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;
import com.vuelosjanbi.flightFare.application.FlightFareService;
import com.vuelosjanbi.flightFare.domain.models.FlightFare;
import com.vuelosjanbi.payment.application.PaymentService;
import com.vuelosjanbi.payment.domain.Payment;
import com.vuelosjanbi.paymentMethod.application.PaymentMethodService;
import com.vuelosjanbi.paymentMethod.domain.PaymentMethod;
import com.vuelosjanbi.trip.application.TripService;
import com.vuelosjanbi.trip.domain.models.Trip;
import com.vuelosjanbi.tripBooking.application.TripBookingService;
import com.vuelosjanbi.tripBooking.domain.models.TripBooking;
import com.vuelosjanbi.tripBookingDetail.application.TripBookingDetailService;
import com.vuelosjanbi.tripBookingDetail.domain.models.TripBookingDetail;

@Controller
public class TripBookingConsoleAdapter {

    @Autowired
    private TripBookingService tripBookingService;

    @Autowired
    private TripBookingDetailService tripBookingDetailService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TripService tripService;

    @Autowired
    private FlightFareService flightFareService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private FlightConnectionService flightConnectionService;

    @Autowired
    private DocumentTypeService documentTypeService;

    public void start() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("1. Search available trips");
                System.out.println("2. Get all details about Trip Booking");
                System.out.println("3. Update Trip Booking");
                System.out.println("4. Delete Trip Booking");
                System.out.println("5. Get All Trip Bookings");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                if (scanner.hasNextInt()) {
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1:
                            searchAvailableTrips(scanner);
                            break;
                        case 2:
                            detailsTripBooking(scanner);
                            break;
                        case 3:
                            updateTripBooking(scanner);
                            break;
                        case 4:
                            deleteTripBooking(scanner);
                            break;
                        case 5:
                            getAllTripBookings();
                            break;
                        case 6:
                            System.out.println("Exiting...");
                            return;
                        default:
                            System.out.println("Invalid option. Please select a valid option.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Clear the invalid input
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
                scanner.next(); // Clear the buffer in case of an error
            }
        }
    }

    private void searchAvailableTrips(Scanner scanner) {
        String originCityName = getInputString(scanner, "Enter origin city name: ");
        String destinationCityName = getInputString(scanner, "Enter destination city name: ");
        Date tripDate = getInputDate(scanner, "Enter trip date (YYYY-MM-DD): ");

        List<Trip> tripsStopOver = tripService.getTripsByOriginCityAndFinalDestinationCityWithStopover(originCityName,
                destinationCityName, tripDate);
        List<Trip> directTrip = tripService.getTripsByOriginCityAndDestinationCity(originCityName, destinationCityName,
                tripDate);
        List<Trip> combinedTrips = new ArrayList<>();
        combinedTrips.addAll(tripsStopOver);
        combinedTrips.addAll(directTrip);
        System.out.println("Available trips: ");
        for (Trip trip : combinedTrips) {
            System.out.println(trip + "\n" + flightConnectionService.getConnectionByTripId(trip.getId()).get() + "\n");
        }
        if (tripsStopOver.isEmpty() && directTrip.isEmpty()) {
            System.out.println("No trips available.");
            return;
        }
        scanner.nextLine();
        if (getInputString(scanner, "Do you want to book a trip? (y/n): ").equalsIgnoreCase("y")) {
            bookTrip(scanner, combinedTrips);
        }
    }

    private void bookTrip(Scanner scanner, List<Trip> trips) {
        long tripId = getInputLong(scanner, "Enter trip id: ");
        Trip trip = tripService.getTripById(tripId);
        if (trip == null) {
            System.out.println("Trip not found.");
            return;
        }

        TripBooking tripBooking = new TripBooking(Date.valueOf(LocalDate.now()), trip);
        while (true) {
            scanner.nextLine();
            String passengerName = getInputString(scanner, "Enter passenger name: ");
            int age = getInputInt(scanner, "Enter passenger age: ");
            documentTypeService.getAllDocumentTypes()
                    .forEach(documentType -> System.out.printf("Document type id: %d  Document type: %s \n",
                            documentType.getId(), documentType.getName()));
            System.out.println("Enter document type: ");
            Long documentType = scanner.nextLong();
            scanner.nextLine();
            String passengerDocument = getInputString(scanner, "Enter passenger document: ");

            Customer customer = new Customer();
            customer.setId(passengerDocument);
            customer.setName(passengerName);
            customer.setAge(age);
            customer.setDocumentType(documentTypeService.getDocumentTypeById(documentType));
            customerService.createCustomer(customer);

            Customer customer1 = customerService.getCustomer(passengerDocument).orElse(null);

            TripBookingDetail tripBookingDetail = new TripBookingDetail();
            tripBookingDetail.setCustomer(customer1);
            tripBookingDetail.setTripBooking(tripBooking);

            List<FlightFare> flightFares = flightFareService.getAllFlightFares();
            flightFares.forEach(flightFare -> System.out.printf("Flight fare id: %d  Flight fare price: %.2f%n",
                    flightFare.getId(), flightFare.getValue()));
            long flightFareId = getInputLong(scanner, "Enter flight fare id: ");
            FlightFare flightFare = flightFareService.getFlightFareById(flightFareId).orElse(null);
            if (flightFare == null) {
                System.out.println("Flight fare not found.");
                return;
            }

            FlightConnection flightConnection = flightConnectionService.getConnectionByTripId(tripId).orElse(null);
            if (flightConnection == null) {
                System.out.println("Flight connection not found.");
                return;
            }

            System.out.println("Select available seat number: ");
            System.out.println(flightConnection.getSeats().size());
            flightConnection.getSeats().forEach(seat -> {
                if (seat.isAvailable()) {
                    System.out.println("Seat number: " + seat.getSeatNumber());
                }
            });

            int seatNumber = getInputInt(scanner, "Enter seat number: ");
            flightConnection.getSeats().forEach(seat -> {
                if (!seat.isAvailable()) {
                    System.out.println(
                            "Seat number: " + seat.getSeatNumber() + " is not available. Please select another.");
                    return;
                } else {
                    if (seat.getSeatNumber().equals(String.valueOf(seatNumber))) {
                        seat.setAvailable(false);
                    }
                }
            });

            tripBookingDetail.setSeatNumber(seatNumber);
            tripBookingDetail.setFlightFare(flightFare);

            scanner.nextLine();
            if (getInputString(scanner, "Do you want to add another passenger? (y/n): ").equalsIgnoreCase("n")) {
                paymentProcess(scanner, tripBooking, tripBookingDetail);
                break;
            }
        }
    }

    private void paymentProcess(Scanner scanner, TripBooking tripBooking, TripBookingDetail tripBookingDetail) {
        List<PaymentMethod> paymentMethods = paymentMethodService.getAllPaymentMethods();
        paymentMethods.forEach(paymentMethod -> System.out.printf("Payment method id: %d  Payment method: %s \n",
                paymentMethod.getId(), paymentMethod.getMethod()));
        Long paymentMethod = getInputLong(scanner, "How do you want to pay? ");
        Payment payment = new Payment();
        System.out.println("Enter card number: ");
        long cardNumber = scanner.nextLong();
        payment.setCardNumber(cardNumber);
        payment.setPaymentMethod(paymentMethodService.getPaymentMethodById(paymentMethod).orElse(null));
        payment.setCustomer(tripBookingDetail.getCustomer());
        paymentService.createPayment(payment);
        tripBookingDetail.setPayment(payment);
        tripBookingService.createTripBooking(tripBooking);
        tripBookingDetailService.createTripBookingDetail(tripBookingDetail);

    }

    public void updateTripBooking(Scanner scanner) {
        System.out.println("Enter the trip booking id:");
        Long tripBookingIdToUpdate = scanner.nextLong();
        TripBooking tripBookingToUpdate = tripBookingService.getTripBooking(tripBookingIdToUpdate);
        System.out.println("Enter day of the trip booking:");
        String dayToUpdate = scanner.next();
        System.out.println("Enter month of the trip booking:");
        String monthToUpdate = scanner.next();
        System.out.println("Enter year of the trip booking:");
        String yearToUpdate = scanner.next();
        String tripBookingDateToUpdate = yearToUpdate + "-" + monthToUpdate + "-" + dayToUpdate;
        Date tripDateToUpdate = Date.valueOf(tripBookingDateToUpdate);
        tripBookingToUpdate.setDate(tripDateToUpdate);
        tripBookingService.updateTripBooking(tripBookingToUpdate);
        System.out.println("Trip booking updated successfully!");
    }

    public void detailsTripBooking(Scanner scanner) {
        System.out.println("How do you want to search for the trip booking, customer or trip?");
        String search = scanner.next();
        if (search.equals("customer")) {
            System.out.println("Enter the customer id:");
            Customer customer = customerService.getCustomer(scanner.next()).orElse(null);
            if (customer == null) {
                System.out.println("Customer not found!");
                return;
            }
            List<TripBooking> tripBookings = tripBookingService.getTripBookingsByCustomerId(customer);
            System.out.println("The trip bookings are:");
            for (int i = 0; i < tripBookings.size(); i++) {
                System.out.println(tripBookings.get(i));
            }
        } else {
            System.out.println("Enter the trip id:");
            Long tripId = scanner.nextLong();
            List<TripBooking> tripBookings = tripBookingService.getTripBookingsByTripId(tripId);
            System.out.println("The trip bookings are:");
            for (int i = 0; i < tripBookings.size(); i++) {
                System.out.println(tripBookings.get(i));
            }
        }

    }

    public void deleteTripBooking(Scanner scanner) {
        System.out.println("Enter the trip booking id:");
        tripBookingService.getAllTripBookings().forEach(System.out::println);
        Long tripBookingIdToDelete = scanner.nextLong();
        tripBookingService.deleteTripBooking(tripBookingIdToDelete);
        if (tripBookingService.getTripBooking(tripBookingIdToDelete) != null) {
            System.out.println("Trip booking not deleted!");
            return;
        }
        System.out.println("Trip booking deleted successfully!");
    }

    public void getAllTripBookings() {
        tripBookingDetailService.getAllTripBookingDetails().forEach(System.out::println);
    }

    // Helper methods for input
    private int getInputInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. " + prompt);
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    private long getInputLong(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextLong()) {
            System.out.print("Invalid input. " + prompt);
            scanner.nextLine();
        }
        return scanner.nextLong();
    }

    // private double getInputDouble(Scanner scanner, String prompt) {
    // System.out.print(prompt);
    // while (!scanner.hasNextDouble()) {
    // System.out.print("Invalid input. " + prompt);
    // scanner.next();
    // }
    // return scanner.nextDouble();
    // }

    private String getInputString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private Date getInputDate(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String dateStr = scanner.next();
        try {
            return Date.valueOf(dateStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please enter in YYYY-MM-DD format.");
            return getInputDate(scanner, prompt);
        }
    }

}