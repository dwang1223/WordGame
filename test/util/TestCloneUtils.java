package util;

import model.Board;
import model.Row;
import model.Word;
import junit.framework.TestCase;

public class TestCloneUtils extends TestCase {

	public void testClone() {
		Word word1 = new Word(10, 10, "di", false, false);
		Word word2 = new Word(20, 20, "wang", false, false);
		Row row1 = new Row(word1, word2);
		Board board1 = new Board();
		board1.addWord(word1);
		board1.addWord(word2);
		board1.addRow(row1);

		Word word3 = CloneUtils.clone(word1);
		word3.setLocation(30, 30);
		System.out.println(word1.getX());
		Row row2 = CloneUtils.clone(row1);
		row2.getWordList().get(0).setLocation(50, 50);
		System.out.println(row1.getWordList().get(0).getX());
		Board board2 = CloneUtils.clone(board1);
		board2.words.get(0).setLocation(60, 60);
		System.out.println(board1.words.get(0).getX());
		board2.rows.get(0).getWordList().get(0).setLocation(70, 70);
		System.out.println(board1.rows.get(0).getWordList().get(0).getX());
	}
}
