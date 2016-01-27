import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private int N = 0; //the size of grid
	private int T = 0; // the number of experiments
	private double [] result;

	//regardless the sequence of function calls

	  public PercolationStats(int N, int T) {
		  if ( N<= 0 || T <= 0) throw new java.lang.IllegalArgumentException("N or T is ilegal!");
		  this.N = N;
		  this.T = T;
		 
		  this.result = new double [this.T];
		  
		  for (int i = 0; i <= this.T - 1; i++) {
			 Percolation objet = new Percolation(this.N);
			 result[i] = 0;
			  while(objet.percolates() == false) {
				  int x = StdRandom.uniform(this.N) + 1;
				  int y = StdRandom.uniform(this.N) + 1;
				  if (objet.isOpen(x, y) == false) {
				  objet.open(x,y);
				  result[i]++;
				  }
			  }
		  	}
		  
		  
		  
		  
	  }    // perform T independent experiments on an N-by-N grid
	   public double mean()    {
		   
			return StdStats.mean(this.result) / (Math.pow(this.N, 2.0));
		   
	   }// sample mean of percolation threshold
	   public double stddev() {
		  return StdStats.stddev(result) / (Math.pow(this.N, 2.0));
		  
	   } // sample standard deviation of percolation threshold
	   public double confidenceLo()  {
		   return StdStats.mean(this.result) / (Math.pow(this.N, 2.0)) - 1.96 * StdStats.stddev(result) / (Math.pow(this.N, 2.0)) / Math.sqrt(T);
	   }            // low  endpoint of 95% confidence interval
	   public double confidenceHi()   {
		   return  StdStats.mean(this.result) / (Math.pow(this.N, 2.0)) + 1.96 * StdStats.stddev(result) / (Math.pow(this.N, 2.0)) / Math.sqrt(T);
	   } // high endpoint of 95% confidence interval

	   public static void main(String[] args)  {
		   PercolationStats testSample;
		   testSample = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]) );
		   System.out.println("mean = " + testSample.mean());
		   System.out.println("stddev = " + testSample.stddev());
		   System.out.println("regoin de confiance lo = "+ testSample.confidenceLo());
		   System.out.println("regoin de confiance hi = "+ testSample.confidenceHi());
		   
		   
		   
	   }// test client (described below)
}
