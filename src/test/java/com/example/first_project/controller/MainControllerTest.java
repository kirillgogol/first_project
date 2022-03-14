package com.example.first_project.controller;

import com.example.first_project.controllers.MainController;
import com.example.first_project.entities.TriangleIdentification;
import org.junit.Test;
import javax.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MainControllerTest {

    @Test
    public void positiveValue() {
        MainController mainController = new MainController();
        TriangleIdentification actual = mainController.identification(3, 5, 3);
        boolean[] actualArr = new boolean[] {actual.getIsEquilateral(), actual.getIsIsosceles(), actual.getIsRectangular()};
        boolean[] exceptedArr = new boolean[] {false, true, false};
        assertArrayEquals(exceptedArr, actualArr);
    }

    @Test(expected = ConstraintViolationException.class)
    public void negativeParam() throws ConstraintViolationException{
        MainController mainController = new MainController();
        TriangleIdentification actual = mainController.identification(3, 5, -1);
        //assertThrows(ConstraintViolationException.class, ()->mainController.identification(3, 5, -1));
    }

}
