package util;

import java.util.ArrayList;

import model.Word;
import junit.framework.TestCase;

/**
 * TestInitializeWord Class
 * 
 * @author diwang
 *
 */
public class TestInitializeWord extends TestCase {
	public void testInitializeWord() {
		ArrayList<Word> initWords = InitializeWord.initWords(100);
		InitializeWord initializeWord = new InitializeWord();
	}
}
