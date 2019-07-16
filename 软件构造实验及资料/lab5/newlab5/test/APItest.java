import core.application.AtomStructure;
import core.application.PersonalAppEcosystem;
import core.application.StellarSystem;
import core.centerObject.Core;
import core.centerObject.Stellar;
import core.centerObject.User;
import core.physicalObject.App;
import core.physicalObject.ElectronicObject;
import core.physicalObject.Planet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import core.APIS.CircularOrbitAPIs;
/**
 * FUCK YOU TEST.
 */
public class APItest {
    /** A. */
    private static final double A = 10.60460290274525;
    /** A. */
    private static final double B = 0.1;
    /** A. */
    private static final double C = 41.03519;
    /** A. */
    private static final double D = 88.31344;
    /** A. */
    private static final double E = 4.787491;
    /** A. */
    private static final double F = 326.082383;
    /** A. */
    private static final double G = 1.6476882802594927E8;
    /** A. */
    private static final int H = 9;
    /**
     * Assert.
     */
    @Test(expected = AssertionError.class) public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    /**
     * DistributionEntropytest.
     *
     * @throws Exception exception
     */
    @Test public void testDistributionEntropy() throws Exception {
        StellarSystem stellarSystem1 =
                new StellarSystem().buildStellarSystemFromFile(
                        "src/testtxtfiles/StellarSystem.txt");
        CircularOrbitAPIs<Stellar, Planet> circularOrbitAPIs =
                new CircularOrbitAPIs<Stellar, Planet>();
        double a =
                circularOrbitAPIs.getObjectDistributionEntropy(stellarSystem1);
        assertEquals("should be 10.60460290274525", A, a, B);
        AtomStructure atom1 = new AtomStructure().buildAtomStructureFromFile(
                "src/testtxtfiles/AtomicStructure.txt");
        AtomStructure atom2 = new AtomStructure().buildAtomStructureFromFile(
                "src/testtxtfiles/AtomicStructure_Medium.txt");
        CircularOrbitAPIs<Core, ElectronicObject> circularOrbitAPIs2 =
                new CircularOrbitAPIs<Core, ElectronicObject>();
        a = circularOrbitAPIs2.getObjectDistributionEntropy(atom1);
        assertEquals("should be C", C, a, B);
        a = circularOrbitAPIs2.getObjectDistributionEntropy(atom2);
        assertEquals("should be D", D, a, B);
//        PersonalAppEcosystem personalAppEcosystem1 =
//                new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile(
//                        "src/testtxtfiles/PersonalAppEcosystem.txt");
//        PersonalAppEcosystem personalAppEcosystem2 =
//                new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile(
//                        "src/testtxtfiles/PersonalAppEcosystem_Medium.txt");
//        CircularOrbitAPIs<User, App> circularOrbitAPIs3 =
//                new CircularOrbitAPIs<User, App>();
//        a = circularOrbitAPIs3.getObjectDistributionEntropy(
//                personalAppEcosystem1);
//        assertEquals("should be E", E, a, B);
//        a = circularOrbitAPIs3.getObjectDistributionEntropy(
//                personalAppEcosystem2);
//        assertEquals("should be F", F, a, B);
    }
    /**
     * Distancetest.
     *
     * @throws Exception AD
     */
    @Test public void testdistance() throws Exception {
        StellarSystem stellarSystem1 =
                new StellarSystem().buildStellarSystemFromFile(
                        "src/testtxtfiles/StellarSystem.txt");
        Planet a = stellarSystem1.getObjectLists(
                stellarSystem1.geTracks().get(0)).get(0);
        Planet b = stellarSystem1.getObjectLists(
                stellarSystem1.geTracks().get(1)).get(0);
        double c = stellarSystem1.getPhysicalDistance(stellarSystem1, a, b);
        assertEquals(G, c, B);
//        PersonalAppEcosystem personalAppEcosystem1 =
//                new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile(
//                        "src/testtxtfiles/PersonalAppEcosystem.txt");
//        App app1 = personalAppEcosystem1.getObjectLists(
//                personalAppEcosystem1.geTracks().get(H)).get(0);
//        App app2 = personalAppEcosystem1.getObjectLists(
//                personalAppEcosystem1.geTracks().get(H)).get(1);
//        App app3 = personalAppEcosystem1.getObjectLists(
//                personalAppEcosystem1.geTracks().get(H)).get(2);
//        int a1 = personalAppEcosystem1.getLogicalDistance(personalAppEcosystem1,
//                                                          app1, app2);
//        int b1 = personalAppEcosystem1.getLogicalDistance(personalAppEcosystem1,
//                                                          app1, app3);
//        assertEquals(1, a1);
//        assertEquals(-1, b1);
    }
}

