package controller;

import model.*;
import view.*;

/**This is class responsible for finding word when user clicks on JTable
 * 
 * @author Chen Chen
 *
 */
public class FindWordController {

	Board board;
	ApplicationPanel panel;
	Word keyWord;
	
	public FindWordController(Board board, ApplicationPanel panel, Word keyWord) {
		this.board = board;
		this.panel = panel;
		this.keyWord = keyWord;
	}
	
	public void process() {
//		board.words.remove(keyWord);
//		board.words.add(keyWord);
		panel.repaint();
		panel.redraw();
	}
	
}
