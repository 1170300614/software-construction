package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

import APIS.CircularOrbitAPIs;
import APIS.CircularOrbitHelper;
import centerObject.Core;
import centerObject.Stellar;
import centerObject.User;
import physicalObject.*;
import track.Track;
import exception.TrackException;
import application.StellarSystem;

public class Main {
	public static void main(String args[]) throws FileNotFoundException, Exception ,IOException {
		CircularOrbitHelper circularOrbitHelper = new CircularOrbitHelper();
		Random random =new Random();
		System.out.println("1.AtomStructure\n2.StellarSystem\n3.PersonalAppSystem\n");
		int choose = 0;// 系统选择
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		choose = in.nextInt();
		in.nextLine();// 读取掉回车符，以免后来字符串读入的时候产生问题，后面的有着同样的道理
		if (choose == 1) {// 选择原子系统
			AtomStructure atomStructure = new AtomStructure()
					.buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure.txt");
			AtomStructure atomStructure2 = new AtomStructure()
					.buildAtomStructureFromFile("src/testtxtfiles/AtomicStructure_Medium.txt");
			int choose2 = 1;
			while (choose2 != 0) {
				System.out.println("输入操作请求?\n" + 
			"1.添加轨道\n" + 
			"2.跃迁\n" + 
			"3.向轨道上加物体\n" + 
			"4.求熵值\n"+
			"5.删除轨道\n" + 
			"6.在轨道上删除\n" + 
			"7.可视化\n" + 
			"0.结束\n");
				choose2 = in.nextInt();
				switch (choose2) {
				case 1:
					System.out.println("有" + atomStructure.getTrackNum() + " 轨道");
					System.out.println("输入待设置轨道的序号和 轨道物体数目");

					// 输入轨道下标和电子数目
					int a1 = in.nextInt();
					int a2 = in.nextInt();

					// 检验输入合法性，不能超过（可以在最内层0加入和最外层加入）
					if (a1 < 0 || a1 > atomStructure.geTracks().size() + 1) {
						System.out.println("无效输入");
						break;
					}
					Track track = new Track("原子轨道", 0);// 新建的轨道
					atomStructure.addTrack(a1 - 1, track);
					for (int i = 0; i < a2; i++) {// 新建相应数目的电子
						ElectronicObject objectElectronObject = new ElectronicObject("electron",
								random.nextDouble() % 360);
						atomStructure.addOnTrack(track, objectElectronObject);
						System.out.println(i);
					}
					break;
				case 2:
					System.out.println("有" + atomStructure.geTracks().size() + "在次系统");
					System.out.println("输入轨道a和b");

					// 输入要参与越前的两个轨道
					int a = in.nextInt();
					int b = in.nextInt();

					// 轨道合法性检验
					if (a <= 0 || b <= 0 || a > atomStructure.geTracks().size()
							|| b > atomStructure.geTracks().size()) {
						System.out.println("无效输入");
						break;
					}
					if (atomStructure.getObjectLists(atomStructure.geTracks().get(a - 1)).size() <= 0) {
						System.out.println("无法减少");
						break;
					}

					atomStructure.transit(a, b);
					break;
				case 3:
					System.out.println("有" + atomStructure.geTracks().size() + "轨道在次系统\n输入系数和序号");
					int index1 = in.nextInt();// 轨道号
					int number1 = in.nextInt();// 加入数量
					// 检验轨道合法性
					if (index1 <= 0 || index1 > atomStructure.geTracks().size()) {
						System.out.println("无效输入");
						break;
					}
					// 依次一个一个加入
					for (int i = 0; i < number1; i++) {
						ElectronicObject electronObject = new ElectronicObject("电子", random.nextDouble() % 360);// 电子的建立需要标记所在几号轨道，也就是要有index1作为参数，详细见电子的方法
						atomStructure.getObjectLists(atomStructure.geTracks().get(index1 - 1)).add(electronObject);
					}
					break;
				case 7:// 可视化
					circularOrbitHelper.draw2(atomStructure);
					break;
				case 4:// 熵值计算
					CircularOrbitAPIs<Core, ElectronicObject> apIs = new CircularOrbitAPIs<Core, ElectronicObject>();
					System.out.println(apIs.getObjectDistributionEntropy(atomStructure));
					apIs.getDifference(atomStructure, atomStructure2);
					break;
				case 5:// 删除轨道
					System.out.println("输入轨道系数：");
					int a6 = in.nextInt();
					atomStructure.deleteTrack(a6 - 1);
					break;
				case 6:// 删除轨道上的物体
					System.out.println("输入轨道序号和物体的标签");
					int a7 = in.nextInt();
					int a71 = in.nextInt();
					for (int i = 0; i < a71; i++) {
						atomStructure.getObjectLists(atomStructure.geTracks().get(a7 - 1)).remove(0);
					}
					break;
				default:
					System.out.println("结束");
				}

				System.out.println("有" + atomStructure.geTracks().size() + "轨道在此系统\n");
				int count = 1;

				for (Track e : atomStructure.geTracks()) {// 输出现在轨道系统的信息
					System.out.println("有" + atomStructure.getObjectLists(e).size() + "物体在" + count + "此轨道");
					count++;
				}
			}
		}
		if (choose == 2) {// 选择行星系统
			System.out.println("加载中。。。");
			StellarSystem stellarSystem = new StellarSystem()
					.buildStellarSystemFromFile("src/testtxtfiles/StellarSystem.txt");

			// 先对读取进去的轨道系统按照轨道半径进行排序，以便后来的检查
			Collections.sort(stellarSystem.geTracks(), new Comparator<Track>() {

				@Override
				public int compare(Track o1, Track o2) {
					return Double.compare(o1.getTrackRadius(), o2.getTrackRadius());
				}

			});

			try {
				stellarSystem.check();
			} catch (TrackException e) {
				System.out.println(e.toString() + e.getMessage());
			}

			int choose1 = 1;
			while (choose1 != 0) {
				System.out.println("输入操作请求？\n"+ 
			"1.计算行星之间距离\n" + 
		    "2.计算行星与恒星之间的距离\n" + 
			"3.计算某一时刻行星的位置\n" + 
		    "4.熵值\n"+ 
			"5.删除轨道\n" +
		    "6.可视化\n" + 
			"0.结束\n");
				choose1 = in.nextInt();// 输入选择
				in.nextLine();
				switch (choose1) {
				case 1:
					System.out.println("输入两行星的名字");
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
					System.out.println("输入行星的名字");
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
					System.out.println("输入时间");
					double a15 = in.nextDouble();
					in.nextLine();// 输入时间，注意回车符的读入问题
					System.out.println("输入行星名字");
					String name15 = in.nextLine();// 输入星星名称
					System.out.println(name15);
					Planet p1 = stellarSystem.findPlanet(name15);
					if (p1 != null) {
						stellarSystem.CalculatePosition(a15, p1);// 输出结果
					} else {
						System.out.println("未找到!");
					}
					break;
				case 6:
					circularOrbitHelper.draw(stellarSystem);// 绘画
					break;
				case 4:
					CircularOrbitAPIs<Stellar, Planet> apIs = new CircularOrbitAPIs<Stellar, Planet>();
					System.out.println(apIs.getObjectDistributionEntropy(stellarSystem));
					break;
				case 5:
					System.out.println("输入轨道序号");
					int a6 = in.nextInt();
					stellarSystem.deleteTrack(a6 - 1);
					break;
				default:
					System.out.println("结束!");
				}
			}
		}
		if (choose == 3) { // 选择app系统
			PersonalAppEcosystem personalAppEcosystem = new PersonalAppEcosystem()
					.buildPersonalAppEcosystemFromFile("src/testtxtfiles/PersonalAppEcosystem.txt");
			int choose3 = 1;
			while (choose3 != 0) {
				System.out.println("请输入操作请求？");
				System.out.println("1:加入关系");
				System.out.println("2:删除关系");
				System.out.println("3:用户名称更改");
				System.out.println("4:移除App");
				System.out.println("5:删除轨道");
				System.out.println("6:熵值计算");
				System.out.println("7:可视化");
				System.out.println("8:退出");
				int choice = in.nextInt();
				in.nextLine();
				switch (choice) {
				case 1:
					String a1 = in.nextLine();
					String a2 = in.nextLine();
					personalAppEcosystem.addPhyPhyRelation(personalAppEcosystem.findApp(a1),
							personalAppEcosystem.findApp(a2));
					break;
				case 2:
					a1 = in.nextLine();
					a2 = in.nextLine();
					personalAppEcosystem.removePhyPhyRelation(personalAppEcosystem.findApp(a1),
							personalAppEcosystem.findApp(a2));
					break;
				case 3:
					System.out.println("旧用户名为" + personalAppEcosystem.getCenter().getName());
					a1 = in.nextLine();
					User user = new User(a1);
					personalAppEcosystem.removeCenter();
					personalAppEcosystem.addCenter(user);
					System.out.println("新用户名为" + personalAppEcosystem.getCenter().getName());
					break;
				case 4:
					a1 = in.nextLine();
					personalAppEcosystem.removeOnTrack(
							personalAppEcosystem.getObjectTrack(personalAppEcosystem.findApp(a1)),
							personalAppEcosystem.findApp(a1));
					break;
				case 5:
					int a = in.nextInt();
					in.nextLine();
					personalAppEcosystem.deleteTrack(personalAppEcosystem.geTracks().get(a - 1));
					break;
				case 6:
					CircularOrbitAPIs<User, App> apIs3 = new CircularOrbitAPIs<User, App>();
					System.out.println(apIs3.getObjectDistributionEntropy(personalAppEcosystem));
					break;
				case 7:
					circularOrbitHelper.draw3(personalAppEcosystem);
					break;
				case 8:
					System.out.println("退出");
					return;
				default:
					System.out.println("错误输入，请再次输入");
					break;
				}
			}

			circularOrbitHelper.draw3(personalAppEcosystem);
		}
	}
}
