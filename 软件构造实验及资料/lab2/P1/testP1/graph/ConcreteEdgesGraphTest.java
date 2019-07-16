/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;


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
        return new ConcreteEdgesGraph<>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
    public void testToStringEmptyGraph(){
        Graph<String> graph = emptyInstance();
        String emptyGraph = "Empty Graph";
        assertTrue("Empty Graph", graph.toString().equals(emptyGraph));
    }
    @Test
    public void testToString(){
        Graph<String> graph = new ConcreteEdgesGraph<>();
        final String source1 = "A1";
        final String target1 = "B1";
        final String source2 = "A2";
        final String target2 = "B2";
        final int weight1 = 1;
        final int weight2 = 2;
        graph.set(source1, target1, weight1);
        graph.set(source2, target2, weight2);
        String regex1 = "(A1,B1,1)";
        String regex2 = "(A2,B2,2)";
        assertEquals("correct",regex1+regex2,graph.toString());
    }
    /*
     * Testing Edge...
     */
    // Testing strategy for Edge
    //   TODO
    
    // TODO tests for operations of Edge
    @Test
    public void testGetSource(){
        final String source = "vertex1";
        Edge<String> edge = new Edge<String>(source, "vertex2", 5);
        assertEquals("Correct", source, edge.getSource()); 
    }
    @Test
    public void testGetTarget(){
        final String target = "vertex2";
        Edge<String> edge = new Edge<String>("vertex1", target, 5);
        assertEquals("correct", target, edge.getTarget());
    }
    @Test
    public void testGetWeight(){
        final int weight = 5;
        Edge<String> edge = new Edge<String>("vertex1", "vertex2", weight);
        assertEquals("Expected correct weight", weight, edge.getWeight());
    }
    @Test
    //covers thisEdge equal to thatEdge
    public void testEdgesEquals(){
        Edge<String> edge1 = new Edge<>("vertex1", "vertex2", 1);
        Edge<String> edge2 = new Edge<>("vertex1", "vertex2", 1);
        Edge<String> edge3 = new Edge<>("vertex1", "vertex3", 1);
        Edge<String> edge4 = new Edge<>("vertex3", "vertex2", 1);
        Edge<String> edge5 = new Edge<>("vertex1", "vertex2", 3);
        assertTrue("Expected egde1 equal to itself", edge1.equals(edge1));
        assertTrue("Expected egde2 equal to itself", edge2.equals(edge2));
        assertTrue("Expected egde1 equal to edge2", edge1.equals(edge2));
        assertTrue("Expected edge2 equal to edge1", edge2.equals(edge1));
        
        assertFalse("Expected edges not equal",edge1.equals(edge3));
        assertFalse("Expected edges not equal",edge1.equals(edge4));
        assertFalse("Expected edges not equal",edge1.equals(edge5));
    }
    @Test
    public void testEdgesHashCode(){
        Edge<String> edge1 = new Edge<String>("vertex1", "vertex2", 1);
        Edge<String> edge2 = new Edge<String>("vertex1", "vertex2", 1);
        Edge<String> edge3 = new Edge<String>("vertex1", "vertex3", 1);
        Edge<String> edge4 = new Edge<String>("vertex3", "vertex2", 1);
        Edge<String> edge5 = new Edge<String>("vertex1", "vertex2", 3);
        
        int code1 = edge1.hashCode();
        int code2 = edge2.hashCode();
        int code3 = edge3.hashCode();
        int code4 = edge4.hashCode();
        int code5 = edge5.hashCode();
        assertEquals("Expected same hashcode",code1,code2);
        assertNotEquals("Expected different hashcode",code1,code3);
        assertNotEquals("Expected different hashcode",code1,code4);
        assertNotEquals("Expected different hashcode",code1,code5);
        
        
    }
}
