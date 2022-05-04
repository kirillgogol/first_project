package com.example.first_project.controllers;

import com.example.first_project.entities.Triangle;
import com.example.first_project.entities.TriangleIdentification;
import com.example.first_project.exceptions.RestExceptionHandler;
import com.example.first_project.exceptions.TriangleDoesNotExistException;
import com.example.first_project.services.TriangleIdentificationHash;
import com.example.first_project.services.TriangleIdentificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

@Validated
@RestController
public class MainController {

    private static final Logger logger = LogManager.getLogger(MainController.class);

    @Autowired
    private TriangleIdentificationService triangleIdentificationService;

	@GetMapping("/identification")
	public TriangleIdentification identification(@RequestParam("x") @Positive double x,
												 @RequestParam("y") @Positive double y,
												 @RequestParam("z") @Positive double z)
			throws TriangleDoesNotExistException {
		triangleIdentificationService.validate(x, y, z);
		logger.info("Successfully GetMapping");
		return triangleIdentificationService.identificate(x, y, z);
	}

	@PostMapping("/identification")
	public ResponseEntity<?> calculateBulkParams(@Valid @RequestBody List<Triangle> bodyList) {
		Triangle triangle = new Triangle();
		List<TriangleIdentification> resultList = new LinkedList<>();
		bodyList.forEach((currentElement) -> {
			try {
				resultList.add(triangleIdentificationService.identificate(currentElement.getX(),
						currentElement.getY(), currentElement.getZ()));
				triangle.setSumOfSides(TriangleIdentificationService.calculateSumOfSides(currentElement));
				triangle.setMinSide(TriangleIdentificationService.findMinOfSides(currentElement));
				triangle.setMaxSide(TriangleIdentificationService.findMaxOfSides(currentElement));
			} catch (TriangleDoesNotExistException e) {
				logger.error("Error in postMapping");
			}
		});

		logger.info("Successfully postMapping");

		return new ResponseEntity<>(resultList + "\nSum: " + triangle.getSumOfSides() + "\nMax result: " +
				triangle.getMaxSide() + "\nMin result: " + triangle.getMinSide(), HttpStatus.OK);
	}

    @GetMapping("/cache")
    public TriangleIdentificationHash printTriangleIdentificationHash(){
        return triangleIdentificationService.getTriangleIdentificationHash();
    }
}