package serviceTests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import service.Service;

import static org.junit.Assert.assertEquals;

public class IntegrationTests {

    private Service service;

    @Before
    public void setUp() {
        this.service = Mockito.mock(Service.class);
    }

    @Test
    public void testAddStudentOK() {
        assertEquals(0, this.service.saveStudent("5", "Ionut", 933));
    }

    @Test
    public void testAddAssignmentOK() {
        assertEquals(0, this.service.saveTema("5", "Ionut", 1, 3));
    }

    @Test
    public void testAddGradeOK() {
        assertEquals(0, this.service.saveNota("5", "5", 10, 3, "done"));
    }

    @Test
    public void testIntegrationOK() {
        this.testAddStudentOK();
        this.testAddAssignmentOK();
        this.testAddGradeOK();
    }

    @Test
    public void testAddStudentTopDown() {
        assertEquals(0, this.service.saveStudent("6", "Dragos", 933));
    }

    @Test
    public void testAddAssignmentTopDown() {
        this.testAddStudentTopDown();
        assertEquals(0, this.service.saveTema("6", "Dragos", 1, 3));
    }

    @Test
    public void testAddGradeTopDown() {
        this.testAddStudentTopDown();
        this.testAddAssignmentTopDown();
        assertEquals(0, this.service.saveNota("6", "6", 10, 3, "done"));

    }
}
