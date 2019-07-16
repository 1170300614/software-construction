package test;

import application.AtomStructure;
import exception.FileException;
import physicalObject.ElectronicObject;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;

public class AtomStructureTest {
//
//	@Test
//	public void testBuildTracksFromFile() throws Exception,FileNotFoundException {
////		AtomStructure atomStructure = new AtomStructure().buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure_Medium.txt");
////		assertNotEquals("原子核名为：\"Rb\"", "Er", atomStructure.getCenter().getName());
////		assertEquals("轨道数为 6", 6, atomStructure.geTracks().size());
////		assertEquals("电子数为 68", 68, atomStructure.getPhysicalObjects().size());
//		AtomStructure atomStructure = new AtomStructure().buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure_Medium.txt");
//		assertNotEquals("the core's name should be \"Rb\"", "Er", atomStructure.getCenter().getName());
//		assertEquals("the number of track should be 6", 6, atomStructure.geTracks().size());
//		assertEquals("the number of electronic should be 68", 68, atomStructure.getPhysicalObjects().size());
//	}
//
//	@Test public void testFileException() throws FileNotFoundException, FileException {
////		AtomStructure atomStructure0 = new AtomStructure().buildAtomStructureFromFile(
////				"src/testtxtfiles/AtomicStructureFileException0.txt");
////		assertNotNull("Illegal label format", atomStructure0);
////		AtomStructure atomStructure1 = new AtomStructure().buildAtomStructureFromFile(
////				"src/testtxtfiles/AtomicStructureFileException1.txt");
////		assertNotNull("Wrong track number", atomStructure1);
////		AtomStructure atomStructure2 = new AtomStructure().buildAtomStructureFromFile(
////				"src/testtxtfiles/AtomicStructureFileException2.txt");
////		assertNotNull("Wrong file format", atomStructure2);
////		AtomStructure atomStructure3 = new AtomStructure().buildAtomStructureFromFile(
////				"src/testtxtfiles/AtomicStructureFileException3.txt");
////		assertNotNull("Core data error", atomStructure3);
////		AtomStructure atomStructure4 = new AtomStructure().buildAtomStructureFromFile(
////				"src/testtxtfiles/AtomicStructureFileException4.txt");
////		assertNotNull("Number of track data error", atomStructure4);
////		AtomStructure atomStructure5 = new AtomStructure().buildAtomStructureFromFile(
////				"src/testtxtfiles/AtomicStructureFileException5.txt");
////		assertNotNull("Number of electron data error", atomStructure5);
//		AtomStructure atomStructure1 = new AtomStructure().buildAtomStructureFromFile(
//				"src/testtxtfiles/AtomicStructure_FileFormatException_1.txt");
//		assertNull("Illegal label format", atomStructure1);
//		AtomStructure atomStructure2 = new AtomStructure().buildAtomStructureFromFile(
//				"src/testtxtfiles/AtomicStructure_FileFormatException_2.txt");
//		assertNull("Illegal label format", atomStructure2);
//		AtomStructure atomStructure3 = new AtomStructure().buildAtomStructureFromFile(
//				"src/testtxtfiles/AtomicStructure_FileFormatException_3.txt");
//		assertNull("Illegal label format", atomStructure3);
//		AtomStructure atomStructure4 = new AtomStructure().buildAtomStructureFromFile(
//				"src/testtxtfiles/AtomicStructure_FileFormatException_4.txt");
//		assertNull("Illegal label format", atomStructure4);
//		AtomStructure atomStructure5 = new AtomStructure().buildAtomStructureFromFile(
//				"src/testtxtfiles/AtomicStructure_FileFormatException_5.txt");
//		assertNull("Illegal label format", atomStructure5);
//		AtomStructure atomStructure6 = new AtomStructure().buildAtomStructureFromFile(
//				"src/testtxtfiles/AtomicStructure_FileFormatException_6.txt");
//		assertNull("Illegal label format", atomStructure6);
//	}
//
//	@Test
//	public void testTransit() throws Exception {
////		AtomStructure atomStructure = new AtomStructure().buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure.txt");
//////		int a = atomStructure.getPhysicalMap().get(atomStructure.geTracks().get(3)).size();
//////		int b = atomStructure.getPhysicalMap().get(atomStructure.geTracks().get(4)).size();
//////		atomStructure.transit(4, 5);
////		assertNotEquals("第三轨道物体数为", 18,3);
////		assertEquals("第四轨道物体数为", 8,8);
//		AtomStructure atomStructure = new AtomStructure().buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure_Medium.txt");
//		int a = 4;
//		int b = atomStructure.getPhysicalMap().get(atomStructure.geTracks().get(4)).size();
//		atomStructure.transit(4, 5);
//		assertEquals("the 3th track's object number should be", a - 1,
//				atomStructure.getPhysicalMap().get(atomStructure.geTracks().get(3)).size());
//		assertEquals("the 3th track's object number should be", b + 1,
//				atomStructure.getPhysicalMap().get(atomStructure.geTracks().get(4)).size());
//	}
////	@Test
////	public void testAddOnTrack() throws Exception{
////		AtomStructure atomStructure = new AtomStructure().buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure_Medium.txt");
////		int a = atomStructure.getPhysicalMap().get(atomStructure.geTracks().get(3)).size();
////		atomStructure.addOnTrack(atomStructure.geTracks().get(3), new ElectronicObject());
////		assertEquals("the 3th track's object number should be", a + 1,
////				atomStructure.getPhysicalMap().get(atomStructure.geTracks().get(3)).size());
////}
//	@Test public void testmess() throws FileNotFoundException, FileException {
//		AtomStructure atomStructure = new AtomStructure().buildAtomStructureFromFile(
//				"src/testtxtfiles/AtomicStructure_Non_standard.txt");
////		assertNotEquals("The core's name should be \"Rb\"", "Rb",
////		             atomStructure.getCenter().getName());
//		assertEquals("The number of track should be 5", 5, atomStructure.geTracks().size());
//		assertEquals("The number of electronic should be 37", 37,
//		             atomStructure.getPhysicalObjects().size());
//	}
//}
	//正常测试
	@Test
	public void testBuildTracksFromFile() throws Exception {
		AtomStructure atomStructure = new AtomStructure().buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure_Medium.txt");
		assertEquals("the core's name should be \"Rb\"", "Er", atomStructure.getCenter().getName());
		assertEquals("the number of track should be 6", 6, atomStructure.geTracks().size());
		assertEquals("the number of electronic should be 68", 68, atomStructure.getPhysicalObjects().size());
	}

	@Test
	public void testTransit() throws Exception {
		AtomStructure atomStructure = new AtomStructure().buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure_Medium.txt");
		int a = atomStructure.getPhysicalMap().get(atomStructure.geTracks().get(3)).size();
		int b = atomStructure.getPhysicalMap().get(atomStructure.geTracks().get(4)).size();
		atomStructure.transit(4, 5);
		assertEquals("the 3th track's object number should be", a - 1,
				atomStructure.getPhysicalMap().get(atomStructure.geTracks().get(3)).size());
		assertEquals("the 3th track's object number should be", b + 1,
				atomStructure.getPhysicalMap().get(atomStructure.geTracks().get(4)).size());
	}
	
	
	@Test
	public void testAddOnTrack() throws Exception{
		AtomStructure atomStructure = new AtomStructure().buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure_Medium.txt");
		int a = atomStructure.getPhysicalMap().get(atomStructure.geTracks().get(3)).size();
		atomStructure.addOnTrack(atomStructure.geTracks().get(3), new ElectronicObject());
		assertEquals("the 3th track's object number should be", a + 1,
				atomStructure.getPhysicalMap().get(atomStructure.geTracks().get(3)).size());
	}
	
	//文件给出有误
	@Test public void testFileFormatException() throws FileNotFoundException {
		AtomStructure atomStructure1 = new AtomStructure().buildAtomStructureFromFile(
				"src/testtxtfiles/AtomicStructure_FileFormatException_1.txt");
		assertNull("Illegal label format", atomStructure1);
		AtomStructure atomStructure2 = new AtomStructure().buildAtomStructureFromFile(
				"src/testtxtfiles/AtomicStructure_FileFormatException_2.txt");
		assertNull("Illegal label format", atomStructure2);
		AtomStructure atomStructure3 = new AtomStructure().buildAtomStructureFromFile(
				"src/testtxtfiles/AtomicStructure_FileFormatException_3.txt");
		assertNull("Illegal label format", atomStructure3);
		AtomStructure atomStructure4 = new AtomStructure().buildAtomStructureFromFile(
				"src/testtxtfiles/AtomicStructure_FileFormatException_4.txt");
		assertNull("Illegal label format", atomStructure4);
		AtomStructure atomStructure5 = new AtomStructure().buildAtomStructureFromFile(
				"src/testtxtfiles/AtomicStructure_FileFormatException_5.txt");
		assertNull("Illegal label format", atomStructure5);
		AtomStructure atomStructure6 = new AtomStructure().buildAtomStructureFromFile(
				"src/testtxtfiles/AtomicStructure_FileFormatException_6.txt");
		assertNull("Illegal label format", atomStructure6);
	} 
	
	//未按照标准输入，但不影响结果的文件输入，可以正常运行
	@Test public void test7() throws FileNotFoundException {
		AtomStructure atomStructure = new AtomStructure().buildAtomStructureFromFile(
				"src/testtxtfiles/AtomicStructure_Non_standard.txt");
		assertEquals("The core's name should be \"Rb\"", "Rb",
		             atomStructure.getCenter().getName());
		assertEquals("The number of track should be 5", 5, atomStructure.geTracks().size());
		assertEquals("The number of electronic should be 37", 37,
		             atomStructure.getPhysicalObjects().size());
	}
}