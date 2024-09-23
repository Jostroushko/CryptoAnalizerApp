package org.example;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validator {
    public boolean isValidKey(int key, char[] alphabet) {
        // Проверка ключа
        return key >= 0 && key < alphabet.length;
    }
    public boolean isFileExists(String filePath) {
        // Проверка существования файла
        Path path = Paths.get(filePath);
        return Files.exists(path) && Files.isRegularFile(path);
    }
}