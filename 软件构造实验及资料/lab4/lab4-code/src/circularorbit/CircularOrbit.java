package circularorbit;

import java.util.List;
import java.util.Map;
import java.util.Set;

import track.Track;

public interface CircularOrbit<L,E> {
	//设置接口实现中心轨道
	static <L,E> CircularOrbit<L,E> empty(){
		return new ConcreteCircularOrbit<>();
	}
	public List<Track> geTracks();
	public L getCenter();
	public Map<Track, List<E>> getPhysicalMap();
	public void addTrack(Track track);
	public void deleteTrack(Track track);
	public void addCenter(L l);
	public void removeCenter();
	public void addOnTrack(Track track,E o);
	public boolean removeOnTrack(Track track,E o);
	public Set<E> getPhysicalObjects();
	public List<E> getPhysicalObjectsOnTrack(Track track);
	public List<E> getRelatedPhysicalObjects(E e);
	public double getPhyTrackRadius(E e);

}
