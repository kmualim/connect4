package assignment4Game;

import java.io.*;

public class Game {
	
	public static int play(InputStreamReader input){
		BufferedReader keyboard = new BufferedReader(input);
		Configuration c = new Configuration();
		int columnPlayed = 3; int player;
		
		// first move for player 1 (played by computer) : in the middle of the grid
		c.addDisk(firstMovePlayer1(), 1);
		int nbTurn = 1;
		
		while (nbTurn < 42){ // maximum of turns allowed by the size of the grid
			player = nbTurn %2 + 1;
			if (player == 2){
				columnPlayed = getNextMove(keyboard, c, 2);
			}
			if (player == 1){
				columnPlayed = movePlayer1(columnPlayed, c);
			}
			System.out.println(columnPlayed);
			c.addDisk(columnPlayed, player);
			if (c.isWinning(columnPlayed, player)){
				c.print();
				System.out.println("Congrats to player " + player + " !");
				return(player);
			}
			nbTurn++;
		}
		return -1;
	}
	
	public static int getNextMove(BufferedReader keyboard, Configuration c, int player){
		// how to deal with IO exception
		int column = 0;
		System.out.println("Player " + player + ": Please input a number to place your disk");
		try {
			column = Integer.parseInt(keyboard.readLine());
			if (c.available[column] == 6) {
				System.out.println("Column" + column + " is full! Pick another!");
				getNextMove(keyboard, c, player);
			}
			else { 
				System.out.println("Disk added to Column " + column);
				c.addDisk(column, player);
				c.print();
			}
		}
		catch(Exception e) { 
			System.out.println("Input a valid column, please!");
			getNextMove(keyboard, c, player);
		}
		return column;
		//if ask for column with no space, keep asking for valid one 
	}
	
	public static int firstMovePlayer1 (){
		return 3;
	}
	
	public static int movePlayer1 (int columnPlayed2, Configuration c){
		// IF PLAYER CAN WIN NEXT ROUND
		int winningColumn = c.canWinNextRound(1);
		if (winningColumn >= 0) {
			return winningColumn;
		}
		// IF PLAYER CAN WIN NEXT TWO ROUNDS
		winningColumn = c.canWinTwoTurns(1); 
		if (winningColumn >= 0) {
			return winningColumn;
		}
		//
		int last = columnPlayed2;
		for ( int i =0; i <= 6; i++) {
			if ((last-i)>=0&&(last-i)<=6) {
				if(c.available[last-i]<6) { 
				return last-i;
				}
			}
			if ((last+i)>=0&&(last+i)<=6) {
				if(c.available[last+i]<6) { 
				return last+i; 
				}
			}	
		}
		return -1;
	}
}
