package com.vuelosjanbi.customer.infrastructure.adapters.in;

import java.util.List;
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
        List<DocumentType> dTypes = documentTypeService.getAllDocumentTypes();

        while (true) {
            System.out.println("1. Create Customer.");
            System.out.println("2. Update Customer.");
            System.out.println("3. Delete Customer.");
            System.out.println("4. List all Customers.");
            System.out.println("5. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createCustomer(scanner, dTypes);
                    break;

                default:
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
            System.out.printf("ID: %d, Name: %s", dType.getId(), dType.getName());
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
2
}
