package core.circularorbit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import core.APIS.CircularOrbitAPIs;
import core.track.Track;

public class ConcreteCircularOrbit<L, E> extends CircularOrbitAPIs<L, E>
        implements CircularOrbit<L, E> {
    /** a. */
    private List<Track> trackLists = new LinkedList<Track>();
    /** a. */
    private L centerObject = null;
    /** a. */
    private Map<Track, List<E>> physicalMap = new HashMap<Track, List<E>>();
    /** a. */
    private Map<E, List<E>> physicalPhysicalMap = new HashMap<E, List<E>>();
    /** a. */
    private List<E> centerPhysicalRelationLists = new LinkedList<E>();
    /** a. */
    private Set<E> physicalObjects = new HashSet<>();
    /**
     * a.
     *
     * @param track s
     * @return s
     */
    public List<E> getObjectLists(final Track track) {
        return this.physicalMap.get(track);
    }
    /**
     * a.
     *
     * @return s
     */
    public Map<E, List<E>> getPhysicalPhysicalMap() {
        return this.physicalPhysicalMap;
    }
    /**
     * a.
     *
     * @return a
     */
    public List<E> getCenterPhysicalRelationLists() {
        return centerPhysicalRelationLists;
    }
    /**
     * a.
     *
     * @param o a
     */
    public void addCenterPhysicalRelation(final E o) {
        this.centerPhysicalRelationLists.add(o);
    }
    /**
     * a.
     *
     * @param o a
     */
    public void removeCenterPhysicalRelation(final E o) {
        this.centerPhysicalRelationLists.remove(o);
    }
    /**
     * a.
     *
     * @param e1 a
     * @param e2 a
     */
    public void addPhyPhyRelation(final E e1, final E e2) {
        if (this.physicalPhysicalMap.containsKey(e1)) {
            this.physicalPhysicalMap.get(e1).add(e2);
        } else {
            this.physicalPhysicalMap.put(e1, new LinkedList<E>());
            this.physicalPhysicalMap.get(e1).add(e2);
        }
        if (this.physicalPhysicalMap.containsKey(e2)) {
            this.physicalPhysicalMap.get(e2).add(e1);
        } else {
            this.physicalPhysicalMap.put(e2, new LinkedList<E>());
            this.physicalPhysicalMap.get(e2).add(e1);
        }
    }
    /**
     * a.
     *
     * @param e1 a
     * @param e2 a
     */
    public void removePhyPhyRelation(final E e1, final E e2) {
        if (this.physicalPhysicalMap.containsKey(e1)
            &&
            this.physicalPhysicalMap.get(e1).contains(e2)) {
            this.physicalPhysicalMap.get(e1).remove(e2);
        }
        if (this.physicalPhysicalMap.containsKey(e2)
            &&
            this.physicalPhysicalMap.get(e2).contains(e1)) {
            this.physicalPhysicalMap.get(e2).remove(e1);
        }
    }
    /**
     * a.
     *
     * @param track a a
     */
    @Override public void addTrack(final Track track) {
        // TODO Auto-generated method stub
        this.trackLists.add(track);
        physicalMap.put(track, new LinkedList<E>());
    }
    /**
     * a.
     *
     * @param index a
     * @param track a
     */
    public void addTrack(final int index, final Track track) {
        this.trackLists.add(index, track);
        physicalMap.put(track, new LinkedList<E>());
    }
    /**
     * a.
     *
     * @return a
     */
    public int getTrackNum() {
        return this.trackLists.size();
    }
    /**
     * a.
     *
     * @param i a
     */
    public void deleteTrack(final int i) {
        // TODO Auto-generated method stub
        this.physicalMap.remove(this.trackLists.get(i));
        this.trackLists.remove(i);
    }
    /**
     * a.
     *
     * @param track a a
     */
    @Override public void deleteTrack(final Track track) {
        // TODO Auto-generated method stub
        int a = getPhysicalObjectsOnTrack(track).size();
        System.out.println(a);
        if (a != 0) {
            System.out.println();
            for (int i = 0; i < a; i++) {
                removeOnTrack(track, this.physicalMap.get(track).get(0));
            }
        }
        this.physicalMap.remove(track);
        this.trackLists.remove(track);
    }
    /**
     * a.
     *
     * @param l a a
     */
    @Override public void addCenter(final L l) {
        // TODO Auto-generated method stub
        this.centerObject = l;
    }
    /**
     * a.
     *
     * @param track a
     * @param o     a
     */
    @Override public void addOnTrack(final Track track, final E o) {
        if (!physicalMap.get(track).contains(o)) {
            physicalMap.get(track).add(o);
        }
    }
    /**
     * a.
     *
     * @param track a
     * @param o     a
     * @return a
     */
    @Override public boolean removeOnTrack(final Track track, final E o) {
        if (this.physicalPhysicalMap != null
            &&
            this.physicalPhysicalMap.get(o) != null) {
            for (E e: this.physicalPhysicalMap.get(o)) {
                if (this.physicalPhysicalMap.get(e).size() != 0) {
                    this.physicalPhysicalMap.get(e).remove(o);
                }
            }
        }
        if (this.physicalPhysicalMap != null
            &&
            this.physicalPhysicalMap.containsKey(o)) {
            this.physicalPhysicalMap.remove(o);
        }
        if (this.centerPhysicalRelationLists.contains(o)) {
            this.centerPhysicalRelationLists.remove(o);
        }
        return this.physicalMap.get(track).remove(o);
    }
    /**
     * a.
     *
     * @return a
     */
    @Override public List<Track> geTracks() {
        // TODO Auto-generated method stub
        return this.trackLists;
    }
    /**
     * a.
     *
     * @return a
     */
    @Override public L getCenter() {
        // TODO Auto-generated method stub
        return this.centerObject;
    }
    /**
     * a.
     */
    @Override public void removeCenter() {
        // TODO Auto-generated method stub
        centerObject = null;
        this.centerPhysicalRelationLists.clear();
    }
    /**
     * a.
     *
     * @return a
     */
    @Override public Map<Track, List<E>> getPhysicalMap() {
        // TODO Auto-generated method stub
        return this.physicalMap;
    }
    /**
     * a.
     *
     * @return a
     */
    @Override public Set<E> getPhysicalObjects() {
        // TODO Auto-generated method stub
        for (Track track: this.geTracks()) {
            for (E e: this.getObjectLists(track)) {
                this.physicalObjects.add(e);
            }
        }
        return this.physicalObjects;
    }
    /**
     * a.
     *
     * @param track a
     * @return a
     */
    @Override public List<E> getPhysicalObjectsOnTrack(final Track track) {
        // TODO Auto-generated method stub
        return this.physicalMap.get(track);
    }
    /**
     * a.
     *
     * @param e a
     * @return a
     */
    @Override public List<E> getRelatedPhysicalObjects(final E e) {
        // TODO Auto-generated method stub
        return this.physicalPhysicalMap.get(e);
    }
    /**
     * a.
     *
     * @param e a
     * @return a
     */
    @Override public double getPhyTrackRadius(final E e) {
        // TODO Auto-generated method stub
        for (Track a: this.geTracks()) {
            if (this.physicalMap.get(a).contains(e)) {
                return a.gettrackRadius();
            }
        }
        return -1;
    }
    /**
     * a.
     *
     * @param e c
     * @return a
     */
    public Track getObjectTrack(final E e) {
        for (Track track: geTracks()) {
            if (getPhysicalObjectsOnTrack(track).contains(e)) {
                return track;
            }
        }
        return null;
    }
}
