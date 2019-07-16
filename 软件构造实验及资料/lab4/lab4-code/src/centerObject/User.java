package centerObject;

/**
 * The center object of the personal app ecosystem
 */
public class User implements CenterObject{
	private final String Name;
	
	
	private void checkRep() { 
		assert Name != null;
		}
	
	public User(){ 
		this.Name = "default";
		checkRep();
		}
	
	public User(String name) {
		this.Name = name;
		checkRep();
	}
	
	@Override
	public int hashCode() { return Name.hashCode(); }

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
	public boolean equal(CenterObject centerObject) {
		// TODO Auto-generated method stub
		return this.Name == centerObject.getName();
	}
}