package com.vuelosjanbi.documentType.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.documentType.application.ports.out.DocumentTypeRepositoryPort;
import com.vuelosjanbi.documentType.domain.models.DocumentType;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long>, DocumentTypeRepositoryPort {

}
