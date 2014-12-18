package controller;

import junit.framework.TestCase;

import model.*;
import view.*;

/**
 * This is the test case for testing MoveWordController
 * @author Chen
 *
 */
public class TestMoveWordController extends TestCase {

	public void testConstructor() {
		
		Board board = new Board();
		Model model = new Model(board);
		Application app = new Application(model);
		
		MoveWordController controller = new MoveWordController(model, app);
		controller.register();
		
		
		Word word1 = new Word(1, 1, "I", "noun", true, false, false, false);
		Word word2 = new Word (20, 1, "he", "noun", true, true, false, false);
		Word word3 = new Word(40, 1, "she", "noun", true, true, false, false);
		
		Row row1 = new Row(word2, word3);
		
		board.addWord(word1);
		board.addWord(word2);
		board.addWord(word3);
		
		board.addRow(row1);
		
		assertFalse(controller.select(100, 100));
		assertFalse(controller.drag(150, 150));
		assertFalse(controller.release(150, 150));
		
		assertFalse(controller.select(20, 1));
		assertTrue(controller.select(1, 1));
		assertTrue(controller.drag(20, 1));
		assertTrue(controller.drag(701, 601));
		System.out.println(model.getSelectedWord().getContent());
		controller.release(20, 1);
		
		Word word4 = new Word(500, 500, "who", "noun", false, false, false, false);
		board.addWord(word4);
		controller.select(500, 500);
		controller.drag(600, 395);
		assertTrue(controller.release(600, 395));
	}
	
}
