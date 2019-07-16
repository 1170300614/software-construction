package logsource;

import exception.IllegalInputStatementException;
import exception.TimeFormatException;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class LogSearch {
	private static final Logger logger = Logger.getLogger(LogSearch.class.getName());
	
	private String fileName;
	private List<String> lineData = new ArrayList<>();
	
	//Standard log format
	private static final Pattern pattern = Pattern.compile(
			"([12]\\d{3}-[01]\\d-[0-3]\\d)\\s([012]\\d:[0-5]\\d:[0-5]\\d)" +
					".\\d{3}\\s\\[([A-Z]+)]\\s[\\w.]+\\[[\\d]+]\\[(\\w+)]");
	
	//Builder
	public LogSearch(String fileName) throws FileNotFoundException {
		this.fileName = fileName;
		update();
	}
	

	private void update() throws FileNotFoundException {
		this.lineData.clear();
		this.lineData =
				new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName),
		                                                         StandardCharsets.UTF_8)).lines().collect(
				Collectors.toList());
	}
	

	public void searchByPeriod(String start, String end)
			throws FileNotFoundException, ParseException {
		logger.info("searchByPeriod");
		update();
		Pattern time = Pattern.compile("[12]\\d{3}-[01]\\d-[0-3]\\d\\s[012]\\d:[0-5]\\d:[0-5]\\d");
		Matcher startMatcher = time.matcher(start);
		Matcher endMatcher = time.matcher(end);
		if (startMatcher.find() && endMatcher.find()) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar startTime = Calendar.getInstance();
			Calendar endTime = Calendar.getInstance();
			if (startTime.after(endTime) || start.equals(end)) {
				System.err.println("Start time should be after end time");
				logger.error("IllegalInputStatementException");
				throw new IllegalInputStatementException();
			}
			startTime.setTime(simpleDateFormat.parse(startMatcher.group()));
			endTime.setTime(simpleDateFormat.parse(endMatcher.group()));
			for (String line: lineData) {
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					Calendar currentTime = Calendar.getInstance();
					currentTime.setTime(simpleDateFormat.parse(matcher.group()));
					if (currentTime.after(startTime) && currentTime.before(endTime)) {
						System.out.println(line);
					}
				}
			}
		} else {
			System.err.println(
					"Time format error, check and try again\nTIME FORMAT: yyyy-MM-dd HH:mm:ss");
			logger.error("TimeFormatException");
			throw new TimeFormatException();
		}
	}
	

	public void searchByType(String type) throws FileNotFoundException {
		logger.info("searchByType");
		update();
		if (!type.equals("INFO") && !type.equals("WARN") && !type.equals("ERROR")) {
			System.err.println("Type error, try again");
			logger.error("IllegalInputStatementException");
			throw new IllegalInputStatementException();
		}
		for (String line: lineData) {
			Matcher matcher = pattern.matcher(line);
			if (matcher.find()) {
				if (matcher.group(3).equals(type)) {
					System.out.println(line);
				}
			}
		}
	}
	

	public void searchByMethodName(String methodName) throws FileNotFoundException {
		logger.info("searchByMethodName");
		update();
		int count = 0;
		for (String line: lineData) {
			Matcher matcher = pattern.matcher(line);
			if (matcher.find()) {
				if (matcher.group(4).equals(methodName)) {
					count++;
					System.out.println(line);
				}
			}
		}
		if (count == 0) {
			System.out.println("No search results");
		}
	}
}