package test;

import debug.TopVotedCandidate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * TopVotedCandidate Tester.
 *
 * @author <1170300603>
 * @version 1.0
 * @since <pre>5月 16, 2019</pre>
 */
public class TopVotedCandidateTest {
	
	/**
	 * Method: TopVotedCandidate(int[] persons, int[] times)
	 */
	@Test public void testTopVotedCandidate() {
		int[] persons = {0, 1, 1, 0, 0, 1, 0};
		int[] times = {0, 5, 10, 15, 20, 25, 30};
		TopVotedCandidate topVotedCandidate = new TopVotedCandidate(persons, times);
		assertEquals("Correct answer",0,topVotedCandidate.q(3));
		assertEquals("Correct answer",1,topVotedCandidate.q(12));
		assertEquals("Correct answer",1,topVotedCandidate.q(25));
		assertEquals("Correct answer",0,topVotedCandidate.q(15));
		assertEquals("Correct answer",1,topVotedCandidate.q(14));
		assertEquals("Correct answer",1,topVotedCandidate.q(8));
	}
}