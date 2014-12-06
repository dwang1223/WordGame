package controller;

import model.Board;
import model.Model;
import model.Word;
import util.CloneUtils;

/**
 * ConnectWord Class
 * 
 * @author diwang
 *
 */
public class ConnectWord extends Move {

	final Word word;
	final int newx;
	final int newy;
	int oldx;
	int oldy;
	boolean newIsInRow;
	boolean oldIsInRow;
	Board newBoard;
	Board oldBoard;
	Model model;

	public ConnectWord(Word word, int oldx, int oldy, int newx, int newy,
			boolean oldIsInRow, boolean newIsInRow, Board oldBoard, Model model) {
		this.word = word;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
		this.oldIsInRow = oldIsInRow;
		this.newIsInRow = newIsInRow;
		this.oldBoard = CloneUtils.clone(oldBoard);
		this.newBoard = CloneUtils.clone(model.getBoard());
		this.model = model;
	}

	@Override
	public boolean execute() {
		word.setLocation(newx, newy);
		word.setInRow(newIsInRow);
		model.setBoard(newBoard);
		return true;
	}

	@Override
	public boolean undo() {
		word.setLocation(oldx, oldy);
		word.setInRow(oldIsInRow);
		model.setBoard(oldBoard);
		return true;
	}

}
