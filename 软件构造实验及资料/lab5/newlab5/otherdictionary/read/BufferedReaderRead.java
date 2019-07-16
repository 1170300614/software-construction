package read;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;


public class BufferedReaderRead implements ReadStrategy {

    @Override public List<String> readFile2Lines(final String fileName) {
        try {
            return new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName),
                                      StandardCharsets.UTF_8)).lines().collect(
                Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
