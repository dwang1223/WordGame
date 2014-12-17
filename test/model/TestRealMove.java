package model;

import junit.framework.TestCase;

/**
 * TestRealMove Class
 * 
 * @author diwang
 *
 */
public class TestRealMove extends TestCase {
	public void testConstruction() {
		Board curBoard = new Board();
		Board oldBoard = new Board();
		Model model = new Model(curBoard);
		RealMove realMove = new RealMove(oldBoard, model);
		realMove.execute();
		realMove.undo();
		realMove.redo();
		Board oldBoard2 = realMove.getOldBoard();
		Board newBoard = realMove.getNewBoard();
	}
}
