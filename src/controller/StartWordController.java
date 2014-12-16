package controller;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import model.Board;
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
			boolean ok = true;
			// judge whether out of bound
			if (Board.isOutOfUnprotectedArea(word)) {
				ok = false;
			}
			if (!ok) {
				Toolkit.getDefaultToolkit().beep();
			} else {
				word.setProtected(false);
				model.getBoard().addWord(word);

				app.getWordTable().refreshTable();
				panel.redraw();
				panel.repaint();
			}
		}
	}

}
