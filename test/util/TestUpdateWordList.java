package util;

import java.util.ArrayList;

import model.Word;
import junit.framework.TestCase;

/**
 * TestUpdateWordList Class
 * 
 * @author Di Yu
 *
 */
public class TestUpdateWordList extends TestCase {

	public void testUpdateRemove() {
		Word word1 = new Word(10, 10, "di", "noun", false, false, false, false);
		Word word2 = new Word(10, 10, "yu", "noun", false, false, false, false);
		String[] wordArray = { "di", "yu" };
		int index = 0;
		ArrayList<Word> wordList = new ArrayList<Word>();
		wordList.add(word1);
		wordList.add(word2);

		UpdateWordList updt = new UpdateWordList();

		updt.updateRemove(wordList, wordArray, index);
	}

	public void testUpdateAdd() {
		Word word1 = new Word(10, 10, "di", "noun", false, false, false, false);
		Word word2 = new Word(10, 10, "yu", "noun", false, false, false, false);
		String[] wordArray = { "di", "yu" };
		String[] typeArray = { "noun", "noun" };
		int index = 0;
		ArrayList<Word> wordList = new ArrayList<Word>();
		wordList.add(word1);
		wordList.add(word2);

		UpdateWordList updt = new UpdateWordList();

		updt.updateAdd(wordList, wordArray, typeArray, index);
	}

}
