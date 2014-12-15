package controller;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import model.Board;
import model.Model;
import model.Row;
import model.Word;
import util.CloneUtils;
import view.Application;
import view.ApplicationPanel;

/**
 * ConnectWordController Class
 * 
 * @author diwang
 *
 */
public class ConnectWordController extends MouseAdapter {

	/** Needed for controller behavior. */
	Model model;
	Application app;
	ApplicationPanel panel;

	/** Original x,y where word was before move. */
	int originalx;
	int originaly;
	/** Original isInRow whether word was before move. */
	Board originalBoard;

	/** Anchor point where first grabbed and delta from that location. */
	Point anchor;
	int deltaX;
	int deltaY;

	/** Button that started off. */
	int buttonType;

	/** Constructor holds onto key manager objects. */
	public ConnectWordController(Model model, Application app) {
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
		// judge whether out of bound
		if (Board.isOutOfProtectedArea(word)) {
			ok = false;
		} else {
			if (word.isInRow()) {
				ok = false;
			}
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
		// judge whether out of bound
		// just connect protected words
		if (Board.isOutOfProtectedArea(selected)) {
			ok = false;
		} else {
			for (Word word : model.getBoard().words) {
				// judge whether intersect
				if (word.intersects(selected)) {
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

		// if (!selected.isInRow()) {// if selected is not in row
		ArrayList<Word> words = model.getBoard().words;
		ArrayList<Row> rows = model.getBoard().rows;
		// connect words which are close to each other
		for (Word word : words) {
			if (word.isProtected()) {// word should be protected
				if (!selected.equals(word)) {// word should be different
												// from
												// selected
					double distanceR = getDistance(selected.getX(),
							selected.getY(), word.getX() + word.getWidth(),
							word.getY());
					double distanceL = getDistance(
							selected.getX() + selected.getWidth(),
							selected.getY(), word.getX(), word.getY());
					if (distanceR < 20) {// In the right connectable area
						selected.setLocation(word.getX() + word.getWidth(),
								word.getY());
						if (!word.isInRow()) {
							// If word is not in row, create a new row
							Row row = new Row(word, selected);
							model.getBoard().addRow(row);
						} else {
							// If word is in row, add selected word to the
							// row
							Row row = model.getBoard().getRowFromRowListByWord(
									rows, word);
							row.addWord(selected, true);
						}
					} else if (distanceL < 20) {// In the left connectable
												// area
						selected.setLocation(word.getX() - selected.getWidth(),
								word.getY());
						if (!word.isInRow()) {
							// If word is not in row, create a new row
							Row row = new Row(selected, word);
							model.getBoard().addRow(row);
						} else {
							// If word is in row, add selected word to the
							// row
							Row row = model.getBoard().getRowFromRowListByWord(
									rows, word);
							row.addWord(selected, false);
						}

					}
				}
			}
			// System.out.println(word.getContent());
		}
		// }

		// now released we can create Move
		model.getBoard().addWord(selected);

		RealMove realMove = new RealMove(originalBoard, model);
		realMove.execute();
		model.recordUndoMove(realMove);
		model.clearRedoMoves();

		// no longer selected
		model.setSelectedWord(null);

		panel.redraw();
		panel.repaint();
		return true;
	}

	public double getDistance(int x1, int y1, int x2, int y2) {
		int dx = x2 - x1;
		int dy = y2 - y1;
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}
}
