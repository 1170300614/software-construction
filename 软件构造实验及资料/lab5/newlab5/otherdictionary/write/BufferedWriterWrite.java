package write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BufferedWriterWrite implements WriteStrategy {

    public void writeLines2File(final List<String> lineData,
                                final String fileName) {
        try {
            FileWriter fileWriter =
                new FileWriter(new File(fileName).getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String line: lineData) {
                bufferedWriter.write(line + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
