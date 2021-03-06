package ua.alex.file.manager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Manny on 3/23/17.
 */


class FileManagerTest {
    static final String DEFAULT_SEPAPRATOR = FileSystems.getDefault().getSeparator();

    @BeforeAll
    static void setUp() throws IOException {

        Path rootFolder = Paths.get("to_calc" + DEFAULT_SEPAPRATOR + "subFolder2" + DEFAULT_SEPAPRATOR + "subFolder3" + DEFAULT_SEPAPRATOR + "subFolder3_1");
        Files.createDirectories(rootFolder);
        //Files.createDirectory(rootFolder);
        Files.createFile(Paths.get("to_calc" + DEFAULT_SEPAPRATOR + "file1.txt"));
        Files.createFile(Paths.get("to_calc" + DEFAULT_SEPAPRATOR + "file2.txt"));
//        Path subFolder2 = Paths.get(rootFolder.toString() + DEFAULT_SEPAPRATOR + "subFolder2");
//        Path subFolder3 = Paths.get(subFolder2.toString() + DEFAULT_SEPAPRATOR + "subFolder3");
//        Path subFolder3_1 = Paths.get(subFolder3.toString() + DEFAULT_SEPAPRATOR + "subFolder3_1");
        Files.createFile(Paths.get("to_calc" + DEFAULT_SEPAPRATOR + "subFolder2" + DEFAULT_SEPAPRATOR + "subFolder3" + DEFAULT_SEPAPRATOR + "file3.txt"));
        Files.createFile(Paths.get("to_calc" + DEFAULT_SEPAPRATOR + "subFolder2" + DEFAULT_SEPAPRATOR + "subFolder3" + DEFAULT_SEPAPRATOR + "subFolder3_1" + DEFAULT_SEPAPRATOR + "file3_1.txt"));
        System.out.println("Finish Before All");
    }

    @AfterAll
    static void tearDown() throws IOException {

        FileManager.delete(Paths.get("to_calc").toAbsolutePath());
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
        FileManager.move("to_calc" + DEFAULT_SEPAPRATOR + "file1.txt", "to_calc" + DEFAULT_SEPAPRATOR + "subFolder2" + DEFAULT_SEPAPRATOR + "subFolder3" + DEFAULT_SEPAPRATOR + "file3_2.txt");

        assertTrue(Files.exists(Paths.get("to_calc" + DEFAULT_SEPAPRATOR + "subFolder2" + DEFAULT_SEPAPRATOR + "subFolder3" + DEFAULT_SEPAPRATOR + "file3_2.txt")));
        assertFalse(Files.exists(Paths.get("to_calc" + DEFAULT_SEPAPRATOR + "file1.txt")));

    }

    @Test
    void testStreams() throws IOException {
        File file = new File("/Users/Manny/Downloads/test.bmp");
        InputStream inputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        System.out.println(bufferedInputStream.read());
        //inputStream.close();
        System.out.println(bufferedInputStream.read());
        bufferedInputStream.close();
        System.out.println(inputStream.read());

    }

    ;


}