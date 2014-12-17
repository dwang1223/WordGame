package model;

import util.CloneUtils;

/**
 * RealMove Class
 * 
 * @author diwang
 *
 */
public class RealMove extends Move {

	Board curBoard;
	Board newBoard;
	Board oldBoard;
	Model model;

	public RealMove(Board oldBoard, Model model) {
		this.oldBoard = oldBoard;
		// this.newBoard = CloneUtils.clone(newBoard);
		this.model = model;
	}

	@Override
	public boolean execute() {
		curBoard = model.getBoard();
		this.newBoard = CloneUtils.clone(curBoard);
		return true;
	}

	@Override
	public boolean undo() {
		model.setBoard(oldBoard);
		return true;
	}

	@Override
	public boolean redo() {
		model.setBoard(CloneUtils.clone(newBoard));
		return true;
	}

	@Override
	public Board getOldBoard() {
		return oldBoard;
	}

	@Override
	public Board getNewBoard() {
		return newBoard;
	}

}
