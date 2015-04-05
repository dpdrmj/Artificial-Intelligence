package ailab;

import java.util.*;

public class GenerateAndTest
{
    private int n;
    private int op;
    private String opres;
    private int[] num;
    private String[] operators={"+","-","^"};
    
    public GenerateAndTest(int n)
    {
        this.n=n;
        opres="";
        getnum();
        getop();
    }
    
    public void getnum()
    {
        Scanner in=new Scanner(System.in);
        num=new int[n];
        System.out.println("Enter "+n+" numbers:");
        for(int i=0;i<n;i++)
        {
            num[i]=in.nextInt();
        }
    }
    
    public void getop()
    {
        Scanner in=new Scanner(System.in);
        System.out.print("Enter the required result: ");
        op=in.nextInt();
    }
    
    public void rungt()
    {
        if(recur(1,num[0],operators[0])||recur(1,num[0],operators[1])||recur(1,num[0],operators[2]))
            System.out.println(num[0]+opres);
        else
            System.out.println("Solution does not exist");
    }
    
    public boolean recur(int a,int res,String s)
    {
        int curr=num[a];
        if(s.equals("+"))
            res=res+curr;
        else if(s.equals("-"))
            res=res-curr;
        else if(s.equals("^"))
            res=(int)Math.pow(res,curr);
        if(a==num.length-1 && res==op)
        {
            opres=" "+s+" "+num[a]+opres;
            return true;
        }
        else if(a==num.length-1)
            return false;
        if(recur(a+1,res,operators[0])||recur(a+1,res,operators[1])||recur(a+1,res,operators[2]))
        {
            opres=" "+s+" "+num[a]+opres;
            return true;
        }
        return false;
    }
}