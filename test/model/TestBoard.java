package model;

import java.util.ArrayList;
import java.util.Comparator;

import junit.framework.TestCase;

/**
 * TestBoard Class
 * 
 * @author diwang
 *
 */
public class TestBoard extends TestCase {
	public void testConstruction() {
		Board board = new Board();
		Word word = new Word(10, 10, "king", "noun", false, false, false, false);
		board.addWord(word);
		board.removeWord(word);
		Word word1 = new Word(10, 10, "di", "noun", false, false, false, false);
		Word word2 = new Word(20, 20, "wang", "noun", false, false, false,
				false);
		board.addWord(word1);
		board.addWord(word2);
		Row row = new Row(word1, word2);
		board.addRow(row);
		Row row2 = board.getRowFromRowListByWord(board.rows, word1);
		assertEquals(row, row2);
		row2 = board.getRowFromRowListByWord(board.rows, word);
		assertEquals(null, row2);

		Poem poem = new Poem(row, row);
		board.addPoem(poem);
		Poem poem2 = board.getPoemFromPoemListByRow(board.poems, row);
		assertEquals(poem, poem2);
		poem2 = board
				.getPoemFromPoemListByRow(board.poems, new Row(word, word));
		assertEquals(null, poem2);

		board.removeRow(row);
		board.removePoem(poem);
	}

	public void testWordsOperation() {
		Board board = new Board();
		Word word1 = new Word(10, 10, "di", "noun", false, false, false, false);
		Word word2 = new Word(20, 20, "wang", "noun", false, false, false,
				false);
		board.addWord(word1);
		board.addWord(word2);
		Word word = board.findWord(11, 11);
		assertEquals(word1, word);
		word = board.findWord(100, 100);
		assertEquals(null, word);
		ArrayList<Word> unprotectedWords = board.getUnprotectedWords();
		ArrayList<Word> searchedWords = board.setSearchedWords("di");
		board.sortUnprotectedWords(new Comparator<Word>() {
			@Override
			public int compare(Word o1, Word o2) {
				return 0;
			}
		});
	}

	public void testOutOfBound() {
		Word word1 = new Word(100, 100, "king", "noun", false, false, false,
				false);
		boolean b1 = Board.isOutOfBound(word1);
		assertEquals(false, b1);
		b1 = Board.isOutOfProtectedArea(word1);
		assertEquals(false, b1);
		word1.setLocation(1000, 1000);
		b1 = Board.isOutOfBound(word1);
		assertEquals(true, b1);
		b1 = Board.isOutOfProtectedArea(word1);
		assertEquals(true, b1);
		b1 = Board.isOutOfUnprotectedArea(word1);
		assertEquals(true, b1);
		word1.setLocation(100, 500);
		b1 = Board.isOutOfUnprotectedArea(word1);
		assertEquals(false, b1);
	}
}
