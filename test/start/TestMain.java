package start;

import model.Board;
import junit.framework.TestCase;

/**
 * TestMain Class
 * 
 * @author diwang
 *
 */
public class TestMain extends TestCase {
	public void testConstruction() {
		Board board = new Board();
		Main main = new Main();
		main.loadState(Main.defaultStorage);
		main.storeState(board, Main.defaultStorage);
	}
}
