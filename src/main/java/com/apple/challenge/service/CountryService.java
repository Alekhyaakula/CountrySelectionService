package com.apple.challenge.service;

import com.apple.challenge.dto.Continent;
import com.apple.challenge.dto.Country;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);

    private static List<Continent> continentList;

    /**
     * When the service is initialized load the country list from json file
     */
    public CountryService() {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("continents.json")) {
            String staticCountryList = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            continentList = mapper.readValue(staticCountryList,new TypeReference<List<Continent>>() { });
        } catch (IOException e) {
            logger.error("Failed to process Static Country Json file", e);
        }
    }

    /**
     * @param continentSelected continent that the user selected
     * @param countrySelected country that the user selected
     * @return All data, if continent is provided then pull list of countries and flag. If countries is provided then RETURN the flag
     */
    public String getCountries(String continentSelected, String countrySelected) {
        ObjectMapper mapper = new ObjectMapper();
        String response = null;
        Optional<Continent> continentFiltered;

        try {
            if (StringUtils.isEmpty(continentSelected) && StringUtils.isEmpty(countrySelected)) {
                //Return continentList;
                return mapper.writeValueAsString(continentList);

            }
            else if (!StringUtils.isEmpty(continentSelected)) {
                //If Continent is selected
                continentFiltered = continentList.stream().filter(continent -> continent.getContinent().equals(continentSelected)).findAny();

                //Return countries
                response = mapper.writeValueAsString(continentFiltered.get().getCountries());

            } else {
                //If Country is selected
                continentFiltered = continentList.stream().filter(continent -> Arrays.asList(continent.getCountries()).stream().anyMatch(s -> s.getName().equals(countrySelected))).findAny();
                Optional<Country> country = Arrays.asList(continentFiltered.get().getCountries()).stream().filter(country1 -> country1.getName().equals(countrySelected)).findAny();

                //Return Flag
                response = mapper.writeValueAsString(country.get().getFlag());
            }
        }
        catch (JsonProcessingException e){
            logger.error("Error Parsing",e);
        }
        return response;
    }
}