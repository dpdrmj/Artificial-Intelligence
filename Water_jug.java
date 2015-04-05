package ailab;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
 
 
public class Water_jug 
{
 static int cA;
static int cB;
static int cC;
static int num_of_sol;
private static Scanner in;
    public static void main(String[] args) throws IOException 
    {
    	in = new Scanner(new InputStreamReader(System.in));

    	num_of_sol = 0;
    	System.out.println("Enter the capacity of jug A (A>B)");
    	cA = in.nextInt();
    	System.out.println("Enter the capacity of jug B");
    	cB = in.nextInt();
    	System.out.println("Enter the required amount");
    	cC = in.nextInt();
    	
    	Node root = new Node(cA,cB);
    	root.cNode(0, 0, 0);
    	root.parent = null;
    	root = check(root,0,0);
    	if(num_of_sol==0)
    		System.out.println("No Solution Exist");
    	//System.out.println("root count is " + root.count);
    	//printroot(root);
    }
    
    static void printroot(Node root)
    {
    	//System.out.println("the root: ("+root.curA + " , " + root.curB + ")    depth : " + root.depth);
    	for(int i=0;i<root.count;i++)
    	{
    		if(root.next[i]!=null && root.next[i].tr!=0)
    		{
    			System.out.println("State is (" + root.next[i].curA + " , " + root.next[i].curB + ")     depth : " + root.next[i].depth);
    			root.next[i].tr = 0;
    			printroot(root.next[i]);
    		}
    	}
    }
    static void printpath(Node root)
    {
    	//System.out.println("State is ("+ root.curA + "," + root.curB +")" + "depth is " + root.depth);
    	root.tr = 1;
    	if(root.parent!=null)
    		printpath(root.parent);
    	else
    	   {
    		System.out.println("State is ("+ root.curA + " , " + root.curB + ")     depth : " + root.depth);
    	    printroot(root);
    	   }
    }
    static Node check(Node root,int x,int y)
    {
    	/*System.out.println("Encountered    "+x+"    " + y + " depth: = "+ root.depth);
        for(int i =0;i<cA;i++)
        {
        	for(int j=0;j<cB;j++)
        	{
        		System.out.println("state["+i+"]["+j+"] = "+ root.state[i][j]);
        	}
        }*/
    	if(x==cC || y==cC)
    	{
    		num_of_sol++;
    		System.out.println("Solution "+num_of_sol);
    		printpath(root);
    		return root;
    	}
    	
    	if(x==0 && y==0)
    	{
    		//fillA
    		if(root.state[x+cA][y]!=1)
    		{
    	    	
                //System.out.println("COme here ofr foeifdijf");
    			Node n1 = new Node(cA,cB);
    			n1.copyi(root);

    			root.next[root.count++] = n1;

    			n1.parent = root;

    			n1.cNode(x+cA, y, root.depth);
    			
    			
    			n1 = check(n1,n1.curA,n1.curB);
    		}
    		
    		//fillB
    		if(root.state[x][y+cB]!=1)
    		{
    			Node n1 = new Node(cA,cB);
    			n1.copyi(root);
    			root.next[root.count++] = n1;
    			n1.parent = root;
    			n1.cNode(x, y+cB, root.depth);
    			n1 = check(n1,n1.curA,n1.curB);
    		}
    		
    		
    	}
    	
    	else if(x==0 && y!=0)
    	{
    		
    			//fill A
    			if(root.state[cA][y]!=1)
        		{
        			Node n1 = new Node(cA,cB);
        			n1.copyi(root);
        			root.next[root.count++] = n1;
        			n1.parent = root;
        			n1.cNode(cA, y, root.depth);
        			n1 = check(n1,n1.curA,n1.curB);
        		}
    			
    			if(y<cB)
    			{
    				//fill B
    				if(root.state[x][cB]!=1)
    				{
    					Node n1 = new Node(cA,cB);
    					n1.copyi(root);
    					root.next[root.count++] = n1;
    					n1.parent = root;
    					n1.cNode(x, cB, root.depth);
    					n1 = check(n1,n1.curA,n1.curB);
    				}
    	       }
    			//pour water from B to A
    			if(root.state[y][0]!=1)
        		{
        			Node n1 = new Node(cA,cB);
        			n1.copyi(root);
        			root.next[root.count++] = n1;
        			n1.parent = root;
        			n1.cNode(y, 0, root.depth);
        			n1 = check(n1,n1.curA,n1.curB);
        		}
    			//empty B
    			if(root.state[x][0]!=1)
        		{
        			Node n1 = new Node(cA,cB);
        			n1.copyi(root);
        			root.next[root.count++] = n1;
        			n1.parent = root;
        			n1.cNode(x, 0, root.depth);
        			n1 = check(n1,n1.curA,n1.curB);
        		}
    			
    	}
    	
    	else if(x!=0 && y==0)
    	{
    		
    		if(x<cA)
    		{
    			//fill A
    			if(root.state[cA][y]!=1)
				{
					Node n1 = new Node(cA,cB);
					n1.copyi(root);
					root.next[root.count++] = n1;
					n1.parent = root;
					n1.cNode(cA, y, root.depth);
					n1 = check(n1,n1.curA,n1.curB);
				}
    		}
    			
    			//fill B
    		if(root.state[x][cB]!=1)
    		{
    			Node n1 = new Node(cA,cB);
    			n1.copyi(root);
    			root.next[root.count++] = n1;
    			n1.parent = root;
    			n1.cNode(x, cB, root.depth);
    			n1 = check(n1,n1.curA,n1.curB);
    		}
    		
    		
    			//pour water from A to B
    		if((x>=(cB-y)) && (root.state[x-(cB-y)][cB]!=1))
    		{
    			Node n1 = new Node(cA,cB);
    			n1.copyi(root);
    			root.next[root.count++] = n1;
    			n1.parent = root;
    			n1.cNode(x-(cB-y), cB, root.depth);
    			n1 = check(n1,n1.curA,n1.curB);
    		}
    		
    			//empty A
    		if(root.state[0][y]!=1)
    		{
    			Node n1 = new Node(cA,cB);
    			n1.copyi(root);
    			root.next[root.count++] = n1;
    			n1.parent = root;
    			n1.cNode(0, y, root.depth);
    			n1 = check(n1,n1.curA,n1.curB);
    		}
    		
    	}
    	
    	else
    	{
    		//pour water from A to B
    		if( (x>=(cB-y)) && (root.state[x-(cB-y)][cB]!=1))
    		{
    			Node n1 = new Node(cA,cB);
    			n1.copyi(root);
    			root.next[root.count++] = n1;
    			n1.parent = root;
    			n1.cNode(x-(cB-y), cB, root.depth);
    			n1 = check(n1,n1.curA,n1.curB);
    		}
    		
    		//pour from B to A
    		if((y>=(cA-x)) && root.state[cA][y-(cA-x)]!=1)
    		{
    			Node n1 = new Node(cA,cB);
    			n1.copyi(root);
    			root.next[root.count++] = n1;
    			n1.parent = root;
    			n1.cNode(cA, y-(cA-x), root.depth);
    			n1 = check(n1,n1.curA,n1.curB);
    		}
    		
    		//empty A
    		if(root.state[0][y]!=1)
    		{
    			Node n1 = new Node(cA,cB);
    			n1.copyi(root);
    			root.next[root.count++] = n1;
    			n1.parent = root;
    			n1.cNode(0, y, root.depth);
    			n1 = check(n1,n1.curA,n1.curB);
    		}
    		//empty B
    		if(root.state[x][0]!=1)
    		{
    			Node n1 = new Node(cA,cB);
    			n1.copyi(root);
    			root.next[root.count++] = n1;
    			n1.parent = root;
    			n1.cNode(x, 0, root.depth);
    			n1 = check(n1,n1.curA,n1.curB);
    		}
    		//fill A
    		if(root.state[cA][y]!=1)
    		{
    			Node n1 = new Node(cA,cB);
    			n1.copyi(root);
    			root.next[root.count++] = n1;
    			n1.parent = root;
    			n1.cNode(cA, y, root.depth);
    			n1 = check(n1,n1.curA,n1.curB);
    		}
    		// fill B
    		if(root.state[x][cB]!=1)
    		{
    			Node n1 = new Node(cA,cB);
    			n1.copyi(root);
    			root.next[root.count++] = n1;
    			n1.parent = root;
    			n1.cNode(x, cB, root.depth);
    			n1 = check(n1,n1.curA,n1.curB);
    		}
    	}
    	//System.out.println("root count inside " + root.count);
    	return root;
    }
}


class Node {

    	
    	Node next[];
        int depth;
        int state[][];
        Node parent;
        int count;
        int curA,curB;
        int tr;
        //curA--> water in jug A for this state
 
       Node(int cA,int cB)  //cA--> capacity of A
       {
    	   next = new Node[cA*cB];   // total possible states is cA*cB
    	   state = new int[cA+1][cB+1];
    	   tr=0;
    	   for (int i = 0; i < cA*cB; i++) {
    		   next[i]=null;
		}
    	   for(int i=0;i<cA + 1;i++)
    	   {
    		   for(int j=0;j<cB + 1;j++)
    		   {
    			   state[i][j]=0;
    		   }
    	   }
       }
       void cNode(int x,int y,int depth) //x--> amount of water in jug A, y-->water in jug B
       {
    	   
    	   this.depth = depth+1;    // depth will be increased by one from the parent's depth
    	   curA = x;                 
    	   curB = y;
    	   state[x][y] = 1; 
    	   count = 0;
    	   //System.out.println("Chenge the point ");
    	   for(int i =0;i<Water_jug.cA + 1;i++)
           {
           	for(int j=0;j<Water_jug.cB + 1;j++)
           	{
           		//System.out.println("state["+i+"]["+j+"] = "+ state[i][j]);
           	}
           }
       }
              
       void copyi(Node root)
       {
    	   for(int i=0;i<Water_jug.cA + 1;i++)
    	   {
    		   for(int j=0;j<Water_jug.cB + 1;j++)
    		   {
    			   this.state[i][j]= root.state[i][j];
    		   }
    	   }
       }
 }
