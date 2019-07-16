package P3;

public class Position {
	int x;//横坐标
	int y;//纵坐标
	
	public Position(int x, int y) {//初始化
		this.x = x;
		this.y = y;
	}
	
	public int getx() {//获得横坐标
		return x;
	}
	
	public int gety() {//获得纵坐标
		return y;
	}
	
	public void setposition(int x, int y) {//修改位置
		this.x = x;
		this.y = y;
	}
}
