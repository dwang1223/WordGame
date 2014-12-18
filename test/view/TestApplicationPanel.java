package view;

import model.Board;
import model.Model;
import model.Poem;
import model.Row;
import model.Word;
import junit.framework.TestCase;

/**
 * TestApplicationPanel Class
 * 
 * @author diwang
 *
 */
public class TestApplicationPanel extends TestCase {
	public void testConstruction() {
		Board curBoard = new Board();
		Word word1 = new Word(100, 100, "di", "noun", true, false, false, false);
		Word word2 = new Word(120, 100, "wang", "noun", true, false, false,
				false);
		Word word3 = new Word(100, 125, "di", "noun", true, false, false, false);
		Word word4 = new Word(120, 125, "wang", "noun", true, false, false,
				false);
		Word word5 = new Word(200, 150, "di", "noun", true, false, false, false);
		Word word6 = new Word(220, 150, "wang", "noun", true, false, false,
				false);
		Word word7 = new Word(300, 600, "di", "noun", false, false, false,
				false);
		Word word8 = new Word(330, 600, "wang", "noun", false, false, false,
				false);
		Word word9 = new Word(300, 625, "di", "noun", false, false, false, true);
		Word word0 = new Word(320, 655, "wang", "noun", false, false, false,
				true);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word3, word4);
		Row row3 = new Row(word5, word6);
		Poem poem = new Poem(row1, row2);
		curBoard.addWord(word1);
		curBoard.addWord(word2);
		curBoard.addWord(word3);
		curBoard.addWord(word4);
		curBoard.addWord(word5);
		curBoard.addWord(word6);
		curBoard.addWord(word7);
		curBoard.addWord(word8);
		curBoard.addWord(word9);
		curBoard.addWord(word0);
		curBoard.addRow(row1);
		curBoard.addRow(row2);
		curBoard.addRow(row3);
		curBoard.addPoem(poem);
		Model model = new Model(curBoard);
		ApplicationPanel panel = new ApplicationPanel(model);
		panel.redraw();
	}
}
