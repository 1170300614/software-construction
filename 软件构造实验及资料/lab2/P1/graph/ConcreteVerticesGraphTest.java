/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import P1.graph.*;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void testTostring()
    {
    	Graph<String> graph = emptyInstance();
    	graph.add("a");
    	graph.add("b");
    	graph.add("c");
    	graph.add("d");
    	graph.add("e");
    	graph.set("a", "b", 3);
    	graph.set("a", "d", 2);
    	graph.set("a", "c", 4);
    	graph.set("b", "c", 3);
    	graph.set("c", "d", 2);
    	String xString = "ab3	ac4	ad2	\n" + 
    			"bc3	\n" + 
    			"cd2	\n" + 
    			"\n" + 
    			"\n";
    	assertEquals(xString, graph.toString());
    }

   
    /*
     * Testing Vertex...
     */
    @Test
    public void testVertex() {
		Vertex xVertex = new Vertex();
		xVertex.add("a");
		xVertex.add("b", 4);
		xVertex.add("c", 5);
		xVertex.add("f", 3);
		assertEquals("a", xVertex.getname());
		assertEquals(4, xVertex.getweight("b"));
		xVertex.delete("b");
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("c", 5);
		map.put("f", 3);
		assertEquals(map, xVertex.getMap());
		Set<String> set = new HashSet<String>();
		set.add("c");
		set.add("f");
		assertEquals(set, xVertex.getSet());
	}
    
     
    
    
    
    
    // Testing strategy for Vertex
    //   TODO
    
    // TODO tests for operations of Vertex
   
    
}
