public class Raffle {
	private static Raffle raffle;
	private Machine A; 
	private Machine B;
	private long c1; // Cost of executing Machine A.
	private long c2; // Cost of executing Machine B.
	private int n;  // Range of values for raffle tickets.

	public Raffle(Machine _A, Machine _B) {
		A = _A;
		B = _B;
		c1 = A.getCost();
		c2 = B.getCost();
		n = A.getN(); // Will be the same as B.getN();
		play();
	}

	public static void main(String [] args) {
		raffle = new Raffle(new Machine(100000, 17), new Machine(100000, 21));
		// This is just one execution of the Raffle program. We will be testing with different cases.
		// Make sure your program is working for all cases.
	}
	
	public int calculateCost(int a, int b) {
		return a * A.getCost() + b * B.getCost();
	}

	public void findlowCost()
	{
		// Load the array with the values in a "sorted" order using indexes as the ticket number.
		// the array stores the number of times you bought the ticket in the memory location.
		// i, the array index power, is the ticket number
		int Array[][] = new int [2][this.n];
		for (int i = 0; i < this.n; i++) {
            int aValue = A.nextInt();
            int bValue = B.nextInt();
			Array[0][aValue] = i+1;
			Array[1][bValue] = i+1;
		}
		int lowCost = calculateCost(A.getN(), B.getN());
		int lowTicket = 0;
		// We must iterate over the array
		for (int i = 0; i < this.n; i++)
			{
				int cost = calculateCost(Array[0][i] , Array[1][i]);
				 // replace cost into lowest if bigger.
				if (lowCost > cost) 
				{
					lowCost = cost;
					lowTicket = i;
				}
			}
			System.out.println("The raffle ticket with the least cost is " + lowTicket);
			System.out.println("Its cost is " + lowCost);
		}	
		
	private void play() 
	{
		findlowCost();
	}
}
