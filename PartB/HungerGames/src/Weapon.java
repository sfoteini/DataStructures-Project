/** 
 * Class denoting a weapon (pistol, bow, sword).
 */
public class Weapon {
	private int id;
	private int x;
	private int y;
	private int playerId;
	private String type;
	
	/**
	 * Initializes the weapon by setting its id, x and y coordinates,
	 * player id to zero.
	 */
	Weapon(){
		id = 0;
		x = 0;
		y = 0;
		playerId = 0;
		type = "";
	}
	
	/**
	 * Initializes the weapon by setting its id, x and y coordinates,
	 * player id and type (pistol, bow, sword).
	 * @param id the id of the weapon.
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 * @param playerId the player who owns the weapon.
	 * @param type the type of the weapon.
	 */
	Weapon(int id, int x, int y, int playerId, String type){
		this.id = id;
		this.x = x;
		this.y = y;
		this.playerId = playerId;
		this.type = type;
	}
	
	/**
	 * Initializes the weapon by an other weapon object.
	 * @param w a weapon object.
	 */
	Weapon(Weapon w){
		id = w.id;
		x = w.x;
		y = w.y;
		playerId = w.playerId;
		type = w.type;
	}
	
	/**
	 * The setId() method sets the id of the weapon.
	 * @param id the id to be set.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * The setX() method sets the x coordinate of the weapon.
	 * @param x the x value to be set.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * The setY() method sets the y coordinate of the weapon.
	 * @param y the y value to be set.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * The setPlayerId() method sets the id of the player who owns the weapon.
	 * @param playerId the player's id to be set.
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	/**
	 * The setType() method sets the type of the weapon.
	 * @param type the type to be set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * The getId() method returns the id of the weapon.
	 * @return the id of the weapon.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * The getX() method returns the x coordinate of the weapon.
	 * @return the x coordinate of the weapon.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * The getY() method returns the y coordinate of the weapon.
	 * @return the y coordinate of the weapon.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * The getPlayerId() method returns the id of the player who owns the weapon.
	 * @return the id of the player.
	 */
	public int getPlayerId() {
		return playerId;
	}
	
	/**
	 * The getType() method returns the type of the weapon.
	 * @return the type of the weapon.
	 */
	public String getType() {
		return type;
	}

}
