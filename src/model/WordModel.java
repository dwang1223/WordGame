package model;

import java.util.Comparator;

import javax.swing.table.AbstractTableModel;


/**
 * This class is responsible for handling the JTable stuff
 * @author Chen Chen
 *
 */
public class WordModel extends AbstractTableModel{
	
	/**
	 * default UID
	 */
	private static final long serialVersionUID = 1L;

	Board board;
	
	public static final String wordLabel = "Word";
	public static final String typeLabel = "Type";
	
	public WordModel (Board board) {
		this.board = board;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return board.getUnprotectedWords().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Word selected = board.getUnprotectedWords().get(rowIndex);
		
		if (columnIndex == 0) {
			return selected.getContent();
		} else if (columnIndex == 1) {
			return selected.getType();
		}
		
		return "";
	}
	
	/**
	 * This is method used for sorting table based on user's selection.
	 * @param fieldName: either "Word" or "Type"
	 */
	public void sort(final String fieldName) {
		board.sortUnprotectedWords(new Comparator<Word>(){

			@Override
			public int compare(Word w1, Word w2) {
				// TODO Auto-generated method stub
				if (fieldName.equals(wordLabel)) {
					return w1.getContent().compareTo(w2.getContent());
				} else {
					return w1.getType().compareTo(w2.getType());
				}
			}
			
		});
	}

}
