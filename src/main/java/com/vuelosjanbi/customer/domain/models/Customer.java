package com.vuelosjanbi.customer.domain.models;

import com.vuelosjanbi.documentType.domain.models.DocumentType;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Customer {
  @Id
  private String id;
  private String name;
  private Integer age;

  @ManyToOne
  private DocumentType documentType;

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

  public DocumentType getDocumentType() {
    return documentType;
  }

  public void setDocumentType(DocumentType documentType) {
    this.documentType = documentType;
  }

  @Override
  public String toString() {
    return String.format("Id: %d Name: %s", id, name);
  }

}
