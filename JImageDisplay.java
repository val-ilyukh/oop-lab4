import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class JImageDisplay extends JComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	public static final int TYPE_INT_RGB = 1;
	public JImageDisplay (int width, int height) {
		this.setImage(new BufferedImage(width, height, TYPE_INT_RGB));
		Dimension dim = new Dimension(width, height);
		this.setPreferredSize(dim);
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage (image, 0, 0, image.getWidth(), image.getHeight(), null);
	}
	public void clearImage() {
		int[] res = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, 1);
		for (int k : res) {
			res[k] = 0;
		}
		image.setRGB(0, 0, image.getWidth(), image.getHeight(), res, 0, 1);
	}
	public void drawPixel(int x, int y, int colorRGB) {
		image.setRGB(x, y, colorRGB);
	}
	
	
}
