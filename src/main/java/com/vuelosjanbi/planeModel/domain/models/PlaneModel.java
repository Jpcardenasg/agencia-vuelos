package com.vuelosjanbi.planeModel.domain.models;

import com.vuelosjanbi.planeManufacturer.domain.models.PlaneManufacturer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PlaneModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @ManyToOne
  private PlaneManufacturer planeManufacturer;

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

  public PlaneManufacturer getPlaneManufacturer() {
    return planeManufacturer;
  }

  public void setPlaneManufacturer(PlaneManufacturer planeManufacturer) {
    this.planeManufacturer = planeManufacturer;
  }

}
