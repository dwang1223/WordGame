package view;

import junit.framework.TestCase;
import model.Board;
import model.Model;
import model.Word;
import model.WordModel;

/**
 * This is test case for testing WordTable
 * 
 * @author Chen, Di Yu
 *
 */
public class TestWordTable extends TestCase {

	public void testConstruction() {

		Word word1 = new Word(1, 401, "hello", "noun", false, false, false,
				false);
		Word word2 = new Word(100, 401, "you", "noun", false, false, false,
				false);

		Board board = new Board();
		board.addWord(word1);
		board.addWord(word2);

		WordModel model = new WordModel(board);
		assertEquals(2, model.getColumnCount());
		assertEquals(2, model.getRowCount());

		assertEquals("hello", model.getValueAt(0, 0));
		assertEquals("noun", model.getValueAt(0, 1));
		assertEquals("", model.getValueAt(0, 2));

		model.sort("Word");
		assertEquals(0, board.words.indexOf(word1));
		model.sort("Type");
		assertEquals(0, board.words.indexOf(word1));
	}

	public void testWordTable() {
		Board board = new Board();
		Model model = new Model();
		Application app = new Application(model);

		WordTable wordTable = new WordTable(board, app);
	}

	public void testRefreshTable() {
		Board board = new Board();
		Model model = new Model();
		Application app = new Application(model);

		WordTable wordTable = new WordTable(board, app);
		wordTable.refreshTable(board);
	}

}