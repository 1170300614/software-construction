/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph;
    private final List<String> corpusWord;
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        corpusWord=getWordsFromFile(corpus);
        graph=generateAffinityGraph(corpusWord);
        checkRep();
    }
    private List<String> getWordsFromFile(File corpus) throws IOException{
    	List<String> getwords=new ArrayList<>();
    	try {
    		FileReader f = new FileReader(corpus);
    		BufferedReader buff = new BufferedReader(f);
    		Scanner scan=new Scanner(buff);
    		while(scan.hasNext()) {
    			getwords.add(scan.next().toLowerCase());
    		}
    		scan.close();
    	}catch (IOException e) {
    		e.printStackTrace();
		}
    	return getwords;
    }
    private Graph<String> generateAffinityGraph(List<String> wordsList){
    	Graph<String> graph=Graph.empty();
    	int size=wordsList.size();
    	for(int i=0;i<size;i++) {
    		String source = wordsList.get(i);
    		graph.add(source);
    		if(i+1>=size) {
    			break;
    		}
    		String target = wordsList.get(i+1);
    		int preweight = graph.set(source, target, 1);
    		graph.set(source, target, preweight+1);
    	}
    	return graph;	
    }
    public List<String> getCorpusWords() {
        return Collections.unmodifiableList(corpusWord);
    }
    // TODO checkRep
    private void checkRep() {
    	assert(graph!=null);
    }
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        String[] words = input.split("\\s");
        StringBuilder poem = new StringBuilder();
        int length=words.length;
        for(int i=0;i<length;i++) {
        	if((i+1)>=length) {
        		break;
        	}
        	Map<String, Integer> fwordTarget = graph.targets(words[i].toLowerCase());
        	Map<String, Integer> swordSource = graph.sources(words[i+1].toLowerCase());
        	Set<String> edge=fwordTarget.keySet();
        	List<String> alledges =edge.stream()
                    .filter(e -> swordSource.containsKey(e))
                    .collect(Collectors.toList());
        	if(!alledges.isEmpty()) {
        		poem.append(alledges.get(0)+" ");
        	}
        }
        checkRep();
        return poem.toString();
        
    }
    
    // TODO toString()
    @Override public String toString() {
        return graph.toString();
    }
}
