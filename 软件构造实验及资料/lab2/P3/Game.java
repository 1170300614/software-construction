package P3;

public class Game {
	String Name;//游戏名字(确定类比)
	boolean result;//游戏结果
	boolean type;//游戏类型
	
	
	
	public Game(String name) {//初始化
		this.Name = name;
		result = false;
		if(name.equals("chess") || name.equals("Chess"))
			this.type = false;
		else
			this.type = true;
	}
	
	public boolean gettype() {
		return type;
	}
	
	public boolean getresult() {
		return result;
	}
	
}
