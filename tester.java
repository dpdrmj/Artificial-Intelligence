package ailab;

import java.util.*;
public class tester
{
    public static void main(String args[])
    {
        Scanner in=new Scanner(System.in);
        int n;
        System.out.print("Enter n: ");
        n=in.nextInt();
        GenerateAndTest g=new GenerateAndTest(n);
        g.rungt();
    }
}