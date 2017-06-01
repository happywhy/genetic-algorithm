/* DNA.java
 * Represents the genes of the organisms and manipulates them.
 * Vianney Coppe - June 2017
 */

public class DNA {
	
	private static int lifetime = Main.lifetime;
	private static double rate = Main.mutationRate, minForce = 0.002, maxForce = 0.05;
	
	double xs[], ys[];
	
	/* Create DNA with random genes */
	public DNA() {
		xs = new double[lifetime];
		ys = new double[lifetime];
		
		double z, r;
		for(int i=0; i<lifetime; i++) {
			z = Math.random()*Math.PI*2;
			r = minForce + Math.random()*(maxForce-minForce);
			xs[i] = Math.sin(z)*r;
			ys[i] = Math.cos(z)*r;
		}
	}
	
	/* Crossover between 2 parents to generate next generation */
	public DNA crossover(DNA partner) {
		DNA child = new DNA();
		int cross = (int) (Math.floor(Math.random()*lifetime));
		for(int i=0; i<lifetime; i++) {
			if(i < cross) {
				child.xs[i] = this.xs[i];
				child.ys[i] = this.ys[i];
			}
			else {
				child.xs[i] = partner.xs[i];
				child.ys[i] = partner.ys[i];
			}
		}
		
		return child;
	}
	
	/* Mutate the genes with probability "rate" */
	public void mutate() {
		int rv, val = (int) (Math.floor((100*rate)));
		double z, r;
		for(int i=0; i<lifetime; i++) {
			rv = (int) (Math.random()*100);
			if(rv <= val) {
				z = Math.random()*Math.PI*2;
				r = minForce + Math.random()*(maxForce-minForce);
				xs[i] = Math.sin(z)*r;
				ys[i] = Math.cos(z)*r;
			}
		}
	}
}
