/**
 * Class denoting a player of the game.
 */
public class Player {
	private int id;
	private String name;
	private Board board;
	private int score;
	private int x;
	private int y;
	private Weapon bow;
	private Weapon pistol;
	private Weapon sword;
	
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
		int[] newPosition = new int[2];
		int randomMove = 0;
		/*
		 * The variable checkOuterFrame is true when the player is in the outer frame of the board.
		 */
		boolean checkOuterFrame = x == (board.getN() / 2) || x == -(board.getN() / 2) || 
				                  y == (board.getM() / 2) || y == -(board.getM() / 2);
		
		// First check if the player isn't in the outer frame.
		if(!checkOuterFrame) {
			randomMove = (int)(Math.random() * 8 + 1);
		}
		
		// Check if the player is in a corner.
		else if(x == -(board.getN() / 2) && y == -(board.getM() / 2)) {			// top left corner
			// Possible moves: 3, 4, 5.
			randomMove = (int)(Math.random() * 3 + 3);
		}
		else if(x == (board.getN() / 2) && y == -(board.getM() / 2)) {			// top right corner
			// Possibles moves: 5, 6, 7.
			randomMove = (int)(Math.random() * 3 + 5);
		}
		else if(x == -(board.getN() / 2) && y == (board.getM() / 2)) {			// bottom left corner
			// Possibles moves: 1, 2, 3.
			randomMove = (int)(Math.random() * 3 + 1); 
		}
		else if(x == (board.getN() / 2) && y == (board.getM() / 2)) {			// bottom right corner
			// Possibles moves: 1, 7, 8.
			randomMove = (int)(Math.random() * 3 + 1);
			if(randomMove != 1)
				randomMove += 5;
		}
		
		// Check if the player is in the outer rows or columns.
		else if(y == -(board.getM() / 2) && x > -(board.getN() / 2) && x < (board.getN() / 2)) {	// first row
			// Possible moves: 3, 4, 5, 6, 7;
			randomMove = (int)(Math.random() * 5 + 3);
		}
		else if(y == (board.getM() / 2) && x > -(board.getN() / 2) && x < (board.getN() / 2)) {		// last row
			// Possible moves: 1, 2, 3, 7, 8.
			randomMove = (int)(Math.random() * 5 + 1);
			if(randomMove > 3)
				randomMove += 3;
		}
		else if(x == -(board.getN() / 2) && y > -(board.getM() / 2) && y < (board.getM() / 2)) {	// first column
			// Possible moves: 1, 2, 3, 4, 5.
			randomMove = (int)(Math.random() * 5 + 1);
		}
		else if(x == (board.getN() / 2) && y > -(board.getM() / 2) && y < (board.getM() / 2)) {		// last column
			// Possible moves: 1, 5, 6, 7, 8.
			randomMove = (int)(Math.random() * 5 + 1);
			if(randomMove != 1)
				randomMove += 3;
		}
		
		// Calculate the coordinates of the new position.
		switch(randomMove){
		case 1: 
			newPosition[0] = x;
			if(y == 1)
				newPosition[1] = -1;
			else
				newPosition[1] = y - 1;
			break;
			
		case 2:
			if(x == -1)
				newPosition[0] = 1;
			else
				newPosition[0] = x + 1;
			
			if(y == 1)
				newPosition[1] = -1;
			else 
				newPosition[1] = y - 1;
			break;
			
		case 3:
			newPosition[1] = y;
			if(x == -1)
				newPosition[0] = 1;
			else
				newPosition[0] = x + 1;
			break;
			
		case 4:
			if(x == -1)
				newPosition[0] = 1;
			else 
				newPosition[0] = x + 1;
			
			if(y == -1)
				newPosition[1] = 1;
			else
				newPosition[1] = y + 1;
			break;
			
		case 5:
			newPosition[0] = x;
			if(y == -1)
				newPosition[1] = 1;
			else
				newPosition[1] = y + 1;
			break;
			
		case 6:
			if(x == 1)
				newPosition[0] = -1;
			else
				newPosition[0] = x - 1;
			
			if(y == -1)
				newPosition[1] = 1;
			else 
				newPosition[1] = y + 1;
			break;
			
		case 7:
			newPosition[1] = y;
			if(x == 1)
				newPosition[0] = -1;
			else
				newPosition[0] = x - 1;
			break;
			
		case 8:
			if(x == 1)
				newPosition[0] = -1;
			else
				newPosition[0] = x - 1;
			
			if(y == 1)
				newPosition[1] = -1;
			else 
				newPosition[1] = y - 1;
			break;
			
		default:
			newPosition[0] = x;
			newPosition[1] = y;
			System.out.println("Not accepted move.");
		}
		return newPosition;
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
