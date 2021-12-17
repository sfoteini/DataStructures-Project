package game;

/**
 * ******** HUNGER GAMES - PART B **********
 * 
 * @author  Fotini Savvidou
 * 			AEM: 9657
 * 			TEL: 6909067533
 * 			EMAIL: sfoteini@ece.auth.gr 
 */


/**
 * Class denoting the game.
 */
public class Game {
	private int round;
	private Board board;
	private Player[] players;
	private int turn;						// the number of first player
	private String gameInfo;				// statistics
	boolean gameOver;
	int resizeTime;							// checks if the board must be resized
	
	/**
	 * Initializes the game by setting the round to zero.
	 */
	Game(){
		round = 0;
		board = null;
		players = null;
		turn = -1;
		gameInfo = "";
		gameOver = false;
		resizeTime = 0;
	}
	
	/**
	 * Initializes the game by setting the round, the board and the players.
	 * @param round the round of the game.
	 */
	public Game(int round){
		this.round = round;
		board = null;
		players = null;
		turn = -1;
		gameInfo = "";
		gameOver = false;
		resizeTime = 0;
	}
	
	/**
	 * The setRound() method sets the round of the game.
	 * @param round the round to be set.
	 */
	public void setRound(int round) {
		this.round = round;
	}
	
	/**
	 * The setBoard() method sets the board of the game.
	 * @param b the board to be set.
	 */
	public void setBoard(Board b) {
		board = new Board(b);
	}
	
	/**
	 * The setPlayers() method sets the players of the game.
	 * @param p the players to be set.
	 */
	public void setPlayers(Player p[]) {
		players = new Player[p.length];
		// Copy
		for(int i = 0; i < p.length; i++) {
			players[i] = p[i];
		}
	}
	
	/**
	 * The setTurn() method sets the number of first player.
	 * @param turn the number of first player.
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	/**
	 * The setGameInfo() method sets information about the game.
	 * @param info the text to be set.
	 */
	public void setGameInfo(String info) {
		gameInfo = info;
	}
	
	/**
	 * The setGameOver() method sets a value to the gameOver variable.
	 * @param gameOver the value to be set.
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	/**
	 * The getRound() method returns the round of the game.
	 * @return the round of the game.
	 */
	public int getRound() {
		return round;
	}
	
	/**
	 * The getBoard() method returns the board of the game.
	 * @return the board of the game.
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * The getPlayers() returns the players of the game.
	 * @return the players of the game.
	 */
	public Player[] getPlayers() {
		return players;
	}
	
	/**
	 * The getTurn() method returns the number of the first player.
	 * @return the number of the first player.
	 */
	public int getTurn() {
		return turn;
	}
	
	/**
	 * The getGameInfo() method returns the text stored in gameInfo variable.
	 * @return information about the game.
	 */
	public String getGameInfo() {
		return gameInfo;
	}
	
	/**
	 * The getGameOver() method returns true if the game has ended.
	 * @return the value of the gameOver variable.
	 */
	public boolean getGameOver() {
		return gameOver;
	}
	/**
	 * The setTurns() method picks randomly the first player and updates the turn variable.
	 */
	public void setTurns() {
		// We have two players so turn = 0 or turn = 1.
		turn = (int)(Math.random() * 2);
	}
	
	/**
	 * The start() method starts the game (initializes the board and the players).
	 * @param type1 the type of first player.
	 * @param type2 the type of second player.
	 */
	public void start(String type1, String type2) {
		board = new Board(20, 20, 6, 10, 8);
		board.createBoard();
		
		players = new Player[2];
		
		// Find player's 1 type.
		switch(type1) {
		case "Random Player":
			players[0] = new Player(1, "Player 1", board, 15, board.getN() / 2, board.getM() / 2);
			break;
		case "Heuristic Player":
			players[0] = new HeuristicPlayer(1, "Player 1", board, 15, board.getN() / 2, board.getM() / 2);
			break;
		case "Min Max Player":
			players[0] = new MinMaxPlayer(1, "Player 1", board, 15, board.getN() / 2, board.getM() / 2);
			break;
		}
		
		// Find player's 2 type.
		switch(type2) {
		case "Random Player":
			players[1] = new Player(2, "Player 2", board, 15, board.getN() / 2, board.getM() / 2);
			break;
		case "Heuristic Player":
			players[1] = new HeuristicPlayer(2, "Player 2", board, 15, board.getN() / 2, board.getM() / 2);
			break;
		case "Min Max Player":
			players[1] = new MinMaxPlayer(2, "Player 2", board, 15, board.getN() / 2, board.getM() / 2);
			break;
		}
		
		setTurns();
	}
	
	/**
	 * The run() method simulates a game round.
	 */
	public void run() {
		// Next round
		round++;
		
		resizeTime++;
		
		// Turn of first player
		
		if(players[turn] instanceof HeuristicPlayer) {
			((HeuristicPlayer)(players[turn])).move(players[1 - turn]);
			gameInfo = ((HeuristicPlayer)(players[turn])).statistics() + "\n";
			
			// Check if the player can kill the opponent. 
			if(HeuristicPlayer.kill(players[turn], players[1 - turn], 2)){
				players[1 - turn].setDead(true);			// second player dies
				gameInfo += "Player " + players[turn].getId() + " killed player " + players[1 - turn].getId() + ".\n"; 
				gameOver = true;
				return;										// end of game
			}
		}
		else if(players[turn] instanceof MinMaxPlayer) {
			((MinMaxPlayer)(players[turn])).getNextMove(players[turn].getX(), players[turn].getY(), 
					players[1 - turn].getX(), players[1 - turn].getY());

			gameInfo = ((MinMaxPlayer)(players[turn])).statistics() + "\n";

			// Check if the player can kill the opponent. 
			if(MinMaxPlayer.kill(players[turn], players[1 - turn], 2)) {
				players[1 - turn].setDead(true);			// second player dies
				gameInfo += "Player " + players[turn].getId() + " killed player " + players[1 - turn].getId() + ".\n"; 
				gameOver = true;
				return;										// end of game
			}
		}
		else {
			players[turn].move();
			gameInfo = players[turn].statistics() + "\n";
			
			// Check if the player can kill the opponent. 
			if(Player.kill(players[turn], players[1 - turn], 2)){
				players[1 - turn].setDead(true);			// second player dies
				gameInfo += "Player " + players[turn].getId() + " killed player " + players[1 - turn].getId() + ".\n"; 
				gameOver = true;
				return;										// end of game
			}
		}
		
		// Turn of second player
		
		if(players[1 - turn] instanceof HeuristicPlayer) {
			((HeuristicPlayer)(players[1 - turn])).move(players[turn]);
			gameInfo += ((HeuristicPlayer)(players[1 - turn])).statistics() + "\n";
			
			// Check if the player can kill the opponent. 
			if(HeuristicPlayer.kill(players[1 - turn], players[turn], 2)){
				players[turn].setDead(true);				// first player dies
				gameInfo += "Player " + players[1 - turn].getId() + " killed player " + players[turn].getId() + ".\n"; 
				gameOver = true;
				return;										// end of game
			}
			
		}
		else if(players[1 - turn] instanceof MinMaxPlayer) {
			((MinMaxPlayer)(players[1 - turn])).getNextMove(players[1 - turn].getX(), players[1 - turn].getY(), 
					players[turn].getX(), players[turn].getY());

			gameInfo += ((MinMaxPlayer)(players[1 - turn])).statistics() + "\n";

			// Check if the player can kill the opponent. 
			if(MinMaxPlayer.kill(players[1 - turn], players[turn], 2)) {
				players[turn].setDead(true);				// first player dies
				gameInfo += "Player " + players[1 - turn].getId() + " killed player " + players[turn].getId() + ".\n"; 
				gameOver = true;
				return;										// end of game
			}
		}
		
		else {
			players[1 - turn].move();
			gameInfo += players[1 - turn].statistics() + "\n";
			
			// Check if the player can kill the opponent. 
			if(Player.kill(players[1 - turn], players[turn], 2)){
				players[turn].setDead(true);				// first player dies
				gameInfo += "Player " + players[1 - turn].getId() + " killed player " + players[turn].getId() + ".\n"; 
				gameOver = true;
				return;										// end of game
			}
			
		}
		
		// Check if the board must be resized.
		// For a quick game use (resizeTime >= 3) instead of (round % 3 == 0).
		if(round % 3 == 0) {
			int oldN = board.getN();
			int oldM = board.getM();
			board.resizeBoard(players[0], players[1]);
						
			// If the board has been resized print a message
			if((board.getN() != oldN) && (board.getM() != oldM)) {
				resizeTime = 0;
				gameInfo += "The board has been resized. \n";
			}
			
		}
		
		// Check if players have negative score.
		if(players[0].getScore() < 0 || players[1].getScore() < 0) {
			gameOver = true;
			return;													// end of game
		}
		
		// Check the size of the board
		if(board.getN() <= 4 && board.getM() <= 4) {
			gameOver = true;
			return;													// end of game
		}
	}
	
	/**
	 * The check() method calculates the winner of the game and returns an information message.
	 * @return an information message.
	 */
	public String check() {
		// Check for winner.
		if(players[0].getDead())
			return "Player " + players[0].getId() + " died. Player " + players[1].getId() + " won.";
		else if(players[1].getDead())
			return "Player " + players[1].getId() + " died. Player " + players[0].getId() + " won.";
		else if(players[0].getScore() < 0) 
			return "Player " + players[0].getId() + " has negative score. Player " + players[1].getId() + " won.";
		else if(players[1].getScore() < 0) 
			return "Player " + players[1].getId() + " has negative score. Player " + players[0].getId() + " won.";
		else {
			if(players[0].getScore() > players[1].getScore()) 
				return "Player " + players[0].getId() + " won.";
			else if(players[0].getScore() < players[1].getScore()) 
				return "Player " + players[1].getId() + " won.";
			else 
				return "It's a draw.";
		}
	}
	

}
