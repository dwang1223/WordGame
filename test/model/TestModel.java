package model;

import junit.framework.TestCase;

/**
 * TestModel Class
 * 
 * @author diwang
 *
 */
public class TestModel extends TestCase {
	public void testConstruction() {
		Board board = new Board();
		Model model1 = new Model();
		model1.setBoard(board);
		Model model = new Model(board);
		Board board2 = model.getBoard();
		assertEquals(board, board2);
	}

	public void testMoveStack() {
		Board board = new Board();
		Model model = new Model();
		RealMove realMove = new RealMove(board, model);
		model.recordUndoMove(realMove);
		int undoMovesSize = model.getUndoMovesSize();
		assertEquals(1, undoMovesSize);
		model.removeLastUndoMove();
		int redoMovesSize = model.getRedoMovesSize();
		assertEquals(1, redoMovesSize);
		model.removeLastRedoMove();
		model.removeLastRedoMove();
		model.removeLastUndoMove();
		model.removeLastUndoMove();
		model.clearUndoMoves();
		model.clearRedoMoves();
	}

	public void testSelect() {
		Model model = new Model();
		Word selectedWord = new Word(10, 10, "king", "noun", false, false,
				false, false);
		Word word1 = new Word(10, 10, "di", "noun", false, false, false, false);
		Word word2 = new Word(20, 20, "wang", "noun", false, false, false,
				false);
		Row selectedRow = new Row(word1, word2);
		Poem selectedPoem = new Poem(selectedRow, selectedRow);
		model.setSelectedWord(selectedWord);
		model.setSelectedRow(selectedRow);
		model.setSelectedPoem(selectedPoem);
		Word word = model.getSelectedWord();
		Row row = model.getSelectedRow();
		Poem poem = model.getSelectedPoem();
		assertEquals(selectedWord, word);
		assertEquals(selectedRow, row);
		assertEquals(selectedPoem, poem);
	}
}
