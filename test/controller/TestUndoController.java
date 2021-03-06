package controller;

import model.Board;
import model.Model;
import model.RealMove;
import view.Application;
import junit.framework.TestCase;

/**
 * TestUndoController Class
 * 
 * @author diwang
 *
 */
public class TestUndoController extends TestCase {
	public void testConstruction() {
		Board curBoard = new Board();
		Board oldBoard = new Board();
		Model model = new Model(curBoard);
		RealMove realMove = new RealMove(oldBoard, model);
		model.recordUndoMove(realMove);
		Application app = new Application(model);
		UndoController undoController = new UndoController(model, app);
		undoController.process();
		undoController.process();
	}
}
