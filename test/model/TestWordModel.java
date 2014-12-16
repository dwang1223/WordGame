package model;

import junit.framework.TestCase;

public class TestWordModel extends TestCase {

	public void testConstructor() {
		Word word1 = new Word(70, 85, "eat", "verb", false, false, false, false);
		Word word2 = new Word(20, 20, "are", "noun", false, false, false, false);
		Word word3 = new Word(100, 100, "apple", "noun", false, false, false, false);
		
		Board board = new Board();
		board.addWord(word1);
		board.addWord(word2);
		board.addWord(word3);
		
		WordModel wordModel = new WordModel(board);
		
		assertEquals(2, wordModel.getColumnCount());
		assertEquals(3, wordModel.getRowCount());
		assertEquals("are", wordModel.getValueAt(1, 0));
		assertEquals("noun", wordModel.getValueAt(1, 1));
		assertEquals("", wordModel.getValueAt(1, -1));
		
		wordModel.sort("Word");
		assertEquals(0, board.words.indexOf(word3));
		wordModel.sort("Type");
		assertEquals(0, board.words.indexOf(word3));
	}
	
}
