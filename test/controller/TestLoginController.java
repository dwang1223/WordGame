package controller;

import model.Model;
import view.Application;
import junit.framework.TestCase;

public class TestLoginController extends TestCase {

	public TestLoginController(String name) {
		super(name);
	}
	
	public void testLoginController(){
		Model model = new Model();
		Application app = new Application(model);
		
		LoginController loginController = new LoginController(model,app);
		
		loginController.process();
	}

}
