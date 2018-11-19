package com.studentapp.cucumber.serenity;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.ReusableSpecifications;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class StudentSerenitySteps {

    @Step("Creating student with firstName:{0}, lastName:{1}, email:{2}, programme:{3}, courses:{4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courses) {

        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses((ArrayList<String>) courses);

        return SerenityRest.rest().given()
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .when()
                .body(student)
                .log()
                .all()
                .post()

                .then()
                .log()
                .all();
    }

    @Step

    public HashMap<String, Object> getStudentInfoByFirstName(String firstName) {
        java.lang.String p1 = "findAll{it.firstName=='";
        java.lang.String p2 = "'}.get(0)";

        return SerenityRest.rest().given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .path(p1 + firstName + p2);
    }

    @Step("Updating student with firstName:{0}, lastName:{1}, email:{2}, programme:{3}, courses:{4}")
    public ValidatableResponse updateStudent(int studentId, String firstName, String lastName, String email, String programme, ArrayList<String> courses) {

        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .log()
                .all()
                .when()
                .body(student)
                .put("/" +studentId)
                .then();
    }

    @Step("Deleting student information with ID:{0}")
            public void deleteStudent(int studentId){
        SerenityRest
                .rest()
                .given()
                .when()
                .delete("/" +studentId);
    }
    @Step("Getting information about the student with ID: {0}")
     public ValidatableResponse getStudentById(int studentId){
        return
                SerenityRest
                        .rest()
                        .given()
                        .when()
                        .get("/" + studentId).then()
                        .log()
                        .all();

    }

}
