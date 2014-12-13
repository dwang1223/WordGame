package model;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * TestPoem Class
 * 
 * @author diwang
 *
 */
public class TestPoem extends TestCase {
	public void testConstruction() {
		Word word1 = new Word(10, 10, "di", "noun", false, false, false, false);
		Word word2 = new Word(20, 20, "wang", "noun", false, false, false,
				false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word1, word2);
		List<Row> rowList = new ArrayList<Row>();
		rowList.add(row1);
		rowList.add(row2);
		Poem poem1 = new Poem(row1, row2);
		assertEquals(10, poem1.getX());
		assertEquals(10, poem1.getY());
		assertEquals(rowList, poem1.getRowList());
		Poem poem2 = new Poem(row1, row2);
		Poem poem3 = new Poem(poem1, poem2);
	}

	public void testSelected() {
		Word word1 = new Word(10, 10, "di", "noun", false, false, false, false);
		Word word2 = new Word(20, 20, "wang", "noun", false, false, false,
				false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word1, word2);
		Poem poem1 = new Poem(row1, row2);
		poem1.setSelected(false);
		assertEquals(false, poem1.isSelected());
	}

	public void testRowOperation() {
		Word word1 = new Word(10, 10, "di", "noun", false, false, false, false);
		Word word2 = new Word(20, 20, "wang", "noun", false, false, false,
				false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word1, word2);
		Poem poem1 = new Poem(row1, row2);
		poem1.addRow(row2, false);
		poem1.addRow(row2, true);
		poem1.addRow(null, true);
		poem1.removeRow(row1);
	}

	public void testLocation() {
		Word word1 = new Word(10, 10, "di", "noun", false, false, false, false);
		Word word2 = new Word(30, 10, "wang", "noun", false, false, false,
				false);
		Row row1 = new Row(word1, word2);
		Word word3 = new Word(20, 35, "di", "noun", false, false, false, false);
		Word word4 = new Word(40, 35, "wang", "noun", false, false, false,
				false);
		Row row2 = new Row(word3, word4);
		Poem poem = new Poem(row1, row2);
		poem.setLocation(110, 110);
		for (Row row : poem.getRowList()) {
			System.out.println("=======================");
			for (Word word : row.getWordList()) {
				System.out.println("wordX: " + word.getX() + "   wordY: "
						+ word.getY());
			}
		}
		// row1.setLocation(110, 110);
		// for (Word word : row1.getWordList()) {
		// System.out.println("wordX: " + word.getX() + "   wordY: "
		// + word.getY());
		// }
		// System.out.println("word1X: " + word1.getX() + "   word1Y: "
		// + word1.getY());
		// System.out.println("word2X: " + word2.getX() + "   word2Y: "
		// + word2.getY());
	}

	public void testShowPoem() {
		Word word1 = new Word(10, 10, "di", "noun", false, false, false, false);
		Word word2 = new Word(20, 20, "wang", "noun", false, false, false,
				false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word1, word2);
		Poem poem1 = new Poem(row1, row2);
		// poem1.showPoem();
	}
}
