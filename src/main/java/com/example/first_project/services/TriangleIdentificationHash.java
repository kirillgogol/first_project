package com.example.first_project.services;

import com.example.first_project.entities.Triangle;
import com.example.first_project.entities.TriangleIdentification;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TriangleIdentificationHash {
    private final HashMap<Triangle, TriangleIdentification> hashMap = new HashMap<>();

    public boolean isContain(Triangle key) {
        return hashMap.containsKey(key);
    }

    public void addToMap(Triangle key, TriangleIdentification result) {
        hashMap.put(key, result);
    }

    public TriangleIdentification getParameters(Triangle key) {
        return hashMap.get(key);
    }
}
