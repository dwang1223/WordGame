package model;

import java.util.Stack;

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
	Poem selectedPoem;

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
	}

	public Move removeLastUndoMove() {
		if (undoMoves.isEmpty()) {
			return null;
		}
		Move undoMove = undoMoves.pop();
		redoMoves.add(undoMove);
		return undoMove;
	}

	public Move removeLastRedoMove() {
		if (redoMoves.isEmpty()) {
			return null;
		}
		Move redoMove = redoMoves.pop();
		undoMoves.add(redoMove);
		return redoMove;
	}

	public void clearUndoMoves() {
		undoMoves.clear();
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

	public Poem getSelectedPoem() {
		return selectedPoem;
	}

	public void setSelectedPoem(Poem selectedPoem) {
		this.selectedPoem = selectedPoem;
	}

}
