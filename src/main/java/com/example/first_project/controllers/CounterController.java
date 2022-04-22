package com.example.first_project.controllers;

import com.example.first_project.counter.RequestCounter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {
    Logger logger = LogManager.getLogger(CounterController.class);

    @GetMapping(value = "/counter")
    public String getCounter() {
        logger.info("Visited RequestCounterController");
        return RequestCounter.getCounter() + " запросов было выполнено";
    }
}
