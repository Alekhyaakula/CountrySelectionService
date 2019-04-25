package com.apple.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Continent {

    @JsonProperty("continent")
    private String continentName;
    private Country[] countries;

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public Country[] getCountries() {
        return countries;
    }

    public void setCountries(Country[] countries) {
        this.countries = countries;
    }
}
