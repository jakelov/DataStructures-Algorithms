import java.util.Scanner;

public class AreaUnderACurve {

	/**
		The function f(x) = x^2 + x + 1.
	*/
	private static double f(double x) {
		return x * x + x + 1; 
	}

	/**
		Returns an approximation for the area under the curve f(x) between x = a and x = b.
	*/
	private static double computeArea(double a, double b) {
		//boolean flag = true; // flags for while loops
		//boolean flag2 = true;
		//int splitCount = 0;
		//double midpoint = 0;
		double error = 1e-08; // This is the comparison error. See document for description.
		// We create the PQ and insert the first interval inside.
		// Computing an approximation for the area under the curve
		PriorityQueue pq = new PriorityQueue(1); 
		Interval ival = new Interval(a,b);
		pq.insert(ival); 
		double area = (b-a) * f(b);
		double newArea = area;
		while (true) {
		Interval max = pq.remove_max(); // we 'pop' off the max to get as an Interval
		double end = max.getEnd();
		double start = max.getStart();
		double midpoint = findMidPoint(max); // (start + end)/2f; 
		newArea = area - (end - start) * f(end) + (midpoint - start) * f(midpoint) + (end - midpoint) * f(end);
		//if (isErrorChangeSig(max, error) == true) { 
			if (Math.abs(newArea-area) <= error) {
			area = newArea;
			break;
			// if error significant, we add two new interval's
			//midpoint = findMidPoint(max);
			//pq.insert(new Interval(max.getStart(), midpoint));
			//pq.insert(new Interval(midpoint, max.getEnd()));	
			//pq.insert(max);
			//splitCount++;
		}
		else 
		{
			pq.insert(new Interval(start, midpoint));
			pq.insert(new Interval(midpoint, end));
			area = newArea;	
			//pq.insert(max);
			//flag = false; 
		}		
	}
		return area; // return the computed area.
	}

	// Helper Method to find the midpoint between an Interval by splitting it in half.
	public static double findMidPoint(Interval a)
	{
		double midpoint = (a.getStart() + a.getEnd())/2f;
		return midpoint;
	}

	// Helper Method to check to see if the change in error is significant to continue splitting.
	public static boolean isErrorChangeSig(Interval A, double error)
	{
		double area = calculateArea(A);
		double mpArea = calcAreaMP(A); // smaller
		double difference = area - mpArea;
		if (difference > error)
		{
			return true;
		}
		return false;
	}

	// Helper Method to Calculate the area of an single Interval
	public static double calculateArea(Interval a)
	{
		double area = (a.getEnd() - a.getStart()) * f(a.getEnd());
		return area;
	}

	// Helper Method to calculate the area of a Interval with a midpoint. This is more 'accurate'
	public static double calcAreaMP(Interval a)
	{
		double midpoint = findMidPoint(a);
		double area1 = (midpoint - a.getStart()) * f(midpoint);
		double area2 = (a.getEnd() - midpoint) * f(a.getEnd());
		return area1 + area2;
	}
	public static void main(String [] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("We have the function f(x) = x^2 + x + 1.");
		System.out.print("Please enter lower value a: ");
		double a = kb.nextDouble();
		System.out.print("Please enter upper value b: ");
		double b = kb.nextDouble();
		double area = computeArea(a, b);
		System.out.println("\nAn approximation for the area under the curve f(x) \nbetween a = " + a + " and b = " + b + " is " + area);
	}
}
