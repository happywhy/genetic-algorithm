/* Organism.java
 * Represents an organism and allows to simulate its behaviour.
 * Vianney Coppe - June 2017
 */

public class Organism {
	
	private static int lifetime = Main.lifetime, WIDTH = Main.WIDTH, HEIGHT = Main.HEIGHT, 
			startX = Main.startX, startY = Main.startY;
	private Obstacle[] obstacles, target;
	
	DNA dna;						// DNA of the driver
	private double fitness;			// fitness
	private int time;				// time to finish the race
	private double distance;		// distance from target
	private boolean finished;		// true <==> finished the race
	private int collisions;			// number of collisions
	private boolean end;			// simulation ended
	
	double px, py, x, y, xs, ys, xa, ya;	// position and speed of the organism
	
	private final double DEFAULT = -1000000007, DRAG = 0.9;
	
	/* Create random organism */
	public Organism(Obstacle[] obst, Obstacle[] targ) {
		dna  = new DNA();
		fitness = DEFAULT;
		finished = end = false;
		distance = Double.MAX_VALUE;
		collisions = 0;
		
		time = 0;
		px = x = startX;
		py = y = startY;
		xs = ys = xa = ya = 0;
		
		obstacles = obst;
		target = targ;
	}
	
	/* Create organism from DNA */
	public Organism(Obstacle[] obst, Obstacle[] targ, DNA newdna) {
		dna  = newdna;
		fitness = DEFAULT;
		finished = end = false;
		distance = Double.MAX_VALUE;
		collisions = 0;
		
		time = 0;
		px = x = startX;
		py = y = startY;
		xs = ys = xa = ya = 0;
		
		obstacles = obst;
		target = targ;
	}
	
	/* Compute fitness */
	private void calcFitness() {
		if(finished) distance = 1;
		fitness = 1/(Math.pow(distance/WIDTH, 4)*Math.pow(time, 2));
		
		if(collisions > 0) fitness *= 0.5;
	}
	
	/* Run the organism's behavior */
	public void update() {
		if(time == lifetime) end = true;
		if(finished) return;
		if(end) return;
		
		ya += dna.ys[time];
		xa += dna.xs[time];
		
		ys += ya;
		xs += xa;
		
		px = x;
		py = y;

		x += xs;
		y += ys;
		
		for(int k=0; k<target.length && !end; k++) {
			distance = Math.min(distance, Math.sqrt(Math.pow(Math.min(x-target[k].x1,x-target[k].x2), 2) + Math.pow(Math.min(y-target[k].y1, y-target[k].y2), 2)));
			
			if(x >= target[k].x1 && x <= target[k].x2 && y >= target[k].y1 && y <= target[k].y2) {
				finished = end = true;
			}
		}

		if(!end && (x < 0 || x > WIDTH || y < 0 || y > HEIGHT)) {
			end = true;
			time = lifetime;
			collisions++;
		}

		for(int k=0; k<obstacles.length && !end; k++) {
			if(x >= obstacles[k].x1 && x <= obstacles[k].x2 && y >= obstacles[k].y1 && y <= obstacles[k].y2) {
				end = true;
				collisions++;
				time = lifetime;
			}
		}
		
		ya *= DRAG;
		xa *= DRAG;
		
		time++;
	}

	/* Access function */
	public double fitness() {
		if(fitness == DEFAULT) calcFitness();
		return fitness;
	}
	
	/* Access function */
	public int time() {
		return time;
	}
	
	/* true <==> simulation is finished */
	public boolean end() {
		return end;
	}
	
	/* true <==> organism reached target */
	public boolean finished() {
		return finished;
	}
}
