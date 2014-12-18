package controller;

import java.awt.event.MouseEvent;

import junit.framework.TestCase;
import model.*;
import view.*;

/**
 * This is test case for testing RemoveWordController
 * @author Chen
 *
 */
public class TestRemoveWordController extends TestCase {

	public void testConstructor() {
		
		Board board = new Board();
		Model model = new Model(board);
		Application app = new Application(model);
		
		RemoveWordController controller = new RemoveWordController(model, app);
		controller.register();
		
		Word word1 = new Word(1, 1, "am", "noun", true, false, false, false);
		Word word2 = new Word(500, 500, "I", "noun", false, false, false, false);
		
		board.addWord(word1);
		board.addWord(word2);
		
		assertFalse(controller.process(100, 100));
		assertTrue(controller.process(2, 2));
		assertTrue(controller.process(500, 500));
	}
	
}
