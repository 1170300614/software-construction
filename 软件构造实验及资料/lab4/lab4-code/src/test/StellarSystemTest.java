package test;

import application.StellarSystem;
import exception.FileException;
import exception.TrackException;

import org.junit.Test;

import physicalObject.Planet;
import track.Track;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;

public class StellarSystemTest {

//	@Test public void testBuildTracksFromFile() throws Exception,FileNotFoundException {
//		StellarSystem stellarSystem =
//				new StellarSystem().buildStellarSystemFromFile("src/testtxtfiles/StellarSystem_Larger.txt");
//		assertEquals("行星名为：\"ThreeBody\"", "ThreeBody",
//		             stellarSystem.getCenter().getName());
//		assertEquals("轨道数为10000", 10000,
//		             stellarSystem.geTracks().size());
//		assertEquals("行星数为10000", 10000,
//		             stellarSystem.getPhysicalObjects().size());
//	}

	@Test public void testMove() {
		StellarSystem stellarSystem = new StellarSystem();
		Track track_1 = new Track("一号轨道", 1);
		Track track_2 = new Track("二号轨道", 1);
		stellarSystem.addTrack(track_1);
		stellarSystem.addTrack(track_2);
		Planet planet_1 = new Planet("Earth1", "solid", "blue", 1, 30, Math.PI, "CW", 30);
		Planet planet_2 = new Planet("Earth2", "solid", "blue", 1, 30, Math.PI, "CCW", 30);
		stellarSystem.addOnTrack(track_1, planet_1);
		stellarSystem.addOnTrack(track_2, planet_2);
		assertEquals("期望角度", 6,
		             stellarSystem.CalculatePosition(4, planet_1), 0.1);
		assertEquals("期望角度 ", 54,
		             stellarSystem.CalculatePosition(4, planet_2), 0.1);
	}
	@Test public void testOrbitException() throws FileNotFoundException, FileException {
		StellarSystem stellarSystem1 = new StellarSystem().buildStellarSystemFromFile(
				"src/testtxtfiles/StellarSystem_FileFormatException_1.txt");
		assertNull("The system should be null", stellarSystem1);
		StellarSystem stellarSystem2 = new StellarSystem().buildStellarSystemFromFile(
				"src/testtxtfiles/StellarSystem_FileFormatException_2.txt");
		assertNull("The system should be null", stellarSystem2);
		StellarSystem stellarSystem3 = new StellarSystem().buildStellarSystemFromFile(
				"src/testtxtfiles/StellarSystem_FileFormatException_3.txt");
		assertNull("The system should be null", stellarSystem3);
	}
//	@Test public void testNo() throws FileNotFoundException, FileException {
//		StellarSystem stellarSystem = new StellarSystem().buildStellarSystemFromFile(
//				"src/testtxtfiles/StellarSystem_Non-standard.txt");
//		assertNotNull("Interference information should not make influence", stellarSystem);
//		assertEquals("The stellar's name should be \"Sun\"", "Sun",
//		             stellarSystem.getCenter().getName());
//		assertEquals("The number of track should be 8", 8, stellarSystem.geTracks().size());
//		assertEquals("The number of planet should be 8", 8,
//		             stellarSystem.getPhysicalObjects().size());
//	}
	@Test public void testNon_standard() throws FileNotFoundException, TrackException, FileException {
		StellarSystem stellarSystem = new StellarSystem().buildStellarSystemFromFile(
				"src/testtxtfiles/StellarSystem_Non-standard.txt");
		assertNotNull("Interference information should not make influence", stellarSystem);
		assertEquals("The stellar's name should be \"Sun\"", "Sun",
		             stellarSystem.getCenter().getName());
		assertEquals("The number of track should be 8", 8, stellarSystem.geTracks().size());
		assertEquals("The number of planet should be 8", 8,
		             stellarSystem.getPhysicalObjects().size());
	}
	@Test public void testEqualLabelException() throws FileNotFoundException, FileException {
		StellarSystem stellarSystem = new StellarSystem().buildStellarSystemFromFile(
				"src/testtxtfiles/StellarSystem_SameLabelException.txt");
		assertNull("Same label", stellarSystem);
	}
	@Test public void testNumberFormatException() throws FileNotFoundException, FileException {
		StellarSystem stellarSystem = new StellarSystem().buildStellarSystemFromFile(
				"src/testtxtfiles/StellarSystem_NumberFormatException.txt");
		assertNull("Illegal number format", stellarSystem);
	}
}