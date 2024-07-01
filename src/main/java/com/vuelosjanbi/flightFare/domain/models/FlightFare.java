package com.vuelosjanbi.flightFare.domain.models;

import java.util.List;

import com.vuelosjanbi.tripBookingDetail.domain.models.TripBookingDetail;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class FlightFare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String details;
    private Double value;

    @OneToMany(mappedBy = "flightFare")
    private List<TripBookingDetail> tripBookingDetails;

    public FlightFare() {
    }

    public FlightFare(Long id, String description, String details, Double value) {
        this.id = id;
        this.description = description;
        this.details = details;
        this.value = value;
    }

    public FlightFare(String description, String details, Double value) {
        this.description = description;
        this.details = details;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(" ")
                .append("\n\tId: ").append(id)
                .append(",\n\tDescription: ").append(description)
                .append(",\n\tDetails: ").append(details)
                .append(",\n\tValue: ").append(value);
        return sb.toString();
    }

}
