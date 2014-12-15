package controller;

import junit.framework.TestCase;
import model.Board;
import model.Model;
import view.Application;

public class TestAddWordController extends TestCase {
	public void testConstruction() {
		Board b = new Board();
		Model model = new Model(b);
		Application app = new Application(model);
		// register controller
		AddWordController addWordController = new AddWordController(model, app);
		addWordController.register();
		addWordController.process(0, 0);
	}
}
