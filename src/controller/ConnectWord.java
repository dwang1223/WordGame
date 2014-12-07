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
	final int oldx;
	final int oldy;
	final Board newBoard;
	final Board oldBoard;
	Model model;

	public ConnectWord(Word word, int oldx, int oldy, int newx, int newy,
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
		// System.out.println("Connect redo");
		// System.out.println("Connect newBoard");
		// List<Word> words = newBoard.words;
		// for (Word word : words) {
		// System.out.println(word + word.getContent() + " inRow: "
		// + word.isInRow() + " X: " + word.getX() + " Y: "
		// + word.getY());
		// }
		// System.out.println("Connect oldBoard");
		// List<Word> words1 = oldBoard.words;
		// for (Word word : words1) {
		// System.out.println(word + word.getContent() + " inRow: "
		// + word.isInRow() + " X: " + word.getX() + " Y: "
		// + word.getY());
		// }
		return true;
	}

}
