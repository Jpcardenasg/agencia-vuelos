package com.vuelosjanbi.documentType.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.documentType.domain.models.DocumentType;

public interface DocumentTypeRepositoryPort {

  DocumentType save(DocumentType documentType);

  void deleteById(Long documentTypeId);

  List<DocumentType> findAll();

  Optional<DocumentType> findById(Long documentTypeId);

  Optional<DocumentType> findByName(String documentTypeName);

}
