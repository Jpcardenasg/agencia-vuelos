package com.vuelosjanbi.documentType.application.DTO;

import java.util.List;

import com.vuelosjanbi.customer.application.DTO.CustomerDTO;

public class DocumentTypeDTO {
  private Long id;
  private String name;
  List<CustomerDTO> customers;

  public DocumentTypeDTO() {
  }

  public DocumentTypeDTO(String name) {
    this.name = name;
  }

  public DocumentTypeDTO(Long id, String name, List<CustomerDTO> customers) {
    this.id = id;
    this.name = name;
    this.customers = customers;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<CustomerDTO> getCustomers() {
    return customers;
  }

  public void setCustomers(List<CustomerDTO> customers) {
    this.customers = customers;
  }

}
