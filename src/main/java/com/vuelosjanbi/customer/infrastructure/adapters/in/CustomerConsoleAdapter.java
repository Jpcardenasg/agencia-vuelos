package com.vuelosjanbi.customer.infrastructure.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.customer.application.CustomerService;
import com.vuelosjanbi.customer.domain.models.Customer;
import com.vuelosjanbi.documentType.application.DocumentTypeService;
import com.vuelosjanbi.documentType.domain.models.DocumentType;

@Controller
public class CustomerConsoleAdapter {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private DocumentTypeService documentTypeService;

    public void start() {

        Scanner scanner = new Scanner(System.in);
        List<Customer> customers;

        while (true) {
            customers = customerService.getAllCustomers();
            System.out.println("\n");
            System.out.println("1. Register Customer.");
            System.out.println("2. Update Customer.");
            System.out.println("3. Delete Customer.");
            System.out.println("4. Find Customer by ID.");
            System.out.println("5. List all Customers.");
            System.out.println("0. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createCustomer(scanner);
                    break;
                case 2:
                    updateCustomer(scanner, customers);
                    break;
                case 3:
                    deleteCustomer(scanner, customers);
                    break;
                case 4:
                    findCustomer(scanner);
                    break;
                case 5:
                    listCustomers(customers);
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

    private void createCustomer(Scanner scanner) {
        List<DocumentType> dTypes = documentTypeService.getAllDocumentTypes();
        System.out.println("Type the Identification Number:");
        String id = scanner.nextLine();
        System.out.println("Type the Customer name:");
        String name = scanner.nextLine();
        System.out.println("Type the Customer age:");
        Integer age = scanner.nextInt();
        scanner.nextLine();

        for (DocumentType dType : dTypes) {
            System.out.printf("ID: %d  Name: %s\n", dType.getId(), dType.getName());
        }
        System.out.println("Choose the Document Type:");
        Long dTypeChoice = scanner.nextLong();
        scanner.nextLine();

        DocumentType chosenDocumentType = dTypes.stream()
                .filter(dType -> dType.getId().equals(dTypeChoice))
                .findFirst()
                .orElse(null);

        if (chosenDocumentType == null) {
            System.out.println("Invalid document type ID.");
            return;
        }

        Customer newCustomer = new Customer();
        newCustomer.setId(id);
        newCustomer.setName(name);
        newCustomer.setAge(age);
        newCustomer.setDocumentType(chosenDocumentType);

        customerService.createCustomer(newCustomer);

        System.out.println("Customer created successfully!");
    }

    private void updateCustomer(Scanner scanner, List<Customer> customers) {
        List<DocumentType> dTypes = documentTypeService.getAllDocumentTypes();

        if (customers.isEmpty()) {
            System.out.println("There are not customers registered.");
            return;
        }

        Customer chosenCustomer = choosecustomer(customers, scanner);
        if (chosenCustomer == null) {
            System.out.println("Invalid Customer ID.");
            return;
        }

        String newCustomerName = getInput("Type the new name:", scanner);
        Integer newCustomerAge = getInt("Type the new customer age:", scanner);
        scanner.nextLine();

        for (DocumentType dType : dTypes) {
            System.out.printf("ID: %d  Name: %s\n", dType.getId(), dType.getName());
        }
        System.out.println("Choose the Document Type:");
        Long dTypeChoice = scanner.nextLong();
        scanner.nextLine();

        DocumentType chosenDocumentType = dTypes.stream()
                .filter(dType -> dType.getId().equals(dTypeChoice))
                .findFirst()
                .orElse(null);

        if (chosenDocumentType == null) {
            System.out.println("Invalid document type ID.");
            return;
        }
        chosenCustomer.setName(newCustomerName);
        chosenCustomer.setAge(newCustomerAge);
        chosenCustomer.setDocumentType(chosenDocumentType);

        customerService.updateCustomer(chosenCustomer);

        System.out.println("Customer updated successfully!");
    }

    private void deleteCustomer(Scanner scanner, List<Customer> customers) {
        System.out.println("Customers:");
        listCustomers(customers);
        System.out.println("Type the Customer Id you want to delete");
        String deleteCustomerChoice = scanner.nextLine();

        customerService.deleteCustomer(deleteCustomerChoice);
        System.out.println("Customer deleted successfully!");
    }

    private void findCustomer(Scanner scanner) {
        System.out.println("Type the Customer ID:");
        String customerId = scanner.nextLine();

        Optional<Customer> customerOpt = customerService.getCustomer(customerId);

        customerOpt.ifPresentOrElse(customer -> {
            System.out.printf("ID: %s  Name: %s, Age: %d, Document Type: %s\n",
                    customer.getId(), customer.getName(), customer.getAge(), customer.getDocumentType().getName());
        }, () -> System.out.println("Customer not found"));

    }

    private void listCustomers(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.printf("ID: %s  Name: %s, Age: %d, Document Type: %s\n",
                    customer.getId(), customer.getName(), customer.getAge(), customer.getDocumentType().getName());
        }
    }

    // Input Helpers
    private String getInput(String prompt, Scanner scanner) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private Integer getInt(String prompt, Scanner scanner) {
        System.out.println(prompt);
        return scanner.nextInt();
    }

    private Customer choosecustomer(List<Customer> customers, Scanner scanner) {
        System.out.println("Customers:");
        for (Customer customer : customers) {
            System.out.printf("ID: %s  Name: %s \n", customer.getId(), customer.getName());
        }
        String customerId = getInput("Choose the ID of the customer:", scanner);
        return customerService.getCustomer(customerId).orElse(null);
    }
}
