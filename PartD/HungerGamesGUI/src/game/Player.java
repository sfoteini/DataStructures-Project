package game;

import java.util.ArrayList;

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

	ArrayList<Integer[]> path;					// Information: 
												// number of move, points collected,
												// food, traps, weapons gained 
												// and type of weapon (1: bow, 2: sword, 3: pistol)

	String imgSrc;
	boolean dead;
	
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
		path = new ArrayList<Integer[]>();
		imgSrc = "";
		dead = false;
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
		
		path = new ArrayList<Integer[]>();
		imgSrc = loadImgSrc();
		dead = false;
	}
	
	/**
	 * The loadImgSrc() method calculates the source of the image.
	 * @return the source.
	 */
	public String loadImgSrc() {
		if(id == 1)
			return "/resources/RandomPlayer1.png";
		else 
			return "/resources/RandomPlayer2.png";
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
	 * The setImgSrc() method set the source of the icon of the player.
	 * @param imgSrc the source to be set.
	 */
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
	/**
	 * The setDead() method sets the dead variable of the player.
	 * @param dead the value to be set.
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
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
	 * The getImgSrc() method returns the source of the icon of the player.
	 * @return the source of the image.
	 */
	public String getImgSrc() {
		return imgSrc;
	}
	
	/**
	 * The getDead() method returns true if the player is dead, else returns false.
	 * @return the dead value of the player.
	 */
	public boolean getDead() {
		return dead;
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
	 * The move() method moves the player to the new position.
	 * @return an array with the new x, y coordinates of the new position.
	 */
	public int[] move() {
		// Find the new position.
		int[] newPosition = getRandomMove();
		
		// Move the player to the new position.
		x = newPosition[0];
		y = newPosition[1];
		
		// Update the path variable.
		
		int pointsOfMove = 0;
		
		// Check for weapons.
		int weapon = 0;
		int type = 0;
		for(int i = 0; i < board.getW(); i++) {
			if(board.getWeapons()[i].getX() == x && board.getWeapons()[i].getY() == y && 
					board.getWeapons()[i].getPlayerId() == id) {
				
				board.getWeapons()[i].setX(0);
				board.getWeapons()[i].setY(0);
				switch(board.getWeapons()[i].getType()) {
				case "bow":
					bow = board.getWeapons()[i];
					type = 1;
					break;
				
				case "pistol":
					pistol = board.getWeapons()[i];
					type = 3;
					break;
					
				case "sword":
					sword = board.getWeapons()[i];
					type = 2;
					break;
				}
				weapon = 1;
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
				pointsOfMove = board.getFood()[i].getPoints();
				break;
			}
		}
		
		// Check for traps.
		int traps = 0;
		for(int i = 0; i < board.getT(); i++) {
			if(board.getTraps()[i].getX() == x && board.getTraps()[i].getY() == y) {
				traps = 1;
				
				if((board.getTraps()[i].getType() == "ropes" && sword == null) ||
						(board.getTraps()[i].getType() == "animal" && bow == null)) {
					score += board.getTraps()[i].getPoints();
					pointsOfMove = board.getTraps()[i].getPoints();
				}
				break;
			}
		}
		
		Integer[] status = new Integer[5];
		status[0] = pointsOfMove;
		status[1] = food;
		status[2] = traps;
		status[3] = weapon;
		status[4] = type;
		
		path.add(status);
		
		return newPosition;
	}
	
	/**
	 * The playersDistance() method calculates and returns the distance of two players.
	 * @param p the second player.
	 * @return the distance of two players.
	 */
	public float playersDistance(Player p) {
		// First, we convert the (x, y) coordinates of the players into 
		// (j, i) coordinates (indexes of the board array) and then
		// we calculate their distance.
		
		float a = (board.y2i(y) - p.board.y2i(p.getY())) * (board.y2i(y) - p.board.y2i(p.getY()));
		float b = (board.x2j(x) - p.board.x2j(p.getX())) * (board.x2j(x) - p.board.x2j(p.getX()));
		
		float d = (float)(Math.sqrt(a + b));				// distance
		
		return d;
	}
	
	/**
	 * The kill() method checks if a player can kill the other player. A player
	 * can kill their opponent if their distance is less than d.
	 * @param player1 the first player.
	 * @param player2 the opponent. 
	 * @param d the distance limit.
	 * @return true if the player can kill the opponent. 
	 */
	public static boolean kill(Player player1, Player player2, float d) {
		
		if(player1.playersDistance(player2) >= 0 &&
			(int)(player1.playersDistance(player2)) < d && player1.getPistol() != null) 
				
				return true;
		
		
		return false;
	}
	
	/**
	 * The statistics() method returns information about the player's move (new position,
	 * points earned, food supplies, traps and weapons gained).
	 * @return a string with player's info.
	 */
	public String statistics() {
		Integer[] status = path.get(path.size() - 1);
		
		String a = "Player " + id + " moved to (" + x + ", " + y + ") and earned " + status[0] + " points.\n";
		
		String b = "";
		if(status[1] == 1)
			b = "Player " + id + " got a food supply.\n";
		
		String c = "";
		if(status[2] == 1) {
			c = "Player " + id + " found a trap. ";
			if(status[0] < 0)												// if points earned are negative		
				c += " Player didn't manage to cope with the trap.\n";
			else
				c += "\n";
		}
		
		String d = "";
		if(status[3] == 1) {
			d = "Player " + id + " got a weapon (";
			switch(status[4]) {												// type of weapon
			case 1:
				d += "bow).\n";
				break;
			case 2:
				d += "sword).\n";
				break;
			case 3:
				d += "pistol).\n";
				break;
			}
		}
		
		return a + b + c + d;
	}

}
