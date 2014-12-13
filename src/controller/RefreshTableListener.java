package controller;

import java.io.Serializable;

import view.WordTable;

public class RefreshTableListener implements Listener, Serializable {
	private static final long serialVersionUID = 4729218981236907860L;

	/** Widget to be refreshed. */
	WordTable wordTable;

	public RefreshTableListener(WordTable wordTable) {
		this.wordTable = wordTable;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		wordTable.refreshTable();
	}
}
