package game;
import java.util.ArrayList;
import java.util.List;

/**
 * Class denoting the board of the game.
 */
public class Board {
	private int N;
	private int M;
	private int W;			// number of weapons
	private int F;			// number of food supplies
	private int T;			// number of traps
	private int[][] weaponAreaLimits;
	private int[][] foodAreaLimits;
	private int[][] trapAreaLimits;
	private Weapon[] weapons;
	private Food[] food;
	private Trap[] traps;
	
	/**
	 * Initializes the board by setting its N, M dimensions to zero, the number of weapons, 
	 * food supplies and traps to zero and the arrays weaponAreaLimits, foodAreaLimits,
	 * trapAreaLimits, weapons, food, traps to null.
	 */
	Board(){
		N = 0;
		M = 0;
		W = 0;
		F = 0;
		T = 0;
		weaponAreaLimits = null;
		foodAreaLimits = null;
		trapAreaLimits = null;
		weapons = null;
		food = null;
		traps = null;
	}
	
	/**
	 * Initializes the board by setting its N, M dimensions (N = M, because the board is square), 
	 * the number of weapons, food supplies and traps. Sets the arrays weaponAreaLimits, 
	 * foodAreaLimits, trapAreaLimits to their default values.
	 * @param N the length of the board.
	 * @param M the width of the board.
	 * @param W the number of the weapons.
	 * @param F the number of the food supplies.
	 * @param T the number of the traps.
	 */
	Board(int N, int M, int W, int F, int T){
		this.N = N;
		this.M = M;
		this.W = W;
		this.F = F;
		this.T = T;
		
		
		// (-2, -2), (2, -2), (-2, 2) and (2, 2) are the (x, y) coordinates of the angles of the
		// square that consists of the weapons.
		weaponAreaLimits = new int[][] {{-2, -2}, {2, -2}, {-2, 2}, {2, 2}};
		
		// (-3, -3), (3, -3), (-3, 3) and (3, 3) are the (x, y) coordinates of the angles of the
		// square that consists of the food supplies.
		foodAreaLimits = new int[][] {{-3, -3}, {3, -3}, {-3, 3}, {3, 3}};
		
		// (-4, -4), (4, -4), (-4, 4) and (4, 4) are the (x, y) coordinates of the angles of the
		// square that consists of the traps.
		trapAreaLimits = new int[][] {{-4, -4}, {4, -4}, {-4, 4}, {4, 4}};
		
		weapons = new Weapon[W];
		food = new Food[F];
		traps = new Trap[T];
	}
	
	/**
	 * Initializes the board by an other board object.
	 * @param b the board object.
	 */
	Board(Board b) {
		N = b.N;
		M = b.M;
		W = b.W;
		F = b.F;
		T = b.T;
		
		// Arrays
		weaponAreaLimits = new int[4][2];
		foodAreaLimits = new int[4][2];
		trapAreaLimits = new int[4][2];
		weapons = new Weapon[W];
		food = new Food[F];
		traps = new Trap[T];
				
		// Deep copy
		for(int i=0; i<4; i++) {
			for(int j=0; j<2; j++) {
				weaponAreaLimits[i][j] = b.weaponAreaLimits[i][j];
				foodAreaLimits[i][j] = b.foodAreaLimits[i][j];
				trapAreaLimits[i][j] = b.trapAreaLimits[i][j];
			}
		}
		
		for(int i=0; i<W; i++) {
			weapons[i] = new Weapon(b.weapons[i]);
		}
		
		for(int i=0; i<F; i++) {
			food[i] = new Food(b.food[i]);
		}
		
		for(int i=0; i<T; i++) {
			traps[i] = new Trap(b.traps[i]);
		}	
	}
	
	/**
	 * The setN() method set the length of the board.
	 * @param N the length to be set.
	 */
	public void setN(int N) {
		this.N = N;
	}
	
	/**
	 * The setM() method sets the width of the board.
	 * @param M the width to be set.
	 */
	public void setM(int M) {
		this.M = M;
	}
	
	/**
	 * The setW() method sets the number of weapons.
	 * @param W the number of weapons to be set.
	 */
	public void setW(int W) {
		this.W = W;
	}
	
	/**
	 * The setF() method sets the number of food supplies.
	 * @param F the number of food supplies to be set.
	 */
	public void setF(int F) {
		this.F = F;
	}
	
	/**
	 * The setT() method sets the number of traps.
	 * @param T the number of traps to be set. 
	 */
	public void setT(int T) {
		this.T = T;
	}
	
	/**
	 * The setWeaponAreaLimits() method sets an array with the (x, y) coordinates
	 * of the limits of the weapon area.
	 * @param weaponAreaLimits the array to be set.
	 */
	public void setWeaponAreaLimits(int[][] weaponAreaLimits) {
		this.weaponAreaLimits = weaponAreaLimits;
	}
	
	/**
	 * The setFoodAreaLimits() method sets an array with the (x, y) coordinates
	 * of the limits of the food area.
	 * @param foodAreaLimits the array to be set.
	 */
	public void setFoodAreaLimits(int[][] foodAreaLimits) {
		this.foodAreaLimits = foodAreaLimits;
	}
	
	/**
	 * The setTrapAreaLimits() method sets an array with the (x, y) coordinates
	 * of the limits of the trap area.
	 * @param trapAreaLimits the array to be set.
	 */
	public void setTrapAreaLimits(int[][] trapAreaLimits) {
		this.trapAreaLimits = trapAreaLimits;
	}
	
	/**
	 * The setWeapons() method sets an array of the weapons.
	 * @param weapons the array of weapons to be set.
	 */
	public void setWeappons(Weapon[] weapons) {
		this.weapons = weapons;
	}
	
	/**
	 * The setFood() method sets an array of the food supplies.
	 * @param food the array of food supplies to be set.
	 */
	public void setFood(Food[] food) {
		this.food = food;
	}
	
	/**
	 * The setTraps() method sets an array of the traps.
	 * @param traps the array of traps to be set.
	 */
	public void setTraps(Trap[] traps) {
		this.traps = traps;
	}
	
	/**
	 * The getN() method returns the length of the board.
	 * @return the length of the board.
	 */
	public int getN() {
		return N;
	}
	
	/**
	 * The getM() method returns the width of the board.
	 * @return the width of the board.
	 */
	public int getM() {
		return M;
	}
	
	/**
	 * The getW() method returns the number of weapons.
	 * @return the number of weapons.
	 */
	public int getW() {
		return W;
	}
	
	/**
	 * The getF() method returns the the number of food supplies.
	 * @return the number of food supplies.
	 */
	public int getF() {
		return F;
	}
	
	/**
	 * The getT() method returns the number of traps.
	 * @return the number of traps.
	 */
	public int getT() {
		return T;
	}
	
	/**
	 * The getWeaponAreaLImits() method returns a 2-D array of the coordinates of
	 * the limits of the weapon area.
	 * @return the array of the coordinates of the limits of the weapon area.
	 */
	public int[][] getWeaponAreaLimits(){
		return weaponAreaLimits;
	}
	
	/**
	 * The getFoodAreaLimits() method returns a 2-D array of the coordinates of the 
	 * limits of the food area.
	 * @return the array of the coordinates of the limits of the food area.
	 */
	public int[][] getFoodAreaLimits(){
		return foodAreaLimits;
	}
	
	/**
	 * The getTrapAreaLimits() method returns a 2-D array of the coordinates of
	 * the limits of the trap area.
	 * @return the array of the coordinates of the limits of the trap area.
	 */
	public int[][] getTrapAreaLimits(){
		return trapAreaLimits;
	}
	
	/**
	 * The getWeapons() method returns an array of the weapons.
	 * @return the array of the weapons.
	 */
	public Weapon[] getWeapons() {
		return weapons;
	}
	
	/**
	 * The getFood() method returns an array of the food supplies.
	 * @return the array of the food supplies.
	 */
	public Food[] getFood() {
		return food;
	}
	
	/**
	 * The getTraps() method returns an array of the traps.
	 * @return the array of the traps.
	 */
	public Trap[] getTraps() {
		return traps;
	}
	
	/**
	 * The createRandomeWeapon() method initializes the weapons array with random values. We create
	 * W weapons. W / 2 weapons have playerId = 1 and the other W / 2 weapons have playerId = 2.
	 * W / 3 weapons are bows, W / 3 weapons are pistols and W / 3 weapons are swords. 
	 * We assume that W (number of weapons) is a multiple of 6.
	 */
	
	public void createRandomWeapon() {
		/*
		 * In the array coordinates we store the (x, y) coordinates of every square in the
		 * weapon area. We will use the function Math.random() to pick a random number
		 * between 0 and 15 (both inclusively). We will use this number as index for the array, 
		 * in order to find the correct coordinates.
		 */
		int[][] coordinates = new int[][] { {-2, -2}, {-1, -2}, {1, -2}, {2, -2},
											{-2, -1}, {-1, -1}, {1, -1}, {2, -1},
											{-2, 1}, {-1, 1}, {1, 1}, {2, 1},
											{-2, 2}, {-1, 2}, {1, 2}, {2, 2}};
		
		/*
		 * In the list listOfCoordinates we store the (x, y) coordinates of every new weapon object,
		 * so as not to create two different weapons with the same coordinates. The coordinates are
		 * represented by the number of row of the coordinates array.
		 */
		List<Integer> listOfCoordinates = new ArrayList<Integer>();
		
		int x, y, r;							// x the x coordinate
												// y the y coordinate
												// r the random number
		
		// Creating the bows
		for(int i = 0 ; i < (W / 3) ; i++) {
			while(true) {
				r = (int)(Math.random() * 16);
				if(!listOfCoordinates.contains(r)) {
					x = coordinates[r][0];
					y = coordinates[r][1];
					listOfCoordinates.add(r);
					break;
				}
			}
			if(i <= (W / 6 - 1))
				weapons[i] = new Weapon(i, x, y, 1, "bow");
			else
				weapons[i] = new Weapon(i, x, y, 2, "bow");
		}
		
		// Creating the pistols
		for(int i = W / 3 ; i < (2 * W / 3) ; i++) {
			while(true) {
				r = (int)(Math.random() * 16);
				if(!listOfCoordinates.contains(r)) {
					x = coordinates[r][0];
					y = coordinates[r][1];
					listOfCoordinates.add(r);
					break;
				}
			}
			if(i <= (W / 2 - 1))
				weapons[i] = new Weapon(i, x, y, 1, "pistol");
			else
				weapons[i] = new Weapon(i, x, y, 2, "pistol");
		}
		
		// Creating the swords
		for(int i = (2 * W / 3) ; i < W ; i++) {
			while(true) {
				r = (int)(Math.random() * 16);
				if(!listOfCoordinates.contains(r)) {
					x = coordinates[r][0];
					y = coordinates[r][1];
					listOfCoordinates.add(r);
					break;
				}
			}
			if(i <= (5 * W / 6 - 1))
				weapons[i] = new Weapon(i, x, y, 1, "sword");
			else
				weapons[i] = new Weapon(i, x, y, 2, "sword");
		}
		
	}
	
	/**
	 * The createRandomTrap() method initializes the traps array with random values. 
	 * We create T traps. T / 2 traps are animals and the other T / 2 traps are ropes.
	 * We assume that T (number of traps) is a multiple of 2.
	 */
	public void createRandomTrap() {
		/*
		 * In the array coordinates we store the (x, y) coordinates of every square in the
		 * trap area. We will use the function Math.random() to pick a random number
		 * between 0 and 27 (both inclusively). We will use this number as index for the array, 
		 * in order to find the correct coordinates.
		 */
		int[][] coordinates = new int[][] { {-4, -4}, {-3, -4}, {-2, -4}, {-1, -4}, 
											{1, -4}, {2, -4}, {3, -4}, {4, -4},
											{4, -3}, {4, -2}, {4, -1}, {4, 1},
											{4, 2}, {4, 3}, {4, 4}, {3, 4}, 
											{2, 4}, {1, 4}, {-1, 4}, {-2, 4},
											{-3, 4}, {-4, 4}, {-4, 3}, {-4, 2},
											{-4, 1}, {-4, -1}, {-4, -2}, {-4, -3}};
		
		/*
		 * In the list listOfCoordinates we store the (x, y) coordinates of every new trap object,
		 * so as not to create two different traps with the same coordinates. The coordinates are
		 * represented by the number of row of the coordinates array.
		 */
		List<Integer> listOfCoordinates = new ArrayList<Integer>();
		
		int x, y, r, p;							// x the x coordinate
												// y the y coordinate
												// r the random number
												// p the points of the trap
		
		// Creating the animals
		for(int i = 0 ; i < (T / 2) ; i++) {
			while(true) {
				r = (int)(Math.random() * 28);
				if(!listOfCoordinates.contains(r)) {
					x = coordinates[r][0];
					y = coordinates[r][1];
					listOfCoordinates.add(r);
					break;
				}
			}
			p = -(int)(Math.random() * 10 + 1);
			traps[i] = new Trap(i, x, y, "animal", p);
		}
		
		// Creating the ropes
				for(int i = (T / 2) ; i < T ; i++) {
					while(true) {
						r = (int)(Math.random() * 28);
						if(!listOfCoordinates.contains(r)) {
							x = coordinates[r][0];
							y = coordinates[r][1];
							listOfCoordinates.add(r);
							break;
						}
					}
					p = -(int)(Math.random() * 10 + 1);
					traps[i] = new Trap(i, x, y, "ropes", p);
				}
	}
	
	/**
	 * The createRandomFood() method initializes the food array with random values. 
	 * We create F food objects.
	 */
	public void createRandomFood() {
		/*
		 * In the array coordinates we store the (x, y) coordinates of every square in the
		 * food area. We will use the function Math.random() to pick a random number
		 * between 0 and 19 (both inclusively). We will use this number as index for the array, 
		 * in order to find the correct coordinates.
		 */
		int[][] coordinates = new int[][] { {-3, -3}, {-2, -3}, {-1, -3}, {1, -3},
											{2, -3}, {3, -3}, {3, -2}, {3, -1},
											{3, 1}, {3, 2}, {3, 3}, {2, 3},
											{1, 3}, {-1, 3}, {-2, 3}, {-3, 3},
											{-3, 2}, {-3, 1}, {-3, -1}, {-3, -2}};
		
		/*
		 * In the list listOfCoordinates we store the (x, y) coordinates of every new food object,
		 * so as not to create two different food objects with the same coordinates. The coordinates 
		 * are represented by the number of row of the coordinates array.
		 */
		List<Integer> listOfCoordinates = new ArrayList<Integer>();
		
		int x, y, r, p;							// x the x coordinate
												// y the y coordinate
												// r the random number
												// p the points of the food supply
		
		// Creating the food objects
		for(int i = 0 ; i < F ; i++) {
			while(true) {
				r = (int)(Math.random() * 20);
				if(!listOfCoordinates.contains(r)) {
					x = coordinates[r][0];
					y = coordinates[r][1];
					listOfCoordinates.add(r);
					break;
				}
			}
			p = (int)(Math.random() * 10 + 1);
			food[i] = new Food(i, x, y, p);
		}
	}
	
	/**
	 * The createBoard() method creates randomly the board of the game.
	 */
	public void createBoard() {
		createRandomWeapon();
		createRandomTrap();
		createRandomFood();
		
	}
	
	/**
	 * The resizeBoard() method reduces the dimensions N, M of the board by two units.
	 * If the players aren't in the outer frame of the board, we can resize the board.
	 * @param p1 the first player of the game.
	 * @param p2 the second player of the game.
	 */
	public void resizeBoard(Player p1, Player p2) {
		/*
		 * The variable checkForResize is true if at least one of the players is 
		 * in the outer frame of the board. In this case we can't resize the board.
		 */
		boolean checkForResize = p1.getX() == (N / 2) || p1.getX() == -(N / 2) || 
								 p1.getY() == (M / 2) || p1.getY() == -(M / 2) ||
								 p2.getX() == (N / 2) || p2.getX() == -(N / 2) || 
								 p2.getY() == (M / 2) || p2.getY() == -(M / 2);
		if(!checkForResize) {
			N = N - 2;
			M = M - 2;
		}
	}
	
	/**
	 * The tileAsString() method (helper method) returns the string representation 
	 * of the tile at the given position (x, y).The tiles that don't have any object 
	 * are represented by "___". The tiles that have a weapon are represented by 
	 * "W + playerId + WeaponId" and the tiles that have a food supply or a trap are 
	 * represented by "F + FoodId" or "T + TrapId" respectively.
	 * @param x the x coordinate of the tile.
	 * @param y the y coordinate of the tile.
	 * @return the string representation of the given tile.
	 */
	public String tileAsString(int x, int y) {
		// Look for weapon in the tile
		for(int i = 0; i < W; i++) {
			if(weapons[i].getX() == x && weapons[i].getY() == y) {
				return "W" + weapons[i].getPlayerId() + weapons[i].getId(); // Tile with weapon
			}
		}
			
		// Look for food in the tile
		for(int i = 0; i < F; i++) {
			if(food[i].getX() == x && food[i].getY() == y) {
				return " F" + food[i].getId();  								// Tile with food
			}
		}
			
		// Look for trap in the tile
		for(int i = 0; i < T; i++) {
			if(traps[i].getX() == x && traps[i].getY() == y) {
				return " T" + traps[i].getId();  							// Tile with trap
			}
		}
			
		return "___"; 														// Empty tile
	}
			
	/**
	 * The x2j() method (helper method) converts from x coordinate to array index j.
	 * @param x the given x coordinate.
	 * @return the index j of the array.
	 */
	public int x2j(int x) {
		if(x > 0) 
			return x + M/2 - 1;
		 else 
			return x + M/2 ;
	}
		
	/**
	 * The y2i() method (helper method) converts from y coordinate to array index i.
	 * @param y the given y coordinate.
	 * @return the index i of the array.
	 */
	public int y2i(int y) {
		if(y > 0) 
			return y + N/2 - 1;
		else 
			return y + N/2;
	}
	
	/**
	 * The getStringRepresentation() method returns a string array that represents 
	 * the board of the game.
	 * @return a string array that represents the board of the game.
	 */
	public String[][] getStringRepresentation() {
		String[][] stringBoard = new String[N][M];
		for(int x = -N/2; x <= N/2; x++) {
			for(int y = -M/2; y <= M/2; y++) {
					stringBoard[y2i(y)][x2j(x)] = tileAsString(x, y);
			}
		}
		return stringBoard;
	}
	
	/**
	 * The isPositionValid() method (helper method) checks if the given position (x, y)
	 * lies inside the board area.
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 * @return true if the given position is valid.
	 */
	public boolean isPositionValid(int x, int y) {
		return (x >= -N/2 && x <= N/2 && y >= -M/2 && y <= M/2);
	}
	
}
