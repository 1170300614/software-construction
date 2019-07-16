package core.physicalObject;

public class Planet implements PhysicalObject {
    /** a. */
    private final String name;
    /** a. */
    private final String state;
    /** a. */
    private final String color;
    /** a. */
    private final double planetradius;
    /** a. */
    private final double trackradius;
    /** a. */
    private final double speed;
    /** a. */
    private final String direction;
    /** a. */
    private final double primaryangle;
    /**a.*/
    private static final int A = 360;
    /**
     * a.
     */
    private void checkRep() {
        assert name != null;
        assert state != null;
        assert color != null;
        assert planetradius >= 0;
        assert trackradius >= 0;
        assert speed >= 0;
        assert direction != null;
        assert primaryangle >= 0 && primaryangle <= A;
    }
    /**
     * a.
     */
    public Planet() {
        this.name = "";
        this.state = "";
        this.color = "";
        this.trackradius = 0;
        this.planetradius = 0;
        this.speed = 0;
        this.direction = "";
        this.primaryangle = 0;
        checkRep();
    }
    /**
     * a.
     *
     * @param names      s
     * @param states    s
     * @param colors    s
     * @param pRadiuss  s
     * @param tRadiuss   s
     * @param speeds     s
     * @param directions s
     * @param angles     s
     */
    public Planet(final String names, final String states, final String colors,
                  final double pRadiuss, final double tRadiuss,
                  final double speeds, final String directions,
                  final double angles) {
        this.name = names;
        this.state = states;
        this.color = colors;
        this.trackradius = tRadiuss;
        this.planetradius = pRadiuss;
        this.speed = speeds;
        this.direction = directions;
        this.primaryangle = angles;
        checkRep();
    }
    /**
     * a.
     *
     * @return a
     */
    public String getState() {
        return state;
    }
    /**
     * a.
     *
     * @return a
     */
    public String getColor() {
        return this.color;
    }
    /**
     * a.
     *
     * @return a
     */
    public double getTrackRadius() {
        return this.trackradius;
    }
    /**
     * a.
     *
     * @return a
     */
    public double getSpeed() {
        return this.speed;
    }
    /**
     * a.
     *
     * @return a
     */
    public String getDirection() {
        return this.direction;
    }
    /**
     * a.
     *
     * @param a a
     * @return a
     */
    public boolean isEqual(final Planet a) {
        if (a.name == this.name) {
            System.out.println("same!");
            return true;
        } else {
            return false;
        }
    }
    /**
     * a.
     *
     * @return a
     */
    @Override public String getName() {
        // TODO Auto-generated method stub
        return this.name;
    }
    /**
     * a.
     *
     * @return a
     */
    @Override public double getRadius() {
        // TODO Auto-generated method stub
        return this.planetradius;
    }
    /**
     * a.
     *
     * @return a
     */
    @Override public double getAngle() {
        // TODO Auto-generated method stub
        return this.primaryangle;
    }
}
