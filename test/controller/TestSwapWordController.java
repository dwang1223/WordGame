package controller;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import client.ClientApp;
import view.Application;
import model.Model;
import junit.framework.TestCase;

/**
 * TestSwapWordController Class
 * 
 * @author Di Yu
 *
 */
public class TestSwapWordController extends TestCase {

	public void testProcess() {

		Model model = new Model();
		Application app = new Application(model);
		SwapWordController swapWordController = new SwapWordController(app,
				new JTable());
		swapWordController.process(1, 2, "a", "b");

	}

	public void testProcessRequirement() {

		Model model = new Model();
		Application app = new Application(model);
		ClientApp client = new ClientApp(model, app);
		app.setClient(client);
		SwapWordController swapWordController = new SwapWordController(app,
				new JTable());
		swapWordController.processWordRequirement(2, 1);

	}

	public void testReset() {

		Model model = new Model();
		Application app = new Application(model);
		ClientApp client = new ClientApp(model, app);
		app.setClient(client);
		SwapWordController swapWordController = new SwapWordController(app,
				new JTable());
		swapWordController.reset();
	}

	public void testSetUnable() {
		JPanel fields = new JPanel(new GridLayout(2, 1));
		JTextField field = new JTextField(10);
		JComboBox<String> comboBox = new JComboBox<>(new String[] { "ANYTYPE",
				"noun", "verb", "prefix" });
		Model model = new Model();
		Application app = new Application(model);
		ClientApp client = new ClientApp(model, app);
		app.setClient(client);
		SwapWordController swapWordController = new SwapWordController(app,
				new JTable());
		swapWordController.setUnable(fields, field, comboBox);

	}

	public void testMakeRequest() {
		Model model = new Model();
		Application app = new Application(model);
		ClientApp client = new ClientApp(model, app);
		app.setClient(client);
		SwapWordController swapWordController = new SwapWordController(app,
				new JTable());
		String rid = "uniqueid";
		String aid = "*";
		int n = 1;
		String[] offerTypes = { "noun" };
		String[] offerWords = { "have" };
		String[] requestTypes = { "noun" };
		String[] requestWords = { "*" };
		swapWordController.makeRequest(rid, aid, n, offerTypes, offerWords,
				requestTypes, requestWords);
	}

}
