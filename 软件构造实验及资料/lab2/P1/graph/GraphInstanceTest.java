/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   TODO 
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    @Test
    public void testAddVertices() { 
        Graph<String> graph = emptyInstance();
        assertEquals(true, graph.add("a1"));
        assertEquals(true, graph.add("a2"));
        assertEquals(false, graph.add("a1"));
        assertEquals(false, graph.add("a2"));
    }
    @Test
    public void testsetVertices() {
    	Graph<String> graph = emptyInstance();//edges seem like to have problems for setting for more than twice
    	assertEquals(0, graph.set("a1", "a2", 3));
    	assertEquals(0, graph.set("a2", "a3", 2));
    	assertEquals(2, graph.set("a2", "a3", 1));
    	assertEquals(3, graph.set("a1", "a2", 2)); //0->2
    	assertEquals(2, graph.set("a1", "a2", 1));//a1，a2反复set三次出现问题,Edge测试出现问题
    	assertEquals(0, graph.set("a2", "a1", 1)); //反过来的都有问题
    	assertEquals(0, graph.set("a3", "a2", 3)); //两个a3->a2样例错误
    	assertEquals(1, graph.set("a2", "a3", 2)); //a2到a3的长度与a3到a2的长度是否相同？
     	assertEquals(3, graph.set("a3", "a2", 0)); //同样不行
    	assertEquals(2, graph.set("a2", "a3", 0)); //weight有问题
    }
    @Test
    public void testRemoveEdge() {
    	Graph<String> graph = emptyInstance();
    	assertEquals(false, graph.remove("a1"));
        graph.set("a1", "a2", 2);
        assertEquals(2, graph.set("a1", "a2", 2));
        assertEquals(true, graph.remove("a1"));
        assertEquals(0, graph.set("a1", "a2", 2));
        assertEquals(true,graph.remove("a2"));
        assertEquals(true, graph.remove("a1"));
        
    }
    @Test
    public void testSetVertices() {
    	Graph<String> graph = emptyInstance();
    	graph.add("a1");
    	graph.add("a2");
    	graph.add("a3");
    	graph.add("a4");
    	graph.remove("a1");
    	Set<String> verset = new HashSet<String>();
    	verset.add("a3");
    	verset.add("a2");
    	verset.add("a4");
    	assertEquals(graph.vertices(), verset);
    	
    }
    @Test
    public void testMapSources() {
    	Graph<String> graph = emptyInstance();
    	Map<String, Integer> map1 = new HashMap<String, Integer>();
    	graph.set("a1", "a2", 2);
    	graph.set("a1", "a3", 3);
    	graph.set("a3", "a2", 1);
    	graph.set("a2", "a1", 4);
    	map1.put("a1", 2);
    	map1.put("a3", 1);
    	assertEquals(map1, graph.sources("a2"));
    }
    @Test
    public void testMapTargets() {
    	Graph<String> graph = emptyInstance();
    	Map<String, Integer> map2 = new HashMap<String, Integer>();
    	graph.set("a1", "a2", 2);
    	graph.set("a1", "a3", 3);
    	graph.set("a3", "a2", 1);
    	graph.set("a2", "a1", 4);
    	map2.put("a2", 2);
    	map2.put("a3", 3);
    	assertEquals(map2, graph.targets("a1"));
    }
    // TODO other tests for instance methods of Graph
    
}
