package controller;

import model.Model;
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
		Move m = model.removeLastRedoMove();
		if (m == null) {
			return false;
		}

		m.redo();

		// refresh table
		app.getWordTable().refreshTable(m.getNewBoard());

		// force board to redraw
		canvas.redraw();
		canvas.repaint();
		return true;
	}
}
