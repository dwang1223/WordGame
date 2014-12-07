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
		Word word = new Word(10, 10, "king", false, false, false);
		assertEquals("king", word.getContent());
		assertEquals(10, word.getX());
		assertEquals(10, word.getY());
		assertEquals(100, word.getWidth());
		assertEquals(20, word.getHeight());
	}

	public void testMove() {
		Word word = new Word(10, 10, "di", false, false, false);
		word.setLocation(30, 50);
		assertEquals(30, word.getX());
		assertEquals(50, word.getY());
	}

	public void testIntersect() {
		Word word = new Word(10, 10, "di", false, false, false);
		boolean b1 = word.intersect(15, 15);
		assertEquals(true, b1);
		Word other = new Word(20, 20, "wang", false, false, false);
		boolean b2 = word.intersects(other);
		assertEquals(true, b2);
	}

	public void testEqual() {
		Word word = new Word(10, 10, "di", false, false, false);
		Word other = new Word(10, 10, "di", false, false, false);
		Word other2 = new Word(10, 10, "wang", false, false, false);
		Word other3 = new Word(20, 10, "di", false, false, false);
		assertEquals(false, word.equals(null));
		assertEquals(false, word.equals(new String()));
		assertEquals(false, word.equals(other2));
		assertEquals(false, word.equals(other3));
		assertEquals(true, word.equals(other));
	}
}
