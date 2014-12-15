package view;

import junit.framework.TestCase;
import model.Board;
import model.Model;
import util.CloneUtils;

public class TestWordTable extends TestCase {
	public void testConstruction() {
			Board board = new Board();
			Model model = new Model();
			ApplicationPanel panel = new ApplicationPanel(model);
			WordTable wordTable = new WordTable(board, panel);
			WordTable wordTable2 = CloneUtils.clone(wordTable);
//			RefreshTableListener refreshTableListener = new RefreshTableListener(wordTable);
//			RefreshTableListener refreshTableListener2 = CloneUtils.clone(refreshTableListener);
//			board.addListener(refreshTableListener);
//			Board board2 = CloneUtils.clone(board);
	}
}
