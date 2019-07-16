package circularorbit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import APIS.CircularOrbitAPIs;
import track.Track;

public class ConcreteCircularOrbit<L, E> extends CircularOrbitAPIs<L, E> implements CircularOrbit<L, E> {
	private List<Track> TrackLists = new LinkedList<Track>();
	private L CenterObject = null;
	private Map<Track, List<E>> PhysicalMap = new HashMap<Track, List<E>>();
	private Map<E, List<E>> physicalPhysicalMap = new HashMap<E, List<E>>();
	private List<E> CenterPhysicalRelationLists = new LinkedList<E>();
	protected Set<E> physicalObjects = new HashSet<>();

	public List<E> getObjectLists(Track track) {
		return this.PhysicalMap.get(track);
	}

	public Map<E, List<E>> getPhysicalPhysicalMap() {
		return this.physicalPhysicalMap;
	}

	public List<E> getCenterPhysicalRelationLists() {
		return CenterPhysicalRelationLists;
	}

	public void addCenterPhysicalRelation(E o) {
		this.CenterPhysicalRelationLists.add(o);
	}

	public void removeCenterPhysicalRelation(E o) {
		this.CenterPhysicalRelationLists.remove(o);

	}

	public void addPhyPhyRelation(E e1, E e2) {
		if (this.physicalPhysicalMap.containsKey(e1))
			this.physicalPhysicalMap.get(e1).add(e2);
		else {
			this.physicalPhysicalMap.put(e1, new LinkedList<E>());
			this.physicalPhysicalMap.get(e1).add(e2);
		}
		if (this.physicalPhysicalMap.containsKey(e2))
			this.physicalPhysicalMap.get(e2).add(e1);
		else {
			this.physicalPhysicalMap.put(e2, new LinkedList<E>());
			this.physicalPhysicalMap.get(e2).add(e1);
		}

	}

	public void removePhyPhyRelation(E e1, E e2) {
		if (this.physicalPhysicalMap.containsKey(e1) && this.physicalPhysicalMap.get(e1).contains(e2))
			this.physicalPhysicalMap.get(e1).remove(e2);
		if (this.physicalPhysicalMap.containsKey(e2) && this.physicalPhysicalMap.get(e2).contains(e1))
			this.physicalPhysicalMap.get(e2).remove(e1);
	}

	@Override
	public void addTrack(Track track) {
		// TODO Auto-generated method stub
		this.TrackLists.add(track);
		PhysicalMap.put(track, new LinkedList<E>());
//		//每次加入都排序，使列表有序化

	}

	public void addTrack(int index, Track track) {
		this.TrackLists.add(index, track);
		PhysicalMap.put(track, new LinkedList<E>());
	}

	public int getTrackNum() {
		return this.TrackLists.size();
	}

	public void deleteTrack(int i) {
		// TODO Auto-generated method stub
		this.PhysicalMap.remove(this.TrackLists.get(i));
		this.TrackLists.remove(i);
	}

	@Override
	public void deleteTrack(Track track) {
		// TODO Auto-generated method stub
		int a = getPhysicalObjectsOnTrack(track).size();
		System.out.println(a);
		if(a!=0) {
			System.out.println();
			for(int i = 0;i < a;i++) {
				
				removeOnTrack(track, this.PhysicalMap.get(track).get(0));
			}
		}
		this.PhysicalMap.remove(track);
		this.TrackLists.remove(track);
	}

	@Override
	public void addCenter(L l) {
		// TODO Auto-generated method stub
		this.CenterObject = l;
	}

	@Override
	public void addOnTrack(Track track, E o) {
		// TODO Auto-generated method stub
//		PhysicalMap.get(track).add(o);
		if (!PhysicalMap.get(track).contains(o)) {
			PhysicalMap.get(track).add(o);
		}
	}

	@Override
	public boolean removeOnTrack(Track track, E o) {
		if (this.physicalPhysicalMap!=null && this.physicalPhysicalMap.get(o)!=null)
			for (E e : this.physicalPhysicalMap.get(o)) {
				if (this.physicalPhysicalMap.get(e).size() != 0)
					this.physicalPhysicalMap.get(e).remove(o);
			}
		if(this.physicalPhysicalMap!=null && this.physicalPhysicalMap.containsKey(o)) this.physicalPhysicalMap.remove(o);
		if(this.CenterPhysicalRelationLists.contains(o)) {
			this.CenterPhysicalRelationLists.remove(o);
		}
//		this.physicalPhysicalMap.remove(o);
		return this.PhysicalMap.get(track).remove(o);

	}

	@Override
	public List<Track> geTracks() {
		// TODO Auto-generated method stub
		return this.TrackLists;
	}

	@Override
	public L getCenter() {
		// TODO Auto-generated method stub
		return this.CenterObject;
	}

	@Override
	public void removeCenter() {
		// TODO Auto-generated method stub
		CenterObject = null;
		this.CenterPhysicalRelationLists.clear();
	}

	@Override
	public Map<Track, List<E>> getPhysicalMap() {
		// TODO Auto-generated method stub
		return this.PhysicalMap;
	}

	@Override
	public Set<E> getPhysicalObjects() {
		// TODO Auto-generated method stub
		for (Track track : this.geTracks()) {
			for (E e : this.getObjectLists(track)) {
				this.physicalObjects.add(e);
			}
		}
		return this.physicalObjects;
	}

	@Override
	public List<E> getPhysicalObjectsOnTrack(Track track) {
		// TODO Auto-generated method stub
		return this.PhysicalMap.get(track);
	}

	@Override
	public List<E> getRelatedPhysicalObjects(E e) {
		// TODO Auto-generated method stub
		return this.physicalPhysicalMap.get(e);
	}

	@Override
	public double getPhyTrackRadius(E e) {
		// TODO Auto-generated method stub
		for (Track a : this.geTracks()) {
			if (this.PhysicalMap.get(a).contains(e))
				;
			return a.getTrackRadius();
		}
		return -1;
	}

	public Track getObjectTrack(E e) {
		for(Track track:geTracks()) {
			if(getPhysicalObjectsOnTrack(track).contains(e)) {
				return track;
			}
		}
		return null;
	}


}
