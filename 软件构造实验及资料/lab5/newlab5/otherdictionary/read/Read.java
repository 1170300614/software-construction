package read;

import java.io.IOException;
import java.util.List;

import read.ReadStrategy;

public class Read {
    /** Strategy class. */
    private ReadStrategy readFileStrategy;
   
    public Read(final ReadStrategy newReadFileStrategy) {
        this.readFileStrategy = newReadFileStrategy;
    }
    public List<String> readFile2Line(final String fileName)
    throws IOException {
        return readFileStrategy.readFile2Lines(fileName);
    }
}
