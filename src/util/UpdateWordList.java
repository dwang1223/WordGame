package util;

import java.util.ArrayList;
import java.util.Random;

import model.Board;
import model.Word;

public class UpdateWordList {
	/*update the global word list. If this word is in unprotected area 
	 * and it content matches with the one in the message, then remove it*/
	public static void updateRemove(ArrayList<Word>wordList,String [] wordArray, int index){
		for(int i =0; i<wordList.size(); i++){
			if(wordList.get(i).getContent().equals(wordArray[index])&&!wordList.get(i).isProtected())
				wordList.remove(i);
		}
	}
	
	public static void updateAdd(ArrayList<Word>wordList,String [] wordArray,String[] typeArray, int index){
		wordList.add(new Word(getRandomX(),500,wordArray[index],typeArray[index],false,false,false,false));
	}
	
	
	private static int getRandomX(){
		Random random = new Random();
		int x = random.nextInt(650)+20;
		return x;
	}
	
	private static int getRandomY(){
		Random random = new Random();
		int y = random.nextInt(600)+100;
		return y;
	}
	
}
