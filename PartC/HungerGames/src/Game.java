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
	
	/**
	 * Initializes the game by setting the round to zero.
	 */
	Game(){
		round = 0;
	}
	
	/**
	 * Initializes the game by setting the round.
	 * @param round the round of the game.
	 */
	Game(int round){
		this.round = round;
	}
	
	/**
	 * The setRound() method sets the round of the game.
	 * @param round the round to be set.
	 */
	public void setRound(int round) {
		this.round = round;
	}
	
	/**
	 * The getRound() method returns the round of the game.
	 * @return the round of the game.
	 */
	public int getRound() {
		return round;
	}

	public static void main(String[] args) {
		
		Game game = new Game(0);
		
		// The board of the game.
		Board board = new Board(20, 20, 6, 10, 8);
		board.createBoard();
		
		// Coordinates of players.
		int x = board.getN() / 2;
		int y = board.getM() / 2;
		
		MinMaxPlayer p1 = new MinMaxPlayer(1, "Player1", board, 15, x, y);
		
		Player p2 = new Player(2, "Player2", board, 15, x, y);
		
		// The variable resizeTime checks if the board must be resized.
		int resizeTime = 0;
		
		// The variable p1Kill is true if the player p1 kills player p2.
		boolean p1Kill = false;
		
		// The variable p2Kill is true if the player p2 kills player p1.
		boolean p2Kill = false;
		
		// The variable p1NegativeScore is true when p1's score is negative.
		boolean p1NegativeScore = false;
		
		// The variable p2NegativeScore is true when p2's score is negative.
		boolean p2NegativeScore = false;
		
		System.out.println("************** HUNGER GAMES **************");
		System.out.println("*** MAY THE ODDS BE EVER IN YOUR FAVOR ***\n");
		
		System.out.println("Player 1 position: (x, y) = (" + p1.getX() + ", " + p1.getY() + ").");
		System.out.println("Player 2 position: (x, y) = (" + p2.getX() + ", " + p2.getY() + ").");
		System.out.println("Let the games begin!\n");
		
		while((board.getN() > 4) && (board.getM() > 4)) {
			game.round++;
			System.out.println("Round: " + game.round);
			
			// Turn of player 1.
			int[] p1Status = p1.getNextMove(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			p1.statistics();
			
			if(MinMaxPlayer.kill(p1, p2, 2)) {
				System.out.println("Player " + p1.getId() + " kills player " + p2.getId() + ".");
				p1Kill = true;
				break;				// End of game.
			}
			
			
			// Turn of player 2.
			int[] p2Status = p2.move();
			
			if(MinMaxPlayer.kill(p2, p1, 2)) {
				System.out.println("Player " + p2.getId() + " kills player " + p1.getId() + ".");
				p2Kill = true;
				break;				// End of game.
			}
			
			resizeTime++;
			
			System.out.println();
				
						
			// Print the board and the status of the game.
			System.out.println("Board: ");
			String[][] boardRepresentation = board.getStringRepresentation();
			
			// Add players' positions on the board
			boardRepresentation[board.y2i(p1.getY())][board.x2j(p1.getX())] = " P1";
			boardRepresentation[board.y2i(p2.getY())][board.x2j(p2.getX())] = " P2";	
			
			if(p1.getX() == p2.getX() && p1.getY() == p2.getY())
				boardRepresentation[board.y2i(p1.getY())][board.x2j(p1.getX())] = "P12";
			
			
			for(int i = 0; i < board.getN(); i++) {
				for(int j = 0; j < board.getM(); j++) {
					System.out.print(boardRepresentation[i][j] + "   ");
				}
				System.out.println();
			}
			
			System.out.println();
			
			System.out.println("Player 1 moved to: (x, y) = (" + p1Status[0] + ", " + p1Status[1] + ").");
			// Player 1 status
			System.out.println("\t Score: " + p1.getScore());
			System.out.println("\t Weapons: bow: " + ((p1.getBow() == null) ? 0 : 1) +
											"\t sword: " + ((p1.getSword() == null) ? 0 : 1) +
											"\t pistol: " + ((p1.getPistol() == null) ? 0 : 1));
			
			System.out.println();
			
			System.out.println("Player 2 moved to: (x, y) = (" + p2Status[0] + ", " + p2Status[1] + ").");
			// Player 2 status
			System.out.println("\t Score: " + p2.getScore());
			System.out.println("\t Weapons: bow: " + ((p2.getBow() == null) ? 0 : 1) +
											"\t sword: " + ((p2.getSword() == null) ? 0 : 1) +
											"\t pistol: " + ((p2.getPistol() == null) ? 0 : 1));
			
			System.out.println();
			
			
			// Check if the board must be resized.
			if(resizeTime >= 3) {
				int oldN = board.getN();
				int oldM = board.getM();
				board.resizeBoard(p1, p2);
				
				// If the board has been resized set resizeTime to 0
				// else check for resize in the next round.
				if((board.getN() != oldN) && (board.getM() != oldM)) {
					resizeTime = 0;	
					System.out.println("The board has been resized.\n");
				}
			}
			
			// Check if players' score is negative.
			if(p1.getScore() < 0) {
				p1NegativeScore = true;
				System.out.println("Player " + p1.getId() + " has negative score. Player " + p1.getId() + " died.\n");
				break;
			}
			if(p2.getScore() < 0) {
				p2NegativeScore = true;
				System.out.println("Player " + p2.getId() + " has negative score. Player " + p2.getId() + " died.\n");
				break;
			}
			
		}		// End of the game.
		
		System.out.println("Game over!!");
		System.out.println("Board:");
		String[][] boardRepresentation= board.getStringRepresentation();
		for(int i = 0; i < board.getN(); i++) {
			for(int j = 0; j < board.getM(); j++) {
				System.out.print(boardRepresentation[i][j] + "   ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("Rounds: " + game.getRound());
		System.out.println("Player 1: Score: " + p1.getScore());
		System.out.println("Player 2: Score: " + p2.getScore());
		
		// Check for winner.
		if(p1Kill)
			System.out.println("Player 1 won.");
		else if(p2Kill)
			System.out.println("Player 2 won.");
		else if(p1NegativeScore) 
			System.out.println("Player 2 won.");
		else if(p2NegativeScore) 
			System.out.println("Player 1 won.");
		else {
			if(p1.getScore() > p2.getScore()) 
				System.out.println("Player 1 won.");
			else if(p2.getScore() > p1.getScore()) 
				System.out.println("Player 2 won.");
			else 
				System.out.println("It's a draw.");
		}
		
	}
	

}
