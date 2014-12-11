package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Row Class
 * 
 * @author diwang
 *
 */
public class Row implements Serializable, Cloneable {
	/**
	 * Unique ID for serialized Row objects.
	 */
	private static final long serialVersionUID = 2174232094987029918L;
	private ArrayList<Word> wordList = new ArrayList<Word>();
	private boolean isSelected;

	public Row(Word word1, Word word2) {
		word1.setInRow(true);
		word2.setInRow(true);
		this.wordList.add(word1);
		this.wordList.add(word2);
	}

	public Row(Row row1, Row row2) {
		ArrayList<Word> wordList1 = row1.wordList;
		ArrayList<Word> wordList2 = row2.wordList;
		wordList1.addAll(wordList2);
		this.wordList = wordList1;
	}

	public void setLocation(int x, int y) {
		int xofWord = x;
		int yofWord = y;
		for (int i = 0; i < wordList.size(); i++) {
			Word w = wordList.get(i);
			w.setLocation(xofWord, yofWord);
			xofWord = xofWord + w.getWidth();
		}
	}

	public int getX() {
		return wordList.get(0).getX();
	}

	public int getY() {
		return wordList.get(0).getY();
	}

	public int getWidth() {
		int width = 0;
		for (Word word : wordList) {
			width = width + word.getWidth();
		}
		return width;
	}

	public int getHeight() {
		return wordList.get(0).getHeight();
	}

	public boolean isInPoem() {
		return wordList.get(0).isInPoem();
	}

	public List<Word> getWordList() {
		return wordList;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean addWord(Word word, boolean isRight) {
		if (word != null) {
			word.setInRow(true);
			if (isRight) {
				this.wordList.add(word);
			} else {
				int rowSize = wordList.size();
				List<Word> wordListTemp = new ArrayList<Word>();
				for (int i = 0; i < rowSize; i++) {
					wordListTemp.add(wordList.get(i));
				}
				for (int i = 0; i < rowSize - 1; i++) {
					this.wordList.set(i + 1, wordListTemp.get(i));
				}
				this.wordList.add(wordListTemp.get(rowSize - 1));
				this.wordList.set(0, word);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean removeWord(Word word) {
		word.setInRow(false);
		wordList.remove(word);
		if (wordList.size() == 1) {
			// if the size of row is 1
			wordList.get(0).setInRow(false);
			return false;
		}
		return true;
	}

	public void showRow() {
		for (Word word : wordList) {
			System.out.print(word.getContent() + " ");
		}
		System.out.println();
	}
}
