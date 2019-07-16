package core.circularorbit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import core.centerObject.CenterObject;
import core.physicalObject.PhysicalObject;
import core.track.Track;

/**
 * 计算保存两系统区别
 */
public class Difference {
	private int trackSizeDifference;
	private final Map<Integer, Integer> quantityDifference = new HashMap<>();
	private final Map<Integer, Set<PhysicalObject>> positiveObjectDifference = new HashMap<>();
	private final Map<Integer, Set<PhysicalObject>> negativeObjectDifference = new HashMap<>();
	
	public Difference() {}
	
	public Difference getDifference(CircularOrbit c1, CircularOrbit c2) {
		objectDifference(c1, c2);
		return this;
	}
	
	public int getTrackSizeDifference() { return trackSizeDifference; }
	
	public Map<Integer, Integer> getQuantityDifference() { return quantityDifference; }
	
	public Map<Integer, Set<PhysicalObject>> getPositiveObjectDifference() { return positiveObjectDifference; }
	
	public Map<Integer, Set<PhysicalObject>> getNegativeObjectDifference() { return negativeObjectDifference; }
	
	/**
	 *计算同轨道物体的区别
	 *
	 */
	private void objectDifference(CircularOrbit<CenterObject, PhysicalObject> c1,
	                              CircularOrbit<CenterObject, PhysicalObject> c2) {
		List<Track> tracks_1 = c1.geTracks();
		List<Track> tracks_2 = c2.geTracks();
		int sizeOfTracks_1 = tracks_1.size();
		int sizeOfTracks_2 = tracks_2.size();
		trackSizeDifference = sizeOfTracks_1 - sizeOfTracks_2;
		System.out.println("轨道数差异：" + Math.abs(sizeOfTracks_1 - sizeOfTracks_2));
		if (sizeOfTracks_1 >= sizeOfTracks_2) {
			for (int i = 0; i < sizeOfTracks_2; i++) {
				quantityDifference.put(i + 1,
				                       (c1.getPhysicalObjectsOnTrack(tracks_1.get(i)).size() -
						                       c2.getPhysicalObjectsOnTrack(
								                       tracks_2.get(i)).size()));
				Set<PhysicalObject> objects_1 = new HashSet<>();
				Set<PhysicalObject> objects_2 = new HashSet<>();
				for (PhysicalObject physicalObject_1: c1.getPhysicalObjectsOnTrack(
						tracks_1.get(i))) {
					if (!c2.getPhysicalObjectsOnTrack(tracks_2.get(i)).contains(physicalObject_1)) {
						objects_1.add(physicalObject_1);
					}
					positiveObjectDifference.put(i + 1, objects_1);
				}
				for (PhysicalObject physicalObject_2: c2.getPhysicalObjectsOnTrack(
						tracks_2.get(i))) {
					if (!c1.getPhysicalObjectsOnTrack(tracks_1.get(i)).contains(physicalObject_2)) {
						objects_2.add(physicalObject_2);
					}
					negativeObjectDifference.put(i + 1, objects_2);
				}
				if (objects_1.isEmpty() && objects_2.isEmpty()) {
					System.out.println("轨道" + (i + 1) + "物体差异：" + "无");
				} else {
					System.out.println(
							"轨道" + (i + 1) + "物体差异："  + objects_1.size() + " " + objects_2.size());
				}
			}

		} else {
			for (int i = 0; i < sizeOfTracks_1; i++) {
				quantityDifference.put(i + 1,
				                       (c1.getPhysicalObjectsOnTrack(tracks_1.get(i)).size() -
						                       c2.getPhysicalObjectsOnTrack(
								                       tracks_2.get(i)).size()));
				Set<PhysicalObject> objects_1 = new HashSet<>();
				Set<PhysicalObject> objects_2 = new HashSet<>();
				for (PhysicalObject physicalObject_1: c1.getPhysicalObjectsOnTrack(
						tracks_1.get(i))) {
					if (!c2.getPhysicalObjectsOnTrack(tracks_2.get(i)).contains(physicalObject_1)) {
						objects_1.add(physicalObject_1);
					}
					positiveObjectDifference.put(i + 1, objects_1);
				}
				for (PhysicalObject physicalObject_2: c2.getPhysicalObjectsOnTrack(
						tracks_2.get(i))) {
					if (!c1.getPhysicalObjectsOnTrack(tracks_1.get(i)).contains(physicalObject_2)) {
						objects_2.add(physicalObject_2);
					}
					negativeObjectDifference.put(i + 1, objects_2);
				}
				if (objects_1.isEmpty() && objects_2.isEmpty()) {
					System.out.println("轨道" + (i + 1) + "物体差异：" + "无");
				} else {
					System.out.println(
							"轨道" + (i + 1) + "物体差异："  + objects_1.size() + " " + objects_2.size()
									);
				}
			}

		}
	}
}