package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import centerObject.User;
import circularorbit.ConcreteCircularOrbit;
import exception.EqualLabelException;
import exception.FileException;
import exception.LabelException;
import exception.NoAppException;
import exception.SentenceException;
import exception.TimeException;
import physicalObject.App;
import track.Track;

//建造app
public class PersonalAppEcosystem extends ConcreteCircularOrbit<User, App> {
	Logger logger = Logger.getLogger(PersonalAppEcosystem.class.getName());
	private final Map<App, List<Calendar>> installLog = new HashMap<>();
	private final Map<App, List<Calendar>> uninstallLog = new HashMap<>();
	private final Map<App, List<Calendar>> usageLog = new HashMap<>();
	private final Map<App, Integer> usageTime = new HashMap<>();
	private final List<PersonalAppEcosystem> applicationConfigurations = new ArrayList<>();
	private int period;
	private Calendar startTime;
	private Calendar endTime;
	
	private static final int RANK = 10;
	private static final int HOUR = 1;
	private static final int DAY = 2;
	private static final int WEEK = 3;
	private static final int MONTH = 4;
	
	//正则表达式
	private static final Pattern label = Pattern.compile("^\\w+$");
	private static final Pattern version =
			Pattern.compile("^[vV\\d.\\-_]+$|^(ver)?[vV\\d.\\-_]+(ver)?[vV\\d.\\-_]+$");
	private static final Pattern sentence = Pattern.compile("^\"([\\w\\s]+)\"$");
	private static final Pattern time =
			Pattern.compile("^([12]\\d{3}-[01]\\d-[0-3]\\d)\\s([012]\\d:[0-5]\\d:[0-5]\\d)$");
	
	public PersonalAppEcosystem() {}
	
	
	private void checkRep() { 
		assert installLog.size() >= usageLog.size(); 
		}
	
	public Map<App, List<Calendar>> getInstallLog() { return installLog; }
	
	public Map<App, List<Calendar>> getUninstallLog() { return uninstallLog; }
	
	public Map<App, List<Calendar>> getUsageLog() { return usageLog; }
	
	public Map<App, Integer> getUsageTime() { return usageTime; }
	
	public List<PersonalAppEcosystem> getApplicationConfigurations() { return applicationConfigurations; }
	
	public int getPeriod() { return period; }
	
	public Calendar getStartTime() { return startTime; }
	
	public Calendar getEndTime() { return endTime; }
	
//读文件操作
	public PersonalAppEcosystem buildPersonalAppEcosystemFromFile(String fileName)
			throws ParseException, IOException, FileException {
//		List<String> lineData =
//				new BufferedReader((new FileReader(fileName))).lines().collect(Collectors.toList());
//		Pattern pattern = Pattern.compile("([A-Za-z]+)\\s::=");
//		for (String line: lineData) {
//			Matcher matcher = pattern.matcher(line);
//			if (matcher.find()) {
//				if (matcher.group(1).equals("User")) {
//					buildPersonalAppEcosystem(lineData);
//					arrange();
//					checkRep();
//					return this;
//				}
//			}
//		}
//		return this;
		List<String> lineData =
				new BufferedReader((new FileReader(fileName))).lines().collect(Collectors.toList());
		try {
			buildPersonalAppEcosystem(lineData);
			//generateSegmentLog(fileName);
		} catch (FileException|EqualLabelException | LabelException | SentenceException | TimeException | NoAppException e) {
			e.printStackTrace();
			logger.error("fileexception");
			return null;
		}
		arrange();
		logger.info("buildsystemfromfiles");
		return this;
	}
	
	//建立系统
	private void buildPersonalAppEcosystem(List<String> lineData) throws ParseException, FileException {
//		Track[] track = new Track[10];
//		for (int i = 0; i < RANK; i++) {
//			track[i] = new Track("Track_".concat(Integer.toString(i + 1)), i + 1);
//			addTrack(track[i]);
//		}
//		Pattern pattern = Pattern.compile("([A-Za-z]+)\\s::=\\s<?([^<>]+)>?");
//		for (String lineDatum: lineData) {
//			if (lineDatum != null) {
//				Matcher matcher = pattern.matcher(lineDatum);
//				if (matcher.find()) {
//					switch (matcher.group(1)) {
//						case "User": buildUser(matcher.group(2));
//							break;
//						case "Period":
//							switch (matcher.group(2)) {
//								case "Hour": this.period = 1;
//									break;
//								case "Day": this.period = 2;
//									break;
//								case "Week": this.period = 3;
//									break;
//								case "Month": this.period = 4;
//									break;
//							}
//							break;
//						case "App": buildApp(matcher.group(2), track[9]);
//							break;
//					}
//				}
//			}
//		}
//		for (String lineDatum: lineData) {
//			if (lineDatum != null) {
//				Matcher matcher = pattern.matcher(lineDatum);
//				if (matcher.find()) {
//					switch (matcher.group(1)) {
//						case "Relation": addRelation(matcher.group(2));
//							break;
//						case "InstallLog": addInstallLog(matcher.group(2));
//							break;
//						case "UsageLog": addUsageLog(matcher.group(2));
//							break;
//						case "UninstallLog": addUninstallLog(matcher.group(2));
//							break;
//					}
//				}
//			}
//		}
//		this.startTime = getEarliestTime();
//		this.endTime = getLatestTime();
		int userFlag = 0;
		int periodFlag = 0;
		int appFlag = 0;
		Track[] track = new Track[10];
		for (int i = 0; i < RANK; i++) {
			track[i] = new Track("Track_".concat(Integer.toString(i + 1)), i + 1);
			addTrack(track[i]);
		}
		Pattern pattern = Pattern.compile("([A-Za-z]+)\\s+::=+\\s<?([^<>]+)>?");
		for (String lineDatum: lineData) {
			Matcher matcher = pattern.matcher(lineDatum);
			if (matcher.find()) {
				switch (matcher.group(1)) {
					case "User": buildUser(matcher.group(2));
						userFlag++;
						break;
					case "Period":
						switch (matcher.group(2)) {
							case "Hour": this.period = 1;
								break;
							case "Day": this.period = 2;
								break;
							case "Week": this.period = 3;
								break;
							case "Month": this.period = 4;
								break;
							default:
								System.err.println("Period format error, check and try again");
								logger.error("format error");
								throw new FileException();
						}
						periodFlag++;
						break;
					case "App": buildApp(matcher.group(2), track[9]);
						appFlag++;
						break;
				}
			}
		}
		if (userFlag != 1) {
			System.err.println("User data error, check and try again");
			logger.error("data error");
			throw new FileException();
		}
		if (periodFlag != 1) {
			System.err.println("Period data error, check and try again");
			logger.error("data error");
			throw new FileException();
		}
		if (appFlag == 0) {
			System.err.println("App data error, check and try again");
			logger.error("file error");
			throw new FileException();
		}
		for (String lineDatum: lineData) {
			Matcher matcher = pattern.matcher(lineDatum);
			if (matcher.find()) {
				switch (matcher.group(1)) {
					case "Relation": addRelation(matcher.group(2));
						break;
					case "InstallLog": addInstallLog(matcher.group(2));
						break;
					case "UsageLog": addUsageLog(matcher.group(2));
						break;
					case "UninstallLog": addUninstallLog(matcher.group(2));
						break;
				}
				
			}
		}
		this.startTime = getEarliestTime();
		this.endTime = getLatestTime();
	}
	//生成系统参与安装和卸载应用程序时间间隔
	private void generateSegmentLog(String fileName) throws ParseException, IOException, FileException {
		List<Date> timeList = getIntervalTimeList();
		for (int i = 0; i < timeList.size() - 1; i++) {
			PersonalAppEcosystem personalAppEcosystem = new PersonalAppEcosystem();
			Track[] track = new Track[10];
			for (int j = 0; j < RANK; j++) {
				track[j] = new Track("Track_".concat(Integer.toString(j + 1)), j + 1);
				personalAppEcosystem.addTrack(track[j]);
			}
			List<String> lineData = new BufferedReader((new FileReader(fileName))).lines().collect(
					Collectors.toList());
			Pattern pattern = Pattern.compile("([A-Za-z]+)\\s+::=\\s+<?\\s*([^<>]+)\\s*>?");
			for (String line: lineData) {
				if (line != null) {
					Matcher matcher = pattern.matcher(line);
					if (matcher.find()) {
						switch (matcher.group(1)) {
							case "User": personalAppEcosystem.buildUser(matcher.group(2));
								break;
							case "Period":
								switch (matcher.group(2)) {
									case "Hour": personalAppEcosystem.period = HOUR;
										break;
									case "Day": personalAppEcosystem.period = DAY;
										break;
									case "Week": personalAppEcosystem.period = WEEK;
										break;
									case "Month": personalAppEcosystem.period = MONTH;
										break;
								}
								break;
							case "App": personalAppEcosystem.buildApp(matcher.group(2), track[9]);
								break;
						}
					}
				}
			}
			for (String line: lineData) {
				if (line != null) {
					Matcher matcher = pattern.matcher(line);
					if (matcher.find()) {
						switch (matcher.group(1)) {
							case "Relation": personalAppEcosystem.addRelation(matcher.group(2));
								break;
							case "InstallLog": personalAppEcosystem.addInstallLog(matcher.group(2),
							                                                      timeList.get(i),
							                                                      timeList.get(
									                                                      i + 1));
								break;
							case "UsageLog": personalAppEcosystem.addUsageLog(matcher.group(2),
							                                                  timeList.get(i),
							                                                  timeList.get(i + 1));
								break;
							case "UninstallLog": personalAppEcosystem.addUninstallLog(
									matcher.group(2), timeList.get(i), timeList.get(i + 1));
								break;
						}
					}
				}
			}
			personalAppEcosystem.startTime = Calendar.getInstance();
			personalAppEcosystem.startTime.setTime(timeList.get(i));
			personalAppEcosystem.endTime = Calendar.getInstance();
			personalAppEcosystem.endTime.setTime(timeList.get(i + 1));
			personalAppEcosystem.arrange();
			logger.info("time");
			this.applicationConfigurations.add(personalAppEcosystem);
		}
	}
	
	/**
	 * 最早安装和最晚卸载时间
	 */
	public List<Date> getIntervalTimeList() {
		Calendar start = getEarliestTime();
		Calendar end = getLatestTime();
		List<Date> timeList = new ArrayList<>();
		while (start.before(end)) {
			timeList.add(start.getTime());
			switch (this.period) {
				case 4: start.add(Calendar.MONTH, 1);
					break;
				case 3: start.add(Calendar.WEEK_OF_MONTH, 1);
					break;
				case 2: start.add(Calendar.DAY_OF_WEEK, 1);
					break;
				case 1: start.add(Calendar.HOUR, 1);
					break;
				default:
					return null;
			}
			if (start.after(end)) {
				timeList.add(end.getTime());
				break;
			}
		}
		logger.info("gettimelist");
		return timeList;
	}
	
	/**
	 * 最早安装
	 */
	private Calendar getEarliestTime() {
		Calendar calendar = Calendar.getInstance();
		for (Map.Entry<App, List<Calendar>> entry: usageLog.entrySet()) {
			if (entry.getValue().size() > 0) {
				calendar = entry.getValue().get(0);
				break;
			}
		}
		for (Map.Entry<App, List<Calendar>> entry: installLog.entrySet()) {
			entry.getValue().sort(Comparator.naturalOrder());
			if (entry.getValue().size() > 0) {
				if (calendar.after(entry.getValue().get(0))) {
					calendar = entry.getValue().get(0);
				}
			}
		}
		return calendar;
	}
	
	/**
	 * 最晚卸载
	 */
	private Calendar getLatestTime() {
		Calendar calendar = Calendar.getInstance();
		for (Map.Entry<App, List<Calendar>> entry: usageLog.entrySet()) {
			if (entry.getValue().size() > 0) {
				calendar = entry.getValue().get(0);
				break;
			}
		}
		for (Map.Entry<App, List<Calendar>> entry: uninstallLog.entrySet()) {
			entry.getValue().sort(Comparator.reverseOrder());
			if (entry.getValue().size() > 0) {
				if (calendar.before(entry.getValue().get(0))) {
					calendar = entry.getValue().get(0);
				}
			}
		}
		return calendar;
	}
	
	/**
	 * 添加用户
	 */
	private void buildUser(String userData) {
//		User user = new User(userData);
//		addCenter(user);
		Matcher matcher = label.matcher(userData);
		if (matcher.find()) {
			addCenter(new User(matcher.group()));
		} else {
			System.err.println(
					"\"" + userData + "\"" + "User name format error, check and try again");
			logger.error("name error");
			throw new LabelException();
		}
	}
	
	/**
	 * 添加物体
	 * @throws FileException 
	 */
	private void buildApp(String appData, Track track) throws FileException {
//		String regex =
//				"(\\w+)\\s?,\\s?(\\w+)\\s?,\\s?([vV\\d.\\-_]+|(ver)?[vV\\d.\\-_]+(ver)?[vV\\d" +
//						".\\-_]+)\\s?,\\s?\"([\\w\\s]+)\"\\s?,\\s?\"([\\w\\s]+)\"";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(appData);
//		if (matcher.find()) {
//			App app =
//					new App(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(6),
//					        matcher.group(7), 0);
//			addOnTrack(track, app);
//			installLog.put(app, new ArrayList<>());
//			usageLog.put(app, new ArrayList<>());
//			uninstallLog.put(app, new ArrayList<>());
//			usageTime.put(app, 0);
//		}
		String[] args = appData.split("\\s*,\\s*");
		if (args.length != 5) {
			System.err.println("\"" + appData + "\" data format error, check and try again");
			logger.error("fileerror");
			throw new FileException();
		}
		Matcher labelMatcher1 = label.matcher(args[0]);
		if (!labelMatcher1.find()) {
			System.err.println("\"" + appData + "\" app name error, check and try again");
			
			logger.error("labelexception");
			throw new LabelException();
		}
		Matcher labelMatcher2 = label.matcher(args[1]);
		if (!labelMatcher2.find()) {
			System.err.println("\"" + appData + "\" corporation name error, check and try again");
			logger.error("label error");
			throw new LabelException();
		}
		Matcher versionMatcher = version.matcher(args[2]);
		if (!versionMatcher.find()) {
			System.err.println("\"" + appData + "\" version format error, check and try again");
			logger.error("file error");
			throw new FileException();
		}
		Matcher sentenceMatcher1 = sentence.matcher(args[3]);
		if (!sentenceMatcher1.find()) {
			System.err.println("\"" + appData + "\" feature format error, check and try again");
			logger.error("char error");
			throw new SentenceException();
		}
		Matcher sentenceMatcher2 = sentence.matcher(args[4]);
		if (!sentenceMatcher2.find()) {
			System.err.println(
					"\"" + appData + "\" business area format error, check and try again");
			logger.error("format error");
			throw new SentenceException();
		}
		for (App app: getPhysicalObjects()) {
			if (app.getName().equals(labelMatcher1.group())) {
				System.err.println(
						"\"" + labelMatcher1.group() + "\" has the same label with previous" +
								" labels");
				logger.error("equal error");
				throw new EqualLabelException();
			}
		}
		App app = new App(labelMatcher1.group(), labelMatcher2.group(), versionMatcher.group(),
		                  sentenceMatcher1.group(), sentenceMatcher2.group(), 0);
		addOnTrack(track, app);
		installLog.put(app, new ArrayList<>());
		usageLog.put(app, new ArrayList<>());
		uninstallLog.put(app, new ArrayList<>());
		usageTime.put(app, 0);
	}
	
	/**
	 * 添加app关系
	 * @throws FileException 
	 */
	private void addRelation(String appData) throws FileException {
//		String regex = "(\\w+)\\s?,\\s?(\\w+)";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(appData);
//		Set<App> apps = getPhysicalObjects();
//		if (matcher.find()) {
//			for (App app_1: apps) {
//				if (app_1.getName().equals(matcher.group(1))) {
//					for (App app_2: apps) {
//						if (app_2.getName().equals(matcher.group(2))) {
//						addPhyPhyRelation(app_1, app_2);
//							return;
//						}
//					}
//				}
//			}
//		}
		Pattern pattern = Pattern.compile("(\\w+)\\s*,\\s*(\\w+)");
		Matcher matcher = pattern.matcher(appData);
		Set<App> apps = getPhysicalObjects();
		boolean flag = true;
		if (matcher.find()) {
			for (App app_1: apps) {
				if (app_1.getName().equals(matcher.group(1))) {
					for (App app_2: apps) {
						if (app_2.getName().equals(matcher.group(2))) {
							addPhyPhyRelation(app_1, app_2);
							flag = false;
						}
					}
				}
			}
			if (flag) {
				System.err.println(
						matcher.group(1) + " or " + matcher.group(2) + " does not exist, " +
								"check and try again");
				throw new NoAppException();
			}
		} else {
			System.err.println("\"" + appData + "\" relation data error, check and try again");
			throw new FileException();
		}
	}
	/**
	 * 安装日志
	 * @throws FileException 
	 */
	private void addInstallLog(String installData) throws ParseException, FileException {
//		String regex =
//				"([12]\\d{3}-[01]\\d-[0-3]\\d)\\s?,\\s?([012]\\d:[0-5]\\d:[0-5]\\d)\\s?,\\s?" +
//						"(\\w+)";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(installData);
//		if (matcher.find()) {
//			String time = matcher.group(1) + " " + matcher.group(2);
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			for (App app: getPhysicalObjects()) {
//				if (app.getName().equals(matcher.group(3))) {
//					Calendar calendar = Calendar.getInstance();
//					calendar.setTime(simpleDateFormat.parse(time));
//					installLog.get(app).add(calendar);
//					break;
//				}
//			}
//		}
		String[] args = installData.split("\\s*,\\s*");
		boolean flag = true;
		if (args.length != 3) {
			System.err.println(
					"\"" + installData + "\" install data format error, check and try again");
			throw new FileException();
		}
		Matcher timeMatcher = time.matcher(args[0] + " " + args[1]);
		if (!timeMatcher.find()) {
			System.err.println("\"" + installData + "\" time format error, check and try " +
					                   "again\nTIME FORMAT: yyyy-MM-dd,HH:mm:ss");
			throw new TimeException();
		}
		Matcher labelMatcher = label.matcher(args[2]);
		if (!labelMatcher.find()) {
			System.err.println("\"" + installData + "\" app name error, check and try again");
			throw new LabelException();
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (App app: getPhysicalObjects()) {
			if (app.getName().equals(labelMatcher.group())) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(simpleDateFormat.parse(timeMatcher.group()));
				installLog.get(app).add(calendar);
				flag = false;
				break;
			}
		}
		if (flag) {
			System.err.println(labelMatcher.group() + " does not exist, check and try again");
			throw new NoAppException();
		}
	}
	
	/**
	 * 删除日志
	 */
	private void addInstallLog(String installData, Date start, Date end) throws ParseException {
		String regex =
				"([12]\\d{3}-[01]\\d-[0-3]\\d)\\s*,\\s*([012]\\d:[0-5]\\d:[0-5]\\d)\\s*,\\s*" +
						"(\\w+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(installData);
		if (matcher.find()) {
			String time = matcher.group(1) + " " + matcher.group(2);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (App app: getPhysicalObjects()) {
				if (app.getName().equals(matcher.group(3))) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(simpleDateFormat.parse(time));
					if (calendar.after(start) && calendar.before(end)) {
						installLog.get(app).add(calendar);
					}
					break;
				}
			}
		}
	}
	
	/**
	 * 使用日志
	 * @throws FileException 
	 */
	private void addUsageLog(String usageData) throws ParseException, FileException {
//		String regex =
//				"([12]\\d{3}-[01]\\d-[0-3]\\d)\\s?,\\s?([012]\\d:[0-5]\\d:[0-5]\\d)\\s?,\\s?" +
//						"(\\w+)" + "\\s?,\\s?(\\d+)";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(usageData);
//		if (matcher.find()) {
//			String time = matcher.group(1) + " " + matcher.group(2);
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			for (App app: getPhysicalObjects()) {
//				if (app.getName().equals(matcher.group(3))) {
//					Calendar calendar = Calendar.getInstance();
//					calendar.setTime(simpleDateFormat.parse(time));
//					usageLog.get(app).add(calendar);
//					int oldTime = usageTime.get(app);
//					usageTime.replace(app, oldTime + Integer.parseInt(matcher.group(4)));
//					break;
//				}
//			}
//		}
		String[] args = usageData.split("\\s*,\\s*");
		boolean flag = true;
		if (args.length != 4) {
			System.err.println(
					"\"" + usageData + "\" install data format error, check and try again");
			throw new FileException();
		}
		Matcher timeMatcher = time.matcher(args[0] + " " + args[1]);
		if (!timeMatcher.find()) {
			System.err.println(
					"\"" + usageData + "\" time format error, check and try again\nTIME FORMAT: " +
							"yyyy-MM-dd,HH:mm:ss");
			throw new TimeException();
		}
		Matcher labelMatcher = label.matcher(args[2]);
		if (!labelMatcher.find()) {
			System.err.println("\"" + usageData + "\" app name error, check and try again");
			throw new LabelException();
		}
		Pattern pattern = Pattern.compile("\\d+");
		Matcher numberMatcher = pattern.matcher(args[3]);
		if (!numberMatcher.find()) {
			System.err.println("\"" + usageData + "\" usage time error, check and try again");
			throw new FileException();
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (App app: getPhysicalObjects()) {
			if (app.getName().equals(labelMatcher.group())) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(simpleDateFormat.parse(timeMatcher.group()));
				usageLog.get(app).add(calendar);
				int oldTime = usageTime.get(app);
				usageTime.replace(app, oldTime + Integer.parseInt(numberMatcher.group()));
				flag = false;
				break;
			}
		}
		if (flag) {
			System.err.println(labelMatcher.group() + " does not exist, check and try again");
			throw new NoAppException();
		}
	}
	
	/**
	 * 受限使用日志
	 */
	private void addUsageLog(String usageData, Date start, Date end) throws ParseException {
		String regex =
				"([12]\\d{3}-[01]\\d-[0-3]\\d)\\s*,\\s*([012]\\d:[0-5]\\d:[0-5]\\d)\\s*,\\s*" +
						"(\\w+)\\s*,\\s*(\\d+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(usageData);
		if (matcher.find()) {
			String time = matcher.group(1) + " " + matcher.group(2);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (App app: getPhysicalObjects()) {
				if (app.getName().equals(matcher.group(3))) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(simpleDateFormat.parse(time));
					Calendar usageEndTime = calendar;
					usageEndTime.add(Calendar.MINUTE, Integer.parseInt(matcher.group(4)));
					if ((calendar.after(start) && calendar.before(end)) ||
							(usageEndTime.after(start) && usageEndTime.before(end))) {
						usageLog.get(app).add(calendar);
						int oldTime = usageTime.get(app);
						usageTime.replace(app, oldTime + Integer.parseInt(matcher.group(4)));
						break;
					}
				}
			}
		}
	}
	
	/**
	 *卸载日志
	 * @throws FileException 
	 */
	private void addUninstallLog(String uninstallData) throws ParseException, FileException {
//		String regex =
//				"([12]\\d{3}-[01]\\d-[0-3]\\d)\\s?,\\s?([012]\\d:[0-5]\\d:[0-5]\\d)\\s?,\\s?" +
//						"(\\w+)";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(uninstallData);
//		if (matcher.find()) {
//			String time = matcher.group(1) + " " + matcher.group(2);
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			for (App app: getPhysicalObjects()) {
//				if (app.getName().equals(matcher.group(3))) {
//					Calendar calendar = Calendar.getInstance();
//					calendar.setTime(simpleDateFormat.parse(time));
//					uninstallLog.get(app).add(calendar);
//					break;
//				}
//			}
//		}
		String[] args = uninstallData.split("\\s*,\\s*");
		boolean flag = true;
		if (args.length != 3) {
			System.err.println(
					"\"" + uninstallData + "\" uninstall data format error, check and try again");
			throw new FileException();
		}
		Matcher timeMatcher = time.matcher(args[0] + " " + args[1]);
		if (!timeMatcher.find()) {
			System.err.println("\"" + uninstallData + "\" time format error, check and try " +
					                   "again\nTIME FORMAT: yyyy-MM-dd,HH:mm:ss");
			throw new TimeException();
		}
		Matcher labelMatcher = label.matcher(args[2]);
		if (!labelMatcher.find()) {
			System.err.println("\"" + uninstallData + "\" app name error, check and try again");
			throw new LabelException();
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (App app: getPhysicalObjects()) {
			if (app.getName().equals(labelMatcher.group())) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(simpleDateFormat.parse(timeMatcher.group()));
				uninstallLog.get(app).add(calendar);
				flag = false;
				break;
			}
		}
		if (flag) {
			System.err.println(labelMatcher.group() + " does not exist, check and try again");
			throw new NoAppException();
		}
	}
	
	/**
	 * 受限卸载日志
	 */
	private void addUninstallLog(String uninstallData, Date start, Date end) throws ParseException {
		String regex =
				"([12]\\d{3}-[01]\\d-[0-3]\\d)\\s*,\\s*([012]\\d:[0-5]\\d:[0-5]\\d)\\s*,\\s*" +
						"(\\w+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(uninstallData);
		if (matcher.find()) {
			String time = matcher.group(1) + " " + matcher.group(2);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (App app: getPhysicalObjects()) {
				if (app.getName().equals(matcher.group(3))) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(simpleDateFormat.parse(time));
					if (calendar.after(start) && calendar.before(end)) {
						uninstallLog.get(app).add(calendar);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * app分类和转移轨道
	 */
	private void arrange() {
		List<Map.Entry<App, Integer>> info = new ArrayList<>(usageTime.entrySet());
		info.sort((i1, i2)->i2.getValue().compareTo(i1.getValue()));
		if (usageTime.size() <= RANK) {
			for (int j = 0; j < info.size(); j++) {
				transit(info.get(j).getKey(), geTracks().get(j));
				j++;
			}
		} else {
			int sizePerTrack = usageTime.size() / RANK;
			int j = 1, z = 0;
			for (Map.Entry<App, Integer> appIntegerEntry: info) {
				transit(appIntegerEntry.getKey(), geTracks().get(z));
				if (j < sizePerTrack && z < RANK - 1) {
					j++;
				} else if (j >= sizePerTrack && z < RANK - 1) {
					j = 1;
					z++;
				}
			}
		}
	}
	//转移
	public void transit(App app,Track track) {
		for(Track a:this.geTracks()) {
			if(getPhysicalMap().get(a).contains(app)) {
				getPhysicalMap().get(a).remove(app);
			}
		}
		this.getPhysicalMap().get(track).add(app);
	}
	
	public App findApp(String name) {
		for(App e:getPhysicalObjects()) {
			if(e.getName().equals(name)) return e;
		}
		return null;
	}
}