package write;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class FileOutputStreamWrite implements WriteStrategy {

    public void writeLines2File(final List<String> lineData,
                                final String fileName) {
        try {
            FileOutputStream out = new FileOutputStream(new File(fileName));
            for (String line: lineData) {
                String s = line + "\n";
                out.write(s.getBytes());
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
