package util;

import java.util.ArrayList;
import java.util.Hashtable;

import model.Word;

/**
 * ConvertWildcard Class
 * 
 * @author Di Yu
 *
 */
public class ConvertWildcard {

	public static Hashtable<Integer, Word> convert(
			ArrayList<Word> unprotectedWordList,
			ArrayList<WordBean> requestWordList) {
		int matchCount = 0;
		Hashtable<Integer, Word> tempWordTable = new Hashtable<Integer, Word>();
		int n = requestWordList.size();
		for (int i = 0; i < requestWordList.size(); i++) {
			for (int j = 0; j < unprotectedWordList.size(); j++) {
				if (!requestWordList.get(i).content.equals("*")
						&& requestWordList.get(i).type.equals("*")) {
					if (unprotectedWordList.get(j).getContent()
							.equals(requestWordList.get(i).content)) {
						matchCount++;
						tempWordTable.put(i, unprotectedWordList.get(j));
						unprotectedWordList.remove(j);
						if (matchCount == n) {
							return tempWordTable;
						}
						break;
					}
				} else if (requestWordList.get(i).content.equals("*")
						&& !requestWordList.get(i).type.equals("*")) {
					if (unprotectedWordList.get(j).getType()
							.equals(requestWordList.get(i).type)) {
						matchCount++;
						tempWordTable.put(i, unprotectedWordList.get(j));
						unprotectedWordList.remove(j);
						if (matchCount == n) {
							return tempWordTable;
						}
						break;
					}
				} else {
					matchCount++;
					tempWordTable.put(i, unprotectedWordList.get(j));
					unprotectedWordList.remove(j);
					if (matchCount == n) {
						return tempWordTable;
					}
					break;
				}
			}

		}
		return new Hashtable<Integer, Word>();
	}

}
