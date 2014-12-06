package controller;

import view.ApplicationPanel;
import model.Board;
import model.Model;
import junit.framework.TestCase;

public class TestAddWordController extends TestCase {
	public void testConstruction() {
		Board b = new Board();
		Model model = new Model(b);
		ApplicationPanel panel = new ApplicationPanel(model);
		;
		// register controller
		AddWordController addWordController = new AddWordController(model, panel);
		addWordController.register();
		addWordController.process(0, 0);
	}
}
