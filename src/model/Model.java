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
	Stack<Move> moves = new Stack<Move>();

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

	public void recordMove(Move move) {
		moves.add(move);
		// System.out.println("recordMove_moves size: " + moves.size());
	}

	public Move removeLastMove() {
		if (moves.isEmpty()) {
			return null;
		}
		return moves.pop();
	}

	public int getMovesSize() {
		return moves.size();
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
