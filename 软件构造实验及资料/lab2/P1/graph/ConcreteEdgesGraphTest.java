/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */


package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;


/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteEdgesGraph.toString()
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   TODO
    
    // TODO tests for operations of Edge
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
    	assertEquals("ab3\n" + 
    			"ad2\n" + 
    			"ac4\n" + 
    			"bc3\n" + 
    			"cd2\n" + 
    			"", graph.toString());
    }
    
    @Test
    public void testedge()
    {
    	Edge xEdge = new Edge("a", "b", 4);
    	assertEquals(4, xEdge.getweight());
    	xEdge.changeweight(6);
    	assertEquals(6, xEdge.getweight());
    	assertEquals("a", xEdge.getsource());
    	assertEquals("b", xEdge.gettarget());
    	
    	assertEquals("ab6", xEdge.toString());
    }
    
    
}
