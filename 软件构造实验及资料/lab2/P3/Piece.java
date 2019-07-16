package P3;

public class Piece {//棋子
	Position pos;//位置
	boolean flag;//棋手类别(true代表player1,false代表player2)
	String kind;//种类(针对国际象棋)
	
	public Piece(int x, int y, boolean flag, String kind){//初始化
		this.pos = new Position(x, y);
		this.flag = flag;
		this.kind = kind;
	}
	
	public Position getposition() {//获得棋子的位置
		return this.pos;
	}
	
	public boolean getflag() {//获得棋手类别
		return flag;
	}
	
	public String getkind(){//获得类别
		return this.kind;
	}

}
