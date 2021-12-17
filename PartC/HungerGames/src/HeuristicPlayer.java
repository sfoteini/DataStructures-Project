import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class denoting a strategy player of the game.
 */
public class HeuristicPlayer extends Player {
	private ArrayList<Integer[]> path;			// Information: 
												// number of move, points collected,
												// food, traps, weapons gained 
												// and type of weapon (1: bow, 2: sword, 3: pistol)
	
	private static int r;
	
	/**
	 * Initializes the heuristic player by setting its id, score, x  and y coordinates to
	 * zero and board, bow, pistol, sword to null.
	 */
	HeuristicPlayer(){
		super();
		path = new ArrayList<Integer[]>();
	}
	
	/**
	 * Initializes the heuristic player by setting its id, score, name, x  and y coordinates, board.
	 * @param id the id of the player.
	 * @param name the name of the player.
	 * @param board the board of the player.
	 * @param score the score of the player.
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 */
	HeuristicPlayer(int id, String name, Board board, int score, int x, int y){
		super(id, name, board, score, x, y);
		path = new ArrayList<Integer[]>();
	}
	
	/**
	 * The setR() method sets the radius of the area that the player can see.
	 * @param r the radius to be set.
	 */
	public static void setR(int r) {
		HeuristicPlayer.r = r;
	}
	
	/**
	 * The getR() method returns the radius of the area that the player can see.
	 * @return the radius.
	 */
	public static int getR() {
		return r;
	}
	
	/**
	 * The fieldOfView() method (helper method) calculates and returns the coordinates 
	 * of the last top left and the last bottom right tile that the player can see.
	 * @return an array with the coordinates.
	 */
	public int[][] fieldOfView(){
		// In the tiles array we will save the coordinates of the last top left tile
		// and last bottom right tile that the player can see.
		int[][] tiles = new int[2][2];
		int[][] moves = { {-1, -1} , {1, 1} };
	
		
		for(int j = 0; j < 2; j++) {
			
			int[][] temp = new int[r][2];
			int counter = -1;
			
			int xIsZero = 0;
			int yIsZero = 0;
			
			for(int i = 1; i <= r; i++) {
				int newx = x + (i + xIsZero) * moves[j][0];

				int newy = y + (i + yIsZero) * moves[j][1];
				
				if(newx == 0) {
					newx += moves[j][0];
					xIsZero = 1;
				}
				if(newy == 0) {
					newy += moves[j][1];
					yIsZero = 1;
				}
				
				if(board.isPositionValid(newx, newy)) {
					counter++;
					temp[counter][0] = newx;
					temp[counter][1] = newy;
					
				}
			}
			
			// Store the last valid position.
			if(counter == -1) {
				tiles[j][0] = x;
				tiles[j][1] = y;
			}
			else {
				tiles[j][0] = temp[counter][0];
				tiles[j][1] = temp[counter][1];
			}
			
		}
		
		return tiles;
	}
	
	/**
	 * The playersDistance() method calculates and returns the distance of two players.
	 * It returns -1 when the opponent isn't in the field of view of the player. 
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
		
		int[][] tiles = fieldOfView();
		
		// Check if the opponent is inside the field of view of the player.
		for(int x = tiles[0][0]; x <= tiles[1][0]; x++) {
			for(int y = tiles[0][1]; y <= tiles[1][1]; y++) {
				
				// Skip row 0 and column 0.
				if(x == 0 || y == 0)
					continue;
				
				// Check the opponent's position.
				if(p.getX() == x && p.getY() == y)
					// If the player sees the opponent, return the distance.
					return d;
			}
		}
		
		// The player can't see the opponent. Return -1.
		return -1;
	}

	/**
	 * The newPosition() method (helper method) returns the coordinates (x, y) of
	 * the new position for a specific number of move.  
	 * @param dice the number of move.
	 * @return an array with the (x, y) coordinates of the new position.
	 */
	public int[] newPosition(int dice) {
		int[] newPosition = new int[2];
		
		// Find next tile.
		int moves[][] = {
				{0,-1},			// dice = 1
				{1,-1},			// dice = 2
				{1,0},			// dice = 3
				{1,1},			// dice = 4
				{0,1},			// dice = 5
				{-1,1},			// dice = 6
				{-1,0},			// dice = 7
				{-1,-1}			// dice = 8
		};
		
		int newx = x + moves[dice - 1][0];
		int newy = y + moves[dice - 1][1];
		
		// Skip row 0 and column 0.
		if(newx == 0)
			newx += moves[dice - 1][0];
		
		if(newy == 0)
			newy += moves[dice - 1][1];
		
		if(board.isPositionValid(newx, newy)) {
			newPosition[0] = newx;
			newPosition[1] = newy;
		}
		else {
			newPosition[0] = x;
			newPosition[1] = y;
		}
		
		return newPosition;
	}
	
	/**
	 * The possibleMoves() method (helper method) returns the numbers of the
	 * possible player's moves.
	 * @return an array with the numbers of the possible moves.
	 */
	public int[] possibleMoves() {

		// Find the number of possible moves.
		int possibleMoves = 8;
		boolean isXEdge = x == -board.getN()/2 || x == board.getN()/2;
		boolean isYEdge = y == -board.getM()/2 || y == board.getM()/2;
		if(isXEdge && isYEdge) 
			possibleMoves = 3;
		else if(isXEdge || isYEdge) 
			possibleMoves = 5;
		
		int[] moveNumbers = new int[possibleMoves];
		
		// Use the newPosition() method to find the next tile and to check
		// if the position is valid. If the position is valid, the number
		// of move is valid too.
		int counter = 0;
		for(int i = 1; i <= 8; i++) {
			int newx = newPosition(i)[0];
			int newy = newPosition(i)[1];
			
			if(newx == x && newy == y) 						// If the position is not valid.
				continue;
			else {
				moveNumbers[counter] = i;
				counter++;
			}
		}
		
		return moveNumbers;
	}
	
	/**
	 * The evaluate() method evaluates a possible player's move and returns 
	 * a double value. If this value is positive, the move is a good choice.
	 * @param dice the number of the move.
	 * @param p the opponent player.
	 * @return a double number.
	 */
	public double evaluate(int dice, Player p) {
		// Find new position.
		int newX = newPosition(dice)[0];
		int newY = newPosition(dice)[1];
		
		// Save the previous position of the player.
		int prevX = x;
		int prevY = y;
		
		// Set the x, y coordinates of the player to the new x, y coordinates
		// in order to call the playersDistance() method and calculate the new 
		// distance of the players.
		x = newX;
		y = newY;
		
		double gainPoints = 0;
		double gainWeapons = 0;
		
		double wPoints = 1;
		double wWeapons = 1;
		
		// Check if the player can kill the opponent.
		if((playersDistance(p) >= 0) && ((int)(playersDistance(p)) < 2) && (pistol != null)) {
			// Return a random positive value. The player will select this move.
			x = prevX;					// Restore the player's coordinates
			y = prevY;
			return 1000;
		}
		
		
		// Check if the opponent can kill the player.
		if((playersDistance(p) >= 0) && ((int)(playersDistance(p)) < 3) && (p.getPistol() != null)) { 
			// Return a random negative value. The player will not select this move.
			x = prevX;					// Restore the player's coordinates
			y = prevY;
			return -1000;
		}
		
		
		// If player's score is less than 0 or opponent's score, collect points.
		if((score < 0) || (score < p.getScore())) {
			wPoints = 1.4;
			wWeapons = 0.6;
		}
		
		// If the player owns all the weapons, don't check for weapons.
		if(pistol != null && sword != null && bow != null)
			wWeapons = 0;
		
		// If the player owns a sword or a bow, reduce the wWeapons variable.
		if(sword != null || bow != null) {
			wPoints = 1.2;
			wWeapons = 0.8;
		}
		
		// Check for trap in the new position.
		for(int i = 0; i < board.getT(); i++) {
			if(board.getTraps()[i].getX() == newX && board.getTraps()[i].getY() == newY) {
				if(board.getTraps()[i].getType() == "ropes" && sword == null ||
				   board.getTraps()[i].getType() == "animal" && bow == null) {
					// Give the gainPoints variable a negative value.
					gainPoints = board.getTraps()[i].getPoints();
					break;
				}
				// If the player owns the weapon the gainPoints variable will be 0.
				// The move doesn't affect player's score.
			}
		}
		
		// Check for food supplies in the new position.
		for(int i = 0; i < board.getF(); i++) {
			if(board.getFood()[i].getX() == newX && board.getFood()[i].getY() == newY) {
				// Give the gainPoints variable a positive value.
				gainPoints = board.getFood()[i].getPoints();
				break;
			}
		}
		
		// Check for weapon in the new position.
		if(wWeapons != 0) {						// If the player doesn't own all the weapons...
			for(int i = 0; i < board.getW(); i++) {
				if(board.getWeapons()[i].getX() == newX && board.getWeapons()[i].getY() == newY &&
				   board.getWeapons()[i].getPlayerId() == id) {
					
					if(board.getWeapons()[i].getType() == "pistol")
						// If the weapon is a pistol, give the gainWeapons variable a random max value.
						// The player will get the pistol.
						gainWeapons = 1000;
					else 
						// If the player owns a sword and/or a bow, give the gainWeapons variable
						// the points max value.
						gainWeapons = 10;
					
					break;
				}
			}
		}
		
		// Restore the player's coordinates
		x = prevX;
		y = prevY;
		
		double result = wPoints * gainPoints + wWeapons * gainWeapons;
		return result;
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
		// Find the heuristic player in order to call the playersDistance() method.
		
		if(player1 instanceof HeuristicPlayer) {
			if(((HeuristicPlayer) player1).playersDistance(player2) >= 0 &&
			   (int)(((HeuristicPlayer) player1).playersDistance(player2)) < d && player1.getPistol() != null) 
				
				return true;
			
			else
				return false;
		}
		else {
			if(((HeuristicPlayer) player2).playersDistance(player1) >= 0 &&
			   (int)(((HeuristicPlayer) player2).playersDistance(player1)) < d && player1.getPistol() != null) 
				
				return true;
			
			else
				return false;
		}
	}
	
	/**
	 * The move() method moves the player to the new position.
	 * @param p the opponent player.
	 * @return an array with the (x, y) coordinates of the new position.
	 */
	public int[] move(Player p) {
		int[] newPos = new int[2];
		
		// Numbers of possible moves;
		int[] moveNumber = possibleMoves();
		
		// Possible player's moves and move's evaluation
		Map<Integer, Double> moves = new HashMap<Integer, Double>(moveNumber.length);
		
		
		for(int i = 0; i < moveNumber.length; i++) {
			moves.put(moveNumber[i], evaluate(moveNumber[i], p));
		}
		
		// Find the best move.
		double maxValue = Double.NEGATIVE_INFINITY;			
		
		
		// Find the max value.
		for(Map.Entry<Integer, Double> entry : moves.entrySet()) {
			if(entry.getValue() > maxValue)
				maxValue = entry.getValue();
		}
		
		// Store the keys of the max value in an array list.
		ArrayList<Integer> keys = new ArrayList<Integer>();
		
		// Find the keys.
		for(Map.Entry<Integer, Double> entry : moves.entrySet()) {
			if(entry.getValue() == maxValue)
				keys.add(entry.getKey());
		}
		
		int n;
		if(keys.size() > 1)
			n = (int)(Math.random() * keys.size());
		else
			n = keys.size() - 1;
		
		// Move the player to the new position.
		int[] newPosition = newPosition(keys.get(n));
		x = newPosition[0];
		y = newPosition[1];
		
		newPos[0] = x;
		newPos[1] = y;
		
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
		
		
		Integer[] status = new Integer[6];
		status[0] = keys.get(n);				// Number of move.
		status[1] = pointsOfMove;
		status[2] = food;
		status[3] = traps;
		status[4] = weapon;
		status[5] = type;
		
		path.add(status);
		
		return newPos;
	}
	
	/**
	 * The statistics() method prints the status of the player (number of selected move,
	 * points earned, food supplies, traps and weapons gained).
	 */
	public void statistics() {
		Integer[] status = path.get(path.size() - 1);
		
		System.out.println("Player " + id + " chose the move with number " + status[0] +
				" and earned " + status[1] + " points.");
		if(status[2] == 1) {
			System.out.println("Player " + id + " got a food supply.");
		}
		if(status[3] == 1) {
			System.out.println("Player " + id + " found a trap.");
			if(status[1] < 0)												// if points earned are negative		
				System.out.println("Player didn't manage to cope with the trap.");
		}
		if(status[4] == 1) {
			System.out.print("Player " + id + " got a weapon (");
			switch(status[5]) {												// type of weapon
			case 1:
				System.out.print("bow).\n");
				break;
			case 2:
				System.out.print("sword).\n");
				break;
			case 3:
				System.out.print("pistol).\n");
				break;
			}
		}
	}
	
}
