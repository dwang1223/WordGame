package model;

import junit.framework.TestCase;

public class TestMemento extends TestCase {

	public void testConstruction() {
		Board b = new Board();

		Word word = new Word(0, 0, "wang", false, false);
		b.addWord(word);

		// create memento and restore b2 to that state
		BoardMemento m = b.getState();
		Board b2 = new Board();
		b2.restore(m);

		// both b and b2 should have just single word
		assertEquals(b.words.get(0), b2.words.get(0));
	}

	public void testRestoration() {
		Board b = new Board();

		Word s = new Word(0, 0, "di", false, false);
		b.addWord(s);
		BoardMemento m = b.getState();
		b.removeWord(s);
		b.restore(m);

		// same shape back
		assertEquals(b.words.get(0), s);
	}
}
