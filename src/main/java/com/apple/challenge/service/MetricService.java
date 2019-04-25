package com.apple.challenge.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class MetricService {

    private ConcurrentMap<String, Integer> statusMetric;

    public MetricService() {
        statusMetric = new ConcurrentHashMap<String, Integer>();
    }

    /**
     * Metrics of user selection. This is a very basic implementation to get some stats
     * @param selection
     */
    public void increaseCount(String selection) {
        if(selection == null){
            return;
        }
        Integer statusCount = statusMetric.getOrDefault(selection, 0) + 1;
        statusMetric.put(selection, statusCount);
    }

    /**
     * Returns metrics
     * @return
     */
    public Map getStatusMetric() {
        return statusMetric;
    }
}