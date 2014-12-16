package controller;

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
 * ReleasePoemController Class
 * 
 * @author diwang
 *
 */
public class ReleasePoemController extends MouseAdapter {

	/** Needed for controller behavior. */
	Model model;
	Application app;
	ApplicationPanel panel;

	Board originalBoard;

	/** Constructor holds onto key manager objects. */
	public ReleasePoemController(Model model, Application app) {
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
		process(me.getX(), me.getY());
	}

	/** Separate out this function for testing purposes. */
	protected boolean process(int x, int y) {
		Word word = model.getBoard().findWord(x, y);
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

			int randomX;
			int randomY;
			// release all the words in this poem
			for (Row r : poem.getRowList()) {
				for (Word w : r.getWordList()) {
					w.setInPoem(false);
					w.setInRow(false);
					w.setProtected(false);
					w.setSearched(false);
					randomX = (int) Math.round(Math.random()
							* (Board.widthOfUnprotectedArea - w.getContent()
									.length() * 10));
					randomY = (int) Math.round(Math.random()
							* (Board.heightOfUnprotectedArea - 25)
							+ Board.heightOfProtectedArea);
					w.setLocation(randomX, randomY);
				}
				r.getWordList().clear();
				model.getBoard().removeRow(r);
			}
			poem.getRowList().clear();
			model.getBoard().removePoem(poem);

			RealMove realMove = new RealMove(originalBoard, model);
			realMove.execute();
			model.recordUndoMove(realMove);
			model.clearRedoMoves();

			// paint will happen once moves. This redraws state to prepare for
			// paint
			app.getWordTable().refreshTable();
			panel.redraw();
			panel.repaint();
			return true;
		}
	}

}
