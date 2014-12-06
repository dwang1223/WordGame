package controller;

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
}
