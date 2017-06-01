/* Population.java
 * Manage the population and its evolution.
 * Vianney Coppe - June 2017
 */

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Population {
	
	private static int size = Main.populationSize, MOD = 20;
	
	Organism[] organisms;					// current organisms
	private int gen;						// number of the current generation
	private double minScore,				// info about the generation
		maxScore,
		averageScore;
	private int minTime;
	private int reached;					// organisms that reached target
	private ArrayList<Organism> matingPool;	// to create the next generation
	
	private Obstacle[] obstacles, target;
	private Display display;
	
	/* Creates a population of random organisms */
	public Population(Obstacle[] obst, Obstacle[] targ) {
		gen = 0;
		matingPool = new ArrayList<Organism>();
		organisms = new Organism[size];
		obstacles = obst;
		target = targ;
		
		/* Build window for animation */
		display = new Display(obstacles, target, this);
		
		for(int i=0; i<size; i++) organisms[i] = new Organism(obstacles, target);
	}
	
	/* Run test for each organism */
	private void live() {
		
		minScore = 10000000000000000000000.0;
		maxScore = 1;
		averageScore = 0;
		reached = 0;
		minTime = Integer.MAX_VALUE;
		
		double score;
		
		if(gen % MOD == 0) display.updateIt(true);
		
		boolean finished = false;
		while(!finished) {
			finished = true;
			
			for(int i=0; i<obstacles.length; i++) {
				obstacles[i].update();
			}
			
			for(int i=0; i<size; i++) {
				organisms[i].update();
				
				if(!organisms[i].end()) finished = false;
			}
			
			if(gen % MOD == 0) {
				Thread thr = new Thread(display);
				thr.start();
				
				try {
					TimeUnit.MILLISECONDS.sleep(72);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		for(int i=0; i<size; i++) {
			score = organisms[i].fitness();
			
			minScore = Math.min(minScore, score);
			maxScore = Math.max(maxScore, score);
			averageScore += score;
			
			if(organisms[i].finished()) {
				reached++;
				minTime = Math.min(minTime, organisms[i].time());
			}
		}
		
		averageScore /= size;
	}
	
	/* Constructs the mating pool */
	private void select() {
		matingPool.clear();
		
		for(int i=0; i<size; i++) for(int e=0; e<=100*organisms[i].fitness()/maxScore; e++) {
			matingPool.add(organisms[i]);
		}
	}
	
	/* Create new population from the past one */
	private void reproduct() {
		for(int i=0; i<size; i++) {
			int m = (int) (Math.floor(Math.random() * matingPool.size()));
			int d = (int) (Math.floor(Math.random() * matingPool.size()));
			
			Organism mom = matingPool.get(m);
			Organism dad = matingPool.get(d);
			
			DNA child = mom.dna.crossover(dad.dna);
			
			child.mutate();
			
			organisms[i] = new Organism(obstacles, target, child);
		}
		
	}
	
	/* Complete cycle of a generation */
	public void cycle() {
		for(int i=0; i<obstacles.length; i++) {
			obstacles[i].reset();
		}
			
		live();
		
		if(gen % MOD == 0) System.out.printf("\nGeneration %d : [%f, %f] ~ %f\n\tREACHED : %f\n\tBEST TIME : %d\n", gen, minScore, maxScore, averageScore, (double) reached / (double) size, minTime);
		
		select();
		
		reproduct();
		
		gen++;
	}
	
	/* Access function */
	public int gen() {
		return gen;
	}
}
