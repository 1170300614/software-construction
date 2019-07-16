package read;

import java.io.IOException;
import java.util.List;

public interface ReadStrategy {

    List<String> readFile2Lines(String fileName) throws IOException;
}