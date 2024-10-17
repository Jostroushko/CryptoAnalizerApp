package org.example;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class FileManager {
    static final Validator validator = new Validator();
    public String readFile(String filePath) throws IOException {
        if (!validator.isFileExists(filePath)) {
            throw new FileNotFoundException(filePath);
        }
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public void writeFile(String content, String filePath) throws IOException {
        if (!validator.isFileExists(filePath)) {
            throw new FileNotFoundException(filePath);
        }
        Path path = Paths.get(filePath);
        Files.writeString(path, content);
    }
}