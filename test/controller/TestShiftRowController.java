package controller;

import junit.framework.TestCase;
import model.Board;
import model.Model;
import model.Poem;
import model.Row;
import model.Word;
import view.Application;

/**
 * TestShiftRowController Class
 * 
 * @author diwang
 *
 */
public class TestShiftRowController extends TestCase {
	public void testShiftRow() {
		Board curBoard = new Board();
		Word word1 = new Word(100, 100, "di", "noun", true, false, false, false);
		Word word2 = new Word(120, 100, "wang", "noun", true, false, false,
				false);
		Word word3 = new Word(100, 125, "di", "noun", true, false, false, false);
		Word word4 = new Word(120, 125, "wang", "noun", true, false, false,
				false);
		Word word5 = new Word(100, 150, "di", "noun", true, false, false, false);
		Word word6 = new Word(120, 150, "wang", "noun", true, false, false,
				false);
		Word word7 = new Word(180, 100, "di", "noun", true, false, false, false);
		Word word8 = new Word(200, 100, "di", "noun", true, false, false, false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word3, word4);
		Row row3 = new Row(word5, word6);
		Row row4 = new Row(word7, word8);
		Poem poem = new Poem(row1, row2);
		poem.addRow(row3, true);
		curBoard.addWord(word1);
		curBoard.addWord(word2);
		curBoard.addWord(word3);
		curBoard.addWord(word4);
		curBoard.addWord(word5);
		curBoard.addWord(word6);
		curBoard.addWord(word7);
		curBoard.addWord(word8);
		curBoard.addRow(row1);
		curBoard.addRow(row2);
		curBoard.addRow(row3);
		curBoard.addRow(row4);
		curBoard.addPoem(poem);
		Model model = new Model(curBoard);
		Application app = new Application(model);
		ShiftRowController shiftRowController = new ShiftRowController(model,
				app);
		shiftRowController.register();
		shiftRowController.select(10, 10);
		shiftRowController.select(190, 110);
		shiftRowController.select(110, 110);
		shiftRowController.drag(1000, 110);
		shiftRowController.drag(220, 110);
		shiftRowController.drag(120, 110);
		shiftRowController.drag(160, 110);
		shiftRowController.release(120, 110);
		shiftRowController.select(110, 130);
		shiftRowController.drag(220, 130);
		shiftRowController.drag(120, 130);
		shiftRowController.release(120, 130);
		shiftRowController.select(110, 160);
		shiftRowController.drag(220, 160);
		shiftRowController.drag(120, 160);
		shiftRowController.release(120, 160);
	}
}
