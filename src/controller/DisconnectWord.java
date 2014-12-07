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
public class DisconnectWord extends Move {

	final Word word;
	final int newx;
	final int newy;
	final int oldx;
	final int oldy;
	final Board newBoard;
	final Board oldBoard;
	Model model;

	public DisconnectWord(Word word, int oldx, int oldy, int newx, int newy,
			Board oldBoard, Board newBoard, Model model) {
		this.word = word;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
		this.oldBoard = oldBoard;
		this.newBoard = CloneUtils.clone(newBoard);
		this.model = model;
	}

	@Override
	public boolean execute() {
		word.setLocation(newx, newy);
		return true;
	}

	@Override
	public boolean undo() {
		word.setLocation(oldx, oldy);
		model.setBoard(oldBoard);
		return true;
	}

	@Override
	public boolean redo() {
		word.setLocation(newx, newy);
		model.setBoard(newBoard);
		return false;
	}

}
