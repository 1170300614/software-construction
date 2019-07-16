package test;

import debug.FindMedianSortedArrays;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * FindMedianSortedArrays Tester.
 *
 * @author <1170300603>
 * @version 1.0
 * @since <pre>5月 16, 2019</pre>
 */
public class FindMedianSortedArraysTest {
	
	/**
	 * Method: findMedianSortedArrays(int[] A, int[] B)
	 */
	@Test public void testFindMedianSortedArrays() throws Exception {
		int[] A1 = {1, 3};
		int[] B1 = {2};
		FindMedianSortedArrays f = new FindMedianSortedArrays();
		assertEquals("The answer should be 2.0", 2.0, f.findMedianSortedArrays(A1, B1), 0.01);
		int[] A2 = {1, 2};
		int[] B2 = {3, 4};
		assertEquals("The answer should be 2.5", 3.5, f.findMedianSortedArrays(A2, B2), 0.01);
		int[] A3 = {1, 1, 1};
		int[] B3 = {5, 6, 7};
		assertEquals("The answer should be 3.0", 5.5, f.findMedianSortedArrays(A3, B3), 0.01);
		int[] A4 = {1, 1};
		int[] B4 = {1, 2, 3};
		assertEquals("The answer should be 1.0", 1.0, f.findMedianSortedArrays(A4, B4), 0.01);
	}
}