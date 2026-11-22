package rpn.szamologep.benedek.hazi.model;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class MemoryManager {
    private final File file; 

    public MemoryManager(File file) {
        this.file = file;
    }

    public MemoryManager() {
        this.file = new File("memory.dat");
    }

    public void saveToFile(Double data)  throws IOException {
        try(FileOutputStream fos = new FileOutputStream(file)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
        } catch (IOException e) {
            throw new IOException("Error saving data to file: " + e.getMessage());
        }
    }

    public Double loadFromFile() throws IOException, ClassNotFoundException {
        try(FileInputStream fis = new FileInputStream(file)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            Double data = (Double) ois.readObject();
            ois.close();
            return data;
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Error loading data from file: " + e.getMessage());
        }
    }

}
