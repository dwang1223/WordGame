package controller;

import model.Board;
import model.Model;
import model.Row;
import util.CloneUtils;

/**
 * MoveWord Class
 * 
 * @author diwang
 *
 */
public class MoveRow extends Move {

	final Row row;
	final int newx;
	final int newy;
	int oldx;
	int oldy;
	Board newBoard;
	Board oldBoard;
	Model model;

	public MoveRow(Row row, int oldx, int oldy, int newx, int newy,
			Board oldBoard, Model model) {
		this.row = row;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
		this.oldBoard = CloneUtils.clone(oldBoard);
		this.newBoard = CloneUtils.clone(model.getBoard());
		this.model = model;
	}

	@Override
	public boolean execute() {
		row.setLocation(newx, newy);
		model.setBoard(newBoard);
		return true;
	}

	@Override
	public boolean undo() {
		row.setLocation(oldx, oldy);
		model.setBoard(oldBoard);
		return true;
	}

}
