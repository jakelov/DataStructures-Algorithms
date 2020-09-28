import java.util.Scanner;

public class PeakDay
{
    public int n;
    public float [] rateChange;
    boolean debug = true;
    
    public PeakDay(int n)
    {
        this.n = n;
        this.rateChange = new float [n];
    }

    public static void main(String [] args)
    {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt(); // get size of input
        PeakDay pd = new PeakDay(n);
        for (int i = 0; i < n; i++) {
            pd.rateChange[i] = kb.nextFloat(); // reads the input and stores them in the array.
        }
        kb.close();
        int peakDay = pd.findPeak() + 1;
        System.out.println("The data peaks on day " + peakDay);
    }

    // function to find peak and it's corresponding i.
    public int findPeak()
    {
        float greatestNum = rateChange[1];
        int day = 1;
        for (int i = 0; i < n; i++) {
            if (rateChange[i] > greatestNum) {
                greatestNum = rateChange[i]; // store the greatest day and it's value.
                day = i;
            }
            if (debug) System.out.print("The peak value is " + greatestNum);
            if (debug) System.out.println(" For Day (plus one for actual count): " + day);
        }
        return day;
    }
}