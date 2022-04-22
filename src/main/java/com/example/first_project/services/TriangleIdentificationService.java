package com.example.first_project.services;

import com.example.first_project.counter.RequestCounterThread;
import com.example.first_project.entities.Triangle;
import com.example.first_project.entities.TriangleIdentification;
import com.example.first_project.exceptions.TriangleDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class TriangleIdentificationService {

    private static final Logger logger = LogManager.getLogger(TriangleIdentificationService.class);
    private Triangle triangle;

    @Autowired
    private TriangleIdentificationHash hashMap;

    public TriangleIdentification identificate(double x, double y, double z) {
        new RequestCounterThread().start();
        triangle = new Triangle(x, y, z);
        TriangleIdentification triangleIdentification;

        if(hashMap.findByKey(triangle)) {
            triangleIdentification = hashMap.getTriangleIdentification(triangle);
            logger.info("get hashMap");
        }
        else {
            boolean isEquilateral = false, isIsosceles = false, isRight = false;

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
}
