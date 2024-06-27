package com.vuelosjanbi.documentType.infrastructure.adapters.in;

import java.util.List;
import java.util.Scanner;

import com.vuelosjanbi.documentType.application.DocumentTypeService;
import com.vuelosjanbi.documentType.domain.models.DocumentType;

public class DocumentTypeConsoleAdapter {

    private final DocumentTypeService documentTypeService;

    public DocumentTypeConsoleAdapter(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    public void start(boolean useJpa) {
        Scanner scanner = new Scanner(System.in);
        List<DocumentType> documentTypes;

        while (true) {
            documentTypes = documentTypeService.getAllDocumentTypes();
            System.out.println("1. Create Document Type.");
            System.out.println("2. Update Document Type.");
            System.out.println("3. Delete Document Type.");
            System.out.println("4. List all document types.");
            System.out.println("5. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Type the name of the Document Type:");
                    String documentTypeName = scanner.nextLine();

                    DocumentType newDocumentType = new DocumentType(documentTypeName);
                    documentTypeService.createDocumentType(newDocumentType);
                    break;

                case 2:
                    System.out.println("Choose the Document Type you want to modify:");
                    documentTypes.forEach(doc -> {
                        System.out.printf("ID: %d =, Name: %s\n", doc.getId(), doc.getName());
                    });

                    Long updateDocumentTypeId = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Type the new name of the Document:");
                    String newDocumentTypeName = scanner.nextLine();

                    DocumentType updatedDocumentType = new DocumentType(updateDocumentTypeId, newDocumentTypeName);
                    documentTypeService.updateDocumentType(updatedDocumentType);
                    break;

                case 3:
                    System.out.println("Choose the DocumentType you want to delete:");
                    documentTypes.forEach(doc -> {
                        System.out.printf("ID: %d =, Name: %s\n", doc.getId(), doc.getName());
                    });

                    Long deleteDocumentTypeId = scanner.nextLong();
                    scanner.nextLine();

                    documentTypeService.deleteDocumentType(deleteDocumentTypeId);
                    break;

                case 4:
                    System.out.println("List of Document Types:");
                    documentTypes.forEach(doc -> {
                        System.out.printf("ID: %d =, Name: %s\n", doc.getId(), doc.getName());
                    });

                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option, please try again");
            }
        }

    }

}
