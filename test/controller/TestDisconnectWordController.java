package controller;

import junit.framework.TestCase;

import model.*;
import view.*;

/**
 * This is test case for testing DisconnectWordController
 * 
 * @author Chen
 *
 */
public class TestDisconnectWordController extends TestCase {

	public void testConstructor() {

		Board board = new Board();
		Model model = new Model(board);
		Application app = new Application(model);

		DisconnectWordController controller = new DisconnectWordController(
				model, app);
		controller.register();

		Word word1 = new Word(1, 1, "he", "noun", true, false, false, false);
		Word word2 = new Word(1, 401, "is", "noun", false, false, false, false);

		board.addWord(word1);
		board.addWord(word2);

		assertFalse(controller.select(500, 500));
		assertFalse(controller.drag(500, 500));
		assertFalse(controller.select(1, 401));

		Word word3 = new Word(30, 1, "am", "noun", true, true, false, false);
		Word word4 = new Word(50, 1, "she", "noun", true, true, false, false);
		Row row1 = new Row(word3, word4);
		board.addWord(word3);
		board.addWord(word4);
		board.addRow(row1);

		assertFalse(controller.select(1, 1));

		Word word5 = new Word(100, 1, "hi", "noun", true, true, true, false);
		Word word6 = new Word(120, 1, "there", "noun", true, true, true, false);
		Word word7 = new Word(100, 26, "how", "noun", true, true, true, false);
		Word word8 = new Word(130, 26, "do", "noun", true, true, true, false);

		Row row2 = new Row(word5, word6);
		Row row3 = new Row(word7, word8);

		Poem poem = new Poem(row2, row3);

		board.addWord(word5);
		board.addWord(word6);
		board.addWord(word7);
		board.addWord(word8);

		board.addRow(row2);
		board.addRow(row3);

		board.addPoem(poem);

		assertFalse(controller.select(100, 1));

		controller.select(50, 1);
		controller.drag(1000, 1000);
		controller.drag(100, 1);
		assertTrue(controller.drag(100, 100));
		controller.release(100, 1);

		Word word9 = new Word(1, 100, "I", "noun", true, false, false, false);
		Word word10 = new Word(11, 100, "love", "noun", true, false, false,
				false);
		Word word11 = new Word(15, 100, "us", "noun", true, false, false, false);

		Row row4 = new Row(word9, word10);
		row4.addWord(word11, true);

		board.addWord(word9);
		board.addWord(word10);
		board.addWord(word11);
		board.addRow(row4);

		controller.select(11, 100);
		controller.select(16, 100);
	}

}
