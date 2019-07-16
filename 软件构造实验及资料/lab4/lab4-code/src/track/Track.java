package track;


public class Track {
	private final String TrackName;
	private final double TrackRadius;
	
	public Track() {
		this.TrackName = "";
		this.TrackRadius = 0;
	}
	
	public Track(String name,double radius) {
		this.TrackName = name;
		this.TrackRadius = radius;
	}
	
	public Track(String name) {
		this.TrackName = name;
		this.TrackRadius = 0;
	}
	public String getTrackName() {
		return this.TrackName;
	}
	
	public double getTrackRadius() {
		return this.TrackRadius;
	}
	
	@Override
	public int hashCode() {
		return this.TrackName.hashCode() ^ (int) this.TrackRadius;
	}
	
	public boolean equals(Object object) {
		if(!( object instanceof Track)) {
			return false;
		}
		Track track = (Track) object;
		return this.TrackRadius == track.TrackRadius;
	}
}
