import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import read.ConcreteRead;
import read.Read;
import read.BufferedReaderRead;
import read.FileInputStreamRead;

public class Readtest {
    /** File name 1. */
    private static final String FILE_NAME_1 =
        "src/testtxtfiles/StellarSystem_Huge.txt";
    /** File name 2. */
    private static final String FILE_NAME_2 =
        "src/testtxtfiles/PersonalAppEcosystem_Huge.txt";
    /**
     * ReadFileStrategyTest instance.
     *
     * @throws FileNotFoundException if file is not found
     */
    public Readtest() throws FileNotFoundException {
    }
    /** Standard data 1. */
    private final List<String> standard1 = new BufferedReader(
        new InputStreamReader(new FileInputStream(FILE_NAME_1),
                              StandardCharsets.UTF_8)).lines().collect(
        Collectors.toList());
    /** Standard data 2. */
    private final List<String> standard2 = new BufferedReader(
        new InputStreamReader(new FileInputStream(FILE_NAME_2),
                              StandardCharsets.UTF_8)).lines().collect(
        Collectors.toList());
    /**
     * FileInputStreamRead Tester.
     *
     * @throws IOException IOException
     */
    @Test public void testFileInputStream() throws IOException {
        long startTime = System.nanoTime();
        List<String> list =
            new Read(new FileInputStreamRead()).readFile2Line(FILE_NAME_1);
        long endTime = System.nanoTime();
        assertEquals("Equals", standard1, list);
        System.out.println(list.size());
        System.out.println("FileInputStreamRead " + FILE_NAME_1 + " cost: " +
                           (endTime - startTime) + " ns");
        startTime = System.nanoTime();
        list =
            new Read(new FileInputStreamRead()).readFile2Line(FILE_NAME_2);
        endTime = System.nanoTime();
        assertEquals("Equals", standard2.size() - 2, list.size());
        System.out.println(list.size());
        System.out.println("FileInputStreamRead " + FILE_NAME_2 + " cost: " +
                           (endTime - startTime) + " ns");
    }
    /**
     * FileReaderRead Tester.
     *
     * @throws IOException IOException
     */
    @Test public void testFileReaderRead() throws IOException {
        long startTime = System.nanoTime();
        List<String> list =
            new Read(new ConcreteRead()).readFile2Line(FILE_NAME_1);
        long endTime = System.nanoTime();
        assertEquals("Equals", standard1, list);
        System.out.println(list.size());
        System.out.println("FileReaderRead " + FILE_NAME_1 + " cost: " +
                           (endTime - startTime) + " ns");
        startTime = System.nanoTime();
        list = new Read(new ConcreteRead()).readFile2Line(FILE_NAME_2);
        endTime = System.nanoTime();
        assertEquals("Equals", standard2.size() - 2, list.size());
        System.out.println(list.size());
        System.out.println("FileReaderRead " + FILE_NAME_2 + " cost: " +
                           (endTime - startTime) + " ns");
    }
    /**
     * BufferedReader Tester.
     *
     * @throws IOException IOException
     */
    @Test public void testConcreteRead() throws IOException {
        long startTime = System.nanoTime();
        List<String> list =
            new Read(new BufferedReaderRead()).readFile2Line(FILE_NAME_1);
        long endTime = System.nanoTime();
        assertEquals("Equals", standard1, list);
        System.out.println(list.size());
        System.out.println("BufferedReaderRead " + FILE_NAME_1 + " cost: " +
                           (endTime - startTime) + " ns");
        startTime = System.nanoTime();
        list =
            new Read(new BufferedReaderRead()).readFile2Line(FILE_NAME_2);
        endTime = System.nanoTime();
        assertEquals("Equals", standard2, list);
        System.out.println(list.size());
        System.out.println("BufferedReaderRead " + FILE_NAME_2 + " cost: " +
                           (endTime - startTime) + " ns");
    }
}
