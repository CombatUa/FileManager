package ua.alex.file.manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Manny on 3/23/17.
 */
public class FileManager {
    /**
     * принимает путь к папке, возвращает количество файлов в папке и всех подпапках по пути
     *
     * @param path path to the folder
     * @return number of files, -1 no such folder exists
     */
    public static int calculateFiles(String path) {
        if (path == null && Files.notExists(Paths.get(path))) {
            return -1;
        }
        int accumulator = 0;
        File tmpFile = new File(path);
        File[] tmpListOfFile = tmpFile.listFiles();
        for (int i = 0; i < tmpListOfFile.length; i++) {
            if (tmpListOfFile[i].isDirectory()) {
                accumulator += calculateFiles(tmpListOfFile[i].getPath());
            } else {
                accumulator++;
            }

        }
        return accumulator;

    }


    /**
     * принимает путь к папке, возвращает количество папок в папке и всех подпапках по пути
     *
     * @param path path to the folder
     * @return number of the folders in the folder
     */
    public static int calculateDirs(String path) {
        if (path == null && Files.notExists(Paths.get(path))) {
            return -1;
        }
        int accumulator = 0;
        File tmpFile = new File(path);
        File[] tmpListOfFile = tmpFile.listFiles();
        for (int i = 0; i < tmpListOfFile.length; i++) {
            if (tmpListOfFile[i].isDirectory()) {
                accumulator += calculateDirs(tmpListOfFile[i].getPath());
                accumulator++;
            }

        }
        return accumulator;

    }

    /**
     * метод по копированию папок и файлов.
     *
     * @param from путь к файлу или папке
     * @param to   путь к папке куда будет производиться копирование.
     * @return is successful ?
     */
    public static boolean copy(String from, String to) throws IOException {
        if ((from == null || to == null) && (Files.notExists(Paths.get(from)))) {
            return false;
        }
        Files.copy(Paths.get(from), Paths.get(to));

        return true;

    }

    /**
     * метод по перемещению папок и файлов. Параметр from - , параметр to - .
     *
     * @param from путь к файлу или папке
     * @param to   путь к папке куда будет производиться перемещение
     * @return
     */
    public static boolean move(String from, String to) throws IOException {
        if ((from == null || to == null) && (Files.notExists(Paths.get(from)))) {
            return false;
        }
        Files.move(Paths.get(from), Paths.get(to).resolve(Paths.get(from).getFileName()));

        return true;
    }

    public static boolean delete(Path path) throws IOException {

        Files.list(path).forEach(
                s -> {
                    try {
                        if (s.toFile().isDirectory()) {
                            FileManager.delete(s);
                        } else
                            Files.delete(s);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

        );
        Files.delete(path);

        return true;
    }

    ;

}
