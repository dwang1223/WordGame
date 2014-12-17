package controller;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import model.Board;
import model.Model;
import model.Poem;
import model.RealMove;
import model.Row;
import model.Word;
import util.CloneUtils;
import view.Application;
import view.ApplicationPanel;

/**
 * ConnectRowController Class
 * 
 * @author diwang
 *
 */
public class ConnectRowController extends MouseAdapter {

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
	public ConnectRowController(Model model, Application app) {
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

		if (!word.isInRow()) {
			ok = false;
		} else {
			if (word.isInPoem()) {
				ok = false;
			}
		}
		if (!ok) {
			Toolkit.getDefaultToolkit().beep();
			return false;
		} else {
			// no longer in the board since we are moving it around...
			originalBoard = CloneUtils.clone(model.getBoard());
			// move the row where the word is
			ArrayList<Row> rows = model.getBoard().rows;
			Row row = model.getBoard().getRowFromRowListByWord(rows, word);
			model.setSelectedRow(row);
			originalx = row.getX();
			originaly = row.getY();

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
		Row selectedRow = model.getSelectedRow();

		for (Word word : selectedRow.getWordList()) {
			panel.paintBackground(word);
		}
		int oldx = selectedRow.getX();
		int oldy = selectedRow.getY();
		selectedRow.setLocation(x - deltaX, y - deltaY);

		boolean ok = true;
		ArrayList<Row> rows = model.getBoard().rows;
		for (Word word : selectedRow.getWordList()) {
			// judge whether out of bound
			if (Board.isOutOfProtectedArea(word)) {
				ok = false;
			} else {
				for (Word w : model.getBoard().words) {
					Row row = model.getBoard().getRowFromRowListByWord(rows, w);
					// judge whether intersect
					if (row != selectedRow) {
						// if w is not in the row where word is in
						if (w.intersects(word)) {
							ok = false;
							break;
						}
					}
				}
			}
		}

		if (!ok) {
			selectedRow.setLocation(oldx, oldy);
		} else {
			panel.redraw();
			panel.repaint();
		}
		return true;
	}

	/** Separate out this function for testing purposes. */
	protected boolean release(int x, int y) {
		Row selectedRow = model.getSelectedRow();

		int xofSelectedRow = selectedRow.getX();
		int yofSelectedRow = selectedRow.getY();
		// connect row
		ArrayList<Row> rows = model.getBoard().rows;
		ArrayList<Poem> poems = model.getBoard().poems;
		for (Row row : rows) {
			int xofRow = row.getX();
			int yofRow = row.getY();
			int widthofRow = row.getWidth();
			int heightofRow = row.getHeight();
			int distanceToTop = yofRow
					- (yofSelectedRow + selectedRow.getHeight());
			int distanceToBottom = yofSelectedRow - (yofRow + heightofRow);
			if (distanceToTop > -5 && distanceToTop < 20) {
				// if selectedRow is at the top of row
				if ((xofRow - selectedRow.getWidth()) < xofSelectedRow
						&& xofSelectedRow < (xofRow + widthofRow)) {
					selectedRow.setLocation(xofSelectedRow, yofRow
							- selectedRow.getHeight());
					if (!row.isInPoem()) {
						// If row is not in poem, create a new poem
						Poem poem = new Poem(selectedRow, row);
						model.getBoard().addPoem(poem);
					} else {
						// If row is in poem, add selectedRow to the poem
						Poem poem = model.getBoard().getPoemFromPoemListByRow(
								poems, row);
						poem.addRow(selectedRow, false);
					}
				}
			} else if (distanceToBottom > -5 && distanceToBottom < 20) {
				// if selectedRow is at the bottom of row
				if ((xofRow - selectedRow.getWidth()) < xofSelectedRow
						&& xofSelectedRow < (xofRow + widthofRow)) {
					selectedRow.setLocation(xofSelectedRow,
							yofRow + row.getHeight());
					if (!row.isInPoem()) {
						// If row is not in poem, create a new poem
						Poem poem = new Poem(row, selectedRow);
						model.getBoard().addPoem(poem);
					} else {
						// If row is in poem, add selectedRow to the poem
						Poem poem = model.getBoard().getPoemFromPoemListByRow(
								poems, row);
						poem.addRow(selectedRow, true);
					}
				}
			}
		}

		// now released we can create Move
		RealMove realMove = new RealMove(originalBoard, model);
		realMove.execute();
		model.recordUndoMove(realMove);
		model.clearRedoMoves();

		// no longer selected
		model.setSelectedRow(null);

		panel.redraw();
		panel.repaint();
		return true;
	}
}
