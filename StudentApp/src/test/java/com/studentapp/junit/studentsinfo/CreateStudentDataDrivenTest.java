package com.studentapp.junit.studentsinfo;
import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static com.studentapp.junit.studentsinfo.StudentsCRUDTest.email;
import static com.studentapp.junit.studentsinfo.StudentsCRUDTest.programme;

@Concurrent(threads = "4x")
@UseTestDataFrom("testdata/studentinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentDataDrivenTest extends TestBase {

    @Steps
    StudentSerenitySteps steps;

    private String firstName;
    private String lastName;
    private String email;
    private String programme;
    private String courses;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgramme() {
        return programme;
    }
    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getCourses() { return courses; }
    public void setCourses(String courses) { this.courses = courses; }

    public StudentSerenitySteps getSteps() { return steps; }
    public void setSteps(StudentSerenitySteps steps) { this.steps = steps; }


    @Title("DataDriven test for adding multiple students to the Student App")
    @Test
    public void createMultipleStudents(){
        ArrayList<String> course = new ArrayList<>();
        course.add(courses);
        steps.createStudent(firstName, lastName, email, programme, course)
        .statusCode(201);
    }
}
