package repository;

import domain.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import validation.ValidationException;
import validation.Validator;

public class StudentFileRepository extends AbstractFileRepository<String, Student> {

    public StudentFileRepository(Validator<Student> validator, String filename) {
        super(validator, filename);
        loadFromFile();
    }

    protected void loadFromFile() {
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(filename));
            String line = buffer.readLine();
            while (line != null) {
                String[] result = line.split("#");
                Student student = new Student(result[0], result[1], Integer.parseInt(result[2]));
                try {
                    super.save(student);
                } catch (ValidationException ve) {
                    ve.printStackTrace();
                }
                line = buffer.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void writeToFile(Student student) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            bw.write(student.getID() + "#" + student.getNume() + "#" + student.getGrupa() + "\n");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void writeToFileAll() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false));
            for (Student student : super.entities.values()) {
                try {
                    bw.write(student.getID() + "#" + student.getNume() + "#" + student.getGrupa() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
