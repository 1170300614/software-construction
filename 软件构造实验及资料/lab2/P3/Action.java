package P3;

import java.util.ArrayList;

public class Action {
	
	public boolean put(int x, int y, boolean flag, String name, ArrayList<Piece> p) {//放子
		if(x > 18 || x < 0 || y > 18 || y < 0)//超出界限
		{
			System.out.println("超出棋盘范围");
			return false;
		}
		for(Piece i : p)
		{
			if(i.getposition().getx() == x && i.getposition().gety() == y)
			{
				System.out.println("该位置已存在棋子");
				return false;
			}
		}
		
		Piece t = new Piece(x, y, flag, name);
		p.add(t);
		return true;
	}
	
	public boolean Chessmove(ArrayList<Piece> p, boolean flag, int x1, int y1, int x2, int y2) {//国际象棋
		if(x1 > 7 || x1 < 0 || y1 > 7 || y1 < 0 || x2 > 7 || x2 < 0 || y2 > 7 || y2 < 0)//超出范围
		{
			System.out.println("输入棋子位置超出棋盘范围");
			return false;
		}
		if(x1 == x2 && y1 == y2)//两个位置相同
		{
			System.out.println("输入的两个位置相同");
			return false;
		}
		boolean flag1 = false;
		int t = 0,  temp = 0;
		for(Piece i : p)
		{
			if(i.getposition().getx() == x2 && i.getposition().gety() == y2)//目的地已有其他棋子
			{
				System.out.println("目的地已有其他棋子");
				return false;
			}
			if(i.getposition().getx() == x1 && i.getposition().gety() == y1 && i.getflag() == flag)//初始位置有棋子
			{
				flag1 =  true;
				temp = t;
			}
			t++;
		}
		if(flag1)
		{
			p.get(temp).getposition().setposition(x2, y2);
			return true;
		}
		System.out.println("初始位置不存在该棋手的棋子");
		return false;
	}
	
	public boolean Goeat(ArrayList<Piece> p, boolean flag, int x, int y) {//围棋提子
		if(x < 0 || x > 18 || y < 0 || y > 18)//提子超过棋盘范围
		{
			System.out.println("输入棋子位置超出棋盘范围");
			return false;
		}
		for(Piece i : p)
		{
			if(i.getposition().getx() == x && i.getposition().gety() == y && flag != i.getflag())//该位置存在对手棋子
				return true;
		}
		System.out.println("输入位置不存在对方棋子");
		return false;
	}
	
	public boolean Chesseat(ArrayList<Piece> p, boolean flag, int x1, int y1, int x2, int y2) {//国际象棋吃子
		if(x1 > 7 || x1 < 0 || y1 > 7 || y1 < 0 || x2 > 7 || x2 < 0 || y2 > 7 || y2 < 0)//超出范围
		{
			System.out.println("输入棋子位置超出棋盘范围");
			return false;
		}
		if(x1 == x2 && y1 == y2)
		{
			System.out.println("输入的两个位置相同");
			return false;
		}
		boolean flag1 = false, flag2 = false;
		int t1 = 0, t2 = 0;
		for(Piece i : p)
		{
			if(i.getposition().getx() == x1 && i.getposition().gety() == y1)
				flag1 = true;
			if(i.getposition().getx() == x2 && i.getposition().gety() == y2)
				flag2 = true;
			t1++;
			t2++;
		}
		if(flag1 && flag2 && p.get(t1).flag != p.get(t2).flag)//第一、二位置存在棋子，并且棋子不是属于一个玩家
		{
			p.remove(t2);
			p.get(t1).getposition().setposition(x2, y2);
			return true;
		}
		return false;	
	}
	
	public boolean theposition(ArrayList<Piece> p, boolean flag1, int x, int y) {//查询该位置信息
		if(flag1 &&(x > 7 || x < 0 || y > 7 || y < 0))
		{
			System.out.println("输入棋子位置超出棋盘范围");
			return false;
		}
		if(!flag1 &&(x > 18 || x < 0 || y > 18 || y < 0))
		{
			System.out.println("输入棋子位置超出棋盘范围");
			return false;
		}
		
		for(Piece i : p)
		{
			if(i.getposition().getx() == x && i.getposition().gety() == y)
			{
				if(i.getflag())
					System.out.println("该位置已存在player1的棋子");
				else
					System.out.println("该位置已存在player2的棋子");
				return false;
			}
		}
		return true;
	}
	
	public void thesum(ArrayList<Piece> p) {//查询数量
		int sum1 = 0, sum2 = 0;
		for(Piece i : p)
		{
			if(i.getflag())
				sum1++;
			else
				sum2++;
		}
		System.out.println("player1棋子个数" + sum1);
		System.out.println("player2棋子个数" + sum2);
	}
	
}
