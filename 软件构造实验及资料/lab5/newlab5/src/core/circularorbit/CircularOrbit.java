package core.circularorbit;

import java.util.List;
import java.util.Map;
import java.util.Set;

import core.track.Track;

public interface CircularOrbit<L, E> {
    /**
     * a.
     *
     * @param <L> a
     * @param <E> a
     * @return a
     */
    static <L, E> CircularOrbit<L, E> empty() {
        return new ConcreteCircularOrbit<>();
    }
    /**
     * a.
     *
     * @return a
     */
    List<Track> geTracks();
    /**
     * a.
     *
     * @return a
     */
    L getCenter();
    /**
     * a.
     *
     * @return a
     */
    Map<Track, List<E>> getPhysicalMap();
    /**
     * a.
     *
     * @param track a
     */
    void addTrack(Track track);
    /**
     * a.
     *
     * @param track a
     */
    void deleteTrack(Track track);
    /**
     * a.
     *
     * @param l a
     */
    void addCenter(L l);
    /**
     * a.
     */
    void removeCenter();
    /**
     * a.
     *
     * @param track a
     * @param o     a
     */
    void addOnTrack(Track track, E o);
    /**
     * a.
     *
     * @param track a
     * @param o     a
     * @return a
     */
    boolean removeOnTrack(Track track, E o);
    /**
     * a.
     *
     * @return a
     */
    Set<E> getPhysicalObjects();
    /**
     * a.
     *
     * @param track a
     * @return a
     */
    List<E> getPhysicalObjectsOnTrack(Track track);
    /**
     * a.
     *
     * @param e a
     * @return a
     */
    List<E> getRelatedPhysicalObjects(E e);
    /**
     * a.
     *
     * @param e a
     * @return a
     */
    double getPhyTrackRadius(E e);
}
