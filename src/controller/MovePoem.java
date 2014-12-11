package controller;

import model.Board;
import model.Model;
import model.Poem;
import util.CloneUtils;

/**
 * MovePoem Class
 * 
 * @author diwang
 *
 */
public class MovePoem extends Move {

	final Poem poem;
	final int newx;
	final int newy;
	final int oldx;
	final int oldy;
	final Board newBoard;
	final Board oldBoard;
	Model model;

	public MovePoem(Poem poem, int oldx, int oldy, int newx, int newy,
			Board oldBoard, Model model) {
		this.poem = poem;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
		this.oldBoard = oldBoard;
		this.newBoard = CloneUtils.clone(model.getBoard());
		this.model = model;
	}

	@Override
	public boolean execute() {
		poem.setLocation(newx, newy);
		return true;
	}

	@Override
	public boolean undo() {
		poem.setLocation(oldx, oldy);
		model.setBoard(oldBoard);
		return true;
	}

	@Override
	public boolean redo() {
		poem.setLocation(newx, newy);
		model.setBoard(newBoard);
		return false;
	}

}
