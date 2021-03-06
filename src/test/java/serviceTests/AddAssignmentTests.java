package serviceTests;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import service.Service;
import validation.ValidationException;

public class AddAssignmentTests {

    private Service service;

    @Before
    public void setUp() {
        this.service = Mockito.mock(Service.class);
    }

    @Test
    public void testAddAssignmentOK() {
        assertEquals(0, this.service.saveTema("1", "Ionut", 1, 3));
        assertEquals(0, this.service.saveTema("2", "Dragos", 1, 3));
    }

    @Test
    public void testAddIdNotOK() {
        try {
            this.service.saveTema(null, "Student1", 1, 3);
        } catch (ValidationException e) {
            Assert.fail();
        }
        try {
            this.service.saveTema("", "Student1", 1, 3);
        } catch (ValidationException e) {
            Assert.fail();
        }
    }

    @Test
    public void testAddDescriptionNotOK() {
        try {
            this.service.saveTema("1", null, 1, 3);
        } catch (ValidationException e) {
            Assert.fail();
        }
        try {
            this.service.saveTema("1", "", 1, 3);
        } catch (ValidationException e) {
            Assert.fail();
        }
    }

    @Test
    public void testAddDeadlineNotOK() {
        try {
            this.service.saveTema("1", "Student1", -1, 3);
        } catch (ValidationException e) {
            Assert.fail();
        }
        try {
            this.service.saveTema("", "Student1", 15, 3);
        } catch (ValidationException e) {
            Assert.fail();
        }
    }

    @Test
    public void testAddStartlineNotOK() {
        try {
            this.service.saveTema(null, "Student1", 1, -1);
        } catch (ValidationException e) {
            Assert.fail();
        }
        try {
            this.service.saveTema("", "Student1", 1, 14);
        } catch (ValidationException e) {
            Assert.fail();
        }
    }

}
