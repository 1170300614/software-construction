package test;

import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;

import org.junit.Test;

import exception.IllegalInputStatementException;
import exception.TimeFormatException;
import logsource.LogSearch;

public class testlog {
	public  testlog() throws FileNotFoundException {}
	
	private LogSearch logSearch = new LogSearch("src/log/log.log");

	@Test public void tesbytime() throws Exception {
		try {
			logSearch.searchByPeriod("2019-05-19 00:46:80", "2019");
		} catch (TimeFormatException e) {
			assertNull("Time format error", e.getMessage());
		}
		try {
			logSearch.searchByPeriod("2019-05-19 00:46:40", "2019-05-19 00:45:00");
		} catch (IllegalInputStatementException e) {
			assertNull("Time format error", e.getMessage());
		}
		logSearch.searchByPeriod("2019-05-19 00:46:25", "2019-05-19 00:46:27");
	}
	

	@Test public void testbylogtype() throws Exception {
		try {
			logSearch.searchByType("TEST");
		} catch (IllegalInputStatementException e) {
			assertNull("Type format error", e.getMessage());
		}
		logSearch.searchByType("INFO");
		logSearch.searchByType("WARN");
		logSearch.searchByType("ERROR");
	}
	

	@Test public void testbumethodName() throws Exception {
		logSearch.searchByMethodName("TEST");
		logSearch.searchByMethodName("buildPersonalAppEcosystemFromFile");
		logSearch.searchByMethodName("buildElectronic");
	}
}
