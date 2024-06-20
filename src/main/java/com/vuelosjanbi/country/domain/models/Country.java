package com.vuelosjanbi.country.domain.models;

import java.util.List;

import javax.print.DocFlavor.STRING;

import com.vuelosjanbi.city.domain.models.City;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;

    @OneToMany(mappedBy = "country")
    private List<City> cities;

    public Country() {
    }

    public Country(String countryName) {
        this.name = countryName;
    }

    public Country(String countryId, String countryName) {
        this.id = countryId;
        this.name = countryName;
    }

    public Country(int countryId, String countryName) {
        this.id = String.valueOf(countryId);
        this.name = countryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String countryName) {
        this.name = countryName;
    }

    public String getId() {
        return id;
    }

    public void setId(String countryId) {
        this.id = countryId;
    }
}
