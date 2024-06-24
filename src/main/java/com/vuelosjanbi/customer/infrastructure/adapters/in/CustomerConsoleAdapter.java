package com.vuelosjanbi.customer.infrastructure.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.customer.application.CustomerService;
import com.vuelosjanbi.customer.domain.models.Customer;
import com.vuelosjanbi.customer.infrastructure.adapters.out.MySQLCustomerRepository;
import com.vuelosjanbi.documentType.application.DocumentTypeService;
import com.vuelosjanbi.documentType.domain.models.DocumentType;
import com.vuelosjanbi.documentType.infrastructure.adapters.out.MySQLDocumentTypeRepository;

@Controller
public class CustomerConsoleAdapter {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private DocumentTypeService documentTypeService;

    private final String url = "jdbc:mysql://localhost:3307/vuelosjanpi";
    private final String user = "root";
    private final String password = "1324";

    public void start(boolean useJpa) {
        if (useJpa) {
            System.out.println("Using JPA");
        } else {
            documentTypeService = new DocumentTypeService(new MySQLDocumentTypeRepository(url, user, password));
            customerService = new CustomerService(new MySQLCustomerRepository(url, user, password,
                    new MySQLDocumentTypeRepository(url, user, password)));
            System.out.println("Using MYSQL");
        }

        Scanner scanner = new Scanner(System.in);
        List<Customer> customers;
        List<DocumentType> dTypes = documentTypeService.getAllDocumentTypes();

        while (true) {
            customers = customerService.getAllCustomers();
            System.out.println("1. Register Customer.");
            System.out.println("2. Update Customer.");
            System.out.println("3. Delete Customer.");
            System.out.println("4. Find Customer by ID.");
            System.out.println("5. List all Customers.");
            System.out.println("6. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createCustomer(scanner, dTypes);
                    break;
                case 2:
                    updateCustomer(scanner, dTypes, customers);
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
                case 6:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void createCustomer(Scanner scanner, List<DocumentType> dTypes) {
        System.out.println("Type the Identification Number:");
        String id = scanner.nextLine();
        System.out.println("Type the Customer name:");
        String name = scanner.nextLine();
        System.out.println("Type the Customer age:");
        Integer age = scanner.nextInt();
        scanner.nextLine();

        for (DocumentType dType : dTypes) {
            System.out.printf("ID: %d, Name: %s\n", dType.getId(), dType.getName());
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

    private void updateCustomer(Scanner scanner, List<DocumentType> dTypes, List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.printf("ID: %d, Name: %s\n", customer.getId(), customer.getName());
        }
        System.out.println("Choose the customer ID you want to modify:");

        String customerId = scanner.nextLine();

        Customer customerToUpdate = customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst()
                .orElse(null);

        if (customerToUpdate == null) {
            System.out.println("Invalid Customer ID.");
            return;
        }

        System.out.println("Type the new ID:");
        String newId = scanner.nextLine();

        System.out.println("Type the new customer name:");
        String newName = scanner.nextLine();
        System.out.println("Type the new customer age:");
        Integer newAge = scanner.nextInt();
        scanner.nextLine();

        for (DocumentType dType : dTypes) {
            System.out.printf("ID: %d, Name: %s\n", dType.getId(), dType.getName());
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
        customerToUpdate.setId(newId);
        customerToUpdate.setName(newName);
        customerToUpdate.setAge(newAge);
        customerToUpdate.setDocumentType(chosenDocumentType);

        customerService.updateCustomer(customerToUpdate);

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
            System.out.printf("ID: %d, Name: %s, Age: %d, Document Type: %s\n",
                    customer.getId(), customer.getName(), customer.getAge(), customer.getDocumentType().getName());
        }, () -> System.out.println("Customer not found"));

    }

    private void listCustomers(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.printf("ID: %d, Name: %s, Age: %d, Document Type: %s\n",
                    customer.getId(), customer.getName(), customer.getAge(), customer.getDocumentType().getName());
        }
    }
}
