package com.example.first_project.controllers;

import com.example.first_project.entities.TriangleIdentification;
import com.example.first_project.services.TriangleIdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private TriangleIdentificationService triangleIdentificationService;

    @GetMapping("/identification")
    public TriangleIdentification identification(@RequestParam("x") double x,
                                                 @RequestParam("y") double y,
                                                 @RequestParam("z") double z) {
        return triangleIdentificationService.identificate(x, y, z);
    }

}