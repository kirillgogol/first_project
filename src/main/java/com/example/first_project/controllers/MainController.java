package com.example.first_project.controllers;

import com.example.first_project.entities.JsonIdentification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/identification")
    public JsonIdentification identification(@RequestParam("x") double x,
                                             @RequestParam("y") double y,
                                             @RequestParam("z") double z) {

        boolean equilateral = false, isosceles = false, right = false;

        if(x == y && x == z){
            equilateral = true;
        }

        if((x == y && x != z) || (x == z && x != y) || (y == z && y != x)){
            isosceles = true;
        }

        double minNum1 = Math.min(x, y);
        double maxNum = Math.max(x, y);
        double minNum2 = Math.min(maxNum, z);
        maxNum = Math.max(maxNum, z);

        if(Math.pow(maxNum, 2) == Math.pow(minNum1, 2) + Math.pow(minNum2, 2)){
            right = true;
        }

        return new JsonIdentification(equilateral, isosceles, right);
    }

}

