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
public class Row implements Serializable {
	/**
	 * Unique ID for serialized Row objects.
	 */
	private static final long serialVersionUID = 2174232094987029918L;
	private List<Word> wordList = new ArrayList<Word>();
	private boolean isSelected = false;

	public Row(Word word1, Word word2) {
		this.wordList.add(word1);
		this.wordList.add(word2);
	}

	public Row(Row row1, Row row2) {
		List<Word> wordList1 = row1.wordList;
		List<Word> wordList2 = row2.wordList;
		wordList1.addAll(wordList2);
		this.wordList = wordList1;
	}

	public void setLocation(int x, int y) {
		wordList.get(0).setLocation(x, y);
	}

	public int getX() {
		return wordList.get(0).getX();
	}

	public int getY() {
		return wordList.get(0).getY();
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

	public boolean removeWord(boolean isRight) {
		if (isRight) {
			this.wordList.remove(this.wordList.size() - 1);
		} else {
			this.wordList.remove(0);
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
