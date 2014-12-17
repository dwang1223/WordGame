package controller;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import model.Model;
import model.Word;
import util.InitializeWord;
import view.Application;
import view.ApplicationPanel;

/**
 * StartWordController Class
 * 
 * @author diwang
 *
 */
public class StartWordController extends MouseAdapter {

	/** Needed for controller behavior. */
	Model model;
	Application app;
	ApplicationPanel panel;

	/** Constructor holds onto key manager objects. */
	public StartWordController(Model model, Application app) {
		this.model = model;
		this.app = app;
		this.panel = app.getWordPanel();
	}

	public void process() {
		ArrayList<Word> initWords = InitializeWord.initWords();
		for (Word word : initWords) {
			word.setProtected(false);
			model.getBoard().addWord(word);

			app.getWordTable().refreshTable();
			panel.redraw();
			panel.repaint();
		}
	}

}
