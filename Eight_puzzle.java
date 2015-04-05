package ailab;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Eight_puzzle
{
	public static void main(String[] args)
	{
		System.out.println("Enter size of puzzle(m x m)");
		in = new Scanner(new InputStreamReader(System.in));
        Eight_puzzle.n = in.nextInt();
		goal = new State();
		goal.tile[0] = 1;
		
		goal.tile[1] = 2;
		goal.tile[2] = 3;
		goal.tile[3] = 8;
		goal.tile[4] = 0;
		goal.tile[5] = 4;
		goal.tile[6] = 7;
		goal.tile[7] = 6;
		goal.tile[8] = 5;
		goal.h = 0;
		
		start = new State();
		start.g = 0;
		start.f = start.compute_h();
		start.parent = null;
		System.out.println("Enter the Starting State of the puzzle ( input digits from 1-8 with 0 as blank)");
		for(int d=0;d<Eight_puzzle.n*Eight_puzzle.n;d++)
		{
			start.tile[d] = in.nextInt();
			//System.out.println("hello");
		}
		//System.out.println("djksdhk");
		PriorityQueue<State> pq = new PriorityQueue<>(20, new StateComparator());
		HashSet<State> openset = new HashSet<State>();
		HashSet<State> closeset  = new HashSet<State>();
		pq.add(start);
		openset.add(start);
		int hhk=0;
		while(!openset.isEmpty())
		{
			System.out.println("Next State");
			State x = pq.poll();
			openset.remove(x);
			
			if(hhk==25)
				break;
			hhk++;
			int j=0;
			for(int i=0;i<Eight_puzzle.n*Eight_puzzle.n;i++)
			{
				
				System.out.print(x.tile[i]+"\t");
				j++;
				if(j%Eight_puzzle.n==0)
					System.out.println();
			}
			
			if(Eight_puzzle.check_equal(x.tile, goal.tile))
			{
				//do something
				//System.out.println("Got it");

				num_of_sol++;
				System.out.println("Solution "+num_of_sol);
				print_path(x);
				break;
				//continue;
			}
			
			closeset.add(x);
			
			//move for blank
			int i;
			
			for(i=0;i<Eight_puzzle.n*Eight_puzzle.n;i++)
				if(x.tile[i]==0)
					break;
			
			//System.out.println("value of i = " + i);
			int row    = i/Eight_puzzle.n;
			int column = i - Eight_puzzle.n*row;
			//System.out.println("row = " + row + "  column = " + column);
			
			
			if(row>0)
			{
				State up   = new State();
				up.tile = Arrays.copyOf(x.tile, Eight_puzzle.n * Eight_puzzle.n);
				up.tile[i]   = up.tile[i-3];
			    up.tile[i-3] = 0;
			    up.g = x.g+1;
			    up.f=up.compute_h()+up.g;
			    
			    if(Eight_puzzle.check_contain(closeset, up.tile)==0)
				{
			        //System.out.println("Move up.g = " + up.g + "  up.h = " + up.h);
					up.parent = x;
					openset.add(up);
					pq.add(up);
					
				}
			}
			
			if(row<Eight_puzzle.n-1)
			{
				State down = new State();
				down.tile = Arrays.copyOf(x.tile, Eight_puzzle.n * Eight_puzzle.n);
				down.tile[i] = down.tile[i+3];
			    down.tile[i+3] = 0;
			    down.g = x.g+1;
			    down.f=down.compute_h()+down.g;
			    
			    if(Eight_puzzle.check_contain(closeset, down.tile)==0)
				{
			    	//System.out.println("Move down.g = " + down.g + "  down.h = " + down.h);
					down.parent = x;
					openset.add(down);
					pq.add(down);
					
				}
			    
			}

			if(column<Eight_puzzle.n-1)
			{
				State right = new State();
				right.tile = Arrays.copyOf(x.tile, Eight_puzzle.n * Eight_puzzle.n);
				right.tile[i] = right.tile[i+1];
			    right.tile[i+1] = 0;
			    right.g = x.g+1;
			    right.f=right.compute_h()+right.g;
			    
			    if(Eight_puzzle.check_contain(closeset, right.tile)==0)
				{
			    	//System.out.println("Move right.g = " + right.g + "  right.h = " + right.h);
			    	right.parent = x;
					openset.add(right);
					pq.add(right);
					
				}
			}
			
			if(column>0)
			{
				State left = new State();
				left.tile = Arrays.copyOf(x.tile, Eight_puzzle.n * Eight_puzzle.n);
				left.tile[i] = left.tile[i-1];
			    left.tile[i-1] = 0;
			    left.g = x.g+1;
			    left.f=left.compute_h()+left.g;
			    
			    if(Eight_puzzle.check_contain(closeset, left.tile)==0)
				{
			    	//System.out.println("Move left.g = " + left.g + "  left.h = " + left.h);
			    	left.parent = x;
					openset.add(left);
					pq.add(left);
					
				}
			}
			
			
			
		}
		if(num_of_sol==0)
			System.out.println("No Solution Exist!");
	}
	
	static int n,num_of_sol=0;
	static State start,goal;
	private static Scanner in;
	
	static int check_contain(HashSet<State> close,int a[])
	{
		int i=0;
		Iterator<State> it = close.iterator();
		
		while(it.hasNext())
		{
			if(it.next().tile.equals(a))
			{
				i=1;break;
			}
		}
		return i;
	}
	
	static void print_path(State x)
	{
		if(x==null)
			return;
		int j=0;
		for(int i=0;i<Eight_puzzle.n*Eight_puzzle.n;i++)
		{
			
			System.out.print(x.tile[i]+"\t");
			j++;
			if(j%Eight_puzzle.n==0)
				System.out.println();
		}
		System.out.println("\n\n");
		print_path(x.parent);
	}
	
	static boolean check_equal(int a[],int b[])
	{
		boolean r = true;
		
		for(int i=0;i<Eight_puzzle.n*Eight_puzzle.n;i++)
		{
			if(a[i]!=b[i])
			{
				r = false;break;
			}
		}
		return r;
	}
	
}

class StateComparator implements Comparator<State>
{
	
	@Override
	public int compare(State first, State second) {
		
		if(first.f>second.f)
			return 1;
		else if(first.f<second.f)
			return -1;
		else
		return 0;	
		}
}

class State
{
	int f,g,h;
	int[] tile;   //say tile[i] = j    =====> jth tile is at ith position(0 base index) 
	static int n = Eight_puzzle.n * Eight_puzzle.n;
	State parent;
	 public State()
	{
		tile = new int[State.n];
		for(int i=0;i<State.n;i++)
			tile[i] = 9;
		
	}
	int compute_h()
	{
		
		int i,j,row,row1,col,col1;
		this.h = 0;
		
		
		for(i=0;i<State.n;i++)
		{
			for(j=0;j<State.n;j++)
			{
				if(Eight_puzzle.goal.tile[j]==this.tile[i])
				{
					break;
				}
			}
			row  = i/Eight_puzzle.n;
			row1 = j/Eight_puzzle.n;
			col  = i - row*Eight_puzzle.n;
			col1 = j - row1*Eight_puzzle.n;
			row = row - row1;
			col = col - col1;
			if(row<0)
				row=-row;
			if(col<0)
				col = -col;
			this.h = this.h + row + col;
			
		}
		
		return this.h;
	}
}









/*if(row>0 && row<Eight_puzzle.n-1)
{
	//up,down
	if(Eight_puzzle.check_contain(closeset, up.tile)==0)
	{
		up.parent = x;
		openset.add(up);
		pq.add(up);
		
	}
	
	if(Eight_puzzle.check_contain(closeset, down.tile)==0)
	{
		down.parent = x;
		openset.add(down);
		pq.add(down);
		
	}
	
	
	
	if(column>0 && column<Eight_puzzle.n-1)
	{
		//left,right
		if(Eight_puzzle.check_contain(closeset, left.tile)==0)
		{
			left.parent = x;
			openset.add(left);
			pq.add(left);
			
		}
		if(Eight_puzzle.check_contain(closeset, right.tile)==0)
		{
			right.parent = x;
			openset.add(right);
			pq.add(right);
			
		}
	}
	
	if(column==0)
	{
		//right
		
		if(Eight_puzzle.check_contain(closeset, right.tile)==0)
		{
			right.parent = x;
			openset.add(right);
			pq.add(right);
			
		}
	}
	if(column==n-1)
	{
		//left
		if(Eight_puzzle.check_contain(closeset, left.tile)==0)
		{
			left.parent = x;
			openset.add(left);
			pq.add(left);
			
		}
	}
}

if(row==0)
{
	//down
	
	
	if(Eight_puzzle.check_contain(closeset, down.tile)==0)
	{
		down.parent = x;
		openset.add(down);
		pq.add(down);
		
	}
	
	if(column>0 && column<Eight_puzzle.n-1)
	{
		//left,right
		if(Eight_puzzle.check_contain(closeset, left.tile)==0)
		{
			left.parent = x;
			openset.add(left);
			pq.add(left);
			
		}
		if(Eight_puzzle.check_contain(closeset, right.tile)==0)
		{
			right.parent = x;
			openset.add(right);
			pq.add(right);
			
		}
	}
	
	if(column==0)
	{
		//right
		
		if(Eight_puzzle.check_contain(closeset, right.tile)==0)
		{
			right.parent = x;
			openset.add(right);
			pq.add(right);
			
		}
	}
	if(column==n-1)
	{
		//left
		if(Eight_puzzle.check_contain(closeset, left.tile)==0)
		{
			left.parent = x;
			openset.add(left);
			pq.add(left);
			
		}
	}
}

if(row==Eight_puzzle.n-1)
{
	//up
	if(Eight_puzzle.check_contain(closeset, up.tile)==0)
	{
		up.parent = x;
		openset.add(up);
		pq.add(up);
		
	}
	
	if(column>0 && column<Eight_puzzle.n-1)
	{
		//left,right
		if(Eight_puzzle.check_contain(closeset, left.tile)==0)
		{
			left.parent = x;
			openset.add(left);
			pq.add(left);
			
		}
		if(Eight_puzzle.check_contain(closeset, right.tile)==0)
		{
			right.parent = x;
			openset.add(right);
			pq.add(right);
			
		}
	}
	
	if(column==0)
	{
		//right
		
		if(Eight_puzzle.check_contain(closeset, right.tile)==0)
		{
			right.parent = x;
			openset.add(right);
			pq.add(right);
			
		}
	}
	if(column==n-1)
	{
		//left
		if(Eight_puzzle.check_contain(closeset, left.tile)==0)
		{
			left.parent = x;
			openset.add(left);
			pq.add(left);
			
		}
	}
	
}*/


