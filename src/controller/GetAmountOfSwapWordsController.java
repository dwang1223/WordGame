package controller;

import java.awt.event.MouseAdapter;

import javax.swing.JOptionPane;

import model.Model;
import view.Application;
import view.ApplicationPanel;

/**
 * GetAmountOfSwapWordsController
 * 
 * @author Di Yu
 *
 */
public class GetAmountOfSwapWordsController extends MouseAdapter {

	/** Needed for controller behavior. */
	Model model;
	Application app;
	ApplicationPanel panel;

	/** Constructor holds onto key manager objects. */
	public GetAmountOfSwapWordsController(Model model, Application app) {
		this.model = model;
		this.app = app;
		this.panel = app.getWordPanel();
	}

	public void process() {
		this.app.getClient().swapRequested = true;
		String number = JOptionPane.showInputDialog(this.app,
				"Type In A Number Of Words You Want To Swap", null);
		try {
			if (Integer.valueOf(number) >= 5) {
				JOptionPane.showMessageDialog(app,
						"cannot choose number out of five", "Exception",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			app.getClient().numberOfSwapWords = Integer.valueOf(number);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(app,
					"input is not a valid number...", "Exception",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}
	}

}