package model;

import java.util.Stack;

import controller.Move;
import controller.MoveWord;

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
	Word selected;

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
//		System.out.println("recordMove_moves size: " + moves.size());
	}

	public Move removeLastMove() {
		if (moves.isEmpty()) {
			return null;
		}
		return moves.pop();
	}

	public int getMovesSize(){
		return moves.size();
	}
	
	public void setSelected(Word word) {
		selected = word;
	}

	public Word getSelected() {
		return selected;
	}
}
