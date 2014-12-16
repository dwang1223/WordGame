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
		assertEquals(row1.getHeight() + row2.getHeight(), poem2.getHeight());
		assertEquals(row1.getHeight() + row2.getHeight() + row1.getHeight()
				+ row2.getHeight(), poem3.getHeight());
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
		poem1.removeRow(row2);
		poem1.removeRow(row2);
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
	}

	public void testShowPoem() {
		Word word1 = new Word(10, 10, "di", "noun", false, false, false, false);
		Word word2 = new Word(20, 20, "wang", "noun", false, false, false,
				false);
		Word word3 = new Word(10, 10, "so", "noun", false, false, false, false);
		Word word4 = new Word(20, 20, "cool", "noun", false, false, false,
				false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word3, word4);
		Poem poem1 = new Poem(row1, row2);
		Poem poem2 = new Poem(row2, row1);
		poem1.addPoem(poem2, true);
		// poem1.addPoem(poem2, true);
		poem1.addPoem(poem2, false);
		poem1.addPoem(null, true);
		System.out.println("=============");
		poem1.showPoem();
		System.out.println("=============");
	}
}
