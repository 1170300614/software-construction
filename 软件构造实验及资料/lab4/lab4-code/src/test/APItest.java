package test;

import application.*;
import centerObject.Core;
import centerObject.Stellar;
import centerObject.User;
import physicalObject.App;
import physicalObject.ElectronicObject;
import physicalObject.Planet;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import APIS.CircularOrbitAPIs;

public class APItest {
	@Test(expected = AssertionError.class) public void testAssertionsEnabled() {
		assert false; // make sure assertions are enabled with VM argument: -ea
	}
	
	@Test public void testDistributionEntropy() throws Exception {
		StellarSystem stellarSystem1 =
				new StellarSystem().buildStellarSystemFromFile("src/testtxtfiles/StellarSystem.txt");
			CircularOrbitAPIs< Stellar, Planet> circularOrbitAPIs = new CircularOrbitAPIs<Stellar, Planet>();
			double a = circularOrbitAPIs.getObjectDistributionEntropy(stellarSystem1);
			assertEquals("should be 10.60460290274525",10.60460290274525, a,0.1);
			AtomStructure atom1 = new AtomStructure().buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure.txt"); 
			AtomStructure atom2 = new AtomStructure().buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure_Medium.txt");
			CircularOrbitAPIs<Core, ElectronicObject> circularOrbitAPIs2 = new CircularOrbitAPIs<Core, ElectronicObject>();
			a = circularOrbitAPIs2.getObjectDistributionEntropy(atom1);
			assertEquals("should be 41.03519",41.03519, a,0.1);
			a = circularOrbitAPIs2.getObjectDistributionEntropy(atom2);
			assertEquals("should be 88.31344",88.31344, a,0.1);
			PersonalAppEcosystem personalAppEcosystem1 = new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile("src/testtxtfiles/PersonalAppEcosystem.txt");
			PersonalAppEcosystem personalAppEcosystem2 = new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile("src/testtxtfiles/PersonalAppEcosystem_Medium.txt");
			CircularOrbitAPIs<User, App> circularOrbitAPIs3 = new CircularOrbitAPIs<User, App>();
			a = circularOrbitAPIs3.getObjectDistributionEntropy(personalAppEcosystem1);
			assertEquals("should be 4.787491",4.787491, a,0.1);
			a = circularOrbitAPIs3.getObjectDistributionEntropy(personalAppEcosystem2);
			assertEquals("should be 326.082383",326.082383, a,0.1);
	}
	
	@Test public void testdistance() throws Exception {
		StellarSystem stellarSystem1 =
				new StellarSystem().buildStellarSystemFromFile("src/testtxtfiles/StellarSystem.txt");
		Planet a = stellarSystem1.getObjectLists(stellarSystem1.geTracks().get(0)).get(0);
		Planet b = stellarSystem1.getObjectLists(stellarSystem1.geTracks().get(1)).get(0);
		double c = stellarSystem1.getPhysicalDistance(stellarSystem1, a, b);
		assertEquals(1.6476882802594927E8, c,0.1);
		PersonalAppEcosystem personalAppEcosystem1 = new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile("src/testtxtfiles/PersonalAppEcosystem.txt");
		App app1 = personalAppEcosystem1.getObjectLists(personalAppEcosystem1.geTracks().get(9)).get(0);
		App app2 = personalAppEcosystem1.getObjectLists(personalAppEcosystem1.geTracks().get(9)).get(1);
		App app3 = personalAppEcosystem1.getObjectLists(personalAppEcosystem1.geTracks().get(9)).get(2);
		int a1 = personalAppEcosystem1.getLogicalDistance(personalAppEcosystem1, app1, app2);
		int b1 = personalAppEcosystem1.getLogicalDistance(personalAppEcosystem1, app1, app3);
		assertEquals(1, a1);
		assertEquals(-1, b1);
	}
}
