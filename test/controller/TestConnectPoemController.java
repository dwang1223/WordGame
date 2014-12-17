package controller;

import model.Board;
import model.Model;
import model.Poem;
import model.Row;
import model.Word;
import view.Application;
import junit.framework.TestCase;

/**
 * TestConnectPoemController Class
 * 
 * @author diwang
 *
 */
public class TestConnectPoemController extends TestCase {
	public void testConnectTopPoem() {
		Board curBoard = new Board();
		Word word1 = new Word(100, 100, "di", "noun", true, false, false, false);
		Word word2 = new Word(120, 100, "wang", "noun", true, false, false,
				false);
		Word word3 = new Word(100, 125, "di", "noun", true, false, false, false);
		Word word4 = new Word(120, 125, "wang", "noun", true, false, false,
				false);
		Word word5 = new Word(200, 150, "di", "noun", true, false, false, false);
		Word word6 = new Word(220, 150, "wang", "noun", true, false, false,
				false);
		Word word7 = new Word(300, 300, "di", "noun", true, false, false, false);
		Word word8 = new Word(320, 300, "wang", "noun", true, false, false,
				false);
		Word word9 = new Word(300, 325, "di", "noun", true, false, false, false);
		Word word0 = new Word(320, 325, "wang", "noun", true, false, false,
				false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word3, word4);
		Row row3 = new Row(word5, word6);
		Row row4 = new Row(word7, word8);
		Row row5 = new Row(word9, word0);
		Poem poem = new Poem(row1, row2);
		Poem poem2 = new Poem(row4, row5);
		curBoard.addWord(word1);
		curBoard.addWord(word2);
		curBoard.addWord(word3);
		curBoard.addWord(word4);
		curBoard.addWord(word5);
		curBoard.addWord(word6);
		curBoard.addWord(word7);
		curBoard.addWord(word8);
		curBoard.addWord(word9);
		curBoard.addWord(word0);
		curBoard.addRow(row1);
		curBoard.addRow(row2);
		curBoard.addRow(row3);
		curBoard.addRow(row4);
		curBoard.addRow(row5);
		curBoard.addPoem(poem);
		curBoard.addPoem(poem2);
		Model model = new Model(curBoard);
		Application app = new Application(model);
		ConnectPoemController connectPoemController = new ConnectPoemController(
				model, app);
		connectPoemController.register();
		connectPoemController.select(10, 10);
		connectPoemController.select(210, 160);
		connectPoemController.select(110, 110);
		connectPoemController.drag(1000, 1000);
		connectPoemController.drag(210, 160);
		connectPoemController.drag(300, 300);
		connectPoemController.drag(300, 250);
		connectPoemController.release(300, 250);
	}

	public void testConnectBottomPoem() {
		Board curBoard = new Board();
		Word word1 = new Word(100, 100, "di", "noun", true, false, false, false);
		Word word2 = new Word(120, 100, "wang", "noun", true, false, false,
				false);
		Word word3 = new Word(100, 125, "di", "noun", true, false, false, false);
		Word word4 = new Word(120, 125, "wang", "noun", true, false, false,
				false);
		Word word5 = new Word(200, 150, "di", "noun", true, false, false, false);
		Word word6 = new Word(220, 150, "wang", "noun", true, false, false,
				false);
		Word word7 = new Word(300, 200, "di", "noun", true, false, false, false);
		Word word8 = new Word(320, 200, "wang", "noun", true, false, false,
				false);
		Word word9 = new Word(300, 225, "di", "noun", true, false, false, false);
		Word word0 = new Word(320, 225, "wang", "noun", true, false, false,
				false);
		Row row1 = new Row(word1, word2);
		Row row2 = new Row(word3, word4);
		Row row3 = new Row(word5, word6);
		Row row4 = new Row(word7, word8);
		Row row5 = new Row(word9, word0);
		Poem poem = new Poem(row1, row2);
		Poem poem2 = new Poem(row4, row5);
		curBoard.addWord(word1);
		curBoard.addWord(word2);
		curBoard.addWord(word3);
		curBoard.addWord(word4);
		curBoard.addWord(word5);
		curBoard.addWord(word6);
		curBoard.addWord(word7);
		curBoard.addWord(word8);
		curBoard.addWord(word9);
		curBoard.addWord(word0);
		curBoard.addRow(row1);
		curBoard.addRow(row2);
		curBoard.addRow(row3);
		curBoard.addRow(row4);
		curBoard.addRow(row5);
		curBoard.addPoem(poem);
		curBoard.addPoem(poem2);
		Model model = new Model(curBoard);
		Application app = new Application(model);
		ConnectPoemController connectPoemController = new ConnectPoemController(
				model, app);
		connectPoemController.register();
		connectPoemController.select(110, 110);
		connectPoemController.drag(300, 270);
		connectPoemController.release(300, 270);
	}
}
