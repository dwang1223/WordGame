package model;


/**
 * Move Class
 * 
 * @author diwang
 *
 */
public abstract class Move {

	/** Execute given move. */
	public abstract boolean execute();

	/** Request undo. */
	public abstract boolean undo();
	
	/** Request redo. */
	public abstract boolean redo();
	
	public abstract Board getOldBoard();
	
	public abstract Board getNewBoard();
}
