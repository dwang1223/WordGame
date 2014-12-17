package controller;

import model.Model;
import model.Move;
import view.Application;
import view.ApplicationPanel;

/**
 * UndoController Class
 * 
 * @author diwang
 *
 */
public class UndoController {
	Model model;
	Application app;
	ApplicationPanel canvas;

	public UndoController(Model m, Application app) {
		this.model = m;
		this.app = app;
		this.canvas = app.getWordPanel();
	}

	public boolean process() {
		Move move = model.removeLastUndoMove();
		if (move == null) {
			return false;
		}

		move.undo();

		// refresh table
		app.getWordTable().refreshTable(move.getOldBoard());

		// force board to redraw
		canvas.redraw();
		canvas.repaint();
		return true;
	}
}
