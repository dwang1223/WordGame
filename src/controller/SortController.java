package controller;

import java.awt.Point;
import javax.swing.table.JTableHeader;
import model.*;
import view.*;

/**Controller that handles user actions over the JTable
 * 
 * @author Chen Chen
 *
 */
public class SortController {
	
	WordTable table;
	
	public SortController(WordTable table) {
		this.table = table;
	}
	
	public void process (JTableHeader h, Point point) {
		int columnIndex = h.columnAtPoint(point);
		
		if (columnIndex != -1) {
			String fieldName = h.getColumnModel().getColumn(columnIndex).getHeaderValue().toString();
			WordModel model = (WordModel) h.getTable().getModel();
			model.sort(fieldName);
		}
	}
}
