package assignment4Game;

public class Configuration {
	
	public int[][] board;
	public int[] available;
	boolean spaceLeft;
	
	public Configuration(){
		board = new int[7][6];
		//board is the grid 
		available = new int[7];
		//obtains the first available row in the i-th column
		spaceLeft = true;
	}
	
	public void print(){
		System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
		System.out.println("+---+---+---+---+---+---+---+");
		for (int i = 0; i < 6; i++){
			System.out.print("|");
			for (int j = 0; j < 7; j++){
				if (board[j][5-i] == 0){
					System.out.print("   |");
				}
				else{
					System.out.print(" "+ board[j][5-i]+" |");
				}
			}
			System.out.println();
		}
	}
	
	public void addDisk (int index, int player){
		
		if (index >= 0 && index<7) {
			board[index][available[index]] = player; 
			available[index] += 1;
		}
		if (available[index] == 6) { 
			spaceLeft = false; 	
		}
	}
	
	public boolean isWinning (int lastColumnPlayed, int player){
		
		// you need to check horizontal, vertical and diagonal 

		int count = 0; 
		int y = 0; 
		int boardHeight = 6; 
		int boardWidth = 7; 
		
		if (available[lastColumnPlayed] > 0) { 
			y = available[lastColumnPlayed]-1;
		}
		int row = y;

		//HORIZONTAL
		int j = lastColumnPlayed; 
		while (j < boardWidth && board[j][row] == player) { 
			count ++; 
			j++;
		}
		j = lastColumnPlayed -1;
		while ( j >= 0 && board[j][row] == player) { 
			count ++; 
			j--;
		}
		if (count >= 4) {
			return true; 
		}
		
		//VERTICAL
		
		count = 0;
		j = lastColumnPlayed;
		while ( row < boardHeight && board[j][row] == player) { 
			count ++; 
			row ++; 
		}
		row = y-1;
		
		while (row >= 0 && board[j][row] == player) { 
			count ++; 
			row --; 
		}

		if (count >= 4) { 
			return true; 
		}
		
		//SECONDARY DIAGONAL 
		
		count = 0; 
		j = lastColumnPlayed; 
		row = y; 
		while ( j < boardWidth && row < boardHeight && board[j][row] == player ) {
			count++;
			j++; 
			row++;
		}
		j = lastColumnPlayed -1 ;
		row = y -1; 
		
		while ( j >= 0 && row >= 0 && board[j][row] == player ) { 
			count++;
			j--; 
			row --;
		}
		if (count >= 4) { 
			return true; 
		}
		
		// LEADING DIAGONAL
		
		count = 0;
		j = lastColumnPlayed; 
		row = y; 
		
		while ( j < boardWidth && row >= 0 && board[j][row] == player ) {
			count ++; 
			j++; 
			row--;
		}
		
		j = lastColumnPlayed -1 ;
		row = y +1; 
		while ( j >= 0 && row < boardHeight && board[j][row] == player ) { 
			count ++; 
			j--; 
			row ++;
		}
		if (count >= 4) { 
			return true; 
		}
		
		return false; 
	}
	
	public int canWinNextRound (int player){
		
		// implement is winning on different columns, save i and return i 
		for ( int i = 0; i < 7; i++) {
			if(available[i]<6) {
				addDisk(i,player);
					if (isWinning(i,player)) { 
						available[i] -=1;
						board[i][available[i]] = 0;
						return i;
					}
					else {
						available[i] -=1;
						board[i][available[i]] = 0;	
					}	
				} 
		}
		return -1;
	}
	
	public int canWinTwoTurns (int player){
		
		// other player does not win in between
		int otherPlayer;
		if (player == 1) { 
			otherPlayer = 2; 
		}
		else { 
			otherPlayer = 1;
		}
		// other player does not win in between
		   for(int i=0; i<7; i++){
		         if(this.available[i]<6){
		           addDisk(i,player);
		           boolean x =true;
		           for(int j=0; j<7; j++){
		             if (this.available[j]<6){
		               addDisk(j, otherPlayer);
		               if(canWinNextRound(player)==-1) {
		            	   x=false;
		               }
		               available[j]-=1;
		               board[j][available[j]]=0;
		             }
		           }
		           available[i]-=1;
		           board[i][available[i]]=0;
		           if (x == true) {
		        	   return i;
		           }
		         }
		      }
		      return -1; 
		  
		  
		  }
}
