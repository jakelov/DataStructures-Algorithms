import java.util.Scanner;
import java.io.*;
import java.lang.Math;
public class Closest {
DoubleNode [][] grid;
int b = 1000;
public int hash(double num)
{
    // return the an output from double to int by casting.
    // make sure it returns a valid input within b x b.
     return  ((int) (b * num));
}
public Closest(int b)
{
    grid = new DoubleNode[b][b];
    b = b;
}

public double findClosest()
{
    try
    {
    Scanner read = new Scanner(System.in);
    while(read.hasNext())
     {
        double xVal = read.nextDouble();
        int x = hash(xVal); // hashes x
        double yVal = read.nextDouble();
        int y = hash(yVal); // hashes y
       // System.out.println("xVal: " + xVal + " hashed too: " + x + " & yVal: " + yVal " hashed too: " + y);
        grid[x][y] = new DoubleNode(xVal, yVal, grid[x][y]);
     }
    }
     catch (Exception e)
     {
         System.out.println("NO INPUT PROVIDED");
     }
     double minimum = Double.NaN;
for(int x = 0; x < b; x++) // rows
{
    for(int y = 0; y < b; y++) // columns
    {
        DoubleNode xCur = grid[x][y]; // linked list in every grid
        while(xCur != null)
        {
            for(int i = x - 1; i < x + 2; i++) // get neighbors left/ right
            {
                for (int j = y - 1; j < y + 2; j++) // get neighbors up/down
                {
                    if (checkBounds(i, j)) {
                    DoubleNode yCur = grid[x][y]; // linked list of the neighbors
                    while(yCur != null)
                    {
                       if (xCur != yCur) { 
                       double distance = findDistance(xCur.x, xCur.y, yCur.x, yCur.y);
                       if (Double.isNaN(minimum) || distance < minimum) 
                           minimum = distance; 
                       }
                       yCur = yCur.next;
                    }
                  }
                }
            }
            xCur = xCur.next;
        }
      }
  }
  return minimum;
}
public static void main(String [] args)
{
    Closest cs = new Closest(1000);
    double printThis = cs.findClosest();
    System.out.println("The closest pair of points is: " + printThis);
}

public double findDistance(double x1, double y1, double x2, double y2)
{
   return Math.sqrt((Math.pow(x2-x1, 2) + (Math.pow(y2-y1, 2)))); 
}

public boolean checkBounds(int x, int y)
{
    if (x < 0 || x >= b || y >= b || y < 0) 
    {
        return false;
    }
    else
        return true;
}
}
