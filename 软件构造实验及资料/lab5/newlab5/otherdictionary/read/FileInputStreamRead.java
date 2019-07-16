package read;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileInputStreamRead implements ReadStrategy {
    /** Rank. */
    private static final int RANK = 26;
    /** Max length. */
    private static final int LENGTH = 1 << RANK;

    @Override public List<String> readFile2Lines(final String fileName) {
        try {
            List<String> list = new ArrayList<>();
            InputStream in = new FileInputStream(new File(fileName));
            byte[] bytes = new byte[LENGTH];
            int flag = in.read(bytes, 0, bytes.length);
            if (flag != -1) {
                String s = new String(bytes, 0, flag, StandardCharsets.UTF_8);
                list.addAll(Arrays.asList(s.split("\n")));
            }
            in.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
