package P3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChessGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("输入棋的名字: ");
		Scanner in = new Scanner(System.in);
		String choice = in.next();
		Game f = new Game(choice);
		
		System.out.println("输入两名玩家的名字: ");
		String name1 = in.next();
		String name2 = in.next();
		Player player1 = new Player(name1, true);
		Player player2 = new Player(name2, false);
		
		Action A = new Action();
		
		
		if(!f.gettype())//国际象棋
		{
			Board b = new Board(8,8);
			ArrayList<Piece> p = new ArrayList<>();
			//棋子初始化
			for(int i = 0; i < 16; i++)
			{
				if(i < 8)
					A.put(i, 1, true, "P", p);
				else
					A.put(i, 6 , false, "P", p);
			}
			for(int i = 0; i < 2; i++)
			{
				int t = 0;
				boolean flag = true;
				if(i > 0)
				{
					t = 7;
					flag = false;
				}
				A.put(0, t , flag, "R", p);
				A.put(7, t , flag, "R", p);
				A.put(1, t , flag, "N", p);
				A.put(6, t , flag, "N", p);
				A.put(2, t , flag, "B", p);
				A.put(5, t , flag, "B", p);
				A.put(3, t , flag, "Q", p);
				A.put(4, t , flag, "K", p);
			}
			
			int t = 0;
			System.out.println("end");//结束
			System.out.println("move");//移子
			System.out.println("eat");//吃子
			System.out.println("position");//查询目标位置是否空闲
			System.out.println("sum");//计算玩家棋子总数
			
			do
			{
				int x1, x2, y1, y2;
				boolean flag = false;
				if(t%2 == 0)
				{	
					flag = true;
					System.out.println("请" + player1.getname() + "进行操作:");
				}
				else
					System.out.println("请" + player2.getname() + "进行操作:");
				
				String lp = in.next();
				
				if(lp.equals("end"))
					break;
				else if(lp.equals("move"))
				{
					x1 = in.nextInt();
					y1 = in.nextInt();
					x2 = in.nextInt();
					y2 = in.nextInt();
					if(!A.Chessmove(p, flag, x1, y1, x2, y2))
					{
						System.out.println("本次操作出错，请重新进行操作!");
						continue;
					}
				}
				else if(lp.equals("eat"))
				{
					x1 = in.nextInt();
					y1 = in.nextInt();
					x2 = in.nextInt();
					y2 = in.nextInt();
					if(!A.Chesseat(p, flag, x1, y1, x2, y2))
					{
						System.out.println("本次操作出错，请重新进行操作!");
						continue;
					}
				}
				else if(lp.equals("position"))
				{
					x1 = in.nextInt();
					y1 = in.nextInt();
					if(!A.theposition(p, flag, x1, y1))
					{
						System.out.println("本次操作出错，请重新进行操作!");
						continue;
					}
				}
				else if(lp.equals("sum"))
					A.thesum(p);
				else
				{
					System.out.println("本次操作出错，请重新进行操作!");
					continue;
				}
				
				t++;
			}while(true);
		}
		else//围棋
		{
			Board b = new Board(18,18);
			ArrayList<Piece> p = new ArrayList<>();
			Action B = new Action();
			
			int t = 0;
			System.out.println("end");//结束
			System.out.println("put");//放子
			System.out.println("eat");//吃子
			System.out.println("position");//查询目标位置是否空闲
			System.out.println("sum");//计算玩家棋子总数
			
			do
			{
				int x, y;
				boolean flag = false;
				if(t%2 == 0)
				{	
					flag = true;
					System.out.println("请" + player1.getname() + "进行操作:");
				}
				else
					System.out.println("请" + player2.getname() + "进行操作:");
				
				String lp = in.next();
				
				if(lp.equals("end"))
					break;
				else if(lp.equals("put"))
				{
					x = in.nextInt();
					y = in.nextInt();
					if(!B.put(x, y, flag, "1", p))
					{
						System.out.println("本次操作出错，请重新进行操作!");
						continue;
					}
				}
				else if(lp.equals("eat"))
				{
					System.out.println("请先放子");
					x = in.nextInt();
					y = in.nextInt();
					if(!B.put(x, y, flag, "1", p))
					{
						System.out.println("本次操作出错，请重新进行操作!");
						continue;
					}
					
					System.out.println("开始提子(输入提子数量,如果中间输入错误可继续输入)");
					
					int n = in.nextInt(); 
					
					for(int i = 0; i < n;)
					{
						if(B.Goeat(p, flag, x, y))
							i++;
						else
							System.out.println("本次输入错误，请重新输入");
					}
				}
				else if(lp.equals("position"))
				{
					x = in.nextInt();
					y = in.nextInt();
					if(!B.theposition(p, flag, x, y))
					{
						System.out.println("本次操作出错，请重新进行操作!");
						continue;
					}
				}
				else if(lp.equals("sum"))
					B.thesum(p);
				else
				{
					System.out.println("本次操作出错，请重新进行操作!");
					continue;
				}
				t++;
			}while(true);
		}
		System.out.println("游戏结束! 再见");
	}

}
