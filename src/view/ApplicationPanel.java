package view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Board;
import model.Model;
import model.Row;
import model.Word;

/**
 * ApplicationPanel Class
 * 
 * @author diwang
 *
 */
public class ApplicationPanel extends JPanel {
	private static final long serialVersionUID = 4145224259053243010L;

	Model model;
	Image offscreenImage;
	Graphics offscreenGraphics;
	Graphics canvasGraphics;

	// Current mouse listener
	MouseListener activeListener;
	MouseMotionListener activeMotionListener;

	/** Properly register new listener (and unregister old one if present). */
	public void setActiveListener(MouseListener ml) {
		this.removeMouseListener(activeListener);
		activeListener = ml;
		if (ml != null) {
			this.addMouseListener(ml);
		}
	}

	/**
	 * Properly register new motion listener (and unregister old one if
	 * present).
	 */
	public void setActiveMotionListener(MouseMotionListener mml) {
		this.removeMouseMotionListener(activeMotionListener);
		activeMotionListener = mml;
		if (mml != null) {
			this.addMouseMotionListener(mml);
		}
	}

	/**
	 * Construct ApplicationPanel with a Model instance used for information.
	 */
	public ApplicationPanel(Model m) {
		super();
		this.model = m;
		initialize();
	}

	/**
	 * Initialize frame by adding all views
	 */
	void initialize() {
	}

	public void redraw() {
		// nothing to draw into? Must stop here.
		if (offscreenImage == null)
			return;

		// clear the image.
		offscreenGraphics.clearRect(0, 0, this.getWidth(), this.getHeight());

		/** Draw all words. */
		for (Word word : model.getBoard().words) {
			paintWord(offscreenGraphics, word);
		}
	}

	/** Make sure that image is created as needed. */
	void ensureImageAvailable(Graphics g) {
		if (offscreenImage == null) {
			offscreenImage = this
					.createImage(this.getWidth(), this.getHeight());
			offscreenGraphics = offscreenImage.getGraphics();
			canvasGraphics = g;

			redraw();
		}
	}

	/**
	 * To Draw within a JPanel, you need to have a protected void method of this
	 * name. Note that the first operation of this method MUST BE to invoke
	 * super.paintComponent(g)
	 * 
	 * @param g
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		ensureImageAvailable(g);
		g.drawImage(offscreenImage, 0, 0, getWidth(), getHeight(), this);

		// draw the line to separate the protected area and unprotected area
		g.drawLine(0, Board.heightOfProtectedArea, Board.widthOfBoard,
				Board.heightOfProtectedArea);

		// draw selected
		Word selected = model.getSelectedWord();
		if (selected != null) {
			paintWord(g, selected);
		}
	}

	/** Paint the word right to the screen */
	public void paintWord(Word word) {
		paintWord(canvasGraphics, word);
	}

	/** Paint the word into the given graphics context. */
	public void paintWord(Graphics g, Word word) {
		if (g == null) {
			return;
		}
		// this.showBoardState();
		if (word.isProtected()) {
			g.setColor(Color.WHITE);
			if (word.isInRow()) {
				g.setColor(Color.YELLOW);
				if (word.isInPoem()) {
					g.setColor(Color.GREEN);
				}
			}
		} else {
			g.setColor(Color.LIGHT_GRAY);
		}
		g.fillRect(word.getX(), word.getY(), word.getWidth(), word.getHeight());
		g.setColor(Color.BLACK);
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D r = fm.getStringBounds(word.getContent(), g);
		int x = word.getX() + (word.getWidth() - (int) r.getWidth()) / 2;
		int y = word.getY() + (word.getHeight() - (int) r.getHeight()) / 2
				+ fm.getAscent();
		g.drawString(word.getContent(), x, y);
	}

	/** Repaint to the screen just the given part of the image. */
	public void paintBackground(Word word) {
		// Only updates to the screen the given region
		if (canvasGraphics != null) {
			canvasGraphics.drawImage(offscreenImage, word.getX(), word.getY(),
					word.getWidth(), word.getHeight(), this);
			repaint(word.getX(), word.getY(), word.getWidth(), word.getHeight());
		}
	}

	public void showBoardState() {
		ArrayList<Word> words = model.getBoard().words;
		ArrayList<Row> rows = model.getBoard().rows;
		System.out.println("Words");
		System.out.println("content   isProtected   isInRow   x   y");
		for (Word word : words) {
			System.out.println(word.getContent() + "   " + word.isProtected()
					+ "   " + word.isInRow() + "   " + word.getX() + "   "
					+ word.getY());
		}
		System.out.println("Rows");
		for (Row row : rows) {
			System.out.println("Row " + row.getWordList().get(0).getContent());
			List<Word> wordList = row.getWordList();
			for (Word word : wordList) {
				System.out.println(word.getContent() + "   "
						+ word.isProtected() + "   " + word.isInRow() + "   "
						+ word.getX() + "   " + word.getY());
			}
		}
	}

}
