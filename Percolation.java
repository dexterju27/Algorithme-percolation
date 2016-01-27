import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int N = 0; // the number of size
	private boolean[][] area; // the area
	private WeightedQuickUnionUF arrayofConnection; // the array
	private WeightedQuickUnionUF arrayofConnectionTop;
	private boolean alreadyPercolates;
	
	public Percolation(int N){
		if (N <= 0) throw new java.lang.IllegalArgumentException("N is less than 0");
		this.N = N;
		this.area = new boolean [this.N][this.N];
		for (int i = 0; i < N; i++ ) {
			for (int j = 0; j< N; j++) {
				area[i][j] = false; //blocked
			}
		}
		arrayofConnection = new WeightedQuickUnionUF (this.N * this.N + 2);
		arrayofConnectionTop = new WeightedQuickUnionUF (this.N * this.N + 1);
		

	};  
	
	// create N-by-N grid, with all sites blocked
	   public void open(int i, int j) {
		   if ( i < 1 || i > N || j < 1 || j > N ) throw new  java.lang.IndexOutOfBoundsException();
		   i = i -1;
		   j = j- 1;
		   
		   int self = i * this.N  + j;
		   // open an area
		   if ( this.area[i][j] == false) {
		   this.area[i][j] = true;
		  if (i - 1 >= 0 ) {
			  if (this.area[i-1][j] == true) {
				  this.arrayofConnection.union( (i - 1) * this.N + j, self);
				  this.arrayofConnectionTop.union( (i - 1) * this.N + j, self);
			  }
		  }
		  
		  if ( i + 1 <= this.N - 1) {
			  if (this.area[i + 1][ j ] == true) {
				  this.arrayofConnection.union((i + 1)* this.N + j, self);
				  this.arrayofConnectionTop.union((i + 1)* this.N + j, self);
			  }
		  }
		  
		  if ( j - 1 >= 0) {
			  if(this.area[i][j - 1] == true) {
				  this.arrayofConnection.union(i* this.N+(j-1), self);
				  this.arrayofConnectionTop.union(i* this.N+(j-1), self);
			  }
		  }
		  
		  if (j + 1 <= this.N - 1) {
			  if (this.area[i][j+1] == true) {
				  this.arrayofConnection.union(i * this.N + j + 1, self);
				  this.arrayofConnectionTop.union(i * this.N + j + 1, self);
			  }
		  }
		   
		   }
		   else return;
	   };         // open site (row i, column j) if it is not open already
	   public boolean isOpen(int i, int j){
		   if( i < 1 || i > N || j < 1 || j > N ) throw new  java.lang.IndexOutOfBoundsException();
		   return this.area[i - 1][j - 1];
		   
	   };  // is site (row i, column j) open?
	   
	   public boolean isFull(int i, int j){
		   if( i < 1 || i > N || j < 1 || j > N ) throw new  java.lang.IndexOutOfBoundsException();
		   i = i - 1;
		   j = j - 1;
		   int self = i* this.N + j; 
		   if (area[i][j] == false) return false;
		   else {
			   for ( int m = 0; m <= this.N - 1; m++) {
				   if (area[0][m] == true ) {
					   if (this.arrayofConnectionTop.connected(m,self) == true ) return true;
				   }
		   }
		   }
		   return false;
		   
	   };   // is site (row i, column j) full?
	   
	   public boolean percolates(){
		  // vitural point 
		   if (this.alreadyPercolates == false) {
		   for (int m = 0; m <= this.N - 1; m++) {
			   if (area[0][m] == true) {
				   
				   this.arrayofConnection.union(m, this.N * this.N);
			   }
		   }
		   
		   for (int m = 0; m <= this.N - 1; m++) {
			   if (area[this.N - 1][m] == true) {
				   
				   this.arrayofConnection.union((this.N - 1) * this.N + m, this.N * this.N + 1);
			   }
			   
		   }
		   
		   if (this.arrayofConnection.connected(this.N * this.N,  this.N * this.N + 1) == true) {
			   this.alreadyPercolates = true;
			   return true;
		   }
		   return false;
		   }
		   else return true;
		   
	   };             // does the system percolate?
}
