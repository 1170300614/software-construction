import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import core.application.PersonalAppEcosystem;
import core.application.StellarSystem;
import core.exception.FileException;
import core.exception.FileFormatException;
import read.Read;

public class DataTest {
//	@Test
//	public void testBuildAppFromFile() throws Exception, FileFormatException {
//		long startTime = System.nanoTime();
//		PersonalAppEcosystem personalAppEcosystem = new PersonalAppEcosystem()
//				.buildPersonalAppEcosystemFromFile("src/testtxtfiles/PersonalAppEcosystem_Huge.txt");
////        long startTime = System.nanoTime();
//		long endTime = System.nanoTime();
//		System.out.println("this app file " + " cost: " + (endTime - startTime) / 1000000000 + "s");
//	}

	@Test
	public void testBuildTracksFromFile() throws Exception, FileException {
		long startTime = System.nanoTime();
		StellarSystem stellarSystem = new StellarSystem()
				.buildStellarSystemFromFile("src/testtxtfiles/StellarSystem_Huge.txt");
		long endTime = System.nanoTime();
		System.out.println("this stellarsystem file " + " cost: " + (endTime - startTime) / 1000000000 + "s");
	}
}
