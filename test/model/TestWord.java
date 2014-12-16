package model;

import junit.framework.TestCase;

/**
 * TestWord Class
 * 
 * @author diwang
 *
 */
public class TestWord extends TestCase {
	public void testConstruction() {
		String content = "king";
		String type = "noun";
		Word word = new Word(10, 10, "king", "noun", false, false, false, false);
		assertEquals(content, word.getContent());
		assertEquals(type, word.getType());
		assertEquals(10, word.getX());
		assertEquals(10, word.getY());
		assertEquals(content.length() * 10, word.getWidth());
		assertEquals(25, word.getHeight());
		assertEquals(false, word.isProtected());
		assertEquals(false, word.isInRow());
		assertEquals(false, word.isInPoem());
		assertEquals(false, word.isSearched());
		word.setProtected(true);
		word.setInRow(true);
		word.setInPoem(true);
		word.setSearched(true);
		assertEquals(true, word.isProtected());
		assertEquals(true, word.isInRow());
		assertEquals(true, word.isInPoem());
		assertEquals(true, word.isSearched());
	}

	public void testMove() {
		Word word = new Word(10, 10, "di", "noun", false, false, false, false);
		word.setLocation(30, 50);
		assertEquals(30, word.getX());
		assertEquals(50, word.getY());
	}

	public void testIntersect() {
		Word word = new Word(10, 10, "di", "noun", false, false, false, false);
		boolean b1 = word.intersect(15, 15);
		assertEquals(true, b1);
		Word other = new Word(20, 20, "wang", "noun", false, false, false,
				false);
		boolean b2 = word.intersects(other);
		assertEquals(true, b2);
	}

	public void testEqual() {
		Word word = new Word(10, 10, "di", "noun", false, false, false, false);
		Word other = new Word(10, 10, "di", "noun", false, false, false, false);
		Word other2 = new Word(10, 10, "wang", "noun", false, false, false,
				false);
		Word other3 = new Word(20, 10, "di", "noun", false, false, false, false);
		assertEquals(false, word.equals(null));
		assertEquals(false, word.equals(new String()));
		assertEquals(false, word.equals(other2));
		assertEquals(false, word.equals(other3));
		assertEquals(true, word.equals(other));
	}
}
