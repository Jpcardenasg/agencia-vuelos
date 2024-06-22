package com.vuelosjanbi.planeStatus.domain.models;

import java.util.List;

import com.vuelosjanbi.plane.domain.models.Plane;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class PlaneStatus {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "status")
  private List<Plane> planes;

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

  public List<Plane> getPlanes() {
    return planes;
  }

  public void setPlanes(List<Plane> planes) {
    this.planes = planes;
  }

}
