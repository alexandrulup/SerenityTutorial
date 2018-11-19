package com.studentapp.junit.studentsinfo;
import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {

    @BeforeClass
    public static void init(){
 RestAssured.baseURI="http://www.google.com";
    }

    @Test
    public void getAllStudents() {
        RestAssured.given()
                .when()
                .get()
                .then()
                .log()
                .all()
                .statusCode(200);
    }
        @Test
                public void fileDoesNotExist() throws FileNotFoundException {
            File file = new File("D://file.txt");
            FileReader fr = new FileReader(file);
        }

    @Title("This test will bring all the students from the Student App")
    @Test
    public void getAllStuds() {
        RestAssured.given()
                .when()
                .get()
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    public static class StudentsCRUDTest {
    }
}
