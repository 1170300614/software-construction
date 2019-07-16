package core.physicalObject;

public class App implements PhysicalObject {
	private final String Name;
	private final String corporation;
	private final String version;
	private final String feature;
	private final String businessArea;
	private final double azimuth;
	
	
	private void checkRep() {
		assert this.Name != null;
		assert this.corporation != null;
		assert this.version != null;
		assert this.feature != null;
		assert this.businessArea != null;
		assert azimuth >= 0 && azimuth <= 360;
	}
	
	public App() {
		this.Name = "default";
		this.corporation = "default";
		this.version = "default";
		this.feature = "default";
		this.businessArea = "default";
		this.azimuth = 0;
		checkRep();
	}
	
	public App(String name, String corporation, String version, String feature,
	           String businessArea,
	           double azimuth) {
		this.Name = name;
		this.corporation = corporation;
		this.version = version;
		this.feature = feature;
		this.businessArea = businessArea;
		this.azimuth = azimuth;
		checkRep();
	}
	
	
	public String getCorporation() { return corporation; }
	
	public String getVersion() { return version; }
	
	public String getFeature() { return feature; }
	
	public String getBusinessArea() { return businessArea; }
	
	public double getAzimuth() { return azimuth; }
	

	
	@Override public int hashCode() {
		return Name.hashCode() ^ corporation.hashCode() ^ version.hashCode() ^ feature.hashCode() ^
				businessArea.hashCode();
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
		return 0;
	}

	@Override
	public double getTrackRadius() {
		// TODO Auto-generated method stub
		return 0;
	}
}