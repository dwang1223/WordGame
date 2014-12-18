package util;

import junit.framework.TestCase;

/**
 * TestWordBean Class
 * 
 * @author Di Yu
 *
 */

public class TestWordBean extends TestCase {

	public void testWordBean() {
		String type = "noun";
		String content = "diyu";

		WordBean wb = new WordBean(type, content);
		wb.content = "other";
		wb.type = "adj";
	}
}
