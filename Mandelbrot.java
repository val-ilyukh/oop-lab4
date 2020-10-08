import java.awt.geom.Rectangle2D.Double;

public class Mandelbrot extends FractalGenerator {
	
	public static final int MAX_ITERATIONS = 2000;
	@Override
	public void getInitialRange(Double range) {
		range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;		
	}

	
	public int numIterations(double x, double y) {
		double sequence_x = 0;
		double sequence_y = 0;
		double sequence_x_old = 0;
		double sequence_y_old = 0;
		int numiterations = 0;
		short flag = 0;
		while ((flag == 0) && (numiterations < MAX_ITERATIONS)) {
			if (((sequence_x * sequence_x) + (sequence_y * sequence_y)) > 4)  {
				flag = 1;
			}
			sequence_x_old = sequence_x;
			sequence_y_old = sequence_y;
			sequence_x = sequence_x_old * sequence_x_old - sequence_y_old * sequence_y_old + x;
			sequence_y = 2 * sequence_x_old * sequence_y_old + y;
			numiterations++;
		}
		if (flag == 0) return -1;
		else return numiterations;
	}
	

}
