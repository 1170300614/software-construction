package centerObject;

public class Stellar implements CenterObject{

	private final String Name;
	private final double Weight;
	private final double Radius;
	
	private void checkRep() {
		assert Name !=null;
		assert Weight >=0;
		assert Radius >=0;
 		
	}
	public Stellar() {
		// TODO Auto-generated constructor stub
		this.Name = "";
		this.Radius = 0;
		this.Weight = 0;
		checkRep();
	}
	
	public Stellar(String name,double radius,double weight) {
		this.Name = name;
		this.Radius = radius;
		this.Weight = weight;
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
		return this.Radius;
	}

	@Override
	public boolean equal(CenterObject centerObject) {
		// TODO Auto-generated method stub
		return centerObject.getName() == this.Name;
	}

	public double getWeight() {
		return this.Weight;
	}
}
