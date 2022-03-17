package com.example.first_project.controller;

import com.example.first_project.controllers.MainController;
import com.example.first_project.entities.TriangleIdentification;
import org.junit.Test;
import javax.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.MissingServletRequestParameterException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MainControllerTest {

    @Autowired
    private MainController mainController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test//(expected = MissingServletRequestParameterException.class)
    public void missingParam() throws MissingServletRequestParameterException{
        String actual = testRestTemplate.getForObject("http://localhost:8080/identification?x=3&y=6", String.class);
        String excepted = "{\"message\":\"Required request parameter 'z' for method parameter type double is not present\",\"code\":400}";
        //System.out.println(actual);
        assertEquals(excepted, actual);
    }

    @Test
    public void positiveValue() {
        TriangleIdentification actual = mainController.identification(3, 5, 3);
        boolean[] actualArr = new boolean[] {actual.getIsEquilateral(), actual.getIsIsosceles(), actual.getIsRectangular()};
        boolean[] exceptedArr = new boolean[] {false, true, false};
        assertArrayEquals(exceptedArr, actualArr);
    }

    @Test(expected = ConstraintViolationException.class)
    public void negativeParam() throws ConstraintViolationException{
        TriangleIdentification actual = mainController.identification(3, 5, -1);
    }

}
