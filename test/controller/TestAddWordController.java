package controller;

import java.awt.event.MouseEvent;

import junit.framework.TestCase;
import model.Board;
import model.Model;
import view.Application;
import view.ApplicationPanel;

/**
 * This is test case for testing AddWordController
 * 
 * @author Chen
 *
 */
public class TestAddWordController extends TestCase {

	public void testConstruction() {

		Board board = new Board();
		Model model = new Model(board);
		Application app = new Application(model);
		ApplicationPanel panel = new ApplicationPanel(model);

		AddWordController addWordController = new AddWordController(model, app);
		addWordController.register();

		MouseEvent mouseEvent = new MouseEvent(panel, 1, 1, 1, 97, 490, 1, true);
		addWordController.mousePressed(mouseEvent);

		// addWordController.process(97, 490);

		assertEquals("noun", board.words.get(0).getType());

		MouseEvent mouseEvent2 = new MouseEvent(panel, 1, 1, 1, 97, 750, 1,
				true);
		addWordController.mousePressed(mouseEvent2);
		assertEquals(1, board.words.size());
	}

}
