package com.example.first_project.controller;

import com.example.first_project.controllers.MainController;
import com.example.first_project.entities.TriangleIdentification;
import com.example.first_project.exceptions.TriangleDoesNotExistException;
import org.junit.Test;
import javax.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MainControllerTest {

    @Autowired
    private MainController mainController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void missingParam() {
        String actual = testRestTemplate.getForObject("http://localhost:8080/identification?x=3&y=6", String.class);
        String excepted = "{\"message\":\"Required request parameter 'z' for method parameter type double is not present\",\"code\":400}";
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
    public void negativeParam() {
        TriangleIdentification actual = mainController.identification(3, 5, -1);
    }

	@Test(expected = TriangleDoesNotExistException.class)
	public void longTriangleSide_firstApproach() throws TriangleDoesNotExistException{
		mainController.identification(3, 5, 115);
	}

	@Test
	public void longTriangleSide_secondApproach() {
		String actual = testRestTemplate.getForObject("http://localhost:8080/identification?x=3&y=6&z=100", String.class);
		String excepted = "{\"message\":\"Triangle does not exist\",\"code\":500}";
		assertEquals(excepted, actual);
	}
}
