package APIS;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import circularorbit.CircularOrbit;
import circularorbit.Difference;
import physicalObject.PhysicalObject;
import track.Track;

public class CircularOrbitAPIs<L, E> {
	Logger logger = Logger.getLogger(CircularOrbitAPIs.class.getName());
	public CircularOrbitAPIs() {
	}

	/**
	 * 求熵
*/
	public double getObjectDistributionEntropy(CircularOrbit<L, E> c) {
		int physicalObjectsSize = c.getPhysicalObjects().size();
		List<Track> tracks = c.geTracks();
		int tracksSize = c.geTracks().size();
		int sum = 0;
		double result = 0;
		if (tracksSize == 0) {
			return 0;
		} else {
			for (Track track : tracks) {
				int size = c.getPhysicalObjectsOnTrack(track).size();
				result += logCombination(physicalObjectsSize - sum, size);
				sum += size;
			}
		}
		logger.info("get DistributionEntropy");
		return result;
	}

	/**
	 * 求轨道物体的逻辑距离
	 *
	 * @param c  current circular orbit system
	 * @param e1 one of the orbit objects
	 * @param e2 another orbit object
	 * @return
	 */
	public int getLogicalDistance(CircularOrbit<L, E> c, E e1, E e2) {
		logger.info("get logical distance");
		if (e1.equals(e2))
			return 0;
		Set<E> physicalObjects = c.getPhysicalObjects();
		if (!physicalObjects.contains(e1) && !physicalObjects.contains(e2)) {
			return -1;
		}
		Map<E, Boolean> visited = new HashMap<>();
		Map<E, Integer> distance = new HashMap<>();
		for (E object : physicalObjects) {
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
			for (E physicalObject : relatedObjects.stream().filter(person -> !visited.get(person))
					.collect(Collectors.toSet())) {
				if (physicalObject.equals(e2))
					return distance.get(current) + 1;
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
	 * 求轨道物体的实际距离
	 *
	 * @param system current circular orbit system
	 * @param e1     one of the orbit system
	 * @param e2     another orbit objects
	 * @return
	 */
	public double getPhysicalDistance(CircularOrbit<L, E> system, PhysicalObject e1, PhysicalObject e2) {
		logger.info("get physical distence");
		Set<E> physicalObjects = system.getPhysicalObjects();
		if (physicalObjects.contains(e1) && physicalObjects.contains(e2)) {
			if (e1.equals(e2)) {
				return 0;
			}
			double alpha = Math.toRadians(Math.abs(e1.getAngle() - e2.getAngle()) % 180);
			return Math.sqrt(e1.getTrackRadius() * e1.getTrackRadius() + e2.getTrackRadius() * e2.getTrackRadius()
					- 2 * e1.getTrackRadius() * e2.getTrackRadius() * Math.cos(alpha));
		}
		return -1;
	}

	/**
	 * 得到两系统的区别
*/
	public Difference getDifference(CircularOrbit<L, E> c1, CircularOrbit<L, E> c2) {
		return new Difference().getDifference(c1, c2);
	}

	private double logFactorial(int n) {
		if (n == 0)
			return 0;
		int m = 1;
		if (n >= 1 && n <= 12) {
			for (int i = 1; i <= n; i++) {
				m *= i;
			}
			return Math.log((double) m);
		} else {
			return 0.5 * Math.log(2 * Math.PI * (double) n) + (double) n * (Math.log((double) n) - 1);
		}
	}

	private double logCombination(int m, int n) {
		return logFactorial(m) - logFactorial(n) - logFactorial(m - n);
	}
}