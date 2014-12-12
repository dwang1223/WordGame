package controller;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import model.Board;
import model.Model;
import model.Poem;
import model.Row;
import model.Word;
import util.CloneUtils;
import view.Application;
import view.ApplicationPanel;

/**
 * MovePoemController Class
 * 
 * @author diwang
 *
 */
public class MovePoemController extends MouseAdapter {

	/** Needed for controller behavior. */
	Model model;
	Application app;
	ApplicationPanel panel;

	/** Original x,y where row was before move. */
	int originalx;
	int originaly;
	Board originalBoard;

	/** Anchor point where first grabbed and delta from that location. */
	Point anchor;
	int deltaX;
	int deltaY;

	/** Button that started off. */
	int buttonType;

	/** Constructor holds onto key manager objects. */
	public MovePoemController(Model model, Application app) {
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

		if (!word.isInPoem()) {
			ok = false;
		}
		if (!ok) {
			Toolkit.getDefaultToolkit().beep();
			return false;
		} else {
			// no longer in the board since we are moving it around...
			originalBoard = CloneUtils.clone(model.getBoard());
			// move the poem where the word is
			ArrayList<Poem> poems = model.getBoard().poems;
			ArrayList<Row> rows = model.getBoard().rows;
			Row row = model.getBoard().getRowFromRowListByWord(rows, word);
			Poem poem = model.getBoard().getPoemFromPoemListByRow(poems, row);
			model.setSelectedPoem(poem);

			originalx = poem.getX();
			originaly = poem.getY();

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

		Poem selectedPoem = model.getSelectedPoem();
		if (selectedPoem == null) {
			return false;
		}

		for (Row row : selectedPoem.getRowList()) {
			for (Word word : row.getWordList()) {
				panel.paintBackground(word);
			}
		}

		int oldx = selectedPoem.getX();
		int oldy = selectedPoem.getY();
		selectedPoem.setLocation(x - deltaX, y - deltaY);

		boolean ok = true;
		ArrayList<Poem> poems = model.getBoard().poems;
		ArrayList<Row> rows = model.getBoard().rows;

		for (Row row : selectedPoem.getRowList()) {
			for (Word word : row.getWordList()) {
				// judge whether out of bound
				if (Board.isOutOfProtectedArea(word)) {
					ok = false;
				} else {
					for (Word w : model.getBoard().words) {
						Row r = model.getBoard().getRowFromRowListByWord(rows,
								w);
						Poem poem = null;
						if (r != null) {
							poem = model.getBoard().getPoemFromPoemListByRow(
									poems, r);
						}
						// judge whether intersect
						if (poem != selectedPoem) {
							// if w is not in the poem where word is in
							if (w.intersects(word)) {
								ok = false;
								break;
							}
						}
					}
				}
			}
		}

		if (!ok) {
			selectedPoem.setLocation(oldx, oldy);
		} else {
			panel.redraw();
			panel.repaint();
		}
		return true;
	}

	/** Separate out this function for testing purposes. */
	protected boolean release(int x, int y) {
		Poem selectedPoem = model.getSelectedPoem();
		if (selectedPoem == null) {
			return false;
		}
		// now released we can create Move
		MovePoem move = new MovePoem(selectedPoem, originalx, originaly,
				selectedPoem.getX(), selectedPoem.getY(), originalBoard, model);
		if (move.execute()) {
			model.recordUndoMove(move);
			model.clearRedoMoves();
		}

		// no longer selected
		model.setSelectedRow(null);

		panel.redraw();
		panel.repaint();
		return true;
	}
}
