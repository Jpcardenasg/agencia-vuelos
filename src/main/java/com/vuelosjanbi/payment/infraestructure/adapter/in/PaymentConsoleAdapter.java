// package com.vuelosjanbi.payment.infraestructure.adapter.in;

// import java.util.Scanner;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;

// import com.vuelosjanbi.payment.application.PaymentService;
// import com.vuelosjanbi.payment.domain.Payment;
// import com.vuelosjanbi.paymentMethod.application.PaymentMethodService;

// @Controller
// public class PaymentConsoleAdapter {

// @Autowired
// private PaymentService paymentService;

// public void start() {

// Scanner scanner = new Scanner(System.in);
// List<Payment> payments;

// while (true) {
// payments = paymentService.getAllPayments();
// System.out.println("\n");
// System.out.println("1. Create Payment.");
// System.out.println("2. Update Payment.");
// System.out.println("3. Delete Payment.");
// System.out.println("4. List all Payments.");
// System.out.println("0. Exit.");

// int choice = scanner.nextInt();
// scanner.nextLine();

// switch (choice) {
// case 1:
// Payment payment = new Payment();
// System.out.println("Enter the card number:");
// payment.setCardNumber(scanner.nextLong());
// System.out.println("Enter the payment method id:");
// break;

// default:
// break;
// }

// }

// }
// }
