package centerObject;

import static org.junit.Assert.assertArrayEquals;

public class Core implements CenterObject{
	private final String Name;
	private final int zhizishu;
	private final int zhongzishu;
	public Core(String name,int a,int b) {
		this.Name = name;
		this.zhizishu = a;
		this.zhongzishu = b;
	}
	private void checkRep() {
		assert Name != null;
		assert zhizishu >=0;
		assert zhongzishu >=0;
	}
	
	public Core(String name) {
		this.Name = name;
		this.zhizishu = 0;
		this.zhongzishu = 0;
		checkRep();
	}
	public Core() {
		this.Name = "";
		this.zhizishu = 0;
		this.zhongzishu = 0;
		checkRep();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.Name;
	}

	public int getZhizishu() {
		return this.zhizishu;
	}
	
	public int getZhongzishu() {
		return this.zhongzishu;
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
