package serviceTests;

import static org.junit.Assert.assertEquals;

import domain.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import service.Service;
import validation.ValidationException;

public class AddStudentTests {

    private Service service;

    @Before
    public void setUp() {
        this.service = Mockito.mock(Service.class);
    }

    @Test
    public void testAddStudentOK() {
        assertEquals(0, this.service.saveStudent("1", "Ionut", 933));
        assertEquals(0, this.service.saveStudent("2", "Dragos", 933));
    }

    @Test
    public void testAddStudentGroupNotOK() {
        try {
            this.service.saveStudent("3", "Student1", 12134);
        } catch (ValidationException e) {
            Assert.fail();
        }

        try {
            this.service.saveStudent("3", "Student1", 1);
        } catch (ValidationException e) {
            Assert.fail();
        }
    }

    @Test
    public void testAddStudentIdNotOK() {
        try {
            this.service.saveStudent(null, "Student1", 933);
        } catch (ValidationException e) {
            Assert.fail();
        }
        try {
            this.service.saveStudent("", "Student1", 933);
        } catch (ValidationException e) {
            Assert.fail();
        }
    }

    @Test
    public void testAddStudentNameNotOK() {
        try {
            this.service.saveStudent("1", null, 933);
        } catch (ValidationException e) {
            Assert.fail();
        }
        try {
            this.service.saveStudent("1", "", 933);
        } catch (ValidationException e) {
            Assert.fail();
        }
    }
}