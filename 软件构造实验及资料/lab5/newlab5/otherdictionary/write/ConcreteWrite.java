package write;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import write.WriteStrategy;

public class ConcreteWrite implements WriteStrategy {

    @Override public void writeLines2File(final List<String> lineData,
                                          final String fileName) {
        try {
            FileWriter fileWriter =
                new FileWriter(new File(fileName).getAbsoluteFile());
            for (String line: lineData) {
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
