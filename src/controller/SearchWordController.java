package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.*;
import view.*;

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

	public SearchWordController(Board board, String keyWord,
			ApplicationPanel panel) {
		this.board = board;
		this.keyWord = keyWord;
		this.panel = panel;
	}

	public void process() {
		board.setSearchedWords(keyWord);
		panel.repaint();
		panel.redraw();
	}

}
