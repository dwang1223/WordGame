package controller;

import util.CloneUtils;
import model.Board;
import model.Model;
import model.Word;

/**
 * MoveWord Class
 * 
 * @author diwang
 *
 */
public class MoveWord extends Move {

	final Word word;
	final int newx;
	final int newy;
	int oldx;
	int oldy;
	boolean newIsProtected;
	boolean oldIsProtected;
	Board newBoard;
	Board oldBoard;
	Model model;

	public MoveWord(Word word, int oldx, int oldy, int newx, int newy,
			boolean oldIsProtected, boolean newIsProtected, Board oldBoard,
			Model model) {
		this.word = word;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
		this.oldIsProtected = oldIsProtected;
		this.newIsProtected = newIsProtected;
		this.oldBoard = CloneUtils.clone(oldBoard);
		this.newBoard = CloneUtils.clone(model.getBoard());
		this.model = model;
	}

	@Override
	public boolean execute() {
		word.setLocation(newx, newy);
		word.setProtected(newIsProtected);
		model.setBoard(newBoard);
		return true;
	}

	@Override
	public boolean undo() {
		word.setLocation(oldx, oldy);
		word.setProtected(oldIsProtected);
		model.setBoard(oldBoard);
		return true;
	}

}
