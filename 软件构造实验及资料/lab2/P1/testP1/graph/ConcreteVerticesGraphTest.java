/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

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
        return new ConcreteVerticesGraph<>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void testtoString() {
    	Graph<String> graph = new ConcreteVerticesGraph<>();
    	final String source1 = "A1";
        final String target1 = "B1";
        final String source2 = "A2";
        final String target2 = "B2";
        final int weight1 = 1;
        final int weight2 = 2;
        graph.set(source1, target1, weight1);
        //graph.set(source1, target2, weight1);
        graph.set(source2, target2, weight2);
        String regex1 = "A1:B1";
       // String regex2 = "A2:B2";
        assertEquals("correct",regex1,graph.toString());
        //assertEquals("correct",regex2,graph.toString());
    }
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO
    
    // TODO tests for operations of Vertex
    
}
