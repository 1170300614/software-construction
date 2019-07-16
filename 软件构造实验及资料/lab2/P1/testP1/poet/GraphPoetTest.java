/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    private static final GraphPoet InitGraph(String source) {
        try {
            final File corpus = new File(source);
            GraphPoet graphPoet = new GraphPoet(corpus);
            return graphPoet;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    final GraphPoet graph1 = InitGraph("test/P1/poet/1.txt/");
    final GraphPoet graph2 = InitGraph("test/P1/poet/2.txt");
    final GraphPoet graph3 = InitGraph("test/P1/poet/3.txt");
    // TODO tests
    @Test
    public void testGraphPoet1() {        
        List<String> corpusWords = graph1.getCorpusWords();
        assertEquals("Expected one word",
                1, corpusWords.size());
        assertTrue("Expected 'lihonglin'",
                corpusWords.contains("lihonglin"));
    }
    @Test
    public void testGraphPoet2() {        
        List<String> corpusWords = graph2.getCorpusWords();
        assertEquals("Expected four word",
                4, corpusWords.size());
        assertTrue("Expected 'clever'",
                corpusWords.contains("clever"));
    }
    @Test
    public void testGraphPoet3() {        
        List<String> corpusWords = graph3.getCorpusWords();
        assertEquals("Expected eighteen word",
                18, corpusWords.size());
        assertTrue("Expected 'relationship'",
                corpusWords.contains("relationship"));
        assertTrue("Expected 'believe'",
                corpusWords.contains("believe"));
        assertTrue("Expected 'you'",
                corpusWords.contains("you"));
    }
}
