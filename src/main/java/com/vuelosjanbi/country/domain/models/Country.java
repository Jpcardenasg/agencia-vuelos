package com.vuelosjanbi.country.domain.models;

public class Country {
    private int countryId;
    private String countryName;

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
