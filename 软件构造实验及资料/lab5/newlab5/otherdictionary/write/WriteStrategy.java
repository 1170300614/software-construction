package write;

import java.io.IOException;
import java.util.List;

public interface WriteStrategy {
    void writeLines2File(List<String> lineData, String fileName)
    throws IOException;
}
