package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import controller.Listener;

/**
 * Board Class
 * 
 * @author diwang
 *
 */
public class Board implements Serializable {
	private static final long serialVersionUID = 3875544904467596047L;
	public final static int widthOfFrame = 1000;
	public final static int heightOfFrame = 720;
	public final static int heightOfTopMenu = 50;
	public final static int widthOfBoard = 700;
	public final static int heightOfBoard = 600;
	public final static int widthOfProtectedArea = 700;
	public final static int heightOfProtectedArea = 400;
	public final static int widthOfUnprotectedArea = 700;
	public final static int heightOfUnprotectedArea = 200;
	public final static int heightOfBottomMenu = 50;

	/** Listeners. */
	ArrayList<Listener> listeners = new ArrayList<Listener>();
	// transient ArrayList<Listener> listeners = new ArrayList<Listener>();

	/** Words being maintained. */
	public ArrayList<Word> words = new ArrayList<Word>();
	/** Rows being maintained. */
	public ArrayList<Row> rows = new ArrayList<Row>();
	/** Poems being maintained. */
	public ArrayList<Poem> poems = new ArrayList<Poem>();

	public Row getRowFromRowListByWord(ArrayList<Row> rows, Word wordToConnect) {
		for (Row row : rows) {
			for (Word word : row.getWordList()) {
				if (word.equals(wordToConnect)) {
					return row;
				}
			}
		}
		return null;
	}

	public Poem getPoemFromPoemListByRow(ArrayList<Poem> poems, Row rowToConnect) {
		Word wordToConnect = rowToConnect.getWordList().get(0);
		for (Poem poem : poems) {
			ArrayList<Row> rows = poem.getRowList();
			for (Row row : rows) {
				for (Word word : row.getWordList()) {
					if (word.equals(wordToConnect)) {
						return poem;
					}
				}
			}
		}
		return null;
	}

	/** Add a listener. */
	public void addListener(Listener list) {
		listeners.add(list);
	}

	/** Remove a listener. */
	public void removeListener(Listener list) {
		listeners.remove(list);
	}

	/**
	 * get the unprotected words
	 * 
	 * @return
	 */
	public ArrayList<Word> getUnprotectedWords() {
		ArrayList<Word> unprotectedWords = new ArrayList<Word>();
		for (Word word : words) {
			if (word.isProtected() == false) {
				unprotectedWords.add(word);
			}
		}
		return unprotectedWords;
	}

	/**
	 * set isSearched of word through keyWord
	 * 
	 * @param keyWord
	 * @return
	 */
	public ArrayList<Word> setSearchedWords(String keyWord) {
		ArrayList<Word> searchedWords = new ArrayList<Word>();
		for (Word word : this.getUnprotectedWords()) {
			word.setSearched(false);
			if (keyWord.equals(word.getContent())) {
				word.setSearched(true);
			}
		}
		return searchedWords;
	}

	// public BoardMemento getState() {
	// return new BoardMemento(words);
	// }

	/** Add word to board. */
	public void addWord(Word word) {
		words.add(word);
		// state changed
		// notifyListeners();
	}

	/** Remove word from board. */
	public void removeWord(Word word) {
		words.remove(word);
		// state changed
		// notifyListeners();
	}

	/** Add row to board. */
	public void addRow(Row row) {
		rows.add(row);
	}

	/** Remove row from board. */
	public void removeRow(Row row) {
		rows.remove(row);
	}

	/** Add poem to board. */
	public void addPoem(Poem poem) {
		poems.add(poem);
	}

	/** Remove poem from board. */
	public void removePoem(Poem poem) {
		poems.remove(poem);
	}

	/** Remove poem from board. */
	public void removePoemByIndex(int index) {
		poems.remove(index);
	}

	/** Return shape that intersects (x,y) point. */
	public Word findWord(int x, int y) {
		for (Word word : words) {
			if (word.intersect(x, y)) {
				return word;
			}
		}

		return null;
	}

	/** Return all words in the board. */
	public Iterator<Word> iterator() {
		return words.iterator();
	}

	/** Return the number of words on the board. */
	public int wordSize() {
		return words.size();
	}

	/** Return the given word by index position. */
	public Word getWordByIndex(int index) {
		return words.get(index);
	}

	/** Sort words using given comparator. */
	public void sortUnprotectedWords(Comparator<Word> comparator) {
		Collections.sort(this.getUnprotectedWords(), comparator);
	}

	/**
	 * Notify all listeners.
	 * 
	 * During this event, no new changes can happen.
	 */
	void notifyListeners() {
		synchronized (listeners) {
			for (Listener list : listeners) {
				list.update();
			}
		}
	}

	public static boolean isOutOfBound(Word word) {
		int widthOfWord = word.getWidth();
		int heightOfWord = word.getHeight();
		int x = word.getX();
		int y = word.getY();
		int x1 = 0;
		int y1 = 0;
		int x2 = Board.widthOfBoard - widthOfWord;
		int y2 = Board.heightOfBoard - heightOfWord;
		if (((x1 > x) || (x > x2)) || ((y1 > y) || (y > y2))) {
			return true;
		}
		return false;
	}

	public static boolean isOutOfProtectedArea(Word word) {
		int widthOfWord = word.getWidth();
		int heightOfWord = word.getHeight();
		int x = word.getX();
		int y = word.getY();
		int x1 = 0;
		int y1 = 0;
		int x2 = Board.widthOfProtectedArea - widthOfWord;
		int y2 = Board.heightOfProtectedArea - heightOfWord;
		if (((x1 > x) || (x > x2)) || ((y1 > y) || (y > y2))) {
			return true;
		}
		return false;
	}

	public static boolean isOutOfUnprotectedArea(Word word) {
		int widthOfWord = word.getWidth();
		int heightOfWord = word.getHeight();
		int x = word.getX();
		int y = word.getY();
		int x1 = 0;
		int y1 = Board.heightOfProtectedArea;
		int x2 = Board.widthOfBoard - widthOfWord;
		int y2 = Board.heightOfBoard - heightOfWord;
		if (((x1 > x) || (x > x2)) || ((y1 > y) || (y > y2))) {
			return true;
		}
		return false;
	}
}
