package controller;

import model.Model;
import view.ApplicationPanel;

public class UndoController {
	Model model;
	ApplicationPanel canvas;

	public UndoController(Model m, ApplicationPanel canvas) {
		this.model = m;
		this.canvas = canvas;
	}

	public boolean process() {
		Move m = model.removeLastMove();
		if (m == null) {
			return false;
		}

		m.undo();

		// force board to redraw
		canvas.redraw();
		canvas.repaint();
		return true;
	}
}
