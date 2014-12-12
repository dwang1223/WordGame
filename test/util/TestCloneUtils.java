package util;

import model.Board;
import model.Row;
import model.Word;
import junit.framework.TestCase;

public class TestCloneUtils extends TestCase {

	public void testClone() {
		Word word1 = new Word(10, 10, "di", "noun", false, false, false);
		Word word2 = new Word(20, 20, "wang", "noun", false, false, false);
		// Row row1 = new Row(word1, word2);
		// Row row2 = CloneUtils.clone(row1);
		// Word word4 = row2.getWordList().get(0);
		// word4.setLocation(30, 30);
		// word4.setProtected(true);
		// word4.setInRow(true);
		// word4.setInPoem(true);
		// System.out.println("x:" + word1.getX());
		// System.out.println("protected: " + word1.isProtected());
		// System.out.println("row: " + word1.isInRow());
		// System.out.println("poem: " + word1.isInPoem());

		Board board1 = new Board();
		board1.addWord(word1);
		board1.addWord(word2);
		// board1.addRow(row1);
		//
		Board board2 = CloneUtils.clone(board1);
		Word word3 = board2.words.get(0);
		word3.setLocation(30, 30);
		word3.setProtected(true);
		word3.setInRow(true);
		word3.setInPoem(true);
		System.out.println("x:" + word1.getX());
		System.out.println("protected: " + word1.isProtected());
		System.out.println("row: " + word1.isInRow());
		System.out.println("poem: " + word1.isInPoem());

		// Word word3 = CloneUtils.clone(word1);
		// word3.setLocation(30, 30);
		// word3.setProtected(true);
		// word3.setInRow(true);
		// word3.setInPoem(true);
		// System.out.println("x:" + word1.getX());
		// System.out.println("protected: " + word1.isProtected());
		// System.out.println("row: " + word1.isInRow());
		// System.out.println("poem: " + word1.isInPoem());
		// Row row2 = CloneUtils.clone(row1);
		// row2.getWordList().get(0).setLocation(50, 50);
		// System.out.println(row1.getWordList().get(0).getX());
		// Board board2 = CloneUtils.clone(board1);
		// board2.words.get(0).setLocation(60, 60);
		// System.out.println(board1.words.get(0).getX());
		// board2.rows.get(0).getWordList().get(0).setInRow(true);
		// System.out.println(board1.rows.get(0).getWordList().get(0).isInRow());
		// board2.rows.get(0).getWordList().get(0).setProtected(true);
		// System.out.println(board1.rows.get(0).getWordList().get(0)
		// .isProtected());
	}
}
