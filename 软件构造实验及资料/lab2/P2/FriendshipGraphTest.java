package P2;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import P2.FriendshipGraph;
import P2.Person;

public class FriendshipGraphTest {


	FriendshipGraph Graph = new FriendshipGraph();
	Person rachel = new Person("Rachel");
	Person ross = new Person("Ross");
	Person ben = new Person("Ben");
	Person kramer = new Person("Kramer");
	
    @Test(expected=AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}
    
    @Test
	public void addVertexTest()
	{
		Graph.addVertex(rachel);
		Graph.addVertex(ross);
		Graph.addVertex(ben);
		Graph.addVertex(kramer);
		Graph.addVertex(rachel);
		assertTrue(Graph.graph.vertices().contains(rachel));
		assertTrue(Graph.graph.vertices().contains(ross));
		assertTrue(Graph.graph.vertices().contains(ben));
		assertTrue(Graph.graph.vertices().contains(kramer));
		
	}
    
    @Test
    public void addEdgeTest()
    {
    	Graph.addVertex(rachel);
    	Graph.addVertex(ross);
    	Graph.addVertex(ben);
    	Graph.addVertex(kramer);
		Graph.addEdge(rachel, ross);
		Graph.addEdge(ross, rachel);
		Graph.addEdge(ross, ben);
		Graph.addEdge(ben, ross);
		
		Map<Person, Integer> t1 = new HashMap<>();
		Map<Person, Integer> t2 = new HashMap<>();
		
		t1.put(rachel, 1);
		t1.put(ben, 1);
		t2.put(rachel, 1);
		t2.put(ben, 1);
		assertEquals(t1, Graph.graph.targets(ross));
		assertEquals(t2, Graph.graph.sources(ross));
    }
    
    @Test
    public void getDistanceTest()
    {
    	Graph.addVertex(rachel);
    	Graph.addVertex(ross);
    	Graph.addVertex(ben);
    	Graph.addVertex(kramer);
		Graph.addEdge(rachel, ross);
		Graph.addEdge(ross, rachel);
		Graph.addEdge(ross, ben);
		Graph.addEdge(ben, ross);
		Graph.Floyd();
		assertEquals(1, Graph.getDistance(rachel, ross));
		assertEquals(2, Graph.getDistance(rachel, ben));
		assertEquals(0, Graph.getDistance(rachel, rachel));
		assertEquals(-1, Graph.getDistance(rachel, kramer));
    }

}
