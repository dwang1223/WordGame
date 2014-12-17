package controller;

import java.awt.event.MouseAdapter;


import javax.swing.JOptionPane;

import model.Model;
import view.Application;
import view.ApplicationPanel;


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
		String number = JOptionPane.showInputDialog(this.app,
                "Type In A Number Of Words You Want To Swap", null);
		try{
			this.model.numberOfSwapWords = Integer.valueOf(number);
			System.out.println("user input number is "+this.model.numberOfSwapWords);
		}
		catch(Exception e){
			System.err.println("input number is not valid...");
			return;
		}
	}

}