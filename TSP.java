package ailab;

import java.util.Scanner;

public class TSP
{
	static int n,adj[][],p[][],g[][],npow;
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter the number of cities");
		
		n = in.nextInt();
		adj = new int[n][n];
		npow = 1;
		
		for(int i=1;i<=n;i++)
			npow = npow*2;
		
		p = new int[n][npow];
		g = new int[n][npow];
	    System.out.println("Enter the adjacency matrix");
	    
	    for(int i=0;i<n;i++)
	    {
	    	for(int j=0;j<n;j++)
	    	{
	    		adj[i][j] = in.nextInt();
	    	}
	    }
	    
	    in.close();
	    for(int i=0;i<n;i++)
	    {
	    	for(int j=0;j<npow;j++)
	    	{
	    		g[i][j] = -1;
	    	}
	    }
	    
	    for(int i=0;i<n;i++)
	    {
	    	g[i][0]=adj[i][0];
	    }
	    
	    g[0][npow-2] = compute(0,npow-2);
		System.out.println(g[0][npow-2]);
		System.out.println("Path is: ");
		System.out.print("0");
		getpath(0, npow-2);
		System.out.print("-0");
			
	}
	
	static void getpath(int i,int S)
	{
		
		if(S==0)
			return ;
		System.out.print("-"+p[i][S]);
		
		getpath(p[i][S], S-(1<<p[i][S]));
		
	}
	
	static int compute(int i,int S)
	{
		
		if(g[i][S]!=-1)
			return g[i][S];
		int result = Integer.MAX_VALUE;
		for(int j=0;j<n;j++)
		{
			int mask=1<<j;
			int k = (npow-1) - mask;
			int masked = S&k;
			
			if(masked!=S)
			{
				g[j][masked]= compute(j,masked);
				int temp = adj[j][i]+g[j][masked];
				if(temp<result)
				{
					result = temp;
				    p[i][S] = j;	
				    
				}
			}
			
		}
		
		
		
		g[i][S] = result;
		return g[i][S];
		
	}
	
}