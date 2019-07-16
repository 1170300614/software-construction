package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import centerObject.Core;
import circularorbit.ConcreteCircularOrbit;
import exception.FileFormatException;
import exception.LabelException;
import physicalObject.ElectronicObject;
import track.Track;
//读文件操作
public class AtomStructure extends ConcreteCircularOrbit<Core, ElectronicObject>{
	Random random =new Random();
	Logger logger = Logger.getLogger(AtomStructure.class.getName());
	public void checkRep() { 
		assert getCenter() != null;
	}
	@SuppressWarnings("resource")
	public AtomStructure buildAtomStructureFromFile(String fileName) throws FileNotFoundException {
		PropertyConfigurator.configure("log4j.properties");
		logger.info("bulid AtomStructure from file");
		int numberOfTracks = 0;
		int flag = 0;
		List<String> lineData =
				new BufferedReader((new FileReader(fileName))).lines().collect(Collectors.toList());
		Pattern pattern = Pattern.compile("([a-zA-Z]+)\\s+::=\\s+(\\w+)");
		try {
			for (String line: lineData) {
				if (line != null) {
					Matcher matcher = pattern.matcher(line);
					if (matcher.find()) {
						if (matcher.group(1).equals("NumberOfTracks")) {
							numberOfTracks = Integer.parseInt(matcher.group(2));
							flag++;
							break;
						}
					}
				}
			}
			
			if (flag != 1) {
				System.out.println("Number of tracks error");
                logger.error("Number of tracks error");
				throw new FileFormatException();
			}
			buildAtomStructure(lineData, numberOfTracks);
		} catch (FileFormatException | LabelException e) {
			System.err.println("Data format error, check the data and try again");
			e.printStackTrace();
			logger.error("Data format error");
			return null;
		}
		return this;
	}
	
	/**
	 * build the system by the data
	 *
	 * @param lineData standard data
	 */
	private void buildAtomStructure(List<String> lineData, int numberOfTracks) {
		int coreNumber = 0;
		int numberOfElectron = 0;
		Pattern pattern = Pattern.compile("([a-zA-Z]+)\\s*::=\\s*([A-Z][a-z]?|[\\d;/\\s]+)");
		for (String line: lineData) {
			if (line != null) {
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					if (matcher.group(1).equals("ElementName")) {
						buildCore(matcher.group(2));
						coreNumber++;
					} else if (matcher.group(1).equals("NumberOfElectron")) {
						buildElectronic(numberOfTracks, matcher.group(2));
						numberOfElectron++;
					}
				}
			}
		}
		if (coreNumber != 1) {
			System.err.println("Core data error, check and try again");
			logger.error("Core data error");
			throw new FileFormatException();
		}
		if (numberOfElectron != 1) {
			System.err.println("Number of electronic error, check and try again");
			logger.error("Number of electronic error");
			throw new FileFormatException();
		}
	}
	
	/**
	 * build a core from the data
	 *
	 * @param coreData the core
	 */
	private void buildCore(String coreData) {
		Pattern pattern = Pattern.compile("[A-Z][a-z]?");
		Matcher matcher = pattern.matcher(coreData);
		if (matcher.find()) {
			Core core = new Core(coreData);
			addCenter(core);
		} else {
			System.err.println("Core name illegal");
			logger.error("Core name illegal");
			throw new LabelException();
		}
	}
	
	/**
	 * build a electronic from the data
	 *
	 * @param numberOfTracks number of the track
	 * @param trackData      standard data
	 */
	private void buildElectronic(int numberOfTracks, String trackData) {
		String[] args = trackData.split("\\s*[;/]\\s*");
		if (args.length != numberOfTracks * 2) {
			System.err.println("Track number error");
			logger.error("Track number error");
			throw new FileFormatException();
		}
		Track[] tracks = new Track[numberOfTracks];
		for (int i = 0; i < numberOfTracks; i++) {
			tracks[i] = new Track(Integer.toString(i + 1));
			addTrack(tracks[i]);
		}
		for (int i = 0; i < numberOfTracks; i++) {
			for (int j = 0; j < Integer.parseInt(args[i * 2 + 1].replaceAll("\\s", "")); j++) {
				ElectronicObject electronic =
						new ElectronicObject(Integer.toString(j + 1), random.nextDouble() % 360);
				addOnTrack(tracks[Integer.parseInt(args[i * 2]) - 1], electronic);
			}
		}
	}
	
	public void transit(int a,int b) {
		logger.info("transit the electron");
		this.getObjectLists(this.geTracks().get(a - 1)).remove(0);
		ElectronicObject objectElectronObject = new ElectronicObject("eletron",
				random.nextDouble() % 360);
		this.getObjectLists(this.geTracks().get(b - 1)).add(objectElectronObject);
	}
	
}