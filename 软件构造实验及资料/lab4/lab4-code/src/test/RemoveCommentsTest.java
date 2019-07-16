package test;

import debug.RemoveComments;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * RemoveComments Tester.
 *
 * @author <1170300603>
 * @version 1.0
 * @since <pre>5月 16, 2019</pre>
 */
public class RemoveCommentsTest {
	
	/**
	 * Method: removeComments(String[] source)
	 */
	@Test public void testRemoveComments() {
		String[] source1 = {"a/*comment", "*line", "more_comment*/b"};
		RemoveComments r = new RemoveComments();
		assertEquals("Answer without comments", "ab", r.removeComments(source1).get(0));
		String[] source2 =
				{"public static void main(String[] args)//comment", "{int a = 0;", "/*comment",
						"*comment", "*/", "String b = Integer.parse(a + 1);//comment comment",
						"}"};
		List<String> list = new ArrayList<>();
		list.add("public static void main(String[] args)");
		list.add("{int a = 0;");
		list.add("String b = Integer.parse(a + 1);");
		list.add("}");
		assertEquals("Answer without comments", list, r.removeComments(source2));
	}
}