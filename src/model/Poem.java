package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Poem Class
 * 
 * @author diwang
 *
 */
public class Poem implements Serializable {
	private static final long serialVersionUID = -2798021631905491702L;
	private ArrayList<Row> rowList = new ArrayList<Row>();
	private boolean isSelected = false;

	public Poem(Row row1, Row row2) {
		for (Word word1 : row1.getWordList()) {
			word1.setInPoem(true);
		}
		for (Word word2 : row2.getWordList()) {
			word2.setInPoem(true);
		}
		this.rowList.add(row1);
		this.rowList.add(row2);
	}

	public Poem(Poem poem1, Poem poem2) {
		ArrayList<Row> rowList1 = poem1.rowList;
		ArrayList<Row> rowList2 = poem2.rowList;
		rowList1.addAll(rowList2);
		this.rowList = rowList1;
	}

	public void setLocation(int x, int y) {
		int xofRow = x;
		int yofRow = y;
		int diffofX = xofRow - rowList.get(0).getX();
		int diffofY = yofRow - rowList.get(0).getY();
		for (int i = 0; i < rowList.size(); i++) {
			Row row = rowList.get(i);
			row.setLocation(row.getX() + diffofX, row.getY() + diffofY);
		}
	}

	public int getX() {
		return rowList.get(0).getX();
	}

	public int getY() {
		return rowList.get(0).getY();
	}

	public ArrayList<Row> getRowList() {
		return rowList;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean addRow(Row row, boolean isDown) {
		if (row != null) {
			for (Word word : row.getWordList()) {
				word.setInPoem(true);
			}
			if (isDown) {
				this.rowList.add(row);
			} else {
				int rowSize = rowList.size();
				List<Row> rowListTemp = new ArrayList<Row>();
				for (int i = 0; i < rowSize; i++) {
					rowListTemp.add(rowList.get(i));
				}
				for (int i = 0; i < rowSize - 1; i++) {
					this.rowList.set(i + 1, rowListTemp.get(i));
				}
				this.rowList.add(rowListTemp.get(rowSize - 1));
				this.rowList.set(0, row);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean removeRow(Row row) {
		for (Word word : row.getWordList()) {
			word.setInPoem(false);
		}
		rowList.remove(row);
		if (rowList.size() == 1) {
			// if the size of poem is 1
			Row lastRow = rowList.get(0);
			for (Word word : lastRow.getWordList()) {
				word.setInPoem(false);
			}
			return false;
		}
		return true;
	}

	public void showPoem() {
		for (Row row : rowList) {
			for (Word word : row.getWordList()) {
				System.out.print(word.getContent() + " ");
			}
			System.out.println();
		}
	}

}
