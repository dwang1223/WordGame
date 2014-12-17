package controller;

import junit.framework.TestCase;
import model.Board;
import model.Model;
import model.Row;
import model.Word;
import view.Application;

/**
 * TestMoveRowController Class
 * 
 * @author diwang
 *
 */
public class TestMoveRowController extends TestCase {
	public void testMoveRow() {
		Board curBoard = new Board();
		Word word1 = new Word(100, 100, "di", "noun", true, false, false,
				false);
		Word word2 = new Word(120, 100, "wang", "noun", true, false, false,
				false);
		Word word3 = new Word(10, 10, "di", "noun", true, false, false, false);
		Word word4 = new Word(30, 10, "wang", "noun", true, false, false,
				false);
		Word word5 = new Word(10, 300, "wang", "noun", true, false, false,
				false);
		Word word6 = new Word(10, 350, "wang", "noun", true, true, true, false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word3, word4);
		curBoard.addWord(word1);
		curBoard.addWord(word2);
		curBoard.addWord(word3);
		curBoard.addWord(word4);
		curBoard.addWord(word5);
		curBoard.addWord(word6);
		curBoard.addRow(row1);
		curBoard.addRow(row2);
		Model model = new Model(curBoard);
		Application app = new Application(model);
		MoveRowController moveRowController = new MoveRowController(
				model, app);
		moveRowController.register();
		moveRowController.select(5, 5);
		moveRowController.select(15, 305);
		moveRowController.select(15, 355);
		moveRowController.select(15, 15);
		moveRowController.drag(200, 100);
		moveRowController.drag(1000, 1000);
		moveRowController.drag(15, 305);
		moveRowController.select(20, 10);
		moveRowController.drag(80, 75);
		moveRowController.release(80, 75);
	}

}
