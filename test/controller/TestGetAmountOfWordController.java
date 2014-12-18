package controller;

import client.ClientApp;
import model.Model;
import view.Application;
import junit.framework.TestCase;

public class TestGetAmountOfWordController extends TestCase {

	public TestGetAmountOfWordController(String name) {
		super(name);
	}
	
	public void testGetAmountOfSwapController(){
		Model model = new Model();
		Application app = new Application(model);
		ClientApp client = new ClientApp(model,app);
		app.setClient(client);
		
		GetAmountOfSwapWordsController gc = new GetAmountOfSwapWordsController(model,app);
		gc.process();
	}

}
