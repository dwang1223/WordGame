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
	private List<Row> rowList = new ArrayList<Row>();
	private boolean isSelected = false;

	public Poem(Row row1, Row row2) {
		this.rowList.add(row1);
		this.rowList.add(row2);
	}

	public Poem(Poem poem1, Poem poem2) {
		List<Row> rowList1 = poem1.rowList;
		List<Row> rowList2 = poem2.rowList;
		rowList1.addAll(rowList2);
		this.rowList = rowList1;
	}

	public int getX() {
		return rowList.get(0).getX();
	}

	public int getY() {
		return rowList.get(0).getY();
	}

	public List<Row> getRowList() {
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

	public boolean removeRow(boolean isDown) {
		if (isDown) {
			this.rowList.remove(this.rowList.size() - 1);
		} else {
			this.rowList.remove(0);
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
