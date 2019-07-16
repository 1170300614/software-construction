package P2;

import java.util.HashMap;
import java.util.Map;

public class Person {
	String Name;//名字
	int Index;//下标
	
	public Person(String name)
	{
		this.Name = name;
	}
	
	
	public void setindex(int index) {
		this.Index = index;
	}
	
	public int getindex() {
		return Index;
	}
}
