package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import model.Board;
import model.Word;

/**
 * InitializeWord Class
 * 
 * @author diyu, diwang
 *
 */
public class InitializeWord {

	public static ArrayList<Word> initWords() {
		ArrayList<Word> initWords = new ArrayList<Word>();
		BufferedReader br;
		int randomX;
		int randomY;
		try {
			br = new BufferedReader(new FileReader("WordsAndTypes.csv"));
			String line;
			while ((line = br.readLine()) != null) {
				// 0 is the word content and 1 is the word type
				String[] wordContentType = line.split(",");
				randomX = (int) Math.round(Math.random()
						* (Board.widthOfUnprotectedArea - wordContentType[0]
								.length() * 10));
				randomY = (int) Math.round(Math.random()
						* (Board.heightOfUnprotectedArea - 25)
						+ Board.heightOfProtectedArea);
				Word word = new Word(randomX, randomY,
						wordContentType[0].trim(), wordContentType[1].trim(),
						false, false, false, false);
				initWords.add(word);
				if (initWords.size() == 50) {
					return initWords;
				}
			}
			br.close();
		} catch (Exception ex) {
			System.out.println("errors when parsing the file");
		}
		return initWords;
	}

}
