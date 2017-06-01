/* Main.java
 * Launcher for this genetic algorithm project.
 * Vianney Coppe - June 2017
 */

public class Main {
	
	public static int cycles = 1001, lifetime = 800, populationSize = 500, targetSize = 1, obstacleSize = 35, 
			WIDTH = 800, HEIGHT = 600, startX = 0, startY = HEIGHT/2;
	public static double mutationRate = 0.01;

	public static void main(String[] args) {
		
		/* Generate obstacles and target */
		Obstacle[] target = new Obstacle[targetSize];
		target[0] = new Obstacle(WIDTH-30, HEIGHT/2-15, WIDTH, HEIGHT/2+15);
		//target[1] = new Obstacle(WIDTH-30, HEIGHT/4-15, WIDTH, HEIGHT/4+15);
		//target[2] = new Obstacle(WIDTH-30, 3*HEIGHT/4-15, WIDTH, 3*HEIGHT/4+15);
		
		Obstacle[] obstacles = new Obstacle[obstacleSize];
		
		for(int j=1; j<=4; j++) for(int i=0; i<5; i++) {
			if(i >= 1 && i <= 3) obstacles[i + (j-1)*5] = new Obstacle(j*WIDTH/5-10, 40+i*HEIGHT/6, j*WIDTH/5+10, 100+(i%5)*HEIGHT/6);
			else obstacles[i + (j-1)*5] = new Obstacle(j*WIDTH/5-10, 50+i*HEIGHT/6, j*WIDTH/5+10, 90+(i%5)*HEIGHT/6);
		}
	
		for(int i=20; i<25; i++) obstacles[i] = new Obstacle(WIDTH/5+40, 70+(i%5)*HEIGHT/6, 2*WIDTH/5-40, 90+(i%5)*HEIGHT/6);
		for(int i=25; i<30; i++) obstacles[i] = new Obstacle(2*WIDTH/5+40, 70+(i%5)*HEIGHT/6, 3*WIDTH/5-40, 90+(i%5)*HEIGHT/6);
		for(int i=30; i<35; i++) obstacles[i] = new Obstacle(3*WIDTH/5+40, 70+(i%5)*HEIGHT/6, 4*WIDTH/5-40, 90+(i%5)*HEIGHT/6);
		
		/* Create initial population */
		Population population = new Population(obstacles, target);
		
		/* Apply life cycles */
		for(int i=0; i<cycles; i++) population.cycle();
	}
}
