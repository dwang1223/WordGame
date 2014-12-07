package model;

import java.util.Stack;

import controller.Move;

/**
 * Model Class
 * 
 * @author diwang
 *
 */
public class Model {

	Board board;
	Stack<Move> undoMoves = new Stack<Move>();
	Stack<Move> redoMoves = new Stack<Move>();

	/** Currently selected shape (or null if none). */
	Word selectedWord;
	Row selectedRow;

	public Model(Board b) {
		board = b;
	}

	public Model() {
		this(new Board());
	}

	public void setBoard(Board b) {
		board = b;
	}

	public Board getBoard() {
		return board;
	}

	public void recordUndoMove(Move move) {
		undoMoves.add(move);
		// System.out
		// .println("recordUndoMove_undoMoves size: " + undoMoves.size());
	}

	public Move removeLastUndoMove() {
		if (undoMoves.isEmpty()) {
			return null;
		}
		Move undoMove = undoMoves.pop();
		redoMoves.add(undoMove);
		// System.out
		// .println("recordUndoMove_undoMoves size: " + undoMoves.size());
		// System.out
		// .println("recordRedoMove_redoMoves size: " + redoMoves.size());
		return undoMove;
	}

	public Move removeLastRedoMove() {
		if (redoMoves.isEmpty()) {
			return null;
		}
		Move redoMove = redoMoves.pop();
		undoMoves.add(redoMove);
		// System.out
		// .println("recordUndoMove_undoMoves size: " + undoMoves.size());
		// System.out
		// .println("recordRedoMove_redoMoves size: " + redoMoves.size());
		return redoMove;
	}

	public void clearRedoMoves() {
		redoMoves.clear();
	}

	public int getUndoMovesSize() {
		return undoMoves.size();
	}

	public int getRedoMovesSize() {
		return redoMoves.size();
	}

	public void setSelectedWord(Word word) {
		this.selectedWord = word;
	}

	public Word getSelectedWord() {
		return selectedWord;
	}

	public Row getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(Row selectedRow) {
		this.selectedRow = selectedRow;
	}

}
