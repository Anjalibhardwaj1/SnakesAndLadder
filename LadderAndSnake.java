/**
* Anjali Bhardwaj 40170314
* COMP249
* Assignment # 1
* Due Date February 8
*/

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
/**
* The LadderAndSnake class keeps track of each player's game data.
* It keeps track of player board data, player, turns, player positions, dice values and more.
*
* @author Anjali Bhardwaj
* @see PlayLadderAndSnake
*
*/

public class LadderAndSnake {
	// Variables
	
	/**
	* Stores the total number of users.
	*/
	public static int numberUsers;
	/**
	* Keeps track of user attempts for user number input.
	*/
	public static int attempts = 1;
	/**
	* Stores user symbol.
	*/
	public static char userSymbol;

	// Board variables
	private boolean snake;
	private boolean ladder;
	private int firstIndex;
	private int secondIndex;
	private static int[][] snakePositions = {
			{ 16, 6 }, { 79, 19 }, { 48, 30 }, { 95, 24 }, { 64, 60 }, { 97, 76 }, { 98, 78 } };

	private static int[][] ladderPositions = { { 1, 38 }, { 4, 14 }, { 9, 31 }, { 21, 42 }, { 28, 84 }, { 36, 44 },
			{ 51, 67 }, { 71, 91 }, { 80, 100 } };

	private int position;

	// Player variables
	private int playerNumber;
	private char symbol;
	private int turn;
	private int turnDiceValue;
	private int symbolReference;

	// Dice variables
	private int dice;

	// constructor
	LadderAndSnake(int playerNumber) { 
		this.playerNumber = playerNumber;
		this.symbol = '-';
		this.turn = 0;
		this.turnDiceValue = 0;
		this.dice = 0;
		this.position = 0;
		this.snake = false;
		this.ladder = false;
		this.firstIndex = 0;
		this.secondIndex = 0;
		this.symbolReference = 0;
	}

	// Board getters
	/**
	*  Get first index value of a position on the numbered board. 
	*  @return the first index value of a position on the numbered board. 
	*/
	public int getFirstIndex() {
		return firstIndex;
	}
	
	/**
	*  Get second index value of a position on the numbered board. 
	*  @return the second index value of a position on the numbered board. 
	*/
	public int getSecondIndex() {
		return secondIndex;
	}
	
	/**
	* Get symbol for a user.
	* @return user symbol.
	*/
	public char getSymbol() {
		return symbol;
	}
	
	/**
	* Get position on numbered board.
	* @return position on numbered board.
	*
	*/
	public int getPosition() {
		return position;
	}
	// Board setters
	
	/**
	* Set first index value from a position on the board.
	* @param indexA
	* 	first index from a position on the numbered board.
	*/
	public void setFirstIndex(int indexA) {
		firstIndex = indexA;
	}
	/**
	* Set second index value from a position on the board.
	* @param indexB
	* 	second index from a position on the numbered board.
	*/
	public void setSecondIndex(int indexB) {
		secondIndex = indexB;
	}
	
	/**
	*  Set user symbol
	* @param s
	* 	set user symbol to symbol given by user. 
	*/
	public void setSymbol(char s) {
		symbol = s;
	}
	/**
	* Set user position
	* @param p
	* 	set user position to position of user.
	*/
	public void setPosition(int p) {
		position = p;
	}
	// User getters
	/**
	* Get user player number.
	* @return user player number.
	*
	*/
	public int getPlayerNumber() {
		return playerNumber;
	}
	/**
	*  Get user symbol reference
	* @return symbol reference for each player
	*
	*/
	public int getSymbolReference() {
		return symbolReference;
	}
	/**
	*Get turn value
	* @return player turn
	*/
	public int getTurn() {
		return turn;
	}
	/**
	* Get dice value associated with turn
	* @return dice vale associated with turn
	*/
	public int getTurnDiceValue() {
		return turnDiceValue;
	}
	// User setters
	
	/**
	* Set symbol reference for each user.
	* @param i
	* 	comes from a counter that counts from 0 to total player number.
	*/
	public void setSymbolReference(int i) {
		symbolReference = 100 + (i + 1);// the symbol reference will start at 101 and since the counter starts at 0 one is added.
	}
	/**
	*  Set user turn
	* @param t
	* 	the user turn value.
	*/
	public void setTurn(int t) {
		turn = t;
	}
	/**
	* Set dice value associated with turn
	* @param v
	* 	value of dice value associated with turn. 
	*/
	public void setTurnDiceValue(int v) {
		turnDiceValue = v;
	}
	// Dice getters
	
	/**
	* Get dice value
	* @return dice value
	*/
	public int getDice() {
		return dice;
	}
	// Dice setters
	
	/**
	* Set dice value
	* @param d
	* 	value of dice.
	*/
	public void setDice(int d) {
		dice = d;
	}
	/**
	* Get user attempts
	* @return user attempts. 
	*/
	// Get user attempts
	public static int getAttempts() {
		return attempts;
	}
	// Methods
	
	/**
	* Fill integer board with numbers 1-100.
	* @param board
	* 	integer 10 x 10 board .
	*/
	public static void fillBoard(int[][] board) {
		int counter = 1;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = counter; //increasing counter
				counter++;
			}
		}
	}
	
	/**
	* Find index of a position on numbered board.
	* @param users
	* 	user objects.
	* @param board
	* 	10 X 10 numbered board.
	* @param i
	* 	value that comes from a counter. 
	*/ 
	public static void findBoardIndex(LadderAndSnake[] users, int[][] board, int i) {
		int indexA = 0;
		int indexB = 0;
		for (int k = 0; k < 10; k++)
			for (int j = 0; j < 10; j++) {
				if (board[k][j] == users[i].getPosition()) { //if user position is the same and the current board position save this index.
					
					indexA = k;
					indexB = j;

					users[i].setFirstIndex(indexA);
					users[i].setSecondIndex(indexB);

					break;
				}
			}
	}
	
	/**
	* Display numbered board with no symbols
	* @param board
	* 	10 X 10 numbered board.
	*/ 
	public static void displayBoard(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}
	
	/**
	* Display game board with symbol references
	* @param users
	* 	user objects.
	* @param board
	* 	10 X 10 numbered board.
	*/ 
	public static void gameBoard(LadderAndSnake[] users, int[][] board) {
		
		fillBoard(board);// Fill number board with numbers
		
		// When the user position is equal the board number for each user, find index and replace with symbol reference
		for (int i = 0; i < users.length; i++) {
			findBoardIndex(users, board, i);
			board[users[i].getFirstIndex()][users[i].getSecondIndex()] = users[i].getSymbolReference();
		}
		
		// Check if the positions are the same for any two users and display a message
		for (int j = 0; j < users.length; j++) {
			for (int k = 0; k < j; k++) {
				if (users[k].getPosition() == users[j].getPosition()) {
					System.out.println(
							"Same position for players " + users[k].getPlayerNumber() + " and " + users[j].getPlayerNumber());
				}
			}
		}
	}
	
	/**
	* Create board that displays user symbols
	* @param users
	* 	user objects.
	* @param board
	* 	10 X 10 numbered board.
	* @param symbolBoard
	* 	10 x 10 String board.
	*/

	public static void symboledGameBoard(LadderAndSnake[] users, int[][] board, String[][] symbolBoard) {
		
		for (int k = 0; k < board.length; k++) {
			for (int j = 0; j < board[k].length; j++) {

				// Change symbol reference to user symbols 
				for (int i = 0; i < users.length; i++) {
					if (board[k][j] == 101 && users[i].getPlayerNumber() == 1) {
						symbolBoard[k][j] = Character.toString(users[i].getSymbol());

					} else if (board[k][j] == 102 && users[i].getPlayerNumber() == 2) {
						symbolBoard[k][j] = Character.toString(users[i].getSymbol());

					} else if (board[k][j] == 103 && users[i].getPlayerNumber() == 3) {
						symbolBoard[k][j] = Character.toString(users[i].getSymbol());

					} else if (board[k][j] == 104 && users[i].getPlayerNumber() == 4) {
						symbolBoard[k][j] = Character.toString(users[i].getSymbol());

					}
				}

				if (board[k][j] != 101 && board[k][j] != 102 && board[k][j] != 103 && board[k][j] != 104) {
					symbolBoard[k][j] = Integer.toString(board[k][j]);
				}
				System.out.print(symbolBoard[k][j] + "\t");

			}
			System.out.println("\n");
		}

	}
	
	/**
	* Get valid number of players that is within range and attempts
	* @param key
	* 	user scanner input.
	* @return the total amount of players. 
	*/
	public static int getPlayers(Scanner key) {

		// If attempts is not 4 check if attempts is valid 
		if (attempts != 4) {

			while (attempts < 4) {
				// Prompt user for player number
				System.out.println("\n" + "Please enter the number of players (2-4): ");
				numberUsers = key.nextInt();

				//Error prompt if number is not within range and within attempts
				while ((numberUsers < 2 || numberUsers > 4) && attempts != 4) {
					System.out.println("\n" + "Please enter a vaild number of players (2-4): ");
					numberUsers = key.nextInt();
					attempts++;
				}
				
				//If number and attempts are valid exit
				if (!(numberUsers < 2 || numberUsers > 4) && attempts != 4) {
					break;
				}
			}
		}
		return numberUsers;
	}
	
	/**
	*  Get valid user symbol for each player
	* @param users
	* 	user objects.
	* @param i
	* 	comes from a counter. 
	* @param key
	* 	user scanner input.
	* @return the user symbol.
	*/
	public static char getUserSymbol(LadderAndSnake[] users, int i, Scanner key) {
		
		String symbolOptions = "#*@%";
		
		// Prompt each user for symbol
		System.out.println("Player " + (i + 1) + ", enter a symbol (#, *, @, %): ");
		userSymbol = key.next().charAt(0);

		// If string contains user symbol
		if (symbolOptions.contains(Character.toString(userSymbol))) {

			// Check if symbol is already taken
			for (int k = 0; k < users.length; k++) {
				
				// While user chooses a symbol that is taken, prompt error 
				while (users[k].getSymbol() == userSymbol) {
					System.out.println("Please enter a symbol  that is not taken (#, *, @, %):");
					userSymbol = key.next().charAt(0);
					
					// While user chooses a symbol that is not valid, give error prompt
					while (!(symbolOptions.contains(Character.toString(userSymbol)))) {
						System.out.println("Please enter a valid symbol (#, *, @, %):");
						userSymbol = key.next().charAt(0);

					}
				}
				break;
			}
		} else {
			// While user chooses a symbol that is not valid, give error prompt
			while (!(symbolOptions.contains(Character.toString(userSymbol)))) {
				System.out.println("Please enter a valid symbol (#, *, @, %):");
				userSymbol = key.next().charAt(0);
				
				// While user chooses a symbol that is not valid, give error prompt
				for (int k = 0; k < users.length; k++) {
					while (users[k].getSymbol() == userSymbol) {
						System.out.println("Please enter a symbol  that is not taken (#, *, @, %):");
						userSymbol = key.next().charAt(0);
					}
				}
				
			}
		}
		return userSymbol;
	}
	
	/**
	*  Check if there is a snake head in player position
	* @param users
	* 	user objects.
	* @param i
	* 	comes from a counter. 
	* @return true if there is a snake head.
	*/
	public boolean isSnakeHead(LadderAndSnake[] users, int i) {
		for (int k = 0; k < 7; k++) {
			if (snakePositions[k][0] == users[i].getPosition()) {
				snake = true;
				break;
			} else {
				snake = false;
			}
		}
		return snake;
	}
	
	/**
	*  Check if there is a ladder end in player position
	* @param users
	* 	user objects.
	* @param i
	* 	comes from a counter. 
	* @return true if there is a ladder end.
	*/
	public boolean isLadderEnd(LadderAndSnake[] users, int i) {
		for (int k = 0; k < 9; k++) {
			if (ladderPositions[k][0] == users[i].getPosition()) {
				ladder = true;
				break;
			} else {
				ladder = false;
			}
		}
		return ladder;
	}
	
	/**
	* Get ladder top value if ladder end position is known
	* @param users
	* 	user objects.
	* @param i
	* 	comes from a counter. 
	* @return the value of the ladder top when given the ladder end
	*/
	public static int getLadderTop(LadderAndSnake[] users, int i) {
		int ladderTop = 0;
		for (int k = 0; k < 9; k++)
			for (int j = 0; j < 1; j++) {
				if (ladderPositions[k][j] == users[i].getPosition()) {
					ladderTop = ladderPositions[k][j + 1];
					break;
				}
			}
		return ladderTop;
	}

	/**
	*  Get snake tail if snake head position is known
	* @param users
	* 	user objects.
	* @param i
	* 	comes from a counter.
	* @return the value of the snake tail give the snake head. 
	*/
	public static int getSnakeTail(LadderAndSnake[] users, int i) {
		int snakeTail = 0;
		for (int k = 0; k < 7; k++)
			for (int j = 0; j < 1; j++) {
				if (snakePositions[k][j] == users[i].getPosition()) {
					snakeTail = snakePositions[k][j + 1];
					break;
				}
			}
		return snakeTail;
	}
	
	/**
	* Will return a random number from 1-6 and prompt user
	* @param users
	* 	user objects.
	* @param i
	* 	comes from a counter. 
	*/
	public void turnGenerator(LadderAndSnake[] users, int i) {
		this.turnDiceValue = ThreadLocalRandom.current().nextInt(1, 7);
		System.out.println("Player " + getPlayerNumber() + " rolled a " + this.turnDiceValue);
		users[i].setDice(turnDiceValue);
	}
	
	/**
	* Dice flip method 
	* @return a random number from 1-6 
	*/
	public int flipDice() {
		return dice = ThreadLocalRandom.current().nextInt(1, 7);
	}
	
	/**
	* Play method to start game
	* @param users
	* 	user objects.
	* @param board
	* 	10 X 10 numbered board.
	* @param key
	* 	user scanner input.
	* @param i
	* 	comes from a counter. 
	*/
	public static void play(LadderAndSnake[] users,  int[][] board, Scanner key, int i) {
		
		// Prompt users to roll dice
		System.out.println(
				"Player " + users[i].playerNumber + ": (" + users[i].getSymbol() + ") please hit 'r' to roll your dice.");
		String enter = key.next();

		// If user hits enter flip dice, check for snakes and ladders, and store position
		if (enter.toLowerCase().equals("r")) {
			int diceValueRolled = users[i].flipDice(); //Roll Dice
			users[i].setDice(diceValueRolled);// Set dice value 
			System.out.println("\nPlayer " + users[i].playerNumber + ": " + "\nRolled a " + diceValueRolled); // Prompt user

			// Store new position using dice value 
			users[i].setPosition(users[i].getPosition() + diceValueRolled);
			System.out.println("Position: " + users[i].getPosition()); // Prompt user

			// Check for snake head and adjust position if snake head is there
			if (users[i].isSnakeHead(users, i)) {
				users[i].setPosition(getSnakeTail(users, i)); // Set new position
				System.out.println("OH NO! You got a snake head... moving to position " + users[i].getPosition()); // Prompt user
				System.out.println("New position: " + users[i].getPosition()); // Prompt user
			}

			// Check for ladder end and adjust position if ladder head is there
			if (users[i].isLadderEnd(users, i)) {
				users[i].setPosition(getLadderTop(users, i)); // Set new position
				System.out.println("Good Job! You got a ladder end!!... moving to position " + users[i].getPosition());// Prompt user
				System.out.println("New position: " + users[i].getPosition());// Prompt user
			}

			// If position is greater than 100 move player back
			if (users[i].getPosition() > 100) {
				int difference = users[i].getPosition() - 100; // Move back according to the difference between value greater than 100 and 100
				int newPosition = 100 - difference; 
				users[i].setPosition(newPosition); //set position
				System.out.println("Your position is greater than 100! Moving back..."); // Prompt user
				System.out.println("New position: " + users[i].getPosition()); // Prompt user
			}

		}

	}

}
