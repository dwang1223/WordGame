package controller;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Board;
import model.Model;
import model.Word;
import view.ApplicationPanel;

public class AddWordController extends MouseAdapter {

	/** Needed for controller behavior. */
	Model model;
	ApplicationPanel panel;

	/** Constructor holds onto key manager objects. */
	public AddWordController(Model model, ApplicationPanel panel) {
		this.model = model;
		this.panel = panel;
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
	protected void process(int x, int y) {
		// alternate to demonstrate swapping
		Word word;
		word = new Word(x, y, "Di Wang " + x, false, false, false);
		// java.util.Random random = new java.util.Random();
		// if (random.nextFloat() < 0.5) {
		// word = new Word(x, y, "Di Wang");
		// } else {
		// word = new Word(x, y, "ObjectRiver");
		// }

		boolean ok = true;
		// judge whether out of bound
		if (Board.isOutOfUnprotectedArea(word)) {
			ok = false;
		}
		// else {
		// for (Word w : model.getBoard()) {
		// // judge whether intersect
		// if (w.intersects(word)) {
		// ok = false;
		// break;
		// }
		// }
		// }

		if (!ok) {
			Toolkit.getDefaultToolkit().beep();
		} else {
			word.setProtected(false);
			model.getBoard().addWord(word);
			panel.redraw();
			panel.repaint();
		}
	}

}
