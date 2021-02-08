/**
* Anjali Bhardwaj 40170314
* COMP249
* Assignment # 1
* Due Date February 8
*/
import java.util.*;
// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Assignment #1
// Written by: Anjali Bhardwaj 40170314
// This is a simple SnakesAndLadders game! There can be 2 to 4 players and the board is 10 x 10 numbered from 1 -100. Each player must chose a symbol to represent them (#, *, @, %).
// The player order is determined by rolling a dice for each user. The turns are ranked in deceasing order of dice rolls. Once the order is determined the game will start. Each player
// will have to roll a dice in order to determine how far up the board they go. If a player is in a position with a snake head, the player will move down positions. 
// If the player is in a position with a ladder end, the player will move up positions. Once a player reaches the position 100, the game is over. If the player, 
// reaches a position that is greater than 100, the player must move back according to the rule, number greater than 100 - 100. 
// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


/**
* The PlayLadderAndSnake class begins the Snake and Ladder game.
* It initializes board and contains the flow of the game.
*
* @author Anjali Bhardwaj
* @see LadderAndSnake
*
*/
public class PlayLadderAndSnake {

	// Creating boards
	/**
	* Stores number of rows.
	*/
	public static int numRows = 10;
	/**
	* Stores number of columns.
	*/
	public static int numCols = 10; 
	/**
	* Integer board
	*/
	public static int[][] board = new int[numCols][numRows]; 
	/**
	* String board with symbols
	*/
	public static String[][] symbolBoard = new String[numCols][numRows]; 

	/**
	* main method
	* @param args 
	* 	main method argument
	*/
	public static void main(String[] args) {
		// Welcome message
		System.out.println("Welcome to Snakes and Ladder's. This Game was made by Anjali Bhardwaj");

		// Set up int board
		LadderAndSnake.fillBoard(board);
		
		// Get number of players
		Scanner key = new Scanner(System.in);
		int playerTotal = LadderAndSnake.getPlayers(key);

		// If attempts were valid, start game, if not end game.
		if (!(LadderAndSnake.getAttempts() == 4)) {

			// Array to store each user
			LadderAndSnake[] users = new LadderAndSnake[playerTotal];

			// Creating each user
			for (int i = 0; i < users.length; i++) {
				//creating user object with player number
				users[i] = new LadderAndSnake(i + 1);
				//create symbol references
				users[i].setSymbolReference(i);
			}
			
			// Setting symbols 
			for (int s = 0; s < users.length; s++) {
				char symbolChoosen = LadderAndSnake.getUserSymbol(users, s, key);
				users[s].setSymbol(symbolChoosen);
			}

			// Determining order of players 
			// Generating unique number from 1-6 for each player
			boolean tie = false;
			for (int i = 0; i < users.length; i++) {
				tie=false;
				users[i].turnGenerator(users, i);
				// check if values are different
					for (int k = 0; k < i; k++) {
						if (users[k].getDice() == users[i].getDice()) {
							System.out.println("There was a tie... re-rolling...");
							tie = true;
							break;
						}
				}
				//making sure there are no ties
				while (tie) {
					tie = false;
					users[i].turnGenerator(users, i);
					for (int k = 0; k < i; k++) {
						if (users[k].getDice() == users[i].getDice()) {
							System.out.println("There was a tie... re-rolling...");
							tie = true;
							break;
						}
					}
				}
			}
			
			//sort the player order according to dice value and display
				LadderAndSnake b;
				for(int i=0;i<users.length;i++){
		            for(int j=i+1;j<users.length;j++){
		                if(users[j].getDice()<users[i].getDice()){
		                    b=users[j];
		                    users[j]=users[i];
		                    users[i]=b;
		                }
		            }
		        }
				System.out.println("\nTurn order is:");
				
				//Display turn order
				for(int i=users.length-1;i>=0;i--){
					System.out.println("Player "+ users[i].getPlayerNumber() + " ");
					
				}
				
			     //Set turn number for each user
				int count = 0;
				for(int j=0; j < users.length;j++) {
					users[count].setTurn(j);
					count++;
				}
				

			//Start Game!
			System.out.println();
			System.out.println("You are all set!");
			System.out.println("-- GAME BOARD --");
			
			// Display numbered board
			LadderAndSnake.displayBoard(board);
			System.out.println();
			
			// Play, until game is false
			boolean game = true;
			while (game) {
			
			// Play game according to turn order
			for (int i = users.length-1; i>=0;i--) {
				LadderAndSnake.play(users, board, key, i);
				
				 // End game when position is 100 for a user
				 if (users[i].getPosition() == 100) {
					 System.out.println("Congrats Player "+ users[i].getPlayerNumber()+" you win!"); // Prompt user
					 game = false; // Set game to false to leave game 
					 break;
				 }
				};
			
			// Display game board with user symbols
			LadderAndSnake.gameBoard(users, board);
			LadderAndSnake.symboledGameBoard(users, board, symbolBoard);
			
			}


		} else {
			System.out.println("Too many attempts...");// Prompt if attempts are too much
		}

		// Closing message
		System.out.println("\n" + "Program ended... Play again next time!");

	}

}
