package com.vuelosjanbi.planeRevision.domain.models;

import com.vuelosjanbi.plane.domain.models.Plane;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.sql.Date;

@Entity
public class PlaneRevision {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Temporal(TemporalType.DATE)
  Date revisionDate;

  @ManyToOne
  Plane plane;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getRevisionDate() {
    return revisionDate;
  }

  public void setRevisionDate(Date revisionDate) {
    this.revisionDate = revisionDate;
  }

  public Plane getPlane() {
    return plane;
  }

  public void setPlane(Plane plane) {
    this.plane = plane;
  }

  @Override
  public String toString() {
    return String.format("PlaneRevision: ID=%d | Revision Date=%s | Plane=%s", id, revisionDate, plane.getPlate());
  }

}
