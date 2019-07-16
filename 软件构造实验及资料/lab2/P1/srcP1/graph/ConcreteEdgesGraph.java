/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    
    // TODO checkRep
    
    @Override public boolean add(L vertex) {
        return vertices.add(vertex);
    }
    @Override public int set(L source, L target, int weight) {
        int flag=IsexistEdge(source, target);
        int backvalue=0;
        if(weight>0) {
        	Edge<L> setEdge = new Edge<L>(source,target,weight);
        	if(flag<0){
        		add(source);
        		add(target);
        		edges.add(setEdge);
        	}else {
        		Edge<L> tempEdge=edges.set(flag, setEdge);
        		backvalue=tempEdge.getWeight();
        	}
        }else if(weight==0&&flag>=0) {
        	Edge<L> tempEdge=edges.remove(flag);
    		backvalue=tempEdge.getWeight();
        }
        return backvalue;
    }
    private int IsexistEdge(L source,L target) {
    	int i=0;
    	for(Edge<L> e:edges) {
    		if(e.getSource().equals(source)&&e.getTarget().equals(target)) {
    			return i;
    		}
    		i++;
    	}
    	return -1;
    }
    @Override public boolean remove(L vertex) {
        if(vertices.contains(vertex)) {
        	vertices.remove(vertex);
        	for(Edge<L> e:edges) {
        		if(e.getSource().equals(vertex)||e.getTarget().equals(vertex)) {
        			edges.remove(e);
        		}
        	}
        	return true;
        }
        return false;
    }
    
    @Override public Set<L> vertices() {
        return Collections.unmodifiableSet(vertices);
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	 return edges.stream().filter(edge -> edge.getTarget().equals(target)).collect(Collectors.toMap(Edge::getSource, Edge::getWeight));
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	return edges.stream().filter(edge -> edge.getSource().equals(source)).collect(Collectors.toMap(Edge::getTarget, Edge::getWeight));
    }
    
    // TODO toString()
    @Override public String toString(){
        if ( edges.isEmpty() ) {
            return "Empty Graph";
        }
        String str="";
    	for (Edge<L> edge: edges) {
    		str+=edge.toString();
    	}
    	return str; 
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
	private final L from;
    private final L to;
    private final int weight;
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    public Edge(final L from,final L to,final int weight){
    	this.from=from;
    	this.to=to;
    	this.weight=weight;
    }
    // TODO checkRep
    private void checkRep() {
    	assert(from!=null);
    	assert(to!=null);
    	assert(weight>0);
    }
    // TODO methods
    public int getWeight() {
    	return weight;
    }
    public L getSource() {
		return from;
	}
    public L getTarget() {
		return to;
	}
    public Edge<L> setWeight(int newweight) {
    	checkRep();
    	return new Edge<>(from, to, newweight);
    }
    @Override public boolean equals(Object anObject) {
		if(this==anObject) {
			return true;
		}
		if(!(anObject instanceof Edge)) {
			return false;
		}
		Edge<?> anotherEdge=(Edge<?>) anObject;
		boolean flag=this.getSource().equals(anotherEdge.getSource())&&
				     this.getTarget().equals(anotherEdge.getTarget())&&
				     this.getWeight()==anotherEdge.getWeight();
		return flag;       
	}
    @Override public int hashCode() {
    	int result=20;
    	result=result*31+getSource().hashCode();
    	result=result*31+getTarget().hashCode();
    	result=result*31+getWeight();
    	return result;
    }
    // TODO toString()
    @Override public String toString() {
    	return "("+ getSource().toString() +"," + getTarget().toString() + "," + getWeight() +")";
    }
}
