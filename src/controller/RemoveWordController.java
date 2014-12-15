package controller;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Board;
import model.Model;
import model.Word;
import view.Application;
import view.ApplicationPanel;

/**
 * RemoveWordController Class
 * 
 * @author diwang
 *
 */
public class RemoveWordController extends MouseAdapter {

	/** Needed for controller behavior. */
	Model model;
	Application app;
	ApplicationPanel panel;

	/** Constructor holds onto key manager objects. */
	public RemoveWordController(Model model, Application app) {
		this.model = model;
		this.app = app;
		this.panel = app.getWordPanel();
	}

	/** Set up press events but no motion events. */
	public void register() {
		panel.setActiveListener(this);
		panel.setActiveMotionListener(null);
	}

	/**
	 * Whenever mouse is pressed (left button), attempt to add object. This is a
	 * GUI controller.
	 */
	@Override
	public void mousePressed(MouseEvent me) {
		process(me.getX(), me.getY());
	}

	/** Separate out this function for testing purposes. */
	protected boolean process(int x, int y) {
		Word word = model.getBoard().findWord(x, y);
		if (word == null) {
			return false;
		}

		boolean ok = true;
		// judge whether out of bound
		if (Board.isOutOfUnprotectedArea(word)) {
			ok = false;
		}
		if (!ok) {
			Toolkit.getDefaultToolkit().beep();
		} else {
			model.getBoard().removeWord(word);

			app.getWordTable().refreshTable();
			panel.redraw();
			panel.repaint();
		}

		return true;
	}
}
