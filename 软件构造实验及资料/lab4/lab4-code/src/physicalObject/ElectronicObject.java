package physicalObject;

public class ElectronicObject implements PhysicalObject{
	private final String Name;
	private final double Angle;
	private void checkRep() {
		assert Name != null;
		assert Angle >=0 && Angle <=360;
		
	}
	public ElectronicObject() {
		// TODO Auto-generated constructor stub
		this.Name = "";
		this.Angle = 0;
		checkRep();
	}
	
	public ElectronicObject(String name,double angle) {
		this.Name = name;
		this.Angle = angle;
		checkRep();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.Name;
	}

	@Override
	public double getRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return this.Angle;
	}

	@Override
	public double getTrackRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

}
