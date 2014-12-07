package controller;

import model.Board;
import model.Model;
import model.Word;
import util.CloneUtils;

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
	final int oldx;
	final int oldy;
	final boolean newIsProtected;
	final boolean oldIsProtected;
	final Board newBoard;
	final Board oldBoard;
	Model model;

	public MoveWord(Word word, int oldx, int oldy, int newx, int newy,
			boolean oldIsProtected, boolean newIsProtected, Board oldBoard,
			Board newBoard, Model model) {
		this.word = word;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
		this.oldIsProtected = oldIsProtected;
		this.newIsProtected = newIsProtected;
		this.oldBoard = oldBoard;
		this.newBoard = CloneUtils.clone(newBoard);
		this.model = model;
	}

	@Override
	public boolean execute() {
		word.setLocation(newx, newy);
		word.setProtected(newIsProtected);
		// System.out.println("Move execute");
		// System.out.println("Move newBoard");
		// List<Word> words = newBoard.words;
		// for (Word word : words) {
		// System.out.println(word + word.getContent() + " inRow: "
		// + word.isInRow() + " X: " + word.getX() + " Y: "
		// + word.getY());
		// }
		// System.out.println("Move oldBoard");
		// List<Word> words1 = oldBoard.words;
		// for (Word word : words1) {
		// System.out.println(word + word.getContent() + " inRow: "
		// + word.isInRow() + " X: " + word.getX() + " Y: "
		// + word.getY());
		// }
		return true;
	}

	@Override
	public boolean undo() {
		word.setLocation(oldx, oldy);
		word.setProtected(oldIsProtected);
		model.setBoard(oldBoard);
		// System.out.println("Move undo");
		// System.out.println("Move newBoard");
		// List<Word> words = newBoard.words;
		// for (Word word : words) {
		// System.out.println(word + word.getContent() + " inRow: "
		// + word.isInRow() + " X: " + word.getX() + " Y: "
		// + word.getY());
		// }
		// System.out.println("Move oldBoard");
		// List<Word> words1 = oldBoard.words;
		// for (Word word : words1) {
		// System.out.println(word + word.getContent() + " inRow: "
		// + word.isInRow() + " X: " + word.getX() + " Y: "
		// + word.getY());
		// }
		return true;
	}

	@Override
	public boolean redo() {
		word.setLocation(newx, newy);
		word.setProtected(newIsProtected);
		model.setBoard(newBoard);
		// System.out.println("Move redo");
		// System.out.println("Move newBoard");
		// List<Word> words = newBoard.words;
		// for (Word word : words) {
		// System.out.println(word + word.getContent() + " inRow: "
		// + word.isInRow() + " X: " + word.getX() + " Y: "
		// + word.getY());
		// }
		// System.out.println("Move oldBoard");
		// List<Word> words1 = oldBoard.words;
		// for (Word word : words1) {
		// System.out.println(word + word.getContent() + " inRow: "
		// + word.isInRow() + " X: " + word.getX() + " Y: "
		// + word.getY());
		// }
		return true;
	}

}
