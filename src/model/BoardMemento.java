package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * BoardMemento Class
 * 
 * @author diwang
 *
 */
public class BoardMemento implements Serializable {

	/**
	 * Unique tag for memento objects on disk
	 * 
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = -2843739094020112892L;

	ArrayList<Word> stored = new ArrayList<Word>();

	/**
	 * Has special permissions to be able to inspect all attributes of Word
	 * objects and make copy as needed.
	 * 
	 * @param shapes
	 */
	public BoardMemento(ArrayList<Word> words) {
		for (Word word : words) {
			stored.add(new Word(word.getX(), word.getY(), word.getContent(),
					word.isProtected(), word.isInRow(), word.isInPoem()));
		}
	}
}
