package com.vuelosjanbi.customer.application.DTO;

import com.vuelosjanbi.documentType.application.DTO.DocumentTypeDTO;

public class CustomerDTO {
  private String id;
  private String name;
  private Integer age;

  private DocumentTypeDTO documentType;

  public CustomerDTO() {
  }

  public CustomerDTO(String id, String name, Integer age, DocumentTypeDTO documentType) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.documentType = documentType;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public DocumentTypeDTO getDocumentType() {
    return documentType;
  }

  public void setDocumentType(DocumentTypeDTO documentType) {
    this.documentType = documentType;
  }

}
