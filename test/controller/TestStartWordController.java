package controller;

import view.Application;
import model.Model;
import junit.framework.TestCase;

/**
 * TestStartWordController Class
 * 
 * @author diwang
 *
 */
public class TestStartWordController extends TestCase {
	public void testConstruction() {
		Model model = new Model();
		Application app = new Application(model);
		StartWordController startWordController = new StartWordController(model, app);
		startWordController.process();
	}
}
