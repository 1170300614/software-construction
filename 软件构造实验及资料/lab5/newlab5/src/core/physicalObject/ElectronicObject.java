package core.physicalObject;

public class ElectronicObject implements PhysicalObject {
    /** a. */
    private final String name;
    /** a. */
    private final double angle;
    /**a.*/
    private static final int A = 360;
    /**
     * a.
     */
    private void checkRep() {
        assert name != null;
        assert angle >= 0 && angle <= A;
    }
    /**
     * a.
     */
    public ElectronicObject() {
        // TODO Auto-generated constructor stub
        this.name = "";
        this.angle = 0;
        checkRep();
    }
    /**
     * a.
     *
     * @param names  a
     * @param angles a
     */
    public ElectronicObject(final String names, final double angles) {
        this.name = names;
        this.angle = angles;
        checkRep();
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
        return 0;
    }
    /**
     * a.
     *
     * @return a
     */
    @Override public double getAngle() {
        // TODO Auto-generated method stub
        return this.angle;
    }
    /**
     * a.
     *
     * @return a
     */
    @Override public double getTrackRadius() {
        // TODO Auto-generated method stub
        return 0;
    }
}
