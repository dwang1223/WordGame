package model;

import java.awt.Rectangle;

/**
 * Word Class
 * 
 * @author diwang
 *
 */
public class Word implements java.io.Serializable {
	/**
	 * Unique ID for serialized Word objects.
	 */
	private static final long serialVersionUID = 9018383050622954183L;
	private Rectangle rect;
	private String content;
	private String type;
	private boolean isProtected;
	private boolean isInRow;
	private boolean isInPoem;
	private boolean isSearched;

	public Word(int x, int y, String content, String type, boolean isProtected,
			boolean isInRow, boolean isInPoem, boolean isSearched) {
		this.rect = new Rectangle(x, y, content.length() * 10, 25);
		this.content = content;
		this.type = type; // Chen Chen modified
		this.isProtected = isProtected;
		this.isInRow = isInRow;
		this.isInPoem = isInPoem;
		this.isSearched = isSearched;
	}

	public boolean isProtected() {
		return isProtected;
	}

	public void setProtected(boolean isProtected) {
		this.isProtected = isProtected;
	}

	public boolean isInRow() {
		return isInRow;
	}

	public void setInRow(boolean isInRow) {
		this.isInRow = isInRow;
		// System.out.println(this + " inRow: " + isInRow);
	}

	public boolean isInPoem() {
		return isInPoem;
	}

	public void setInPoem(boolean isInPoem) {
		this.isInPoem = isInPoem;
	}

	public boolean isSearched() {
		return isSearched;
	}

	public void setSearched(boolean isSearched) {
		this.isSearched = isSearched;
	}

	public void setLocation(int x, int y) {
		this.rect.x = x;
		this.rect.y = y;
	}

	public int getX() {
		return rect.x;
	}

	public int getY() {
		return rect.y;
	}

	public int getWidth() {
		return rect.width;
	}

	public int getHeight() {
		return rect.height;
	}

	public String getContent() {
		return content;
	}

	public String getType() {
		return type;
	}

	public boolean intersect(int x, int y) {
		return rect.contains(x, y);
	}

	public boolean intersects(Word other) {
		return this.rect.intersects(other.rect);
	}

	// need to check whether two objects are identical. Java interface
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof Word) {
			Word other = (Word) o;
			return other.rect.equals(this.rect)
					&& other.content.equals(content) && other.type.equals(type);
		}

		return false; // can't compare different classes.
	}
}
