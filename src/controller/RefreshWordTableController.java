package controller;

import view.WordTable;

/**This class is responsible for refreshing JTable
 * 
 * @author Chen Chen
 *
 */
public class RefreshWordTableController implements Listener{
	
	WordTable wordTable;
	
	public RefreshWordTableController (WordTable wordTable) {
		this.wordTable = wordTable;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		wordTable.refreshTable();
	}

}
