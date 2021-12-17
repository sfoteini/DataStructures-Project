/**
 * Class denoting a player of the game.
 */
public class Player {
	int id;
	String name;
	Board board;
	int score;
	int x;
	int y;
	Weapon bow;
	Weapon pistol;
	Weapon sword;
	
	/**
	 * Initializes the player by setting its id, score, x  and y coordinates to
	 * zero and board, bow, pistol, sword to null.
	 */
	Player(){
		id = 0;
		name = "";
		score = 0;
		x = 0;
		y = 0;
		board = null;
		bow = null;
		pistol = null;
		sword = null;
	}
	
	/**
	 * Initializes the player by setting its id, score, name, x  and y coordinates, board.
	 * @param id the id of the player.
	 * @param name the name of the player.
	 * @param board the board of the player.
	 * @param score the score of the player.
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 */
	Player(int id, String name, Board board, int score, int x, int y){
		this.id = id;
		this.name = name;
		this.board = board;
		this.score = score;
		this.x = x;
		this.y = y;
		
		// The player doesn't own any weapon at the beginning of the game.
		this.bow = null;
		this.pistol = null;
		this.sword = null;
	}
	
	/**
	 * The setId() method sets the id of the player.
	 * @param id the id to be set.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * The setName() method sets the name of the player.
	 * @param name the name to be set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * The setBoard() method sets the board of the player.
	 * @param board the board to be set.
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
	/**
	 * The setScore() method sets the score of the player.
	 * @param score the score to be set.
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * The setX() method sets the x coordinate of the player.
	 * @param x the x coordinate to be set.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * The setY() method set the y coordinate of the player.
	 * @param y the y coordinate to be set.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * The setBow() method sets the bow of the player.
	 * @param bow the bow to be set.
	 */
	public void setBow(Weapon bow) {
		this.bow = bow;
	}
	
	/**
	 * The setPistol() method sets the pistol of the player.
	 * @param pistol the pistol to be set.
	 */
	public void setPistol(Weapon pistol) {
		this.pistol = pistol;
	}
	
	/**
	 * The setSword() method sets the sword of the player.
	 * @param sword the sword to be set.
	 */
	public void setSword(Weapon sword) {
		this.sword = sword;
	}
	
	/**
	 * The getId() method returns the id of the player.
	 * @return the id of the player.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * The getName() method returns the name of the player.
	 * @return the name of the player.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * The getBoard() method returns the board of the player.
	 * @return the board of the player.
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * The getScore() method returns the score of the player.
	 * @return the score of the player.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * The getX() method returns the x coordinate of the player.
	 * @return the x coordinate of the player.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * The getY() method returns the y coordinate of the player.
	 * @return the y coordinate of the player.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * The getBow() method returns the bow of the player.
	 * @return the bow of the player.
	 */
	public Weapon getBow() {
		return bow;
	}
	
	/**
	 * The getPistol() method returns the pistol of the player.
	 * @return the pistol of the player.
	 */
	public Weapon getPistol() {
		return pistol;
	}
	
	/**
	 * The getSword() method returns the sword of the player.
	 * @return the sword of the player.
	 */
	public Weapon getSword() {
		return sword;
	}
	
	/**
	 * The getRandomMove() method calculates a random move and returns the coordinates of the new position 
	 * of the player.
	 * @return the coordinates of the new position.
	 */
	public int[] getRandomMove(){
		// Find number of possible moves.
		int possibleMoves = 8;
		boolean isXEdge = x == -board.getN()/2 || x == board.getN()/2;
		boolean isYEdge = y == -board.getM()/2 || y == board.getM()/2;
		if(isXEdge && isYEdge) {
			possibleMoves = 3;
		} else if(isXEdge || isYEdge) {
			possibleMoves = 5;
		}
		
		// Get random dice number.
		int dice = (int)(Math.random() * possibleMoves);
		
		// Find next tile based on the dice.
		int moves[][] = {
				{0,-1},
				{1,-1},
				{1,0},
				{1,1},
				{0,1},
				{-1,1},
				{-1,0},
				{-1,-1},
		};
		
		int newPos[][] = new int[possibleMoves][2];
		int counter = 0;
		for(int i = 0; i < 8; i++) {
			int newx = x + moves[i][0];
			int newy = y + moves[i][1];
			
			// Skip row 0 and column 0.
			if(newx == 0) {
				newx += moves[i][0];
			}
			if(newy == 0) {
				newy += moves[i][1];
			}
			
			if(board.isPositionValid(newx, newy)) {
				newPos[counter][0] = newx;
				newPos[counter][1] = newy;
				counter++;
			}
		}
		
		return newPos[dice];
	}
	
	/**
	 * The move() method moves the player to the new position and calculates the number of 
	 * weapons, food supplies and traps that the player got.
	 * @return an array with the new x, y coordinates, the number of weapons, food supplies
	 * and traps the player got in this move.
	 */
	public int[] move() {
		int[] status = new int[5];
		
		// Find the new position.
		int[] newPosition = getRandomMove();
		
		// Move the player to the new position.
		x = newPosition[0];
		y = newPosition[1];
		
		System.out.println("Player " + id + " moved to (" + x + ", " + y + ").");
		
		// Check for weapons.
		int weapon = 0;
		for(int i = 0; i < board.getW(); i++) {
			if(board.getWeapons()[i].getX() == x && board.getWeapons()[i].getY() == y && 
					board.getWeapons()[i].getPlayerId() == id) {
				board.getWeapons()[i].setX(0);
				board.getWeapons()[i].setY(0);
				switch(board.getWeapons()[i].getType()) {
				case "bow":
					bow = board.getWeapons()[i];
					break;
				
				case "pistol":
					pistol = board.getWeapons()[i];
					break;
					
				case "sword":
					sword = board.getWeapons()[i];
					break;
				}
				weapon = 1;
				System.out.println("Player " + id + " got a weapon (" + board.getWeapons()[i].getType() + ").");
				break;
			}
		}
		
		// Check for food.
		int food = 0;
		for(int i = 0; i < board.getF(); i++) {
			if(board.getFood()[i].getX() == x && board.getFood()[i].getY() == y) {
				board.getFood()[i].setX(0);
				board.getFood()[i].setY(0);
				
				// The player gets the points of the food.
				score += board.getFood()[i].getPoints();
				food = 1;
				System.out.println("Player " + id + " got a food supply.");
				break;
			}
		}
		
		// Check for traps.
		int traps = 0;
		for(int i = 0; i < board.getT(); i++) {
			if(board.getTraps()[i].getX() == x && board.getTraps()[i].getY() == y) {
				traps = 1;
				System.out.println("Player " + id + " found a trap (" + board.getTraps()[i].getType() + ").");
				if((board.getTraps()[i].getType() == "ropes" && sword == null) ||
						(board.getTraps()[i].getType() == "animal" && bow == null)) {
					score += board.getTraps()[i].getPoints();
					System.out.println("Player didn't managed to cope with the trap.");
				}
				break;
			}
		}
		
		status[0] = x;
		status[1] = y;
		status[2] = weapon;
		status[3] = food;
		status[4] = traps;
		return status;
	}

}
