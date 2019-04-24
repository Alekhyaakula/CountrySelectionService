package com.apple.challenge.controller;

import com.apple.challenge.service.CountryService;
import com.apple.challenge.service.MetricService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class CountryController {

    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    CountryService countryService;

    @Autowired
    MetricService metricService;

    /**
     *
     * @param continent continent that the user selected
     * @param country country that the user selected
     * @return All data, if continent is provided then pull list of countries and flag. If countries is provided then RETURN the flag
     */
    @RequestMapping(value="/country", method = RequestMethod.GET)
    public @ResponseBody
    String getCountries(String continent, String country){
        logger.info("User Selection is continent={}, country={}", continent, country);
        return countryService.getCountries(continent, country);
    }

    @RequestMapping(value = "/country/metrics", method = RequestMethod.GET)
    @ResponseBody
    public Map getStatusMetric() {
        return metricService.getStatusMetric();
    }
}