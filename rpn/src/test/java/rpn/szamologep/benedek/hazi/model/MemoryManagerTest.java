package rpn.szamologep.benedek.hazi.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MemoryManagerTest {

    private static final double DELTA = 1e-9;

    @Test
    void testSaveAndLoadValue(@TempDir Path tempDir) throws IOException, ClassNotFoundException {
        File testFile = tempDir.resolve("test_memory.dat").toFile();
        MemoryManager manager = new MemoryManager(testFile);

        manager.saveToFile(123.456);
        assertTrue(testFile.exists());

        Double loaded = manager.loadFromFile();
        assertEquals(123.456, loaded, DELTA);
    }

    @Test
    void testOverwriteSavedValue(@TempDir Path tempDir) throws IOException, ClassNotFoundException {
        File testFile = tempDir.resolve("overwrite_memory.dat").toFile();
        MemoryManager manager = new MemoryManager(testFile);

        manager.saveToFile(10.0);
        manager.saveToFile(-99.5);

        assertEquals(-99.5, manager.loadFromFile(), DELTA);
    }

    @Test
    void testLoadFromNonExistentFileThrowsException(@TempDir Path tempDir) {
        File missingFile = tempDir.resolve("does_not_exist.dat").toFile();
        MemoryManager manager = new MemoryManager(missingFile);

        assertThrows(IOException.class, manager::loadFromFile);
    }
}
