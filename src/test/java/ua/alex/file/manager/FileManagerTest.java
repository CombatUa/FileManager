package ua.alex.file.manager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Manny on 3/23/17.
 */


class FileManagerTest {
    static final String DEFAULT_SEPAPRATOR = FileSystems.getDefault().getSeparator();

    @BeforeAll
    static void setUp() throws IOException {

        Path rootFolder = Paths.get("to_calc");
        Files.createDirectory(rootFolder);
        Files.createFile(Paths.get(rootFolder.toString() + DEFAULT_SEPAPRATOR + "file1.txt"));
        Files.createFile(Paths.get(rootFolder.toString() + DEFAULT_SEPAPRATOR + "file2.txt"));
        Path subFolder2 = Files.createDirectory(Paths.get(rootFolder.toString() + DEFAULT_SEPAPRATOR + "subFolder2"));
        Path subFolder3 = Files.createDirectory(Paths.get(subFolder2.toString() + DEFAULT_SEPAPRATOR + "subFolder3"));
        Path subFolder3_1 = Files.createDirectory(Paths.get(subFolder3.toString() + DEFAULT_SEPAPRATOR + "subFolder3_1"));
        Files.createFile(Paths.get(subFolder3.toString() + DEFAULT_SEPAPRATOR + "file3.txt"));
        Files.createFile(Paths.get(subFolder3_1.toString() + DEFAULT_SEPAPRATOR + "file3_1.txt"));
    }

    @AfterAll
    static void tearDown() throws IOException {

        FileManager.delete(Paths.get("to_calc"));
    }

    @Test
    void calculateFiles() {
        assertEquals(2, FileManager.calculateFiles("to_calc" + DEFAULT_SEPAPRATOR + "subFolder2"));
    }

    @Test
    void calculateDirs() {
        assertEquals(3, FileManager.calculateDirs("to_calc"));
    }


    @Test
    void copy() throws IOException {
        FileManager.copy("to_calc" + DEFAULT_SEPAPRATOR + "file1.txt", "to_calc" + DEFAULT_SEPAPRATOR + "file3.txt");

        assertTrue(Files.exists(Paths.get("to_calc" + DEFAULT_SEPAPRATOR + "file3.txt")));
    }

    @Test
    void move() throws IOException {
        FileManager.move("to_calc" + DEFAULT_SEPAPRATOR + "file1.txt", "to_calc" + DEFAULT_SEPAPRATOR + "subFolder2");

        assertTrue(Files.exists(Paths.get("to_calc" + DEFAULT_SEPAPRATOR + "subFolder2" + DEFAULT_SEPAPRATOR + "file1.txt")));

    }

    @Test
    void math() {
        int r = 23;
        System.out.println(~r + 1);
    }

}