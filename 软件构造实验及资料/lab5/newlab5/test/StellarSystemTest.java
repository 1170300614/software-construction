import core.application.StellarSystem;
import core.exception.FileException;
import core.exception.TrackException;

import org.junit.Test;

import core.physicalObject.Planet;
import core.track.Track;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;

public class StellarSystemTest {
    /** A. */
    private static final int A = 30;
    /** A. */
    private static final int B = 6;
    /** A. */
    private static final int C = 4;
    /** A. */
    private static final int Z = 54;
    /** A. */
    private static final double P = 0.1;
    /** A. */
    private static final int Q = 8;
    /**
     * A.
     */
    @Test public void testMove() {
        StellarSystem stellarSystem = new StellarSystem();
        Track track1 = new Track("一号轨道", 1);
        Track track2 = new Track("二号轨道", 1);
        stellarSystem.addTrack(track1);
        stellarSystem.addTrack(track2);
        Planet planet1 =
                new Planet("Earth1", "solid", "blue", 1, A, Math.PI, "CW", A);
        Planet planet2 =
                new Planet("Earth2", "solid", "blue", 1, A, Math.PI, "CCW", A);
        stellarSystem.addOnTrack(track1, planet1);
        stellarSystem.addOnTrack(track2, planet2);
        assertEquals("期望角度", B, stellarSystem.CalculatePosition(C, planet1), P);
        assertEquals("期望角度 ", Z, stellarSystem.CalculatePosition(C, planet2),
                     P);
    }
    /**
     * A.
     *
     * @throws FileNotFoundException A
     * @throws FileException         C
     */
    @Test public void testOrbitException()
            throws FileNotFoundException, FileException {
        StellarSystem stellarSystem1 =
                new StellarSystem().buildStellarSystemFromFile(
                        "src/testtxtfiles/StellarSystem_FileFormatException_1" +
                                ".txt");
        assertNull("The system should be null", stellarSystem1);
        StellarSystem stellarSystem2 =
                new StellarSystem().buildStellarSystemFromFile(
                        "src/testtxtfiles/StellarSystem_FileFormatException_2" +
                                ".txt");
        assertNull("The system should be null", stellarSystem2);
        StellarSystem stellarSystem3 =
                new StellarSystem().buildStellarSystemFromFile(
                        "src/testtxtfiles/StellarSystem_FileFormatException_3" +
                                ".txt");
        assertNull("The system should be null", stellarSystem3);
    }
    /**
     * A.
     *
     * @throws FileNotFoundException A
     * @throws TrackException        B
     * @throws FileException         C
     */
    @Test public void testNostandard()
            throws FileNotFoundException, TrackException, FileException {
        StellarSystem stellarSystem =
                new StellarSystem().buildStellarSystemFromFile(
                        "src/testtxtfiles/StellarSystem_Non-standard.txt");
        assertNotNull("Interference information should not make influence",
                      stellarSystem);
        assertEquals("The stellar's name should be \"Sun\"", "Sun",
                     stellarSystem.getCenter().getName());
        assertEquals("The number of core.track should be 8", Q,
                     stellarSystem.geTracks().size());
        assertEquals("The number of planet should be 8", Q,
                     stellarSystem.getPhysicalObjects().size());
    }
    /**
     * A.
     *
     * @throws FileNotFoundException A
     * @throws FileException         G
     */
    @Test public void testEqualLabelException()
            throws FileNotFoundException, FileException {
        StellarSystem stellarSystem =
                new StellarSystem().buildStellarSystemFromFile(
                        "src/testtxtfiles/StellarSystem_SameLabelException" +
                                ".txt");
        assertNull("Same label", stellarSystem);
    }
    /**
     * A.
     *
     * @throws FileNotFoundException A
     * @throws FileException         A
     */
    @Test public void testNumberFormatException()
            throws FileNotFoundException, FileException {
        StellarSystem stellarSystem =
                new StellarSystem().buildStellarSystemFromFile(
                        "src/testtxtfiles/StellarSystem_NumberFormatException" +
                                ".txt");
        assertNull("Illegal number format", stellarSystem);
    }
}
