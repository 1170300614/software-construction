import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;

import org.junit.Test;

import core.application.AtomStructure;
import core.physicalObject.ElectronicObject;

public class AtomStructureTest {
    /** A. */
    private static final int A = 3;
    /** A. */
    private static final int B = 4;
    /** A. */
    private static final int C = 5;
    /** A. */
    private static final int D = 6;
    /** A. */
    private static final int E = 37;
    /** A. */
    private static final int F = 68;
    /**
     * A.
     *
     * @throws Exception E
     */
    @Test public void testBuildTracksFromFile() throws Exception {
        AtomStructure atomStructure =
                new AtomStructure().buildAtomStructureFromFile(
                        "src/testtxtfiles/AtomicStructure_Medium.txt");
        assertEquals("the core's name should be \"Rb\"", "Er",
                     atomStructure.getCenter().getName());
        assertEquals("the number of core.track should be 6", D,
                     atomStructure.geTracks().size());
        assertEquals("the number of electronic should be 68", F,
                     atomStructure.getPhysicalObjects().size());
    }
    /**
     * A.
     *
     * @throws Exception E
     */
    @Test public void testTransit() throws Exception {
        AtomStructure atomStructure =
                new AtomStructure().buildAtomStructureFromFile(
                        "src/testtxtfiles/AtomicStructure_Medium.txt");
        int a = atomStructure.getPhysicalMap().get(
                atomStructure.geTracks().get(A)).size();
        int b = atomStructure.getPhysicalMap().get(
                atomStructure.geTracks().get(B)).size();
        atomStructure.transit(B, C);
        assertEquals("the 3th core.track's object number should be", a - 1,
                     atomStructure.getPhysicalMap().get(
                             atomStructure.geTracks().get(A)).size());
        assertEquals("the 3th core.track's object number should be", b + 1,
                     atomStructure.getPhysicalMap().get(
                             atomStructure.geTracks().get(B)).size());
    }
    /**
     * B.
     *
     * @throws Exception D
     */
    @Test public void testAddOnTrack() throws Exception {
        AtomStructure atomStructure =
                new AtomStructure().buildAtomStructureFromFile(
                        "src/testtxtfiles/AtomicStructure_Medium.txt");
        int a = atomStructure.getPhysicalMap().get(
                atomStructure.geTracks().get(A)).size();
        atomStructure.addOnTrack(atomStructure.geTracks().get(A),
                                 new ElectronicObject());
        assertEquals("the 3th core.track's object number should be", a + 1,
                     atomStructure.getPhysicalMap().get(
                             atomStructure.geTracks().get(A)).size());
    }
    /**
     * C.
     *
     * @throws FileNotFoundException A
     */
    // 文件给出有误
    @Test public void testFileFormatException() throws FileNotFoundException {
        AtomStructure atomStructure1 =
                new AtomStructure().buildAtomStructureFromFile(
                        "src/testtxtfiles" +
                                "/AtomicStructure_FileFormatException_1.txt");
        assertNull("Illegal label format", atomStructure1);
        AtomStructure atomStructure2 =
                new AtomStructure().buildAtomStructureFromFile(
                        "src/testtxtfiles" +
                                "/AtomicStructure_FileFormatException_2.txt");
        assertNull("Illegal label format", atomStructure2);
        AtomStructure atomStructure3 =
                new AtomStructure().buildAtomStructureFromFile(
                        "src/testtxtfiles" +
                                "/AtomicStructure_FileFormatException_3.txt");
        assertNull("Illegal label format", atomStructure3);
        AtomStructure atomStructure4 =
                new AtomStructure().buildAtomStructureFromFile(
                        "src/testtxtfiles" +
                                "/AtomicStructure_FileFormatException_4.txt");
        assertNull("Illegal label format", atomStructure4);
        AtomStructure atomStructure5 =
                new AtomStructure().buildAtomStructureFromFile(
                        "src/testtxtfiles" +
                                "/AtomicStructure_FileFormatException_5.txt");
        assertNull("Illegal label format", atomStructure5);
        AtomStructure atomStructure6 =
                new AtomStructure().buildAtomStructureFromFile(
                        "src/testtxtfiles" +
                                "/AtomicStructure_FileFormatException_6.txt");
        assertNull("Illegal label format", atomStructure6);
    }
    /**A.
     * @throws FileNotFoundException A
     */
    @Test public void test7() throws FileNotFoundException {
        AtomStructure atomStructure =
                new AtomStructure().buildAtomStructureFromFile(
                        "src/testtxtfiles/AtomicStructure_Non_standard.txt");
        assertEquals("The core's name should be \"Rb\"", "Rb",
                     atomStructure.getCenter().getName());
        assertEquals("The number of core.track should be 5", C,
                     atomStructure.geTracks().size());
        assertEquals("The number of electronic should be 37", E,
                     atomStructure.getPhysicalObjects().size());
    }
}
