package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import junit.framework.TestCase;
import view.*;
import model.*;

public class TestSortController extends TestCase {

	public void testConstruction() {
		Word word1 = new Word(70, 85, "eat", "verb", false, false, false, false);
		Word word2 = new Word(20, 20, "are", "noun", false, false, false, false);
		Word word3 = new Word(100, 100, "apple", "noun", false, false, false, false);
		Board board = new Board();
		board.addWord(word1);
		board.addWord(word2);
		board.addWord(word3);
		Model model = new Model(board);
		ApplicationPanel panel = new ApplicationPanel(model);
		WordTable wordTable = new WordTable(board, panel);
		WordModel wordModel = new WordModel(board);
		JTable jtable = new JTable(wordModel);
		TableColumnModel columnModel = new DefaultTableColumnModel();
		String[] headers = new String[] { WordModel.wordLabel,
				WordModel.typeLabel };
		int index = 0;
		for (String h : headers) {
			TableColumn col = new TableColumn(index++);
			col.setHeaderValue(h);
			columnModel.addColumn(col);
		}
		jtable.setColumnModel(columnModel);
		JTableHeader h = jtable.getTableHeader();
		Point point = new Point(63, 12);
		new SortController(wordTable).process(h, point);
//		System.out.println(board.words.indexOf(word3));
//		int columnIndex = h.columnAtPoint(point);
//		System.out.println(columnIndex);
//		String fieldName = h.getColumnModel().getColumn(columnIndex)
//				.getHeaderValue().toString();
//		System.out.println(fieldName);
		assertEquals(0, board.words.indexOf(word3));
		assertEquals(1, board.words.indexOf(word2));
		assertEquals(2, board.words.indexOf(word1));
	}
	
}
