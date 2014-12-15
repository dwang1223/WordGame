package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import model.Board;
import model.Model;
import model.Word;
import view.ApplicationPanel;

/**
 * PublishController Class
 * 
 * @author diwang
 *
 */
public class PublishController extends MouseAdapter {

	/** Needed for controller behavior. */
	Model model;
	ApplicationPanel panel;

	/** Constructor holds onto key manager objects. */
	public PublishController(Model model, ApplicationPanel panel) {
		this.model = model;
		this.panel = panel;
	}

	/** Separate out this function for testing purposes. */
	public void process() {
		Board board = model.getBoard();

		// create image of just three random words. Note this is demonstration
		// shows the mechanics
		// and you will have to adjust accordingly for your poem. This sample
		// code is here because
		// I don't have Poem objects in the WordMap application
		ArrayList<Word> three = new ArrayList<Word>();
		int minx = 999999, maxx = -99999;
		int miny = 999999, maxy = -99999;
		for (Word word : board.words) {
			if (three.size() > 2) {
				break;
			}
			if (word.getX() < minx) {
				minx = word.getX();
			}
			if (word.getX() + word.getWidth() > maxx) {
				maxx = word.getX() + word.getWidth();
			}
			if (word.getY() < miny) {
				miny = word.getY();
			}
			if (word.getY() + word.getHeight() > maxy) {
				maxy = word.getY() + word.getHeight();
			}

			three.add(word);
		}

		// This full-sized image has wasted space. We will deal with later
		BufferedImage bufferedImage = new BufferedImage(maxx, maxy,
				BufferedImage.TYPE_INT_RGB);

		// this just draws the three shapes into the graphics context
		Graphics g = bufferedImage.getGraphics();
		for (Word word : three) {
			panel.paintWord(g, word);
		}
		g.dispose();

		// now translate image into transparent one
		int color = bufferedImage.getRGB(0, 0);
		Image image = makeColorTransparent(bufferedImage, new Color(color));

		// extract just the lower-right based on the minx, miny
		BufferedImage transparent = imageToBufferedImage(image, maxx - minx,
				maxy - miny, 0, 0, maxx - minx, maxy - miny, minx, miny, maxx,
				maxy);

		// this places output in sample file. You should consider creating file
		// names "on the fly..."
		// if you do this, as well as store PNG files in an images/ directory
		File out = new File("sample.png");
		try {
			ImageIO.write(transparent, "PNG", out);
			System.out.println("Wrote file:" + out);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Helper function to convert image into Buffered Image.
	 * 
	 * The image passed in has more room than needed. This is because the shapes
	 * are drawn using the same code from the application, which positions the
	 * words as they would appear on the screen. Of course, our goal is to
	 */
	static BufferedImage imageToBufferedImage(Image image, int width,
			int height, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1,
			int sx2, int sy2) {
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = bufferedImage.createGraphics();
		g.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		g.dispose();

		return bufferedImage;
	}

	/**
	 * This takes a given image (and proposed transparent color) and does the
	 * necessary work to produce an image whose transparency pixels have all
	 * been set.
	 * 
	 * Helper methods from :
	 * http://stackoverflow.com/questions/665406/how-to-make
	 * -a-color-transparent-in-a-bufferedimage-and-save-as-png
	 */
	public static Image makeColorTransparent(BufferedImage im, final Color color) {
		ImageFilter filter = new RGBImageFilter() {

			// the color we are looking for... Alpha bits are set to opaque
			public int markerRGB = color.getRGB() | 0xFF000000;

			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					// Mark the alpha bits as zero - transparent
					return 0x00FFFFFF & rgb;
				} else {
					// nothing to do
					return rgb;
				}
			}
		};

		ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}
}
