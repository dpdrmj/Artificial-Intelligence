package ailab;

import java.util.Scanner;
public class IterativeDeepening {

	static int adj[][],n,done,visited[];
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter the number of nodes in tree");
		n = in.nextInt();
		
		System.out.println("Enter the adjacency matrix i.e. 1 if connected ad 0 if not connected");
		
		adj = new int[n][n];
		
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				adj[i][j]=in.nextInt();
			}
		}
		
		int result;
		visited = new int[n];
		System.out.println("Enter the term to be searched");
		result = in.nextInt();
		done=0;
		int depth=0;
		boolean t = false;
		while(/*depth<n && */done<n)
		{
			done = 0;
			for(int i=0;i<n;i++)
				visited[i]=0;
			t = deep(0,result,depth);
			System.out.println("\n Done is " + done);
			if(t==true)
			{
				System.out.println("\n"+result + " Found at depth " + depth);
				break;
			}
			
			System.out.println();
			depth++;
			
		}
		
		if(t==false)
			System.out.println(result+ " Not found");
		in.close();
	}
	
	static boolean deep(int x,int result,int depth)
	{
		if(visited[x]==0)
		{
			visited[x] = 1;
			if(depth==0)
			{
			
				done++;
			
				System.out.print(x + " ");
				if(x==result)
					return true;
				return false;
			}
			
			System.out.print(x+" ");
		
		
			done++;
			
			for(int i=0;i<n;i++)
			{
				if(adj[x][i]==1)
				{
					boolean t = deep(i,result,depth-1);
					if(t==true)
						return true;
				}
			}
		}
		
		
		return false;
		
	}

}
