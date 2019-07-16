import core.application.AtomStructure;
import core.application.StellarSystem;
import core.centerObject.Core;
import core.centerObject.Stellar;
import core.exception.FileException;
import core.exception.TrackException;
import core.physicalObject.ElectronicObject;
import core.physicalObject.Planet;
import core.track.Track;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;

import org.junit.Test;

public class AllTracksTest {
    /** A. */
    private static final int A = 3;
    /** A. */
    private static final int B = 4;
    /**
     * A.
     *
     * @throws FileNotFoundException A
     * @throws TrackException        G
     * @throws FileException         D
     */
    /**
     * A.
     *
     * @throws FileNotFoundException A
     * @throws TrackException        G
     * @throws FileException         C
     */
    @Test public void testAddTrack()
    throws FileNotFoundException, TrackException, FileException {
        StellarSystem stellarSystem1 = new StellarSystem()
                .buildStellarSystemFromFile(
                        "src/testtxtfiles/StellarSystem.txt");
        int a = stellarSystem1.geTracks().size();
        Track track1 = new Track();
        Planet planet = new Planet();
        stellarSystem1.addTrack(track1);
        stellarSystem1.addOnTrack(track1, planet);
        assertEquals(a + 1, stellarSystem1.geTracks().size());
    }
    /**
     * A.
     *
     * @throws FileNotFoundException C
     * @throws TrackException        G
     * @throws FileException         D
     */
    @Test public void testDeleteTrack()
    throws FileNotFoundException, TrackException, FileException {
        StellarSystem stellarSystem1 = new StellarSystem()
                .buildStellarSystemFromFile(
                        "src/testtxtfiles/StellarSystem.txt");
        int a = stellarSystem1.geTracks().size();
        stellarSystem1.deleteTrack(A);
        assertEquals(a - 1, stellarSystem1.geTracks().size());
        Track track = stellarSystem1.geTracks().get(A);
        stellarSystem1.deleteTrack(track);
        assertEquals(a - 2, stellarSystem1.geTracks().size());
    }
    /**
     * A.
     *
     * @throws FileNotFoundException F
     * @throws TrackException        S
     * @throws FileException         G
     */
    @Test public void testAddOnTrackandRemoveOnTrack()
    throws FileNotFoundException, TrackException, FileException {
        AtomStructure atomStructureTest = new AtomStructure()
                .buildAtomStructureFromFile(
                        "src/testtxtfiles/AtomicStructure.txt");
        StellarSystem stellarSystem1 = new StellarSystem()
                .buildStellarSystemFromFile(
                        "src/testtxtfiles/StellarSystem.txt");
        int a = stellarSystem1.getObjectLists(stellarSystem1.geTracks().get(B))
                              .size();
        stellarSystem1.removeOnTrack(stellarSystem1.geTracks().get(B),
                                     stellarSystem1.getObjectLists(
                                             stellarSystem1.geTracks().get(B))
                                                   .get(0));
        assertEquals(a - 1, stellarSystem1
                .getObjectLists(stellarSystem1.geTracks().get(B)).size());
        stellarSystem1
                .addOnTrack(stellarSystem1.geTracks().get(B), new Planet());
        assertEquals(a, stellarSystem1
                .getObjectLists(stellarSystem1.geTracks().get(B)).size());
        int b = atomStructureTest
                .getObjectLists(atomStructureTest.geTracks().get(B)).size();
        atomStructureTest.removeOnTrack(atomStructureTest.geTracks().get(B),
                                        atomStructureTest.getObjectLists(
                                                atomStructureTest.geTracks()
                                                                 .get(B))
                                                         .get(0));
        assertEquals(b - 1, atomStructureTest
                .getObjectLists(atomStructureTest.geTracks().get(B)).size());
        ElectronicObject electronicObject = new ElectronicObject();
        atomStructureTest.addOnTrack(atomStructureTest.geTracks().get(B),
                                     electronicObject);
        assertEquals(b, atomStructureTest
                .getObjectLists(atomStructureTest.geTracks().get(B)).size());
    }
    /**
     * S.
     *
     * @throws FileNotFoundException F
     * @throws TrackException        G
     * @throws FileException         G
     */
    @Test public void testAddandRemoveCenter()
    throws FileNotFoundException, TrackException, FileException {
        StellarSystem stellarSystem1 = new StellarSystem()
                .buildStellarSystemFromFile(
                        "src/testtxtfiles/StellarSystem.txt");
        AtomStructure atomStructureTest = new AtomStructure()
                .buildAtomStructureFromFile(
                        "src/testtxtfiles/AtomicStructure.txt");
        atomStructureTest.removeCenter();
        assertNull(atomStructureTest.getCenter());
        atomStructureTest.addCenter(new Core());
        assertNotNull(atomStructureTest.getCenter());
        stellarSystem1.removeCenter();
        assertNull(stellarSystem1.getCenter());
        stellarSystem1.addCenter(new Stellar());
        assertNotNull(stellarSystem1.getCenter());
    }
}
