package controller;

import model.Model;
import view.ApplicationPanel;

public class RedoController {
	Model model;
	ApplicationPanel canvas;

	public RedoController(Model m, ApplicationPanel canvas) {
		this.model = m;
		this.canvas = canvas;
	}

	public boolean process() {
		Move m = model.removeLastRedoMove();
		if (m == null) {
			return false;
		}

		m.redo();

		// force board to redraw
		canvas.redraw();
		canvas.repaint();
		return true;
	}
}
