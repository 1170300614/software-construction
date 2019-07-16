package test;

import application.AtomStructure;
import application.StellarSystem;
import centerObject.Core;
import centerObject.Stellar;
import exception.FileException;
import exception.TrackException;
import physicalObject.ElectronicObject;
import physicalObject.Planet;
import track.Track;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;

import org.junit.Test;

public class AllTracksTest {
	@Test
	public void testAddTrack() throws FileNotFoundException, TrackException, FileException {
		StellarSystem stellarSystem1 = new StellarSystem()
				.buildStellarSystemFromFile("src/testtxtfiles/StellarSystem.txt");
		int a = stellarSystem1.geTracks().size();
		Track track1 = new Track();
		Planet planet = new Planet();
		stellarSystem1.addTrack(track1);
		stellarSystem1.addOnTrack(track1, planet);
		assertEquals(a + 1, stellarSystem1.geTracks().size());

	}

	@Test
	public void testDeleteTrack() throws FileNotFoundException, TrackException, FileException {
		StellarSystem stellarSystem1 = new StellarSystem()
				.buildStellarSystemFromFile("src/testtxtfiles/StellarSystem.txt");
		int a = stellarSystem1.geTracks().size();
		stellarSystem1.deleteTrack(3);
		assertEquals(a - 1, stellarSystem1.geTracks().size());
		Track track = stellarSystem1.geTracks().get(3);
		stellarSystem1.deleteTrack(track);
		assertEquals(a - 2, stellarSystem1.geTracks().size());
	}

	@Test
	public void testAddOnTrackandRemoveOnTrack() throws FileNotFoundException, TrackException, FileException {
		AtomStructure atomStructureTest = new AtomStructure()
				.buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure.txt");
		StellarSystem stellarSystem1 = new StellarSystem()
				.buildStellarSystemFromFile("src/testtxtfiles/StellarSystem.txt");
		
		int a = stellarSystem1.getObjectLists(stellarSystem1.geTracks().get(4)).size();
		stellarSystem1.removeOnTrack(stellarSystem1.geTracks().get(4),
				stellarSystem1.getObjectLists(stellarSystem1.geTracks().get(4)).get(0));
		assertEquals(a - 1, stellarSystem1.getObjectLists(stellarSystem1.geTracks().get(4)).size());
		stellarSystem1.addOnTrack(stellarSystem1.geTracks().get(4), new Planet());
		assertEquals(a, stellarSystem1.getObjectLists(stellarSystem1.geTracks().get(4)).size());
		int b = atomStructureTest.getObjectLists(atomStructureTest.geTracks().get(4)).size();
		atomStructureTest.removeOnTrack(atomStructureTest.geTracks().get(4),
				atomStructureTest.getObjectLists(atomStructureTest.geTracks().get(4)).get(0));
		assertEquals(b - 1, atomStructureTest.getObjectLists(atomStructureTest.geTracks().get(4)).size());
		ElectronicObject electronicObject = new ElectronicObject();
		atomStructureTest.addOnTrack(atomStructureTest.geTracks().get(4), electronicObject);
		assertEquals(b, atomStructureTest.getObjectLists(atomStructureTest.geTracks().get(4)).size());
	}

	@Test
	public void testAddandRemoveCenter() throws FileNotFoundException, TrackException, FileException {
		StellarSystem stellarSystem1 = new StellarSystem()
				.buildStellarSystemFromFile("src/testtxtfiles/StellarSystem.txt");
		AtomStructure atomStructureTest = new AtomStructure()
				.buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure.txt");
		
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
