import java.util.ArrayList;

/**
 * Class denoting a player who plays with the MinMax algorithm.
 */
public class MinMaxPlayer extends Player {
	private ArrayList<Integer[]> path;			// Information: 
												// number of move, points collected,
												// food, traps, weapons gained 
												// and type of weapon (1: bow, 2: sword, 3: pistol)
	
	/**
	 * Initializes the MinMaxPlayer by setting its id, score, x  and y coordinates to
	 * zero and board, bow, pistol, sword to null.
	 */
	MinMaxPlayer(){
		super();
		path = new ArrayList<Integer[]>();
	}
	
	/**
	 * Initializes the MinMaxPlayer by setting its id, score, name, x  and y coordinates, board.
	 * @param id the id of the player.
	 * @param name the name of the player.
	 * @param board the board of the player.
	 * @param score the score of the player.
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 */
	MinMaxPlayer(int id, String name, Board board, int score, int x, int y){
		super(id, name, board, score, x, y);
		path = new ArrayList<Integer[]>();
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
	 * The playersDistance() method calculates and returns the distance of two players.
	 * @param xPos the x coordinate of the first player.
	 * @param yPos the y coordinate of the first player.
	 * @param xOpponentPos the x coordinate of the second player.
	 * @param yOpponentPos the y coordinate of the second player.
	 * @return the distance of the two players.
	 */
	public float playersDistance(int xPos, int yPos, int xOpponentPos, int yOpponentPos) {
		// First, we convert the (x, y) coordinates of the players into 
		// (j, i) coordinates (indexes of the board array) and then
		// we calculate their distance.
		
		float a = (board.y2i(yPos) - board.y2i(yOpponentPos)) * (board.y2i(yPos) - board.y2i(yOpponentPos));
		float b = (board.x2j(xPos) - board.x2j(xOpponentPos)) * (board.x2j(xPos) - board.x2j(xOpponentPos));
		
		float d = (float)(Math.sqrt(a + b));				// distance
		
		return d;
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
	 * The kill() method checks if a player can kill the other player. A player
	 * can kill their opponent if their distance is less than d.
	 * @param player1 the first player.
	 * @param player2 the opponent. 
	 * @param d the distance limit.
	 * @return true if the player can kill the opponent. 
	 */
	public static boolean kill(Player player1, Player player2, float d) {
		// Find the MinMaxPlayer in order to call the playersDistance() method.
		
		if(player1 instanceof MinMaxPlayer) {
			if(((MinMaxPlayer) player1).playersDistance(player2) >= 0 &&
			   (int)(((MinMaxPlayer) player1).playersDistance(player2)) < d && player1.getPistol() != null) 
				
				return true;
			
			else
				return false;
		}
		else {
			if(((MinMaxPlayer) player2).playersDistance(player1) >= 0 &&
			   (int)(((MinMaxPlayer) player2).playersDistance(player1)) < d && player1.getPistol() != null) 
				
				return true;
			
			else
				return false;
		}
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
	
	/**
	 * The evaluate() method evaluates a possible player's move and returns 
	 * a double value.
	 * @param dice the number of move.
	 * @param x the current x coordinate of the player.
	 * @param y the current y coordinate of the player.
	 * @param opponent the opponent player.
	 * @return the evaluation of the move.
	 */
	public double evaluate(int dice, int x, int y, Player opponent) {
		// Find new position.
		int newX = newPosition(dice)[0];
		int newY = newPosition(dice)[1];
		
		// Calculate the new distance of the players.
		float d = playersDistance(newX, newY, opponent.getX(), opponent.getY());
		
		// Check if the player can kill the opponent.
		if(((int)d < 2) && (pistol != null)) {
			// Return a random positive value. The player will select this move.
			return 1000;
		}
		
		// Check if the opponent can kill the player.
		if(((int)d < 3) && (opponent.getPistol() != null)) { 
			// Return a random negative value. The player will not select this move.
			return -1000;
		}
		
		double gainPoints = 0;
		double gainWeapons = 0;
		
		double wPoints = 1;
		double wWeapons = 1;
		
//		// If player's score is less than 0 or opponent's score, collect points.
//		if((score < 0) || (score < opponent.getScore())) {
//			wPoints = 1.4;
//			wWeapons = 0.6;
//		}
//		
//		// If the player owns all the weapons, don't check for weapons.
//		if(pistol != null && sword != null && bow != null)
//			wWeapons = 0;
//		
//		// If the player owns a sword or a bow, reduce the wWeapons variable.
//		if(sword != null || bow != null) {
//			wPoints = 1.2;
//			wWeapons = 0.8;
//		}
		
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
		//if(wWeapons != 0) {						// If the player doesn't own all the weapons...
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
	//	}
		
		double result = wPoints * gainPoints + wWeapons * gainWeapons;
		return result;
	}
	
	/**
	 * The simulateMove() method (helper method) simulates a player's possible move.
	 * @param newX the new x coordinate of the player.
	 * @param newY the new y coordinate of the player.
	 * @param playerId the id of the player.
	 * @param b the new board of the game.
	 */
	public void simulateMove(int newX, int newY, int playerId, Board b) {
		// We will check for weapons and traps in the new position in order to delete
		// these items from the new board state.
		
		// Check for weapons.
		for(int i = 0; i < b.getW(); i++) {
			if(b.getWeapons()[i].getX() == newX && b.getWeapons()[i].getY() == newY && 
			   b.getWeapons()[i].getPlayerId() == playerId) {
				
				b.getWeapons()[i].setX(0);
				b.getWeapons()[i].setY(0);
			}
		}
		
		// Check for food.
		for(int i = 0; i < b.getF(); i++) {
			if(b.getFood()[i].getX() == newX && b.getFood()[i].getY() == newY) {
				b.getFood()[i].setX(0);
				b.getFood()[i].setY(0);
			}
		}
	}
	
	/**
	 * The chooseMinMaxMove() method finds the best move.
	 * @param root the root of the tree.
	 * @return the number of the best move.
	 */
	public int chooseMinMaxMove(Node root) {

		// For each child of the root...
		for(int i = 0; i < root.getChildren().size(); i++) {
			Node parent = root.getChildren().get(i);
			
			// For each child of the parent node...
			double min = Double.POSITIVE_INFINITY;
			for(int j = 0; j < parent.getChildren().size(); j++) {
				// Find the minimum evaluation value.
				Node n = parent.getChildren().get(j);
				
				if(n.getNodeEvaluation() < min)
					min = n.getNodeEvaluation();
			}
			
			// Set the minimum value in the parent's node evaluation value.
			parent.setNodeEvaluation(min);
		}
		
		// For each child of the root find the max evaluation value.
		double max = Double.NEGATIVE_INFINITY;
		for(int i = 0; i < root.getChildren().size(); i++) {
			Node n = root.getChildren().get(i);
			if(n.getNodeEvaluation() > max)
				max = n.getNodeEvaluation();
		}
		
		// Store the numbers of moves of the max value in an array list.
		ArrayList<Integer> keys = new ArrayList<Integer>();
		
		// Find the numbers of best moves.
		for(int i = 0; i < root.getChildren().size(); i++) {
			Node n = root.getChildren().get(i);
			if(n.getNodeEvaluation() == max)
				keys.add(n.getNodeMove()[2]);
		}
		
		int n;
		if(keys.size() > 1)
			n = (int)(Math.random() * keys.size());
		else
			n = keys.size() - 1;
		
		return keys.get(n);
	}
	
	/**
	 * The getÍextMove() method moves the player to the new position.
	 * @param xCurrentPos the x coordinate of the player.
	 * @param yCurrentPos the y coordinate of the player.
	 * @param xOpponentCurrentPos the x coordinate of the opponent.
	 * @param yOpponentCurrentPos the y coordinate of the opponent.
	 * @return the new x, y coordinates of the player.
	 */
	public int[] getNextMove(int xCurrentPos, int yCurrentPos, 
							 int xOpponentCurrentPos, int yOpponentCurrentPos) {
		
		// Use current board to create a new node which corresponds to the root of the tree.
		Board b = new Board(getBoard());
		Node root = new Node();
		root.setNodeBoard(b);
		
		// Call the createMySubtree() method.
		createMySubtree(root, 1, xCurrentPos, yCurrentPos, xOpponentCurrentPos, yOpponentCurrentPos);
		
		// The tree is now finished 
		
		// Call the chooseMinMaxMove(Node root)to choose the best available move. 
		int dice = chooseMinMaxMove(root);
		
		// Move the player to the new position.
		int[] newPos = new int[2];							// return this array
		int[] newPosition = newPosition(dice);
		x = newPosition[0];
		y = newPosition[1];
		
		newPos[0] = x;
		newPos[1] = y; 
		
		// Update path variable. 
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
		status[0] = dice;								// Number of move.
		status[1] = pointsOfMove;
		status[2] = food;
		status[3] = traps;
		status[4] = weapon;
		status[5] = type;
		
		path.add(status);
		
		return newPos;
	}
	
	/**
	 * The createMySubtree() method creates the tree of movements of the first player.
	 * We assume that the MinMaxPlayer of the game has id = 1.
	 * @param root the root of the tree.
	 * @param depth the depth of the new node.
	 * @param xCurrentPos the x coordinate of the player.
	 * @param yCurrentPos the y coordinate of the player.
	 * @param xOpponentCurrentPos the x coordinate of the opponent.
	 * @param yOpponentCurrentPos the y coordinate of the opponent.
	 */
	public void createMySubtree(Node root, int depth, int xCurrentPos, int yCurrentPos, 
								int xOpponentCurrentPos, int yOpponentCurrentPos) {
		
		// Create a copy of the root's board.
		Board rootBoard = new Board(root.getNodeBoard());
		
		// Make two MinMaxPlayer with the given coordinates.
		MinMaxPlayer p = new MinMaxPlayer(depth, "p", rootBoard, 0, xCurrentPos, yCurrentPos);
		MinMaxPlayer op = new MinMaxPlayer(depth + 1, "op", rootBoard, 0, xOpponentCurrentPos, yOpponentCurrentPos);
				
		// Find the number of available movements.   
		int[] moveNumbers = p.possibleMoves();
		
		// Check manually if the players own their pistols.
		for(int i = 0; i < rootBoard.getW(); i++) {
			// Check if the pistol is not on the board.
			if(rootBoard.getWeapons()[i].getType() == "pistol" && rootBoard.getWeapons()[i].getX() == 0 &&
			   rootBoard.getWeapons()[i].getY() == 0) {
				
				// Check which player owns the pistol.
				
				if(rootBoard.getWeapons()[i].getPlayerId() == p.getId())
					p.setPistol(rootBoard.getWeapons()[i]);
				else
					op.setPistol(rootBoard.getWeapons()[i]);
			}
		}
		
		// For each available movement (dice): Evaluate the move.
		for(int i = 0; i < moveNumbers.length; i++) {
			double evaluation = p.evaluate(moveNumbers[i], xCurrentPos, yCurrentPos, op);
			
			// Find the new coordinates.
			int[] newPos = p.newPosition(moveNumbers[i]);
			int newX = newPos[0];
			int newY = newPos[1];
			
			// Create a clone of the root node’s board and simulate making the movement.
			Board b = new Board(root.getNodeBoard());
			p.simulateMove(newX, newY, p.getId(), b);
			
			// Create an array with the new x, y coordinates and the number of dice.
			int[] move = new int[] {newX, newY, moveNumbers[i]};
			
			// Create a new node as child of the parent node using the new board state.
		
			Node newNode = new Node(root, new ArrayList<Node>(), depth, move, b, evaluation);
			
			// Add the new node in the children list of the parent node.
			root.getChildren().add(newNode);
			
			// Complete the tree branches by calling the creteOpponentSubTree() method.
			createOpponentSubtree(newNode, depth + 1, newX, newY, xOpponentCurrentPos, yOpponentCurrentPos);
		}
		
	}
	
	/**
	 * The createOpponentSubtree() method creates the tree of movements of the second player.
	 * We assume that the opponent has id = 2.
	 * @param parent the parent node.
	 * @param depth the depth of the tree.
	 * @param xCurrentPos the x coordinate of the player.
	 * @param yCurrentPos the y coordinate of the player.
	 * @param xOpponentCurrentPos the x coordinate of the opponent.
	 * @param yOpponentCurrentPos the y coordinate of the opponent.
	 */
	public void createOpponentSubtree(Node parent, int depth, int xCurrentPos, int yCurrentPos, 
									  int xOpponentCurrentPos, int yOpponentCurrentPos) {
		
		// Create a copy of the parent's board.
		Board parentBoard = new Board(parent.getNodeBoard());
		
		// Make two MinMaxPlayer with the given coordinates.
		MinMaxPlayer p = new MinMaxPlayer(depth - 1, "p", parentBoard, 0, xCurrentPos, yCurrentPos);
		MinMaxPlayer op = new MinMaxPlayer(depth, "op", parentBoard, 0, xOpponentCurrentPos, yOpponentCurrentPos);
				
		// Find the number of available movements.
		int[] moveNumbers = op.possibleMoves();
		
		// Check manually if the players own their pistols.
		for(int i = 0; i < parentBoard.getW(); i++) {
			// Check if the pistol is not on the board.
			if(parentBoard.getWeapons()[i].getType() == "pistol" && parentBoard.getWeapons()[i].getX() == 0 &&
			   parentBoard.getWeapons()[i].getY() == 0) {
				
				// Check which player owns the pistol.
				
				if(parentBoard.getWeapons()[i].getPlayerId() == p.getId())
					p.setPistol(parentBoard.getWeapons()[i]);
				else
					op.setPistol(parentBoard.getWeapons()[i]);
			}
		}
		
		// For each available movement (dice): Evaluate the move.
		for(int i = 0; i < moveNumbers.length; i++) {
			double opEvaluation = op.evaluate(moveNumbers[i], xOpponentCurrentPos, yOpponentCurrentPos, p);
			
			// Find the new coordinates.
			int[] newPos = op.newPosition(moveNumbers[i]);
			int newX = newPos[0];
			int newY = newPos[1];
			
			// Create a clone of the parent node’s board and simulate making the movement.
			Board b = new Board(parent.getNodeBoard());
			op.simulateMove(newX, newY, op.getId(), b);
			
			// Create an array with the new x, y coordinates and the number of dice.
			int[] move = new int[] {newX, newY, moveNumbers[i]};
			
			// Create a new node as child of the parent node using the new board state.
			double evaluation = parent.getNodeEvaluation() - opEvaluation;
			Node newNode = new Node(parent, new ArrayList<Node>(0), depth, move, b, evaluation);
			
			// Add the new node in the children list of the parent node.
			parent.getChildren().add(newNode);
			
		}
	}
	
}
