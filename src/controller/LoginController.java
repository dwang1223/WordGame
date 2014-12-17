package controller;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.ArrayList;

import client.ClientApp;
import model.Board;
import model.Model;
import model.Word;
import util.InitializeWord;
import view.Application;
import view.ApplicationPanel;

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
		try {
			client.execute();
			// pr.setTitle();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.err.println("System crashed due to a client exception...");
			System.exit(1);
		}
	}

}
