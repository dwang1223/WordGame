package util;

import java.util.ArrayList;

import model.Board;
import model.Word;

public class UpdateWordList {
	/*
	 * update the global word list. If this word is in unprotected area and it
	 * content matches with the one in the message, then remove it
	 */
	public static void updateRemove(ArrayList<Word> wordList,
			String[] wordArray, int index) {
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).getContent().equals(wordArray[index])
					&& !wordList.get(i).isProtected())
				wordList.remove(i);
			return;
		}
	}

	public static void updateAdd(ArrayList<Word> wordList, String[] wordArray,
			String[] typeArray, int index) {
		int randomX = (int) Math
				.round(Math.random()
						* (Board.widthOfUnprotectedArea - wordArray[index]
								.length() * 10));
		int randomY = (int) Math.round(Math.random()
				* (Board.heightOfUnprotectedArea - 25)
				+ Board.heightOfProtectedArea);
		wordList.add(new Word(randomX, randomY, wordArray[index],
				typeArray[index], false, false, false, false));
	}

}
