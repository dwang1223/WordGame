package controller;

import java.awt.event.MouseAdapter;
import java.io.IOException;

import model.Model;
import view.Application;
import view.ApplicationPanel;
import client.ClientApp;

/**
 * LoginController Class
 * 
 * @author Di Yu
 *
 */
public class LoginController extends MouseAdapter {

	/** Needed for controller behavior. */
	Model model;
	Application app;
	ApplicationPanel panel;

	/** Constructor holds onto key manager objects. */
	public LoginController(Model model, Application app) {
		this.model = model;
		this.app = app;
		this.panel = app.getWordPanel();
	}

	public void process() {
		ClientApp client = new ClientApp(model, app);
		app.setClient(client);
		try {
			client.execute();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
