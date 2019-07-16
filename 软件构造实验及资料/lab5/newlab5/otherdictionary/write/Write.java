package write;

import java.io.IOException;
import java.util.List;

import write.WriteStrategy;

public class Write {
    private WriteStrategy readFileStrategy;

    public Write(final WriteStrategy newWriteFileStrategy) {
        this.readFileStrategy = newWriteFileStrategy;
    }

    public void writeLines2File(final List<String> lineData,
                                final String fileName) throws IOException {
        readFileStrategy.writeLines2File(lineData, fileName);
    }
}
