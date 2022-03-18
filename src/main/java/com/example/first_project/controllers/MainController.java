package com.example.first_project.controllers;

import com.example.first_project.entities.TriangleIdentification;
import com.example.first_project.entities.ExceptionInfo;
import com.example.first_project.exceptions.TriangleDoesNotExistException;
import com.example.first_project.services.TriangleIdentificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Positive;

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
}