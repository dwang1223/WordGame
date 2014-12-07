package controller;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Board;
import model.Model;
import model.Word;
import util.CloneUtils;
import view.Application;
import view.ApplicationPanel;

/**
 * MoveWordController Class
 * 
 * @author diwang
 *
 */
public class MoveWordController extends MouseAdapter {

	/** Needed for controller behavior. */
	Model model;
	Application app;
	ApplicationPanel panel;

	/** Original x,y where word was before move. */
	int originalx;
	int originaly;
	/** Original isProtected whether word was before move. */
	boolean originalIsProtected;
	Board originalBoard;

	/** Anchor point where first grabbed and delta from that location. */
	Point anchor;
	int deltaX;
	int deltaY;

	/** Button that started off. */
	int buttonType;

	/** Constructor holds onto key manager objects. */
	public MoveWordController(Model model, Application app) {
		this.model = model;
		this.app = app;
		this.panel = app.getWordPanel();
	}

	/** Set up press events but no motion events. */
	public void register() {
		panel.setActiveListener(this);
		panel.setActiveMotionListener(this);
	}

	/**
	 * Whenever mouse is pressed (left button), attempt to select object. This
	 * is a GUI controller.
	 */
	@Override
	public void mousePressed(MouseEvent me) {
		buttonType = me.getButton();
		select(me.getX(), me.getY());
	}

	/**
	 * Whenever mouse is dragged, attempt to start object. This is a GUI
	 * controller.
	 */
	@Override
	public void mouseDragged(MouseEvent me) {
		drag(me.getX(), me.getY());
	}

	/**
	 * Whenever mouse is released, complete the move. This is a GUI controller.
	 */
	@Override
	public void mouseReleased(MouseEvent me) {
		release(me.getX(), me.getY());
	}

	/** Separate out this function for testing purposes. */
	protected boolean select(int x, int y) {
		anchor = new Point(x, y);

		// pieces are returned in order of Z coordinate
		Word word = model.getBoard().findWord(anchor.x, anchor.y);
		if (word == null) {
			return false;
		}

		boolean ok = true;

		if (word.isInRow()) {
			ok = false;
		}
		if (!ok) {
			Toolkit.getDefaultToolkit().beep();
			return false;
		} else {
			// no longer in the board since we are moving it around...
			originalBoard = CloneUtils.clone(model.getBoard());
			model.getBoard().removeWord(word);
			model.setSelectedWord(word);
			originalx = word.getX();
			originaly = word.getY();
			originalIsProtected = word.isProtected();

			// set anchor for smooth moving
			deltaX = anchor.x - originalx;
			deltaY = anchor.y - originaly;

			// paint will happen once moves. This redraws state to prepare for
			// paint
			panel.redraw();
			return true;
		}
	}

	/** Separate out this function for testing purposes. */
	protected boolean drag(int x, int y) {
		// no board? no behavior! No dragging of right-mouse buttons...
		if (buttonType == MouseEvent.BUTTON3) {
			return false;
		}
		Word selected = model.getSelectedWord();

		if (selected == null) {
			return false;
		}

		panel.paintBackground(selected);
		int oldx = selected.getX();
		int oldy = selected.getY();

		selected.setLocation(x - deltaX, y - deltaY);

		boolean ok = true;
		// judge whether protect or release word
		if (Board.isOutOfUnprotectedArea(selected)) {
			// protect word
			for (Word word : model.getBoard().words) {
				// judge whether intersect
				if (word.intersects(selected)) {
					ok = false;
					break;
				}
				// judge whether out of bound
				else if (Board.isOutOfBound(selected)) {
					ok = false;
					break;
				}
			}
		}

		if (!ok) {
			selected.setLocation(oldx, oldy);
		} else {
			panel.paintWord(selected);
			panel.repaint();
		}

		return true;
	}

	/** Separate out this function for testing purposes. */
	protected boolean release(int x, int y) {
		Word selected = model.getSelectedWord();
		if (selected == null) {
			return false;
		}
		// if move the word to the border of two areas
		if (selected.getY() > (Board.heightOfProtectedArea - selected
				.getHeight()) && selected.getY() < Board.heightOfProtectedArea) {
			selected.setLocation(selected.getX(), Board.heightOfProtectedArea);
		}
		// judge whether protect or release word
		if (Board.isOutOfUnprotectedArea(selected)) {
			// protect word
			selected.setProtected(true);
		} else {
			selected.setProtected(false);
		}

		// now released we can create Move
		model.getBoard().addWord(selected);
		MoveWord move = new MoveWord(selected, originalx, originaly,
				selected.getX(), selected.getY(), originalIsProtected,
				selected.isProtected(), originalBoard, model.getBoard(), model);
		if (move.execute()) {
			model.recordUndoMove(move);
			model.clearRedoMoves();
		}

		// no longer selected
		model.setSelectedWord(null);

		panel.redraw();
		panel.repaint();
		return true;
	}
}
