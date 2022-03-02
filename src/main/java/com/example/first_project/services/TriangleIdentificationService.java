package com.example.first_project.services;

import com.example.first_project.entities.TriangleIdentification;
import org.springframework.stereotype.Service;

@Service
public class TriangleIdentificationService {

    public TriangleIdentification identificate(double x, double y, double z) {
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

        return new TriangleIdentification(isEquilateral, isIsosceles, isRight);
    }
}
