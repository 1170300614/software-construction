package test;

import application.PersonalAppEcosystem;
import exception.FileException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.omg.PortableInterceptor.TRANSPORT_RETRY;

public class PersonalAppEcosystemTest {

	public PersonalAppEcosystemTest() throws IOException, ParseException ,FileException{
	}
	
	@Test public void testBuildTracksFromFile() throws Exception {
		PersonalAppEcosystem personalAppEcosystem =
				new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile(
						"src/testtxtfiles/PersonalAppEcosystem_Medium.txt");
		assertEquals("the user's name should be \"TimWong\"", "TimWong",
	             personalAppEcosystem.getCenter().getName());
	assertEquals("the number of track should be 10", 10,
	             personalAppEcosystem.geTracks().size());
	assertEquals("the number of app should be 150", 150,
	             personalAppEcosystem.getPhysicalObjects().size());
	assertEquals("the number of installLog should be 150", 150,
	             personalAppEcosystem.getPhysicalObjects().size());
	assertEquals("the number of usageLog should be 150", 150,
	             personalAppEcosystem.getPhysicalObjects().size());
	assertEquals("the number of uninstallLog should be 150", 150,
	             personalAppEcosystem.getPhysicalObjects().size());
	}
	

	@Test public void testGetIntervalTimeList() throws Exception {
		PersonalAppEcosystem personalAppEcosystem =
				new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile(
						"src/testtxtfiles/PersonalAppEcosystem_Medium.txt");
		List<Date> dateList = personalAppEcosystem.getIntervalTimeList();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar begin = Calendar.getInstance();
		begin.setTime(simpleDateFormat.parse("2019-01-01 8:16:11"));
		Calendar end = Calendar.getInstance();
		end.setTime(simpleDateFormat.parse("2019-05-30 22:53:01"));
		assertEquals("size should be 151 day", 151, dateList.size());
		assertEquals("earliest time should be 2019-01-01 10:16:08", begin.getTime(),
		             dateList.get(0));
		assertEquals("latest time should be 2019-05-30 22:53:51", end.getTime(),
		             dateList.get((dateList.size() - 1)));
	}
	@Test public void testFileException() throws ParseException, IOException, FileException {
		PersonalAppEcosystem personalAppEcosystem =
				new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile(
						"src/testtxtfiles/PersonalAppEcosystem_FileFormatException_1.txt");
		assertNull("User null", personalAppEcosystem);
		PersonalAppEcosystem personalAppEcosystem2 =
				new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile(
						"src/testtxtfiles/PersonalAppEcosystem_FileFormatException_2.txt");
		assertNull("Version format error", personalAppEcosystem2);
	}
	@Test public void testNoAppException() throws ParseException, IOException, FileException {
		PersonalAppEcosystem personalAppEcosystem =
				new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile(
						"src/testtxtfiles/PersonalAppEcosystem_NullApplicationException.txt");
		assertNull("Null app error", personalAppEcosystem);
	}
	@Test public void testSentenceException() throws ParseException, IOException, FileException {
		PersonalAppEcosystem personalAppEcosystem =
				new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile(
						"src/testtxtfiles/PersonalAppEcosystem_SentenceException.txt");
		assertNull("Sentence error", personalAppEcosystem);
	}
	@Test public void testEqualLabelException() throws ParseException, IOException, FileException {
		PersonalAppEcosystem personalAppEcosystem =
				new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile(
						"src/testtxtfiles/PersonalAppEcosystem_SameLabelException.txt");
		assertNull("Same label", personalAppEcosystem);
	}
	@Test public void testLabelException() throws ParseException, IOException, FileException {
		PersonalAppEcosystem personalAppEcosystem =
				new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile(
						"src/testtxtfiles/PersonalAppEcosystem_labelException.txt");
		assertNull("Label format error", personalAppEcosystem);
	}
	@Test public void testTimeFormatException() throws ParseException, IOException, FileException {
		PersonalAppEcosystem personalAppEcosystem =
				new PersonalAppEcosystem().buildPersonalAppEcosystemFromFile(
						"src/testtxtfiles/PersonalAppEcosystem_TimeFormatException.txt");
		assertNull("Time format error", personalAppEcosystem);
	}
}