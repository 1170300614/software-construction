package core.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import core.centerObject.Stellar;
import core.circularorbit.ConcreteCircularOrbit;
import core.exception.EqualLabelException;
import core.exception.FileException;
import core.exception.NumberFormatException;
import core.exception.TrackException;
import core.physicalObject.Planet;
import core.track.Track;

public class StellarSystem extends ConcreteCircularOrbit<Stellar, Planet> {
	Logger logger = Logger.getLogger(StellarSystem.class.getName());

	private void checkRep() {
		for (Track track : geTracks()) {
			assert getPhysicalMap().get(track).size() == 1;
		}
	}

	/**
	 * 读文件
	 */
	@SuppressWarnings("resource")
	public StellarSystem buildStellarSystemFromFile(String fileName) throws FileNotFoundException, FileException {
		/*
		 * List<String> lineData = new BufferedReader((new
		 * FileReader(fileName))).lines().collect(Collectors.toList()); Pattern pattern
		 * = Pattern.compile("([a-zA-Z]+)\\s::="); for (String line: lineData) { if
		 * (line != null) { Matcher matcher = pattern.matcher(line); if (matcher.find())
		 * { if (matcher.group(1).equals("Stellar")) { buildStellarSystem(lineData);
		 * checkRep(); return this; } } } } checkRep(); return this;
		 */
//		List<String> lineData =
//				new BufferedReader((new FileReader(fileName))).lines().collect(Collectors.toList());
//		try {
//			buildStellarSystem(lineData);
//		} catch (NumberFormatException | EqualLabelException e) {
//			e.printStackTrace();
//			return null;
//		}
//		try {
//			checkRep();
//		} catch (RuntimeException e) {
//			System.err.println("Track has more than one planet");
//			System.err.println("Data format error, check the data and try again");
//			e.printStackTrace();
//			return null;
//		}
//		try {
//			checkInterval();
//		} catch (OrbitException e) {
//			System.err.println("Check the data and try again");
//			e.printStackTrace();
//			return null;
//		}
//		return this;
		List<String> lineData = new BufferedReader((new FileReader(fileName))).lines().collect(Collectors.toList());
		try {
			buildStellarSystem(lineData);
		} catch (NumberFormatException | FileException | EqualLabelException e) {
			e.printStackTrace();
			logger.error("FileException");
			return null;
		}
		try {
			checkRep();
		} catch (RuntimeException e) {
			System.err.println("Track has more than one planet");
			System.err.println("Data format error, check the data and try again");
			e.printStackTrace();
			logger.error("Data format error");
			return null;
		}
		try {
			ck();
			check();
		} catch (TrackException e) {
			System.err.println("Check the data and try again");
			logger.error("Check the data");
			e.printStackTrace();
			return null;
		}
		logger.info("buildsystemfromfiles");
		return this;
	}

	public void ck() {
		Collections.sort(this.geTracks(), new Comparator<Track>() {

			@Override
			public int compare(Track o1, Track o2) {
				return Double.compare(o1.gettrackRadius(), o2.gettrackRadius());
			}

		});
	}

	/**
	 * 建立系统
	 * 
	 * @throws FileException
	 */
	private void buildStellarSystem(List<String> lineData) throws FileException {
		logger.info("buildsystem");
		Pattern pattern = Pattern.compile("([a-zA-Z]+)\\s+::=\\s+<\\s*([^<>]+)\\s*>");
		int stellarNumber = 0;
		int planetNumber = 0;
		for (String line : lineData) {
			if (line != null) {
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					if (matcher.group(1).equals("Stellar")) {
						buildStellar(matcher.group(2));
						stellarNumber++;
					} else if (matcher.group(1).equals("Planet")) {
						buildPlanet(matcher.group(2));
						planetNumber++;
					} else
						throw new FileException();
				}
			}
		}
		if (stellarNumber != 1) {
			System.err.println("The stellar data has error");
			logger.error("The stellar data has error");
			throw new FileException();
		} else if (planetNumber == 0) {
			System.err.println("The planet data has error");
			logger.error("The planet data has error");
			throw new FileException();
		}
	}

	/**
	 * 建立恒星
	 */
	private void buildStellar(String stellarData) {
		logger.info("buildstellar");
		String regex = "^(^\\w+)\\s*,\\s*([1-9]\\d{0,3}(.\\d+)?|[1-9](.\\d+)?(e\\d+)?)\\s*,\\s*"
				+ "([1-9]\\d{0,3}(.\\d+)?|[1-9](.\\d+)?(e\\d+)?)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(stellarData);
		if (matcher.find()) {
			addCenter(new Stellar(matcher.group(1), Double.parseDouble(matcher.group(2)),
					Double.parseDouble(matcher.group(6))));
		} else {
			System.err.println("行星数据 \"" + stellarData + "\" 无法确定形式" + "");
			logger.error("numberException");
			throw new NumberFormatException();
		}
	}

	/**
	 * 建立行星
	 * 
	 * @throws FileException
	 */
	private void buildPlanet(String planetData) throws FileException {
//		String regex =
//				"^(^\\w+)\\s*,\\s*(\\w+)\\s*,\\s*(\\w+)\\s*,\\s*(\\d{1,4}(.\\d+)?|[1-9](.\\d+)?" +
//						"(e\\d+)?)\\s*,\\s*(\\d{1,4}(\\.\\d+)?|[1-9]0?(.\\d+)?(e\\d+)?)\\s*,\\s*" +
//						"(\\d{1,4}(.\\d+)?|[1-9](.\\d+)?(e\\d+)?)\\s*,\\s*(CC?W)\\s*,\\s*(360(" +
//						".0+)?|3[0-5]\\d(.\\d+)?|[12]\\d{2}(.\\d+)?|[1-9]\\d(.\\d+)?|\\d(.\\d+)" +
//						"?)$";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(planetData);
////		if (matcher.find()) {
//////			Track core.track = new Track("Track", Double.parseDouble(matcher.group(8)));
//////			Planet planet = new Planet(matcher.group(1), matcher.group(2), matcher.group(3),
//////			                           Double.parseDouble(matcher.group(4)),
//////			                           Double.parseDouble(matcher.group(8)),
//////			                           Double.parseDouble(matcher.group(12)), matcher.group(16),
//////			                           Double.parseDouble(matcher.group(17)));
//////			addTrack(core.track);
//////			addOnTrack(core.track, planet);
////			
////		}
//		if (matcher.find()) {
//			for (Planet planet: getPhysicalObjects()) {
//				if (planet.getName().equals(matcher.group(1))) {
//					System.err.println(
//							"\"" + matcher.group(1) + "\" has the same label with privious" +
//									" labels");
//					throw new EqualLabelException();
//				}
//			}
//			Track core.track = new Track("Track", Double.parseDouble(matcher.group(8)));
//			Planet planet = new Planet(matcher.group(1), matcher.group(2), matcher.group(3),
//			                           Double.parseDouble(matcher.group(4)),
//			                           Double.parseDouble(matcher.group(8)),
//			                           Double.parseDouble(matcher.group(12)), matcher.group(16),
//			                           Double.parseDouble(matcher.group(17)));
//			addTrack(core.track);
//			addOnTrack(core.track, planet);
//		} else {
//			System.err.println("Planet data \"" + planetData + "\" is not conforming the format");
//			throw new FileException();
//		}
		logger.info("buildplanet");
		String regex = "^(^\\w+)\\s*,\\s*(\\w+)\\s*,\\s*(\\w+)\\s*,\\s*(\\d{1,4}(.\\d+)?|[1-9](.\\d+)?"
				+ "(e\\d+)?)\\s*,\\s*(\\d{1,4}(\\.\\d+)?|[1-9]0?(.\\d+)?(e\\d+)?)\\s*,\\s*"
				+ "(\\d{1,4}(.\\d+)?|[1-9](.\\d+)?(e\\d+)?)\\s*,\\s*(CC?W)\\s*,\\s*(360("
				+ ".0+)?|3[0-5]\\d(.\\d+)?|[12]\\d{2}(.\\d+)?|[1-9]\\d(.\\d+)?|\\d(.\\d+)" + "?)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(planetData);
		if (matcher.find()) {
			for (Planet planet : getPhysicalObjects()) {
				if (planet.getName().equals(matcher.group(1))) {
					System.err.println("\"" + matcher.group(1) + "\" has the same label with privious" + " labels");
					logger.error("equallabel");
					throw new EqualLabelException();
				}
			}
			Track track = new Track("Track", Double.parseDouble(matcher.group(8)));
			Planet planet = new Planet(matcher.group(1), matcher.group(2), matcher.group(3),
					Double.parseDouble(matcher.group(4)), Double.parseDouble(matcher.group(8)),
					Double.parseDouble(matcher.group(12)), matcher.group(16), Double.parseDouble(matcher.group(17)));
			addTrack(track);
			addOnTrack(track, planet);
//			System.out.println(planetData);
		} else {
			System.err.println("Planet data \"" + planetData + "\" is not conforming the format");
			logger.error("fileexception");
			throw new FileException();
		}
	}

	// 计算位置
	public double CalculatePosition(double t, Planet planet) {
		logger.info("calculatedistance");
		double angle1 = (planet.getSpeed() * t) * (360) / (2 * Math.PI * planet.getTrackRadius());
		if (planet.getDirection() == "CCW") {
			System.out.println("angle from " + planet.getAngle() + "change to" + (planet.getAngle() + angle1));
			return planet.getAngle() + angle1;
		} else {
			System.out.println("angle from " + planet.getAngle() + "change to" + (-planet.getAngle() + angle1));
			return planet.getAngle() - angle1;
		}
	}

	// 计算两个星星之间的距离，需要用到余弦定理等，其他的也类似
	public void CalculateDistanceBetweenPlant(Planet p1, Planet p2) {
		double r1 = p1.getTrackRadius();
		double r2 = p2.getTrackRadius();
		double angle = Math.abs(p1.getAngle() - p2.getAngle());
		if (angle >= 180) {
			angle = angle - 180;
		}
		double ans = r1 * r1 + r2 * r2 - 2 * r1 * r2 * Math.cos(angle);
		System.out.println("The distance of two planet is " + ans + " .");
		logger.info("distance");
	}

	// 计算行星和恒星之间的距离
	public double CalculateDiatanceBetweenStellar(Planet disPlanet) {
		System.out.println("The distance from Stellar is " + disPlanet.getTrackRadius() + " .");
		return disPlanet.getTrackRadius();
	}

	// 检查轨道是否合法
	public int check() throws TrackException {
		// 检查是否有空轨道（有空轨道就算不合法）
		for (Track e : this.geTracks()) {
			double a = e.gettrackRadius();
			double b = e.gettrackRadius();
			double c = getObjectLists(e).get(0).getRadius();
			double d = getObjectLists(e).get(0).getRadius();
			System.out.println(a + " " + b + " " + c + " " + d);
			if (this.getPhysicalMap().get(e).size() != 1) {
				throw new TrackException("Track radius error!");
			}
		}

		// 检查是否有放不下的情况
		for (int i = 0, j = 1; j < this.getTrackNum(); i++, j++) {
			double a = this.geTracks().get(j).gettrackRadius();
			double b = this.geTracks().get(i).gettrackRadius();
			double c = getObjectLists(this.geTracks().get(j)).get(0).getRadius();
			double d = getObjectLists(this.geTracks().get(i)).get(0).getRadius();
			System.out.println(a + " " + b + " " + c + " " + d);
			if ((a - b) < (c + d)) {
				throw new TrackException("Track radius error!");
			}
		}
		return 0;
	}

	// 寻找行星，用来验证存在与否，存在的话则返回找的的行星
	public Planet findPlanet(String name) {
		for (Track e : this.geTracks()) {
			if (getObjectLists(e).get(0).getName().equals(name)) {
				return getObjectLists(e).get(0);
			}
		}
		return null;
	}
}
