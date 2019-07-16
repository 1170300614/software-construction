package core.application;

import java.io.IOException;
import java.text.ParseException;
import java.util.Random;
import java.util.Scanner;

import core.centerObject.Core;
import core.centerObject.Stellar;
import core.APIS.CircularOrbitAPIs;
import core.APIS.CircularOrbitHelper;
import core.exception.FileException;
import core.exception.TrackException;
import core.physicalObject.ElectronicObject;
import core.physicalObject.Planet;

public class Newmain {
	static Random random = new Random();
	public static Scanner in = new Scanner(System.in);
	public static CircularOrbitHelper circularOrbitHelper = new CircularOrbitHelper();

	public static void main(String args[]) throws IOException, TrackException, ParseException, FileException {
		System.out.println("1.AtomStructure\n2.StellarSystem\n3.PersonalAppSystem\n0.end");
		int choose = in.nextInt();
		while (choose != 0) {
			switch (choose) {
			case 1:
				startAtomStructureSystem();
				break;
			case 2:
				startStellarySystem();
				break;
			case 3:
				startPersonalAppSystem();
				break;
			default:
				break;
			}
			System.out.println("1.AtomStructure\n2.StellarSystem\n3.PersonalAppSystem\n0.end");
			choose = in.nextInt();
		}
	}

	public static void startAtomStructureSystem() throws IOException, FileException {
		AtomStructure atomStructure = new AtomStructure()
				.buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure.txt");
		System.out.println("1.display\n.2.addtrack\n3.reducetrack\n4.reduceOnTrack\n5.tranist\n.6.熵值计算\n0.end");
		int choose1 = in.nextInt();
		while (choose1 != 0) {
			switch (choose1) {
			case 1:
				circularOrbitHelper.draw2(atomStructure);
				break;
			case 2:
				System.out.println("there are " + atomStructure.geTracks().size()
						+ "racks in this System\ninput the index and the number");
				int index1 = in.nextInt();// 轨道号
				int number1 = in.nextInt();// 加入数量
				// 检验轨道合法性
				if (index1 <= 0 || index1 > atomStructure.geTracks().size()) {
					System.out.println("error input");
					break;
				}
				// 依次一个一个加入
				for (int i = 0; i < number1; i++) {
					ElectronicObject electronObject = new ElectronicObject("eletron", random.nextDouble() % 360);
					atomStructure.getObjectLists(atomStructure.geTracks().get(index1 - 1)).add(electronObject);
				}
				break;
			case 3:
				System.out.println("intput core.track index");
				int a3 = in.nextInt();
				atomStructure.deleteTrack(a3 - 1);
				break;
			case 4:
				System.out.println("input core.track index and object number");
				int a4 = in.nextInt();
				int b4 = in.nextInt();
				for (int i = 0; i < b4; i++) {
					atomStructure.getObjectLists(atomStructure.geTracks().get(a4 - 1)).remove(0);
				}
				break;
			case 5:
				System.out.println("there are " + atomStructure.geTracks().size() + "racks in this System");
				System.out.println("input core.track a and core.track b(a -> b)");

				// 输入要参与越前的两个轨道
				int a5 = in.nextInt();
				int b5 = in.nextInt();

				// 轨道合法性检验
				if (a5 <= 0 || b5 <= 0 || a5 > atomStructure.geTracks().size()
						|| b5 > atomStructure.geTracks().size()) {
					System.out.println("error input");
					break;
				}
				if (atomStructure.getObjectLists(atomStructure.geTracks().get(a5 - 1)).size() <= 0) {
					System.out.println("can't reduce anymore!");
					break;
				}

				atomStructure.transit(a5, b5);
				break;
			case 6:
				CircularOrbitAPIs<Core, ElectronicObject> apIs = new CircularOrbitAPIs<Core, ElectronicObject>();
				System.out.println(apIs.getObjectDistributionEntropy(atomStructure));
//			    apIs.getDifference(atomStructure, atomStructure2);
				break;
			default:
				break;
			}
			System.out.println("1.display\n.2.addtrack\n3.reducetrack\n4.reduceOnTrack\n5.tranist\n.6.");
			choose1 = in.nextInt();
		}
	}

	public static void startStellarySystem() throws IOException, TrackException, FileException {
		StellarSystem stellarSystem = new StellarSystem()
				.buildStellarSystemFromFile("src/testtxtfiles/StellarSystem_Huge.txt");
		System.out.println(
				"1.计算行星之间距离\n" + "2.计算行星与恒星之间的距离\n" + "3.计算某一时刻行星的位置\n" + "4.熵值\n" + "5.删除轨道\n" + "7.可视化\n" + "0.结束\n");
		int choose1 = 0;
		while (choose1 != 0) {
			choose1 = in.nextInt();
			switch (choose1) {
			case 6:
				circularOrbitHelper.draw(stellarSystem);
				break;
			case 1:
				System.out.println("1.计算行星之间距离\n" + "2.计算行星与恒星之间的距离\n" + "3.计算某一时刻行星的位置\n" + "4.熵值\n" + "5.删除轨道\n"
						+ "6.可视化\n" + "0.结束\n");
				System.out.println("input the two planets' name");
				Planet planet1 = null;
				Planet planet2 = null;
				String name131 = in.nextLine();
				String name132 = in.nextLine();

				// 进行星星寻找。如果找到则正确进行
				planet1 = stellarSystem.findPlanet(name131);
				planet2 = stellarSystem.findPlanet(name132);
				if (planet1 == null || planet2 == null) {// 任何一个未找到的话就显示错误
					System.out.println("未找到天体");
				} else {
					stellarSystem.CalculateDistanceBetweenPlant(planet1, planet2);// 计算距离并输出
				}
				break;
			case 2:
				System.out.println("input the planet name");
				Planet planet14 = null;
				String name14 = in.nextLine();
				planet14 = stellarSystem.findPlanet(name14);// 验证存在
				if (planet14 == null) {
					System.out.println("未找到天体");
				} else {
					stellarSystem.CalculateDiatanceBetweenStellar(planet14);// 输出距离
				}
				break;
			case 3:
				System.out.println("input time");
				double a15 = in.nextDouble();
				in.nextLine();// 输入时间，注意回车符的读入问题
				System.out.println("input the planet's name");
				String name15 = in.nextLine();// 输入星星名称
//				System.out.println(stellarSystem.getObjectLists(core.track).getObjectNum(stellarSystem.geTracks().get(0)));// 验证存在与否
				Planet p1 = stellarSystem.findPlanet(name15);
				if (p1 != null) {
					stellarSystem.CalculatePosition(a15, p1);// 输出结果
				} else {
					System.out.println("not find!");
				}
				break;
			case 4:
				CircularOrbitAPIs<Stellar, Planet> apIs = new CircularOrbitAPIs<Stellar, Planet>();
				System.out.println(apIs.getObjectDistributionEntropy(stellarSystem));
				break;
			case 5:
				System.out.println("intput core.track index");
				int a6 = in.nextInt();
				if (a6 <= stellarSystem.geTracks().size() && a6 > 1)
					stellarSystem.deleteTrack(a6 - 1);
				else
					System.out.println("input error!");
				break;
			default:
				break;
			}
		}
	}

	public static void startPersonalAppSystem() throws IOException, ParseException, FileException {
		@SuppressWarnings("unused")
		PersonalAppEcosystem personalAppEcosystem = new PersonalAppEcosystem()
				.buildPersonalAppEcosystemFromFile("src/testtxtfiles/AtomicStructure.txt");

	}
}
