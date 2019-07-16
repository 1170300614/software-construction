package APIS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import application.AtomStructure;
import application.PersonalAppEcosystem;
import application.StellarSystem;
import physicalObject.App;
import physicalObject.ElectronicObject;
import track.Track;

public class CircularOrbitHelper {
	Logger logger = Logger.getLogger(CircularOrbitHelper.class.getName());
	// 不同的绘画方式函数，后面的建造也是同样的道理
	public void draw2(AtomStructure atomStructure) {
		logger.info("draw the atomstructure");
		EventQueue.invokeLater(() -> {
			JFrame frame = new DrawFrame2(atomStructure);
			frame.setTitle("mycircle");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

	public void draw(StellarSystem stellarSystem) {
		logger.info("draw the stellar system");
		EventQueue.invokeLater(() -> {
			JFrame frame = new DrawFrame(stellarSystem);
			frame.setTitle("mycircle");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

	public void draw3(PersonalAppEcosystem personalAppEcosystem) {
		logger.info("draw app");
		EventQueue.invokeLater(() -> {
			JFrame frame = new DrawFrame3(personalAppEcosystem);
			frame.setTitle("mycircle");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

class DrawFrame extends JFrame {
	public DrawFrame(StellarSystem stellarSystem) {
		add(new DrawComponent(stellarSystem));
		pack();
	}
}

class DrawFrame2 extends JFrame {

	public DrawFrame2(AtomStructure atomStructure) {
		add(new DrawComponent2(atomStructure));
		pack();
	}
}

class DrawFrame3 extends JFrame {
	PersonalAppEcosystem personalAppEcosystem;

	public DrawFrame3(PersonalAppEcosystem personalAppEcosystem) {
		this.personalAppEcosystem = personalAppEcosystem;
		add(new DrawPersonalAppEcosystem(personalAppEcosystem));
		pack();
	}
}

class DrawComponent extends JComponent {
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension aDimension1 = kit.getScreenSize();

	int a = aDimension1.width;
	int b = aDimension1.height;
	List<Track> c;

	StellarSystem stellarSystem;

	public DrawComponent(StellarSystem stellarSystem) {
		this.c = stellarSystem.geTracks();
		this.stellarSystem = stellarSystem;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		double centerX = 0.5*a;
		double centerY = 0.5*b;
		Ellipse2D circle1 = new Ellipse2D.Double();
		Ellipse2D circle2 = new Ellipse2D.Double();
		int as = 60;
		for (int i = 0; i < c.size(); i++, as += 60) {
			circle1.setFrameFromCenter(centerX, centerY, centerX + as, centerY + as);
			circle2.setFrameFromCenter(
					centerX + Math.cos(stellarSystem.getObjectLists(c.get(i)).get(0).getAngle()) * as,
					centerY + Math.sin(stellarSystem.getObjectLists(c.get(i)).get(0).getAngle()) * as,
					centerX + Math.cos(stellarSystem.getObjectLists(c.get(i)).get(0).getAngle()) * as
							+ stellarSystem.getObjectLists(c.get(i)).get(0).getRadius() / 500,
					centerY + Math.sin(stellarSystem.getObjectLists(c.get(i)).get(0).getAngle()) * as
							+ stellarSystem.getObjectLists(c.get(i)).get(0).getRadius() / 500);
			switch (stellarSystem.getObjectLists(c.get(i)).get(0).getColor()) {
			case "Red":
				g2.setPaint(Color.RED);
				break;
			case "Dark":
				g2.setPaint(Color.BLACK);
				break;
			case "Blue":
				g2.setPaint(Color.BLUE);
				break;
			case "Yellow":
				g2.setPaint(Color.YELLOW);
			case "Green":
				g2.setPaint(Color.GREEN);
			case "Grey":
				g2.setPaint(Color.GRAY);
				break;
			case "Pink":
				g2.setPaint(Color.PINK);
				break;
			case "White":
				g2.setPaint(Color.WHITE);
				break;
			case "purple":
				g2.setPaint(Color.ORANGE);
				break;

			default:
				g2.setPaint(Color.cyan);
				break;
			}
			g2.draw(circle1);
			g2.draw(circle2);
		}

	}

	public Dimension getPreferredSize() {
		return new Dimension(a, b);
	}
}

class DrawComponent2 extends JComponent {
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension aDimension1 = kit.getScreenSize();

	int a = aDimension1.width;
	int b = aDimension1.height;
	List<Track> c;
	Map<Track, List<ElectronicObject>> map;

	public DrawComponent2(AtomStructure atomStructure) {
		this.c = atomStructure.geTracks();
		this.map = atomStructure.getPhysicalMap();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		double centerX = a / 2;
		double centerY = b / 2;
		Ellipse2D circle1 = new Ellipse2D.Double();
		Ellipse2D circle2 = new Ellipse2D.Double();
		int as = 6;
		for (int i = 0; i < c.size(); i++, as += as) {
			circle1.setFrameFromCenter(centerX, centerY, centerX + as, centerY + as);

			g2.draw(circle1);
			int k = map.get(c.get(i)).size();
			for (int j = 0; j < k; j++) {

				circle2.setFrameFromCenter(centerX + Math.cos(360.0 * j / k) * as,
						centerY + Math.sin(360.0 * j / k) * as, centerX + Math.cos(360.0 * j / k) * as + 3,
						centerY + Math.sin(360.0 * j / k) * as + 3);
				g2.draw(circle2);
			}

		}

	}

	public Dimension getPreferredSize() {
		return new Dimension(a, b);
	}
}

class DrawComponent3 extends JComponent {
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension aDimension1 = kit.getScreenSize();
	PersonalAppEcosystem personalAppEcosystem;
	int a = aDimension1.width;
	int b = aDimension1.height;
	List<Track> c;

	public DrawComponent3(PersonalAppEcosystem personalAppEcosystem) {
		this.personalAppEcosystem = personalAppEcosystem;
		this.c = personalAppEcosystem.geTracks();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		double centerX = a / 2;
		double centerY = b / 2;
		Ellipse2D circle1 = new Ellipse2D.Double();
		Ellipse2D circle2 = new Ellipse2D.Double();
		int as = 100;
		for (int i = 0; i < c.size(); i++, as += 100) {
			circle1.setFrameFromCenter(centerX, centerY, centerX + as, centerY + as);

			g2.draw(circle1);
			int k = personalAppEcosystem.getObjectLists(c.get(i)).size();// 这里我找不出轨道上的物体数，所以就先用5个代替了，先把图画出来
			for (int j = 0; j < k; j++) {
				circle2.setFrameFromCenter(centerX + Math.cos(360.0 * j / k) * as,
						centerY + Math.sin(360.0 * j / k) * as, centerX + Math.cos(360.0 * j / k) * as + 3,
						centerY + Math.sin(360.0 * j / k) * as + 3);
				g2.draw(circle2);
			}

		}

	}

	public Dimension getPreferredSize() {
		return new Dimension(a, b);
	}
}

class DrawPersonalAppEcosystem extends JComponent {
	private PersonalAppEcosystem personalAppEcosystem;
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension aDimension1 = kit.getScreenSize();
	int WIDTH = aDimension1.width;
	int HEIGHT = aDimension1.height;
	int INTERVAL = 45;

	DrawPersonalAppEcosystem(PersonalAppEcosystem personalAppEcosystem) {
		this.personalAppEcosystem = personalAppEcosystem;
	}

	public void paintComponent(Graphics g) {
		Graphics2D graphics2D = (Graphics2D) g;
		double centerX = WIDTH / 2;
		double centerY = HEIGHT / 2;
		Ellipse2D trackCircle = new Ellipse2D.Double();
		Ellipse2D appCircle = new Ellipse2D.Double();
		Map<App, int[]> position = new HashMap<>();
		graphics2D.setColor(Color.BLACK);
		graphics2D.drawString(personalAppEcosystem.getCenter().getName(), (int) centerX, (int) centerY);
		int i = 1;
		for (Track track : personalAppEcosystem.geTracks()) {
			List<App> appsOnTrack = personalAppEcosystem.getObjectLists(track);
			int angle = 0;
			if (appsOnTrack.size() != 0) {
				angle = 360 / appsOnTrack.size();
			}
			i++;
			int j = 1;
			for (App app : personalAppEcosystem.getObjectLists(track)) {
				int[] coordinate = new int[2];
				coordinate[0] = (int) (centerX + (i - 1) * INTERVAL * Math.cos(j * angle));
				coordinate[1] = (int) (centerY + (i - 1) * INTERVAL * Math.sin(j * angle));
				position.put(app, coordinate);
				j++;
			}
		}
		i = 1;
		for (Track track : personalAppEcosystem.geTracks()) {
			trackCircle.setFrameFromCenter(centerX, centerY, centerX + i * INTERVAL, centerY + i * INTERVAL);
			graphics2D.setColor(Color.BLUE);
			graphics2D.draw(trackCircle);
			List<App> appsOnTrack = personalAppEcosystem.getObjectLists(track);
			int angle = 0;
			if (appsOnTrack.size() != 0) {
				angle = 360 / appsOnTrack.size();
			}
			i++;
			int j = 1;
			for (App app : personalAppEcosystem.getObjectLists(track)) {
				int positionX = (int) (centerX + (i - 1) * INTERVAL * Math.cos(j * angle));
				int positionY = (int) (centerY + (i - 1) * INTERVAL * Math.sin(j * angle));
				j++;
				appCircle.setFrameFromCenter(positionX, positionY, positionX + 4, positionY + 4);
				graphics2D.setColor(Color.BLACK);
				graphics2D.draw(appCircle);
				graphics2D.drawString(app.getName(), positionX, positionY - 4);
				List<App> relatedApps = personalAppEcosystem.getPhysicalPhysicalMap().get(app);
				for (App relatedApp : relatedApps) {
					graphics2D.setColor(Color.GREEN);
					int[] target = position.get(relatedApp);
					graphics2D.drawLine(positionX, positionY, target[0], target[1]);
				}
			}
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}
}