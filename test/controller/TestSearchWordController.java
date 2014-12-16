package controller;

import view.Application;
import junit.framework.TestCase;
import model.*;
import view.*;

public class TestSearchWordController extends TestCase {
	
	public void testConstruction() {
		Board b = new Board();
		Word word1 = new Word(20, 20, "are", "noun", false, false, false, false);
		Word word2 = new Word(70, 85, "eat", "verb", false, false, false, false);
		b.addWord(word1);
		b.addWord(word2);
		Model model = new Model(b);
		ApplicationPanel panel = new ApplicationPanel(model);
		SearchWordController searchWordController = new SearchWordController(b, panel, "are");
		searchWordController.process();
		assertEquals(true, word1.isSearched());
		assertEquals(false, word2.isSearched());
	}
	
}
