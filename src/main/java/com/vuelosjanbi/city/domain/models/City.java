package com.vuelosjanbi.city.domain.models;

import java.util.List;

import com.vuelosjanbi.airport.domain.models.Airport;
import com.vuelosjanbi.country.domain.models.Country;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityName;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "city")
    private List<Airport> airports;

    public City(String cityName) {
        this.cityName = cityName;
    }

    public City(Long id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public City() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

}
