package physicalObject;

import static org.junit.Assert.assertArrayEquals;

public class Planet implements PhysicalObject{

	private final String Name;
	private final String State;
    private final String Color;
    private final double Planetradius;
    private final double Trackradius;
    private final double Speed;
    private final String Direction;
    private final double Primaryangle;
   
    private  void checkRep() {
    	assert Name !=null;
    	assert State !=null;
    	assert Color !=null;
    	assert Planetradius >=0;
    	assert Trackradius >=0;
    	assert Speed >=0;
    	assert Direction !=null;
    	assert Primaryangle >=0 &&Primaryangle <=360;
		 
	}
    public Planet() {
    	this.Name = "";
        this.State = "";
        this.Color = "";
        this.Trackradius = 0;
        this.Planetradius = 0;
        this.Speed = 0;
        this.Direction = "";
        this.Primaryangle = 0;
        checkRep();
    }
    
    public Planet(String name,String state,String color, double pRadius, double tRadius, double speed, String direction, double angle){
        this.Name = name;
        this.State = state;
        this.Color = color;
        this.Trackradius = tRadius;
        this.Planetradius = pRadius;
        this.Speed = speed;
        this.Direction = direction;
        this.Primaryangle = angle;
        checkRep();
    }
    
    public String getState() {
    	return State;
    }
    
    public String getColor() {
    	return this.Color;
    }
    
    public double getTrackRadius() {
    	return this.Trackradius;
    }
    
    public double getSpeed() {
    	return this.Speed;
    }
    
    public String getDirection() {
    	return this.Direction;
    }
    
    public boolean isEqual(Planet a) {
    	if(a.Name == this.Name) {
    		System.out.println("same!");
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.Name;
	}

	@Override
	public double getRadius() {
		// TODO Auto-generated method stub
		return this.Planetradius;
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return this.Primaryangle;
	}

}
