package ailab;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Search 
{

	private static Scanner in;

	public static void main(String[] args)
	{
		
		int graph[][],v,e,x,y;
        graph = new int[100][100];
        for(int i=0;i<100;i++)
        {
        	for(int j=0;j<100;j++)
        	{
        		graph[i][j] = 0;
        	}
        }
    
        System.out.println("Entetr the number of vertices and edges");
        in = new Scanner(new InputStreamReader(System.in));
        v = in.nextInt();
        e = in.nextInt();
        
        for(int i=0;i<e;i++)
        {
        	x = in.nextInt();
        	y = in.nextInt();
        	
        	graph[x][y] = 1;
        	graph[y][x] = 1;
        }
        
        System.out.println("Enter the start vertex");
        x = in.nextInt();
        
        bfs sb = new bfs(graph, x, v, e);
        sb.traverse_graph();
	}


}


class bfs
{
	int adj[][],visit[],start,v,e;
	
	bfs(int a[][],int start,int v,int e)
	{
		adj = new int[100][100];
		adj = a;
		this.start = start;
		this.v = v;
		this.e = e;
		visit = new int[100];
        for(int i=0;i<v;i++)
        {
        	visit[i]=0;
        }
        visit[start] = 1;
	}
	
	void traverse_graph()
	{
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		
		while(!q.isEmpty())
		{
			int x = q.poll();
			System.out.println(x);
			for(int i=0;i<v;i++)
			{
				if(adj[x][i]==1 && visit[i]==0)
				{
			      q.add(i);
			      visit[i]=1;
				}
			}
		}
		
	}
}

class dfs
{
int adj[][],visit[],start,v,e;
	
	dfs(int a[][],int start,int v,int e)
	{
		adj = new int[100][100];
		adj = a;
		this.start = start;
		this.v = v;
		this.e = e;
		visit = new int[100];
        for(int i=0;i<v;i++)
        {
        	visit[i]=0;
        }
        visit[start] = 1;
	}
	
	void traverse_graph(int x)
	{
	    visit[x] = 1;
		for(int i=0;i<v;i++)
		{
			if(adj[x][i]==1 && visit[i]==0)
			{
				traverse_graph(i);
			}
		}
		
		System.out.println(x);
		return;
	}
}

class gnt
{
	void gnt()
	{
		
	}
}
