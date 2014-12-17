package controller;

import model.Model;
import model.Move;
import view.Application;
import view.ApplicationPanel;

/**
 * RedoController Class
 * 
 * @author diwang
 *
 */
public class RedoController {
	Model model;
	Application app;
	ApplicationPanel canvas;

	public RedoController(Model m, Application app) {
		this.model = m;
		this.app = app;
		this.canvas = app.getWordPanel();
	}

	public boolean process() {
		Move move = model.removeLastRedoMove();
		if (move == null) {
			return false;
		}

		move.redo();

		// refresh table
		app.getWordTable().refreshTable(move.getNewBoard());

		// force board to redraw
		canvas.redraw();
		canvas.repaint();
		return true;
	}
}
