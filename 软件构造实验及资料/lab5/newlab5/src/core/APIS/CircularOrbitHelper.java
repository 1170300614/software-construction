package core.APIS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import core.application.AtomStructure;
import core.application.PersonalAppEcosystem;
import core.application.StellarSystem;
import core.physicalObject.App;
import core.physicalObject.ElectronicObject;
import core.track.Track;

public class CircularOrbitHelper {
    /** a. */
    private static final int A = 80;
    /** a. */
    private static final int B = 1500;
    /** a. */
    private static final int C = 360;
    /** A. */
    private static final double D = 360.0;
    /** a. */
    private Logger logger =
            Logger.getLogger(CircularOrbitHelper.class.getName());
    /**
     * a.
     *
     * @param atomStructure c
     */
    public void draw2(final AtomStructure atomStructure) {
        logger.info("draw the atomstructure");
        EventQueue.invokeLater(() -> {
            JFrame frame = new DrawFrame2(atomStructure);
            frame.setTitle("mycircle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
    /**
     * a.
     *
     * @param stellarSystem c
     */
    public void draw(final StellarSystem stellarSystem) {
        logger.info("draw the stellar system");
        EventQueue.invokeLater(() -> {
            JFrame frame = new DrawFrame(stellarSystem);
            frame.setTitle("mycircle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
    /**
     * a.
     *
     * @param personalAppEcosystem c
     */
    public void draw3(final PersonalAppEcosystem personalAppEcosystem) {
        logger.info("draw app");
        EventQueue.invokeLater(() -> {
            JFrame frame = new DrawFrame3(personalAppEcosystem);
            frame.setTitle("mycircle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
/**
 * a.
 */
@SuppressWarnings("serial") class DrawFrame extends JFrame {
    /**
     * a.
     *
     * @param stellarSystem c
     */
    DrawFrame(final StellarSystem stellarSystem) {
        add(new DrawComponent(stellarSystem));
        pack();
    }
}
/**
 * a.
 */
@SuppressWarnings("serial") class DrawFrame2 extends JFrame {
    /**
     * a.
     *
     * @param atomStructure c
     */
    DrawFrame2(final AtomStructure atomStructure) {
        add(new DrawComponent2(atomStructure));
        pack();
    }
}
/**
 * a.
 */
@SuppressWarnings("serial") class DrawFrame3 extends JFrame {
    /** a. */
    private PersonalAppEcosystem personalAppEcosystem;
    /**
     * a.
     *
     * @param personalAppEcosystems c
     */
    DrawFrame3(final PersonalAppEcosystem personalAppEcosystems) {
        this.personalAppEcosystem = personalAppEcosystems;
        add(new DrawPersonalAppEcosystem(personalAppEcosystems));
        pack();
    }
}
/**
 * a.
 */
@SuppressWarnings("serial") class DrawComponent extends JComponent {
    /** A. */
    private static final int A = 80;
    /** A. */
    private static final int B = 1500;
    /** a. */
    private Toolkit kit = Toolkit.getDefaultToolkit();
    /** a. */
    private Dimension aDimension1 = kit.getScreenSize();
    /**
     * a.
     */
    private int a = aDimension1.width;
    /** a. */
    private int b = aDimension1.height;
    /**
     * a.
     */
    private List<Track> c;
    /** a. */
    private StellarSystem stellarSystem;
    /**
     * a.
     *
     * @param stellarSystems c
     */
    DrawComponent(final StellarSystem stellarSystems) {
        this.c = stellarSystems.geTracks();
        this.stellarSystem = stellarSystems;
    }
    /**
     * a.
     *
     * @param g x
     */
    public void paintComponent(final Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        double centerX = a / 2;
        double centerY = b / 2;
        Ellipse2D circle1 = new Ellipse2D.Double();
        Ellipse2D circle2 = new Ellipse2D.Double();
        int as = A;
        for (int i = 0; i < c.size(); i++, as += A) {
            circle1.setFrameFromCenter(centerX, centerY, centerX + as,
                                       centerY + as);
            circle2.setFrameFromCenter(centerX + Math.cos(
                    stellarSystem.getObjectLists(c.get(i)).get(0).getAngle())
                                                 * as, centerY + Math.sin(
                    stellarSystem.getObjectLists(c.get(i)).get(0).getAngle())
                                                                 *
                                                               as, centerX
                                                                   +
                                                                   Math.cos(
             stellarSystem
                     .getObjectLists(
                             c.get(i))
                     .get(0)
                     .getAngle())
                                                                   *
                                                                   as
                                                                   +
                                    stellarSystem.getObjectLists(
                                             c.get(i))
                                                                     .get(0)
                                                  .getRadius()
                                                       / B, centerY
                                                                        +
                                                                      Math.sin(
                                                            stellarSystem
                                                                .getObjectLists(
                                                              c.get(i))
                                                            .get(0)
                                                          .getAngle())
                                                   *
                                                       as
                                                      +
                                                   stellarSystem
                                                             .getObjectLists(
                                                                 c.get(i))
                                                            .get(0)
                                                            .getRadius()
                                                     /
                                                   B);
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
                case "Orange":
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
    /**
     * a.
     *
     * @return d
     */
    public Dimension getPreferredSize() {
        return new Dimension(a, b);
    }
}
/*aa.
 */
@SuppressWarnings("serial") class DrawComponent2 extends JComponent {
    /** A. */
    private static final double D = 360.0;

    /** a. */
    private Toolkit kit = Toolkit.getDefaultToolkit();
    /** a. */
    private Dimension aDimension1 = kit.getScreenSize();
    /** a. */
    private int a = aDimension1.width;
    /** a. */
    private int b = aDimension1.height;
    /** a. */
    private List<Track> c;
    /** a. */
    private Map<Track, List<ElectronicObject>> map;
    /**
     * a.
     *
     * @param atomStructure x
     */
    DrawComponent2(final AtomStructure atomStructure) {
        this.c = atomStructure.geTracks();
        this.map = atomStructure.getPhysicalMap();
    }
    /**
     * aa.
     *
     * @param g c
     */
    public void paintComponent(final Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        double centerX = a / 2;
        double centerY = b / 2;
        Ellipse2D circle1 = new Ellipse2D.Double();
        Ellipse2D circle2 = new Ellipse2D.Double();
        int as = (2 * (1 + 2));
        for (int i = 0; i < c.size(); i++, as += as) {
            circle1.setFrameFromCenter(centerX, centerY, centerX + as,
                                       centerY + as);
            g2.draw(circle1);
            int k = map.get(c.get(i)).size();
            for (int j = 0; j < k; j++) {
                circle2.setFrameFromCenter(centerX + Math.cos(D * j / k) * as,
                                           centerY + Math.sin(D * j / k) * as,
                                           centerX + Math.cos(D * j / k) * as
                                           +
                                           (1 + 2),
                                           centerY + Math.sin(D * j / k) * as
                                           +
                                           (1 + 2));
                g2.draw(circle2);
            }
        }
    }
    /**
     * aa.
     *
     * @return c
     */
    public Dimension getPreferredSize() {
        return new Dimension(a, b);
    }
}
/**
 * a.
 */
@SuppressWarnings("serial") class DrawComponent3 extends JComponent {
    /** A. */
    private static final double A = 360.0;
    /** A. */
    private static final int B = 100;
    /** A. */
    private static final int C = 360;
    /** a. */
    private Toolkit kit = Toolkit.getDefaultToolkit();
    /** a. */
    private Dimension aDimension1 = kit.getScreenSize();
    /** a. */
    private PersonalAppEcosystem personalAppEcosystem;
    /** a. */
    private int a = aDimension1.width;
    /** a. */
    private int b = aDimension1.height;
    /** a. */
    private List<Track> c;
    /**
     * a.
     *
     * @param personalAppEcosystems c
     */
    DrawComponent3(final PersonalAppEcosystem personalAppEcosystems) {
        this.personalAppEcosystem = personalAppEcosystems;
        this.c = personalAppEcosystems.geTracks();
    }
    /**
     * a.
     *
     * @param g c
     */
    public void paintComponent(final Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        double centerX = a / 2;
        double centerY = b / 2;
        Ellipse2D circle1 = new Ellipse2D.Double();
        Ellipse2D circle2 = new Ellipse2D.Double();
        int as = B;
        for (int i = 0; i < c.size(); i++, as += B) {
            circle1.setFrameFromCenter(centerX, centerY, centerX + as,
                                       centerY + as);
            g2.draw(circle1);
            int k = personalAppEcosystem.getObjectLists(c.get(i))
                                        .size();
            for (int j = 0; j < k; j++) {
                circle2.setFrameFromCenter(centerX + Math.cos(A * j / k) * as,
                                           centerY + Math.sin(A * j / k) * as,
                                           centerX + Math.cos(A * j / k) * as
                                           +
                                           (1 + 2),
                                           centerY + Math.sin(A * j / k) * as
                                           +
                                           (1 + 2));
                g2.draw(circle2);
            }
        }
    }
    /**
     * a.
     *
     * @return c
     */
    public Dimension getPreferredSize() {
        return new Dimension(a, b);
    }
}
/**
 * a.
 */
@SuppressWarnings("serial") class DrawPersonalAppEcosystem extends JComponent {
    /** A. */
    private static final int C = 360;
    /** A. */
    private static final int B = 45;
    /** a. */
    private PersonalAppEcosystem personalAppEcosystem;
    /** a. */
    private Toolkit kit = Toolkit.getDefaultToolkit();
    /** a. */
    private Dimension aDimension1 = kit.getScreenSize();
    /** a. */
    private int wIDTH = aDimension1.width;
    /** a. */
    private int hEIGHT = aDimension1.height;
    /** a. */
    private int iNTERVEL = B;
    /**
     * a.
     *
     * @param personalAppEcosystems c
     */
    DrawPersonalAppEcosystem(final PersonalAppEcosystem personalAppEcosystems) {
        this.personalAppEcosystem = personalAppEcosystems;
    }
    /**
     * a.
     *
     * @param g c
     */
    public void paintComponent(final Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        double centerX = wIDTH / 2;
        double centerY = hEIGHT / 2;
        Ellipse2D trackCircle = new Ellipse2D.Double();
        Ellipse2D appCircle = new Ellipse2D.Double();
        Map<App, int[]> position = new HashMap<>();
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString(personalAppEcosystem.getCenter().getName(),
                              (int) centerX, (int) centerY);
        int i = 1;
        for (Track track: personalAppEcosystem.geTracks()) {
            List<App> appsOnTrack = personalAppEcosystem.getObjectLists(track);
            int angle = 0;
            if (appsOnTrack.size() != 0) {
                angle = C / appsOnTrack.size();
            }
            i++;
            int j = 1;
            for (App app: personalAppEcosystem.getObjectLists(track)) {
                int[] coordinate = new int[2];
                coordinate[0] = (int) (centerX + (i - 1) * iNTERVEL
                                                 *
                                                 Math.cos(j * angle));
                coordinate[1] = (int) (centerY + (i - 1) * iNTERVEL
                                                 *
                                                 Math.sin(j * angle));
                position.put(app, coordinate);
                j++;
            }
        }
        i = 1;
        for (Track track: personalAppEcosystem.geTracks()) {
            trackCircle.setFrameFromCenter(centerX, centerY,
                                           centerX + i * iNTERVEL,
                                           centerY + i * iNTERVEL);
            graphics2D.setColor(Color.BLUE);
            graphics2D.draw(trackCircle);
            List<App> appsOnTrack = personalAppEcosystem.getObjectLists(track);
            int angle = 0;
            if (appsOnTrack.size() != 0) {
                angle = C / appsOnTrack.size();
            }
            i++;
            int j = 1;
            for (App app: personalAppEcosystem.getObjectLists(track)) {
                int positionX = (int) (centerX + (i - 1) * iNTERVEL
                                                 *
                                                 Math.cos(j * angle));
                int positionY = (int) (centerY + (i - 1) * iNTERVEL
                                                 *
                                                 Math.sin(j * angle));
                j++;
                appCircle.setFrameFromCenter(positionX, positionY,
                                             positionX + (2 + 2),
                                             positionY + (2 + 2));
                graphics2D.setColor(Color.BLACK);
                graphics2D.draw(appCircle);
                graphics2D.drawString(app.getName(), positionX,
                                      positionY - (2 + 2));
                List<App> relatedApps =
                        personalAppEcosystem.getPhysicalPhysicalMap().get(app);
                for (App relatedApp: relatedApps) {
                    graphics2D.setColor(Color.GREEN);
                    int[] target = position.get(relatedApp);
                    graphics2D.drawLine(positionX, positionY, target[0],
                                        target[1]);
                }
            }
        }
    }
    /**
     * a.
     *
     * @return c
     */
    public Dimension getPreferredSize() {
        return new Dimension(wIDTH, hEIGHT);
    }
}
