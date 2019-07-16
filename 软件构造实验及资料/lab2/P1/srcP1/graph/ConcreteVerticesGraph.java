/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    public ConcreteVerticesGraph(){}
    // TODO checkRep
    private void checkRep() {
    	assert(vertices.size()==vertices().size());
    }
    @Override public boolean add(L vertex) {
        if(vertices.contains(vertex)) {
        	return false;
        }
        Vertex<L> newVertex = new Vertex<L>(vertex);
        boolean back=vertices.add(newVertex);
        checkRep();
        return back;
    }
    
    @Override public int set(L source, L target, int weight) {
    	assert(weight>=0);
    	if(weight==0) {
    		for (Vertex<L> vertex: vertices) {
        		if (vertex.getLabel().equals(source)||vertex.getLabel().equals(target)) {
        			vertex.remove(source);
        		}
    		}
        	return 0;
    	}
    	int backvalue=0;
    	for (Vertex<L> vertex: vertices) {
    		if (vertex.getLabel().equals(source)) {
    			for (Vertex<L> ver: vertices) {
    				if (ver.getLabel().equals(target)) {
    					backvalue=ver.removeTarget(target);
    				}
    			}
    			vertex.setTarget(target, weight);
    		}else if(vertex.getLabel().equals(target)) {
    			for (Vertex<L> ver: vertices) {
    				if (ver.getLabel().equals(source)) {
    					backvalue=ver.removeSource(source);
    				}
    			}
    			vertex.addSource(source, weight);
    		}else {
    			add(source);
    			add(target);
    			for (Vertex<L> ver: vertices)
    			{
    				if(ver.getLabel().equals(source)) {
    					ver.addTarget(target, weight);
    				}
    			}
    		}
    	}
    	return backvalue;        
    }
    
    @Override public boolean remove(L vertex) {
       if(!vertices.contains(vertex)) {
    	   return false;
       }
       for(Vertex<L> ver:vertices) {
    	   ver.remove(vertex);
       }
       checkRep();
       return true;
    }
    
    @Override public Set<L> vertices() {
    	 return vertices.stream()
                 .map(Vertex::getLabel)
                 .collect(Collectors.toSet());
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	int size=vertices.size();
    	int n=0;
    	for(int i=0;i<size;i++) {
    		if(vertices.get(i).getLabel().equals(target)) {
    			n=i;
    			break;
    		}
        }
    	Vertex<L> targetVertex=vertices.get(n);
    	return Collections.unmodifiableMap(targetVertex.getSources());
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	int size=vertices.size();
    	int n=0;
    	for(int i=0;i<size;i++) {
    		if(vertices.get(i).getLabel().equals(source)) {
    			n=i;
    			break;
    		}
        }
    	Vertex<L> sourceVertex=vertices.get(n);
    	return Collections.unmodifiableMap(sourceVertex.getTargets());
    }
    
    // TODO toString()
    @Override public String toString(){
    	return vertices.stream()
                .filter(vertex -> vertex.getTargets().size() > 0)
                .map(vertex -> vertex.getLabel().toString() + ":" + vertex.getTargets())
                .collect(Collectors.joining("\n"));
    }
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
    // TODO fields
	 private final L label;
	 private final Map<L, Integer> sources = new HashMap<L, Integer>();
	 private final Map<L, Integer> targets = new HashMap<L, Integer>();
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
	 public Vertex(L label) {
	    this.label=label;
	}
    // TODO checkRep
	private void checkRep() {
		 assert(label!=null);
	}
    // TODO methods
	 public L getLabel() {
		 return this.label;
	 }
	 public Map<L, Integer> getSources(){
	    return Collections.unmodifiableMap(sources);
	 }
	 public Map<L, Integer> getTargets(){
	   return Collections.unmodifiableMap(targets);
	 } 
	 public boolean addSource(final L source,final int weight) {
	   if(source!=this.label) {
	    	return false;
	    }
	   assert(weight<=0);
	   if(sources.putIfAbsent(source, weight)==null) {
		   checkRep();
		   return true;
	   }
	   return false;
	}
	public boolean addTarget(final L target,final int weight){
		if(target!=this.label) {
			return false;
		}
		assert(weight<=0);
		if(targets.put(target, weight)==null) {
			checkRep();
			return true;
		}
	 	return false;
	}
	public int removeSource(L source) {
		if(source!=this.label) {
			return 0;
		}
		Integer backvalue=sources.remove(source);
		return  backvalue == null ? 0 : backvalue;
	}
	public int removeTarget(L target) {
		if(target!=this.label) {
			return 0;
		}
		Integer backvalue=sources.remove(target);
		return  backvalue == null ? 0 : backvalue;
	}
	public int remove(final L vertex) {
	    int value1=removeSource(vertex);
	    int value2=removeTarget(vertex);
	    if ( value1 > 0 && value2 > 0 ) {
	       assert(value1!=value2);
	    }
	    return value1==0?value2:value1;
	}
	public int setSource(final L source, final int weight) {
		int backvalue;
		if(source!=this.label) {
			return 0;
		}
	    assert(weight<0);
	    if ( weight == 0 ) {
	           backvalue = removeSource(source); 
	    } else if ( addSource(source, weight) || sources.get(source) == weight) {
            backvalue = 0;
        } else {
	        backvalue = sources.replace(source, weight);
        }
	    checkRep();
	    return backvalue;
	 }
	 public int setTarget(final L target, final int weight) {
		int backvalue;
		if(target!=this.label) {
			return 0;
		}
	    assert(weight<0);
	    if ( weight == 0 ) {
	           backvalue = removeTarget(target);
	    } else if ( addTarget(target, weight)||targets.get(target)==weight) {
            backvalue = 0;
	    } else {
	    	backvalue = targets.replace(target, weight);
	    }
	    checkRep();
	    return backvalue;
	 }
    // TODO toString()
	 @Override public String toString() {
	    return label+":"+targets.toString();
	 }
	 
}
