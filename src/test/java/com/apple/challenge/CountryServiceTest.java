package com.apple.challenge;

import com.apple.challenge.service.CountryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    public void testAllContinents() {
        String response = countryService.getCountries(null, null);
        Assert.assertNotNull(response);

        //All continent data
        Assert.assertTrue(response.contains("Africa"));
        Assert.assertTrue(response.contains("America"));
        Assert.assertTrue(response.contains("Asia"));
        Assert.assertTrue(response.contains("Europe"));
        Assert.assertTrue(response.contains("Oceania"));
    }

    @Test
    public void testContinentRequest() {
        String response = countryService.getCountries("Africa", null);
        Assert.assertNotNull(response);

        //Only Africa should be returned
        Assert.assertTrue(response.contains("Africa"));
        Assert.assertFalse(response.contains("America"));
        Assert.assertFalse(response.contains("Asia"));
        Assert.assertFalse(response.contains("Europe"));
        Assert.assertFalse(response.contains("Oceania"));
    }

    @Test
    public void testCountryRequest() {
        String response = countryService.getCountries(null, "Egypt");
        Assert.assertNotNull(response);

        //Only Egypt flag
        Assert.assertTrue(response.contains("\uD83C\uDDEA\uD83C\uDDEC"));
    }
}