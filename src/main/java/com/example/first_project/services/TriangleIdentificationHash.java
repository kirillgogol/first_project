package com.example.first_project.services;

import com.example.first_project.entities.Triangle;
import com.example.first_project.entities.TriangleIdentification;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Component
public class TriangleIdentificationHash {
    @JsonProperty
    private final HashMap<Triangle, TriangleIdentification> hashMap = new LinkedHashMap<>();

    public boolean findByKey(Triangle key) {
        return hashMap.containsKey(key);
    }

    public void addToMap(Triangle key, TriangleIdentification result) {
        hashMap.put(key, result);
    }

    public TriangleIdentification getTriangleIdentification(Triangle key) {
        return hashMap.get(key);
    }

    public boolean isContain(Triangle key) {
        return hashMap.containsKey(key);
    }
}
