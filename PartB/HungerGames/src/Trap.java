/**
 * Class denoting a trap (ropes, animals).
 */
public class Trap {
	private int id;
	private int x;
	private int y;
	private String type;
	private int points;
	
	/**
	 * Initializes the Trap by setting its id, x and y coordinates
	 * and points to zero.
	 */
	Trap(){
		id = 0;
		x = 0;
		y = 0;
		type = "";
		points = 0;
	}
	
	/**
	 * Initializes the trap by setting its id, x and y coordinates,
	 * type (ropes, animals), points.
	 * @param id the id of the trap.
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 * @param type the type of the weapon.
	 * @param points the points of the trap.
	 */
	Trap(int id, int x, int y, String type, int points){
		this.id = id;
		this.x = x;
		this.y = y;
		this.type = type;
		this.points = points;
	}
	
	/**
	 * Initializes the trap by an other trap object.
	 * @param t the trap object.
	 */
	Trap(Trap t){
		id = t.id;
		x = t.x;
		y = t.y;
		type = t.type;
		points = t.points;
	}
	
	/**
	 * The setId() method sets the id of the trap.
	 * @param id the id to be set.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * The setX() method sets the x coordinate of the trap.
	 * @param x the x value to be set.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * The setY() method sets the y coordinate of the trap.
	 * @param y the y value to be set.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * The setType() method sets the type of the trap.
	 * @param type the type to be set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * The setPoints() method sets the points that the player loses when they find a trap.
	 * @param points the points to be set.
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * The getId() method returns the id of the trap.
	 * @return the id of the trap.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * The getX() method returns the x coordinate of the trap.
	 * @return the x coordinate of the trap.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * The getY() method returns the y coordinate of the trap.
	 * @return the y coordinate of the trap.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * The getType() method returns the type of the trap.
	 * @return the type of the trap.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * The getPoints() method returns the points that the player loses when they find the trap.
	 * @return the points of the trap.
	 */
	public int getPoints() {
		return points;
	}

}
