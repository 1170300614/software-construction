package P2;

import java.util.ArrayList;
import java.util.List;

import P1.graph.ConcreteEdgesGraph;

public class FriendshipGraph {
	ConcreteEdgesGraph<Person> graph = new ConcreteEdgesGraph<Person>();
	int[][] A = new int[10000][10000];
	
	final int Max = 1000;
	public void addVertex(Person p) {//加点
		int tp = graph.vertices().size();
		if(!graph.add(p))
		{
			System.out.println("已存在");
			return;
		}
		p.setindex(tp);
	}
	
	public void addEdge(Person p1, Person p2) {
		graph.set(p1, p2, 1);
		A[p1.getindex()][p2.getindex()] = 1;
	}
	
	public void Floyd() {//Floyd算法
		int tp = graph.vertices().size();
		for(int i = 0; i < tp; i++)//初始化
		{
			for(int j = 0; j < tp; j++)
			{
				if(A[i][j] != 1)
					A[i][j] = Max;
			}
		}
		
		for(int i = 0; i < tp; i++)
		{
			for(int j = 0; j < tp; j++)
			{
				for(int k = 0; k < tp; k++)
				{
					if(A[i][k] + A[k][j] < A[i][j])
						A[i][j] = A[i][k] + A[k][j];
				}
			}
		}
	}
	
	public int getDistance(Person p1, Person p2) {
		int a = p1.getindex(), b = p2.getindex();
		if (a == b)
			return 0;
		if(A[a][b] == Max)
			return -1;
		return A[a][b];
	}
	
	public static void main(String[] args) 
	{
		FriendshipGraph graph = new FriendshipGraph();
		Person L = new Person("liuhao");
		Person Y = new Person("yanfeng");
		Person G = new Person("guoyue");
		Person H = new Person("haha");
		graph.addVertex(L);
		graph.addVertex(Y);
		graph.addVertex(G);
		graph.addVertex(H);
		graph.addEdge(L, H);
		graph.addEdge(L, Y);
		graph.addEdge(H, G);
		graph.addEdge(G, Y);
		graph.Floyd();
		System.out.println(graph.getDistance(L, L)); 
		System.out.println(graph.getDistance(L, Y)); 
		System.out.println(graph.getDistance(H, L)); 
		System.out.println(graph.getDistance(G, L)); 

	}
	

}
