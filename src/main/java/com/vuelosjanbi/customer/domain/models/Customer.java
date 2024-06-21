package com.vuelosjanbi.customer.domain.models;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;

@Entity
public class Customer {
  @Id
  private String id;
  private String name;
  private Integer age;

  // @OneToMany(mappedBy = "customer")
  private Integer idDocument;

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

  public Integer getIdDocument() {
    return idDocument;
  }

  public void setIdDocument(Integer idDocument) {
    this.idDocument = idDocument;
  }

}
