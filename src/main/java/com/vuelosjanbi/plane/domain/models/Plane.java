package com.vuelosjanbi.plane.domain.models;

import java.sql.Date;

import com.vuelosjanbi.planeModel.domain.models.PlaneModel;
import com.vuelosjanbi.planeStatus.domain.models.PlaneStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String plate;
    private Integer capacity;
    private Date fabricationDate;

    @ManyToOne
    private PlaneStatus status;
    @ManyToOne
    private PlaneModel model;

    public Plane(String plate, Integer capacity, Date fabricationDate, PlaneStatus status, PlaneModel model) {
        this.plate = plate;
        this.capacity = capacity;
        this.fabricationDate = fabricationDate;
        this.status = status;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plates) {
        this.plate = plates;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Date getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(Date fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public PlaneStatus getStatus() {
        return status;
    }

    public void setStatus(PlaneStatus status) {
        this.status = status;
    }

    public PlaneModel getModel() {
        return model;
    }

    public void setModel(PlaneModel model) {
        this.model = model;
    }

}
