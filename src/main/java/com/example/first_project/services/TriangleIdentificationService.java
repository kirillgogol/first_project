package com.example.first_project.services;

import com.example.first_project.counter.RequestCounterThread;
import com.example.first_project.entities.Triangle;
import com.example.first_project.entities.TriangleIdentification;
import com.example.first_project.exceptions.TriangleDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

@Service
public class TriangleIdentificationService {

    private static final Logger logger = LogManager.getLogger(TriangleIdentificationService.class);
    private Triangle triangle;

    @Autowired
    private TriangleIdentificationHash hashMap;

    public TriangleIdentification identificate(Triangle triangle) {
        new RequestCounterThread().start();
        TriangleIdentification triangleIdentification;

        if(hashMap.findByKey(triangle)) {
            triangleIdentification = hashMap.getTriangleIdentification(triangle);
            logger.info("get hashMap");
        }
        else {
            boolean isEquilateral = false, isIsosceles = false, isRight = false;
            double x = triangle.getX();
            double y = triangle.getY();
            double z = triangle.getZ();
            if (x == y && x == z) {
                isEquilateral = true;
            }

            if ((x == y) || (x == z) || (y == z)) {
                isIsosceles = true;
            }

            double minNum1 = Math.min(x, y);
            double maxNum = Math.max(x, y);
            double minNum2 = Math.min(maxNum, z);
            maxNum = Math.max(maxNum, z);

            if (Math.pow(maxNum, 2) == Math.pow(minNum1, 2) + Math.pow(minNum2, 2)) {
                isRight = true;
            }

            triangleIdentification = new TriangleIdentification(isEquilateral, isIsosceles, isRight);
            hashMap.addToMap(triangle, triangleIdentification);
            logger.info("add hashMap");

        }
        return triangleIdentification;
    }

	public void validate(double x, double y, double z) {
		if (x + y <= z || y + z <= x || x + z <= y) {
			throw new TriangleDoesNotExistException("Triangle does not exist");
		}
	}

    public TriangleIdentificationHash getTriangleIdentificationHash(){
        return hashMap;
    }

    public static double calculateSumOfSides(Triangle triangle) {
        List<Double> resList = Arrays.asList(triangle.getX(), triangle.getY(), triangle.getZ());
        return resList.stream().mapToDouble(Double::doubleValue).sum();
    }

    public static double findMinOfSides(Triangle triangle) {
        List<Double> resList = Arrays.asList(triangle.getX(), triangle.getY(), triangle.getZ());

        return resList.stream().mapToDouble(Double::doubleValue).min().getAsDouble();
    }

    public static double findMaxOfSides(Triangle triangle) {
        List<Double> resList = Arrays.asList(triangle.getX(), triangle.getY(), triangle.getZ());
        return resList.stream().mapToDouble(Double::doubleValue).max().getAsDouble();
    }
}
