package ailab;

import java.util.Scanner;

// 'X' is for the computer and '0' is for the player 
public class TicTacToe {

	static String board[];
	static int win_pos[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
	static Scanner in;
	public static void main(String[] args) 
	{
	
	board = new String[9];
    for(int i=0;i<9;i++)
    	board[i]="";
    
    
    in = new Scanner(System.in);
    int first_move=0;
    
    
    
    do
    {
    	System.out.println("Enter 0 if you want to move or 1 if you want computer to move");
        
        first_move = in.nextInt();
        
    	if(first_move!=0 && first_move!=1)
    	System.out.println("Invalid input try again");
    	
    }while(first_move!=0 && first_move!=1);
    
    int ko = 0;
    while(true)
    {
    	if(ko==0)
    	{
    		print();
    		ko++;
    	}
    		
    	
    	//first_move=0==> player's move is the first move
    	if(first_move==0)
    	{
    		int move = take_input();
    		board[move-1] = "0";
    		
    		
    		int result = check_win(board);
    		
    		if(result==0)
    		{
    			print();
    			
    			System.out.println("\nMatch Drawn");
    			break;
    		}
    		
    		else if(result==1)
    		{
    			print();
    			
    			System.out.println("\nCongratulations,You Win!");
    			break;
    		}
    		String put_s[] = new String[9];
    		
    		put_s = copy_it();
    		
    		tile temp = put(1, put_s);
    		board[temp.idx]="X";
    		
    		result = check_win(board);
    		
    		if(result==0)
    		{
    			print();
    			
    			System.out.println("\nMatch Drawn");
    			break;
    		}
    		
    		else if(result==1)
    		{
    			print();
    			
    			System.out.println("\nYou Lose, Game Over!");
    			break;
    		}
    		
    		print();
    	}
    	
    	else
    	{
    		String put_s[] = new String[9];
    		put_s = copy_it();
            tile temp = put(1, put_s);
    		
    		board[temp.idx]="X";
    		
    		int result = check_win(board);
    		
    		if(result==0)
    		{
    			print();
    			
    			System.out.println("\nMatch Drawn");
    			break;
    		}
    		
    		else if(result==1)
    		{
    			print();
    			
    			System.out.println("\nYou Lose, Game Over!");
    			break;
    		}
    		
    		print();
    		
    		int move = take_input();
    		board[move-1] = "0";
    		
    		result = check_win(board);
    		
    		if(result==0)
    		{
    			print();
    			
    			System.out.println("\nMatch Drawn");
    			break;
    		}
    		
    		else if(result==1)
    		{
    			print();
    			
    			System.out.println("\nCongratulations,You Win!");
    			break;
    			
    		}
    		
    	}
    	
    }
        
	}
	//move will be either 0 or 1. specifying whether player has to take a move or computer..as given in move_by
	
	static tile put(int move,String a[])
	{
		tile score = new tile();
		
		tile score_move_player = new tile(); //store minimum score if it is player's move
		tile score_move_comp = new  tile();  // store maximum if it is computer's move
		
		score_move_player.cost = Integer.MAX_VALUE;
		score_move_comp.cost = Integer.MIN_VALUE;
		
		for(int i=0;i<9;i++)
		{
			
			if(a[i].isEmpty())
			{
				//move=0 ==> it's chance of the player
				if(move==0)
				{
					a[i]="0";
					
					int temp = check_win(a);
					
					if(temp==0)
					{
					
						score.cost = 0;
						score.idx = i;
						a[i]="";
						
						if(score.cost<score_move_player.cost)
						{
							score_move_player.cost = score.cost;
							score_move_player.idx = i;
						}
						continue;
					}
					
					else if(temp==1)
					{
						score.cost = -10;
						score.idx = i;
						a[i]="";
						
						if(score.cost<score_move_player.cost)
						{
							score_move_player.cost = score.cost;
							score_move_player.idx = i;
						}
						continue;
					}
					
					score = put(1,a);
					score.idx=i;
					if(score.cost<score_move_player.cost)
					{
						score_move_player.cost = score.cost;
						score_move_player.idx = i;
					}
					a[i]="";
				}
				
				//else move=1 ==> it's the chance of the computer
				else
				{
					a[i]="X";
                    int temp = check_win(a);
					
					if(temp==0)
					{
						
						score.cost = 0;
						score.idx = i;
						a[i]="";
						if(score.cost>score_move_comp.cost)
						{
							score_move_comp.cost = score.cost;
							score_move_comp.idx = i;
						}
						continue;
					}
					
					else if(temp==1)
					{
						
						score.cost = 10;
						score.idx = i;
						a[i]="";
						if(score.cost>score_move_comp.cost)
						{
							score_move_comp.cost = score.cost;
							score_move_comp.idx = i;
						}
						continue;
					}
					
					score = put(0,a);
					//ret maximum
					score.idx = i;
					if(score.cost>score_move_comp.cost)
					{
						score_move_comp.cost = score.cost;
						score_move_comp.idx = i;
					}
					a[i]="";
				}
			}
			
		}
		
		if(move==0)
		{
			return score_move_player;
		}
			
		return score_move_comp;
		
	}
	
	static int take_input()
	{
		int move;
		while(true)
		{
			//print();
    		System.out.println("\nEnter the position, you want to insert");
    		move = in.nextInt();
    		if(!board[move-1].isEmpty())
    		{
    			System.out.println("You cannot move at this place. Select an empty position.");
    			print();
    		}
    		else if(move>9 || move<1)
    		{
    			System.out.println("Position should be between 1 and 9. Try again.");
    			print();
    		}
    		else
    		{
    			board[move-1]="0";
    			break;
    		}
    			

		}
		return move;

	}
	
	 //return 1 if someone wins, 0 if tie
	static int check_win(String a[])
	{
		
		
		for(int i=0;i<8;i++)
		{
			if(!a[win_pos[i][0]].isEmpty()&&a[win_pos[i][0]].equals(a[win_pos[i][1]])&& a[win_pos[i][1]].equals(a[win_pos[i][2]]))
			{
				return 1;
			}
		}
		
		int cnt=0;
		for(int i=0;i<9;i++)
			if(!a[i].isEmpty())
				cnt++;
		if(cnt==9)
			return 0;
		
		return -1;
	}
	
	static void print()
	{
		for(int i=0;i<9;i++)
		{
			if(board[i].isEmpty())
				System.out.print(" ");
			else
			System.out.print(board[i]);
			
			if(i!=2 && i!=5 &&i!=8)
			{
				System.out.print(" | ");
			}
			
			if(i==2 || i==5)
			{
				System.out.print("\n----------\n");
			}
		}
	}
	
	static String[] copy_it()
	{
		String a[] = new String[9];
		
		for(int k=0;k<9;k++)
		{
			a[k]= new String();
			a[k] = board[k];
		}
		
		return a;
	}

}

class tile
{
	int cost,idx;
}