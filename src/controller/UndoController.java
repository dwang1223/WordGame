package controller;

import model.Model;
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
		Move m = model.removeLastUndoMove();
		if (m == null) {
			return false;
		}

		m.undo();

		// refresh table
		app.getWordTable().refreshTable(m.getOldBoard());

		// force board to redraw
		canvas.redraw();
		canvas.repaint();
		return true;
	}
}
