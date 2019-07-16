package core.APIS;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import core.circularorbit.CircularOrbit;
import core.circularorbit.Difference;
import core.physicalObject.PhysicalObject;
import core.track.Track;

public class CircularOrbitAPIs<L, E> {
    /** a. */
    private static final int A = 180;
    /** A. */
    private static final int B = 12;
    /**
     * A.
     */
    private Logger logger = Logger.getLogger(CircularOrbitAPIs.class.getName());
    /**
     * A.
     */
    public CircularOrbitAPIs() {
    }
    /**
     * a.
     *
     * @param c c
     * @return d
     */
    public double getObjectDistributionEntropy(final CircularOrbit<L, E> c) {
        int physicalObjectsSize = c.getPhysicalObjects().size();
        List<Track> tracks = c.geTracks();
        int tracksSize = c.geTracks().size();
        int sum = 0;
        double result = 0;
        if (tracksSize == 0) {
            return 0;
        } else {
            for (Track track: tracks) {
                int size = c.getPhysicalObjectsOnTrack(track).size();
                result += logCombination(physicalObjectsSize - sum, size);
                sum += size;
            }
        }
        logger.info("get DistributionEntropy");
        return result;
    }
    /**
     * a.
     *
     * @param c  c
     * @param e1 c
     * @param e2 c
     * @return c
     */
    public int getLogicalDistance(final CircularOrbit<L, E> c, final E e1,
                                  final E e2) {
        logger.info("get logical distance");
        if (e1.equals(e2)) {
            return 0;
        }
        Set<E> physicalObjects = c.getPhysicalObjects();
        if (!physicalObjects.contains(e1) && !physicalObjects.contains(e2)) {
            return -1;
        }
        Map<E, Boolean> visited = new HashMap<>();
        Map<E, Integer> distance = new HashMap<>();
        for (E object: physicalObjects) {
            visited.put(object, false);
            distance.put(object, 0);
        }
        Queue<E> queue = new LinkedList<>();
        queue.add(e1);
        visited.replace(e1, true);
        while (!queue.isEmpty()) {
            E current = queue.poll();
            visited.replace(current, true);
            List<E> relatedObjects = c.getRelatedPhysicalObjects(current);
            for (E physicalObject: relatedObjects.stream().filter(
                    person -> !visited.get(person)).collect(
                    Collectors.toSet())) {
                if (physicalObject.equals(e2)) {
                    return distance.get(current) + 1;
                }
                if (!visited.get(physicalObject)) {
                    distance.replace(physicalObject, distance.get(current) + 1);
                    visited.replace(physicalObject, true);
                    queue.add(physicalObject);
                }
            }
        }
        return -1;
    }
    /**
     * a.
     *
     * @param system c
     * @param e1     c
     * @param e2     c
     * @return c c
     */
    public double getPhysicalDistance(final CircularOrbit<L, E> system,
                                      final PhysicalObject e1,
                                      final PhysicalObject e2) {
        logger.info("get physical distence");
        Set<E> physicalObjects = system.getPhysicalObjects();
        if (physicalObjects.contains(e1) && physicalObjects.contains(e2)) {
            if (e1.equals(e2)) {
                return 0;
            }
            double alpha =
                    Math.toRadians(Math.abs(e1.getAngle() - e2.getAngle()) % A);
            return Math.sqrt(e1.getTrackRadius() * e1.getTrackRadius()
                             +
                                     e2.getTrackRadius() * e2.getTrackRadius()
                             -
                                     2 * e1.getTrackRadius()
                                     * e2.getTrackRadius()
                                     * Math.cos(alpha));
        }
        return -1;
    }
    /**
     * a.
     *
     * @param c1 c
     * @param c2 d
     * @return c c
     */
    public Difference getDifference(final CircularOrbit<L, E> c1,
                                    final CircularOrbit<L, E> c2) {
        return new Difference().getDifference(c1, c2);
    }
    /**
     * a.
     *
     * @param n a c
     * @return a c
     */
    private double logFactorial(final int n) {
        if (n == 0) {
            return 0;
        }
        int m = 1;
        if (n >= 1 && n <= B) {
            for (int i = 1; i <= n; i++) {
                m *= i;
            }
            return Math.log((double) m);
        } else {
            return Math.log(2 * Math.PI * (double) n) / 2
                   + (double) n * (Math.log((double) n) - 1);
        }
    }
    /**
     * A.
     *
     * @param m c
     * @param n c
     * @return S
     */
    private double logCombination(final int m, final int n) {
        return logFactorial(m) - logFactorial(n) - logFactorial(m - n);
    }
}
