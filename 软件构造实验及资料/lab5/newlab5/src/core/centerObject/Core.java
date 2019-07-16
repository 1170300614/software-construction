package core.centerObject;

public class Core implements CenterObject {
    /** a. */
    private final String name;
    /** a. */
    private final int zhizishu;
    /** a. */
    private final int zhongzishu;
    /**
     * a.
     *
     * @param names a
     * @param a     a
     * @param b     a
     */
    public Core(final String names, final int a, final int b) {
        this.name = names;
        this.zhizishu = a;
        this.zhongzishu = b;
    }
    /**
     * a.
     */
    private void checkRep() {
        assert name != null;
        assert zhizishu >= 0;
        assert zhongzishu >= 0;
    }
    /**
     * a.
     *
     * @param names a
     */
    public Core(final String names) {
        this.name = names;
        this.zhizishu = 0;
        this.zhongzishu = 0;
        checkRep();
    }
    /**
     * a.
     */
    public Core() {
        this.name = "";
        this.zhizishu = 0;
        this.zhongzishu = 0;
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
    public int getZhizishu() {
        return this.zhizishu;
    }
    /**
     * a.
     *
     * @return a
     */
    public int getZhongzishu() {
        return this.zhongzishu;
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
     * @param centerObject a a
     * @return a
     */
    @Override public boolean equal(final CenterObject centerObject) {
        // TODO Auto-generated method stub
        return this.name == centerObject.getName();
    }
}
