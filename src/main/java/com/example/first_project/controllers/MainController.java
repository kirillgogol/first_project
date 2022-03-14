package com.example.first_project.controllers;

import com.example.first_project.entities.TriangleIdentification;
import com.example.first_project.exceptions.ExceptionInfo;
import com.example.first_project.services.TriangleIdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Positive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.MissingServletRequestParameterException;

@Validated
@RestController
public class MainController {

    private static final Logger logger = LogManager.getLogger(MainController.class);

    @Autowired
    private TriangleIdentificationService triangleIdentificationService;

    @GetMapping("/identification")
    public TriangleIdentification identification(@RequestParam("x") @Positive double x,
                                         @RequestParam("y") @Positive double y,
                                         @RequestParam("z") @Positive double z) {

        logger.info("Successfully GetMapping");
        return triangleIdentificationService.identificate(x, y, z);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handlerException(ConstraintViolationException e) {
        ExceptionInfo error = new ExceptionInfo(e.getMessage(), HttpStatus.BAD_REQUEST.value());

        logger.info("ConstraintViolationException");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handlerBadRequestException(MissingServletRequestParameterException e) {
        ExceptionInfo error = new ExceptionInfo(e.getMessage(), HttpStatus.BAD_REQUEST.value());

        logger.info("MissingServletRequestParameterException");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}