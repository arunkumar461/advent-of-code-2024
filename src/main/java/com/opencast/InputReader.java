package com.opencast;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InputReader {
    public static List<String> readInput(String path) {
        try {
            var filePath = Paths.get(
                    InputReader.class.getClassLoader().getResource(path)
                            .toURI());
            return Files.readAllLines(filePath);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
