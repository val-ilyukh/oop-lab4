import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class FractalExplorer {
	private static final int EXIT_ON_CLOSE = 1;
	private int screensize = 800;
	private JImageDisplay image = new JImageDisplay(screensize, screensize);
	private FractalGenerator fg;
	private Rectangle2D.Double range;

	public FractalExplorer () {
		range = new Rectangle2D.Double();
		fg = new Mandelbrot();
		fg.getInitialRange(range);
	}
	public void createAndShowGUI () {
		JFrame window = new JFrame("Fractal explorer");
		JButton button = new JButton("Clear");
		button.setActionCommand("clear");
        button.addActionListener(new ButtonHandler());
        window.addMouseListener(new MouseHandler());
		window.add(image, BorderLayout.CENTER);
		window.add(button, BorderLayout.SOUTH);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
		
	}
	private void drawFractal() {
		for (int i = 0; i < screensize; i++) {
			for (int j = 0; j < screensize; j++) {
				double xCoord = FractalGenerator.getCoord (range.x, range.x + range.width, screensize, i);
				double yCoord = FractalGenerator.getCoord (range.y, range.y + range.height, screensize, j);
				int rgbColor;
				int res = fg.numIterations(xCoord, yCoord);
				if (res == -1) {
					rgbColor = 0;					
				}
				else {
					float hue = 0.7f + (float) res / 400f;
					rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
				}
				image.drawPixel(i, j, rgbColor);
				
			}
		}
		image.repaint();
	}
	
	public class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String comm = e.getActionCommand();
            if (comm.equals("clear")){
            	range = new Rectangle2D.Double();
                fg.getInitialRange(range);
                drawFractal();
            }
        }
    }
	public class MouseHandler extends MouseAdapter{
        public void mouseClicked (MouseEvent e) {
        	double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, screensize, e.getX());
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, screensize, e.getY());
            fg.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
	        drawFractal();
        }
    }
	public static void main(String[] args) {
		FractalExplorer fe = new FractalExplorer();
		fe.createAndShowGUI();
		fe.drawFractal();		
	}
}
