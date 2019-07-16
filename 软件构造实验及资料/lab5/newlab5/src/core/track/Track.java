package core.track;
/**
 * a.
 */
public class Track {
    /** a. */
    private final String trackName;
    /** a. */
    private final double trackRadius;
    /**
     * a.
     */
    public Track() {
        this.trackName = "";
        this.trackRadius = 0;
    }
    /**
     * a.
     *
     * @param name   a
     * @param radius a
     */
    public Track(final String name, final double radius) {
        this.trackName = name;
        this.trackRadius = radius;
    }
    /**
     * a.
     *
     * @param name a
     */
    public Track(final String name) {
        this.trackName = name;
        this.trackRadius = 0;
    }
    /**
     * a.
     *
     * @return a
     */
    public String gettrackName() {
        return this.trackName;
    }
    /**
     * a.
     *
     * @return a
     */
    public double gettrackRadius() {
        return this.trackRadius;
    }
//    /**
//     * a.
//     *
//     * @return a
//     */
//    @Override public int hashCode() {
//        return this.trackName.hashCode() ^ (int) this.trackRadius;
//    }
    /**a.
     * @param objects a
     * @return c
     */
    public boolean equals(final Object objects) {
        if (!(objects instanceof Track)) {
            return false;
        }
        Track track = (Track) objects;
        return this.trackRadius == track.trackRadius;
    }
}
