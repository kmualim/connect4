package assignment4Game;

import java.io.*;

public class Tester {

	public static void main(String[] args) {
		
		System.out.println("This tester covers: individual cases for isWinning, and ensures canWinNextRound");
		System.out.println("gives priority to shorter columns. Need to expand canWinTwoTurns");
		// TEST ADD DISK
		Configuration c = new Configuration();
		c.addDisk(0, 2);
		c.addDisk(3, 1);
		c.addDisk(1, 1);
		c.addDisk(2, 1);
		c.addDisk(3, 1);
		c.addDisk(3, 2);
		c.addDisk(3, 1);
		boolean correct = (c.board[0][0] == 2) && (c.board[1][0] == 1) && (c.board[2][0] == 1)
				&& (c.board[3][0] == 1) &&(c.board[3][1] == 1)&& (c.board[3][3] == 1)&& (c.board[3][2] == 2);
		for (int i = 0; i < 7; i++){
			correct = correct && c.available[i] < 6;
		}
		if (correct && c.spaceLeft){
			System.out.println("addDisk seems to work, test it further though.");
		}
		else{
			System.out.println("addDisk does not work.");
		}
		
		// TEST IS WINNING
		boolean correctColumn;
		boolean fDiagCorrect;
		boolean bDiagCorrect;
		//row testing
		Configuration c2 = new Configuration();
		c2.board[0][0] = 2; c2.board[1][0] = 1; c2.board[2][0] = 1; c2.board[3][0] = 1;
		c2.board[3][1] = 1; c2.board[3][3] = 1; c2.board[3][2] = 2;
		c2.available[0] = 1; c2.available[1] = 1; c2.available[2] = 1; c2.available[3] = 4;
		correct = ! c2.isWinning(2, 1);

		c2.board[4][0] = 1; c2.available[4] = 1;
		correct = correct && c2.isWinning(4, 1);
		//column testing
		Configuration c3 = new Configuration();
		c3.addDisk(4,1);
		c3.addDisk(4,1);
		c3.addDisk(4,1);
		c3.addDisk(4,1);
		correctColumn = c3.isWinning(4,1) && c3.available[4] == 4;

		//fdiag
		Configuration c4 = new Configuration();
		c4.addDisk(0,2);
		c4.addDisk(0,1);
		c4.addDisk(0,2);
		c4.addDisk(0,1);
		c4.addDisk(1,2); c4.addDisk(1,2);
		c4.addDisk(1,1); c4.addDisk(1,2);
		c4.addDisk(2,2);
		c4.addDisk(2,1);
		c4.addDisk(3,1);
		bDiagCorrect = c4.isWinning(3,1);
		//bdiag
		Configuration c5 = new Configuration();
		c5.addDisk(0,1);
		c5.addDisk(1,2);
		c5.addDisk(1,1);
		c5.addDisk(2,1);
		c5.addDisk(2,2); c5.addDisk(2,1);
		c5.addDisk(3,2); c5.addDisk(3,1);
		c5.addDisk(3,2);
		c5.addDisk(3,1);
		fDiagCorrect = c5.isWinning(3,1);

		boolean[] cases = {correct, correctColumn, bDiagCorrect, fDiagCorrect};
		String[] caseNames = {"Row", "Column", "Backward Diagonal", "Forward Diagonal"};
		for (int i = 0; i < 4; i++){
			if (cases[i])
				System.out.println(caseNames[i] + " case works, consider expanding");
		}
		System.out.println("If you aren't told which cases work, it's none of them. Sorry.");
		
		
		// TEST CAN WIN NEXT ROUND
		
		c2.board[4][0] = 0; c2.available[4] = 0;
		correct = c2.canWinNextRound(1) == 4;
		c2.board[4][0] = 2; c2.available[4] = 1;
		correct = correct && c2.canWinNextRound(1) == -1;

		Configuration c6 = new Configuration();
		c6.addDisk(0,1); c6.addDisk(0,2);
		c6.addDisk(0,2); c6.addDisk(0,2);
		c6.addDisk(6,2); c6.addDisk(6,2);
		c6.addDisk(6,2);
		boolean priority = c6.canWinNextRound(2) == 6;
		if (correct && priority){
			System.out.println("canWinNextRound seems to work, test it further though.");
		} else if (correct){
			System.out.println("Make sure you are prioritising(?) shorter columns.");
		}
		else{
			System.out.println("canWinNextRound does not work.");
		}
		
		// TEST CAN WIN TWO TURNS
		                                              
		c2.board[0][3] = 1; c2.board[0][2] = 1; c2.board[0][1] = 2; c2.available[0] = 4;
		c2.board[2][3] = 1; c2.board[2][2] = 2; c2.board[2][1] = 1; c2.available[2] = 4;
		c2.print();
		correct = c2.canWinTwoTurns(1) == 1;
		c2.print();
		System.out.println(correct);
		correct = correct && c2.canWinTwoTurns(2) == -1;
		c2.print();
		if (correct){
			System.out.println("canWinInTwoTurns seems to work, test it further though.");
		}
		else{
			System.out.println("canWinInTwoTurns does not work.");
		}
		
		// TESTING GET NEXT MOVE
		
		// you have to uncomment what comes next when you test this method
		// you probably want to comment it again once you are done, otherwise you will be asked each time
		
		c2.board[0][4] = 1; c2.board[0][5] = 2; c2.available[0] = 6;
		c2.board[2][4] = 1; c2.board[2][5] = 2; c2.available[2] = 6;
		/*int result = -1;
		try{
			InputStreamReader input = new InputStreamReader(System.in);
			BufferedReader keyboard = new BufferedReader(input);
			result = Game.getNextMove(keyboard, c2, 1);
			keyboard.close();
		}
		catch(Throwable e){
			System.out.println("something somewhere went horribly wrong");
		}
		
		System.out.println("does it return what you requested ? " + result);*/
		
		// TESTING MOVE PLAYER 1
		
		c2.board[1][1] = 2; c2.available[1]= 2;
		correct = Game.movePlayer1(2, c2) == 1;
		
		c2.board[1][1] = 0; c2.available[1]= 1;
		correct = correct && Game.movePlayer1(2, c2) == 1;
		
		c2.board[0][3] = 2;
		correct = correct && Game.movePlayer1(2, c2) == 1;
		correct = correct && Game.movePlayer1(0, c2) == 1;
		
		c2.board[3][4] = 2; c2.board[3][5] = 2; c2.available[3]= 6;
		correct = correct && Game.movePlayer1(3, c2) == 4;
		
		if (correct){
			System.out.println("movePlayer1 seems to work, you can now uncomment the last two lines of the tester and play against your AI !");
		}
		else{
			System.out.println("movePlayer1 does not work.");
		}
		
		// When you want to play the game, uncomment the two lines down this comment
		/*InputStreamReader input2 = new InputStreamReader(System.in);
		System.out.println(Game.play(input2));*/
		
	}

}
