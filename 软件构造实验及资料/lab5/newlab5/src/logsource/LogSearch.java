package logsource;

import core.exception.IllegalInputStatementException;
import core.exception.TimeFormatException;
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
    /**
     * a.
     */
    private static final Logger LOGGER =
            Logger.getLogger(LogSearch.class.getName());
    /**
     * a.
     */
    private String fileName;
    /**
     * a.
     */
    private List<String> lineData = new ArrayList<>();

    /**
     * a.
     */
    private static final Pattern PATTERN = Pattern.compile(
            "([12]\\d{3}-[01]\\d-[0-3]\\d)\\s([012]\\d:[0-5]\\d:[0-5]\\d)"
            +
            ".\\d{3}\\s\\[([A-Z]+)]\\s[\\w.]+\\[[\\d]+]\\[(\\w+)]");

    /**
     * a.
     *
     * @param fileNames c
     * @throws FileNotFoundException c
     */
    public LogSearch(final String fileNames) throws FileNotFoundException {
        this.fileName = fileNames;
        update();
    }

    /**
     * a.
     *
     * @throws FileNotFoundException c
     */
    private void update() throws FileNotFoundException {
        this.lineData.clear();
        this.lineData = new BufferedReader(
                new InputStreamReader(new FileInputStream(this.fileName),
                                      StandardCharsets.UTF_8)).lines().collect(
                Collectors.toList());
    }
    /**
     * a.
     *
     * @param start d
     * @param end   d
     * @throws FileNotFoundException c
     * @throws ParseException        c
     */
    public void searchByPeriod(final String start, final String end)
    throws FileNotFoundException, ParseException {
        LOGGER.info("searchByPeriod");
        update();
        Pattern time = Pattern.compile(
                "[12]\\d{3}-[01]\\d-[0-3]\\d\\s[012]\\d:[0-5]\\d:[0-5]\\d");
        Matcher startMatcher = time.matcher(start);
        Matcher endMatcher = time.matcher(end);
        if (startMatcher.find() && endMatcher.find()) {
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar startTime = Calendar.getInstance();
            Calendar endTime = Calendar.getInstance();
            if (startTime.after(endTime) || start.equals(end)) {
                System.err.println("Start time should be after end time");
                LOGGER.error("IllegalInputStatementException");
                throw new IllegalInputStatementException();
            }
            startTime.setTime(simpleDateFormat.parse(startMatcher.group()));
            endTime.setTime(simpleDateFormat.parse(endMatcher.group()));
            for (String line: lineData) {
                Matcher matcher = PATTERN.matcher(line);
                if (matcher.find()) {
                    Calendar currentTime = Calendar.getInstance();
                    currentTime
                            .setTime(simpleDateFormat.parse(matcher.group()));
                    if (currentTime.after(startTime)
                        &&
                        currentTime.before(endTime)) {
                        System.out.println(line);
                    }
                }
            }
        } else {
            System.err.println(
                    "Time format error, check and try again\nTIME FORMAT: "
                    +
                    "yyyy-MM-dd HH:mm:ss");
            LOGGER.error("TimeFormatException");
            throw new TimeFormatException();
        }
    }
    /**
     * a.
     *
     * @param type c
     * @throws FileNotFoundException c
     */
    public void searchByType(final String type) throws FileNotFoundException {
        LOGGER.info("searchByType");
        update();
        if (!type.equals("INFO")
            && !type.equals("WARN")
            &&
            !type.equals("ERROR")) {
            System.err.println("Type error, try again");
            LOGGER.error("IllegalInputStatementException");
            throw new IllegalInputStatementException();
        }
        for (String line: lineData) {
            Matcher matcher = PATTERN.matcher(line);
            if (matcher.find()) {
                if (matcher.group((1 + 2)).equals(type)) {
                    System.out.println(line);
                }
            }
        }
    }
    /**
     * a.
     *
     * @param methodName c
     * @throws FileNotFoundException c
     */
    public void searchByMethodName(final String methodName)
    throws FileNotFoundException {
        LOGGER.info("searchByMethodName");
        update();
        int count = 0;
        for (String line: lineData) {
            Matcher matcher = PATTERN.matcher(line);
            if (matcher.find()) {
                if (matcher.group((2 + 2)).equals(methodName)) {
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
