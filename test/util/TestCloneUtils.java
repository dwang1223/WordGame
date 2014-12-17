package util;

import junit.framework.TestCase;
import model.Word;

/**
 * TestCloneUtils Class
 * 
 * @author diwang
 *
 */
public class TestCloneUtils extends TestCase {
	public void testClone() {
		Word word1 = new Word(10, 10, "di", "noun", false, false, false, false);
		Word word2 = CloneUtils.clone(word1);
		assertEquals(word1.getX(), word2.getX());
		CloneUtils cloneUtils = new CloneUtils();
	}
}
