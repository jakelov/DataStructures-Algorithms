import java.util.Scanner;
public class RunwayReservation {
	private static int n;
	private static int k;

	public static void main(String [] args) {
		Scanner kb = new Scanner(System.in);
		n = kb.nextInt(); // The total number of requests.
		k = kb.nextInt(); // Grace time between requests.

		// Variables for getting the input.
		String cmd;
		int time = 0;
		String flightname = null;
		String flightnumber = null;
		String source = null;
		String destination = null;
		int curtime = 0; // Current time, initialized to 0.

		// An array of requests. This is the data stored outside of our binary search tree.
		Requests [] reqs = new Requests[n];
		int i = 0;

		// Reading the input. All requests are read from the input file and stored in array reqs.
		while(kb.hasNext()) {
			cmd = kb.next();

			if (cmd.equals("r")) {
				time = kb.nextInt();
				flightname = kb.next();
				flightnumber = kb.next();
				source = kb.next();
				destination = kb.next();

				reqs[i++] = new Requests(cmd, time, flightname, flightnumber, source, destination);
			}
			else {
				time = kb.nextInt();
				reqs[i++] = new Requests(cmd, time);
			}
			kb.nextLine();
		}

		// TODO: Code to process each request and solve the Runway Reservation problem.
		BST T = new BST();
		for (int itr = 0; itr < n; itr++) {
			/*
			If the request is ‘r’, then check if it is a valid request (there is a grace period of
			k units between this request and its previous and next reserved requests). If it is
			valid, then insert it into a BST T.
			*/
			if(reqs[itr].getCommand().equals("r"))
			{
				//System.out.println("In R if statement.");
				// get variables for time, pred and succ, and their times to make it easier to read.
				int reqTime = reqs[itr].getTime();
				Node predIndex = T.pred(reqTime);
				Node succIndex = T.succ(reqTime);
				int predTime;
				int succTime;
				if (predIndex == null && succIndex == null) //  there is no pred or succ (there are no reservations)
				{
					//System.out.println("In R double null case");
					T.insert(reqTime, itr);
				}
				else if (predIndex != null && succIndex == null) // - there is a pred, but no succ (there is a reservation before this request)
				{
					predTime = predIndex.getTime();
					if (predTime <= reqTime - k)
					{
						//System.out.println("In R no succ case, predTime = " + predTime);
						T.insert(reqTime, itr);
					}
				}
				else if (predIndex == null && succIndex != null) // there is no pred but there is a succ
				{
					succTime = succIndex.getTime();
					if (succTime >= reqTime + k)
					{
						//System.out.println("In R no pred case");
						T.insert(reqTime, itr);
					}
				}
				else if (predIndex != null && succIndex != null) { // there is a pred/succ
					predTime = predIndex.getTime();
					succTime = succIndex.getTime();
					//System.out.println("In R neither null case (before if)");
					if (predTime <= reqTime - k && succTime >= reqTime + k) {
						//System.out.println("In reservation case");
						T.insert(reqTime, itr);		
					}
				}
				//System.out.println("This iteration proccessed time: " + reqTime);
				//System.out.println("TREE PRINT");
				//T.print();
			}
			else if (reqs[itr].getCommand().equals("t"))
			{
				curtime += reqs[itr].getTime();	
				//System.out.println("In time outside loop");
				//System.out.println("Curtime b4 loop: " + curtime);
				//System.out.println("Min time b4 loop: " + T.min().getTime());			
				System.out.println("Current time = " + curtime + " units");
				while (T.min().getTime() < curtime) {
					System.out.println(reqs[T.min().getReq_index()].getAirline());
					T.delete(T.min().getTime());
					//System.out.println("In time loop with new min time: " + T.min().getTime());
					}	 
			}
		}
        //System.out.println("Code is here");
       // System.out.println(reqs[T.min().getReq_index()].getAirline());
		//System.out.println(reqs[T.max().getReq_index()].getAirline());
		// this solution below passes the first flight data but not the second.
		Node xNode = T.min();
		curtime = T.max().getTime();
		System.out.println("Current time = " + curtime + " units");
		while (xNode != null)
		{
			System.out.println(reqs[xNode.getReq_index()].getAirline());
			T.delete(xNode.getTime());
			xNode = T.succ(xNode.getTime());
		}
	}
}
