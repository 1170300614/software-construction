package core.centerObject;

public class Stellar implements CenterObject {
    /**
     * a.
     */
    private final String name;
    /** a. */
    private final double weight;
    /** a. */
    private final double radius;
    /**
     * a.
     */
    private void checkRep() {
        assert name != null;
        assert weight >= 0;
        assert radius >= 0;
    }
    /**
     * a.
     */
    public Stellar() {
        // TODO Auto-generated constructor stub
        this.name = "";
        this.radius = 0;
        this.weight = 0;
        checkRep();
    }
    /**
     * a.
     *
     * @param names   a
     * @param radiuss a
     * @param weights a
     */
public Stellar(final String names, final double radiuss, final double weights) {
        this.name = names;
        this.radius = radiuss;
        this.weight = weights;
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
        return this.radius;
    }
    /**
     * a.
     *
     * @param centerObject a a
     * @return a
     */
    @Override public boolean equal(final CenterObject centerObject) {
        // TODO Auto-generated method stub
        return centerObject.getName() == this.name;
    }
    /**
     * a.
     *
     * @return a
     */
    public double getWeight() {
        return this.weight;
    }
}
