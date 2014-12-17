package controller;

import model.Board;
import model.Model;
import model.Poem;
import model.Row;
import model.Word;
import view.Application;
import junit.framework.TestCase;

/**
 * TestConnectRowController Class
 * 
 * @author diwang
 *
 */
public class TestConnectRowController extends TestCase {
	public void testConnectTopRow() {
		Board curBoard = new Board();
		Word word1 = new Word(100, 100, "di", "noun", true, false, false,
				false);
		Word word2 = new Word(120, 100, "wang", "noun", true, false, false,
				false);
		Word word3 = new Word(10, 10, "di", "noun", true, false, false, false);
		Word word4 = new Word(30, 10, "wang", "noun", true, false, false,
				false);
		Word word5 = new Word(10, 300, "wang", "noun", true, false, false,
				false);
		Word word6 = new Word(10, 350, "wang", "noun", true, true, true, false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word3, word4);
		curBoard.addWord(word1);
		curBoard.addWord(word2);
		curBoard.addWord(word3);
		curBoard.addWord(word4);
		curBoard.addWord(word5);
		curBoard.addWord(word6);
		curBoard.addRow(row1);
		curBoard.addRow(row2);
		Model model = new Model(curBoard);
		Application app = new Application(model);
		ConnectRowController connectRowController = new ConnectRowController(
				model, app);
		connectRowController.register();
		connectRowController.select(5, 5);
		connectRowController.select(15, 305);
		connectRowController.select(15, 355);
		connectRowController.select(15, 15);
		connectRowController.drag(200, 100);
		connectRowController.drag(1000, 1000);
		connectRowController.drag(15, 305);
		connectRowController.select(20, 10);
		connectRowController.drag(80, 75);
		connectRowController.release(80, 75);

	}

	public void testConnectTopPoem() {
		Board curBoard = new Board();
		Word word1 = new Word(100, 100, "di", "noun", true, false, false,
				false);
		Word word2 = new Word(120, 100, "wang", "noun", true, false, false,
				false);
		Word word3 = new Word(10, 10, "di", "noun", true, false, false, false);
		Word word4 = new Word(30, 10, "wang", "noun", true, false, false,
				false);
		Word word5 = new Word(100, 125, "wang", "noun", true, false, false,
				false);
		Word word6 = new Word(140, 125, "wang", "noun", true, true, true, false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word3, word4);
		Row row3 = new Row(word5, word6);
		Poem poem = new Poem(row1, row3);
		curBoard.addWord(word1);
		curBoard.addWord(word2);
		curBoard.addWord(word3);
		curBoard.addWord(word4);
		curBoard.addWord(word5);
		curBoard.addWord(word6);
		curBoard.addRow(row1);
		curBoard.addRow(row2);
		curBoard.addRow(row3);
		curBoard.addPoem(poem);
		Model model = new Model(curBoard);
		Application app = new Application(model);
		ConnectRowController connectRowController = new ConnectRowController(
				model, app);
		connectRowController.register();
		connectRowController.select(20, 10);
		connectRowController.drag(80, 75);
		connectRowController.release(80, 75);

	}

	public void testConnectBottomRow() {
		Board curBoard = new Board();
		Word word1 = new Word(100, 100, "di", "noun", true, false, false,
				false);
		Word word2 = new Word(120, 100, "wang", "noun", true, false, false,
				false);
		Word word3 = new Word(10, 10, "di", "noun", true, false, false, false);
		Word word4 = new Word(30, 10, "wang", "noun", true, false, false,
				false);
		Word word5 = new Word(10, 300, "wang", "noun", true, false, false,
				false);
		Word word6 = new Word(10, 350, "wang", "noun", true, true, true, false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word3, word4);
		curBoard.addWord(word1);
		curBoard.addWord(word2);
		curBoard.addWord(word3);
		curBoard.addWord(word4);
		curBoard.addWord(word5);
		curBoard.addWord(word6);
		curBoard.addRow(row1);
		curBoard.addRow(row2);
		Model model = new Model(curBoard);
		Application app = new Application(model);
		ConnectRowController connectRowController = new ConnectRowController(
				model, app);
		connectRowController.register();
		connectRowController.select(5, 5);
		connectRowController.select(15, 305);
		connectRowController.select(15, 355);
		connectRowController.select(15, 15);
		connectRowController.drag(200, 100);
		connectRowController.drag(1000, 1000);
		connectRowController.drag(15, 305);
		connectRowController.select(20, 10);
		connectRowController.drag(80, 130);
		connectRowController.release(80, 130);
	}
	
	public void testConnectBottomPoem() {
		Board curBoard = new Board();
		Word word1 = new Word(100, 100, "di", "noun", true, false, false,
				false);
		Word word2 = new Word(120, 100, "wang", "noun", true, false, false,
				false);
		Word word3 = new Word(10, 10, "di", "noun", true, false, false, false);
		Word word4 = new Word(30, 10, "wang", "noun", true, false, false,
				false);
		Word word5 = new Word(100, 125, "wang", "noun", true, false, false,
				false);
		Word word6 = new Word(140, 125, "wang", "noun", true, true, true, false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word3, word4);
		Row row3 = new Row(word5, word6);
		Poem poem = new Poem(row1, row3);
		curBoard.addWord(word1);
		curBoard.addWord(word2);
		curBoard.addWord(word3);
		curBoard.addWord(word4);
		curBoard.addWord(word5);
		curBoard.addWord(word6);
		curBoard.addRow(row1);
		curBoard.addRow(row2);
		curBoard.addRow(row3);
		curBoard.addPoem(poem);
		Model model = new Model(curBoard);
		Application app = new Application(model);
		ConnectRowController connectRowController = new ConnectRowController(
				model, app);
		connectRowController.register();
		connectRowController.select(20, 10);
		connectRowController.drag(80, 160);
		connectRowController.release(80, 160);

	}
}
