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
		Word word1 = new Word(10, 10, "di", false, false, false);
		Word word2 = new Word(20, 20, "wang", false, false, false);
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
		Word word1 = new Word(10, 10, "di", false, false, false);
		Word word2 = new Word(20, 20, "wang", false, false, false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word1, word2);
		Poem poem1 = new Poem(row1, row2);
		poem1.setSelected(false);
		assertEquals(false, poem1.isSelected());
	}

	public void testRowOperation() {
		Word word1 = new Word(10, 10, "di", false, false, false);
		Word word2 = new Word(20, 20, "wang", false, false, false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word1, word2);
		Poem poem1 = new Poem(row1, row2);
		poem1.addRow(row2, false);
		poem1.addRow(row2, true);
		poem1.addRow(null, true);
		poem1.removeRow(false);
		poem1.removeRow(true);
	}

	public void testShowPoem() {
		Word word1 = new Word(10, 10, "di", false, false, false);
		Word word2 = new Word(20, 20, "wang", false, false, false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word1, word2);
		Poem poem1 = new Poem(row1, row2);
		poem1.showPoem();
	}
}
