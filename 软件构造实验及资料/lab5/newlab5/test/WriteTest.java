import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import write.ConcreteWrite;
import write.Write;
import write.BufferedWriterWrite;
import write.FileOutputStreamWrite;

public class WriteTest {
    /** File name 1. */
    private static final String FILE_NAME1 =
        "src/testtxtfiles/StellarSystem_Huge.txt";
    /** File name 2. */
    private static final String FILE_NAME2 =
        "src/testtxtfiles/PersonalAppEcosystem_Huge.txt";
    /** Target 1. */
    private static final String TARGET1 = "test/output/StellarSystem.txt";
    /** Target 2. */
    private static final String TARGET2 =
        "test/output/PersonalAppEcosystem.txt";
    /**
     * ReadFileStrategyTest instance.
     *
     * @throws FileNotFoundException if file is not found
     */
    public WriteTest() throws FileNotFoundException {
    }
    /** Standard data 1. */
    private final List<String> standard1 = new BufferedReader(
        new InputStreamReader(new FileInputStream(FILE_NAME1),
                              StandardCharsets.UTF_8)).lines().collect(
        Collectors.toList());
    /** Standard data 2. */
    private final List<String> standard2 = new BufferedReader(
        new InputStreamReader(new FileInputStream(FILE_NAME2),
                              StandardCharsets.UTF_8)).lines().collect(
        Collectors.toList());
    /**
     * FileOutputStreamWrite Tester.
     *
     * @throws IOException IOException
     */
    @Test public void testOutputStream() throws IOException {
        long startTime = System.nanoTime();
        new Write(new FileOutputStreamWrite())
            .writeLines2File(standard1, TARGET1);
        long endTime = System.nanoTime();
        System.out.println(standard1.size());
        System.out.println("FileOutputStreamWrite " + TARGET1 + " cost: " +
                           (endTime - startTime) + " ns");
        startTime = System.nanoTime();
        new Write(new FileOutputStreamWrite())
            .writeLines2File(standard2, TARGET2);
        endTime = System.nanoTime();
        System.out.println(standard2.size());
        System.out.println("FileOutputStreamWrite " + TARGET2 + " cost: " +
                           (endTime - startTime) + " ns");
    }
    /**
     * FileWriterWrite Tester.
     *
     * @throws IOException IOException
     */
    @Test public void testConcreteWrite() throws IOException {
        long startTime = System.nanoTime();
        new Write(new ConcreteWrite())
            .writeLines2File(standard1, TARGET1);
        long endTime = System.nanoTime();
        System.out.println(standard1.size());
        System.out.println(
            "FileWriterWrite " + TARGET1 + " cost: " + (endTime - startTime) +
            " ns");
        startTime = System.nanoTime();
        new Write(new ConcreteWrite())
            .writeLines2File(standard2, TARGET2);
        endTime = System.nanoTime();
        System.out.println(standard2.size());
        System.out.println(
            "FileWriterWrite " + TARGET2 + " cost: " + (endTime - startTime) +
            " ns");
    }
    /**
     * BufferedWriterWrite Tester.
     *
     * @throws IOException IOException
     */
    @Test public void testBuffered() throws IOException {
        long startTime = System.nanoTime();
        new Write(new BufferedWriterWrite())
            .writeLines2File(standard1, TARGET1);
        long endTime = System.nanoTime();
        System.out.println(standard1.size());
        System.out.println("BufferedWriterWrite " + TARGET1 + " cost: " +
                           (endTime - startTime) + " ns");
        startTime = System.nanoTime();
        new Write(new BufferedWriterWrite())
            .writeLines2File(standard2, TARGET2);
        endTime = System.nanoTime();
        System.out.println(standard2.size());
        System.out.println("BufferedWriterWrite " + TARGET2 + " cost: " +
                           (endTime - startTime) + " ns");
    }
}
