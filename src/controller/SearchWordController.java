package controller;

import model.Board;
import view.ApplicationPanel;

/**
 * This class is responsible for searching the word which is shown in text
 * field.
 * 
 * @author Chen Chen
 *
 */
public class SearchWordController {

	Board board;
	String keyWord;
	ApplicationPanel panel;

	public SearchWordController(Board board, ApplicationPanel panel,
			String keyWord) {
		this.board = board;
		this.panel = panel;
		this.keyWord = keyWord;
	}

	public void process() {
		board.setSearchedWords(keyWord);
		panel.repaint();
		panel.redraw();
	}

}
