package repository;

import domain.Tema;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import validation.ValidationException;
import validation.Validator;

public class TemaFileRepository extends AbstractFileRepository<String, Tema> {

    public TemaFileRepository(Validator<Tema> validator, String filename) {
        super(validator, filename);
        loadFromFile();
    }

    protected void loadFromFile() {
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(filename));
            String line = buffer.readLine();
            while (line != null) {
                String[] result = line.split("#");
                Tema tema = new Tema(result[0], result[1], Integer.parseInt(result[2]), Integer.parseInt(result[3]));
                try {
                    super.save(tema);
                } catch (ValidationException ve) {
                    ve.printStackTrace();
                }
            }
        } catch (
                IOException ioe) {
            ioe.printStackTrace();
        }

    }

    protected void writeToFile(Tema tema) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            bw.write(tema.getID() + "#" + tema.getDescriere() + "#" + tema.getDeadline() + "#" + tema.getStartline()
                    + "\n");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void writeToFileAll() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false));
            for (Tema tema : super.entities.values()) {
                try {
                    bw.write(tema.getID() + "#" + tema.getDescriere() + "#" + tema.getDeadline() + "#" + tema
                            .getStartline() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
