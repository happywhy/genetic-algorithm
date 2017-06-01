/* Obstacle.java
 * Represents an obstacle (or target).
 * Vianney Coppe - June 2017
 */

public class Obstacle {
	
	private static int WIDTH = Main.WIDTH, HEIGHT = Main.HEIGHT;
	private static double SPEED = 2;
	
	int x1, y1, x2, y2;
	private int mod, cnt, x1Start, x2Start, y1Start, y2Start;
	private double xv, yv, xvStart, yvStart;
	
	/* Creates random rectangle */
	public Obstacle() {
		x1 = (int) (Math.floor(Math.random() * WIDTH));
		y1 = (int) (Math.floor(Math.random() * HEIGHT));
		
		x2 = x1 + (int) (Math.floor(Math.random() * (WIDTH-x1)/2));
		y2 = y1 + (int) (Math.floor(Math.random() * (HEIGHT-y1)/2));
		
		int z = (int) (Math.floor(Math.random()*2));
		mod = 10 + (int) (Math.floor(Math.random()*100));
		cnt = 0;
		
		if(z == 1) {
			xv = SPEED;
			yv = 0;
		}
		else {
			xv = 0;
			yv = SPEED;
		}
		
		x1Start = x1;
		x2Start = x2;
		y1Start = y1;
		y2Start = y2;
		
		xvStart = xv;
		yvStart = yv;
	}
	
	/* Creates rectangle */
	public Obstacle(int xx1, int yy1, int xx2, int yy2) {
		x1 = xx1;
		y1 = yy1;
		
		x2 = xx2;
		y2 = yy2;
		
		int z = (int) (Math.floor(Math.random()*2));
		mod = 20 + (int) (Math.floor(Math.random()*100));
		cnt = 0;
		
		if(z == 1) {
			xv = SPEED;
			yv = 0;
		}
		else {
			xv = 0;
			yv = SPEED;
		}
		
		x1Start = x1;
		x2Start = x2;
		y1Start = y1;
		y2Start = y2;
		
		xvStart = xv;
		yvStart = yv;
	}
	
	/* Moves the obstacle */
	public void update() {
		cnt++;
		
		if(cnt > mod/2) {
			cnt = -mod/2;
			xv = -xv;
			yv = -yv;
		}
		
		x1 += xv;
		x2 += xv;
		y1 += yv;
		y2 += yv;
	}
	
	/* Resets its position */
	public void reset() {
		cnt = 0; 
		x1 = x1Start;
		x2 = x2Start;
		y1 = y1Start;
		y2 = y2Start;
		
		xv = xvStart;
		yv = yvStart;
	}
}
