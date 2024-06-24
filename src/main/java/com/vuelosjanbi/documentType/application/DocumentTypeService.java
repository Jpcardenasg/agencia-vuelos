package com.vuelosjanbi.documentType.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vuelosjanbi.documentType.domain.models.DocumentType;
import com.vuelosjanbi.documentType.application.ports.out.DocumentTypeRepositoryPort;

@Service
public class DocumentTypeService {
    private final DocumentTypeRepositoryPort DocumentTypeRepositoryPort;

    public DocumentTypeService(DocumentTypeRepositoryPort DocumentTypeRepositoryPort) {
        this.DocumentTypeRepositoryPort = DocumentTypeRepositoryPort;
    }

    public DocumentType createDocumentType(DocumentType DocumentType) {
        return DocumentTypeRepositoryPort.save(DocumentType);
    }

    public DocumentType updateDocumentType(DocumentType DocumentType) {
        return DocumentTypeRepositoryPort.save(DocumentType);
    }

    public void deleteDocumentType(Long idDocumentType) {
        DocumentTypeRepositoryPort.deleteById(idDocumentType);
    }

    public List<DocumentType> getAllDocumentTypes() {
        return DocumentTypeRepositoryPort.findAll();
    }

}
