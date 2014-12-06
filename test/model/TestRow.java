package model;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * TestRow Class
 * 
 * @author diwang
 *
 */
public class TestRow extends TestCase {
	public void testConstruction() {
		Word word1 = new Word(10, 10, "di", false, false);
		Word word2 = new Word(20, 20, "wang", false, false);
		Row row1 = new Row(word1, word2);
		assertEquals(10, row1.getX());
		assertEquals(10, row1.getY());
		Row row2 = new Row(word1, word2);
		Row row3 = new Row(row1, row2);
		assertEquals(10, row3.getX());
		assertEquals(10, row3.getY());
	}

	public void testMove() {
		Word word1 = new Word(10, 10, "di", false, false);
		Word word2 = new Word(20, 20, "wang", false, false);
		Row row1 = new Row(word1, word2);
		row1.setLocation(30, 30);
	}

	public void testSelected() {
		Word word1 = new Word(10, 10, "di", false, false);
		Word word2 = new Word(20, 20, "wang", false, false);
		Row row1 = new Row(word1, word2);
		row1.setSelected(true);
		boolean b = row1.isSelected();
		assertEquals(true, b);
	}

	public void testWordOperation() {
		Word word1 = new Word(10, 10, "di", false, false);
		Word word2 = new Word(20, 20, "wang", false, false);
		List<Word> wordList = new ArrayList<Word>();
		wordList.add(word1);
		wordList.add(word2);
		Row row1 = new Row(word1, word2);
		assertEquals(wordList, row1.getWordList());
		Word word3 = new Word(20, 20, "cool", false, false);
		row1.addWord(null, true);
		row1.addWord(word3, false);
		row1.addWord(word3, true);
		row1.removeWord(false);
		row1.removeWord(true);
	}

	public void testShowRow() {
		Word word1 = new Word(10, 10, "di", false, false);
		Word word2 = new Word(20, 20, "wang", false, false);
		Row row1 = new Row(word1, word2);
		row1.showRow();
	}
}
