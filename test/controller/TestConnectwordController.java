package controller;

import java.awt.event.MouseEvent;

import junit.framework.TestCase;
import model.*;
import view.*;

/**
 * This is test case for testing ConnnectWordController
 * @author Chen
 *
 */
public class TestConnectwordController extends TestCase {

	public void testConstructor() {
//		Word(int x, int y, String content, String type, boolean isProtected,
//				boolean isInRow, boolean isInPoem, boolean isSearched)
//		Word word1 = new Word(70, 85, "eat", "verb", true, false, false, false);
		Word word1 = new Word(10, 10, "eat", "verb", true, false, false, false);
		Board board = new Board();
		Model model = new Model(board);
		Application app = new Application(model);
		ConnectWordController controller = new ConnectWordController(model, app);
		board.addWord(word1);
		assertFalse(controller.select(1, 1));
		assertFalse(controller.drag(100, 100));
		assertFalse(controller.release(100, 100));
		Word word2 = new Word(500, 500, "apple", "noun", false, false, false, false);
		Word word3 = new Word(50, 50, "orange", "noun", true, true, false, false);
		board.addWord(word2);
		board.addWord(word3);
		assertFalse(controller.select(501, 501));
		assertFalse(controller.select(51, 51));
		Word word4 = new Word(80, 80, "banana", "noun", true, false, false, false);
		board.addWord(word4);
		assertTrue(controller.select(81, 81));
		controller.select(80, 80);
		assertTrue(controller.drag(500, 500));
		assertTrue(controller.drag(11, 11));
		assertTrue(controller.drag(650, 350));
		Word word5 = new Word(150, 150, "me", "noun", true, false, false, false);
		Word word6 = new Word(200, 150, "you", "noun", true, false, false, false);
		Word word7 = new Word(300, 150, "he", "noun", true, true, false, false);
		Word word9 = new Word(280, 150, "is", "noun", true, true, false, false);
		board.addWord(word5);
		board.addWord(word6);
		board.addWord(word7);
		Row row1 = new Row(word9, word7);
		board.addRow(row1);
//		System.out.println(model.getSelectedWord().getContent());
//		assertTrue(controller.release(85, 150));
		controller.release(85, 150);
		controller.select(10, 10);
		controller.drag(232, 151);
		assertTrue(controller.release(232, 151));
		Word word8 = new Word(600, 1, "her", "noun",true, false, false, false);
		board.addWord(word8);
		controller.select(600, 1);
//		System.out.println(model.getSelectedWord().getContent());
		controller.drag(321, 150);
		controller.release(321, 150);
		Word word10 = new Word(650, 1, "ha", "noun", true, false, false, false);
		board.addWord(word10);
		controller.select(650, 1);
		System.out.println(model.getSelectedWord().getContent());
		Word word11 = new Word(650, 30, "a", "noun", true, false, false, false);
		board.addWord(word11);
		controller.drag(625, 30);
		controller.release(625, 30);
		Word word12 = new Word(650, 100, "am", "noun", true, false, false, false);
		Word word13 = new Word(600, 200, "do","noun", true, true, false, false);
		Word word14 = new Word(620, 200, "ww", "noun", true, true, false, false);
		Row row2 = new Row(word13, word14);
		board.addWord(word12);
		board.addWord(word13);
		board.addWord(word14);
		board.addRow(row2);
		controller.select(650,  100);
		controller.drag(575,200);
		controller.release(575, 200);
	}
	
}
