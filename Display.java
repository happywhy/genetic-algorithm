/* Display.java
 * Creates a window and displays an animation of the organisms trying to reach the target.
 * Vianney Coppe
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Display extends JFrame implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private Obstacle[] obstacles, target;
	private Population population;
	
	private final static int radius = 2;
	private static int WIDTH = Main.WIDTH, HEIGHT = Main.HEIGHT;
	
	private boolean clr;
	
	private final static Color back = new Color(249, 249, 207), obst = new Color(163, 116, 88), driv = new Color(32, 112, 81),
			targ = new Color(102, 135, 104), cent = new Color(143, 193, 146);
	
	public Display(Obstacle[] obst, Obstacle[] targ, Population pop) {
		super();
		
		obstacles = obst;
		target = targ;
		population = pop;
		clr = true;
		
		setSize(Main.WIDTH, Main.HEIGHT);
		setTitle("Path finding with genetic algorithm");
		setLocation(200, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(back);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		if(clr) {
			g.fillRect(0, 0, WIDTH, HEIGHT);
			return;
		}

		g.setColor(Color.DARK_GRAY);
		g.drawString("Generation : "+population.gen(), 15, HEIGHT-15);
		g.drawString("Mutation rate : "+Main.mutationRate, WIDTH/2-70, HEIGHT-15);
		g.drawString("Population size : "+Main.populationSize, WIDTH-150, HEIGHT-15);
		
		for(Obstacle o : target) {
			g.setColor(targ);
			g.fillRect(o.x1, o.y1, o.x2-o.x1, o.y2-o.y1);
			g.setColor(cent);
			g.fillOval(o.x1+5, o.y1+5, o.x2-o.x1-10, o.y2-o.y1-10);
		}
		
		g.setColor(obst);
		for(Obstacle o : obstacles) {
			g.fillRect(o.x1, o.y1, o.x2-o.x1, o.y2-o.y1);
		}
		
		g.setColor(driv);
		for(Organism d : population.organisms) {
			if(d != null && (d.finished() || !d.end())) {
				//g.drawLine((int) d.px, (int) d.py, (int) d.x, (int) d.y);
				g.fillOval((int) d.x-radius, (int) d.y-radius, 2*radius, 2*radius);
			}
		}
	}

	public void updateIt(boolean clear) {
		clr = clear;
		revalidate();
		repaint();
	}

	@Override
	public void run() {
		clr = false;
		revalidate();
		repaint();
	}
}
