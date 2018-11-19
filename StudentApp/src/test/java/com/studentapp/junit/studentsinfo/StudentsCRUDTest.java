package com.studentapp.junit.studentsinfo;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.model.StudentClass;
//import com.studentapp.utils.TestUtils;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.http.ContentType;
import io.vavr.collection.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.*;
import javax.swing.plaf.synth.SynthScrollBarUI;
import java.util.ArrayList;
import java.util.HashMap;


@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest extends TestBase {

        static String firstName = TestUtils.getRandomValue()+"Gigi";
        static String lastName = TestUtils.getRandomValue()+"Popescu";
        static String programme="ComputerScience";
        static String email = TestUtils.getRandomValue()+"test2@gmail.com";
        static int studentId;

    @Steps
    StudentSerenitySteps steps;

    @Title("This test will create a new student")
    @Test
    public void test001() {

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("JAVA");
        courses.add("C++");

             steps.createStudent(firstName, lastName, email, programme, courses)
            .statusCode(201);

    }
        @Title("Verify if student is added to the application")
        @Test

        public void test002(){

            HashMap<String, Object> value=steps.getStudentInfoByFirstName(firstName);
            System.out.println("The value is: "+value);

            assertThat(value,hasValue(firstName));
            studentId= (int) value.get("id");
    }

    @Title("Update the user information and verify the updated information")
    @Test

    public void test003() {
        ArrayList<String> courses = new ArrayList<String>();
        courses.add("JAVA");
        courses.add("C++");

        firstName = firstName + "_Updated";
        steps.updateStudent(studentId, firstName, lastName, email, programme, courses);
        HashMap<String, Object> value =steps.getStudentInfoByFirstName(firstName);
        System.out.println("The value is: " +value);
//        assertEquals(firstName, value.get("firstName"));
        assertThat(value, hasValue(firstName));
    }
        @Title("Delete the student and verify if the student is deleted")
        @Test

        public void test004(){
steps.deleteStudent(studentId);
steps.getStudentById(studentId).statusCode(404);

        }
    }





