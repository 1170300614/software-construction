package P3;

import java.util.ArrayList;
import java.util.List;

public class Player {
	String Name;//玩家的名字
	boolean flag;//玩家类别
	public Player(String name, boolean flag){//初始化
		this.Name = name;
		this.flag = flag;
	}
	
	public String getname() {//获得名字
		return this.Name;
	}
	
	public boolean getflag() {//获得类别
		return this.flag;
	}
}
